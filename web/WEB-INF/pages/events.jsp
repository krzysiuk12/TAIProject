<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta charset="utf-8">
  <title>Events</title>

  <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link href="bootstrap.css" rel="stylesheet">
  <link href="bootstrap-responsive.css" rel="stylesheet">
</head>

<body>

<div class="container">
  <div class="row">
    <div class="span8 offset2">
      <h1>Events</h1>
      <form:form method="POST" action="/events" modelAttribute="event" class="form-horizontal">
      <div class="control-group">
        <form:label cssClass="control-label" path="title">Title:</form:label>
        <div class="controls">
          <form:input path="title"/>
        </div>
      </div>
      <div class="control-group">
        <form:label cssClass="control-label" path="description">Description:</form:label>
        <div class="controls">
          <form:input path="description"/>
        </div>
      </div>
      <div class="control-group">
        <form:label cssClass="control-label" path="date">Date:</form:label>
        <div class="controls">
          <form:input path="date"/>
        </div>
      </div>
      <div class="control-group">
        <div class="controls">
          <input type="submit" value="Add Event" class="btn"/>
          </form:form>
        </div>
      </div>

      <c:if test="${!empty events}">
        <h3>Events</h3>
        <table class="table table-bordered table-striped">
          <thead>
          <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Date</th>
            <th>URL</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${events}" var="event">
            <tr>
              <td>${event.title}</td>
              <td>${event.description}</td>
              <td>${event.date}</td>
              <td>${event.url}</td>
              <td>
                <form action="/events/delete/${event.id}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </c:if>
    </div>
  </div>
</div>

</body>
</html>