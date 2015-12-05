(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('showEntitiesController', ['$scope','$location','$timeout','activeData','serviceCall',function($scope,$location,$timeout,activeData,serviceCall) {
      	
  		$scope.Entities;

  		$scope.controllerInit = function() {        
        console.log(activeData.getEntities());
        $scope.Entities = activeData.getEntities();        
  		};
  		
      $scope.changePage = function(path) {
        $location.path(path);       
      };

      $scope.showDetails = function() {        
        $scope.changePage("/entityDetails");
      };

  		$scope.controllerInit();

    }
  ]);

})();