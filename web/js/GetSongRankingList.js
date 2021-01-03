//获取歌曲排行榜

//获取播放次数排行榜

function getSongPlayCountList(){
	
	var songPlayCountListJSON;
	$.ajax({
		type:"get",
		url:serverURL + "/SongPlayCount",
		async:false,//同步
		dataType:'json',//返回数据类型
		success:function(data){
//			alert("播放次数排行榜：" + data)
			songPlayCountListJSON = data;
		},//响应成功后执行代码
		error:function(){
			console.log("无法连接服务器");
		}
	});
	return songPlayCountListJSON;
}

//获取下载次数排行榜
function getSongDownloadCountList(){
	var songDownloadCountListJSON;
	$.ajax({
		type:"get",
		url:serverURL + "/SongDownloadCount",
		async:false,//同步
		dataType:'json',//返回数据类型
		success:function(data){
//			alert("下载次数排行榜：" + data )
			songDownloadCountListJSON = data;
		},//响应成功后执行代码
		error:function(){
			console.log("无法连接服务器");
		}
	});
	return songDownloadCountListJSON;
}

//获取收藏次数排行榜
function getCollectionCountList(){
	var songCollectionCountListJSON;
	$.ajax({
		type:"get",
		url:serverURL + "/SongCollectionCount",
		async:false,//同步
		dataType:'json',//返回数据类型
		success:function(data){
//			alert("收藏次数排行榜：" + data)
			songCollectionCountListJSON = data;
		},//响应成功后执行代码
		error:function(){
			console.log("无法连接服务器");
		}
	});
	return songCollectionCountListJSON;
}



