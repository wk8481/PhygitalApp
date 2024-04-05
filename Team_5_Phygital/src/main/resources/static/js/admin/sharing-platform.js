const submitButton = document.querySelector("#saveButton");
const link = window.location.href.substring(window.location.href);
const sharingPlatformId = extractIdsFromUrl(window.location.href.substring(window.location.href), "platform");

submitButton.addEventListener("click", updatePlatform);

async function updatePlatform(event) {
    const name = document.getElementById("nameInput").value;

    console.log("updating platform to " + name)
    fetch(`/api/sharing-platforms/${sharingPlatformId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', "Content-Type": "application/json"
        }, body: JSON.stringify({
            "id": sharingPlatformId, "name": name

        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}
