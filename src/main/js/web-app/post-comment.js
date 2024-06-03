import {header, token} from "../util/csrf.js";
import { extractIdsFromUrl } from '../utils.js';

const postButton = document.getElementById("postButton");
const projectId = extractIdsFromUrl(window.location.href.substring(window.location.href), "project");
postButton.addEventListener("click", addNewComment);

async function addNewComment() {
    const text = document.getElementById("commentInput").value;
    await fetch(`/api/comments`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [header]: token
        }, body: JSON.stringify({
            text: text, projectId: projectId
        })
    }).then(response => {
        if (response.status === 201) {
            window.location.reload();
        }
    });
}
