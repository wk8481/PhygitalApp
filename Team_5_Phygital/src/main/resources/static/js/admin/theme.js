import {header, token} from "../util/csrf.js";

const submitButton = document.querySelector("#saveButton");
const projectId = extractIdsFromUrl(window.location.href.substring(window.location.href), "project");

submitButton.addEventListener("click", updateTheme);

async function updateTheme(event) {
    const name = document.getElementById("nameInput").value;
    const info = document.getElementById("infoInput").value;

    console.log("updating sub theme to " + name + " and its info to " + info)
    fetch(`/api/themes/{projectId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', [header]: token
        }, body: JSON.stringify({
            "id": projectId, "name": name, "information": info
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}
