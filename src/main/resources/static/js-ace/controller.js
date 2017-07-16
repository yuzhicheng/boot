aceApp.controller('TableController', [ '$rootScope', '$scope', '$http',
		function($rootScope, $scope, $http) {
			$scope.$on('$viewContentLoaded', function() {
				console.log('简单表格页面加载完成');
			});

			$http.get("/list?address=北京").then(function(result) {
				console.log(result.data);
				$scope.people = result.data;
			});

		} ]);

aceApp.controller('BlankController', [ '$rootScope', '$scope', '$http',
		function($rootScope, $scope, $http) {
			$scope.$on('$viewContentLoaded', function() {
				console.log('空页面加载完成');
			});

			$scope.find = function() {
				personName = $scope.personName;
				$http.get('search', {
					params : {
						personName : personName
					}
				}).success(function(data) {
					$scope.person = data;
				});
			};
		} ]);

aceApp.controller('FormElementsController', [ '$rootScope', '$scope', '$http',
		function($rootScope, $scope, $http) {
			$scope.$on('$viewContentLoaded', function() {
				console.log('表单组件页面加载完成');
			});

		} ]);
