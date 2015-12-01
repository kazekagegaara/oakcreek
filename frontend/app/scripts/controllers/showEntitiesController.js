(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('showEntitiesController', ['$scope','$location','activeData',function($scope,$location,activeData) {
      	
  		$scope.Entities = ["some content 1","some content 2","some content 3","some content 4","some content 5","some content 6",
                        "some content 7","some content 8","some content 9","some content 10","some content 11","some content 12",
                        "some content 13","some content 14","some content 15","some content 16","some content 17","some content 18",
                        "some content 19","some content 20","some content 21","some content 22","some content 23","some content 24",
                        "some content 25","some content 26","some content 27","some content 28","some content 29","some content 30",
                        "some content 31","some content 32","some content 33","some content 34","some content 35","some content 36"];

  		$scope.controllerInit = function() {

  		};
  		
      $scope.changePage = function(path) {
        $location.path(path);       
      };

      $scope.showDetails = function() {
        console.log("here");
        $scope.changePage("/entityDetails");
      };

  		$scope.controllerInit();

    }
  ]);

})();