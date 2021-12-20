<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="css/ress.css" rel="stylesheet" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<title>addTask</title>
</head>

<body>
	<header>
		<nav class="navbar navbar-dark bg-dark fixed-top">

			<div class="container-fluid">
				<div class="row justify-content-between">
					<table>
						<tr>
							<td><a class="navbar-brand" href="#">TodoApp</a></td>
							<td><a href="logout" class="btn btn-danger">ログアウト</a></td>
						</tr>

					</table>
				</div>

			</div>



		</nav>
	</header>

	<%--編集ボタン押下後、c:chooseが機能しているかどうかのテスト 	<c:choose>
		<c:when test="${!empty mode}">
			<p>editMode</p>
		</c:when>
		<c:otherwise>
			<p>test</p>
		</c:otherwise>

	</c:choose> --%>


	<div class="container-fluid">
		<div class="row">

			<div class="col-lg-6 col-xl-4" id="addTask">
				<h1>タスクの追加</h1>
				<!-- <div class="container"> -->

				<!-- 				<h1>task登録</h1> -->
				<div class="row">
					<div class="col-md-12 col-xl-12">
						<form action="addTask" method="post">

							<!--タイトル text-->
							<div class="form-group">
								<label for="title">タスク名</label>
								<c:if test="${not empty titleError}">
									<!-- <div class="error-message"> -->
									<div class="alert alert-danger" role="alert">
										<c:out value="${titleError}" />
									</div>
								</c:if>
								<input type="text" name="title" id="formTitle"
									class="form-control" value="<c:out value="${title}" />" />

								<%--編集ボタン押下後、フォームに文字列を入れる。
																<input type="text" name="title" id="formTitle"
									class="form-control"
									value="<c:choose>
										<c:when test="${!empty mode}"><c:out value="${editTask.title}" /></c:when>
										<c:otherwise>	<c:out value="${title}"/></c:otherwise>
										</c:choose>" /> --%>



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
							<div class="form-inline addClear">
								<div class="form-group">
									<label for="formType" style="margin: 10px 10px 10px 0px">優先度</label>
									<c:if test="${not empty priorityError}">
										<div class="alert alert-danger" role="alert">
											<c:out value="${priorityError }" />
										</div>
									</c:if>
									<select name="priorityId" id="formType" class="form-control">
										<c:forEach items="${priorityList}" var="priority">
											<option value="<c:out value="${priority.id}" />"
												<c:if test="${priority.id == priorityId}">selected</c:if>>
												<c:out value="${priority.name}" />
											</option>
										</c:forEach>
									</select>
								</div>

								<!-- 期限 calendar -->
								<div class="form-group">
									<label for="forTimeLimit" style="margin: 10px 10px">期限</label>
									<c:if test="${not empty timeLimitError}">
										<div class="alert alert-danger" role="alert">
											<c:out value="${timeLimitError}" />
										</div>
									</c:if>
									<%-- <input type="datetime-local" name="timeLimit" id="formTimeLimit"
									value="<c:out value="${now}"/>" min="<c:out value="${now}"/>"
									class="form-control" /> --%>
									<input type="datetime-local" name="timeLimit"
										value="<c:out value="${now}"/>" id="formTimeLimit"
										min="<c:out value="${now}"/>" class="form-control" required />
								</div>
							</div>



							<div class="form-group" style="margin-top: 10px;">
								<!-- <div class="text-left"> -->
								<input type="submit" class="btn btn-primary" value="追加" /> <input
									type="button" id="clear" class="btn btn-secondary" value="クリア">
								<!-- </div> -->
							</div>

						</form>

					</div>
				</div>
				<!-- </div> -->

			</div>
			<div class="col-lg-6 col-xl-5" id="taskList">
				<h1>タスク一覧</h1>
				<!-- <div class="card bg-light mb-3" style="max-width: 18rem;"> -->

				<c:forEach items="${taskList}" var="task">
					<div class="card bg-light mb-3">
						<div class="card-header">
							<div class="row">
								<div class="col-9" id="title">
									<b><c:out value="${task.title}" /></b>
								</div>

								<div class="col-3" style="padding-right: 0px;">
									<table>
										<tr>
											<td id="edit">
												<form action="/editTask" method="get" id="editButton">
													<a href="editTask?id=<c:out value="${task.id}" />"
														class="btn btn-success btn-sm" id="editDelete">編集</a>
												</form>
											</td>
											<td>
												<form action="/completeTask" method="get">
													<a href="completeTask?id=<c:out value="${task.id}" />"
														class="btn btn-danger btn-sm">完了</a>
												</form>
											</td>
										</tr>
									</table>






								</div>
							</div>
						</div>
						<div class="card-body">
							<!-- <h5 class="card-title">Light card title</h5> -->
							<div class="row" id="timeLimit">

								<p>
									期限：
									<fmt:formatDate value="${task.timeLimit}"
										pattern="y年M月d日 HH時mm分" />
								</p>

							</div>

							<div class="row">

								<div class="col-10">
									<p class="card-text" id="detail">
										<c:out value="${task.detail}" />
									</p>
								</div>
								<div class="col-2">

									<div class="form-group"></div>

								</div>
							</div>

						</div>
						<p></p>
					</div>
				</c:forEach>


			</div>


			<div class="col-lg-6 col-xl-3" id="statistics">
				<h1>統計</h1>
				<div class="">

					<table class="table table-borderd">
						<tr>
							<th>全タスク</th>
							<td><c:out value="${allCount}" />件</td>
						</tr>
						<tr>
							<th>残タスク</th>
							<td><c:out value="${notDoneCount}" />件</td>
						</tr>
						<tr>
							<th>完了済みタスク</th>
							<td><c:out value="${doneCount}" />件</td>
						</tr>
						<tr>
							<th>完遂率</th>
							<td><c:out value="${completingRate}" />%</td>
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
			$('#clear').click(function() {
				$('#formTitle').val("");
				$('#formDetail').val("");
			});

		});
	</script>

</body>
</html>
