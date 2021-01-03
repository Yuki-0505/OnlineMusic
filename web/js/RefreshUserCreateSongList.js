document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
function songListDivShow(userId) {
//	if(userId == null){
//		userId = 1;
//	}
	//用户创建歌单JSON
	var allSongListJSONString = null;
	$.ajax({
		type:"get",
		url:serverURL + "/SelectUserSongListOfUserId?userId="+userId,
		async:false,//同步
		dataType:'text',//返回数据类型
		success:function(data){
//			alert("热门歌单响应成功：" + data);
			allSongListJSONString = data
		},//响应成功后执行代码
		error:function(){
			console.log("用户创建歌单获取失败");
		}
	});
	//	var length = allSongListJSONString.length;
	//	var lstr = "";
	//	for(var i = 0; i < length; i++){
	//		lstr += allSongListJSONString[i];
	//	}
	var allSongListJSONArr = eval("(" + allSongListJSONString + ")");
	//清空用户创建的歌单显示列表的大div
	$("#mySongList").empty();
	//清空用户收藏的歌单显示列表
	$("#myColleSongList").empty();
	//遍历用户创建的歌单
	if(allSongListJSONArr != null) {
		for(var i = 0; i < allSongListJSONArr.length; i++) {
			var userCreateSongListJSON = allSongListJSONArr[i].userCreateSongList; //用户创建的JSON数组
			var userCreateSongListJSONLength = userCreateSongListJSON.length;
			for(var j = 0; j < userCreateSongListJSONLength; j++) { //遍历用户创建的歌单数组
				var introduce = userCreateSongListJSON[j].introduce; //歌单简介
				var accessCount = userCreateSongListJSON[j].accessCount; //歌单热度（访问次数）
				var songListName = userCreateSongListJSON[j].songListName; //歌单名字
				var tags = userCreateSongListJSON[j].tags;//歌单标签
				var songListId = userCreateSongListJSON[j].songListId; //歌单编号
				// var songListImg = userCreateSongListJSON[j].songListImg;//歌单图片
				var createTheSongListOfUserId = userCreateSongListJSON[j].userId;//创建该歌单的用户编号
				var mySongList_div = $('<div>', {
					class: 'mySongList_div',
					songListId: songListId,
					createTheSongListOfUserId:createTheSongListOfUserId,
					click: function() {
						//设置评论按钮songListId值
						$("#comment_submit").attr("songlistid", $(this).attr("songListId"));
						mySongList_div_click($(this).attr("songListId"),$(this).attr("createTheSongListOfUserId"));
						$(this).children("input[name='bar_input']").prop("checked", true);
						var thisIndex = $(this).index();
						var mySongList_div = $(this);
						$("div[class='mySongList_div']").each(function(index, element) {
							if($(element).children("input").prop("checked")) {
								$(element).css("background", "#E6E6E6");
							} else {
								$(element).css("background", "#F9F9F9");
							}
						})
					},
					//鼠标移入移出，修改与删除图标显示
					mouseover: function() {
						$(this).children("div[class='delete_div']").css({
							"width": "15px",
							"height": "15px"
						});
						$(this).children("div[class='update_div']").css({
							"width": "15px",
							"height": "15px"
						});
					},
					mouseout: function() {
						$(this).children("div[class='delete_div']").css({
							"width": "0px",
							"height": "0px"
						});
						$(this).children("div[class='update_div']").css({
							"width": "0px",
							"height": "0px"
						});
					}
				}).appendTo("#mySongList");
				// $('<img />', {
				// 	alt: '图片',
				// 	src: function(){
				// 		if (songListImg != "" && songListImg != null) {
				// 			return serverURL + songListImg;
				// 		} else{
				// 			return "images/songListImg.jpg";
				// 		}
				// 	},
				// 	border: '0'
				// }).appendTo(mySongList_div);
				var span = $('<span>', {}).appendTo(mySongList_div);
				var songListName_P = $('<p>', {}).appendTo(span);
				$(songListName_P).html(songListName);
				var songListCount_P = $('<p>', {}).appendTo(span);
				$(songListCount_P).html("歌曲数目");
				//单选按钮
				var barInput = $("<input />", {
					type: 'radio',
					name: 'bar_input',
					class: 'bar_input'
				}).appendTo(mySongList_div);
				//删除按钮
				var deleteDiv = $("<div>", {
					class: 'delete_div',
					songListId: songListId,
					click: function() {
						//删除Ajax地址DeleteUserCreateSongList
						$.ajax({
							url: serverURL + '/DeleteUserCreateSongList?userId=' + userId + "&songListId=" + $(this).attr("songListId"),
							type: "GET",
							data: "",
							dataType: "text",
							async: false, //同步
							success: function() {
								getSongListInfo(userId);
								//									window.location.href = "MyMusicIframe.html";
								songListDivShow(userId); //重新调用显示用户创建的歌单列表的函数
							},
							error: function() {
								alert("出现未知错误，删除失败！");
							}
						});
					}
				}).appendTo(mySongList_div);
				var deleteBgImg = $("<img />", {
					height: '100%',
					src: 'images/deleteBgImg.png',
					title: "删除"
				}).appendTo(deleteDiv);

				//歌单修改img
				var updateDiv = $("<div>", {
					class: 'update_div',
					songListId: songListId,
					songListName:songListName,
					tags:tags,
					introduce:introduce,
					click: function() {
						$("#choose_tags").empty();
						$("#tags_yet").empty();//清空已存在标签
						
						//修改Ajax地址/UpdateSongListInfo
						var name = $(this).attr("songListName");
						var introduce = $(this).attr("introduce");
						var songListId = $(this).attr("songListId");
						$("input[name='update_songListName']").attr("value",name);
						$("#introduce").val(introduce);
						$("#choose_songListId").attr("value",songListId);
						$("div[class='updateSongList_div']").css("width","500px");
						if ($(this).attr("tags") != "" && $(this).attr("tags") != undefined) {
							console.log("打印标签值：" + $(this).attr("tags"))
							var tagsArr = $(this).attr("tags").split(" ");
							for (var i = 0; i < tagsArr.length; i++) {
								var tags_span = $("<span>",{
									class:'tags_span_one',
									title:'删除',
									click:function(){
										$(this).remove();
									}
								}).appendTo($("#tags_yet")).html(tagsArr[i]);
							}
						}
						
						var choose_tags_button = $("<span>",{
							id:choose_tags_button,
							click:function(){
								var tagsRecomm = null;
								$.ajax({
									url: serverURL + '/TagsRecommend',
									type: "GET",
									data: {"songListId":songListId},
									dataType: "text",
									async: false, //同步
									success:function(data){
										if (data != "null") {
											var dataJSON = eval("(" + data + ")");
											tagsRecomm = dataJSON;
										}
									},
									error:function(){
										alert("失败")
									}
								});
								$("div[class='tags_choose_input_div']").slideToggle();
								
								$("#choose_tags_table").empty();
								var tags_arr = new Array();
								$("span[class='tags_span_one']").each(function(index, element){
									tags_arr[index] = $(element).text();
								})
								if (tagsRecomm != null) {
									var tags_tr = $("<tr>",{
										class:'choose_tags_table_tr'
									}).appendTo("#choose_tags_table");
									var tags_td_first = $("<td>",{
										class:'recomm_tags_td'
									}).appendTo(tags_tr).html("推荐");
									
									var tags_td_second = $("<td>",{
										valign:"top"
									}).appendTo(tags_tr);
									
									$.each(tagsRecomm, function(name, value){
										var tags_td_a = $("<a>",{
											type:'choose_tags',
											class:'',
											click:function(){
												if($(this).attr("class") == "exist_tags"){
													$(this).toggleClass("exist_tags");
												}else{
													var chooseTagsLen = $("a[class='exist_tags']").length;
													if(chooseTagsLen < 3){
														$(this).toggleClass("exist_tags");
													}else{
														alert("最多选择3个标签");
													}
												}
														
											},
											tags:value
										}).appendTo(tags_td_second).html(value);
									});
								}
								$.getJSON("js/tags.json","",function(data){
									$.each(data, function(name, value){
										var len = value.length;
										var tags_tr = $("<tr>",{
											class:'choose_tags_table_tr'
										}).appendTo("#choose_tags_table");
										
										var tags_td_first = $("<td>",{
											valign:"top"
										}).appendTo(tags_tr).html(name);
										
										var tags_td_second = $("<td>",{
											valign:"top"
										}).appendTo(tags_tr);
										
										for(var i = 0; i < value.length; i++){
											var tags_existOrNot = $.inArray(value[i], tags_arr);//判断是否有某个标签
											if (tags_existOrNot == -1) {
												var tags_td_a = $("<a>",{
													type:'choose_tags',
													class:'',
													click:function(){
														if($(this).attr("class") == "exist_tags"){
															$(this).toggleClass("exist_tags");
														}else{
															var chooseTagsLen = $("a[class='exist_tags']").length;
															if(chooseTagsLen < 3){
																$(this).toggleClass("exist_tags");
															}else{
																alert("最多选择3个标签");
															}
														}
														
													},
													tags:value[i]
												}).appendTo(tags_td_second).html(value[i]);
											} else{
												var tags_td_a = $("<a>",{
													type:'choose_tags',
													class:"exist_tags",
													click:function(){
														if($(this).attr("class") == "exist_tags"){
															$(this).toggleClass("exist_tags");
														}else{
															var chooseTagsLen = $("a[class='exist_tags']").length;
															if(chooseTagsLen < 3){
																$(this).toggleClass("exist_tags");
															}else{
																alert("最多选择3个标签");
															}
														}
													},
													tags:value[i]
												}).appendTo(tags_td_second).html(value[i]);
											}
										}
									})
								})
								var choose_tags_save_tr = $("<tr>",{
									
								}).appendTo("#choose_tags_table");
								
								var choose_tags_save_td = $("<td>",{
									colspan:'2',
									class:'button_td'
								}).appendTo("#choose_tags_table");
								
								var choose_tags_save_button = $("<button>",{
									class:'choose_tags_save_button',
									click:function(){
										//保存并关闭按钮，点击事件
										$("div[class='tags_choose_input_div']").slideToggle();
										$("#tags_yet").empty();
										$("a[class='exist_tags']").each(function(index, element){
											var tags_span = $("<span>",{
												class:'tags_span_one',
												title:'删除',
												click:function(){
													$(this).remove();
												}
											}).appendTo($("#tags_yet")).html($(element).text());
										})
									}
								}).appendTo(choose_tags_save_td).html("保存并关闭");
								
								
							}
						}).appendTo($("#choose_tags")).html("选择标签");
//						alert("歌单名：" + $(this).attr("songListName"));
					}
				}).appendTo(mySongList_div);
				var updateBgImg = $("<img />", {
					height: '100%',
					src: 'images/updateBgImg.png',
					title: "编辑"
				}).appendTo(updateDiv);
				
				//填充添加歌曲到歌单的界面
				var addSongList_p = $("<p>",{
					id:"addSongList_p",
					class:"addSongList_p",
					songListId:songListId,
					click:function(){
						var songListId = $(this).attr("songListId");//歌单编号
						var songId = $(this).parent("div[class='addSongList_songList']").attr("songid");//歌曲编号
//						alert("歌单编号：" + $(this).attr("songListId") + "；歌曲编号：" + songId);
						$.ajax({
							type:"post",
							url:serverURL + "/AddSongListWithSong",
							data:{"songListId":songListId, "songId":songId},
							dataType:'text',//返回数据类型
							async:true,
							success:function(data){
								$("div[class='addSongList_songList']").attr("songid","");
								$("div[class='addSongList_showDiv']").css("display","none");
								switch (data){
									case "200":
										break;
									case "300":
										alert("添加失败，稍后再试！");
										break;
									case "300":
										alert("添加失败，（歌曲收藏次数+1失败）");
										break;
									case "100":
										alert("该歌曲已存在！");
										break;
									default:
										break;
								}
							},
							error:function(){
								alert("呀！出错了！");
							}
						});
					}
				}).appendTo("div[class='addSongList_songList']");
				$(addSongList_p).html(songListName);
			}
			//用户收藏的歌单数组
			var userCollectionSongListJSON = allSongListJSONArr[i].userCollection;
			var userCollectionSongListJSONLength = userCollectionSongListJSON.length;
			for(var c = 0; c < userCollectionSongListJSONLength; c++) { //遍历用户收藏歌单
				var tags = userCollectionSongListJSON[c].tags; //歌单简介
				var accessCount = userCollectionSongListJSON[c].accessCount; //歌单热度（访问次数）
				var songListName = userCollectionSongListJSON[c].songListName; //歌单名字
				var songListId = userCollectionSongListJSON[c].songListId; //歌单编号
				// var songListImg = userCollectionSongListJSON[c].songListImg;//歌单图片
				var createTheSongListOfUserId = userCollectionSongListJSON[c].userId;//创建歌单的用户
				var mySongList_div = $('<div>', {
					class: 'mySongList_div',
					songListId: songListId,
					createTheSongListOfUserId:createTheSongListOfUserId,
					click: function() {
						//设置评论按钮songListId值
						$("#comment_submit").attr("songlistid", $(this).attr("songListId"));
						$(this).children("input[name='bar_input']").prop("checked", true);
						mySongList_div_click($(this).attr("songListId"),$(this).attr("createTheSongListOfUserId"));
						var thisIndex = $(this).index();
						var mySongList_div = $(this);
						$("div[class='mySongList_div']").each(function(index, element) {
							if($(element).children("input").prop("checked")) {
								$(element).css("background", "#E6E6E6");
							} else {
								$(element).css("background", "#F9F9F9");
							}
						})
					},
					//鼠标移入移出，修改与删除图标显示
					mouseover: function() {
						$(this).children("div[class='deleteCollectionDiv']").css({
							"width": "15px",
							"height": "15px"
						});
						$(this).children("div[class='deleteCollectionDiv']").css({
							"width": "15px",
							"height": "15px"
						});
					},
					mouseout: function() {
						$(this).children("div[class='deleteCollectionDiv']").css({
							"width": "0px",
							"height": "0px"
						});
						$(this).children("div[class='deleteCollectionDiv']").css({
							"width": "0px",
							"height": "0px"
						});
					}
				}).appendTo("#myColleSongList");
				// $('<img />', {
				// 	alt: '图片',
				// 	src: function(){
				// 		if (songListImg != "" && songListImg != null) {
				// 			return serverURL + songListImg;
				// 		} else{
				// 			return "images/songListImg.jpg";
				// 		}
				// 	},
				// 	border: '0'
				// }).appendTo(mySongList_div);
				var span = $('<span>', {}).appendTo(mySongList_div);
				var songListName_P = $('<p>', {}).appendTo(span);
				$(songListName_P).html(songListName);
				var songListCount_P = $('<p>', {}).appendTo(span);
				$(songListCount_P).html("歌曲数目");
				//单选按钮
				var barInput = $("<input />", {
					type: 'radio',
					name: 'bar_input',
					class: 'bar_input'
				}).appendTo(mySongList_div);
				//删除收藏
				var deleteCollectionDiv = $("<div>", {
					class: 'deleteCollectionDiv',
					songListId: songListId,
					click: function() {
						//删除Ajax地址DeleteUserCreateSongList
						$.ajax({
							url: serverURL + '/DeleteUserWithSongList',
							type: "POST",
							data: {"userId":userId, "songListId":$(this).attr("songListId")},
							dataType: "text",
							async: false, //同步
							success: function() {
								getSongListInfo(userId);
								//									window.location.href = "MyMusicIframe.html";
								songListDivShow(userId); //重新调用显示用户创建的歌单列表的函数
							},
							error: function() {
								alert("出现未知错误，删除失败！");
							}
						});
					}
				}).appendTo(mySongList_div);
				var deleteBgImg = $("<img />", {
					height: '100%',
					src: 'images/deleteBgImg.png',
					title: "删除"
				}).appendTo(deleteCollectionDiv);
			}
		}
	}
}