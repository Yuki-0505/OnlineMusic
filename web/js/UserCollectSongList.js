//用户收藏歌单
function collectSongList(userId, songListId){
	$.ajax({
		type:"get",
		url:serverURL + "/AddUserCollectionSongList",
		data:{"userId":userId, "songListId":songListId},
		async:true,//异步
		dataType:'text',//返回数据类型
		success:function(data){
			switch (data){
				case "200":
					alert("添加成功");
					break;
				case "300":
					alert("添加失败");
					break;
				case "400":
					alert("添加成功，次数增加失败");
					break;
				case "500":
					alert("该歌单已经收藏");
				default:
					break;
			}
		},
		error:function(){
			alert("数据请求失败！");
		}
	})
}
