$(function() {

	$("#submit").click(function() {
		submitBaseLineForm();
		return false;
	});
	
	$("#thresholdsubmit").click(function() {
		submitThresholdForm();
		return false;
	});

});


function submitBaseLineForm() {
	//enablebaseline=on 居然有问题
//	if (!$("#detectionsetting").validate()){
//		return;
//	}
	
	$.post(appbasepath + "/config/detection/savesetting?time=" + (new Date().getTime()), $("#detectionsetting").serialize(), function(result) {
		// 返回302时
		deal302(result);
		if (result.status == 'succeed') {
			successtoast();
		}
		
	});
}


function submitThresholdForm(){
	$.post(appbasepath + "/config/detection/savethrsetting?time=" + (new Date().getTime()), $("#thresholdsetting").serialize(), function(result) {
		// 返回302时
		deal302(result);
		if (result.status == 'succeed') {
			successtoast();
		}
	});
}