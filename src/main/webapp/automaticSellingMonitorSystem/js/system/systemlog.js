var webSocket = null; 
$(function() {
	if(serverip == null || serverip == ""){
		$("#live_log_messages").html('');
		$("#live_log_messages").append('<div style="color:red;">请配置服务器地址.</div>');
	}else{
		var url = 'ws://'+serverip+'/nfs/chat';
//		var url = 'ws://192.168.24.142:8080/nfs/chat';
		if('WebSocket' in window){ 
			webSocket = new WebSocket(url); 
		}else if('MozWebSocket' in window){ 
			webSocket = new MozWebSocket(url); 
		}else{ 
			console.log("not support websocket"); 
		}
		webSocket.onerror = function(event) {
			onError(event);
		};
		
		webSocket.onopen = function(event) {
			onOpen(event);
		};
		
		webSocket.onmessage = function(event) {
			onMessage(event);
		};
	}
});

function onMessage(event) {
//	console.log("onMessage : " + event.data);
	var data = event.data;
	data = eval('(' + data + ')');
	if(data.type == 'systemloglist' ){
		var log_table_body = $('#logs_table tbody');
		var values = JSON.parse(data.text);
		log_table_body.html('');
		if(values.length == 0){
			log_table_body.append("<tr><td colspan='6' style='text-align: center;'>没有数据</td></tr>");
		}else {
			var datas = new Array();
			for ( var i = 0; i < values.length; i++) {
				var date = values[i].name.split(".")[1];
				var id = parseInt(date.split('-')[0]+date.split('-')[1]+date.split('-')[2]+"");
				datas.push({
					id : id,
					name : values[i].name,
					size : values[i].size
				});
			}
			datas.sort(function sortNumber(a, b) {
				return b.id-a.id ;
			});
			for (var i=0;i<datas.length;i++){
				var value = datas[i];
				var fileName = value.name;
				var fileSize = (value.size/1024).toFixed(2);
				log_table_body.append("<tr><td width='8%'>"+(i+1)+"</td>" +
						"<td width='20%'>"+fileName.split(".")[1]+"</td>" +
						"<td width='25%'>"+fileName+"</td>" +
						"<td width='25%'>"+fileSize+"</td>" +
						'<td width="22%"><a class="btn btn-default entypo-eye querylogs" href=\'javascript: getLogDetailPopup("'+fileName+'");\'">查看日志</a></td></tr>');
			}
		}
	}else if(data.type == 'systemlogdetail' ){
		$('#history_log_messages').append(data.text);
		PageInfo.setPageInfo(data.pageInfo);
		$('#pageing').html(PageInfo.startIndex + "-" + (PageInfo.countOfEveryPage * PageInfo.currentPageNumber) + "行");
		$('#pagetotal').html("总(" + PageInfo.countTotal + "行,"+ PageInfo.pageTotal + "页)");
		initPageCondition();
	}else{
		$("#live_log_messages").append(data.text);
	}
}

function onOpen(event) {
	$("#live_log_messages").html('');
//	console.log("onOpen ");
}

function onError(event) {
//	console.log("onError : " + event.data);
}

function getLogList(){
	var messages = {};
	messages.type = "systemloglist";
	if(($('.loglist_table .panel-body').css('display') == "block") == false){
		webSocket.send(JSON.stringify(messages));
	}
}

function getLogDetail(value){
	$('#goPage').val('');
	$('#history_log_messages').html('');
	var messages = {};
	messages.type = "systemlogdetail";
	messages.message = value;
	messages.pageInfo = PageInfo;
	webSocket.send(JSON.stringify(messages));
}

function getLogDetailPopup(value){
	PageInfo.startIndex = 1;
	PageInfo.currentPageNumber = 1;
	getLogDetail(value);
	$('#logPopupTitle').html('详细日志-'+value);
	$('#logDetailModal').modal('show', {backdrop: 'static'});
}

function getLogDetailPopupByPage(index){
	PageInfo.currentPageNumber = index;
	PageInfo.startIndex = (index -1) * PageInfo.countOfEveryPage + 1;
	var title = $('#logPopupTitle').html();
	var value = title.split("详细日志-")[1];
	getLogDetail(value);
}

function sumbitPage(){
	var pageNumber = $('#goPage').val();
	if(pageNumber > 0 && pageNumber <= PageInfo.pageTotal){
		getLogDetailPopupByPage(pageNumber);
	}else {
		alert("请填写正确的页数");
	}
}

function initPageCondition(){
	$('#pageFill').html('');
	var value = "";
	currentPageNumber = PageInfo.currentPageNumber;
	pageTotal = PageInfo.pageTotal;
	
	value += (currentPageNumber == 0||currentPageNumber == 1 ? "<li class='disabled' ><a href='#'><i class='entypo-left-open-mini'></i></a></li>" 
			: "<li ><a href=\"javascript: getLogDetailPopupByPage('"+(currentPageNumber-1)+"');\"><i class='entypo-left-open-mini'></i></a></li>"); 
     if(currentPageNumber<=3){  
         for(var i=1; i<=(pageTotal<=5?pageTotal:5); i++){  
        	 value +="<li "+(i==currentPageNumber?"class='active'":"")+"><a href=\"javascript: getLogDetailPopupByPage('"+i+"');\">"+i+"</a></li>";  
         }  
     }else if(currentPageNumber>3 && pageTotal<=(currentPageNumber+2)){  
         for(var i=pageTotal-4; i<=pageTotal; i++){  
        	 value +="<li "+(i==currentPageNumber?"class='active'":"")+"><a href=\"javascript: getLogDetailPopupByPage('"+i+"');\">"+i+"</a></li>";  
         }  
     }else if(currentPageNumber>3 && pageTotal>(currentPageNumber+2)){  
         for(var i=currentPageNumber-2; i<=currentPageNumber+2; i++){  
        	 value +="<li "+(i==currentPageNumber?"class='active'":"")+"><a href=\"javascript: getLogDetailPopupByPage('"+i+"');\">"+i+"</a></li>";  
         }  
     }  
     value += (currentPageNumber == pageTotal ? "<li class='disabled' ><a href='#'><i class='entypo-right-open-mini'></i></a></li>" 
 			: "<li ><a href=\"javascript: getLogDetailPopupByPage('"+(currentPageNumber+1)+"');\"><i class='entypo-right-open-mini'></i></a></li>");
     $('#pageFill').html(value);
}

var PageInfo = {
		countTotal : null,
		countOfEveryPage : 100,
		currentPageNumber : 1,
		pageTotal : null,
		startIndex : 1,
		setPageInfo : function(pageInfo){
			this.countTotal = pageInfo.countTotal;
			this.countOfEveryPage = pageInfo.countOfEveryPage;
			this.currentPageNumber = pageInfo.currentPageNumber;
			this.pageTotal = pageInfo.pageTotal;
			this.startIndex = pageInfo.startIndex;
		}
};