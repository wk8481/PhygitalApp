import {header, token} from "../util/csrf.js";
import { extractIdsFromUrl } from '../utils.js'; // Adjust the path as per your file structure


const createButton = document.querySelector("#createButton");
createButton.addEventListener("click", addNewSupervisor);

const sharingPlatformId = extractIdsFromUrl(window.location.href.substring(window.location.href), "supervisor");

async function addNewSupervisor() {
    let name = document.getElementById("nameInput").value;
    let email = document.getElementById("emailInput").value;
    let password = document.getElementById("passwordInput").value;
    await fetch(`/api/supervisors`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            name: name,
            email:  email,
            password: password,
            sharingPlatformId: sharingPlatformId
        })
    });
}
