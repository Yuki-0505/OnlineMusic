function searchSongListLimit(songListName, startPage){
	$.ajax({
		type:"get",
		url:serverURL + "/SearchSongList",
		data:{"searchSongListName":songListName, "startPage":startPage},
		async:false,//异步
		dataType:'text',//返回数据类型
		success:function(data){
			sessionStorage.setItem("searchSong", data);
			window.location.href  = "SearchSong.html";
		},
		error:function(){
			alert("数据请求失败！");
		}
	})
}