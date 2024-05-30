import {header, token} from "../util/csrf.js";

const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
const [projectId, flowId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "flow");

saveButton.addEventListener("click", updateFlow);
deleteButton.addEventListener("click", deleteFlow);

async function updateFlow(event) {
    const name = document.getElementById("nameInput").value;
    console.log("Updating flow")
    fetch(`/api/flows/${flowId}`, {
        method: "PATCH", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            "id": flowId, "name": name,
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}

async function deleteFlow(event) {
    console.log("Deleting flow")
    const response = await fetch(`/api/flows/${flowId}`, {
        method: "DELETE", headers: {
            [header]: token
        }
    });
    if (response.ok){
        window.history.back();
    }
}
