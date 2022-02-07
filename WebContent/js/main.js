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
		} else {
		}
	});
});