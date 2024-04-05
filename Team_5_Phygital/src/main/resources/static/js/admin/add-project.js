const nameInput = document.getElementById("nameInput");
const createButton = document.getElementById("createButton");

createButton?.addEventListener("click", addNewProject);

async function addNewProject() {
    await fetch(`/api/projects`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            name: nameInput.value
        })
    });
}