//歌曲推荐，填充函数
function tianCSongList(songListJSON, tableId, songCountType, songNum){//推荐歌曲JSON数据，填充的table id
	var userId = sessionStorage.getItem("userId");
	var songListJSONLen = songListJSON.length;
	if (songNum == 10) {
		//只循环10条数据，用于显示在首页推荐位置。
		if(songListJSONLen > 10){
			//当数据大于10条时，只显示10条
			for(var i = 0; i < 10; i++){
				var songId = songListJSON[i].song.songId;//歌曲编号
				var songName = songListJSON[i].song.songName;//歌曲名字
				var songUrl = songListJSON[i].song.songUrl;//歌曲地址
				var song_tr = $("<tr>",{
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
				}).appendTo(tableId);
				
				var num_td = $("<td>",{
						
				}).appendTo(song_tr).html(i+1);
				if(i <= 4){
					$(num_td).css("color","#C82F2E");
				}
				//歌曲播放div
				var play_td = $("<td>",{
					
				}).appendTo(song_tr);
				
				//播放按钮
				var play_span_btn = $("<span>",{
					class:'playSongBtn',
					id:"playSongBtn",
					title:'播放',
					songId:songId,
					click:function(){
						var songId = $(this).attr("songId");
						//点击这里，播放音乐
						upSongListenCount(userId, songId);
						
					}
				}).appendTo(play_td);
				
				//歌曲名称
				var songName_td = $("<td>",{
					title:songName
				}).appendTo(song_tr).html(songName);
				
				//收藏音乐
				var addSongToSongList = $("<td>",{
					songId:songId,
					click:function(){
						
					}
				}).appendTo(song_tr);
				
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
				}).appendTo(addSongToSongList);
				
				
			}
			var more_song_tr = $("<tr>",{
				
			}).appendTo(tableId);
			var more_song_td = $("<td>",{
				colspan:'4',
				songCountType:songCountType,
				mouseover:function(){
					$(this).css("cursor","pointer");
				},
				click:function(){
					var songContent_type = $(this).attr("songCountType");
					sessionStorage.setItem("songContent_type",songContent_type);
					window.location.href = "SongRankingList.html";
				}
			}).appendTo(more_song_tr).html("查看全部>");
				
			$(more_song_td).css({"text-alirn":"right"});
		}else{
			//当数据小于10条时，显示所有
			for(var i = 0; i < songListJSONLen; i++){
				var songId = songListJSON[i].song.songId;//歌曲编号
				var songName = songListJSON[i].song.songName;//歌曲名字
				var songUrl = songListJSON[i].song.songUrl;//歌曲地址
				var song_tr = $("<tr>",{
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
				}).appendTo(tableId);
				
				var num_td = $("<td>",{
						
				}).appendTo(song_tr).html(i+1);
				if(i <= 4){
					$(num_td).css("color","#C82F2E");
				}
				//歌曲播放div
				var play_td = $("<td>",{
					songId:songId,
					click:function(){
						var songId = $(this).attr("songId");
						//点击这里，播放音乐
						upSongListenCount(userId, songId);
					}
				}).appendTo(song_tr).html("播");
				
				//歌曲名称
				var songName_td = $("<td>",{
					title:songName
				}).appendTo(song_tr).html(songName);
				
				//收藏音乐
				var addSongToSongList = $("<td>",{
					songId:songId,
					click:function(){
						
					}
				}).appendTo(song_tr);
				
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
				}).appendTo(addSongToSongList);
				
			}
		}
	} else if(songNum == 50){
		$(tableId).empty();
		//循环所有数据（最多50条），也就是显示在排行榜页面
		var titleSong_tr = $("<tr>",{
			
		}).appendTo(tableId);
		
		var titleSong_td1 = $("<td>",{
			
		}).appendTo(titleSong_tr);
		
		var titleSong_td2 = $("<td>",{
			
		}).appendTo(titleSong_tr);
		
		var titleSong_td3 = $("<td>",{
			
		}).appendTo(titleSong_tr).html("歌曲名");
		
		var titleSong_td4 = $("<td>",{
			
		}).appendTo(titleSong_tr).html("操作");
		
		var titleSong_td5 = $("<td>",{
			
		}).appendTo(titleSong_tr).html("歌手名");
		
		var titleSong_td6 = $("<td>",{
			
		}).appendTo(titleSong_tr).html("专辑名");
		
		var titleSong_td7 = $("<td>",{
			
		}).appendTo(titleSong_tr).html("时长");
		for(var i = 0; i < songListJSONLen; i++){
			var songId = songListJSON[i].song.songId;//歌曲编号
			var songName = songListJSON[i].song.songName;//歌曲名字
			var songUrl = songListJSON[i].song.songUrl;//歌曲地址
			var singerName = songListJSON[i].singer.singerName;//歌手名
			var cdName = songListJSON[i].cd.CDName;//专辑名称
			var songTime = songListJSON[i].song.songTime;//时长
			
			
			var song_tr = $("<tr>",{
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
			}).appendTo(tableId);
			
			//歌曲编号（数据库中编号无关，是表格中重新排序）
			var num_td = $("<td>",{
					
			}).appendTo(song_tr).html(i+1);
			if(i <= 4){
				$(num_td).css("color","#C82F2E");
			}
			//歌曲播放div
			var play_td = $("<td>",{
				
			}).appendTo(song_tr);
			
			//播放按钮
			var play_span_btn = $("<span>",{
				class:'playSongBtn',
				id:"playSongBtn",
				title:'播放',
				songId:songId,
				click:function(){
					var songId = $(this).attr("songId");
					//点击这里，播放音乐
					upSongListenCount(userId, songId);
				}
			}).appendTo(play_td);
			
			//歌曲名称
			var songName_td = $("<td>",{
				title:songName
			}).appendTo(song_tr).html(songName);
			
			
			//收藏音乐
			var addSongToSongList = $("<td>",{
				songId:songId,
				click:function(){
					
				}
			}).appendTo(song_tr);
			
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
				}).appendTo(addSongToSongList);
			
			
			//歌手名
			var singerName_td = $("<td>",{
				
			}).appendTo(song_tr).html(singerName);
			
			//专辑名
			var cdName_td = $("<td>",{
				
			}).appendTo(song_tr).html(cdName);
			
			//歌曲时长
			var songTime_td = $("<td>",{
				
			}).appendTo(song_tr).html(songTime);
		}
		
		//设置右侧歌曲列表偶数列和奇数列背景颜色
		$("#songRanking_table tr:even").css({"background":"#E8E8E8"});//tr偶数行背景颜色
		$("#songRanking_table tr:odd").css({"background":"#F4F4F4"});//tr奇数行背景颜色
	}
	
}