myapp.controller('usersctrl', function($scope, $rootScope, $http, $location,myConfig) {

	$rootScope.operation="";
	$rootScope.denied_error = "";
	$scope.error ="";
	$rootScope.denied_error = "";
	$rootScope.edit_pass= 
	         {
			"new_pass": ""  
	          };
	$rootScope.user_show_list = true;
	$scope.fn_load = function() {

		console.log("inside in userscrtl");
		$http.get(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'users/list').success(function(data) {
			if (data.exception == null) {
				if (data.users!= null){
					
					$rootScope.show_btn = true;
					$rootScope.authenticated_show_link = true;
					
					$rootScope.show_logout_btn = true;
					$scope.userList =data.users;
			     	 $rootScope.show_auth_user = true;
					$rootScope.user_show_list = true;
					$rootScope.permission_show_link = true;

					
					}
				else 
				$location.path("/");
			}
		}).error(function(data, status, headers) {
			if (status == 401) {

				$location.path("/");
			}
			if (status == 403) {
				$rootScope.show_logout_btn = true;
				$rootScope.permission_show_link = true;
				$rootScope.user_show_list = false;
				$location.path("/accessdenied");
			}
			if (data.status == 404) {
				$rootScope.authenticated = true;
		 	$location.path("/exception");}
		});
 
	
		$http.get(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'role/list').success(function(data) {
			$rootScope.roleList=data;
		}).error(function(data, status, headers) {
			if (status == 401) {

				$location.path("/");
			}
			/*if (status == 403) {
				$location.path("/accessdenied");
			}*/
			if (data.status == 404) {
				$rootScope.authenticated = true;
		 	$location.path("/exception");}
		});
	}

	 
	$scope.editUser = function(id) {
		console.log("inside in edit function "+id);
		$http.get(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'users/edit?id='+id).success(function(data) {

			if(data.responseCode==200){
				$rootScope.edit_user = data;
				$rootScope.show_auth_user = true;
			} 
		}).error(function(data, status, headers) {
			if (status == 401) {

				$location.path("/");
			}
			if (status == 403) {
				$rootScope.user_show_list = false;
				$rootScope.denied_error = "Access Denied";
				$location.path("/accessdenied");
				
			 
			}
			if (data.status == 404) {
				$rootScope.authenticated = true;
		 	$location.path("/exception");}
		});
	}

	$scope.updateUser = function(edit_user,edit_pass){

		console.log("inside in update User function "+$rootScope.edit_pass.new_pass);
		if($rootScope.edit_pass.new_pass!=""){
			var pwd =sha256_digest($rootScope.edit_pass.new_pass);

			$scope.edit_user.password = pwd;
		}
		$http.post(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'users/edit',edit_user).success(function(data) {

			if(data.responseCode==200){
				$rootScope.response = data;
				$rootScope.user_response_msg  = 'Update User record Successful';
				$scope.username='';
				$scope.password='';
				$location.path("/userlist");
			} 
		 
			 
		}).error(function(data, status, headers) {
			if (status == 401) {

				$location.path("/");
			}
			if (status == 403) {
				$location.path("/accessdenied");
			}
			if (data.status == 404) {
				$rootScope.authenticated = true;
		 	$location.path("/exception");}
		});

	}


	response=$scope.addUser = function() {
		$rootScope.user_response_msg ="";
		var pwd =sha256_digest($scope.password);
		$scope.password = pwd;
		return	$http({
			url: myConfig.url+""+myConfig.port+""+myConfig.applicationName+'users/add',
			method: 'POST',


			data:   {"username":$scope.username,"password":$scope.password,"enabled":$scope.enabled,"roleId":$scope.roleId},
			headers : {'Content-Type': 'application/json'}
		}).success(function(data) { 	
			console.log("inside in addUser function success"+data.responseCode);
			if(data.responseCode==200){
				console.log("inside in if");
				$scope.response_msg = 'add User record Successful';
				$scope.fn_load();
				$scope.username='';
				$scope.password='';
				$scope.rolename= [];
				 

			}else if(data.responseCode==409){
				$scope.username='';
				$scope.password='';
				$scope.response_msg = '';
				$scope.error = "user already exists  ";
			}
			else{
				$scope.denied_error = "Access Denied";
			}

		}).error(function(data) {
			console.log("inside in addUser function error"+data);
			if (data == 403) {
				$scope.username='';
				$scope.password='';
				$location.path("/accessdenied");
			}
			if (data.status == 404) {
				 
		 	$location.path("/exception");}
			 
		});
	}

	$scope.deleteUser = function(id) {
		console.log("inside deleteUser go function "+id);

			$http.get(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'users/delete?id='+id+'&phase=delete').success(function(data) {

				if(data.responseCode==200) {
					console.log("inside if responseCode ");
					$scope.response_msg= 'Delete User record Successful';
					$scope.fn_load();

				}

				$rootScope.authenticated_show_link = true;
			}).error(function(data, status, headers) {
				if (status == 401) {

					$location.path("/");
				}
				if (status == 403) {
					$location.path("/accessdenied");
					//$rootScope.show_btn = true;
				}
				
				if (data.status == 404) {
					$rootScope.authenticated = true;
			 	$location.path("/exception");}
			});
	}
	
	$scope.openConfirmBox = function(id) {

		$.confirm({
			text : "Are you sure you want to Delete that User?",
			title : "Confirmation required",
			confirm : function(button) {
				$scope.deleteUser(id);
			},
			cancel : function(button) {
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
