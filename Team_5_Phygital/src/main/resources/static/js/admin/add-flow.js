import {header, token} from "../util/csrf.js";

const createButton = document.getElementById("createButton");
const projectId = extractIdsFromUrl(window.location.href.substring(window.location.href), "flow");

createButton.addEventListener("click", addNewSubTheme);

async function addNewSubTheme() {
    const nameInput = document.getElementById("nameInput").value;
    await fetch(`/api/flows`, {
        method: "POST", headers: {
            'Accept': 'application/json', [header]: token
        }, body: JSON.stringify({
            name: nameInput, projectId: projectId
        })
    });
}
