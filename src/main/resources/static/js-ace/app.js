var aceApp= angular.module('aceApp',['ngRoute']);

aceApp.config(['$routeProvider',function($routeProvider){
	$routeProvider.when('/tables',{
		controller:'TableController',
		templateUrl:'views/table.html',
	}).when('/blank',{
		controller:'BlankController',
		templateUrl:'views/blank.html',
	}).when('/form-elements',{
		controller:'FormElementsController',
		templateUrl:'views/form-elements.html',
	})
	
	;
}]);