function searchSongLimit(songName, startPage){
	$.ajax({
		type:"get",
		url:serverURL + "/SearchSong",
		data:{"searchSongName":songName, "startPage":startPage},
		async:false,//异步
		dataType:'text',//返回数据类型
		success:function(data){
			sessionStorage.setItem("searchSong", data);
			var searchSongArr = eval("(" + data + ")");
//			var currentPage = searchSongArr[0].startPage;
			window.location.href = "SearchSong.html";
		},
		error:function(){
			alert("数据请求失败！");
		}
	})
}
