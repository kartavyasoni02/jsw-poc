myapp.service('checkloginservice', function (myConfig) {

	this.checkLoginservice=function($scope,$http) {
		 
	return	$http({
			  url: myConfig.url+""+myConfig.port+""+myConfig.applicationName+'checkLogin',
			  method: 'POST',
			  data:  'username='+$scope.username+'&password='+ $scope.password , // Make sure to inject the service you choose to the controller
			  headers : {'Content-Type': 'application/x-www-form-urlencoded'}
			}).success(function(response) { /* do something here */ }).error(function(data) {
		        $scope.user.error = true;
		        $scope.error = data;
		    });;
	
	}
	
	
});