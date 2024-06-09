import {header, token} from '../util/csrf.js'
import { extractIdsFromUrl } from '../utils.js' // Adjust the path as per your file structure

const submitButton = document.querySelector('#saveButton')
const projectId = extractIdsFromUrl(window.location.href.substring(window.location.href), 'project')

submitButton.addEventListener('click', updateTheme)

async function updateTheme(event) {
    const name = document.getElementById('nameInput').value
    const info = document.getElementById('infoInput').value
    const mediaUrl = document.getElementById('mediaUrlInput').value;

    console.log("Updating theme")

    const body = {
        id: projectId, name: name, information: info, mediaUrl: mediaUrl
    };

    if (mediaUrl) {
        body.mediaUrl = mediaUrl;
    }

    fetch(`/api/themes/${projectId}`, {
        method: 'PATCH', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify(body)
    })
        .then(async (response) => {
            if (response.status === 204) {
                console.log('Theme updated successfully');
            } else {
                console.error('Failed to update theme');
            }
        })
        .catch(error => {
            console.error('Error updating theme:', error);
        });
}
