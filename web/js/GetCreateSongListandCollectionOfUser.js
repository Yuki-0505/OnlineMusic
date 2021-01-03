//获取用户的歌单（用户自己创建的和收藏的）

function getCreateSongListAndCollectionOfUserId(userId){
	var userAllSongListJSON;
	$.ajax({
		type:"get",
		url:serverURL + "/SelectUserSongListOfUserId",
		async:false,//同步
		data:{"userId":userId},
		dataType:'json',//返回数据类型
		success:function(data){
			userAllSongListJSON = data;
		},//响应成功后执行代码
		error:function(){
			console.log("歌单信息获取失败");
		}
	});
	return userAllSongListJSON;
}
