export function extractIdsFromUrl(url, partOfUrl) {
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
