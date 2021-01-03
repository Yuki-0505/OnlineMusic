$(function(){
	$("#index_login_div").delegate("p[class='login_yet_exit']", "click", function(){
		sessionStorage.clear();
		alert("已退出");
		window.location.href = "index.html";
	})
})