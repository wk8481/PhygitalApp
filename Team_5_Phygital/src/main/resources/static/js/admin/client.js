const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateClient);
const link = window.location.href.substring(window.location.href);

var [platformId, clientId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "client")

function updateClient(event) {
    const name = document.getElementById("nameInput").value;
    const email = document.getElementById("emailInput").value;

    console.log("updating client to " + name)
    fetch(`/api/clients/${clientId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', "Content-Type": "application/json"
        }, body: JSON.stringify({
            "id": clientId, "name": name, "email": email
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}