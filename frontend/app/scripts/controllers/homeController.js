(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('homeController', ['$scope','$location','serviceCall','activeData',function($scope,$location,serviceCall,activeData) {
    
      $scope.controllerInit = function(){
    
  		};
    
      $scope.changePage = function(path){
      	$location.path(path);    		
  		};

      $scope.getSentiment = function() {
        $scope.changePage("/showSentiment");
      };

  		$scope.controllerInit();

    }
  ]);

})();