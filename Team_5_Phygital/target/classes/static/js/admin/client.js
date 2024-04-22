import {header, token} from "../util/csrf.js";

const submitButton = document.querySelector("#saveButton");
const [platformId, clientId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "client")
submitButton.addEventListener("click", updateClient);

async function updateClient(event) {
    const name = document.getElementById("nameInput").value;
    const email = document.getElementById("emailInput").value;

    console.log("updating client to " + name)
    fetch(`/api/clients/${clientId}`, {
        method: "PATCH", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            "id": clientId, "name": name, "email": email
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}
