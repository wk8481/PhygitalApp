
const createButton = document.getElementById("createButton");
const platformId = extractIdsFromUrl(window.location.href.substring(window.location.href), "project");

createButton.addEventListener("click", addNewProject);

async function addNewProject() {
    const nameInput = document.getElementById("nameInput");
    await fetch(`/api/projects`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            name: nameInput.value,
            sharingPlatformId: platformId
        })
    });
}
