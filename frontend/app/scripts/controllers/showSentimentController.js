(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('showSentimentController', ['$scope','$location',function($scope,$location) {

  		$scope.sentiment = "Positive";

      $scope.controllerInit = function() {
        if($scope.sentiment === "Positive") {
          $scope.sentimentColor = {"color":"green"};
        } else if($scope.sentiment === "Negative") {
          $scope.sentimentColor = {"color":"red"};
        } else if($scope.sentiment === "Mixed") {
          $scope.sentimentColor = {"color":"gray"};
        } else if($scope.sentiment === "Neutral") {
          $scope.sentimentColor = {"color":"white"};
        }
      };
      
  		$scope.changePage = function(path) {
      	$location.path(path);    		
  		};

      $scope.getDetails = function() {
        $scope.changePage("/showEntities");
      };

      $scope.controllerInit();

  	}    
  ]);

})();