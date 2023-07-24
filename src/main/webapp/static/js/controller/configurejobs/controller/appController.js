myapp.controller("appController", function($http, $scope, $location) {

	$scope.showminute = false;
	$scope.showhourly = false;
	$scope.showdaily = false;
	$scope.showweekly = false;
	$scope.showmonthly = false;
	$scope.showyearly = false;

	$scope.minutesload = function() {

		$scope.showminute = true;

		$scope.showhourly = false;
		$scope.showdaily = false;
		$scope.showmonthly = false;
		$scope.showyearly = false;
		$scope.showweekly = false;

	}

	$scope.yearlyload = function() {

		$scope.showyearly = true;

		$scope.showminute = false;
		$scope.showhourly = false;
		$scope.showdaily = false;
		$scope.showmonthly = false;
		$scope.showweekly = false;

	}

	$scope.dailyload = function() {

		$scope.showdaily = true;

		$scope.showminute = false;
		$scope.showhourly = false;
		$scope.showweekly = false;
		$scope.showmonthly = false;
		$scope.showyearly = false;

	}

	$scope.hourlyload = function() {

		$scope.showhourly = true;

		$scope.showminute = false;
		$scope.showweekly = false;
		$scope.showdaily = false;
		$scope.showmonthly = false;
		$scope.showyearly = false;

	}

	$scope.monthlyload = function() {

		$scope.showmonthly = true;

		$scope.showminute = false;
		$scope.showhourly = false;
		$scope.showdaily = false;
		$scope.showweekly = false;
		$scope.showyearly = false;

	}

	$scope.weeklyload = function() {

		$scope.showweekly = true;

		$scope.showminute = false;
		$scope.showhourly = false;
		$scope.showdaily = false;
		$scope.showmonthly = false;
		$scope.showyearly = false;

	}
});
