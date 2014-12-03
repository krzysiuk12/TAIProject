<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta charset="utf-8">
  <title>Event comments</title>

  <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<div class="container">
  <div class="row">
    <div class="span8 offset2">
      <h1>${event.title} - event details</h1>

      <a href="/events" method="get">Back</a>

      <c:if test="${!empty comments}">
        <h2>Comments</h2>
        <table class="table table-bordered table-striped">
          <thead>
          <tr>
            <th>Comment</th>
            <th>Rating</th>
            <th>Private</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${comments}" var="comment">
            <tr>
              <td>${comment.comment}</td>
              <td>${comment.rating}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </c:if>

      <h2>Add comment</h2>
      <form:form method="POST" action="/events/${event.id}/comments" modelAttribute="comment" class="form-horizontal">
      <div class="control-group">
        <form:label cssClass="control-label" path="comment">Comment:</form:label>
        <div class="controls">
          <form:input path="comment"/>
        </div>
      </div>
      <div class="control-group">
        <div class="controls">
          <input type="submit" value="Add Comment" class="btn"/>
          </form:form>
        </div>
      </div>

      <c:if test="${!empty tweets}">
        <h2>Tweets</h2>
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
</div>

</body>
</html>