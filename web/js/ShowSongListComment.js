function showSongListComment(userSongListCommArr){//传入参数是评论JSON数组
	$("#comment_ul").empty();
	var userSongListCommLen = userSongListCommArr.length;
	if(userSongListCommLen > 0){
		for (var i = 0; i < userSongListCommLen; i++) {
			var commUserHeaderUrl = serverURL + userSongListCommArr[i].commentUserInfo.headSculptureUrl;//评论人头像地址
			var commUserName = userSongListCommArr[i].commentUserInfo.userName;//评论人名称
			var commContent = userSongListCommArr[i].commentContent.songListCommText;//评论内容
			var commDate = userSongListCommArr[i].commentContent.songListCommDate;//评论时间
			//填充
			var songListComm_li = $("<li>",{
				class:'comment_ul_li'
			}).appendTo("#comment_ul");
			
			
			var commUserHeaderImg_div = $("<div>", {
				class:'commUserHeaderImg_div'
			}).appendTo(songListComm_li);
			
			//评论人头像
			var commUserHeaderImg = $("<img />",{
				class:'commUserHeaderImg',
				src:commUserHeaderUrl,
				width:'100%'
			}).appendTo(commUserHeaderImg_div);
			
			//评论信息
			var commContent_div = $("<div>",{
				class:'commContent_div'
			}).appendTo(songListComm_li);
			
			//评论人姓名
			var commUserName_span = $("<span>",{
				id:'commUserName'
			}).appendTo(commContent_div).html(commUserName + "：");
			
			//评论内容
			var commContent_span = $("<span>",{
				id:'commContent'
			}).appendTo(commContent_div).html(commContent);
			
			//评论时间
			var commDate_p = $("<p>",{
				class:'commDate'
			}).appendTo(commContent_div).html("时间：" + commDate);
			
			var clear_div1 = $("<div>",{
				class:'clear'
			}).appendTo(songListComm_li);
			
			var clear_div2 = $("<div>",{
				class:'clear'
			}).appendTo("#comment_ul");
		}
	}
}
