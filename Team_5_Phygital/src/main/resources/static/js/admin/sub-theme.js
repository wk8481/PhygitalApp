const name = document.getElementById("nameInput").value;
const info = document.getElementById("infoInput").value;
const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
const [flowId, subThemeId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "sub-theme");

saveButton.addEventListener("click", updateSubTheme);
deleteButton.addEventListener("click", deleteSubTheme);

async function updateSubTheme(event) {
    console.log("Updating sub theme")
    fetch(`/api/sub-themes/${subThemeId}`, {
        method: "PATCH", headers: {
            'Accept': 'application/json', "Content-Type": "application/json"
        }, body: JSON.stringify({
            "id": subThemeId, "name": name, "information": info
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}

async function deleteSubTheme(event) {
    console.log("Deleting sub theme")
    const response = await fetch(`/api/sub-themes/${subThemeId}`, {
        method: "DELETE"
    });
}
