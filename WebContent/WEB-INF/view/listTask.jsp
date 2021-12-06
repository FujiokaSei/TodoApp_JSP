<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListTask</title>
</head>
<body>
	<table border=1>
		<tr>
			<th>id</th>
			<th>title</th>
			<th>detail</th>
			<th>adding_time</th>
			<th>time_limit</th>
			<th>user_id</th>
			<th>task_type_id</th>
		</tr>
		<c:forEach var="task" items="${taskList}">
			<tr>
				<td><c:out value="${task.id }"/></td>
				<td><c:out value="${task.title }"/></td>
				<td><c:out value="${task.detail }"/></td>
				<td><c:out value="${task.addingTime }"/></td>
				<td><c:out value="${task.timeLimit }"/></td>
				<td><c:out value="${task.userId }"/></td>
				<td><c:out value="${task.taskTypeId }"/></td>
			</tr>
		</c:forEach>
	</table>
	<form action="" method="post">
		<input type="submit" value="送信" />
	</form>


</body>
</html>