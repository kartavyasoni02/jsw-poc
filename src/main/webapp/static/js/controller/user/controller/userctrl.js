myapp.controller('userctrl', function($scope, $rootScope, $http, $location,myConfig) {

	$rootScope.operation="";
	$rootScope.denied_error="";
	$scope.error = $rootScope.error;
	$rootScope.response_msg = "";
	$scope.fn_load = function() {

		$http.get(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'user').success(function(data) {
			if (data.userName == null) {
				$location.path("/");
			}
			else if(data.userName=='admin') {
				$rootScope.authenticated = false;
				$rootScope.show_logout_btn = true;
				$rootScope.show_auth_user = true;
				$rootScope.permission_show_link = true;

			}
			else{
				$rootScope.user_authenticated_show_link = true;
				
				$rootScope.show_auth_user = true;
				$rootScope.permission_show_link = true;
				$rootScope.authenticated = false;
				$rootScope.show_logout_btn = true;
				$rootScope.show_btn = true;
				$rootScope.greeting = "Hi " + data.userName;
			}
		}).error(function(data, status, headers) {
			if (status == 401) {
				$location.path("/");
			}
			if (status == 403) {

				$scope.denied_error = "Access Denied";
			}
		});
	};
});
