(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').service('activeData', function(){
  		
      this.entities = [];    
      this.keywords = [];    
      this.sentiment = '';
      this.sentimentHistory = {};
      
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

      this.setKeywords = function(keywords){
        this.keywords = keywords;
      };     
      
      this.getKeywords = function(){
        return this.keywords;
      };

      this.setSentimentHistory = function(sentimentHistory){
        this.sentimentHistory = sentimentHistory;
      };     
      
      this.getSentimentHistory = function(){
        return this.sentimentHistory;
      };  
  		
  	});

})();