myapp.controller("schedularController", function($http, $scope, $location,
		$rootScope,myConfig) {

	$scope.jobName = "";
	
	$scope.jobs = [];
	
	$scope.empty = function() {

		$scope.timeslice = "";
		$scope.date = "";
		$scope.month = "";
		$scope.minuteInterval = "";
		$scope.day = "";
		$scope.hourInterval = "";
		$scope.dayOfWeek = "";
		$scope.days = [];

		$rootScope.success_msg = '';
		$rootScope.response_msg = '';
	};
	
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
	};

	$scope.schduleJob = function(scheduleRadio) {

		console.log("schduleMinuteJob");
		
		console.log($scope.jobName);
		console.log($scope.minuteInterval);

		if ($scope.days.length != 0) {

			for (var int = 0; int < $scope.days.length; int++) {
				$scope.dayOfWeek += $scope.days[int];
				if (int != ($scope.days.length) - 1) {
					$scope.dayOfWeek += ',';
				}
			}
		}

		$http({
			url : 'http://localhost:8080/scheduleJobs',
			method : 'POST',

			data : {
				"jobName" : $scope.jobName,
				"minuteInterval" : $scope.minuteInterval,
				"scheduleRadio" : scheduleRadio,
				"time" : $scope.timeslice,
				"dayOfWeek" : $scope.dayOfWeek,
				"monthDate" : $scope.date,
				"month" : $scope.month,
				"hourInterval" : $scope.hourInterval,
			},
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(response) {
			$rootScope.response_msg = 'Schedule Job Successful';
			$location.path("/configure");
		}).error(function(data) {

			alert("Job is scheduled error " + data);

		});

		$scope.empty();
	}

	$scope.reScheduleJob = function(Jobname) {
		console.log("inside reScheduleJob  " + Jobname);

		$http.get('http://localhost:8080/startJob/' + Jobname).success(
				function(data) {
					console.log("inside reScheduleJob success" + data);

					$rootScope.success_msg = 'Start Job Successful';
					$scope.getAllschduleRunningJob();

				}).error(function(data) {
			console.log("inside reScheduleJob error" + data);
		});
	}

	$scope.stopScheduleJob = function(jobName) {
		console.log("inside stopScheduleJob go function " + jobName);
		$http.get('http://localhost:8080/stopJob/' + jobName).success(
				function(data) {

					if (data.responseCode == 200) {
						console.log("inside if responseCode ");
						$rootScope.success_msg = 'Stop Job Successful';
						$scope.getAllschduleRunningJob();

					}

					$rootScope.authenticated_show_link = true;
				}).error(function(data, status, headers) {

			$location.path("/exception");

		});

	}

	$scope.openConfirmBox = function(jobName) {

		$.confirm({
			text : "Are you sure you want to Stop that Job?",
			title : "Confirmation required",
			confirm : function(button) {
				$scope.stopScheduleJob(jobName);
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

	$scope.getAllschduleRunningJob = function() {
		//$rootScope.success_msg = "";
		$http.get('http://localhost:8080/populateJobs').success(function(data) {
			$rootScope.show_btn = true;
			$rootScope.authenticated_show_link = true;
			console.log("inside  success" + data);
			$scope.listSchedulesJob = data.quartzList;

		}).error(function(data) {

			console.log("Job is scheduled error " + data);

		});
		;

	}

});

myapp.filter('monthName', [ function() {
	return function(monthNumber) {
		var monthNames = [ 'January', 'February', 'March', 'April', 'May',
				'June', 'July', 'August', 'September', 'October', 'November',
				'December' ];
		return monthNames[monthNumber - 1];
	}
} ]);