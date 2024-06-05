import {header, token} from '../util/csrf.js'
import { extractIdsFromUrl } from '../utils.js' // Adjust the path as per your file structure



const createButton = document.getElementById('createButton')
const subThemeId = extractIdsFromUrl(window.location.href.substring(window.location.href), 'question')
createButton.addEventListener('click', addNewQuestion)
document.getElementById('addButton').addEventListener('click', addAnswerField)
document.getElementById('removeButton').addEventListener('click', removeAnswerField)
document.getElementById('questionTypeInput').addEventListener('change', changeButtonVisability)


let fields = document.getElementsByClassName('answer-input')
let rangeFields = document.getElementsByClassName('range')

let addButton = document.getElementById('addButton')
let minusButton = document.getElementById('removeButton')
function changeButtonVisability(){

    let questionType = document.getElementById('questionTypeInput').value
    if (questionType === 'MULTIPLE_CHOICE' || questionType === 'SINGLE_CHOICE') {

        addButton.style.visibility = 'visible'
        minusButton.style.visibility = 'visible'
        for (let i = 0; i < fields.length-2; i++) {
            let answerField = fields[i]
            if (answerField.style.visibility !== 'visible') {
                answerField.style.visibility = 'visible'
            }
        }
        for (let i = 0; i < rangeFields.length; i++) {
            rangeFields[i].style.visibility = 'hidden'
        }
    } else{
        addButton.style.visibility = 'hidden'
        minusButton.style.visibility = 'hidden'
        for (let field of fields) {
            field.style.visibility = 'hidden'
        }
        if (questionType === 'RANGE'){
            for (let i = 0; i < rangeFields.length; i++) {
                rangeFields[i].style.visibility = 'visible'
            }
        } else {
            for (let i = 0; i < rangeFields.length; i++) {
                rangeFields[i].style.visibility = 'hidden'
            }
        }
    }
}


function addAnswerField(){

    for (let field of fields) {
        if (field.style.visibility !== 'visible'){
            field.style.visibility = 'visible'
            break
        }
    }
}

function removeAnswerField(){
    let visibleCount = 0
    for (let field of fields) {
        if (field.style.visibility === 'visible') {
            visibleCount++
        }
    }

    if (visibleCount > 2) {
        for (let i = fields.length - 1; i >= 0; i--) {
            let field = fields[i]
            if (field.style.visibility === 'visible') {
                field.style.visibility = 'hidden'
                break
            }
        }
    }
}
async function addNewQuestion() {
    let rangeFields = document.getElementsByClassName('range')

    fields = document.getElementsByClassName('answer-input')
    let questionType = document.getElementById('questionTypeInput').value
    let answer = []
    if (questionType === 'MULTIPLE_CHOICE' || questionType === 'SINGLE_CHOICE' || questionType === 'RANGE') {
        let visibleCount = 0
        for (let field of fields) {
            if (field.style.visibility === 'visible') {
                if (questionType === 'RANGE'){
                    if (parseInt(rangeFields[0].value) < parseInt(rangeFields[2].value) && parseInt(rangeFields[1].value) < parseInt(rangeFields[2].value)){
                        answer.push(field.value)
                        visibleCount++
                    } else if (parseInt(rangeFields[0].value) > parseInt(rangeFields[2].value) ){
                        alert('Check the red field ( ' +rangeFields[0].placeholder +' ) for mistakes, minimum value should be less than maximum')
                        rangeFields[0].style.borderColor = 'red'
                        rangeFields[2].style.borderColor = 'red'
                        return
                    } else if(parseInt(rangeFields[1].value) > parseInt(rangeFields[2].value)){
                        alert('Check the red field ( ' +rangeFields[1].placeholder +' ) for mistakes, step value should be less than maximum')
                        rangeFields[1].style.borderColor = 'red'
                        rangeFields[2].style.borderColor = 'red'
                        return
                    }
                }else {
                    answer.push(field.value)
                    visibleCount++
                }
            }
        }
    }
    if (answer.length == null){
        answer = 0
    }
    const textInput = document.getElementById('textInput').value
    const type = document.getElementById('questionTypeInput').value
    await fetch('/api/questions', {
        method: 'POST', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify({
            text: textInput,
            type: type,
            subThemeId: subThemeId,
            answers: answer
        })
    }).then(response => {
        if (response.status === 201) {
            window.history.back()
        }
    })
}
