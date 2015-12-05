(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('entityDetailsController', ['$scope','$location','$timeout','serviceCall','activeData',function($scope,$location,$timeout,serviceCall,activeData) {
    
      $scope.EntityName = '';
      $scope.Description = '';
      $scope.Image = '';
      $scope.Thumbnail = '';
      $scope.References = '';
      $scope.RdfTypes = '';
      $scope.Label = '';
      $scope.Comment = '';
      $scope.EntityType = '';
      $scope.Website = '';
      $scope.Sentiment = '';
      $scope.LinkedDataSource = '';
      $scope.SocialMediaSource = '';


      $scope.controllerInit = function(){
        var timestamp = activeData.getTimestamp();
        var selectedEntity = activeData.getSelectedEntity();
        var selectedType = activeData.getSelectedType();
        var params = "timeStamp="+timestamp+"&NAME="+selectedEntity+"&TYPE="+selectedType;
        var entityDetailsCall = new serviceCall("getDetails","GET");
        entityDetailsCall.call(params,$scope.entityDetailsCallSuccess,$scope.serviceError);//,"json/getDetails.json");                
  	  };
          
      $scope.serviceError = function(data, status, headers, config){
        console.log(data);
        alert("Error! Please try again later.");
      };

      $scope.entityDetailsCallSuccess = function(data, status, headers, config){
        console.log(data.result[0]);        
        $scope.EntityName = data.result[0].entityName;
        $scope.Description = data.result[0].description;
        $scope.Image = data.result[0].image;
        $scope.Thumbnail = data.result[0].thumbnail;
        $scope.References = data.result[0].refrences;
        $scope.RdfTypes = data.result[0].rdfTypes;
        $scope.Label = data.result[0].label;
        $scope.Comment = data.result[0].comment;
        $scope.EntityType = data.result[0].entityType;
        $scope.Website = data.result[0].website;
        $scope.Sentiment = data.result[0].sentiment;
        $scope.LinkedDataSource = data.result[0].linkedDataSource;
        $scope.SocialMediaSource = data.result[0].socialMediaSource;
      };

  	  $scope.controllerInit();
    }
  ]);

})();