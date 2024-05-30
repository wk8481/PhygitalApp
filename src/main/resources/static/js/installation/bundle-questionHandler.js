/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./src/main/js/util/csrf.js":
/*!**********************************!*\
  !*** ./src/main/js/util/csrf.js ***!
  \**********************************/
/***/ ((__unused_webpack___webpack_module__, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   header: () => (/* binding */ header),
/* harmony export */   token: () => (/* binding */ token)
/* harmony export */ });
const header = document.querySelector('meta[name="_csrf_header"]').content;
const token = document.querySelector('meta[name="_csrf"]').content;


/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	/* webpack/runtime/define property getters */
/******/ 	(() => {
/******/ 		// define getter functions for harmony exports
/******/ 		__webpack_require__.d = (exports, definition) => {
/******/ 			for(var key in definition) {
/******/ 				if(__webpack_require__.o(definition, key) && !__webpack_require__.o(exports, key)) {
/******/ 					Object.defineProperty(exports, key, { enumerable: true, get: definition[key] });
/******/ 				}
/******/ 			}
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/hasOwnProperty shorthand */
/******/ 	(() => {
/******/ 		__webpack_require__.o = (obj, prop) => (Object.prototype.hasOwnProperty.call(obj, prop))
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/make namespace object */
/******/ 	(() => {
/******/ 		// define __esModule on exports
/******/ 		__webpack_require__.r = (exports) => {
/******/ 			if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 			}
/******/ 			Object.defineProperty(exports, '__esModule', { value: true });
/******/ 		};
/******/ 	})();
/******/ 	
/************************************************************************/
var __webpack_exports__ = {};
// This entry need to be wrapped in an IIFE because it need to be isolated against other modules in the chunk.
(() => {
/*!*****************************************************!*\
  !*** ./src/main/js/installation/questionHandler.js ***!
  \*****************************************************/
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../util/csrf.js */ "./src/main/js/util/csrf.js");

let isCircular

var isCircularExists = document.getElementById("minUser");

document.getElementById("submit").style.visibility = "hidden"
let currentIndex = 0;
if (isCircularExists !== null) {
    isCircular = true
    document.getElementById("minUser").addEventListener("click", minUser);
    document.getElementById("plusUser").addEventListener("click", plusUser);
    document.getElementById("nextButton").classList.remove("btn-secondary")
    document.getElementById("nextButton").classList.add("btn-primary")
} else {
    isCircular = false
}

document.getElementById("submit").addEventListener("click", submitAnswer);
document.getElementById("nextButton").addEventListener("click", moveToNextQuestion);
document.getElementById("backButton").addEventListener("click", moveToPreviousQuestion);
document.getElementById("pauseButton").addEventListener("click", togglePause);
const userCount = document.getElementById("userCount")
let queue = 0

function minUser(){
    if (queue>0){
        queue--
    }
    userCount.innerText = "There are/is " + queue + " people in the queue."
}

function plusUser(){
    if (queue < 15) {
        queue++
    }
    userCount.innerHTML = "There are/is " + queue + " people in the queue."

}

var elapsedTime =0
var seconds =0
let minSeconds = 0

// Start time when the page loads
var startTime = new Date().getTime();

function trackTime() {

    // Function to calculate and display time spent

    displayTime()
    // Update time every second
    timerInterval = setInterval(displayTime, 1000);
}

// Call trackTime() when the page loads
trackTime();
function displayTime() {
    var currentTime = new Date().getTime();
    elapsedTime = currentTime - startTime;
    elapsedTime -= minSeconds
    seconds = Math.floor(elapsedTime / 1000);
    var minutes = Math.floor(seconds / 60);

    var secondsTodisplay =  seconds % 60;
    // Display time on the page
    document.getElementById("timer").innerHTML = "Time spent on the page: " + minutes + "m " + secondsTodisplay + "s";
}


// JavaScript to toggle between pause and resume states
var paused = false; // Initially, page is not paused
var timerInterval
// Function to toggle pause state and update button
var startPause
function togglePause() {
    paused = !paused; // Toggle pause state

    // Update button text and icon based on pause state
    var button = document.getElementById("pauseButton");
    const textArea = document.getElementById("notesField")
    const form = document.getElementById("questionForm")

    if (paused) {
        // Page is paused
        startPause = new Date().getTime();
        button.innerHTML = '';
        button.innerHTML = '<i class="fas fa-play"></i>';
        document.getElementById("blockedOverlay").style.display = "block"; // Display the overlay
        form.style.display = "none"
        textArea.style.display = "block"
        clearInterval(timerInterval)
    } else {
        // Page is resumed
        var endPause = new Date().getTime();
        button.innerHTML = ''
        button.innerHTML = '<i class="fas fa-pause"></i>';
        document.getElementById("blockedOverlay").style.display = "none"; // Dont display the overlay
        minSeconds = endPause - startPause + minSeconds
        form.style.display = "block"
        textArea.style.display = "none"

        timerInterval = setInterval(displayTime, 1000);
    }

    // Perform other actions related to pausing or resuming the page
    // For example, you can pause or resume certain functionality here
}



var questionDivs = document.querySelectorAll('div[id*=question]');

for (let i = 0; i < questionDivs.length; i++) {
    let questionNr = document.getElementById("question"+i)
    let questionId = questionNr.querySelector("h2").id.split("_")[1]
    // Determine answer based on question type

}
window.onload = function () {
    questionDivs.forEach(function(div) {
        // Check if the div's ID is "question0"
        if (div.id === 'question0') {
            // If it is, show the div
            div.style.display = 'block';
        } else if (div.id.includes("question")) {
            // If not, hide the div
            div.style.display = 'none';
        }
    });
}



// Function to handle submitting answers
function submitAnswer(event) {
    if (event != null) {
        event.preventDefault();
    }

    let answers = ""
    let questions = ""

    if (!isCircular) {
        for (let i = 0; i < questionDivs.length; i++) {
            let answer;
            let question = document.getElementById("question_" + i).textContent;
            let questionNr = document.getElementById("question" + i)
            let questionId = questionNr.querySelector("h2").id.split("_")[1]

            let answerName = "answer" + i
            switch (questionNr.querySelector("div").querySelector("div").id) {
                case "open":
                    answer = document.getElementById(answerName).value
                    break
                case "multipleChoice":
                    answer = ""
                    const s = document.querySelectorAll('input[name=' + answerName + ']:checked')
                    for (let sElement of s) {
                        answer += sElement.value
                        answer += ", "
                    }

                    answer = answer.slice(0, -2); // Delete last two characters
                    break
                case "range":
                    answer = document.getElementById(answerName).value;
                    break
                case "singleChoice":

                    answer = document.querySelector('input[name=' + answerName + ']:checked').value
                    break
            }

            answers += answer
            answers += " | "
            questions += questionId
            questions += " | "
        }
    } else {
        let answer;
        let question = document.getElementById("question_" + currentIndex).textContent;
        let questionNr = document.getElementById("question" + currentIndex)
        let questionId = questionNr.querySelector("h2").id.split("_")[1]

        let answerName = "answer" + currentIndex
        switch (questionNr.querySelector("div").querySelector("div").id) {
            case "open":
                answer = document.getElementById(answerName).value
                break
            case "multipleChoice":
                answer = ""
                const s = document.querySelectorAll('input[name=' + answerName + ']:checked')
                for (let sElement of s) {
                    answer += sElement.value
                    answer += ", "
                }

                answer = answer.slice(0, -2); // Delete last two characters
                break
            case "range":
                answer = document.getElementsByClassName('range').item(0).value;
                break
            case "singleChoice":
                answer = document.querySelector('input[name=' + answerName + ']:checked').value
                break
        }

        answers += answer
        answers += " | "
        questions += questionId
        questions += " | "
    }

    const user = document.getElementById("userMail").textContent

    const textArea = document.getElementById("notesField")
    var urlParams = new URLSearchParams(window.location.search);

    answers = answers.slice(0, -1); // Delete last character
    questions = questions.slice(0, -1); // Delete last character
    document.getElementById("questionForm").reset()
    // Get the value of the "subThemeId" parameter
    var subtheme = urlParams.get("subThemeId");
    fetch(`/api/questions/submit`, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
        },
        body: JSON.stringify({
            question: questions,
            answer: answers,
            userMail: user,
            subThemeId: subtheme,
            durationSpend: seconds,
            note: textArea.value
        })
    })
        .then(response => {
            if (response.ok) {
                console.log("Answer submitted successfully.");
                if (!isCircular) {
                    window.location.href = `/installation/flowCompleted`;
                }
            } else {
                console.error("Failed to submit answer.");
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


function moveToNextQuestion() {
    if (isCircular) {
        submitAnswer()
    }
    if (queue === 0){
        if (currentIndex < questionDivs.length - 1) {
            currentIndex++;
            showQuestion(currentIndex);

        } else {
            if (isCircular) {
                currentIndex = 0
                showQuestion(currentIndex)
            }
    }
    } else {
        minUser()
    }

    if (!isCircular && currentIndex === questionDivs.length-1){
        document.getElementById("submit").style.visibility = "visible"

    }
}

function moveToPreviousQuestion() {
    if(!isCircular) {
        document.getElementById("submit").style.visibility = "hidden"
    }
    if (currentIndex > 0) {
        currentIndex--;
        showQuestion(currentIndex);
    }
}

function showQuestion(index) {

    for (var i = 0; i < questionDivs.length; i++) {
        questionDivs[i].style.display = 'none';
    }
    questionDivs[index].style.display = 'block';
}

// Range slider change event listener for all range inputs
let rangeInputs = document.getElementsByClassName('range');

Array.from(rangeInputs).forEach(function(rangeInput) {
    let rangeValue = rangeInput.nextElementSibling;
    rangeInput.addEventListener("input", function() {
        if (rangeValue != null) {
            rangeValue.textContent = "Value: " + rangeInput.value;
        }
    });
});
})();

/******/ })()
;
//# sourceMappingURL=installation\bundle-questionHandler.js.map