(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('entityDetailsController', ['$scope','$location',function($scope,$location) {
    
      $scope.controllerInit = function(){
    
  	  };
    
      $scope.changePage = function(path){
      	$location.path(path);    		
  	  };

  	  $scope.controllerInit();
    }
  ]);

})();