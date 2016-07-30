$(function() {
	// 初始化请求路径
	Modal.data.viewUrl = appbasepath + "/config/viewcust";
	Modal.data.toupdateUrl = appbasepath + "/config/toupdatecust";
	Modal.data.addUrl = appbasepath + "/config/addcust";
	Modal.data.updateUrl = appbasepath + "/config/updatecust";
	Modal.data.deleteUrl = appbasepath + "/config/deletecust";
	
	query_custs();
});

function showsettingwin(custsid,name){
	//alert(custsid);
	//客户异常检测页面
	$("#custdetecionpage").attr("src",appbasepath+"/config/custdetection?custid="+custsid);
	//展示
	$("#custdetectsettingtitle").html(name);
	$('#custdetectsetting').modal('show', {backdrop: 'static'});
}

function query_custs() {
	$.post(appbasepath + "/config/custdata?time=" + (new Date().getTime()), {}, function(result) {
		var routes_tbody = $("#custs tbody");
		if (result.status == 'succeed') {
			routes_tbody.html("");
			if (result.datas.length == 0) {
				return;
			} else {
				//<td>" + result.datas[i].uuid + "</td>
				for ( var i = 0; i < result.datas.length; i++) {
					var data = result.datas[i];
					routes_tbody.append("<tr><td width='10%'>"
							+ (data.cuscode) + "</td><td width='20%'>"
							+ (data.customername) + "</td><td width='25%'>"
							+ (data.address).replace(eval("/,/gi"),"<br/>") + "</td><td width='10%'>"
							+ (data.bandwidth)+"</td><td width='10%'>"
							+ (data.asnum==null?"":data.asnum)+"</td><td width='25%'>"
							+ "<a class='btn btn-default entypo-chart-bar' href='"+appbasepath+"/report/customer?custid="+data.uuid+"' title='流量报表' ></a>"
							+ '&nbsp;&nbsp;'
							+ "<a href=\"javascript: showsettingwin('"+data.uuid+"','"+data.customername+"');\" class=\"btn btn-default entypo-flow-tree\" title='异常检测配置' ></a>"
							+ '&nbsp;&nbsp;'
							+ '<a class="btn btn-default entypo-eye" href=\'javascript: Modal._viewModalShow("'+data.uuid+'","查看客户");\' title="查看客户"></a>'
			    			+ '&nbsp;&nbsp;'
			    			+ '<a class="btn btn-default entypo-cog" href=\'javascript: Modal._updateModalShow("'+data.uuid+'","修改客户");\' title="修改客户"></a>'
					    	+ '&nbsp;&nbsp;'
							+ '<a class="btn btn-default entypo-minus-circled"  href=\'javascript: Modal._deleteModalConfirm("'+data.uuid+'","删除客户");\' title="删除客户"></a>'
							+ "</td></tr>");
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
			var idObj = $('#uuid').val();
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
					$('#updateModal').modal('hide');
					query_custs();
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
				$('#deleteModal').modal('hide');
				query_custs();
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