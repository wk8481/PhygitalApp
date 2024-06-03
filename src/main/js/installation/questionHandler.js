import {header, token} from "../util/csrf.js";
let isCircular

var isCircularExists = document.getElementById("minUser");

let dataArray = [];

function parseContent() {
    var content = document.querySelectorAll("#rangeContent p");

    content.forEach(function(element) {
        var numbers = element.innerText.split(' ');
        var questionId = parseInt(numbers[0]);
        var answer = parseInt(numbers[1]);
        dataArray.push({ questionId: questionId, answer: answer });
    });
    const quotient = Math.floor(dataArray.length / 3);

    for (let i = 0; i < quotient; i++) {
        let id = dataArray[0].questionId
        let min = dataArray[0].answer
        let step = dataArray[1].answer
        let max = dataArray[2].answer
        dataArray.shift()
        dataArray.shift()
        dataArray.shift()

        console.log("id min step max ", id, min, step, max)

        updateSliders(id, min, step, max)
    }

    return dataArray;
}

function updateSliders(questionId, min, step, max) {
    var slider = document.getElementById("sliderQuestionId_" + questionId);
        slider.min = min;
        slider.step = step;
        slider.max = max;
        slider.defaultValue = (max+min)/2;
}

parseContent()


document.getElementById("submit").style.visibility = "hidden"
let currentIndex = 0;
if (isCircularExists !== null) {
    isCircular = true
    document.getElementById("minUser").addEventListener("click", minUser);
    document.getElementById("plusUser").addEventListener("click", plusUser);
    document.getElementById("nextButton").classList.remove("btn-secondary")
    document.getElementById("nextButton").classList.add("btn-primary")
} else {
    isCircular = false
}

document.getElementById("submit").addEventListener("click", submitAnswer);
document.getElementById("nextButton").addEventListener("click", moveToNextQuestion);
document.getElementById("backButton").addEventListener("click", moveToPreviousQuestion);
document.getElementById("pauseButton").addEventListener("click", togglePause);
const userCount = document.getElementById("userCount")
let queue = 0

function minUser(){
    if (queue>0){
        queue--
    }
    userCount.innerText = "There are/is " + queue + " people in the queue."
}

function plusUser(){
    if (queue < 15) {
        queue++
    }
    userCount.innerHTML = "There are/is " + queue + " people in the queue."

}

var elapsedTime =0
var seconds =0
let minSeconds = 0

// Start time when the page loads
var startTime = new Date().getTime();

function trackTime() {

    // Function to calculate and display time spent

    displayTime()
    // Update time every second
    timerInterval = setInterval(displayTime, 1000);
}

// Call trackTime() when the page loads
trackTime();
function displayTime() {
    var currentTime = new Date().getTime();
    elapsedTime = currentTime - startTime;
    elapsedTime -= minSeconds
    seconds = Math.floor(elapsedTime / 1000);
    var minutes = Math.floor(seconds / 60);

    var secondsTodisplay =  seconds % 60;
    // Display time on the page
    document.getElementById("timer").innerHTML = "Time spent on the page: " + minutes + "m " + secondsTodisplay + "s";
}


// JavaScript to toggle between pause and resume states
var paused = false; // Initially, page is not paused
var timerInterval
// Function to toggle pause state and update button
var startPause
function togglePause() {
    paused = !paused; // Toggle pause state

    // Update button text and icon based on pause state
    var button = document.getElementById("pauseButton");
    const textArea = document.getElementById("notesField")
    const form = document.getElementById("questionForm")

    if (paused) {
        // Page is paused
        startPause = new Date().getTime();
        button.innerHTML = '';
        button.innerHTML = '<i class="fas fa-play"></i>';
        document.getElementById("blockedOverlay").style.display = "block"; // Display the overlay
        form.style.display = "none"
        textArea.style.display = "block"
        clearInterval(timerInterval)
    } else {
        // Page is resumed
        var endPause = new Date().getTime();
        button.innerHTML = ''
        button.innerHTML = '<i class="fas fa-pause"></i>';
        document.getElementById("blockedOverlay").style.display = "none"; // Dont display the overlay
        minSeconds = endPause - startPause + minSeconds
        form.style.display = "block"
        textArea.style.display = "none"

        timerInterval = setInterval(displayTime, 1000);
    }

    // Perform other actions related to pausing or resuming the page
    // For example, you can pause or resume certain functionality here
}



var questionDivs = document.querySelectorAll('div[id*=question]');

for (let i = 0; i < questionDivs.length; i++) {
    let questionNr = document.getElementById("question"+i)
    let questionId = questionNr.querySelector("h2").id.split("_")[1]
    // Determine answer based on question type

}
window.onload = function () {
    questionDivs.forEach(function(div) {
        // Check if the div's ID is "question0"
        if (div.id === 'question0') {
            // If it is, show the div
            div.style.display = 'block';
        } else if (div.id.includes("question")) {
            // If not, hide the div
            div.style.display = 'none';
        }
    });
    document.getElementById("none").style.display = "none"
}



