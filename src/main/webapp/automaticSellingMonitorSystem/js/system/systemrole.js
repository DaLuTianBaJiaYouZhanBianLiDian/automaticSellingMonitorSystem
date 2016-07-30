var tableContainer;
var addRoleModal;
$(function() {
	moment.lang('zh-cn');
	insert_role_table();
	Modal._modalListener();  //模态框监听,修改body,防止页面抖动
	$('#table_systemrole_filter').hide();
	$('#table_systemrole_length').hide();
});

function insert_role_table(){
	console.log("insert_role_table-----");
	if(tableContainer == null){
		content_table.init(5, "desc");  //默认按更新时间倒序 排序
	}else {
		tableContainer.fnDraw(); 
	}
}
var content_table = {
		init: function(sortKey, sortType) {
			tableContainer = $("#table_systemrole");
			tableContainer.dataTable({
				"sPaginationType": "bootstrap",
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
				"sAjaxSource": appbasepath + "/systemuser/queryrolelist?time=" + (new Date().getTime()),
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
	    	var roleName = $('#roleName').val();
	    	var roleType = $('#roleType option:selected').val();
	    	if(roleName != ""){
	    		alertCondition.data.roleName = roleName;
	    	} else {
	    		alertCondition.data.roleName = "";
	    	}
	    	if(roleType != ""){
	    		alertCondition.data.roleType = roleType;
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
	    		var startIndex = alertCondition.data.pagingCondition.startIndex;
    			var rowData = this._buildRowData(data,(startIndex + i +1));
    			aaData.push(rowData);
	    	}
	    },
	    _buildRowData : function(data,index){
	    	var rowData = [];
	    	rowData.push(index);
	    	rowData.push(data.roleName);
	    	rowData.push(data.createUser);
	    	rowData.push(data.updateUser);
	    	rowData.push(data.createTime);
	    	rowData.push(data.updateTime);
	    	rowData.push('<a class="btn btn-default entypo-eye"  href=\'javascript: Modal._viewRoleShow("'+data.id+'");\' title="查看角色"></a>' +
	    			'&nbsp;&nbsp;'+ 
	    			'<a class="btn btn-default entypo-cog"  href=\'javascript: Modal._updateRoleShow("'+data.id+'");\' title="修改角色"></a>' +
	    	'&nbsp;&nbsp;'+ 
			'<a class="btn btn-default entypo-minus-circled"  href=\'javascript: Modal._delConfirm("'+data.id+'");\' title="删除角色"></a>');
	    	return rowData;
	    }
};
var Modal = {
		_viewRoleShow : function(roleId){
			$('#viewRoleTitle').html('查看角色');
			$.post(appbasepath + "/systemuser/viewrole?roleId="+roleId+"&time=" + (new Date().getTime()), function(result) {
				$('#userViewContent').html(result);
				$('#viewRole').modal('show', {backdrop: 'static'});
				initRoleFunctionTree(roleId, "view");
			});
		},
		_updateRoleShow : function(roleId){
			$('#updateRoleTitle').html('更新角色');
			$.post(appbasepath + "/systemuser/toupdaterole?roleId="+roleId+"&time=" + (new Date().getTime()), function(result) {
				$('#userUpdateContent').html(result);
				$('#updateRole').modal('show', {backdrop: 'static'});
				initRoleFunctionTree(roleId);
			});
		},
		_delConfirm : function(userId){
			$("#delRoleId").val(userId);
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
			$('#addRoleModal').on('show.bs.modal', function () {
				Modal._beforeShow();
				$("#roleSave")[0].reset();
			});
			$('#viewRole').on('show.bs.modal', function () {
				Modal._beforeShow();
			});
			$('#viewRole').on('hidden.bs.modal', function () {
				Modal._afterShow();
			});
			$('#updateRole').on('show.bs.modal', function () {
				Modal._beforeShow();
			});
			$('#updateRole').on('hidden.bs.modal', function () {
				Modal._afterShow();
			});
		},
//		_saveUser:function(){
//			$.post(appbasepath + "/systemuser/addrole?time=" + (new Date().getTime()), $("#roleSave").serialize(), function(result) {
//				// 返回302时
//				deal302(result);
//				if (result.status == 'succeed') {
//					tableContainer._fnAjaxUpdate();					
//					$('#addRoleModal').modal('hide');
//					successtoast();
//				}
//				
//			});
//		},
		_updateRole:function(){
			var roleName = $('#roleName').val();
			var nameSel = $('#nameSel').html();
			
			var submit = true;
			if(roleName =="" || nameSel==""){
				submit = false;
				alert("请填写完必填项");
			}
			
			if(submit){
				var idObj = $('#roleId').val();
				var request_url = "";
				var request_body = $("#updateForm").serialize();
				if(idObj == null || idObj == ""){
					request_url = "/systemuser/addrole";
					request_body = request_body.substring(3, request_body.length-1);
				}else {
					request_url = "/systemuser/updaterole";
				}
				$.post(appbasepath + request_url+"?time=" + (new Date().getTime()), request_body, function(result) {
					// 返回302时
					deal302(result);
					if (result.status == 'succeed') {
						tableContainer._fnAjaxUpdate();					
						$('#updateRole').modal('hide');
						successtoast();
					}else{
						console.log("result.alerts " + result.alerts);
						if(result.alerts != null){
							alert(result.alerts);
						}
					}
				});
			}
		},
		_deleteUser:function(){
			var roleIdStr = $("#delRoleId").val();
			$.post(appbasepath + "/systemuser/deleterole?time=" + (new Date().getTime())  + "&roleId="+roleIdStr , function(result) {
				// 返回302时
				deal302(result);
				if (result.status == 'succeed') {
					tableContainer._fnAjaxUpdate();					
					$('#delconfirm').modal('hide')
					successtoast();
					$("#delRoleId").val("");
				}else {
					console.log("result.alerts " + result.alerts);
					if(result.alerts != null){
						alert(result.alerts);
					}
				}
			});
		}
};
