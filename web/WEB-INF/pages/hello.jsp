<%@taglib prefix="sec"
		  uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
	<meta charset="utf-8">
	<title>Event Feedback Tracking</title>

	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
</head>

<body>

<div class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="/">Event Feedback Tracking</a>
		</div>

		<div class="navbar-collapse collapse" id="navbar-main">
			<div class="nav navbar-nav">
				<li>
					<a href="/events">Your events</a>
				</li>
			</div>
			<div class="nav navbar-nav navbar-right">
				<li>
					<p class="navbar-text">Signed in as: ${currentUser.firstName}  ${currentUser.lastName}</p>
				</li>
				<li>
					<a data-method="POST" href="/logout">Sign out</a>
				</li>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<h2>${message}</h2>
</div>

</body>
</html>