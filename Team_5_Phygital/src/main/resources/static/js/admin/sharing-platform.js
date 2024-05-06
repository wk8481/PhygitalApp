import {header, token} from "../util/csrf.js";

const name = document.getElementById("nameInput");
const contactEmail = document.getElementById("contactEmailInput");
const logo = document.getElementById("logoInput");
const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
const sharingPlatformId = extractIdsFromUrl(window.location.href.substring(window.location.href), "platform");

saveButton.addEventListener("click", updateSharingPlatform);
deleteButton.addEventListener("click", deleteSharingPlatform);

async function updateSharingPlatform(event) {
    console.log("Updating platform")

    // Create a FormData object to append the form data, including the logo file
    const formData = new FormData();
    formData.append("id", sharingPlatformId);
    formData.append("name", name.value);
    formData.append("contactEmail", contactEmail.value);
    formData.append("logo", logo.files[0]);

    try {
        const response = await fetch(`/api/sharing-platforms/${sharingPlatformId}`, {
            method: "PATCH",
            headers: {
                [header]: token
            },
            body: formData
        });

        if (response.ok) {
            // Handle success
            console.log("Sharing platform updated successfully");
        } else {
            // Handle error
            console.error("Error updating sharing platform:", response.statusText);
        }
    } catch (error) {
        console.error("Error updating sharing platform:", error);
    }
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
