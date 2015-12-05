(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('entityDetailsController', ['$scope','$location','$timeout','serviceCall',function($scope,$location,$timeout,serviceCall) {
    
      $scope.EntityName = '';
      $scope.Description = '';
      $scope.Image = '';
      $scope.Thumbnail = '';
      $scope.References = '';
      $scope.RdfTypes = '';
      $scope.Label = '';
      $scope.EntityType = '';
      $scope.Website = '';
      $scope.Sentiment = '';
      $scope.LinkedDataSource = '';
      $scope.SocialMediaSource = '';


      $scope.controllerInit = function(){
        var entityDetailsCall = new serviceCall("getDetails","GET");
        entityDetailsCall.call("",$scope.entityDetailsCallSuccess,$scope.serviceError,"json/getDetails.json");                
  	  };
          
      $scope.serviceError = function(data, status, headers, config){
        console.log(data);
        alert("Error! Please try again later.");
      };

      $scope.entityDetailsCallSuccess = function(data, status, headers, config){
        console.log(data);
        $scope.EntityName = data.EntityName;
        $scope.Description = data.Description;
        $scope.Image = data.Image;
        $scope.Thumbnail = data.Thumbnail;
        $scope.References = data.References;
        $scope.RdfTypes = data.RdfTypes;
        $scope.Label = data.Label;
        $scope.EntityType = data.EntityType;
        $scope.Website = data.Website;
        $scope.Sentiment = data.Sentiment;
        $scope.LinkedDataSource = data.LinkedDataSource;
        $scope.SocialMediaSource = data.SocialMediaSource;
      };

  	  $scope.controllerInit();
    }
  ]);

})();