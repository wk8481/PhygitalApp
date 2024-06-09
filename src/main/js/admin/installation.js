import {header, token} from '../util/csrf.js'
import { extractIdsFromUrl } from '../utils.js' // Adjust the path as per your file structure



const saveButton = document.getElementById('saveButton')
const deleteButton = document.getElementById('deleteButton')
const [installationId] = extractIdsFromUrl(window.location.href.substring(window.location.href), 'installation')

saveButton.addEventListener('click', updateInstallation)
deleteButton.addEventListener('click', deleteInstallation)

async function updateInstallation(event) {
    console.log('Updating installation')

    const name = document.getElementById('nameInput')
    const province = document.getElementById('provinceInput')
    const city = document.getElementById('cityInput')
    const street = document.getElementById('streetInput')
    const streetNumber = document.getElementById('streetNumberInput')
    console.log(installationId)
    // Create a FormData object to append the form data, including the logo file
    const formData = new FormData()
    const body = {
        id: installationId,
        name: name.value,
        province: province.value,
        city: city.value,
        street: street.value,
        streetNumber: streetNumber.value
    }
    const dtoBlob = new Blob([JSON.stringify(body)], { type: 'application/json' })
    formData.append('updateInstallation', dtoBlob)

    try {
        const response = await fetch(`/api/installations/${installationId}`, {
            method: 'PATCH',
            headers: {
                'Accept': 'application/json', [header]: token
            },
            body: formData // Pass the FormData object as the body
        })

        if (response.ok) {
            // Handle success
            console.log('Installation updated successfully')
        } else {
            // Handle error
            console.error('Error updating installation:', response.statusText)
        }
    } catch (error) {
        console.error('Error updating installation:', error)
    }
}


async function deleteInstallation(event) {
    console.log('Deleting installation')
    const response = await fetch(`/api/installations/${installationId}`, {
        method: 'DELETE', headers: {
            [header]: token
        }
    })
    if (response.ok){
        window.history.back()
    }
}
