const submitButton = document.querySelector("#saveButton");
const link = window.location.href.substring(window.location.href);
const [sharingPlatformId, superVisorId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "supervisor");

submitButton.addEventListener("click", updateSupervisor);

async function updateSupervisor(event) {
    console.log(sharingPlatformId, superVisorId)
    const name = document.getElementById("nameInput").value;
    const email = document.getElementById("emailInput").value;

    console.log("updating supervisor to " + name)
    fetch(`/api/supervisors/${superVisorId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', "Content-Type": "application/json"
        }, body: JSON.stringify({
            "id": superVisorId, "name": name, "email": email, "platform": sharingPlatformId
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}
