/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	// The require scope
/******/ 	var __webpack_require__ = {};
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
/*!******************************!*\
  !*** ./src/main/js/utils.js ***!
  \******************************/
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

/******/ })()
;
//# sourceMappingURL=bundle-utils.js.map