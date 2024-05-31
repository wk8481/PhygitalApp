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
    const mainPattern = new RegExp("/(\\d+)/" + partOfUrl + "/(\\d+)");
    const creatingPattern = new RegExp("/(\\d+)/" + partOfUrl + "/new");
    const specialPattern = new RegExp("/" + partOfUrl + "/(\\d+)");
    let match

    // If match is found, extract the IDs
    if ((match = url.match(mainPattern)) !== null) {
        const firstId = match[1];
        const secondId = match[2];
        return [firstId, secondId];
    } else if ((match = url.match(creatingPattern)) !== null) {
        return match[1]
    } else if ((match = url.match(specialPattern)) !== null) {
        return match[1]
    } else {
        return null;
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
/*!***********************************************!*\
  !*** ./src/main/js/admin/sharing-platform.js ***!
  \***********************************************/
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../util/csrf.js */ "./src/main/js/util/csrf.js");
/* harmony import */ var _utils_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../utils.js */ "./src/main/js/utils.js");

 // Adjust the path as per your file structure



const saveButton = document.querySelector("#saveButton");
const deleteButton = document.querySelector("#deleteButton");
saveButton.addEventListener("click", updateSharingPlatform);
deleteButton.addEventListener("click", deleteSharingPlatform);

async function updateSharingPlatform(event) {
    event.preventDefault(); // Prevent the default form submission
    console.log("Updating platform");

    const name = document.getElementById("nameInput");
    const contactEmail = document.getElementById("contactEmailInput");
    const logo = document.getElementById("logoInput");
    const sharingPlatformId = (0,_utils_js__WEBPACK_IMPORTED_MODULE_1__.extractIdsFromUrl)(window.location.href, "sharing-platform");

    // Create a FormData object to append the form data, including the logo file
    const formData = new FormData();
    const dto = {
        id: sharingPlatformId,
        name: name.value,
        contactEmail: contactEmail.value
    };

    // Convert the JSON object to a Blob with MIME type application/json
    const dtoBlob = new Blob([JSON.stringify(dto)], { type: "application/json" });
    formData.append("updateSharingPlatformDto", dtoBlob);
    formData.append("logo", logo.files[0]);

    try {
        const response = await fetch(`/api/sharing-platforms/${sharingPlatformId}`, {
            method: "PATCH",
            headers: {
                "Accept": "application/json",
                [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
            },
            body: formData // Send formData as-is
        });

        if (response.ok) {
            // Handle success
            console.log("Sharing platform updated successfully");
        } else {
            // Handle error
            console.error("Error updating sharing platform:", response.statusText);
        }
    } catch (error) {
        console.error("Error updating sharing platform:", error);
    }
}


async function deleteSharingPlatform(event) {
    console.log("Deleting sharing platform")
    const sharingPlatformId = (0,_utils_js__WEBPACK_IMPORTED_MODULE_1__.extractIdsFromUrl)(window.location.href, "sharing-platform");

    const response = await fetch(`/api/sharing-platforms/${sharingPlatformId}`, {
        method: "DELETE", headers: {
            [_util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.header]: _util_csrf_js__WEBPACK_IMPORTED_MODULE_0__.token
        }
    });
    if (response.ok) {
        window.history.back();
    }
}

})();

/******/ })()
;
//# sourceMappingURL=admin\bundle-sharing-platform.js.map