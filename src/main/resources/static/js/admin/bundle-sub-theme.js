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
    const pattern = new RegExp("/(\\d+)/" + partOfUrl + "/(\\d+)");

    // Execute the regular expression on the URL
    const match = url.match(pattern);

    // If match is found, extract the IDs
    if (match) {
        const firstId = match[1];
        const secondId = match[2];
        return [firstId, secondId];
    } else {
        const pattern2 = new RegExp("/(\\d+)/" + partOfUrl + "/new");
        const match2 = url.match(pattern2);
        if (match2){
            return match2[1]
        }
        // Return null or handle error
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
/*!****************************************!*\
  !*** ./src/main/js/admin/sub-theme.js ***!
  \****************************************/
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../util/csrf.js */ "./src/main/js/util/csrf.js");
/* harmony import */ var _utils_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../utils.js */ "./src/main/js/utils.js");

 // Adjust the path as per your file structure



const uploadInput = document.getElementById("mediaInput");
const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
const [flowId, subThemeId] = (0,_utils_js__WEBPACK_IMPORTED_MODULE_1__.extractIdsFromUrl)(window.location.href.substring(window.location.href), "sub-theme");

saveButton.addEventListener("click", updateSubTheme);
deleteButton.addEventListener("click", deleteSubTheme);

async function updateSubTheme(event) {
    const name = document.getElementById("nameInput").value;
    const info = document.getElementById("infoInput").value;
    console.log("Updating sub theme")
    fetch(`/api/sub-themes/${subThemeId}`, {
        method: "PATCH", headers: {
            "Accept": "application/json", "Content-Type": "application/json", [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
        }, body: JSON.stringify({
            "id": subThemeId, "name": name, "information": info
        })
    })
        .then(async (response) => {
            if (response.status === 204) {
                // Upload media files
                await uploadMediaFiles();
            }
        });
}

async function deleteSubTheme(event) {
    console.log("Deleting sub theme")
    const response = await fetch(`/api/sub-themes/${subThemeId}`, {
        method: "DELETE", headers: {
            [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
        }
    });
    if (response.ok){
        window.history.back();
    }
}

async function uploadMediaFiles(event) {
    const files = uploadInput.files;
    const formData = new FormData();
    for (const file of files) {
        formData.append("files", file);
    }
    console.log("Uploading media files");
    const response = await fetch(`/api/sub-themes/${subThemeId}/media`, {
        method: "POST",
        headers: {
            [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
        },
        body: formData
    });
    if (response.ok) {
        // Handle success
    }
}

})();

/******/ })()
;
//# sourceMappingURL=admin\bundle-sub-theme.js.map