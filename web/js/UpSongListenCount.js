//根据歌曲编号查询歌曲信息
document.write("<script type='text/javascript' src='js/getSongInfoOfSongId.js'></script>");
//增加用户听歌记录
function upSongListenCount(userId, songId){
	$.ajax({
		type:"get",
		url:serverURL + "/AddUserListenSong",
		async:false,//同步
		data:{"userId":userId, "songId":songId},
		dataType:'text',//返回数据类型
		success:function(data){
			if (data == "false") {
				alert("出现未知错误")	;
			}else{
				var songInfoOfSongIdJSON = getSongInfoOfSongId(songId);
				var songUrl = serverURL + songInfoOfSongIdJSON.songInfo.songUrl;//歌曲链接地址
				var songName = songInfoOfSongIdJSON.songInfo.songName;//歌曲名
				$("#userPlaySong", parent.document).attr("src", songUrl);
				$("#songPlay_name", parent.document).html(songName);
			}
		},//响应成功后执行代码
		error:function(){
			console.log("无法连接到服务器");
		}
	});
	
}
