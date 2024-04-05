const question = document.getElementById("textInput").value;
const questionType = document.getElementById("questionTypeInput").value;
const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
const [subThemeId, questionId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "question");

saveButton.addEventListener("click", updateQuestion);
deleteButton.addEventListener("click", deleteQuestion);

async function updateQuestion(event) {
    console.log("Updating question")
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

async function deleteQuestion(event) {
    console.log("Deleting question")
    const response = await fetch(`/api/questions/${questionId}`, {
        method: "DELETE"
    });
}
