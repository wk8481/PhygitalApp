const nameInput = document.getElementById("nameInput");
const createButton = document.getElementById("createButton");

createButton?.addEventListener("click", addNewSupervisor);

async function addNewSupervisor() {
    await fetch(`/api/supervisors`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            name: nameInput.value
        })
    });
}
