//根据歌曲编查询歌曲详情
function getSongInfoOfSongId(songId){
	var songInfoOfSongIdJSON;
	$.ajax({
		type:"get",
		url:serverURL + "/SelectSongInfoOfSongId",
		async:false,//同步
		data:{
			songId:songId
		},
		dataType:'json',//返回数据类型
		success:function(data){
			songInfoOfSongIdJSON = data;
		},//响应成功后执行代码
		error:function(){
			console.log("无法连接到服务器");
		}
	});
	return songInfoOfSongIdJSON;
}
