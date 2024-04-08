const name = document.getElementById("nameInput");
const contactEmail = document.getElementById("contactEmailInput");
const createButton = document.getElementById("createButton");

createButton.addEventListener("click", addNewSharingPlatform);

async function addNewSharingPlatform() {
    await fetch(`/api/sharing-platforms/`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            name: name.value, contactEmail: contactEmail.value
        })
    });
}