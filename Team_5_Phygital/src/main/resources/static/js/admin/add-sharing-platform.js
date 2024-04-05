const nameInput = document.getElementById("nameInput");
const createButton = document.getElementById("createButton");

createButton?.addEventListener("click", addNewSharingPlatform);

async function addNewSharingPlatform() {
    await fetch(`/api/sharing-platforms/`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            name: nameInput.value
        })
    });
}
