(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('showSentimentController', ['$scope','$location','$timeout','activeData','serviceCall',function($scope,$location,$timeout,activeData,serviceCall) {

  		$scope.sentiment = "";

      $scope.controllerInit = function() {
        $scope.sentiment = activeData.getSentiment();
        if($scope.sentiment === "positive") {
          $scope.sentimentColor = {"color":"green"};
        } else if($scope.sentiment === "negative") {
          $scope.sentimentColor = {"color":"red"};
        } else if($scope.sentiment === "mixed") {
          $scope.sentimentColor = {"color":"gray"};
        } else if($scope.sentiment === "neutral") {
          $scope.sentimentColor = {"color":"white"};
        }
      };
      
  		$scope.changePage = function(path) {
      	$location.path(path);    		
  		};

      $scope.getDetails = function() {
        var detailsCall = new serviceCall("getSentiments","GET");        
        detailsCall.call("",$scope.detailsCallSuccess,$scope.serviceError,"json/getEntities.json");        
      };

      $scope.serviceError = function(data, status, headers, config){
        console.log(data);
        alert("Error! Please try again later.");
      };

      $scope.detailsCallSuccess = function(data, status, headers, config){
        console.log(data);            
        activeData.setEntities(data.entities);
        activeData.setKeywords(data.keywords);
        $scope.changePage("/showEntities");
      };

      $scope.controllerInit();

  	}    
  ]);

})();