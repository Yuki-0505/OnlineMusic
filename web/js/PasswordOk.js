$(function(){
	$("#psd2").change(function(){
		var psd1 = $("#psd1").val();
		var psd2 = $("#psd2").val();
		if(psd1 != psd2){
			$("#psd2").focus();
			$("#psd_text").empty();
			$("#psd_text").html("密码不一致，请修改！");
			$("#psd_text").css({"color":"red", "font-size":"16px"});
		}else{
			$("#psd_text").empty();
			$("#psd_text").html("密码符合要求！");
			$("#psd_text").css({"color":"#20A984", "font-size":"14px"});
		}
	})
})