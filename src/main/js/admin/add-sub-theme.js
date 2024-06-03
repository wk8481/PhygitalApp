import {header, token} from '../util/csrf.js'
import { extractIdsFromUrl } from '../utils.js' // Adjust the path as per your file structure



const createButton = document.getElementById('createButton')
const flowId = extractIdsFromUrl(window.location.href.substring(window.location.href), 'sub-theme')

createButton.addEventListener('click', addNewSubTheme)

async function addNewSubTheme() {
    const name = document.getElementById('nameInput').value
    const information = document.getElementById('infoInput').value
    await fetch('/api/sub-themes', {
        method: 'POST', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [header]: token
        }, body: JSON.stringify({
            name: name,
            information: information,
            flowId: flowId
        })
    })
}
