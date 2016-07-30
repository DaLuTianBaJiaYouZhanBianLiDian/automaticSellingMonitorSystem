function activeDiv(classname,divid){
	var acctive_menu_ids = $('#acctive_menu_ids').val();
	if(acctive_menu_ids != undefined){
		var ids = acctive_menu_ids.split(",");
		for(var i=0; i<ids.length; i++){
			$("#"+ids[i]).addClass("active");
		}
	}
}

$("#nav_search").click(function () {
    var Ip = $(".nav_search-field").val();
    var re = /^((?:(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d))))$/;
    if (!re.test(Ip)) {
        alert("请输入正确的IP...");
        return false;
    } else {
        window.location.href = appbasepath + "/interact/interact?ip=" + Ip;
    }
});


