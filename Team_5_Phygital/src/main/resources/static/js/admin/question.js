import {header, token} from "../util/csrf.js";

document.getElementById("addButton").addEventListener("click", addAnswerField);
document.getElementById("removeButton").addEventListener("click", removeAnswerField);

const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
const [subThemeId, questionId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "question");

saveButton.addEventListener("click", updateQuestion);
deleteButton.addEventListener("click", deleteQuestion);

document.getElementById("addButton").addEventListener("click", addAnswerField);
document.getElementById("removeButton").addEventListener("click", removeAnswerField);
document.getElementById("questionTypeInput").addEventListener("change", changeButtonVisability)


let fields = document.getElementsByClassName("answer-input")

let addButton = document.getElementById("addButton");
let minusButton = document.getElementById("removeButton");
changeButtonVisability();

function changeButtonVisability(){

    let questionType = document.getElementById("questionTypeInput").value;
    if (questionType === "MULTIPLE_CHOICE" || questionType === "SINGLE_CHOICE") {
        addButton.style.visibility = "visible"
        minusButton.style.visibility = "visible"
        for (let i = 0; i < fields.length-2; i++) {
            let answerField = fields[i];
            if (answerField.style.visibility !== "visible") {
                answerField.style.visibility = "visible";
            }
        }
    } else{
        addButton.style.visibility = "hidden"
        minusButton.style.visibility = "hidden"
        for (let field of fields) {
            field.style.visibility = "hidden"
        }
    }
}
async function updateQuestion(event) {
    let answer = []

    const questionType = document.getElementById("questionTypeInput").value;
    if (questionType === "MULTIPLE_CHOICE" || questionType === "SINGLE_CHOICE") {
        let visibleCount = 0;
        for (let field of fields) {
            if (field.style.visibility === "visible") {
                answer.push(field.value)
                visibleCount++;
            }
        }
    }
    if (answer.length == null){
        answer = 0;
    }
    const question = document.getElementById("textInput").value;
    console.log("Updating question")
    fetch(`/api/questions/${questionId}`, {
        method: "PATCH", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            "id": questionId,
            "text": question,
            "type": questionType,
            "answers": answer
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}

async function deleteQuestion(event) {
    console.log("Deleting question")
    const response = await fetch(`/api/questions/${questionId}`, {
        method: "DELETE", headers: {
            [header]: token
        }
    });
    if (response.ok){
        window.history.back();
    }
}


function addAnswerField(){

    for (let field of fields) {
        if (field.style.visibility !== "visible"){
            console.log("showing 1 more")
            field.style.visibility = "visible"
            break;
        }
    }
}

function removeAnswerField(){
    let visibleCount = 0;
    for (let field of fields) {
        if (field.style.visibility === "visible") {
            visibleCount++;
        }
    }

    if (visibleCount > 2) {
        for (let i = fields.length - 1; i >= 0; i--) {
            let field = fields[i];
            if (field.style.visibility === "visible") {
                console.log("Showing 1 less")
                field.style.visibility = "hidden";
                break;
            }
        }
    }
}

