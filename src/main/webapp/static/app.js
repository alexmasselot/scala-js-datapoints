$(window).load(function () {
    var map = new SJSMap();
    map.addBoundingSquare();
});

var SJSMap = function () {
    this.map = L.map('map').setView([37.7750, -122.4183], 13);
    this.apiKey = "f39f2318cf924151bab139be0e677f8e";
    var tileURL = ['http://{s}.tile.cloudmade.com/', this.apiKey, '/997/256/{z}/{x}/{y}.png'].join('');
    L.tileLayer(tileURL, {maxZoom: 18}).addTo(this.map);
};

SJSMap.prototype.data = [];

SJSMap.prototype.addAllMarkers = function () {
    var context = this;
    $.get('/rest/all', function (data) {
        context.data = data;
        $.each(data, function (idx, elem) {
            var marker = L.marker([elem['lat'], elem['lon']]).addTo(context.map);
        });
    });
};

SJSMap.prototype.addBoundingSquare = function () {
    var context = this;
    $.get('/rest/square', function (data) {
        var actualData = data[0];
        var boundingPoints = actualData['area'];

        var boundaries = [];
        for (var i=0; i < boundingPoints.length; i++) {
            boundaries.push([boundingPoints[i]['lon'], boundingPoints[i]['lat']]);
        }
        var polygon = L.polygon(boundaries).addTo(context.map);
    });
};

