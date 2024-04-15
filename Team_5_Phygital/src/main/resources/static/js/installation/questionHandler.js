document.getElementById("submit").addEventListener("click", submitAnswer);
document.getElementById("nextButton").addEventListener("click", moveToNextQuestion);
document.getElementById("backButton").addEventListener("click", moveToPreviousQuestion);
// document.getElementById("updateButton").addEventListener("click", updateQuestion);
var questionDivs = document.querySelectorAll('div[id*=question]');

for (let i = 0; i < questionDivs.length; i++) {
    let questionNr = document.getElementById("question"+i)
    let questionId = questionNr.querySelector("h2").id.split("_")[1]
    // Determine answer based on question type

}
// console.log(questionId)
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
}
console.log(questionDivs.length)

// Function to handle submitting answers
function submitAnswer(event) {
    event.preventDefault();

    var answers = []

    for (let i = 0; i < questionDivs.length; i++) {
        let answer;
        console.log("analising answer of question: " + i)

        let questionNr = document.getElementById("question" + i)
        let questionId = questionNr.querySelector("h2").id.split("_")[1]

        let answerName = "answer"+i
        switch (questionNr.querySelector("div").querySelector("div").id) {
            case "open":
                answer = document.getElementById("answerInput" + i).value
                break
            case "multipleChoice":
                answer = ""
                const s = document.querySelectorAll('input[name='+answerName+']:checked')
                for (let sElement of s) {
                    answer += sElement.value
                    answer += ", "
                }

                answer = answer.slice(0, -2); // Delete last two characters
                break
            case "range":
                answer = document.getElementsByClassName('range').item(0).value;
                break
            case "singleChoice":

                answer = document.querySelector('input[name='+answerName+']:checked').value
                break
        }

        // console.log(answer)
        answers.push({question: questionNr, questionId: questionId , Answer: answer})
    }
    console.log(answers)
return




    console.log("Submitting answer: " + answer);

    fetch(`/api/questions/submit`, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "questionId": questionId,
            "answer": answer,
            "questionType": "OPEN"  // Replace with appropriate question type
        })
    })
        .then(response => {
            if (response.ok) {
                console.log("Answer submitted successfully.");
                moveToNextQuestion();
            } else {
                console.error("Failed to submit answer.");
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

let currentIndex = 0;

// Function to move to the next question
function moveToNextQuestion() {
    // Implement logic to move to the next question
    console.log("Moving to next question");
    // Example: window.location.href = "/next-question";
    if (currentIndex < questionDivs.length - 1) {
        currentIndex++;
        showQuestion(currentIndex);
    }
}

// Function to move to the previous question
function moveToPreviousQuestion() {
    // Implement logic to move to the previous question
    console.log("Moving to previous question");
    // Example: window.location.href = "/previous-question";
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

// Range slider change event listener
let rangeInput = document.getElementsByClassName('range').item(0);
let rangeValue = document.getElementById('rangeValue');
rangeInput.addEventListener("input", function() {
    rangeValue.textContent = "Value: " + rangeInput.value;

});
