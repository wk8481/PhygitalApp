import {header, token} from "../util/csrf.js";

const postButton = document.getElementById("postButton");
const projectId = extractIdsFromUrl(window.location.href.substring(window.location.href), "comment");

postButton.addEventListener("click", addNewComment);

async function addNewComment() {
    const text = document.getElementById("textInput").value;
    await fetch(`/api/comments`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            text: text, projectId: projectId
        })
    });
}
