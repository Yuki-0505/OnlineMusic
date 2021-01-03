//获取用户创建的歌单
function getSongListOfUserId(userId){
	var allSongListJSONOfUserId;
	$.ajax({
		"url":serverURL + "/SelectUserCreateSongList",
		"type":"POST",
		"data":{"userId":userId},
		"async" : false,
		"dataType":"text",
		"success":function(data){
			if(data != "null"){
				allSongListJSONOfUserId = data;
//				sessionStorage.setItem("allSongListJSONOfUserId",data);
			}else{
				allSongListJSONOfUserId = null;
			}
		},
		"error":function(err){
			alert("出现异常");
			allSongListJSONOfUserId = "error";
		}
	})
	return allSongListJSONOfUserId;
}
