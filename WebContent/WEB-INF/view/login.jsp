<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<title>Task管理</title>
</head>
<body id="loginPage">
	<header>
		<nav class="navbar navbar-dark bg-dark fixed-top">
			<div class="container-fluid">
				<div class="row justify-content-between">
					<table>
						<tr>
							<td><a class="navbar-brand" href="#">TodoApp</a></td>
						</tr>
					</table>
				</div>
			</div>
		</nav>
	</header>

	<div class="container">
		<div class="row">
			<div class="col-md-offset-3 col-md-6 mx-auto">
				<div class="panel panel-default">
					<div class="logo">
						<img src="images/logo.png" alt="logo" title="logo"/>
					</div>

					<c:if test="${not empty message}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${message}" />
						</div>
					</c:if>

					<div class="panel-body">
						<c:if test="${not empty error}">
							<div class="alert alert-danger">ログインIDかパスワードが正しくありません。</div>
						</c:if>
						<form action="" method="post">
							<div class="form-group">
								<c:if test="${not empty loginIdError}">
									<div class="error-message">
										<c:out value="${loginIdError}" />
									</div>
								</c:if>
								<input type="text" name="loginId" placeholder="ログインID"
									class="form-control" value="<c:out value="${loginId}" />" />
							</div>
							<div class="form-group">
								<c:if test="${not empty loginPassError}">
									<div class="error-message">
										<c:out value="${loginPassError}" />
									</div>
								</c:if>
								<input type="password" name="loginPass" placeholder="パスワード"
									class="form-control" />
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-primary btn-block"
									value="ログイン" />
							</div>
						</form>

						<div class="guestInfo">
							<p class="guestUser">ゲストユーザー</p>
							<p>
								ログインID：guest<br /> パスワード：guest
							</p>
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
