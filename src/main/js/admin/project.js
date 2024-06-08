import {header, token} from '../util/csrf.js'
import {extractIdsFromUrl} from '../utils.js' // Adjust the path as per your file structure

const name = document.getElementById('nameInput')
const bgColor = document.getElementById('bgColorInput')
const font = document.getElementById('fontNameInput')
const logoUrl = document.getElementById('logoUrlInput')
const isPublic = document.getElementById('isPublicInput')
const saveButton = document.getElementById('saveButton')
const deleteButton = document.getElementById('deleteButton')
const [platformId, projectId] = extractIdsFromUrl(window.location.href.substring(window.location.href), 'project')

saveButton.addEventListener('click', updateProject)
deleteButton.addEventListener('click', deleteProject)

async function updateProject(event) {
    console.log("visible " + isPublic.checked.toString())
    console.log('Updating project')

    // Create a FormData object to append the form data, including the logo file
    const formData = new FormData()
    formData.append('id', projectId)
    formData.append('name', name.value)
    formData.append('backgroundColorHex', bgColor.value)
    formData.append('fontName', font.value)
    formData.append('logoUrl', logoUrl.value)
    formData.append('isVisible', isPublic.checked)

    try {
        const response = await fetch(`/api/projects/${projectId}`, {
            method: 'PATCH',
            headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json',
                [header]: token
            },
            body:
                // formData
                JSON.stringify({
                'id': projectId,
                'name': name.value,
                'backgroundColorHex': bgColor.value,
                'fontName': font.value,
                'logoUrl': logoUrl.value,
                'isVisible': isPublic.checked
            })
        })

        if (response.ok) {
            // Handle success
            console.log('Project updated successfully')
        } else {
            // Handle error
            console.error('Error updating project:', response.statusText)
        }
    } catch (error) {
        console.error('Error updating project:', error)
    }
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
