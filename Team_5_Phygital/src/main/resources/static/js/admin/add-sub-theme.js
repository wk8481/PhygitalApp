
const createButton = document.getElementById("createButton");
const flowId = extractIdsFromUrl(window.location.href.substring(window.location.href), "sub-theme");

createButton.addEventListener("click", addNewSubTheme);

async function addNewSubTheme() {
    const nameInput = document.getElementById("nameInput").value;
    const information = document.getElementById("infoInput").value;
    await fetch(`/api/sub-themes`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            name: nameInput,
            information: information,
            flowId: flowId
        })
    });
}
