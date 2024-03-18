const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateProject);
const link = window.location.href.substring(window.location.href);

var [platformId, superVisorId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "supervisor")

function updateProject(event){
    console.log(platformId, superVisorId)
    const name = document.getElementById("nameInput").value;
    const email = document.getElementById("emailInput").value;

    console.log("updating supervisor to " + name )
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
    alert("Supervisor name changed to: " + name)
}


