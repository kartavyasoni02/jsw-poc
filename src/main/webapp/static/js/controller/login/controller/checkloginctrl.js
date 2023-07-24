myapp.controller('checkloginctrl', function($scope, $http, $location, $window,
		checkloginservice, $log, $rootScope,myConfig) {
	$rootScope.authenticated = true;
	$rootScope.authenticated_show_link = false;
	$rootScope.show_btn = false;
	$scope.String_error = false;
    
	$scope.checkLogin = function() {

		var pwd =sha256_digest($scope.password);
		
	 		$scope.password = pwd;
console.log(pwd);
		var response = checkloginservice.checkLoginservice($scope, $http);

		response.success(function(data) {

			if (data.responseCode == 200 && data.authorities != null) {
				$rootScope.authenticated = false;

				for (var int = 0; int < data.authorities.length; int++) {
					if (data.authorities[int] == 'ROLE_ADMIN') {
						authenticated_show_link = true;
						$location.path("/admin");
						break;
					} else if (data.authorities[int] == 'ROLE_USER') {
						$location.path("/user");
						
						break;
					}
				}
			} else if (data.responseCode == 100) {
				$scope.error = data.message;
				$scope.password = '';
				$scope.username = '';
			} else
				$location.path("/");

		});

		response.error(function(data, status, headers) {
			
			$location.path("/exception");
			/*alert("AJAX failed to post data, status=" + status + "data is: "
					+ data + " header is: " + headers);*/
		});
	}
});
