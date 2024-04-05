const submitButton = document.querySelector("#saveButton");
const link = window.location.href.substring(window.location.href);
const [platformId, clientId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "client")
submitButton.addEventListener("click", updateClient);

async function updateClient(event) {
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
