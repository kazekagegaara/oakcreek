(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').service('activeData', function(){
  		
      this.entities = [];    
      this.keywords = [];    
      this.sentiment = '';
      this.timestamp = '';
      this.sentimentHistory = {};
      this.selectedEntity = '';
      this.selectedType = '';
      
      this.setSentiment = function(sentiment){
        this.sentiment = sentiment;
      };     
  		
      this.getSentiment = function(){
        return this.sentiment;
      };
      
      this.setTimestamp = function(timestamp){
        this.timestamp = timestamp;
      };     
      
      this.getTimestamp = function(){
        return this.timestamp;
      };  

      this.setSelectedEntity = function(selectedEntity){
        this.selectedEntity = selectedEntity;
      };     
      
      this.getSelectedEntity = function(){
        return this.selectedEntity;
      };  

      this.setSelectedType = function(selectedType){
        this.selectedType = selectedType;
      };     
      
      this.getSelectedType = function(){
        return this.selectedType;
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