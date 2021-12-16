<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<title>addTask</title>
</head>

<body>


	<header>
		<nav class="navbar navbar-dark bg-dark fixed-top">

			<div class="container-fluid">
				<a class="navbar-brand" href="#">TodoApp</a>
			</div>
		</nav>
	</header>


	<div class="container-fluid">
		<div class="row">

			<div class="col-lg-6 col-xl-4" id="addTask">
				<p>addTask</p>
				<!-- <div class="container"> -->

				<h1>task登録</h1>
				<div class="row">
					<div class="col-md-12 col-xl-12">
						<form action="addTask" method="post">

							<!--タイトル text-->
							<div class="form-group">
								<label for="title">title</label>
								<c:if test="${not empty titleError}">
									<!-- <div class="error-message"> -->
									<div class="alert alert-danger" role="alert">

										<c:out value="${titleError}" />
									</div>
								</c:if>
								<input type="text" name="title" id="formTitle"
									class="form-control" value="<c:out value="${title}" />" />
							</div>

							<!-- 説明 textarea -->
							<div class="form-group">
								<label for="formDetail">説明</label>
								<c:if test="${not empty detailError }">
									<div class="alert alert-danger" role="alert">
										<c:out value="${detailError }" />
									</div>
								</c:if>
								<textarea name="detail" id="formDetail" class="form-control"><c:out
										value="${detail}" /></textarea>
							</div>

							<!-- 種別 type -->
							<div class="form-group">
								<label for="formType">種別</label>
								<c:if test="${not empty taskTypeError}">
									<div class="alert alert-danger" role="alert">
										<c:out value="${taskTypeError }" />
									</div>
								</c:if>
								<select name="taskTypeId" id="formType" class="form-control">
									<c:forEach items="${taskTypeList}" var="taskType">
										<option value="<c:out value="${taskType.id}" />"
											<c:if test="${taskType.id == taskTypeId}">selected</c:if>>
											<c:out value="${taskType.name}" />
										</option>
									</c:forEach>
								</select>
							</div>

							<!-- 期限 calendar -->
							<div class="form-group">
								<label for="forTimeLimit">期限</label>
								<c:if test="${not empty timeLimitError}">
									<div class="alert alert-danger" role="alert">
										<c:out value="${timeLimitError}" />
									</div>
								</c:if>
								<input type="datetime-local" name="timeLimit" id="formTimeLimit"
									value="<c:out value="${now}"/>" min="<c:out value="${now}"/>"
									class="form-control" />
							</div>




							<div class="form-group">
								<input type="submit" class="btn btn-primary" value="追加" /> <a
									href="listItem" class="btn btn-default">キャンセル</a>
							</div>

						</form>

					</div>
				</div>
				<!-- </div> -->

			</div>
			<div class="col-lg-6 col-xl-5" id="taskList">
				<p>taskList</p>
				<!-- <div class="card bg-light mb-3" style="max-width: 18rem;"> -->
				<div class="card bg-light mb-3">

					<c:forEach items="${taskList}" var="task">

						<div class="card-header">
							<c:out value="${task.title}" />
						</div>
						<div class="card-body">
							<!-- <h5 class="card-title">Light card title</h5> -->

							<form action="/deleteTask" method="get">
								<a href="deleteTask?id=<c:out value="${task.id}" />" class="btn btn-danger btn-sm">削除</a>
							</form>
							<p class="card-text"></p>
						</div>
					</c:forEach>
				</div>


			</div>


			<div class="col-lg-6 col-xl-3" id="statistics">
				<p>statistics</p>
				<div class="">

					<table class="table table-borderd">
						<tr>
							<th>残タスク</th>
							<td>{数字}</td>
						</tr>
						<th>完了済みタスク</th>
						<td>{数字}</td>
						<tr>
							<th>完遂率</th>
							<td>{数字}</td>
						</tr>

					</table>

				</div>
			</div>

		</div>
	</div>


	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>

	<script>
		$(function() {
		});
	</script>

</body>
</html>
