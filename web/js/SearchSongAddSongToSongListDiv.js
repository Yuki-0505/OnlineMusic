document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
//填充添加歌曲到歌单的界面
function setSongListOfUserId(allSongListJSONOfUserId){
	var allSongListJSONOfUserIdArr = eval("(" + allSongListJSONOfUserId + ")");
	$.each(allSongListJSONOfUserIdArr, function(index, userCreateSongList) {
		var songListId = userCreateSongList.songListId;
		var songListName = userCreateSongList.songListName;
		var addSongList_p = $("<p>",{
			id:"addSongList_p",
			class:"addSongList_p",
			songListId:songListId,
			click:function(){
				var songListId = $(this).attr("songListId");//歌单编号
				var songId = $(this).parent("div[class='addSongList_songList']").attr("songid");//歌曲编号
		//		alert("歌单编号：" + $(this).attr("songListId") + "；歌曲编号：" + songId);
				$.ajax({
					type:"post",
					url:serverURL + "/AddSongListWithSong",
					data:{"songListId":songListId, "songId":songId},
					dataType:'text',//返回数据类型
					async:true,
					success:function(data){
						$("div[class='addSongList_songList']").attr("songid","");
						$("div[class='addSongList_showDiv']").css("display","none");
						switch (data){
							case "200":
								break;
							case "300":
								alert("添加失败，稍后再试！");
								break;
							case "100":
								alert("该歌曲已存在！");
								break;
							default:
								break;
						}
					},
					error:function(){
						alert("呀！出错了！");
					}
				});
			}
		}).appendTo("div[class='addSongList_songList']");
		$(addSongList_p).html(songListName);
	});
	
}
