const textInput = document.getElementById("textInput");
const createButton = document.getElementById("createButton");

createButton?.addEventListener("click", addNewQuestion);

async function addNewQuestion() {
    await fetch(`/api/questions`, {
        method: "POST", headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }, body: JSON.stringify({
            text: textInput.value
        })
    });
}
