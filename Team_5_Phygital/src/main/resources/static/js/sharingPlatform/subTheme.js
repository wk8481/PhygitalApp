const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateProject);
const link = window.location.href.substring(window.location.href);

var ids = extractIdsFromUrl(window.location.href.substring(window.location.href))
const flowId = ids[0]
const subThemeId = ids[1]
function updateProject(event){
    const name = document.getElementById("nameInput").value;
    const info = document.getElementById("infoInput").value;

    console.log("updating subtheme to " + name + " and its info to " + info)
    fetch(`/api/sharing-platform/flow/${flowId}/sub-theme/${subThemeId}/update`, {
        method: "PATCH",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        body: JSON.stringify(
            {
                "id": subThemeId,
                "name": name,
                "information": info

            })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
    alert("Project name changed to " + name+ " and its info to " + info)
}



function extractIdsFromUrl(url) {
    // Define the regular expression pattern to match IDs
    var pattern = /\/(\d+)\/sub-theme\/(\d+)/;

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
