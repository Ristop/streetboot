var streetbootApp = angular.module("streetbootModule", ['moment-picker']);

streetbootApp.controller('VehicleController', ['$scope', '$http', 'VehicleService', function ($scope, $http, VehicleService) {

  angular.element(document).ready(function () {
    showVehicles();
    $scope.nodata = false;
  });

  function showVehicles() {
    VehicleService.getVehicles().then(function (data) {
      $scope.vehicles = data;
    });
  }

  $scope.getVehicleEvents = function (name, description, fuelCardNum) {
    if (fuelCardNum === null) {
      return;
    }

    var fuelData;
    var washData;

    VehicleService.getVehiclesRefuelEvents(fuelCardNum).then(function (data) {
      fuelData = data;
      VehicleService.getVehiclesWashEvents(fuelCardNum).then(function (data) {
        washData = data;
        if (fuelData.length > 0 || washData.length > 0) {
          $scope.nodata = false;

          drawChart(fuelData, washData);
        } else {
          $scope.nodataVeh = {
            name: name,
            description: description
          };
          $scope.nodata = true;
        }
      });
    });

    if ($scope.ctrl !== undefined && $scope.ctrl.startDatepicker !== undefined && $scope.ctrl.endDatepicker !== undefined) {
      VehicleService.getVehiclesLocationByStartAndEndDate(name, new Date($scope.ctrl.startDatepicker).valueOf(), new Date($scope.ctrl.endDatepicker).valueOf()).then(function (data) {
        initMap(data);
      })
    } else {
      VehicleService.getVehiclesLocation(name).then(function (data) {
        if (data.length > 0) {
          initMap(data);
        }
      })
    }
  };

}]);

function initMap(cordinateData) {
  if (cordinateData === undefined) {
    return;
  }

  var corData = [];

  for (var i = 0; i < cordinateData.length; i++) {
    corData.push({lat: cordinateData[i]["lat"], lng: cordinateData[i]["long"]});
  }

  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 11,
    center: corData[Math.floor(corData.length / 2)],
    mapTypeId: 'terrain'
  });

  var flightPlanCoordinates = corData;
  var flightPath = new google.maps.Polyline({
    path: flightPlanCoordinates,
    geodesic: true,
    strokeColor: '#FF0000',
    strokeOpacity: 1.0,
    strokeWeight: 2
  });

  flightPath.setMap(map);
}

streetbootApp.filter('searchFor', function () {

  return function (arr, searchString) {

    if (!searchString || searchString.length < 3) {
      return arr;
    }

    var vehiclesWithMap = [
      "511601", "521202", "521304", "521308", "521606", "A30805", "A30806", "A30811", "A30901", "A30909", "A31007", "A31008", "A31011", "A31302", "A31402", "A31404", "A31406", "A31601", "520806", "520905", "520906", "520910", "521001", "521002", "521201", "521306", "521309", "521310", "521311", "A31201", "A31202", "A31203", "A31204", "A31205", "A31206", "A31401", "A31402"
    ];

    var result = [];

    searchString = searchString.toLowerCase();

    if (searchString === "map") {
      angular.forEach(arr, function (vehicle) {
        for (var k = 0; k < vehiclesWithMap.length; k++) {
          if (vehicle["Name"].toLowerCase().indexOf(vehiclesWithMap[k]) !== -1) {
            result.push(vehicle);
          }
        }
      });
    } else {
      angular.forEach(arr, function (vehicle) {
        if (vehicle["Name"].toLowerCase().indexOf(searchString) !== -1 ||
            vehicle["Description"].toLowerCase().indexOf(searchString) !== -1 ||
            (vehicle["FuelCardNumber"] !== undefined &&
                vehicle["FuelCardNumber"].toLowerCase().indexOf(searchString) !== -1)) {
          result.push(vehicle);
        }
      });
    }

    return result;
  };

});

function drawChart(fuelData, washData) {

  var fuelVolumes = [];
  var km = [];
  var wash = [];

  for (var j = 0; j < washData.length; j++) {
    var date2 = new Date(washData[j]["Time"]);
    wash.push([Date.UTC(date2.getUTCFullYear(), date2.getUTCMonth() + 1, date2.getUTCDate()), washData[j]["Quality"]]);
  }

  for (var i = 0; i < fuelData.length; i++) {
    var date = new Date(fuelData[i]["Time"]);
    fuelVolumes.push([Date.UTC(date.getUTCFullYear(), date.getUTCMonth() + 1, date.getUTCDate()), fuelData[i]["FuelVolume"]]);
    if (fuelData[i + 1] !== undefined && fuelData[i]["Km"] > fuelData[i + 1]["Km"]) {
      continue;
    }
    if (fuelData[i - 1] !== undefined && fuelData[i]["Km"] < fuelData[i - 1]["Km"]) {
      continue;
    }
    km.push([Date.UTC(date.getUTCFullYear(), date.getUTCMonth() + 1, date.getUTCDate()), fuelData[i]["Km"]]);
  }

  Highcharts.chart('chart-container', {
    chart: {
      zoomType: 'xy'
    },
    title: {
      text: ''
    },
    exporting: {enabled: false},
    xAxis: {
      type: 'datetime',
      dateTimeLabelFormats: { // don't display the dummy year
        month: '%e. %b',
        year: '%b'
      }
    },
    yAxis: [{ // Primary yAxis
      labels: {
        format: '{value} km',
        style: {
          color: Highcharts.getOptions().colors[1]
        }
      },
      title: {
        text: '',
        style: {
          color: Highcharts.getOptions().colors[1]
        }
      }
    }, { // Secondary yAxis
      title: {
        text: '',
        style: {
          color: Highcharts.getOptions().colors[0]
        }
      },
      labels: {
        format: '{value} l',
        style: {
          color: Highcharts.getOptions().colors[0]
        }
      },
      opposite: true
    }],
    tooltip: {
      shared: true
    },
    legend: {
      enabled: false
    },
    plotOptions: {
      spline: {
        marker: {
          enabled: true
        }
      }
    },
    series: [{
      name: 'Liters',
      type: 'column',
      yAxis: 1,
      data: fuelVolumes,
      tooltip: {
        valueSuffix: ' l'
      }

    }, {
      name: 'Km',
      type: 'spline',
      data: km,
      tooltip: {
        valueSuffix: ' km'
      }
    }, {
      name: 'Wash',
      type: 'spline',
      data: wash
    }]
  });
}