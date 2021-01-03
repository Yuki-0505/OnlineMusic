function tianChongSearchSongList(userId, searchSongList){
	$("#searchSong_table").empty();//清空歌曲填充表
	$("div[class='pageNum_div']").empty();//清空页码div
	//填充搜索类型
	$("#searchTypeName").html("歌单");
	if (searchSongList !="false") {
		var searchSongListArr = eval("(" + searchSongList + ")");
		//填充搜索的关键字
		var searchName = searchSongListArr[0].searchSongListName;
		$("#searchSongName").html(searchName);
		
		//填充搜索到的行数
		var allSongListRowsNum = searchSongListArr[0].allSongListRowsNum;
		$("#songRowNum").html(allSongListRowsNum);
		
		
		//总页数
		var allPageNums = searchSongListArr[0].PageNum;
		
		//当前页
		var currentPage = searchSongListArr[0].startPage;
		
		
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
						searchSongListLimit(searchName, currentPage - 1);
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
							searchSongListLimit(searchName, $(this).attr("theSpanPageNum"));
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
						searchSongListLimit(searchName, currentPage + 1);
					}
				}).appendTo("div[class='pageNum_div']").html("下一页>");
			}
		
		}
		var headerTr = $("<tr>",{
			
		}).appendTo("#searchSong_table");
		
		//播放列
		var headerPlayTd = $("<td>",{
			
		}).appendTo(headerTr);
		
		//歌单名列
		var headerSongListNameTd = $("<td>",{
			
		}).appendTo(headerTr).html("歌单名");
		
		//操作列
		var headerSongListSetTd = $("<td>",{
			
		}).appendTo(headerTr).html("操作");
		
		//创建者列
		var headerCreateUserNameTd = $("<td>",{
			
		}).appendTo(headerTr).html("创建者");
		
		//收藏次数列
		var headerCollectionCountTd = $("<td>",{
			
		}).appendTo(headerTr).html("收藏次数");
		
		//热度列
		var headerAccessCountTd = $("<td>",{
			
		}).appendTo(headerTr).html("热度");
		
		//填充歌单
		$.each(searchSongListArr, function(index, oneSearchSongList){
			var songListId = oneSearchSongList.songListInfo.songListId;//歌单编号
			var songListName = oneSearchSongList.songListInfo.songListName;//歌单名
			var createTheSongListUserId = oneSearchSongList.songListInfo.userId;//创建者Id
			var createTheSongListUserName = oneSearchSongList.createTheSongListUserName;//创建者名称
			var collectionCount = oneSearchSongList.songListInfo.collectionCount;//收藏次数
			var accessCount = oneSearchSongList.songListInfo.accessCount;//热度
			var searchSongList_Tr = $("<tr>", {
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
			
			var searchSongListPlay_td = $("<td>",{
				
			}).appendTo(searchSongList_Tr);
			
			var play_div = $("<div>",{
				class:'play_div',
				songListId:songListId,
				click:function(){
					//点击播放/暂停播放音乐
					
				}
			}).appendTo(searchSongListPlay_td);
			
			//填充歌单名
			var searchSongListName_td = $("<td>",{
				title:songListName,
				songListId:songListId,
				mouseover:function(){
					$(this).css({"cursor":"pointer", "text-decoration":"underline"});
				},
				mouseout:function(){
					$(this).css({"text-decoration":"none"});
				},
				click:function(){
					sessionStorage.setItem("indexIframeSongListId", $(this).attr("songlistId"));
					window.location.href = "indexIframeSongList.html";
				}
			}).appendTo(searchSongList_Tr).html(songListName);
			
			//填充操作
			var searchSongListSet_td = $("<td>",{
				
			}).appendTo(searchSongList_Tr);
			
			var addSongList_img_div = $("<div>",{
				class:'addSongList_img_div',
				title:'收藏',
				songListId:songListId,
				createUserId:createTheSongListUserId,
				click:function(){
					if(userId != null){
						var createUserId = $(this).attr("createUserId");//创建者Id
						var songListId = $(this).attr("songListId");
						if(userId == createUserId){
							alert("不能收藏自己创建的歌单哦");
						}else{
							collectSongList(userId, songListId);
						}
					}else{
						//如果没有登录，提示先登录
						alert("请先登录");
					}
				}
			}).appendTo(searchSongListSet_td);
			
			//填充创建者名
			var searchSongListCreateUserName_td = $("<td>",{
				title:createTheSongListUserName
			}).appendTo(searchSongList_Tr).html(createTheSongListUserName);
			
			//填充收藏次数
			var searchCollectionCount_td = $("<td>",{
				
			}).appendTo(searchSongList_Tr).html(collectionCount);
			
			//填充热度
			var searchAccessCount_td = $("<td>",{
				
			}).appendTo(searchSongList_Tr).html(accessCount);
		});
	}else{
		var searchSongName = sessionStorage.getItem("searchSongName");
		//填充搜索的关键字
		$("#searchSongName").html(searchSongName);
		
		//填充搜索到的行数
		$("#songRowNum").html("0");
	}
}