function getUserInfoOfUserId(userId){
	var userInfoJSON;
	$.ajax({
		type:"post",
		url:serverURL + "/SelectUserInfo",
		async:false,//同步
		data:{"userId":userId},
		dataType:'json',//返回数据类型
		success:function(data){
			userInfoJSON = data;
		},//响应成功后执行代码
		error:function(XMLHttpRequest, textStatus, errorThrown){
			// 状态码
            console.log("状态码：" + XMLHttpRequest.status);
            // 状态
            console.log("状态：" + XMLHttpRequest.readyState);
            // 错误信息   
            console.log("错误信息：" + textStatus);
			console.log("用户信息获取失败");
		}
	});
	return userInfoJSON;
}
