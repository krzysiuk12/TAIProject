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

    <h3>Event details</h3>

    <div class="col-sm-6">
      <div class="panel panel-default">
        <div class="panel-heading">Event name</div>
        <div class="panel-body"><strong>${event.title}</strong></div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">Description</div>
        <div class="panel-body"><strong>${event.description}</strong></div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">Date</div>
        <div class="panel-body"><strong>${event.date}</strong></div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">Author</div>
        <div class="panel-body"><strong>${event.creator.firstName} ${event.creator.lastName}</strong></div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">Hashtags</div>
        <div class="panel-body"><strong>${event.getHashtagsString(", ")}</strong></div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">Rating</div>
        <div class="panel-body"><strong>${event.getEventRating()}</strong></div>
      </div>
    </div>

    <div class="col-sm-6">

      <div id="add-comment">
        <div class="well bs-component">
          <form:form method="POST" action="/events/${event.id}/comments" modelAttribute="comment" class="form-horizontal">
            <fieldset>
              <legend>Add comment</legend>
              <div class="form-group">
                <form:label cssClass="col-lg-4 control-label" path="comment">Comment:</form:label>
                <div class="col-lg-8">
                  <form:input path="comment" cssClass="form-control"/>
                </div>
              </div>
              <div class="form-group">
                <form:label cssClass="col-lg-4 control-label" path="rating">Rating:</form:label>
                <div class="col-lg-8">
                  <form:select path="rating" items="${ratings}" itemLabel="value"/>
                </div>
              </div>
              <div class="form-group">
                <form:label cssClass="col-lg-4 control-label" path="privateComment">Private?</form:label>
                <div class="col-lg-8">
                  <form:checkbox path="privateComment"/>
                </div>
              </div>
              <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                  <button type="submit" value="Add Comment" class="btn btn-primary">Add Comment</button>
                </div>
              </div>
            </fieldset>
          </form:form>
        </div>
      </div>

      <c:if test="${!empty comments}">
        <h3>Comments</h3>
        <table class="table table-bordered table-striped">
          <thead>
          <tr>
            <th>Author</th>
            <th>Comment</th>
            <th>Rating</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${comments}" var="comment">
            <tr>
              <td>${comment.commenter.firstName} ${comment.commenter.lastName}</td>
              <td>${comment.comment}</td>
              <td>${comment.rating.value}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </c:if>

    </div>

  </div>

  <div class="row">

    <c:if test="${!empty tweets}">
      <h3>Tweets</h3>
      <table class="table table-bordered table-striped">
        <thead>
        <tr>
          <th>Author</th>
          <th>Text</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tweets}" var="tweet">
          <tr>
            <td>${tweet.getFromUser()}</td>
            <td>${tweet.getText()}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:if>

  </div>

</div>

</body>
</html>