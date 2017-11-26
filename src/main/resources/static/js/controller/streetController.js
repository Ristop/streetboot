var streetbootApp = angular.module("streetbootModule", []);

streetbootApp.controller('StreetController', ['$scope', '$http', 'StreetService', function ($scope, $http, StreetService) {

  angular.element(document).ready(function () {
    StreetService.getStreetCoordinates().then(function (data) {
      console.log(data);
      dontInitMapYet(data);
    });
    $scope.nodata = false;
  });

}]);

function dontInitMapYet(coordinateData) {
  if (coordinateData === undefined) {
    return;
  }

  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 12,
    center: {lat: 60.21, lng: 25.04},
    mapTypeId: 'satellite'

  });

  for (i = 0; i < coordinateData.length; i++) {
    console.log(coordinateData[i]);
    var image = 'img/markers/' + coordinateData[i].priority.level  + '.png';
    console.log(image);
    var latlng = new google.maps.LatLng(parseFloat(coordinateData[i].latitude), parseFloat(coordinateData[i].longitude));
    var marker = new google.maps.Marker({
      position: latlng,
      map: map,
      title: coordinateData[i].streetName,
      icon: image
    });
    /*
    google.maps.event.addListener(marker, 'click', function() {
      console.log('test');
    }); */
  }
}