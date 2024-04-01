const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateClient);
const link = window.location.href.substring(window.location.href);

const clientId = extractIdsFromUrl(window.location.href.substring(window.location.href))
function updateClient(event){
    const name = document.getElementById("nameInput").value;
    const email = document.getElementById("emailInput").value;

    console.log("updating client to " + name )
    fetch(`/api/admin/${clientId}/update`, {
        method: "PATCH",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        body: JSON.stringify(
            {
                "id": clientId,
                "name": name,
                "email": email
            })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}



function extractIdsFromUrl(url) {
    // Define the regular expression pattern to match IDs
    const pattern = /client\/(\d+)/;

    // Execute the regular expression on the URL
    const match = url.match(pattern);

    // If match is found, extract the IDs
    if (match) {
        return match;
    } else {
        // Return null or handle error
        return null;
    }
}
