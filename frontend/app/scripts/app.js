(function () {

'use strict';

  angular.module('socialSentimentApp', ['ngRoute'])

  .config(['$locationProvider','$routeProvider',function($locationProvider, $routeProvider) {      
      // routes
      $routeProvider
        .when("/", {
          templateUrl: "partials/home.html",
          controller: "homeController"
        })
        .when("/showSentiment",{
          templateUrl: "partials/showSentiment.html",
          controller: "showSentimentController"
        })
        .when("/showEntities",{
          templateUrl: "partials/showEntities.html",
          controller: "showEntitiesController"
        })
        .when("/entityDetails",{
          templateUrl: "partials/entityDetails.html",
          controller: "entityDetailsController"
        })
        .otherwise({
           redirectTo: '/'
        });        
        
    }
  ]);

}());