(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('homeController', ['$scope','$location','$timeout','serviceCall','activeData',function($scope,$location,$timeout,serviceCall,activeData) {
    
      $scope.controllerInit = function(){
    
  		};
    
      $scope.changePage = function(path){
      	$location.path(path);    		
  		};

      $scope.getSentiment = function() {
        var sentimentCall = new serviceCall("getSentiments","GET");
        $timeout(function() {
          sentimentCall.call("",$scope.sentimentCallSuccess,$scope.serviceError,"json/getSentiment.json");        
        }, 5000);        
      };

      $scope.serviceError = function(data, status, headers, config){
        console.log(data);
        alert("Error! Please try again later.");
      };

      $scope.sentimentCallSuccess = function(data, status, headers, config){
        console.log(data);
        activeData.setSentiment(data.result);
        console.log(activeData.getSentiment());
        $scope.changePage("/showSentiment");
      };

  		$scope.controllerInit();

    }
  ]);

})();