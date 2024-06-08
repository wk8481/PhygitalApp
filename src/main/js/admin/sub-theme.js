import {header, token} from '../util/csrf.js'
import {extractIdsFromUrl} from '../utils.js' // Adjust the path as per your file structure

const saveButton = document.querySelector('#saveButton')
const deleteButton = document.querySelector('#deleteButton')
const [flowId, subThemeId] = extractIdsFromUrl(window.location.href.substring(window.location.href), 'sub-theme')

saveButton.addEventListener('click', updateSubTheme)
deleteButton.addEventListener('click', deleteSubTheme)

async function updateSubTheme(event) {
    const name = document.getElementById("nameInput").value;
    const info = document.getElementById("infoInput").value;
    const mediaUrl = document.getElementById('mediaUrlInput').value;
    const isVisible = document.getElementById("isVisibleInput").checked;

    console.log("Updating sub-theme")

    const body = {
        id: subThemeId, name: name, information: info, mediaUrl: mediaUrl, isVisible: isVisible
    };

    if (mediaUrl) {
        body.mediaUrl = mediaUrl;
    }

    fetch(`/api/sub-themes/${subThemeId}`, {
        method: 'PATCH', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify(body)
    })
        .then(async (response) => {
            if (response.status === 204) {
                console.log('Sub theme updated successfully');
            } else {
                console.error('Failed to update sub theme');
            }
        })
        .catch(error => {
            console.error('Error updating sub theme:', error);
        });
}

async function deleteSubTheme(event) {
    console.log('Deleting sub theme')
    const response = await fetch(`/api/sub-themes/${subThemeId}`, {
        method: 'DELETE', headers: {
            [header]: token
        }
    })
    if (response.ok) {
        window.history.back()
    }
}
