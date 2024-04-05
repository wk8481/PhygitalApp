const nameInput = document.getElementById("nameInput");
const createButton = document.getElementById("createButton");

createButton?.addEventListener("click", addNewFlow);

async function addNewFlow() {
    await fetch(`/api/flow`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            name: nameInput.value
        })
    });
}
