!function(){"use strict";angular.module("socialSentimentApp").controller("homeController",["$scope","$location","serviceCall","activeData",function(n,t,e,o){n.controllerInit=function(){},n.changePage=function(n){t.path(n)},n.getSentiment=function(){n.changePage("/showSentiment")},n.controllerInit()}])}();