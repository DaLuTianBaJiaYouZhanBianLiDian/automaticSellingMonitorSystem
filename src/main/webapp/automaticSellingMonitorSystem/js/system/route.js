$(function() {
	// 初始化请求路径
	Modal.data.viewUrl = appbasepath + "/config/viewroute";
	Modal.data.toupdateUrl = appbasepath + "/config/toupdateroute";
	Modal.data.addUrl = appbasepath + "/config/addroute";
	Modal.data.updateUrl = appbasepath + "/config/updateroute";
	Modal.data.deleteUrl = appbasepath + "/config/deleteroute";
	
	query_route();
//	ip_select();
});

function query_route() {
	$.post(appbasepath + "/config/routedata?time=" + (new Date().getTime()), {}, function(result) {
		var routes_tbody = $("#routes tbody");
		if (result.status == 'succeed') {
			routes_tbody.html("");
			if (result.datas.length == 0) {
				return;
			} else {
				var value = "";
				for ( var i = 0; i < result.datas.length; i++) {
					var data = result.datas[i];
					value = value + "<tr><td>"
					+ (data.flow_ip) + "</td><td>"
					+ (data.flow_simple) + "</td><td>"
					+ (data.name) + "</td><td>"
					+ (data.description)+"</td><td>"
					+ (data.routeip)+"</td><td>"
					+ (data.snmp_version)+"</td><td>"
					+ (data.snmp_ip)+"</td><td>"
					+ (data.snmp_communiry)+"</td><td>"
					+ '<a class="btn btn-default entypo-eye" href=\'javascript: Modal._viewModalShow("'+data.routeip+'","查看采集对象");\' title="查看采集对象"></a>'
	    			+ '&nbsp;&nbsp;'
	    			+ '<a class="btn btn-default entypo-cog" href=\'javascript: Modal._updateModalShow("'+data.routeip+'","修改采集对象");\' title="修改采集对象"></a>'
			    	+ '&nbsp;&nbsp;'
					+ '<a class="btn btn-default entypo-minus-circled"  href=\'javascript: Modal._deleteModalConfirm("'+data.routeip+'","删除采集对象");\' title="删除采集对象"></a>'  +"</td>" +
					"</tr>";
				}
				routes_tbody.append(value);
			}
		}
	});
}

function ip_select() {
	var ipSelector = $('#ip-select');
	$.post(appbasepath + "/snmp/iplist" , {}, function(result) {
		if (result.status == 'succeed') {
			if (result.datas.length == 0) {
				return;
			} else {
				for ( var i = 0; i < result.datas.length; i++) {
				    ipSelector.append('<option value="'
				            +(result.datas[i].snmp_ip) + '">'
				            +(result.datas[i].routeip)+'</option>')
				}
			}
		}
        query_interface(result.datas[0].snmp_ip);
	});
    $(ipSelector).on('change', function(){
        query_interface($(this).val());
    });
}

function query_interface(ip) {
	$.post(appbasepath + "/snmp/getifs?ip="+ip , {}, function(result) {
		var routes_tbody = $("#interfaces tbody");
		if (result.status == 'succeed') {
			routes_tbody.html("");
			if (result.datas.length == 0) {
				return;
			} else {
				for ( var i = 0; i < result.datas.length; i++) {
					routes_tbody.append("<tr><td>"
							+ (result.datas[i].index) + "</td><td>"
							+ (result.datas[i].description) + "</td><td>"
							+ (result.datas[i].speed)+"</td>" +
							"</tr>");
				}
			}
		}
	});
}

var Modal = {
	data : {
		viewUrl : '',
		toupdateUrl : '',
		addUrl : '',
		updateUrl : ''
	},
	_beforeShow : function(){
		var body_scrollHeight = $('body')[0].scrollHeight;
		var docHeight = document.documentElement.clientHeight;
		if(body_scrollHeight > docHeight){
			$('body').css({
			  'overflow' : 'hidden',
			  'margin-right' : '17px'
			});
		}else{
			$('body').css({
			  'overflow' : 'auto',
			  'margin-right' : '0'
			});
		}
	},
	_afterShow : function (){
		$('body').css({
			  'overflow' : 'auto',
			  'margin-right' : '0'
		});
	},
	_modalListener:function(){
		$('#addFunctionModal').on('show.bs.modal', function () {
			Modal._beforeShow();
			$("#functionSave")[0].reset();
		});
		$('#viewFunction').on('show.bs.modal', function () {
			Modal._beforeShow();
		});
		$('#viewFunction').on('hidden.bs.modal', function () {
			Modal._afterShow();
		});
		$('#updateFunction').on('show.bs.modal', function () {
			Modal._beforeShow();
		});
		$('#updateFunction').on('hidden.bs.modal', function () {
			Modal._afterShow();
		});
	},
	_viewModalShow : function(id,title){
		console.log("id = " + id);
		console.log("title = " + title);
		$('#viewTitle').html(title);
		$('#viewModal').modal('show', {backdrop: 'static'});
		$.post(Modal.data.viewUrl + "?id="+id+"&time=" + (new Date().getTime()), function(result) {
			$('#viewContent').html(result);
		});
	},
	_updateModalShow : function(id,title){
		console.log("id = " + id);
		console.log("title = " + title);
		$('#updateTitle').html(title);
		$('#updateModal').modal('show', {backdrop: 'static'});
		$.post(Modal.data.toupdateUrl + "?id="+id+"&time=" + (new Date().getTime()), function(result) {
			$('#updateContent').html(result);
		});
	},
	_deleteModalConfirm : function(id){
		$("#deleteId").val(id);
		$('#deleteModal').modal('show', {backdrop: 'static'});
	},	
	_updateData:function(){
		var submit = true;
		if(submit){
			var idObj = $('#routeip').val();
			var request_url = "";
			var request_body = $("#updateForm").serialize();
			if(idObj == null || idObj == ""){
				request_url = Modal.data.addUrl;
			}else {
				request_url = Modal.data.updateUrl;
			}
			$.post(request_url+"?time=" + (new Date().getTime()), request_body, function(result) {
				// 返回302时
				deal302(result);
				if (result.status == 'succeed') {
//					tableContainer._fnAjaxUpdate();		
					$('#updateModal').modal('hide');
					query_route();
					successtoast();
				}else{
					console.log("result.alerts " + result.alerts);
					if(result.alerts != null){
						alert(result.alerts);
					}
				}
			});
		};
	},
	_deleteData:function(){
		var deleteIDs = $("#deleteId").val();
		console.log("deleteIDs :" + deleteIDs);
		$.post(Modal.data.deleteUrl + "?id="+deleteIDs +"&time=" + (new Date().getTime()) , function(result) {
			// 返回302时
			deal302(result);
			if (result.status == 'succeed') {
//				tableContainer._fnAjaxUpdate();	
				$('#deleteModal').modal('hide');
				query_route();
				successtoast();
				$("#deleteId").val("");
			}else {
				console.log("result.alerts " + result.alerts);
				if(result.alerts != null){
					alert(result.alerts);
				}
			}
		});
	}
};
