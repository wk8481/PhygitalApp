const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updatePlatform);
const link = window.location.href.substring(window.location.href);

var platformId = extractIdsFromUrl(window.location.href.substring(window.location.href), "platform");

function updatePlatform(event) {
    const name = document.getElementById("nameInput").value;

    console.log("updating platform to " + name)
    fetch(`/api/admin/platform/${platformId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', "Content-Type": "application/json"
        }, body: JSON.stringify({
            "id": platformId, "name": name

        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}