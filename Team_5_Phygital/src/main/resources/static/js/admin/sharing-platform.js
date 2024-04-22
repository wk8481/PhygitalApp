import {header, token} from "../util/csrf.js";

const name = document.getElementById("nameInput");
const contactEmail = document.getElementById("contactEmailInput");
const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
const sharingPlatformId = extractIdsFromUrl(window.location.href.substring(window.location.href), "platform");

saveButton.addEventListener("click", updateSharingPlatform);
deleteButton.addEventListener("click", deleteSharingPlatform);

async function updateSharingPlatform(event) {
    console.log("Updating platform")
    fetch(`/api/sharing-platforms/${sharingPlatformId}`, {
        method: "PATCH", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            "id": sharingPlatformId, "name": name.value, "contactEmail": contactEmail.value
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}

async function deleteSharingPlatform(event) {
    console.log("Deleting sharing platform")
    const response = await fetch(`/api/sharing-platforms/${sharingPlatformId}`, {
        method: "DELETE", headers: {
            [header]: token
        }
    });
    if (response.ok) {
        window.history.back();
    }
}
