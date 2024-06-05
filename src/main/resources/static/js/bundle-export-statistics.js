/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	// The require scope
/******/ 	var __webpack_require__ = {};
/******/ 	
/************************************************************************/
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
/*!******************************************!*\
  !*** ./src/main/js/export-statistics.js ***!
  \******************************************/
__webpack_require__.r(__webpack_exports__);
document.addEventListener('DOMContentLoaded', function() {
    const statsTable = document.getElementById('statsTable')
    const exportButton = document.getElementById('exportButton')

    exportButton.addEventListener('click', function() {
        // Create a new instance of TableExport and pass the table element
        var tableExport = new TableExport(statsTable, {
            formats: ['csv'], // Specify the formats to include (only CSV in this case)
            exportButtons: false // Hide the export buttons (optional)
        })

        // Export the table to CSV format
        var csvData = tableExport.getExportData()['statsTable']['csv']

        // Extract the CSV content from the export data object
        var csvContent = csvData.data

        // Create a Blob object from the CSV content
        var csvBlob = new Blob([csvContent], { type: 'text/csv;charset=utf-8' })

        // Trigger the download of the CSV file
        saveAs(csvBlob, 'statistics.csv')
    })
})

/******/ })()
;
//# sourceMappingURL=bundle-export-statistics.js.map