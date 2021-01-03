document.write("<script type='text/javascript' src='js/ServerUrl.js'></script>")
document.write("<script type='text/javascript' src='js/SongListOfAccessCountLimit.js'></script>")
$(function(){
	var tagsName = sessionStorage.getItem("tagsName");
//	selectSongListOfAccessCountLimit(tags, currentPage)
	showSongList_ofTags(tagsName);
	//遍历标签JSON
	$("button[class='all_tags_button']").click(function(){
		selectSongListOfAccessCountLimit("", 1);
		sessionStorage.setItem("tagsName", "");
	})
	$("#all_tags_choose").click(function(){
		$("div[class='choose_tags']").slideToggle();
		$("#tags_table").empty();
		$.getJSON("js/tags.json","",function(data){
			$.each(data, function(name, value){
				var len = value.length;
				var tags_tr = $("<tr>",{
					
				}).appendTo("#tags_table");
				
				var tags_td_first = $("<td>",{
					valign:"top"
				}).appendTo(tags_tr).html(name);
				
				var tags_td_second = $("<td>",{
					valign:"top"
				}).appendTo(tags_tr);
				
				for(var i = 0; i < value.length; i++){
					var tags_td_a = $("<a>",{
						tags:value[i],
						onload:function(){
							var tagsNameAttr = $(this).attr("tags");
							if(tagsNameAttr == tagsName){
								$(this).css({"background":"#666", "color":"white"});
							}
						},
						click:function(){
							selectSongListOfAccessCountLimit($(this).attr("tags"), 1);
							sessionStorage.setItem("tagsName", $(this).attr("tags"));
//							showSongList_ofTags();
						}
					}).appendTo(tags_td_second).html(value[i]);
					
					var tags_td_span = $("<span>",{
						
					}).appendTo(tags_td_second).html("|");
				}
			})
		})
	})
})
