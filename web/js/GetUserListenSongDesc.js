//获取用户听歌记录排行榜歌曲信息
function getUserListenSongDesc(userId){
	var userListenSongDescJSON;
	$.ajax({
		type:"get",
		url:serverURL + "/SelectUserListenSongOrderByListenCountDesc",
		async:false,//同步
		data:{"userId":userId},
		dataType:'json',//返回数据类型
		success:function(data){
			userListenSongDescJSON = data;
		},//响应成功后执行代码
		error:function(){
			console.log("用户信息获取失败");
		}
	});
	return userListenSongDescJSON;
}
