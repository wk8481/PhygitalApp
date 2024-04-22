import {header, token} from "../util/csrf.js";

const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
const [subThemeId, questionId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "question");

saveButton.addEventListener("click", updateQuestion);
deleteButton.addEventListener("click", deleteQuestion);

async function updateQuestion(event) {

    const question = document.getElementById("textInput").value;
    const questionType = document.getElementById("questionTypeInput").value;
    console.log("Updating question")
    fetch(`/api/questions/${questionId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', [header]: token
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
        method: "DELETE", headers: {
            [header]: token
        }
    });
    if (response.ok){
        window.history.back();
    }
}
