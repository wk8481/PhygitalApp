const submitButton = document.querySelector("#saveButton");
const link = window.location.href.substring(window.location.href);
const [projectId, flowId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "flow");

submitButton.addEventListener("click", updateFlow);

async function updateFlow(event) {
    const name = document.getElementById("nameInput").value;

    console.log("updating subtheme to " + name)
    fetch(`/api/flows/${flowId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', "Content-Type": "application/json"
        }, body: JSON.stringify({
            "id": flowId, "name": name,
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}
