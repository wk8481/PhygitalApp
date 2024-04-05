const name = document.getElementById("nameInput").value;
const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
const [platformId, projectId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "project");

saveButton.addEventListener("click", updateProject);
deleteButton.addEventListener("click", deleteProject);

async function updateProject(event) {
    console.log("Updating project")
    fetch(`/api/projects/${projectId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', "Content-Type": "application/json"
        }, body: JSON.stringify({
            "id": projectId, "name": name
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}

async function deleteProject(event) {
    console.log("Deleting project")
    const response = await fetch(`/api/projects/${projectId}`, {
        method: "DELETE"
    });
}
