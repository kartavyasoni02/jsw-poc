myapp.controller('rolectrl',function($scope, $rootScope, $http, $location, myConfig) {

	$rootScope.user_response_msg = "";
	$scope.error = $rootScope.error;
	$rootScope.error = false ;
	$rootScope.denied_error = "";
	$rootScope.role_show_list = false;
	$rootScope.operation = "";

	$scope.fn_load = function() {
		console.log("inside in rolectrl 'role/list'");
		$http.get(myConfig.url + "" + myConfig.port + ""+ myConfig.applicationName+'role/list')
		.success(function(data) {

			if (data.exception == null) {
				if (data.roles!= null){
					
					$rootScope.show_btn = true;
					$rootScope.authenticated_show_link = true;
					
					$rootScope.roleList =data.roles;
					$rootScope.role_show_list = true;
					$rootScope.permission_show_link = true;
					$rootScope.show_auth_user = true;
					$rootScope.show_logout_btn = true;
					//$rootScope.hide_link = true;
				}
				else 
					$location.path("/");
			}
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
	};

	$scope.roleAdd = function(rolename) {
		$scope.message =true;
		data = {
				"rolename" : rolename
		}

		$http.post(	myConfig.url + "" + myConfig.port + ""+ myConfig.applicationName+'role/add', data)
		.success(function(data) {

			if (data.responseCode == 200) {
				$rootScope.response_msg = "Record added succesfully";
				$scope.rolename = "";
				$scope.fn_load();

			}
			if (data.responseCode == 409) {
				$rootScope.response_msg = "Record already exist";
				$scope.rolename = "";
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

	$scope.updateRole = function(edit_role_data) {
		$scope.message=true;
		$rootScope.response_msg = "";

		data = {
				"id" : edit_role_data.roleId,
				"rolename" : edit_role_data.rolename
		}

		$http.post(myConfig.url + "" + myConfig.port + ""+myConfig.applicationName+'role/edit', data)
		.success(function(data) {
			console.log("success role edit"
					+ data.responseCode);
			if (data.responseCode == 200) {
				console.log(" if role edit"
						+ edit_role_data);
				$rootScope.response_msg = "Record Updated succesfully";
				$location.path("/rolelist");
			}

			if (data.responseCode == 409) {
				$rootScope.response_msg = "Record already exist";
				$location.path("/rolelist");

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



	$scope.deleterole = function(id) {

			$http.get(myConfig.url + "" + myConfig.port+ ""+myConfig.applicationName+'role/delete?id=' + id+'&phase=delete')
			.success(function(data) {

				if (data.responseCode == 200) {
					$rootScope.response_msg = "Record deleted succesfully";
					$scope.message = false;
					$location.path("/rolelist");

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
	}

	$scope.edit = function(id) {

		$rootScope.response_msg = "";
		console.log("inside in go function " + id);


		$http.get(myConfig.url + "" + myConfig.port + ""+ myConfig.applicationName+'role/edit?id=' + id)
		.success(function(data) {
			if(data.responseCode==200){
				$rootScope.edit_role_data = data;
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
	
	$scope.openConfirmBox = function(id) {

		$.confirm({
			text : "Are you sure you want to Delete that Role?",
			title : "Confirmation required",
			confirm : function(button) {
				$scope.deleterole(id);
			},
			cancel : function(button) {
				$rootScope.response_msg = "";
			},
			confirmButton : "Yes",
			cancelButton : "No",
			post : true,
			confirmButtonClass : "btn-danger",
			cancelButtonClass : "btn-default",
			dialogClass : "modal-dialog" // Bootstrap classes for large modal
		});
	}

});
