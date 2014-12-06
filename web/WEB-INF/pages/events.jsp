<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
  <div class="row">
    <h2>Your events</h2>
    <div class="col-sm-7">
      <c:if test="${!empty events}">
        <table class="table table-bordered table-striped">
          <thead>
          <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Date</th>
            <th>URL</th>
            <th>Hashtags</th>
            <th>Rating</th>
            <th>Details</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${events}" var="event">
            <tr>
              <td>${event.title}</td>
              <td>${event.description}</td>
              <td>${event.date}</td>
              <td><a href="${event.url}" method="get">${event.url}</a></td>
              <td>${event.getHashtagsString(", ")}</td>
              <td>${event.getEventRating()}</td>
              <td>
                <a href="${event.url}" method="get">Details</a>
              </td>
              <td>
                <form action="/events/delete/${event.id}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </c:if>
    </div>
    <div class="col-sm-5">
      <div class="well bs-component">
        <form:form method="POST" action="/events" modelAttribute="event" class="form-horizontal">
          <fieldset>
            <legend>Add event</legend>
            <div class="form-group">
              <form:label cssClass="col-lg-4 control-label" path="title">Title:</form:label>
              <div class="col-lg-8">
                <form:input path="title" cssClass="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <form:label cssClass="col-lg-4 control-label" path="description">Description:</form:label>
              <div class="col-lg-8">
                <form:input path="description" cssClass="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <form:label cssClass="col-lg-4 control-label" path="date">Date:</form:label>
              <div class="col-lg-8">
                <form:input path="date" cssClass="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-4 control-label">Hashtags</label>
              <div class="col-lg-8">
                <input name="hashtagsString" class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <div class="col-lg-10 col-lg-offset-2">
                <button type="submit" value="Submit" class="btn btn-primary">Submit</button>
              </div>
            </div>
          </fieldset>
        </form:form>
      </div>
    </div>
  </div>
</div>

</body>
</html>