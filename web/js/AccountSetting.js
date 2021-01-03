document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
document.write("<script type='text/javascript' src='js/GetUserInfoOfUserId.js'></script>");//获取用户信息
document.write("<script type='text/javascript' src='js/LoginIdOrPhoneExist.js'></script>");//验证用户信息是否已经被使用
$(function(){
	var userId = sessionStorage.getItem("userId");
	if (userId != "" && userId != null) {
		//用户编号不为空
		var userInfoJSON = getUserInfoOfUserId(userId);
		var userName = userInfoJSON.userName;//用户名
		var userSex = userInfoJSON.userSex;//性别
		var userEmail = userInfoJSON.email;//邮箱
		var userPhone = userInfoJSON.phone;//手机号
		var sign = userInfoJSON.sign;//签名
		var headSculptureUrl = serverURL + userInfoJSON.headSculptureUrl;
		//填充
		$("#userName").val(userName);//用户昵称
		$("#email").val(userEmail);//邮箱
		$("#phoneno").val(userPhone);//手机号
		$("#sign_text").val(sign);//签名
		$("#userHeader_img").attr("src",headSculptureUrl);//用户头像
		switch (userSex){
			case "男":
				$("#man").attr("checked",true);
				break;
			case "女":
				$("#woman").attr("checked",true);
				break;
			default:
				$("#man").attr("checked",true);
				break;
		}
		
		//点击保存按钮
		$(".save_userInfo").click(function(){
			var inputUserName = $("#userName").val();//填写的用户名
			var inputUserSex = $("input[name='userSex']:checked").val();//性别
			var inputUserEmail = $("#email").val();//邮箱
			var inputUserPhone = $("#phoneno").val();//手机号
			var inputSign = $("#sign_text").val();//签名信息
			if(inputUserName == "" || inputUserName == null){
				$("#userName").focus();
				$("#userName").attr("placeholder","用户名不能为空");
			}else{
				//可以上传
				$.ajax({
					type:"post",
					url:serverURL + "/UpdateUserInfo",
					data:{
						"userId" : userId,//用户编号
						"userName" : inputUserName,//用户名
						"userSex" : inputUserSex,//用户性别
						"email" : inputUserEmail,//用户邮箱
						"phone" : inputUserPhone,//用户手机号
						"sign" : inputSign//签名
					},
					async:false,//同步
					dataType:'text',//返回数据类型
					success:function(data){
		//				alert("热门歌单响应成功：" + data);
						switch (data){
							case "400":
								alert("用户信息修改失败！");
								break;
							case "200":
								alert("用户信息修改成功，重新获取信息失败！");
								parent.location.reload();
								break;
							default:
								var userInfoJSON = eval("(" + data + ")");
								var userName = userInfoJSON.userName;
								sessionStorage.setItem("userName",userName);
								parent.location.reload();
								break;
						}
						
					},//响应成功后执行代码
					error:function(){
						console.log("热门歌单信息获取失败");
					}
				});
			}
		})
	} else{
		window.location.href = "index.html";
	}
	//点击个人中心，进行跳转
	$("#PersoalCenter").click(function(){
		window.location.href = "PersonalCenter.html";
	});
	
	//点击更换头像按钮，跳转到更换图片的页面
	$("#updtate_img_p").click(function(){
		window.location.href = "updateUserHeaderImg.html";
	})
})
