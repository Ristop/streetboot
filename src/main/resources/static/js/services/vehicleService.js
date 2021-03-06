streetbootApp.service('VehicleService', function ($http) {

  this.getVehicles = function () {
    return $http.get("/api/vehicles", {
          params: {}
        }
    ).then(function (response) {
      return response.data;
    });
  };

  this.getVehiclesWashEvents = function (fuelCardNum) {
    return $http.get("/api/vehicles/wash", {
          params: {
            fuelCardNum: fuelCardNum
          }
        }
    ).then(function (response) {
      return response.data;
    });
  };

  this.getVehiclesRefuelEvents = function (fuelCardNum) {
    return $http.get("/api/vehicles/refuel", {
          params: {
            fuelCardNum: fuelCardNum
          }
        }
    ).then(function (response) {
      return response.data;
    });
  };

  this.getVehiclesLocation = function (name) {
    return $http.get("/api/vehicles/location", {
          params: {
            name: name
          }
        }
    ).then(function (response) {
      return response.data;
    });
  };

  this.getVehiclesLocationByStartAndEndDate = function (name, startDate, endDate) {
    return $http.get("/api/vehicles/locationbydates", {
          params: {
            name: name,
            startDate: startDate,
            endDate: endDate
          }
        }
    ).then(function (response) {
      return response.data;
    });
  };

  this.getVehiclesGarageEvents = function (name) {
    return $http.get("/api/vehicles/garage", {
          params: {
            name: name
          }
        }
    ).then(function (response) {
      return response.data;
    });
  };

  this.getVehiclesGarageByVehicle = function (name) {
    return $http.get("/api/vehicles/garageByVehicle", {
          params: {
            name: name
          }
        }
    ).then(function (response) {
      return response.data;
    });
  };

  this.getVehiclesGarageBySEHIID = function (sehiid) {
    return $http.get("/api/vehicles/garageBySEHIID", {
          params: {
            sehiid: sehiid
          }
        }
    ).then(function (response) {
      return response.data;
    });
  };

});