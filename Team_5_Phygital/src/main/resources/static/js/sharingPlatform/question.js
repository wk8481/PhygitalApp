const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateProject);
const link = window.location.href.substring(window.location.href);

var ids = extractIdsFromUrl(window.location.href.substring(window.location.href))
const subThemeId = ids[0]
const questionId = ids[1]
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



function extractIdsFromUrl(url) {
    // Define the regular expression pattern to match IDs
    var pattern = /\/(\d+)\/question\/(\d+)/;

    // Execute the regular expression on the URL
    var match = url.match(pattern);

    // If match is found, extract the IDs
    if (match) {
        var firstId = match[1];
        var secondId = match[2];
        return [firstId, secondId];
    } else {
        // Return null or handle error
        return null;
    }
}
