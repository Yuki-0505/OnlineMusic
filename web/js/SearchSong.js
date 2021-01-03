document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
document.write("<script type='text/javascript' src='js/SearchSongLimitAjax.js'></script>");
document.write("<script type='text/javascript' src='js/SearchSongListLimitAjax.js'></script>");
document.write("<script type='text/javascript' src='js/GetSongListOfUserId.js'></script>");
document.write("<script type='text/javascript' src='js/SearchSongAddSongToSongListDiv.js'></script>");
document.write("<script type='text/javascript' src='js/TianchongSearchSong.js'></script>");
document.write("<script type='text/javascript' src='js/TianchongSearchSongList.js'></script>");
document.write("<script type='text/javascript' src='js/UserCollectSongList.js'></script>");//歌单收藏
//增加播放次数和增加用户播放记录
document.write("<script type='text/javascript' src='js/UpSongListenCount.js'></script>");
$(function(){
	var userId = sessionStorage.getItem("userId");
	var searchSong = sessionStorage.getItem("searchSong");
	var searchSongName = sessionStorage.getItem("searchSongName");
	
	var search_type = sessionStorage.getItem("search_type");;
	if (search_type == "null" || search_type == "search_song") {
		$("#search_song").addClass("searchType_span_select");
		tianChongSearchSong(userId, searchSong);//填充查询到的歌曲
	}else if(search_type == "search_songList"){
		$("#search_songList").addClass("searchType_span_select");
		tianChongSearchSongList(userId, searchSong);
	}
	
	
	//设置table中tr奇数行背景颜色
	$("#searchSong_table tr:even").css("background","#F7F7F7");
	//用户添加歌曲到歌单，点击X号关闭窗口
	$("#none_addSongList_showDiv").click(function(){
		$("div[class='addSongList_songList']").attr("songid","");
		$("div[class='addSongList_showDiv']").css("display","none");
	})
	
	//点击搜索类别（单曲或者歌单）进行不同的搜索
	$(".search_type_span").click(function(){
		
		$("#search_song").removeClass("searchType_span_select");
		var thisId = $(this).prop("id");
		if(thisId == "search_song"){
			//点击搜索单曲
			sessionStorage.setItem("search_type","search_song");
			$("#search_song").addClass("searchType_span_select");
			$("#search_songList").removeClass("searchType_span_select");
			searchSongLimit(searchSongName, 1);
			searchSong = sessionStorage.getItem("searchSong");
			tianChongSearchSong(userId, searchSong);//填充查询到的歌曲
		}else if(thisId == "search_songList"){
			//点击搜索歌单
			sessionStorage.setItem("search_type","search_songList");
			$("#search_songList").addClass("searchType_span_select");
			$("#search_song").removeClass("searchType_span_select");
			searchSongListLimit(searchSongName, 1);
			searchSong = sessionStorage.getItem("searchSong");
			tianChongSearchSongList(userId, searchSong);
		}
		//设置table中tr奇数行背景颜色
		$("#searchSong_table tr:even").css("background","#F7F7F7");
	})
})
