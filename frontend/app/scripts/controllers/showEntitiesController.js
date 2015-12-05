(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('showEntitiesController', ['$scope','$location','$timeout','activeData','serviceCall',function($scope,$location,$timeout,activeData,serviceCall) {
      	
  		$scope.Entities;
      $scope.Keywords;

  		$scope.controllerInit = function() {        
        $scope.Entities = activeData.getEntities();        
        $scope.Keywords = activeData.getKeywords();
  		};
  		
      $scope.changePage = function(path) {
        $location.path(path);       
      };

      $scope.showDetails = function(name,type) {        
        console.log(name);
        console.log(type);
        $scope.changePage("/entityDetails");
      };

  		$scope.controllerInit();

    }
  ]);

})();