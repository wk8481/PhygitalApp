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
const header = document.querySelector('meta[name="_csrf_header"]').content
const token = document.querySelector('meta[name="_csrf"]').content


/***/ }),

/***/ "./src/main/js/utils.js":
/*!******************************!*\
  !*** ./src/main/js/utils.js ***!
  \******************************/
/***/ ((__unused_webpack___webpack_module__, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   extractIdsFromUrl: () => (/* binding */ extractIdsFromUrl)
/* harmony export */ });
function extractIdsFromUrl(url, partOfUrl) {
    // Used to extract the 2 id's that are in the link, needed to update entity

    // Define the regular expression pattern to match IDs
    const mainPattern = new RegExp('/(\\d+)/' + partOfUrl + '/(\\d+)')
    const creatingPattern = new RegExp('/(\\d+)/' + partOfUrl + '/new')
    const specialPattern = new RegExp('/' + partOfUrl + '/(\\d+)')
    let match

    // If match is found, extract the IDs
    if ((match = url.match(mainPattern)) !== null) {
        const firstId = match[1]
        const secondId = match[2]
        return [firstId, secondId]
    } else if ((match = url.match(creatingPattern)) !== null) {
        return match[1]
    } else if ((match = url.match(specialPattern)) !== null) {
        return match[1]
    } else {
        return null
    }
}


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
/*!***************************************!*\
  !*** ./src/main/js/admin/question.js ***!
  \***************************************/
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../util/csrf.js */ "./src/main/js/util/csrf.js");
/* harmony import */ var _utils_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../utils.js */ "./src/main/js/utils.js");

 // Adjust the path as per your file structure



document.getElementById('addButton').addEventListener('click', addAnswerField)
document.getElementById('removeButton').addEventListener('click', removeAnswerField)

const saveButton = document.querySelector('#saveButton')
const deleteButton = document.querySelector('#deleteButton')
const [subThemeId, questionId] = (0,_utils_js__WEBPACK_IMPORTED_MODULE_1__.extractIdsFromUrl)(window.location.href.substring(window.location.href), 'question')

saveButton.addEventListener('click', updateQuestion)
deleteButton.addEventListener('click', deleteQuestion)

document.getElementById('addButton').addEventListener('click', addAnswerField)
document.getElementById('removeButton').addEventListener('click', removeAnswerField)
document.getElementById('questionTypeInput').addEventListener('change', changeButtonVisability)


let fields = document.getElementsByClassName('answer-input')

let addButton = document.getElementById('addButton')
let minusButton = document.getElementById('removeButton')
changeButtonVisability()

function changeButtonVisability(){
    let rangeFields = document.getElementsByClassName('range')

    let questionType = document.getElementById('questionTypeInput').value
    if (questionType === 'MULTIPLE_CHOICE' || questionType === 'SINGLE_CHOICE') {
        addButton.style.visibility = 'visible'
        minusButton.style.visibility = 'visible'
        for (let i = 0; i < fields.length-2; i++) {
            let answerField = fields[i]
            if (answerField.style.visibility !== 'visible') {
                answerField.style.visibility = 'visible'
            }
            for (let i = 0; i < rangeFields.length; i++) {
                rangeFields[i].style.visibility = 'hidden'
            }
        }
    }
    else{
        addButton.style.visibility = 'hidden'
        minusButton.style.visibility = 'hidden'
        for (let field of fields) {
            field.style.visibility = 'hidden'
        }
        if (questionType === 'RANGE'){
            for (let i = 0; i < rangeFields.length; i++) {
                rangeFields[i].style.visibility = 'visible'
            }
        } else {
            for (let i = 0; i < rangeFields.length; i++) {
                rangeFields[i].style.visibility = 'hidden'
            }
        }
    }
}
async function updateQuestion(event) {
    let answer = []
    let rangeFields = document.getElementsByClassName('range')

    const questionType = document.getElementById('questionTypeInput').value
    if (questionType === 'MULTIPLE_CHOICE' || questionType === 'SINGLE_CHOICE' || questionType === 'RANGE') {
        let visibleCount = 0
        for (let field of fields) {
            if (field.style.visibility === 'visible') {
                if (questionType === 'RANGE'){
                    if (parseInt(rangeFields[0].value) < parseInt(rangeFields[2].value) && parseInt(rangeFields[1].value) < parseInt(rangeFields[2].value)){
                        answer.push(field.value)
                        visibleCount++
                    } else if (parseInt(rangeFields[0].value) > parseInt(rangeFields[2].value) ){
                        alert('Check the red field ( ' +rangeFields[0].placeholder +' ) for mistakes, minimum value should be less than maximum')
                        rangeFields[0].style.borderColor = 'red'
                        rangeFields[2].style.borderColor = 'red'
                        return
                    } else if(parseInt(rangeFields[1].value) > parseInt(rangeFields[2].value)){
                        alert('Check the red field ( ' +rangeFields[1].placeholder +' ) for mistakes, step value should be less than maximum')
                        rangeFields[1].style.borderColor = 'red'
                        rangeFields[2].style.borderColor = 'red'
                        return
                    }
                }else {
                    answer.push(field.value)
                    visibleCount++
                }
            }
        }
    }
    if (answer.length == null){
        answer = 0
    }
    const question = document.getElementById("textInput").value;
    const isVisible = document.getElementById("isVisibleInput").checked;
    console.log("Updating question")
    fetch(`/api/questions/${questionId}`, {
        method: 'PATCH', headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json', [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
        }, body: JSON.stringify({
            "id": questionId,
            "text": question,
            "type": questionType,
            "isVisible": isVisible,
            "answers": answer
        })
    })
        .then(response => {
            if (response.status === 204) {
                window.history.back()
            }
        })
}

async function deleteQuestion(event) {
    console.log('Deleting question')
    const response = await fetch(`/api/questions/${questionId}`, {
        method: 'DELETE', headers: {
            [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
        }
    })
    if (response.ok){
        window.history.back()
    }
}


function addAnswerField(){

    for (let field of fields) {
        if (field.style.visibility !== 'visible'){
            console.log('showing 1 more')
            field.style.visibility = 'visible'
            break
        }
    }
}

function removeAnswerField(){
    let visibleCount = 0
    for (let field of fields) {
        if (field.style.visibility === 'visible') {
            visibleCount++
        }
    }

    if (visibleCount > 2) {
        for (let i = fields.length - 1; i >= 0; i--) {
            let field = fields[i]
            if (field.style.visibility === 'visible') {
                console.log('Showing 1 less')
                field.style.visibility = 'hidden'
                break
            }
        }
    }
}


})();

/******/ })()
;
//# sourceMappingURL=admin\bundle-question.js.map