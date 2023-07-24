myapp.controller("jobController", function($http, $scope, $location,
		$rootScope,myConfig) {
	
	$scope.jobs = [];
	
	$scope.get_jobs = function() {
		console.log("inside in get_jobs");
	$http.get(myConfig.url + "" + myConfig.port + ""+ myConfig.applicationName+'jobs/list')
	.success(function(data) {
		$rootScope.show_btn = true;
		$rootScope.authenticated_show_link = true;
		
		$scope.jobs = data.jobs;
		$rootScope.role_show_list = true;
		$rootScope.permission_show_link = true;
		$rootScope.show_auth_user = true;
		$rootScope.show_logout_btn = true;
	}).error(function(data, status, headers) {
		if (status == 401) {
			$location.path("/");
		}
		if (status == 403) {
			$rootScope.show_btn = true;
			$rootScope.role_show_list = false;
			$rootScope.show_logout_btn = true;

			$rootScope.permission_show_link = true;
			$location.path("/accessdenied");
		}
	});
	}
});