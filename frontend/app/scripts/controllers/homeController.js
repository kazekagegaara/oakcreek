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
        sentimentCall.call("",$scope.sentimentCallSuccess,$scope.serviceError);//,"json/getSentiment.json");
      };

      $scope.getHistory = function() {
        var historyCall = new serviceCall("getHistory","GET");        
        historyCall.call("",$scope.historyCallSuccess,$scope.serviceError);//"json/getHistory.json");        
      }

      $scope.serviceError = function(data, status, headers, config){
        console.log(data);
        alert("Error! Please try again later.");
      };

      $scope.historyCallSuccess = function(data, status, headers, config){        
        console.log(data);
        activeData.setSentimentHistory(data.result);
        $scope.changePage("/showHistory");
      };

      $scope.sentimentCallSuccess = function(data, status, headers, config){
        console.log(data);
        activeData.setSentiment(data.result);
        activeData.setTimestamp(data.timestamp);
        console.log(activeData.getSentiment());
        $scope.changePage("/showSentiment");
      };

  		$scope.controllerInit();

    }
  ]);

})();