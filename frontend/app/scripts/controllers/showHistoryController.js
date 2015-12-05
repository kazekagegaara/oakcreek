(function () {

'use strict';
	
	//Load controller
  	angular.module('socialSentimentApp').controller('showHistoryController', ['$scope','$location','$timeout','serviceCall','activeData',function($scope,$location,$timeout,serviceCall,activeData) {
    
      $scope.data = {};
      $scope.chartData = [];

      $scope.controllerInit = function(){
        $scope.data = activeData.getSentimentHistory();
        console.log($scope.data);        

        angular.forEach($scope.data,function(value,index){
          console.log(value);
          var temp = [];         
          var timestamp = parseInt(value.timstamp)*1000;
          temp.push(timestamp);
          if(value.sentiment === "positive") {
            temp.push(4);  
          } else if(value.sentiment === "negative") {
            temp.push(1);  
          } else if(value.sentiment === "neutral") {
            temp.push(3);  
          } else if(value.sentiment === "mixed") {
            temp.push(2);  
          }
          $scope.chartData.push(temp);          
        });
        console.log($scope.chartData);

        $(function () {
          var chart = new Highcharts.Chart({
              chart: {
                  renderTo: 'container',
                  backgroundColor: 'rgba(255, 255, 255, 0.85)',
                  marginRight: 50 //add margin for text
              },
              title: {
                text: 'Sentiment History'
              },
              xAxis: {          
                title: {text: 'Timestamps'},
                type: 'datetime'
              },
              yAxis: {          
                title: {text: 'Sentiments - 1: Negative 2: Mixed 3:Neutral 4: Positive'},
                tickInterval: 1,
                min: 0,
                max: 4
              },
              series: [{
                  showInLegend: false,
                  data: $scope.chartData
                  }]
          });        
      });

  		};
    
      $scope.changePage = function(path){
      	$location.path(path);    		
  		};

  		$scope.controllerInit();

    }
  ]);

})();