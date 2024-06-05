import {header, token} from '../util/csrf.js'

const createButton = document.getElementById('createButton')
createButton.addEventListener('click', addNewSupervisor)

const sharingPlatformId = extractIdsFromUrl(window.location.href.substring(window.location.href), 'supervisor')
console.log(sharingPlatformId)
async function addNewSupervisor() {
    let name = document.getElementById('nameInput').value
    let email = document.getElementById('emailInput').value
    await fetch('/api/supervisors', {
        method: 'POST', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify({
            name: name,
            email:  email,
            sharingPlatformId: sharingPlatformId
        })
    })
}
