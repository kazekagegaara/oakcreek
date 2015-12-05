(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').service('activeData', function(){
  		
      this.entities = [];    
      this.sentiment = '';
      
      this.setSentiment = function(sentiment){
        this.sentiment = sentiment;
      };     
  		
      this.getSentiment = function(){
        return this.sentiment;
      };  

      this.setEntities = function(entities){
        this.entities = entities;
      };     
      
      this.getEntities = function(){
        return this.entities;
      };            
  		
  	});

})();