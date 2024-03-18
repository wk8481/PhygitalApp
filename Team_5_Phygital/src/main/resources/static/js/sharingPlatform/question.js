const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateProject);
const link = window.location.href.substring(window.location.href);

var [subThemeId, questionId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "question")
function updateProject(event){
    const question = document.getElementById("textInput").value;
    const questionType = document.getElementById("questionTypeInput").value;

    console.log("updating question to " + question )
    fetch(`/api/sharing-platform/sub-theme/${subThemeId}/question/${questionId}/update`, {
        method: "PATCH",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        body: JSON.stringify(
            {
                "id": questionId,
                "text": question,
                "type": questionType

            })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
    alert("question changed to " + question)
}