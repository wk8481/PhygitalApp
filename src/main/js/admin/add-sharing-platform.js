import {header, token} from '../util/csrf.js'

const createButton = document.getElementById('createButton')

createButton.addEventListener('click', addNewSharingPlatform)

async function addNewSharingPlatform() {
    const name = document.getElementById('nameInput')
    const contactEmail = document.getElementById('contactInput')
    const information = document.getElementById('informationInput')
    await fetch('/api/sharing-platforms', {
        method: 'POST', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify({
            name: name.value, contactEmail: contactEmail.value, information: information.value
        })
    })
}
