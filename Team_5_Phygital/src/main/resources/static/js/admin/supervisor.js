const name = document.getElementById("nameInput").value;
const email = document.getElementById("emailInput").value;
const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
const [sharingPlatformId, supervisorId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "supervisor");

saveButton.addEventListener("click", updateSupervisor);
deleteButton.addEventListener("click", deleteSupervisor);

async function updateSupervisor(event) {
    console.log("Updating supervisor")
    fetch(`/api/supervisors/${supervisorId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', "Content-Type": "application/json"
        }, body: JSON.stringify({
            "id": supervisorId, "name": name, "email": email, "platform": sharingPlatformId
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}

async function deleteSupervisor(event) {
    console.log("Deleting supervisor")
    const response = await fetch(`/api/supervisors/${supervisorId}`, {
        method: "DELETE"
    });
}
