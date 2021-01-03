function tianChongSearchSong(userId, searchSong){
	$("#searchSong_table").empty();//清空歌曲填充表
	$("div[class='pageNum_div']").empty();//清空页码div
	//填充搜索类型
	$("#searchTypeName").html("单曲");
	if (searchSong !="false") {
		var searchSongArr = eval("(" + searchSong + ")");
		//填充搜索的关键字
		var searchName = searchSongArr[0].searchSongName;
		$("#searchSongName").html(searchName);
		
		//填充搜索到的行数
		var allSongRowsNum = searchSongArr[0].allSongRowsNum;
		$("#songRowNum").html(allSongRowsNum);
		
		//总页数
		var allPageNums = searchSongArr[0].PageNum;
		
		//当前页
		var currentPage = searchSongArr[0].startPage;
		
		
		if(allPageNums > 0){
			//页码数大于0
			//显示页数
	//		<div class="pageNum_div">
	//			<span class="pageNum_span"></span>
	//		</div>
			//上一页span
			if(currentPage == 1){
				var prevPage_span = $("<span>",{
					class:'prevPage_span currentPageIsOneOrLast',
					click:function(){
						//不跳转
					}
				}).prependTo("div[class='pageNum_div']").html("<上一页");
			}else{
				var prevPage_span = $("<span>",{
					class:'prevPage_span',
					click:function(){
						//传入参数，点击的哪一页（当前页-1）,在js/SongListOfAccessCountLimit.js中定义
						searchSongLimit(searchName, currentPage - 1);
					}
				}).prependTo("div[class='pageNum_div']").html("<上一页");
			}
			
			
			
			//页码span
			for(var j = 1; j <= allPageNums; j++){
				if(j == currentPage){//判断当前页
					var pageNum_span = $("<span>",{
						class:'pageNum_span pageNumIsCurrentPage',
						theSpanPageNum:j,
						click:function(){
							//不跳转
						}
					}).appendTo("div[class='pageNum_div']").html(j);
				}else{
					var pageNum_span = $("<span>",{
						class:'pageNum_span',
						theSpanPageNum:j,
						click:function(){
							//传入参数，点击的哪一页,在js/SongListOfAccessCountLimit.js中定义
							searchSongLimit(searchName, $(this).attr("theSpanPageNum"));
						}
					}).appendTo("div[class='pageNum_div']").html(j);
				}
			}
			//下一页span
			if(currentPage == allPageNums){
				var nextPage_span = $("<span>",{
					class:'nextPage_span currentPageIsOneOrLast',
					click:function(){
						//不跳转
					}
				}).appendTo("div[class='pageNum_div']").html("下一页>");
			}else{
				var nextPage_span = $("<span>",{
					class:'nextPage_span',
					click:function(){
						//传入参数，点击的哪一页（当前页+1）,在js/SongListOfAccessCountLimit.js中定义
						searchSongLimit(searchName, currentPage + 1);
					}
				}).appendTo("div[class='pageNum_div']").html("下一页>");
			}
		
		}
		var headerTr = $("<tr>",{
			
		}).appendTo("#searchSong_table");
		
		//播放列
		var headerPlayTd = $("<td>",{
			
		}).appendTo(headerTr);
		
		//歌曲名列
		var headerSongNameTd = $("<td>",{
			
		}).appendTo(headerTr).html("歌曲名");
		
		//操作列
		var headerSongSetTd = $("<td>",{
			
		}).appendTo(headerTr).html("操作");
		
		//歌手列
		var headerSingerNameTd = $("<td>",{
			
		}).appendTo(headerTr).html("歌手");
		
		//专辑列
		var headerCdNameTd = $("<td>",{
			
		}).appendTo(headerTr).html("专辑");
		
		//时长列
		var headerSongTimeTd = $("<td>",{
			
		}).appendTo(headerTr).html("时长");
		
		//填充歌曲
		$.each(searchSongArr, function(index, oneSearchSong){
			var songName = oneSearchSong.songInfo.songName;//歌曲名
			var songId = oneSearchSong.songInfo.songId;//歌曲Id
			var singerName = oneSearchSong.songInfo.singerName;//歌手名
			var cdName = oneSearchSong.songInfo.CDName;//专辑名
			var songTime = oneSearchSong.songInfo.songTime;//时长
			var searchSong_Tr = $("<tr>", {
				mouseover:function(){
					//鼠标移入，添加到歌单按钮显示
					$(this).find("div[class='addSongList_img_div']").css("display","block");
					$(this).addClass("mouseoverTr");
				},
				mouseout:function(){
					//鼠标移出，添加到歌单按钮消失
					$(this).find("div[class='addSongList_img_div']").css("display","none");
					$(this).removeClass("mouseoverTr");
				}
			}).appendTo("#searchSong_table");
			
			var searchSongPlay_td = $("<td>",{
				
			}).appendTo(searchSong_Tr);
			
			var play_div = $("<div>",{
				class:'play_div',
				songId:songId,
				click:function(){
					upSongListenCount(userId, songId);
				}
			}).appendTo(searchSongPlay_td);
			
			//填充歌曲名
			var searchSongName_td = $("<td>",{
				title:songName
			}).appendTo(searchSong_Tr).html(songName);
			
			//填充操作
			var searchSongSet_td = $("<td>",{
				
			}).appendTo(searchSong_Tr);
			
			var addSongList_img_div = $("<div>",{
				class:'addSongList_img_div',
				title:'收藏',
				songId:songId,
				click:function(){
					if(userId != null){
						var allSongListJSONOfUserId = getSongListOfUserId(userId);
						if(allSongListJSONOfUserId == null || allSongListJSONOfUserId == "null"){
							alert("你还没有创建歌单，先去创建吧")
						}else{
							var thisSongId = $(this).attr("songId");
							$("div[class='addSongList_showDiv']").css("display","block");
							$("div[class='addSongList_songList']").empty();
							$("div[class='addSongList_songList']").attr("songid",$(this).attr("songId"));
							setSongListOfUserId(allSongListJSONOfUserId);
						}
						
					}else{
						//如果没有登录，提示先登录
						alert("请先登录");
					}
				}
			}).appendTo(searchSongSet_td);
			
			//填充歌手名
			var searchSongSingerName_td = $("<td>",{
				title:singerName
			}).appendTo(searchSong_Tr).html(singerName);
			
			//填充专辑名
			var searchSongCdName_td = $("<td>",{
				title:cdName
			}).appendTo(searchSong_Tr).html(cdName);
			
			//填充时长
			var searchSongSongTime_td = $("<td>",{
				
			}).appendTo(searchSong_Tr).html(songTime);
		});
	}else{
		var searchSongName = sessionStorage.getItem("searchSongName");
		//填充搜索的关键字
		$("#searchSongName").html(searchSongName);
		
		//填充搜索到的行数
		$("#songRowNum").html("0");
	}
}
