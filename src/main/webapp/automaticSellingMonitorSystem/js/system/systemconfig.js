$(function() {
	$("#systemset_submit").click(function() {
		var syslogpath = $('#syslogpath').val();
		var serverip = $('#serverip').val();
		console.log(serverip);
		if(syslogpath == "" || serverip == ""){
			return false;
		}
		submitForm();
		return false;
	});
});

function submitForm() {
	$.post(appbasepath + "/system/systemconfig/savesetting?time=" + (new Date().getTime()), $("#systemsetting").serialize(), function(result) {
		// 返回302时
		deal302(result);
		if (result.status == 'succeed') {
			successtoast();
		}
	});
}