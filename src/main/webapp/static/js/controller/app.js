var myapp = angular.module('myApp', ['ngRoute']);

/*

myapp.factory('$exceptionHandler', ['$injector', function($injector) {

    var $location;
    return function(exception, cause) {
        $location = $location || $injector.get('$location');
        $location.path("/exception");
    };
}]); */
myapp.constant("myConfig", {
    "url": "http://localhost:",
    "port": "8080/",
    //  "applicationName": "springsecurity/"
"applicationName": ""
})
myapp.config(function($routeProvider,$httpProvider) {
$routeProvider
	.when('/welcome', {
		templateUrl : 'static/js/controller/welcome/templates/welcome.html',
        controller  : 'welcomectrl'
	})
	.when('/userlist', {
		templateUrl : 'static/js/controller/user/templates/userList.html',
		controller  : 'usersctrl'
	})
	
	
	.when('/emplist', {
		templateUrl : 'static/js/controller/user/templates/employeList.html',
		controller  : 'employesctrl'
	})
	
	.when('/empedit', {
		templateUrl : 'static/js/controller/user/templates/emp-edit.html',
		controller  : 'employesctrl'
	})
	.when('/empadd', {
		templateUrl : 'static/js/controller/user/templates/emp-add.html',
		controller  : 'employesctrl'
	})
	
	
	.when('/useredit', {
		templateUrl : 'static/js/controller/user/templates/user-edit.html',
		controller  : 'usersctrl'
	})
	.when('/userdelete', {
		templateUrl : 'static/js/controller/user/templates/userList.html',
		controller  : 'usersctrl'
	})
	.when('/rolelist', {
		templateUrl : 'static/js/controller/role/templates/roles_list.html',
		controller  : 'rolectrl'
	})
	
	.when('/rolelist_edit', {
		templateUrl : 'static/js/controller/role/templates/edit_role.html',
		controller  : 'rolectrl'
	})
	
	.when('/roledelete', {
		templateUrl : 'static/js/controller/role/templates/roles_list.html',
		controller  : 'rolectrl'
	})
	
	.when('/roleedit',{
		templateUrl : 'static/js/controller/role/templates/roles_list.html',
		controller  : 'rolectrl'
	})
	
	.when('/roleadd',{
		templateUrl : 'static/js/controller/role/templates/roles_list.html',
		controller  : 'rolectrl'
	})
	
	
	
	.when('/rolecancel', {
		templateUrl : 'static/js/controller/role/templates/roles_list.html',
		controller  : 'rolectrl'
	})
	.when('/listschedulejob', {
		templateUrl : '/static/js/controller/configurejobs/templates/lists-schdule-Job.html',
	})
	.when('/configure', {
		templateUrl : 'static/js/controller/configurejobs/templates/configuration-schedule-job.html',
	})
	 .when('/', {
		templateUrl : 'static/js/controller/login/templates/login.html',
		controller  : 'checkloginctrl'
	})
	 .when('/dynaplan', {
		templateUrl : 'static/js/controller/dynaplan/templates/dynaplan-chart.html',
		controller  : 'dynactrl'
	})
	
	.when('/configuredynaplan', {
		templateUrl : 'static/js/controller/dynaplan/templates/configure-dynaplan.html',
		controller  : 'configDynactrl'
	})
	 
	
	.when('/planadd',{
		templateUrl : 'static/js/controller/dynaplan/templates/configure-dynaplan.html',
		controller  : 'configDynactrl'
	})
	
	.when('/dynalist_edit', {
		templateUrl : 'static/js/controller/dynaplan/templates/dyna_edit.html',
		controller  : 'configDynactrl'
	})
	
	
	.when('/dynaedit',{
		templateUrl : 'static/js/controller/dynaplan/templates/configure-dynaplan.html',
		controller  : 'configDynactrl'
	})
	
	
	.when('/dynadelete', {
		templateUrl : 'static/js/controller/dynaplan/templates/configure-dynaplan.html',
		controller  : 'configDynactrl'
	})
	
	
	 .when('/admin', {
		templateUrl : 'static/js/controller/admin/templates/admin.html',
		controller  : 'adminctrl'
	})
	.when('/user', {
		templateUrl : 'static/js/controller/user/templates/user.html',
		controller  : 'userctrl'
	})
	.when('/exception', {
		templateUrl : 'static/js/controller/exception/templates/exception.html',
		controller  : 'exceptionctrl'
	}).otherwise('/');
$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
});