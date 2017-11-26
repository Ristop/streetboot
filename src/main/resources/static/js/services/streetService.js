streetbootApp.service('StreetService', function ($http) {
  this.getStreetCoordinates = function () {
    return $http.get("/api/street/coordinates", {
        params: {}
      }
    ).then(function (response) {
      return response.data;
    });
  };
});