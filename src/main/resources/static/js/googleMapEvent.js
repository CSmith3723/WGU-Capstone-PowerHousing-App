
document.addEventListener('DOMContentLoaded', function() {
    const iframe = document.getElementById('map');
    const dropdown = document.getElementById('cityDropdown');

    // Function to update the iframe src
    function updateMap(city) {
        const baseUrl = 'https://www.google.com/maps/embed/v1/place';
        const apiKey = apikey;
        const zoomLevel = 11;
        const src = `${baseUrl}?key=${apiKey}&q=${encodeURIComponent(city)}&zoom=${zoomLevel}`;
        iframe.src = src;
    }

    // Initialize with default city
    updateMap(dropdown.value);

    // Change map when dropdown value changes
    dropdown.addEventListener('change', function() {
        const selectedCity = this.value;
        updateMap(selectedCity);
    });
});

