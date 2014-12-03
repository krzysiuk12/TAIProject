<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta charset="utf-8">
  <title>${event.title} - event details</title>

  <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<div class="container">
  <div class="row">
    <div class="span8 offset2">

      <a href="/events" method="get">Back</a>

      <h1>${event.title} - event details</h1>
      <h3>Description: ${event.description}</h3>
      <h3>Date: ${event.date}</h3>
      <h3>Author: ${event.creator.login}</h3>
      <h3>Hashtags: ${event.getHashtagsString(", ")}</h3>
      <h3>Rating: ${event.getEventRating()}</h3>

      <c:if test="${!empty comments}">
        <h2>Comments</h2>
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
              <td>${comment.commenter.login}</td>
              <td>${comment.comment}</td>
              <td>${comment.rating.value}</td>
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
        <form:label cssClass="control-label" path="rating">Rating:</form:label>
        <div class="controls">
          <form:select path="rating" items="${ratings}" itemLabel="value"/>
        </div>
      </div>
      <div class="control-group">
        <form:label cssClass="control-label" path="privateComment">Private?</form:label>
        <div class="controls">
          <form:checkbox path="privateComment"/>
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