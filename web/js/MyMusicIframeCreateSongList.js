//点击创建按钮，显示创建界面
document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
document.write("<script type='text/javascript' src='js/RefreshUserCreateSongList.js'></script>");
$(function(){
	
//	new_element = document.createElement("script");
//	new_element.setAttribute("type","text/javascript");
//	new_element.setAttribute("src", "js/MyMusicIframe.js");
//	document.body.appendChild(new_element);
	//获取登陆人的用户Id
	var userId = sessionStorage.getItem("userId");
	$("#createSongList_a").click(function(){
		$("div[class='createSongList_div']").css("display","block");
		$("#songListName").val("");
	})
	$("#none_createSongList").click(function(){
		$("div[class='createSongList_div']").css("display","none");
	})
	$("#quxiaoCreateButton").click(function(){
		$("div[class='createSongList_div']").css("display","none");
	})
//	$("#createSongListButton").mouseover(function(){//鼠标移入
//		$("div[name='list_title']").removeAttr("onclick");
//	});
	$("#createSongListButton").click(function(){
		var songListNameStr = $("#songListName").val();
		var songListName = songListNameStr.replace(/\s+/g," ");
		if (songListName == "") {
			$("#nullJG").css("display","block");
			$("#nullJG").html("歌单名不能为空");
		} else{
			//歌单名不为空，判断歌单名自己是否已经创建
			//使用Ajax判断
			$.ajax({
				type:"get",
				url:serverURL + "/AddSongList?userId="+userId+"&songListName="+songListName,
				async:false,
				data:"",
				dataType:"text",
				error:function(){
					alert("未知错误，创建失败!");
				},
				success:function(data){
					if (data == "true") {
						getSongListInfo(userId);
//						window.location.href = "MyMusicIframe.html";
						songListDivShow(userId);//调用刷新列表的方法
						$("div[class='createSongList_div']").css("display","none");
					} else{
						$("#nullJG").html("该歌单已经存在");
					}
					
				}
			});
		}
	})
})
