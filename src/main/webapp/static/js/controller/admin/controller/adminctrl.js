myapp.controller('adminctrl', function($scope, $rootScope, $http, $location,myConfig) {

	$scope.error = $rootScope.error;
	$scope.logout = function() {
		$http.get(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'checkLogin?logout', {

		}).success(function() {

			$rootScope.authenticated = false;
			$location.path("/");
		}).error(function(data) {
			$rootScope.authenticated = false;
		});
	}
	$scope.user = function() {

		$location.path("/user");
	}
	$scope.admin = function() {
		$location.path("/admin");

	}
	$scope.fn_load = function() {

		$http.get(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'admin').success(function(data) {
			if (data.userName == null) {
				$location.path("/");
			}
			$rootScope.show_btn = true;
			$rootScope.authenticated_show_link = true;
			$scope.greeting = "Hi " + data.userName;
		}).error(function(data, status, headers) {

			if (status == 401) {
				$location.path("/");
			}
			if (status == 403) {

				$rootScope.authenticated = false;
				$rootScope.show_btn = true;
				$scope.denied_error = "Access Denied";
			}
		});
	};
});