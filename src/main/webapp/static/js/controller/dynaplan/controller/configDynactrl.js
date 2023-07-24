myapp.controller('configDynactrl',function($scope, $rootScope, $http, $location, myConfig) {

	$rootScope.user_response_msg = "";
	$scope.error = $rootScope.error;
	$rootScope.error = false ;
	$rootScope.denied_error = "";
	$rootScope.dyna_show_list = false;
	$rootScope.operation = "";

	$scope.fn_load = function() {
		console.log("inside in configDynactrl 'dyna/list'");
		$http.get(myConfig.url + "" + myConfig.port + ""+ myConfig.applicationName+'dynaplan/list')
		.success(function(data) {
			
			if (data.exception == null) {
				
				$rootScope.show_btn = true;
				$rootScope.authenticated_show_link = true;
				
					$rootScope.dynaList =data.dynaPlans;
					$rootScope.dyna_show_list = true;
					$rootScope.permission_show_link = true;
					$rootScope.show_auth_user = true;
					$rootScope.show_logout_btn = true;
					//$rootScope.hide_link = true;
			}
				
		}).error(function(data, status, headers) {
			if (status == 401) {
				$location.path("/");
			}
			if (status == 403) {
				$rootScope.show_btn = true;
				$rootScope.dyna_show_list = false;
				$rootScope.show_logout_btn = true;

				$rootScope.permission_show_link = true;
				$location.path("/accessdenied");
			}
		});
	};
	
	$scope.planAdd = function(dynaPlanName) {
		$scope.message =true;
		data = {
				"dynaPlanName" : dynaPlanName
		}

		$http.post(	myConfig.url + "" + myConfig.port + ""+ myConfig.applicationName+'dynaplan/add', data)
		.success(function(data) {

			if (data.responseCode == 200) {
				$rootScope.response_msg = "Record added succesfully";
				$scope.dynaPlanName = "";
				$scope.fn_load();

			}
			if (data.responseCode == 409) {
				$rootScope.response_msg = "Record already exist";
				$scope.dynaPlanName = "";
				$scope.decision = false;

				$scope.fn_load();

			}

		})
		.error(function(data, status, headers) {
			if (status == 401) {
				$location.path("/");
			}
			if (status == 403) {
				//$rootScope.show_btn = true;
				$location.path("/accessdenied");
			}
		});

	}

	$scope.updateDyna = function(edit_dyna_data) {
		
		$scope.message=true;
		$rootScope.response_msg = "";

		data = {
				"id" : edit_dyna_data.id,
				"dynaPlanName" : edit_dyna_data.dynaPlanName
		}

		$http.post(myConfig.url + "" + myConfig.port + ""+myConfig.applicationName+'dynaplan/edit', data)
		.success(function(data) {
		
			
			if (data.responseCode == 200) {
				
				$rootScope.response_msg = "Record Updated succesfully";
				$location.path("/configuredynaplan");
			}

			if (data.responseCode == 409) {
				$rootScope.response_msg = "Record already exist";
				$location.path("/configuredynaplan");

			}

		})
		.error(function(data, status, headers) {
			if (status == 401) {
				$location.path("/");
			}
			if (status == 403) {

				$location.path("/accessdenied");
			}
		});
	}



	$scope.deletedyna = function(id) {

		var flag = confirm("Do you want to delete this record?");

		if (flag) {
			$http.get(myConfig.url + "" + myConfig.port+ ""+myConfig.applicationName+'dynaplan/delete?id=' + id+'&phase=delete')
			.success(function(data) {

				if (data.responseCode == 200) {
					$rootScope.response_msg = "Record deleted succesfully";
					$scope.message = false;
					$location.path("/configuredynaplan");

				}

				$rootScope.authenticated_show_link = true;
			})
			.error(
					function(data, status, headers) {
						if (status == 401) {
							$location.path("/");
						}
						if (status == 403) {
							//$rootScope.show_btn = true;
							$location.path("/accessdenied");
						}
					});
		} else 
			$rootScope.response_msg = "";

	}

	$scope.edit = function(id) {

		$rootScope.response_msg = "";
		
		console.log("inside in go function " + id);


		$http.get(myConfig.url + "" + myConfig.port + ""+ myConfig.applicationName+'dynaplan/edit?id=' + id)
		.success(function(data) {
			if(data.responseCode==200){
				
				$rootScope.edit_dyna_data = data;
				$rootScope.show_auth_user = true;
			} 

		}).error(function(data, status, headers) {
			if (status == 401) {
				$location.path("/");
			}
			if (status == 403) {
				//$rootScope.show_btn = true;
				$location.path("/accessdenied");
			}
		});

	}
	
	
	
	
	

});
