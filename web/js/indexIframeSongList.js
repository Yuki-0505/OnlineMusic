$(function(){
	var userId = sessionStorage.getItem("userId");
	var songListId = sessionStorage.getItem("indexIframeSongListId");
	mySongList_div_click(songListId);
	
	//点击发表评论
	$("#comment_submit").click(function(){
		if(userId == null || userId == ""){
			alert("请先登录！");
		}else{
			var commContentText = $("#songlist_comment_textarea").val().trim().replace(/\s/g,"");
			if (commContentText == null || commContentText == "") {
				alert("评论不能为空！");
			} else{
				$.ajax({
					type:"get",
					url:serverURL + "/AddSongListComment",
					async:false,//同步
					data:{"userId":userId, "songListId":songListId, "songListCommText":commContentText},
					dataType:'text',//返回数据类型
					success:function(data){
						if (data == "300") {
							alert("添加失败");
						} else{
							$("#songlist_comment_textarea").val("");
							var userSongListCommArr = eval("(" + data + ")");
							showSongListComment(userSongListCommArr);
						}
					},//响应成功后执行代码
					error:function(){
						console.log("发表评论失败");
					}
				});
			}
		}
		
	})
})