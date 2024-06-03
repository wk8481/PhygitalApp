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
