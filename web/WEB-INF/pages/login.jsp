<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns="http://www.w3.org/1999/xhtml">

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
            <div class="nav navbar-nav navbar-right">
                <li>
                    <a href="/auth/twitter">Sign in using Twitter</a>
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