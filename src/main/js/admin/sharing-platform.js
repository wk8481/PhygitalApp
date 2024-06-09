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
    const logoUrl = document.getElementById('logoUrlInput')
    const sharingPlatformId = extractIdsFromUrl(window.location.href, 'sharing-platform')
    const information = document.getElementById("informationInput")

    console.log("Updating sharing platform")

    const body = {
        name: name.value,
        contactEmail: contactEmail.value,
        information: information.value,
        logoUrl: logoUrl.value
    }

    if (logoUrl) {
        body.logoUrl = logoUrl;
    }
    console.log(logoUrl.value)
    fetch(`/api/sharing-platforms/${sharingPlatformId}`, {
        method: 'PATCH', headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            [header]: token
        }, body:
            // JSON.stringify(body)
        JSON.stringify({
            name: name.value,
            contactEmail: contactEmail.value,
            information: information.value,
            logoUrl: logoUrl.value
        })
    })
        .then(async (response) => {
            if (response.status === 204) {
                console.log('Sharing platform updated successfully');
            } else {
                console.error('Failed to update sharing platform');
            }
        })
        .catch(error => {
            console.error('Error updating sharing platform:', error);
        });
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
