import {header, token} from "../util/csrf.js";

const nameInput = document.getElementById("nameInput");
const createButton = document.getElementById("createButton");

createButton.addEventListener("click", addNewInstallation);

async function addNewInstallation() {
    await fetch(`/api/installations`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            name: nameInput.value,
        })
    });
}
