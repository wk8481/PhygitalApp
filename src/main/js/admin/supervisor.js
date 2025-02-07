import {header, token} from '../util/csrf.js'
import { extractIdsFromUrl } from '../utils.js' // Adjust the path as per your file structure



const saveButton = document.querySelector('#saveButton')
const deleteButton = document.querySelector('#deleteButton')
const [sharingPlatformId, supervisorId] = extractIdsFromUrl(window.location.href.substring(window.location.href), 'supervisor')

saveButton.addEventListener('click', updateSupervisor)
deleteButton.addEventListener('click', deleteSupervisor)

async function updateSupervisor(event) {
    let name = document.getElementById('nameInput').value
    let email = document.getElementById('emailInput').value
    console.log('Updating supervisor to: ' + name + ' ' + email)
    fetch(`/api/supervisors/${supervisorId}`, {
        method: 'PATCH', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify({
            'id': supervisorId, 'name': name, 'email': email, 'platform': sharingPlatformId
        })
    })
        .then(response => {
            if (response.status === 204) {

            }
            if (response.ok){
                window.history.back()
            }
        })

}

async function deleteSupervisor(event) {
    console.log('Deleting supervisor')
    const response = await fetch(`/api/supervisors/${supervisorId}`, {
        method: 'DELETE', headers: {
            [header]: token
        }
    })
    if (response.ok){
        window.history.back()
    }
}
