import {header, token} from "../util/csrf.js";

const nameInput = document.getElementById("nameInput");
const provinceInput = document.getElementById("provinceInput");
const cityInput = document.getElementById("cityInput");
const streetInput = document.getElementById("streetInput");
const streetNumberInput = document.getElementById("streetNumberInput");
const createButton = document.getElementById("createButton");

createButton.addEventListener("click", addNewInstallation);

async function addNewInstallation() {
    await fetch(`/api/installations`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            name: nameInput.value,
            province: provinceInput.value,
            city: cityInput.value,
            street: streetInput.value,
            streetNumber: streetNumberInput.value
        })
    });
}
