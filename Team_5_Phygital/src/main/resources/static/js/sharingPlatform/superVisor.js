const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateProject);
const link = window.location.href.substring(window.location.href);

var ids = extractIdsFromUrl(window.location.href.substring(window.location.href))
const platformId = ids[0]
const superVisorId = ids[1]


console.log(ids)
function updateProject(event){
    const name = document.getElementById("nameInput").value;
    const email = document.getElementById("emailInput").value;

    console.log("updating project to " + name )
    fetch(`/api/sharing-platform/platform/${platformId}/supervisor/${superVisorId}/update`, {
        method: "PATCH",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        body: JSON.stringify(
            {
                "id": superVisorId,
                "name": name,
                "email": email,
                "platform": platformId

            })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
    alert("Project name changed to: " + name)
}



function extractIdsFromUrl(url) {
    // Define the regular expression pattern to match IDs
    var pattern = /\/(\d+)\/supervisor\/(\d+)/;

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
