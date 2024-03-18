const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateProject);
const link = window.location.href.substring(window.location.href);

var ids = extractIdsFromUrl(window.location.href.substring(window.location.href))
const projectId = ids[1]
const platformId = ids[0]
function updateProject(event){
    const name = document.getElementById("nameInput").value;

    console.log("updating project to " + name )
    fetch(`/api/admin/${platformId}/project/${projectId}/update`, {
        method: "PATCH",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        body: JSON.stringify(
            {
                "id": projectId,
                "name": name

            })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
    alert("Project name changed to " + name)
}



function extractIdsFromUrl(url) {
    // Define the regular expression pattern to match IDs
    var pattern = /\/(\d+)\/project\/(\d+)/;

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
