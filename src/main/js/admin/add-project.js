import {header, token} from '../util/csrf.js'
import { extractIdsFromUrl } from '../utils.js'

const nameInput = document.getElementById('nameInput')
const createButton = document.getElementById('createButton')
const platformId = extractIdsFromUrl(window.location.href.substring(window.location.href), 'project')

createButton.addEventListener('click', addNewProject)

async function addNewProject() {
    await fetch('/api/projects', {
        method: 'POST', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify({
            name: nameInput.value,
            sharingPlatformId: platformId
        })
    })
}
