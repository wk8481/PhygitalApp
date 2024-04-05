const nameInput = document.getElementById("nameInput");
const createButton = document.getElementById("createButton");

createButton?.addEventListener("click", addNewSubTheme);

async function addNewSubTheme() {
    await fetch(`/api/sub-themes`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            name: nameInput.value
        })
    });
}
