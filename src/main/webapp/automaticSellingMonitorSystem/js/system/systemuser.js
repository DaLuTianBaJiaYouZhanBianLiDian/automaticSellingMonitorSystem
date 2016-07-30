var tableContainer;
var traceDetailModal;
$(function() {
	moment.lang('zh-cn');
	insert_alert_able();
	Modal._modalListener();  //模态框监听,修改body,防止页面抖动
	$('#table_alerts_filter').hide();

});

function insert_alert_able(){
	if(tableContainer == null){
		content_table.init(5, "desc");  //默认按开始时间倒序 排序
		$('#table_alerts_length').hide();
	}else {
		tableContainer.fnDraw(); 
	}
}
var content_table = {
		init: function(sortKey, sortType) {
			tableContainer = $("#table_alerts");
			tableContainer.dataTable({
				"sPaginationType": "bootstrap",
//				"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				'iDisplayLength': 10,
		        "oLanguage": {  
			        	   "oPaginate": {
			               "sFirst": "首页",
			               "sPrevious": "前一页",
			               "sNext": "下一页",
			               "sLast": "尾页"
			             },
		            "sLengthMenu": "每页显示: "+" _MENU_ "+"条",   
		            "sZeroRecords": "没有数据",  
		            "sInfo": "_START_ - _END_ "+"总"+"(_TOTAL_)",  
		            "sInfoEmpty": "0 - 0 "+"总"+"： 0",
		            "sSearch" : "快速查询:  ",
		            "sSearchPlaceholder": "123",
		            "sProcessing": "正在加载中"
		        },
				"bFilter": true,
				"aaSorting": [[sortKey , sortType ]],
				"aoColumns": [
					{ "bSortable": false },
                    { "bSortable": false },
                    { "bSortable": false },
                    { "bSortable": false },
                    { "sType": "string" },
                    { "sType": "string" },
                    { "bSortable": false }
				],
				"bStateSave": true,
				"bProcessing": true,
				"bServerSide": true, //指定从服务器端获取数据
				"sAjaxSource": appbasepath + "/systemuser/queryuserlist?time=" + (new Date().getTime()),
				"fnServerData": function ( sSource, aoData, fnCallback ) {
					var result = alertCondition._buildContentCondition(aoData);
		        	var conditionStr = JSON.stringify(result.condition);
					$.ajax({
						dataType: 'json',
						type: "POST",
						url: sSource,
						data: {conditionStr:conditionStr},
						success: function(data, sEcho){
							if (data.status == 'succeed') {
								var resultData = alertCondition._buildResultData(data,sEcho);
								fnCallback(resultData); //服务器端返回的对象的returnObject部分是要求的格式
							}else {
								alert("查询失败");
							}
						},
						error : function(data) {
							alert("请求失败");
						}
					});
				}
			});
		}
};
var alertCondition = {
		data : null,
		enumData : {
			sortKeyType : {
				create_time : "CREATE_TIME",
				update_time : "UPDATE_TIME"
			},
			sortDirectType : {
				ascType : "ASC",
				descType : "DESC"
			}
		},
		initData : function(data){
			this.data = {};
			this.data.sortKeyType = data.sortKeyType || this.enumData.sortKeyType.update_time;
			this.data.sortDirectType = data.sortDirectType || this.enumData.sortDirectType.descType;
			this.data.pagingCondition = {};
			this.data.pagingCondition.startIndex = data.startIndex || 0;
			this.data.pagingCondition.count = data.count || 10;
			this.data.userName = data.userName || null;
			this.data.userType = data.userType || null;
			this.data.status = data.status || null;
			this.data.searchValue = data.searchValue || null;
		},
		setContentID : function(contentID){
			this.data.contentID = contentID;
		},
		setSearchValue : function(searchValue){
			this.data.searchValue = searchValue;
		},
		_buildContentCondition : function(aoDatas){
	    	var result = {
	    		sEcho : null,
	    		condition : null
	    	};
	    	if(!window.alertCondition.data){
	    		alertCondition.initData({});
	    	}
	    	for(var i=0;i<aoDatas.length;i++){
	    		var data = aoDatas[i];
	    		if(data.name == 'sEcho'){
	    			result.sEcho = data.value;
	    		}else if(data.name == 'iDisplayStart'){
	    			alertCondition.data.pagingCondition.startIndex = data.value;
	    		}else if(data.name == 'iDisplayLength'){
	    			alertCondition.data.pagingCondition.count = data.value;
	    		}else if(data.name == 'iSortCol_0'){
	    			alertCondition.data.sortKeyType = this._buildSortKeyType(data.value);
	    		}else if(data.name == 'sSortDir_0'){
	    			alertCondition.data.sortDirectType = this._buildSortDirectType(data.value);
	    		}else if(data.name == 'sSearch'){
		    		alertCondition.data.searchValue = data.value.trim();
		    	}
	    	}
	    	var userName = $('#userName').val();
	    	var userType = $('#userType option:selected').val();
	    	var status = $('#status').val();
	    	if(userName != ""){
	    		alertCondition.data.userName = userName;
	    	} else {
	    		alertCondition.data.userName = "";
	    	}
	    	if(userType != ""){
	    		alertCondition.data.userType = userType;
	    	}
	    	if(status != ""){
	    		alertCondition.data.status = status;
	    	}
	    	
	    	result.condition = alertCondition.data;
	    	return result;
	    },
	    _buildSortKeyType : function(index){
	    	var result = null;
	    	switch(index){
	    		case 4 :
	    			result = alertCondition.enumData.sortKeyType.create_time;
	    			break;
	    		case 5 :
	    			result = alertCondition.enumData.sortKeyType.update_time;
	    			break;
	    		default:
	    			if(console){
	    				console.log("_buildSortKeyType error,index=" + index);
	    			}
	    	}
	    	return result;
	    },
	    _buildSortDirectType : function(value){
	    	if(value == 'asc'){
	    		return alertCondition.enumData.sortDirectType.ascType;
	    	}else if(value == 'desc'){
	    		return alertCondition.enumData.sortDirectType.descType;
	    	}else {
	    		if(console){
					console.log("_buildSortDirectType error,value=" + index);
				}
	    	}
	    },
		_buildResultData : function(data,sEcho){
			var result ={
	    		sEcho : sEcho,
	    		iTotalRecords : null,
	    		iTotalDisplayRecords : null,
	    		aaData : []
	    	};
	    	if(!data.pageInfo){
	    		if(console){
	    			console.log("query error,data.pageInfo == null");
	    		}
	    		result.iTotalRecords = 0;
	        	result.iTotalDisplayRecords = 0;
	        	return result;
	    	}
	    	
	    	var countTotal = data.pageInfo.countTotal;
	    	result.iTotalRecords = countTotal;
	    	result.iTotalDisplayRecords = countTotal;
	    	var datas = data.datas;
	    	this._buildAaData(datas,result.aaData);
	    	
	    	return result;
		},
		 _buildAaData : function(datas,aaData){
	    	for(var i=0;i<datas.length;i++){
	    		var data = datas[i];
    			var rowData = this._buildRowData(data,(i +1));
    			aaData.push(rowData);
	    	}
	    },
	    _buildRowData : function(data,index){
	    	var rowData = [];
	    	rowData.push(index);
	    	rowData.push(data.userName);
	    	var userType = "";
			if(data.userType == "0"){
				userType = "普通用户";
			}else if(data.userType == "1"){
				userType = "管理员";
			}
	    	rowData.push(userType);
	    	var userStatus = "其他";
			if(data.status == "0"){
				userStatus = "禁用";
			}else if(data.status == "1"){
				userStatus = "启用";
			}
	    	rowData.push(userStatus);
	    	rowData.push(data.dateTime);
	    	rowData.push(data.updateTime);
	    	rowData.push('<a class="btn btn-default entypo-eye"  href=\'javascript: Modal._viewUserShow("'+data.userId+'");\' title="查看用户"></a>' +
	    			'&nbsp;&nbsp;'+ 
	    			'<a class="btn btn-default entypo-cog"  href=\'javascript: Modal._updateUserShow("'+data.userId+'");\' title="修改用户"></a>' +
	    	'&nbsp;&nbsp;'+ 
			'<a class="btn btn-default entypo-minus-circled"  href=\'javascript: Modal._delConfirm("'+data.userId+'");\' title="删除用户"></a>');
	    	return rowData;
	    }
};
var Modal = {
		_viewUserShow : function(userId){
			$('#viewUserTitle').html('查看用户');
			$.post(appbasepath + "/systemuser/viewuser?userId="+userId+"&time=" + (new Date().getTime()), function(result) {
				$('#userViewContent').html(result);
				$('#viewUser').modal('show', {backdrop: 'static'});
			});
		},
		_updateUserShow : function(userId){
			$('#updateUserTitle').html('更新用户');
			$.post(appbasepath + "/systemuser/toupdateuser?userId="+userId+"&time=" + (new Date().getTime()), function(result) {
				$('#userUpdateContent').html(result);
				$('#updateUser').modal('show', {backdrop: 'static'});
			});
		},
		_delConfirm : function(userId){
			$("#delUserId").val(userId);
			$('#delconfirm').modal('show', {backdrop: 'static'});
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
			$('#traceDetailModal').on('show.bs.modal', function () {
				Modal._beforeShow();
				$("#userSave")[0].reset();
			});
			$('#viewUser').on('show.bs.modal', function () {
				Modal._beforeShow();
			});
			$('#viewUser').on('hidden.bs.modal', function () {
				Modal._afterShow();
			});
			$('#updateUser').on('show.bs.modal', function () {
				Modal._beforeShow();
			});
			$('#updateUser').on('hidden.bs.modal', function () {
				Modal._afterShow();
			});
		},
		_updateUser:function(){
			var username = $('#username').val();
			var password = $('#password').val();
			var passwordcheck = $('#passwordcheck').val();
			
			var submit = true;
			if(username =="" || password==""){
				submit = false;
				bootbox.alert("请填写完必填项");
			}
			if(password != passwordcheck){
				submit = false;
				bootbox.alert("两次密码输入不一致");
			}
			var md5Password = hex_md5(password.trim());
			if(submit){
				var userIdObj = $('#userId').val();
				var request_url = "";
				var request_body = $("#updateForm").serialize();
				request_body = request_body + "&password="+md5Password;
				if(userIdObj == null || userIdObj == ""){
					request_url = "/systemuser/adduser";
					request_body = request_body.substring(7, request_body.length);
				}else {
					request_url = "/systemuser/updateuser";
				}
				$.post(appbasepath + request_url+"?time=" + (new Date().getTime()), request_body, function(result) {
					// 返回302时
					deal302(result);
					if (result.status == 'succeed') {
						tableContainer._fnAjaxUpdate();					
						$('#updateUser').modal('hide');
						successtoast();
					}else{
						console.log("result.alerts " + result.alerts);
						if(result.alerts != null){
							bootbox.alert(result.alerts);
						}
					}
				});
			}
		},
		_deleteUser:function(){
			var userIdStr = $("#delUserId").val();
			$.post(appbasepath + "/systemuser/deluser?time=" + (new Date().getTime())  + "&userid="+userIdStr , function(result) {
				// 返回302时
				deal302(result);
				if (result.status == 'succeed') {
					tableContainer._fnAjaxUpdate();					
					$('#delconfirm').modal('hide')
					successtoast();
					$("#delUserId").val("");
				}else{
					console.log("result.alerts " + result.alerts);
					if(result.alerts != null){
						alert(result.alerts);
					}
				}
				
			});
		}
};
