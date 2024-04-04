const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateTheme);
const link = window.location.href.substring(window.location.href);

const projectId = extractIdsFromUrl(window.location.href.substring(window.location.href), "project");

function updateTheme(event) {
    const name = document.getElementById("nameInput").value;
    const info = document.getElementById("infoInput").value;

    console.log("updating subtheme to " + name + " and its info to " + info)
    fetch(`/api/admin/project/${projectId}/theme`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', "Content-Type": "application/json"
        }, body: JSON.stringify({
            "id": projectId, "name": name, "information": info
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}