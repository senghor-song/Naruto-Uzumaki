function sonSubmit(){
	var map = {};
	$(".popup_value").each(function(index,item) {
		map[$(this).attr("id")] = $("#"+$(this).attr("id")).val();// map.put(key, value);
	});
	parent.listSubmit(map);
}

function sonReset(){
	var listType=$("#listType").val();
	var agentSuperiorId=$("#agentSuperiorId").val();
	var memberSuperiorId=$("#memberSuperiorId").val();
	$(".popup_value").each(function(index,item) {
		$(this).val("");
	});
	$("#listType").val(listType);
	$("#agentSuperiorId").val(agentSuperiorId);
	$("#memberSuperiorId").val(memberSuperiorId);
}