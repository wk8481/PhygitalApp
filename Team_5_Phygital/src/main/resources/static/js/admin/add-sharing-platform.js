
const createButton = document.getElementById("createButton");

createButton.addEventListener("click", addNewSharingPlatform);

async function addNewSharingPlatform() {
    const nameInput = document.getElementById("nameInput");
    await fetch(`/api/sharing-platforms/`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            name: nameInput.value
        })
    });
}
