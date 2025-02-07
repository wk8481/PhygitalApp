import {header, token} from '../util/csrf.js'

const name = document.getElementById('nameInput')
const contactEmail = document.getElementById('contactEmailInput')
const createButton = document.getElementById('createButton')

createButton.addEventListener('click', addNewSharingPlatform)

async function addNewSharingPlatform() {
    await fetch('/api/sharing-platforms/', {
        method: 'POST', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify({
            name: name.value, contactEmail: contactEmail.value
        })
    })
}
