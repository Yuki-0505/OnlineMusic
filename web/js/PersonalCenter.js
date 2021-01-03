document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
document.write("<script type='text/javascript' src='js/GetUserInfoOfUserId.js'></script>");//获取用户信息
document.write("<script type='text/javascript' src='js/GetCreateSongListandCollectionOfUser.js'></script>");//获取用户听歌次数排行
document.write("<script type='text/javascript' src='js/GetUserListenSongDesc.js'></script>");//获取用户歌单
//根据用户编号，查询用户创建的歌单
document.write("<script type='text/javascript' src='js/GetSongListOfUserId.js'></script>");
//填充添加歌曲到歌单的歌单界面
document.write("<script type='text/javascript' src='js/SearchSongAddSongToSongListDiv.js'></script>");
//增加播放次数和增加用户播放记录
document.write("<script type='text/javascript' src='js/UpSongListenCount.js'></script>");
$(function(){
	var userId = sessionStorage.getItem("userId");
	if (userId == "" || userId == null) {
		alert("请先登录")
	} else{
		var userInfoJSON = getUserInfoOfUserId(userId);//用户信息JSON
		//填充用户信息
		var userName = userInfoJSON.userName;//用户名
		var email = userInfoJSON.email;//用户邮箱
		var headSculptureUrl = serverURL + userInfoJSON.headSculptureUrl;//用户头像
		var userSex = userInfoJSON.userSex;//用户性别
		
		$("#userHeader_img").attr("src",headSculptureUrl);
		$("#personal_userName").html(userName);
		$("#personal_userSex").html(userSex);
		$("#userEmail").html(email);
		
		var userListenSongJSON = getUserListenSongDesc(userId);//用户听歌次数排行
		
		//填充听歌次数Top20
		$.each(userListenSongJSON, function(index, value) {
			var songId = value.song.songId;
			var songName = value.song.songName;//歌曲名
			var singerName = value.singer.singerName;//歌手名
			var listenSongCount = value.listenCount;//歌曲播放次数
			var cdName = value.cd.CDName;//专辑名
			
			var userSongListen_tr = $("<tr>",{
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
			}).appendTo(".listensong_table");
			
			//序号列
			var num_td = $("<td>",{
				
			}).appendTo(userSongListen_tr).html(index + 1);
			
			//播放按钮
			var songPlay_td = $("<td>",{
				
			}).appendTo(userSongListen_tr);
			
			var playSongBtn = $("<span>",{
				class:"playSongBtn",
				id:"playSongBtn",
				title:'播放',
				songId:songId,
				click:function(){
					var songId = $(this).attr("songId");
					//点击这里，播放音乐
					upSongListenCount(userId, songId);
				}
			}).appendTo(songPlay_td);
			
			//歌曲名
			var songName_td = $("<td>",{
				
			}).appendTo(userSongListen_tr).html(songName);
			
			//添加到歌单
			var addSongToSongList_td = $("<td>",{
				
			}).appendTo(userSongListen_tr);
			
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
				}).appendTo(addSongToSongList_td);
			
			//歌手名
			var singerName_td = $("<td>",{
				
			}).appendTo(userSongListen_tr).html(singerName);
			
			//专辑名
			var cdName_td = $("<td>",{
				
			}).appendTo(userSongListen_tr).html(cdName);
			
			//听歌次数
			var listenSongCount_td = $("<td>",{
				
			}).appendTo(userSongListen_tr).html(listenSongCount);
		});
		
		//奇数偶数行不同背景颜色
		$(".listensong_table tr:even").css("background","#E8E8E8");
		$(".listensong_table tr:odd").css("background","#F4F4F4");
		
		var userAllSongListJSON = getCreateSongListAndCollectionOfUserId(userId);//用户歌单信息
		var userCreateSongList = userAllSongListJSON[0].userCreateSongList;//用户创建的歌单的JSON数组
		//遍历显示创建的歌单
		$.each(userCreateSongList, function(index, value){
			var songListId = value.songListId;//歌单编号
			var songListName = value.songListName;//歌单名称
			var songListImg = value.songListImg;//歌单图片
			var createSongList_li = $("<li>",{
				
			}).appendTo(".userCreateSongList_ul");
			
			//歌单图片div
			var createSongList_img_div = $("<div>",{
				class:'createSongList_img_div',
				title:songListName,
				songListId:songListId
			}).appendTo(createSongList_li);
			
			//歌单图片img
			var createSongList_img = $("<img />",{
				class:'createSongList_img',
				alt:'图片',
				src:function(){
					if (songListImg != "" && songListImg != null) {
						return serverURL + songListImg;
					} else{
						return "images/songListImg.jpg";
					}
				},
				width:'100%'
			}).appendTo(createSongList_img_div);
			
			//歌单名
			var createSongList_name = $("<div>",{
				class:'createSongList_name',
				songListId:songListId,
				title:songListName
			}).appendTo(createSongList_li).html(songListName);
		})
		
		var userCollectionSongList = userAllSongListJSON[0].userCollection;//用户收藏歌单JSON数据
		//遍历显示用户收藏的歌单
		$.each(userCollectionSongList, function(index, value){
			var songListId = value.songListId;//歌单编号
			var songListName = value.songListName;//歌单名
			var songListImg = value.songListImg;//歌单图片
			var collectionSongList_li = $("<li>",{
				
			}).appendTo(".userCollectionSongList_ul");
			
			//歌单图片div
			var collectionSongList_img_div = $("<div>",{
				class:'collectionSongList_img_div',
				title:songListName,
				songListId:songListId
			}).appendTo(collectionSongList_li);
			
			//歌单图片img
			var collectionSongList_img = $("<img />",{
				class:'collectionSongList_img',
				alt:'图片',
				src:function(){
					if (songListImg != "" && songListImg != null) {
						return serverURL + songListImg;
					} else{
						return "images/songListImg.jpg";
					}
				},
				width:'100%'
			}).appendTo(collectionSongList_img_div);
			
			//歌单名
			var collectionSongList_name = $("<div>",{
				class:'collectionSongList_name',
				songListId:songListId,
				title:songListName
			}).appendTo(collectionSongList_li).html(songListName);
		})
		
	}
	
	//用户添加歌曲到歌单，点击X号关闭窗口
	$("#none_addSongList_showDiv").click(function(){
		$("div[class='addSongList_songList']").attr("songid","");
		$("div[class='addSongList_showDiv']").css("display","none");
	})
	
	//点击“编辑个人资料”按钮进行页面跳转
	$("#setting_userInfo").click(function(){
		window.location.href = "AccountSetting.html";
	})
})
