import {header, token} from "../util/csrf.js";

const createButton = document.getElementById("createButton");
const projectId = extractIdsFromUrl(window.location.href.substring(window.location.href), "flow");

createButton.addEventListener("click", addNewSubTheme);

async function addNewSubTheme() {
    const name = document.getElementById("nameInput").value;
    const installationId = document.getElementById("installationInput").value;
    await fetch(`/api/flows`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            name: name, projectId: projectId, installationId: installationId
        })
    });
}