// Function to handle submitting answers
function submitAnswer(event) {
    if (event != null) {
        event.preventDefault();
    }

    let answers = ""
    let questions = ""

    if (!isCircular) {
        for (let i = 0; i < questionDivs.length; i++) {
            let answer;
            let question = document.getElementById("question_" + i).textContent;
            let questionNr = document.getElementById("question" + i)
            let questionId = questionNr.querySelector("h2").id.split("_")[1]

            let answerName = "answer" + i
            switch (questionNr.querySelector("div").querySelector("div").id) {
                case "open":
                    answer = document.getElementById(answerName).value
                    break
                case "multipleChoice":
                    answer = ""
                    const inputs = document.querySelectorAll('input[name="' + answerName + '"]');

                    inputs.forEach(input => {
                        if (input.checked) {
                            answer += input.value + ', ';
                        }
                    });

                    // Remove the trailing comma and space
                    if (answer.endsWith(', ')) {
                        answer = answer.slice(0, -2);
                    }
                    break
                case "range":
                    answer = document.getElementById(answerName).value;
                    break
                case "singleChoice":

                    answer = document.querySelector('input[name=' + answerName + ']:checked').value
                    break
            }

            answers += answer
            answers += " | "
            questions += questionId
            questions += " | "
        }
    } else {
        let answer;
        let question = document.getElementById("question_" + currentIndex).textContent;
        let questionNr = document.getElementById("question" + currentIndex)
        let questionId = questionNr.querySelector("h2").id.split("_")[1]

        let answerName = "answer" + currentIndex
        console.log(answerName)
        switch (questionNr.querySelector("div").querySelector("div").id) {
            case "open":
                answer = document.getElementById(answerName).value
                break
            case "multipleChoice":
                answer = ""
                const s = document.querySelectorAll('input[name=' + answerName + ']:checked')
                for (let sElement of s) {
                    answer += sElement.value
                    answer += ", "
                }

                answer = answer.slice(0, -2); // Delete last two characters
                break
            case "range":
                answer = document.getElementsByName(answerName).item(0).value;
                break
            case "singleChoice":
                answer = document.querySelector('input[name=' + answerName + ']:checked').value
                break
        }

        answers += answer
        answers += " | "
        questions += questionId
        questions += " | "
    }

    const user = document.getElementById("userMail").textContent

    const textArea = document.getElementById("notesField")
    var urlParams = new URLSearchParams(window.location.search);

    answers = answers.slice(0, -1); // Delete last character
    questions = questions.slice(0, -1); // Delete last character
    document.getElementById("questionForm").reset()
    // Get the value of the "subThemeId" parameter
    var subtheme = urlParams.get("subThemeId");
    fetch(`/api/questions/submit`, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            [header]: token
        },
        body: JSON.stringify({
            question: questions,
            answer: answers,
            userMail: user,
            subThemeId: subtheme,
            durationSpend: seconds,
            note: textArea.value
        })
    })
        .then(response => {
            if (response.ok) {
                console.log("Answer submitted successfully.");
                if (!isCircular) {
                    window.location.href = `/installation/flowCompleted`;
                }
            } else {
                console.error("Failed to submit answer.");
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


function moveToNextQuestion() {
    if (isCircular) {
        submitAnswer()
    }
    if (queue === 0){
        if (currentIndex < questionDivs.length - 1) {
            currentIndex++;
            showQuestion(currentIndex);

        } else {
            if (isCircular) {
                currentIndex = 0
                showQuestion(currentIndex)
            }
    }
    } else {
        minUser()
    }

    if (!isCircular && currentIndex === questionDivs.length-1){
        document.getElementById("submit").style.visibility = "visible"

    }
}

function moveToPreviousQuestion() {
    if(!isCircular) {
        document.getElementById("submit").style.visibility = "hidden"
    }
    if (currentIndex > 0) {
        currentIndex--;
        showQuestion(currentIndex);
    }
}



function showQuestion(index) {

    for (var i = 0; i < questionDivs.length; i++) {
        questionDivs[i].style.display = 'none';
    }
    questionDivs[index].style.display = 'block';
}

// Range slider change event listener for all range inputs
let rangeInputs = document.getElementsByClassName('range');

Array.from(rangeInputs).forEach(function(rangeInput) {
    let rangeValue = rangeInput.nextElementSibling;
    rangeValue = rangeValue.nextElementSibling;
    rangeInput.addEventListener("input", function() {
        if (rangeValue != null) {
            rangeValue.textContent = "Value: " + rangeInput.value;
        }
    });
});