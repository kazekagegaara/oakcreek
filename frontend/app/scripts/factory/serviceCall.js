(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').factory('serviceCall', function($http){

  		var url = {};
      
  		// constructor to set up certain defaults
  		function serviceCall(serviceName,callMethod){  		
        /*jshint validthis: true */	
  			this.url = {
  				
  				method : callMethod,
  				name : serviceName 
  			};  			
  		}

  		serviceCall.prototype.call = function(payload,success,error,mockURL){
  			var serviceURL = mockURL ||  "http://localhost:8080"+ "/" + this.url.name + "?" + payload;
        console.log(payload);  		
  			$http(
			    { 
			      method: this.url.method,		      
			      url: serviceURL			      
			    }
	  		).
	    	success(function(data, status, headers, config) {
	      		success(data, status, headers, config);
	    	}).
	    	error(function(data, status, headers, config) {
	      		error(data, status, headers, config);
	    	});
  		};

  		return serviceCall;
  	});

})();