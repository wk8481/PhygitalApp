const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updatePlatform);
const link = window.location.href.substring(window.location.href);

const platformId = extractIdsFromUrl(window.location.href.substring(window.location.href))
function updatePlatform(event){
    const name = document.getElementById("nameInput").value;

    console.log("updating platform to " + name )
    fetch(`/api/admin/${platformId}/update`, {
        method: "PATCH",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        body: JSON.stringify(
            {
                "id": platformId,
                "name": name

            })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}



function extractIdsFromUrl(url) {
    // Define the regular expression pattern to match IDs
    const pattern = /platform\/(\d+)/;

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
