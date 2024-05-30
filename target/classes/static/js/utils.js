function extractIdsFromUrl(url, partOfUrl) {
    // Used to extract the 2 id's that are in the link, needed to update entity

    // Define the regular expression pattern to match IDs
    var pattern = new RegExp("/(\\d+)/" + partOfUrl + "/(\\d+)");

    // Execute the regular expression on the URL
    var match = url.match(pattern);

    // If match is found, extract the IDs
    if (match) {
        var firstId = match[1];
        var secondId = match[2];
        return [firstId, secondId];
    } else {
        var pattern2 = new RegExp("/(\\d+)/" + partOfUrl + "/new");
        var match2 = url.match(pattern2);
        if (match2){
            return match2[1]
        }
        // Return null or handle error
    }
}
