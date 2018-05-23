$(function() {
	$("#show_log_opType").text(buildTypeStr($("#log_opType").val()));
	$("#show_log_opStyle").text(buildStyleStr($("#log_opStyle").val()));
	$("#show_log_opStyle_1").text(buildStyleStr($("#log_opStyle").val()));
});

//构建操作类型描述
function buildTypeStr(val) {
	switch (val) {
	case "1":
		return "匹配";
		break;
	case "2":
		return "修订";
		break;
	default:
		return "";
		break;
	}
}

//构建操作类型描述
function buildStyleStr(val) {
	switch (val) {
	case "1":
		return "自动合并";
		break;
	case "2":
		return "自动新建";
		break;
	case "3":
		return "自动拆分";
		break;
	case "4":
		return "人工合并";
		break;
	case "5":
		return "人工新建";
		break;
	case "6":
		return "人工拆分";
		break;
	default:
		return "";
		break;
	}
}