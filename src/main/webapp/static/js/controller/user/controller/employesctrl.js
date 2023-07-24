myapp.controller('employesctrl', function($scope, $rootScope, $http, $location,myConfig) {

	$rootScope.operation="";
	$rootScope.denied_error = "";
	$scope.error ="";
	$rootScope.emp_response_msg="";
	$rootScope.denied_error = "";
	$rootScope.edit_pass= 
	         {
			"new_pass": ""  
	          };
	$rootScope.employe_show_list = true;
	$scope.fn_load = function() {
		
		console.log("inside in userscrtl");
		$http.get(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'employee/list').success(function(data) {
			if (data.exception == null) {
				
				if (data.employees!= null){
					$rootScope.response_msg="";
					$rootScope.show_logout_btn = true;
					$scope.employeList =data.employees;
			     	 $rootScope.show_auth_user = true;
					$rootScope.employe_show_list = true;
					$rootScope.permission_show_link = true;

					console.log($scope.employeList);
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
				$rootScope.employe_show_list = false;
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
			
			if (data.status == 404) {
				$rootScope.authenticated = true;
		 	$location.path("/exception");}
		});
	}

	 
	$scope.editEmploye = function(id) {
		
		console.log("inside in edit function "+id);
		$http.get(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'employee/edit?id='+id).success(function(data) {

			if(data.responseCode==200){
				$rootScope.edit_employe = data;
				$rootScope.show_auth_user = true;
			} 
		}).error(function(data, status, headers) {
			if (status == 401) {

				$location.path("/");
			}
			if (status == 403) {
				$rootScope.employe_show_list = false;
				$rootScope.denied_error = "Access Denied";
				$location.path("/accessdenied");
				
			 
			}
			if (data.status == 404) {
				$rootScope.authenticated = true;
		 	$location.path("/exception");}
		});
	}

	$scope.updateEmploye = function(edit_employe,edit_pass){
		$http.post(myConfig.url+""+myConfig.port+""+myConfig.applicationName+'employee/update',edit_employe).success(function(data) {

			if(data.responseCode==200){
				$rootScope.response = data;
				$rootScope.emp_response_msg = 'Update Employe record Successful';
				
				
				
				$location.path("/emplist");
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


	$scope.addEmploye = function() {
		var a=$scope.firstName;
		var b=$scope.email;
		var c=$scope.contact;
		
		
		
		
		if (/^\+?([0-9]{2})\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})$/.test(c))  
		  {
			if(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(b))
			{
			$rootScope.employe_response_msg ="";
			
			return	$http({
				url: myConfig.url+""+myConfig.port+""+myConfig.applicationName+'employee/add',
				method: 'POST',


				data:   {"firstName":$scope.firstName,"lastName":$scope.lastName,"address":$scope.address,"contact":$scope.contact,"email":$scope.email},
				headers : {'Content-Type': 'application/json'}
			}).success(function(data) { 	
				console.log("inside in addemploye function success"+data.responseCode);
				if(data.responseCode==200){
					console.log("inside in if");
					$scope.response_msg = 'Added Employe record Successful';
					$scope.fn_load();
					$scope.firstName='';
					$scope.lastName='';
					$scope.address='';
					$scope.contact='';
					$scope.email='';
					$location.path("/emplist");
					$scope.response_msg = 'Added Employe record Successful';
					 

				}else if(data.responseCode==409){
					$scope.firstName='';
					$scope.lastName='';
					$scope.response_msg = '';
					$scope.error = "employe already exists  ";
				}
				else{
					$scope.denied_error = "Access Denied";
				}

			}).error(function(data) {
				console.log("inside in addUser function error"+data);
				if (data == 403) {
					$scope.firstName='';
					$scope.lastName='';
					$location.path("/accessdenied");
				}
				if (data.status == 404) {
					 
			 	$location.path("/exception");}
				 
			});
			
			}
			else{
				alert("please provide a valid email");
			}
			
			
		  }  
		else{
			alert("please provide a valid contact");
		}		
	}
});
