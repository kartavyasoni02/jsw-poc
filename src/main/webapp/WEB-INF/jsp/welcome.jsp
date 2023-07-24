<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

<title>My APP</title>

<!-- Library -->
<script type="text/javascript"
	src="<c:url value="/static/js/controller/scripts/js/jquery-1.12.3.min.js"/>"></script>

<%-- <script
	src="<c:url value="/static/js/controller/scripts/js/jquery-1.6.min.js" />"></script> --%>
<script
	src="<c:url value="/static/js/controller/scripts/js/jquery.canvasjs.min.js" />"></script>

<%-- <script
	src="<c:url value="/static/js/controller/scripts/js/jquery.min.js" />"></script> --%>
<script
	src="<c:url value="/static/js/controller/scripts/js/angular.min.js" />"></script>
<script
	src="<c:url value="/static/js/controller/scripts/js/angular-route.min.js" />"></script>
<script
	src="<c:url value="/static/js/controller/scripts/js/bootstrap.min.js" />"></script>

<script
	src="<c:url value="/static/js/controller/scripts/js/sha256.js" />"></script>

<script
	src="<c:url value="/static/js/controller/scripts/js/jquery.confirm.min.js"/>"></script>

<script
	src="<c:url value="/static/js/controller/scripts/js/bootstrap-clockpicker.min.js"/>"></script>
<script
	src="<c:url value="/static/js/controller/scripts/js/jquery-clockpicker.min.js"/>"></script>

<!-- CSS files -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/js/controller/scripts/css/bootstrap.min.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/js/controller/scripts/css/app.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/js/controller/scripts/css/style.css" />
<%-- 	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/js/controller/scripts/font-awesome.css" /> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/font-awesome/css/font-awesome.min.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/js/controller/scripts/css/bootstrap-clockpicker.min.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/js/controller/scripts/css/jquery-clockpicker.min.css" />

<!-- Custom scripts -->
<script src="<c:url value="/static/js/controller/app.js" />"></script>
<script
	src="<c:url value="/static/js/controller/configurejobs/controller/appController.js" />"></script>
<script
	src="<c:url value="/static/js/controller/configurejobs/controller/schedularController.js" />"></script>

<!-- welcome -->
<script
	src="<c:url value="/static/js/controller/welcome/controller/welcomectrl.js" />"></script>

<!-- Role Controller -->
<script
	src="<c:url value="/static/js/controller/role/controller/rolectrl.js" />"></script>

<!-- home -->
<script
	src="<c:url value="/static/js/controller/dynaplan/controller/dynactrl.js" />"></script>

<!-- admin -->
<script
	src="<c:url value="/static/js/controller/admin/controller/adminctrl.js" />"></script>

<!-- user -->
<script
	src="<c:url value="/static/js/controller/user/controller/userctrl.js" />"></script>

<!-- userList -->
<script
	src="<c:url value="/static/js/controller/user/controller/usersctrl.js" />"></script>

<!-- Exception -->
<script
	src="<c:url value="/static/js/controller/exception/controller/exceptionctrl.js" />"></script>

<!-- Check Login employee -->
<script
	src="<c:url value="/static/js/controller/login/controller/checkloginctrl.js" />"></script>
<script
	src="<c:url value="/static/js/controller/login/service/checkloginservice.js" />"></script>

<script type="text/javascript">

$(document).ready(function(){
$(".toggle").on('click', function() {
    $(this).toggleClass('glyphicon-chevron-right glyphicon-chevron-down');
	});
});
</script>


</head>

<body ng-controller="checkloginctrl">
	<!-- <nav class="navbar navbar-default">
		<div class="container">

			<div class="navbar-header">

				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#myNavbar"
					aria-expanded="false">

					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="">JSW</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav" role="tablist">

					<li ng-show="authenticated"><a href="#"
						style="text-decoration: none;">SignIn</a></li>
					<li ng-show="authenticated_show_link"><a href="#userlist"
						style="text-decoration: none;">Employee Master</a></li>

					<li ng-show="authenticated_show_link" class="dropdown"><a class="dropdown-toggle"
					> Employee <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#userlist">Employee Details</a></li>
							<li><a href="#rolelist">Employee Roles</a></li>
						</ul></li>

					<li ng-show="authenticated_show_link"><a href="#dynaplan"
						style="text-decoration: none;">Dynaplan</a></li>

					<li ng-show="authenticated_show_link"><a href="#configure"
						style="text-decoration: none;">Configure Jobs</a></li>
					<li ng-show="authenticated_show_link"><a href="#rolelist"
						style="text-decoration: none;">Roles</a></li>
					<li ng-show="authenticated_show_link"><a
						href="#listschedulejob" style="text-decoration: none;"
						ng-click="empty()">List Scheduled Jobs</a></li>

				</ul>
				<ul ng-show="authenticated_show_link"
					class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
							Logout</a></li>
				</ul>
			</div>
		</div>
	</nav> -->
	
	<nav class="navbar navbar-default navbar-fixed-top">
	  <div class="container">
    	
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#myNavbar" aria-expanded="false">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
     <a class="navbar-brand" href="#">JSW</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav" role="tablist">

					<li ng-show="authenticated"><a href="#"
						style="text-decoration: none;">SignIn</a></li>
					<li ng-show="authenticated_show_link"><a href="#userlist"
						style="text-decoration: none;">Employee Master</a></li>

					<li ng-show="authenticated_show_link" class="dropdown"><a class="dropdown-toggle"
					> Employee <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#userlist">Employee Details</a></li>
							<li><a href="#rolelist">Employee Roles</a></li>
						</ul></li>

					<li ng-show="authenticated_show_link"><a href="#dynaplan"
						style="text-decoration: none;">Dynaplan</a></li>

					<li ng-show="authenticated_show_link"><a href="#configure"
						style="text-decoration: none;">Configure Jobs</a></li>
					<li ng-show="authenticated_show_link"><a href="#rolelist"
						style="text-decoration: none;">Roles</a></li>
					<li ng-show="authenticated_show_link"><a
						href="#listschedulejob" style="text-decoration: none;"
						ng-click="empty()">List Scheduled Jobs</a></li>

				</ul>
				<ul ng-show="authenticated_show_link"
					class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
							Logout</a></li>
				</ul>
			</div>
    </div>
	</nav>


	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav" ng-show="authenticated_show_link">
			</div>

			<div ng-view class="col-sm-10 text-left container-fluid content"></div>
		</div>
	</div>

	<div ng-view class="container"></div>

	<nav class="navbar navbar-default navbar-fixed-bottom"
		style="background-color: #000000;">
		<div class="container-fluid">
			<div class="container">
				<span class="text-left">Copyright © Yash Technologies. All
					Rights Reserved.</span>
				<p class="site-map pull-right">
					<a href="#">About |</a> <a href="#">Privacy |</a> <a href="#">Terms
						|</a> <a href="#">Contact</a>
				</p>
			</div>
		</div>
	</nav>

</body>
</html>