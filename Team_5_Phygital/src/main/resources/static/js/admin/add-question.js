import {header, token} from "../util/csrf.js";

const createButton = document.getElementById("createButton");

const subThemeId = extractIdsFromUrl(window.location.href.substring(window.location.href), "question");
createButton.addEventListener("click", addNewQuestion);

async function addNewQuestion() {
    const textInput = document.getElementById("textInput").value;
    const type = document.getElementById("questionTypeInput").value;
    await fetch(`/api/questions`, {
        method: "POST", headers: {
            'Accept': 'application/json', [header]: token
        }, body: JSON.stringify({
            text: textInput,
            type: type,
            subThemeId: subThemeId
        })
    });
}
