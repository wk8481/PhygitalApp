import {header, token} from '../util/csrf.js'
import {extractIdsFromUrl} from '../utils.js' // Adjust the path as per your file structure

const saveButton = document.getElementById('saveButton')
const deleteButton = document.getElementById('deleteButton')
const [platformId, projectId] = extractIdsFromUrl(window.location.href.substring(window.location.href), 'project')

saveButton.addEventListener('click', updateProject)
deleteButton.addEventListener('click', deleteProject)

async function updateProject(event) {
    const name = document.getElementById('nameInput').value
    const bgColor = document.getElementById('bgColorInput').value
    const fontName = document.getElementById('fontNameInput').value
    const logoUrl = document.getElementById('logoUrlInput').value
    const isPublic = document.getElementById('isPublicInput').checked

    console.log('Updating project')

    const body = {
        id: projectId, name: name, backgroundColorHex: bgColor, fontName: fontName, logoUrl: logoUrl, isPublic: isPublic
    }

    if (logoUrl) {
        body.logoUrl = logoUrl;
    }

    fetch(`/api/projects/${projectId}`, {
        method: 'PATCH', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify(body)
    })
        .then(async (response) => {
            if (response.status === 204) {
                console.log('Project updated successfully');
            } else {
                console.error('Failed to update project');
            }
        })
        .catch(error => {
            console.error('Error updating project:', error);
        });
}


async function deleteProject(event) {
    console.log('Deleting project')
    const response = await fetch(`/api/projects/${projectId}`, {
        method: 'DELETE', headers: {
            [header]: token
        }
    })
    if (response.ok) {
        window.history.back()
    }
}
