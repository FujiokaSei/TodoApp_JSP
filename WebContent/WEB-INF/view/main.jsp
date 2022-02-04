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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" />
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
							<td><a href="login" class="btn btn-danger">ログアウト</a></td>
						</tr>

					</table>
				</div>

			</div>



		</nav>
	</header>


	<div class="container-fluid">
		<div class="row">

			<div class="col-lg-6 col-xl-4" id="addTask">
				<h1 class="border-bottom addTaskTitle" style="padding: 10px;">
					<i class="bi bi-pencil-square"></i> タスク作成
				</h1>

				<div class="row">
					<div class="col-md-12 col-xl-12">
						<form action="addTask" method="post" class="addTaskButton">
							<input type="hidden" name="id" value="" class="formTaskId" />

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
									class="form-control"
									value="<c:out value="${editTask.title}" default="" />" />
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
										value="${editTask.detail}" default="" /></textarea>
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

									<select name="priorityId" id="formType"
										class="form-control priorityForm" required>

										<c:forEach items="${priorityList}" var="priority">
											<option value="<c:out value="${priority.id}" />"
												<c:if test="${priority.id == editTask.priorityId}">selected</c:if>>
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
										value="<c:out value="${timeLimit}"/>" id="formTimeLimit"
										min="<c:out value="${now}"/>" class="form-control" required />
								</div>
							</div>



							<div class="form-group" style="margin-top: 10px;">
								<!-- <div class="text-left"> -->
								<input type="submit" class="btn btn-primary addButton"
									value="追加" /> <a href="main"
									class="clearButton btn btn-secondary">クリア</a>


								<!-- </div> -->
							</div>

						</form>

					</div>
				</div>
				<!-- </div> -->

			</div>
			<div class="col-lg-6 col-xl-5" id="taskList">
				<h1 class="border-bottom" style="padding: 10px;">
					<i class="bi bi-card-list"></i> タスク一覧
				</h1>
				<!-- <div class="card bg-light mb-3" style="max-width: 18rem;"> -->

				<c:forEach items="${taskList}" var="task" varStatus="count">
					<div class="card bg-light mb-3">
						<div class="card-header headerPriority${task.priorityId}">

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
						<div class="card-body bodyPriority${task.priorityId}">
							<!-- <h5 class="card-title">Light card title</h5> -->
							<div class="row" id="timeLimit">
								<p class="" id="${count.index}">
									<b>期限：</b>
									<fmt:formatDate value="${task.timeLimit}"
										pattern="y年M月d日 HH時mm分" />
									<b>優先度：</b>
									<c:forEach items="${priorityList}" var="priority">
										<c:if test="${priority.id==task.priorityId}">
											<!-- <div class="" id="priority"> -->
											<c:out value="${priority.name}" />
											<!-- </div> -->
										</c:if>
									</c:forEach>
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
					</div>
				</c:forEach>
			</div>


			<div class="col-lg-6 col-xl-3" id="statistics">
				<h1 class="border-bottom" style="padding: 10px;">
					<i class="bi bi-graph-up "></i> データ
				</h1>
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

				<form action="/main" method="get">
					<ul class="list-group list-group-horizontal">
						<li class="list-group-item orderButton ">
							<button type="button" class="btn btn-outline-secondary w-50"
								onclick="location.href='main?order=1'">期限順</button>
						</li>


						<li class="list-group-item orderButton ">
							<button type="button" class="btn btn-outline-secondary w-50"
								onclick="location.href='main?order=2'">優先度順</button>
						</li>
					</ul>

				</form>
			</div>
		</div>
	</div>


	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>

	<script>
		$(function() {
			//crearボタン押下時に動作する
			$('#clear').click(function() {
				$('#formTitle').val("");
				$('#formDetail').val("");
			});

			//ページ更新・読み込み時に動作する
			$(document).ready(function() {
				//URLを読み取り、編集モードに切り替える
				if (location.pathname == "/TaskBoard/editTask") {
					$(".addButton").attr("value", "更新");
					$(".clearButton").attr("value", "編集をやめる");
					$(".clearButton").text("編集をやめる");
					$(".addTaskButton").attr("action", "editTask");
					$(".addTaskButton").attr("method", "post");

					const FormTaskId = $(location).attr('search').substr(4, 8);
					$(".formTaskId").attr("value", FormTaskId);

					//action="addTask" method="post"
				} else {
				}
			});

			/* //優先度の初期値を設定する
			const priorityId = $(location).attr('search').substr(4, 5);
			console.log(priorityId); */

			/* //優先度別の色分け
			//定数を宣言,低/中/高の文字列
			const lowText = "低";
			const middleText = "中";
			const highText = "高";

			//対象タスクの重要度を取得する
			let priorityText = $('#priority').text().trim();

			//console.log('#timeLimit'+' #'+3);

			//for文を回す
			while(){

				let $('#timeLimit'+' #'+i').attr(priority,);
			}




			console.log('#timeLimit'+' #'+ 3);

			//if文(低/中/高で場合分け)
			switch (priorityText) {
			case lowText:
				console.log("lowです");
				break;

			case middleText:
				console.log("middleです");
				break;

			case highText:
				console.log("highです");
				break;
			}

			//クラスを追加する
			$("#priority").addClass("text-danger");

			console.log(priorityText);

			//
			 */
		});
	</script>

</body>
</html>
