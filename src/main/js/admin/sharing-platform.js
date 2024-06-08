import {header, token} from '../util/csrf.js'
import { extractIdsFromUrl } from '../utils.js' // Adjust the path as per your file structure



const saveButton = document.querySelector('#saveButton')
const deleteButton = document.querySelector('#deleteButton')
saveButton.addEventListener('click', updateSharingPlatform)
deleteButton.addEventListener('click', deleteSharingPlatform)

async function updateSharingPlatform(event) {
    event.preventDefault() // Prevent the default form submission
    console.log('Updating platform')

    const name = document.getElementById('nameInput')
    const contactEmail = document.getElementById('contactEmailInput')
    const logo = document.getElementById('logoInput')
    const sharingPlatformId = extractIdsFromUrl(window.location.href, 'sharing-platform')
    const information = document.getElementById("informationInput")

    // Create a FormData object to append the form data, including the logo file
    const formData = new FormData()
    const dto = {
        id: sharingPlatformId,
        name: name.value,
        contactEmail: contactEmail.value,
        information: information.value
    }

    // Convert the JSON object to a Blob with MIME type application/json
    const dtoBlob = new Blob([JSON.stringify(dto)], { type: 'application/json' })
    formData.append('updateSharingPlatformDto', dtoBlob)
    formData.append('logo', logo.files[0])

    try {
        const response = await fetch(`/api/sharing-platforms/${sharingPlatformId}`, {
            method: 'PATCH',
            headers: {
                'Accept': 'application/json',
                [header]: token
            },
            body: formData // Send formData as-is
        })

        if (response.ok) {
            // Handle success
            console.log('Sharing platform updated successfully')
        } else {
            // Handle error
            console.error('Error updating sharing platform:', response.statusText)
        }
    } catch (error) {
        console.error('Error updating sharing platform:', error)
    }
}


async function deleteSharingPlatform(event) {
    console.log('Deleting sharing platform')
    const sharingPlatformId = extractIdsFromUrl(window.location.href, 'sharing-platform')

    const response = await fetch(`/api/sharing-platforms/${sharingPlatformId}`, {
        method: 'DELETE', headers: {
            [header]: token
        }
    })
    if (response.ok) {
        window.history.back()
    }
}
