document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>");
$(function(){
	function setAjax(inputId, setHtmlId, setHtmlText, inputValue, keyValue, url){
		//inputId要判断的输入框的id值，格式为#id
		//setHtmlId提示语显示位置的id值，格式为#id
		//setHtmlText提示语
		//inputValue输入框输入的值
		//keyValue  data属性值，格式"键名"="值"&"键名"="值"
		//url agax提交的url路径
		if(inputValue != null || inputValue != ""){
			$.ajax({
				"url" : url,//提交的URL路径
				"type" : "GET",//提交的方式
				"data" : keyValue,//提交到服务器的数据
				"dataType" : "text",//指定返回数据类型
				"async" : true,
				"success" : callBack,//响应成功后执行代码
				"error" : function(XMLHttpRequest, textStatus, errorThrown){//响应失败执行代码
					alert(XMLHttpRequest.status + ";  " + XMLHttpRequest.readyState + ";  " + textStatus);
				}
			});
			//响应成功时的回调函数
			function callBack(date){
				if(date == "false"){
					$(inputId).focus();
					$(inputId).val("");
					$(setHtmlId).empty();
					$(setHtmlId).html(setHtmlText);
					$(setHtmlId).css({"color":"red", "font-size":"16px"});
				}else{
					$(setHtmlId).empty();
					$(setHtmlId).html("符合要求！");
					$(setHtmlId).css({"color":"#20A984", "font-size":"14px"});
				}
			}
		}
	}
	$("#loginid").change(function(){//验证账号是否重复
		var loginIdZ = /^[a-zA-Z]{1}([a-zA-Z0-9]|[_]){5,17}$/;
		var loginId = this.value;
		var inputId = "#loginid";
		var setHtmlId = "#loginId_text";
		var setHtmlText = "该账号已经被注册";
		var inputValue = loginId;
		var url = serverURL + "/LoginIdExist?callback=callBack()";
		var keyValue = "loginId" + "="+ inputValue;
//		alert(keyValue);
		if(!loginIdZ.test(loginId)){
			$(inputId).focus();
			$(inputId).val("");
			$(inputId).attr("placeholder","账号格式不正确！");
			$(inputId).addClass("inp");
		}else{
			setAjax(inputId, setHtmlId, setHtmlText, inputValue, keyValue, url);
		}
	});
	
	$("input[id='phoneno']").change(function(){//验证手机号是否已经被使用
		var phoneZ = /^1[3|5|7|8][0-9]{9}$/
		var phone = this.value;
		var inputId = "#phoneno";
		var setHtmlId = "#phone_text";
		var setHtmlText = "该手机号已经被使用";
		var inputValue = phone;
		var url = serverURL + "/PhoneExist";
		var dataKey = "phone";
		var keyValue = "phone" + "="+ inputValue;
		if(!phoneZ.test(phone)){
			$(inputId).focus();
			$(inputId).val("");
			$(inputId).attr("placeholder","请正确填写手机号！");
			$(inputId).addClass("inp");
		}else{
			setAjax(inputId, setHtmlId, setHtmlText, inputValue, keyValue, url);
		}
		
	});
	$("input[id='email']").change(function(){//验证邮箱是否已经被使用
		var emailZ = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
		var email = this.value;
		var inputId = "#email";
		var setHtmlId = "#email_text";
		var setHtmlText = "该邮箱已经被使用";
		var inputValue = email;
		var url = serverURL + "/EmailExist";
		var keyValue = "email="+inputValue;
		if(!emailZ.test(email)){
			$(inputId).focus();
			$(inputId).val("");
			$(inputId).attr("placeholder","请正确填写邮箱地址！");
			$(inputId).addClass("inp");
		}else{
			setAjax(inputId, setHtmlId, setHtmlText, inputValue, keyValue, url);
		}
	});
})