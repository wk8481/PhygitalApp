const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateQuestion);
const link = window.location.href.substring(window.location.href);

const [subThemeId, questionId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "question");

function updateQuestion(event) {
    const question = document.getElementById("textInput").value;
    const questionType = document.getElementById("questionTypeInput").value;

    console.log("updating question to " + question)
    fetch(`/api/questions/${questionId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', "Content-Type": "application/json"
        }, body: JSON.stringify({
            "id": questionId, "text": question, "type": questionType
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}