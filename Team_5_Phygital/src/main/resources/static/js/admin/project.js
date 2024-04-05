const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateProject);
const link = window.location.href.substring(window.location.href);

const [platformId, projectId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "project");

function updateProject(event) {
    const name = document.getElementById("nameInput").value;

    console.log("updating project to " + name)
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