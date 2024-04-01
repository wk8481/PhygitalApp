const submitButton = document.querySelector("#saveButton");

submitButton.addEventListener("click", updateProject);
const link = window.location.href.substring(window.location.href);

var [flowId, subThemeId] = extractIdsFromUrl(window.location.href.substring(window.location.href), "sub-theme")
function updateProject(event){
    const name = document.getElementById("nameInput").value;
    const info = document.getElementById("infoInput").value;

    console.log("updating subtheme to " + name + " and its info to " + info)
    fetch(`/api/sharing-platform/flow/${flowId}/sub-theme/${subThemeId}/update`, {
        method: "PATCH",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        body: JSON.stringify(
            {
                "id": subThemeId,
                "name": name,
                "information": info

            })
    })
        .then(response => {
            if (response.status === 204) {

            }
        });
}