// Function to extract IDs from URL
function extractIdsFromUrl(url, prefix) {
    const pattern = new RegExp(`(?:${prefix}\\/(\\d+))`, 'g');
    const matches = [...url.matchAll(pattern)];
    return matches.map(match => parseInt(match[1]));
}

// Function to update question
function updateQuestion(event) {
    event.preventDefault();

    const questionId = extractIdsFromUrl(window.location.href, "question")[0];
    const subThemeId = extractIdsFromUrl(window.location.href, "sub-theme")[0];

    const question = document.getElementById("textInput").value;
    const questionType = document.getElementById("questionTypeInput").value;

    console.log("Updating question to " + question);

    fetch(`/api/installation/questions/${questionId}`, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "id": questionId,
            "text": question,
            "type": questionType
        })
    })
        .then(response => {
            if (response.ok) {
                alert("Question updated successfully.");
            } else {
                alert("Failed to update question.");
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// Function to handle submitting answers
function submitAnswer(event) {
    event.preventDefault();

    const questionId = extractIdsFromUrl(window.location.href, "question")[0];
    let answer;

    // Determine answer based on question type
    if (document.getElementById("textInput")) {
        // Open question
        answer = document.getElementById("textInput").value;
    } else if (document.getElementById("closedAnswerInput")) {
        // Closed question
        answer = document.getElementById("closedAnswerInput").value;
    } else if (document.querySelector('input[name="answer"]:checked')) {
        // Multiple choice question
        answer = document.querySelector('input[name="answer"]:checked').value;
    } else if (document.getElementById("rangeInput")) {
        // Range question
        answer = document.getElementById("rangeInput").value;
    }

    console.log("Submitting answer: " + answer);

    fetch(`/api/installation/questions/submit`, {
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
                alert("Answer submitted successfully.");
                moveToNextQuestion();
            } else {
                alert("Failed to submit answer.");
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// Function to move to the next question
function moveToNextQuestion() {
    // Implement logic to move to the next question
    console.log("Moving to next question");
    // Example: window.location.href = "/next-question";
}

// Function to move to the previous question
function moveToPreviousQuestion() {
    // Implement logic to move to the previous question
    console.log("Moving to previous question");
    // Example: window.location.href = "/previous-question";
}

// Range slider change event listener
document.getElementById("rangeInput").addEventListener("input", function() {
    var rangeValue = document.getElementById("rangeInput").value;
    document.getElementById("rangeValue").innerText = "Value: " + rangeValue;
});

// Bind functions to buttons or elements
document.getElementById("submitButton").addEventListener("click", submitAnswer);
document.getElementById("nextButton").addEventListener("click", moveToNextQuestion);
document.getElementById("previousButton").addEventListener("click", moveToPreviousQuestion);
document.getElementById("updateButton").addEventListener("click", updateQuestion);
