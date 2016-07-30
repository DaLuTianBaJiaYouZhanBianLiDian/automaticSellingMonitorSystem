var tableContainer;
var traceDetailModal;
$(function() {
	moment.lang('zh-cn');
	insert_function_table();
	Modal._modalListener(); //模态框监听,修改body,防止页面抖动
	$('#table_systemfunction_filter').hide();
	$('#table_systemfunction_length').hide();

});

function insert_function_table() {
	if (tableContainer == null) {
		content_table.init(7, "desc"); //默认按开始时间倒序 排序
	} else {
		tableContainer.fnDraw();
	}
}
var content_table = {
	init : function(sortKey, sortType) {
		tableContainer = $("#table_systemfunction");
		tableContainer.dataTable({
			"sPaginationType" : "bootstrap",
		  //"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
			'iDisplayLength' : 10,
			"oLanguage" : {
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "前一页",
					"sNext" : "下一页",
					"sLast" : "尾页"
				},
				"sLengthMenu" : "每页显示: " + " _MENU_ " + "条",
				"sZeroRecords" : "没有数据",
				"sInfo" : "_START_ - _END_ " + "总" + "(_TOTAL_)",
				"sInfoEmpty" : "0 - 0 " + "总" + "： 0",
				"sSearch" : "快速查询:  ",
				"sSearchPlaceholder" : "123",
				"sProcessing" : "正在加载中"
			},
			"bFilter" : true,
			"aaSorting" : [ [ sortKey, sortType ] ],
			"aoColumns" : [ {
				"bSortable" : false
			}, {
				"bSortable" : false
			}, {
				"bSortable" : false
			}, {
				"bSortable" : false
			}, {
				"bSortable" : false
			}, {
				"bSortable" : false
			}, {
				"sType" : "string"
			}, {
				"sType" : "string"
			}, {
				"bSortable" : false
			} ],
			"bStateSave" : true,
			"bProcessing" : true,
			"bServerSide" : true, //指定从服务器端获取数据
			"sAjaxSource" : appbasepath + "/systemuser/queryfunctionlist?time="
					+ (new Date().getTime()),
			"fnServerData" : function(sSource, aoData, fnCallback) {
				var result = alertCondition._buildContentCondition(aoData);
				var conditionStr = JSON.stringify(result.condition);
				$.ajax({
					dataType : 'json',
					type : "POST",
					url : sSource,
					data : {
						conditionStr : conditionStr
					},
					success : function(data, sEcho) {
						if (data.status == 'succeed') {
							var resultData = alertCondition._buildResultData(
									data, sEcho);
							console.log("================="+fnCallback+"==================");
							console.log(resultData);
							fnCallback(resultData); //服务器端返回的对象的returnObject部分是要求的格式
						} else {
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
	initData : function(data) {
		this.data = {};
		this.data.sortKeyType = data.sortKeyType
				|| this.enumData.sortKeyType.start_time;
		this.data.sortDirectType = data.sortDirectType
				|| this.enumData.sortDirectType.descType;
		this.data.pagingCondition = {};
		this.data.pagingCondition.startIndex = data.startIndex || 0;
		this.data.pagingCondition.count = data.count || 10;
		this.data.searchValue = data.searchValue || null;
	},
	setContentID : function(contentID) {
		this.data.contentID = contentID;
	},
	setSearchValue : function(searchValue) {
		this.data.searchValue = searchValue;
	},
	_buildContentCondition : function(aoDatas) {
		var result = {
			sEcho : null,
			condition : null
		};
		if (!window.alertCondition.data) {
			alertCondition.initData({});
		}
		for (var i = 0; i < aoDatas.length; i++) {
			var data = aoDatas[i];
			if (data.name == 'sEcho') {
				result.sEcho = data.value;
			} else if (data.name == 'iDisplayStart') {
				alertCondition.data.pagingCondition.startIndex = data.value;
			} else if (data.name == 'iDisplayLength') {
				alertCondition.data.pagingCondition.count = data.value;
			} else if (data.name == 'iSortCol_0') {
				alertCondition.data.sortKeyType = this
						._buildSortKeyType(data.value);
			} else if (data.name == 'sSortDir_0') {
				alertCondition.data.sortDirectType = this
						._buildSortDirectType(data.value);
			} else if (data.name == 'sSearch') {
				alertCondition.data.searchValue = data.value.trim();
			}
		}
		var functionName = $('#functionName').val();
		if (functionName != "") {
			alertCondition.data.function_name = functionName;
		} else {
			alertCondition.data.function_name = "";
		}
		result.condition = alertCondition.data;
		return result;
	},
	_buildSortKeyType : function(index) {
		var result = null;
		switch (index) {
		case 6:
			result = alertCondition.enumData.sortKeyType.create_time;
			break;
		case 7:
			result = alertCondition.enumData.sortKeyType.update_time;
			break;
		default:
			if (console) {
				console.log("_buildSortKeyType error,index=" + index);
			}
		}
		return result;
	},
	_buildSortDirectType : function(value) {
		if (value == 'asc') {
			return alertCondition.enumData.sortDirectType.ascType;
		} else if (value == 'desc') {
			return alertCondition.enumData.sortDirectType.descType;
		} else {
			if (console) {
				console.log("_buildSortDirectType error,value=" + index);
			}
		}
	},
	_buildResultData : function(data, sEcho) {
		var result = {
			sEcho : sEcho,
			iTotalRecords : null,
			iTotalDisplayRecords : null,
			aaData : []
		};
		if (!data.pageInfo) {
			if (console) {
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
		this._buildAaData(datas, result.aaData);

		return result;
	},
	_buildAaData : function(datas, aaData) {
		for (var i = 0; i < datas.length; i++) {
			var data = datas[i];
			var startIndex = alertCondition.data.pagingCondition.startIndex;
			var rowData = this._buildRowData(data, (startIndex + i + 1));
			aaData.push(rowData);
		}
	},
	_buildRowData : function(data, index) {
		var rowData = [];
		rowData.push(index);
		rowData.push(data.function_name);

		var typeStr = "";
		var type = data.function_type;
		if (type == 1) {
			typeStr = "一级菜单";
		} else if (type == 2) {
			typeStr = "二级菜单";
		} else {
			typeStr = "三级菜单";
		}
		rowData.push(typeStr);
		rowData.push(data.function_url);

		rowData.push(data.create_user);
		rowData.push(data.update_user);
		rowData.push(data.create_time);
		rowData.push(data.update_time);
		rowData
				.push('<a class="btn btn-default entypo-eye"  href=\'javascript: Modal._viewFunctionShow("'
						+ data.id
						+ '");\' title="查看菜单"></a>'
						+ '&nbsp;&nbsp;'
						+ '<a class="btn btn-default entypo-cog"  href=\'javascript: Modal._updateFunctionShow("'
						+ data.id
						+ '");\' title="修改菜单"></a>'
						+ '&nbsp;&nbsp;'
						+ '<a class="btn btn-default entypo-minus-circled"  href=\'javascript: Modal._delConfirm("'
						+ data.id + '");\' title="删除菜单"></a>');
		return rowData;
	}
};
var Modal = {
	_viewFunctionShow : function(functionId) {
		$('#viewFunctionTitle').html('查看菜单');
		$.post(appbasepath + "/systemuser/viewfunction?functionId="
				+ functionId + "&time=" + (new Date().getTime()), function(
				result) {
			$('#functionViewContent').html(result);
			$('#viewFunction').modal('show', {
				backdrop : 'static'
			});
		});
	},
	_updateFunctionShow : function(functionId) {
		console.log("functionId = " + functionId);
		$('#updateFunctionTitle').html('更新菜单');
		$('#updateFunction').modal('show', {
			backdrop : 'static'
		});
		initFunctionTree(functionId);
		$.post(appbasepath + "/systemuser/toupdatefunction?functionId="
				+ functionId + "&time=" + (new Date().getTime()), function(
				result) {
			$('#functionUpdateContent').html(result);
		});
	},
	_delConfirm : function(functionId) {
		$("#delFunctionId").val(functionId);
		$('#delconfirm').modal('show', {
			backdrop : 'static'
		});
	},
	_beforeShow : function() {
		var body_scrollHeight = $('body')[0].scrollHeight;
		var docHeight = document.documentElement.clientHeight;
		if (body_scrollHeight > docHeight) {
			$('body').css({
				'overflow' : 'hidden',
				'margin-right' : '17px'
			});
		} else {
			$('body').css({
				'overflow' : 'auto',
				'margin-right' : '0'
			});
		}
	},
	_afterShow : function() {
		$('body').css({
			'overflow' : 'auto',
			'margin-right' : '0'
		});
	},
	_modalListener : function() {
		$('#addFunctionModal').on('show.bs.modal', function() {
			Modal._beforeShow();
			$("#functionSave")[0].reset();
		});
		$('#viewFunction').on('show.bs.modal', function() {
			Modal._beforeShow();
		});
		$('#viewFunction').on('hidden.bs.modal', function() {
			Modal._afterShow();
		});
		$('#updateFunction').on('show.bs.modal', function() {
			Modal._beforeShow();
		});
		$('#updateFunction').on('hidden.bs.modal', function() {
			Modal._afterShow();
		});
	},
	_updateFunction : function() {
		var function_name = $('#function_name').val();
		var function_url = $('#function_url').val();
		var nameMenuSel = $('#nameMenuSel').val();
		var showOrder = $('#showOrder').val();

		var submit = true;
		if (function_name == "" || function_url == "" || showOrder == "") {
			submit = false;
			alert("请填写完必填项");
		}
		if (submit) {
			var idObj = $('#id').val();
			var request_url = "";
			var request_body = $("#updateForm").serialize();
			if (idObj == null || idObj == "") {
				request_url = "/systemuser/addfunction";
				request_body = request_body.substring(3, request_body.length);
			} else {
				request_url = "/systemuser/updatefucntion";
			}
			$.post(appbasepath + request_url + "?time="
					+ (new Date().getTime()), request_body, function(result) {
				// 返回302时
				deal302(result);
				if (result.status == 'succeed') {
					tableContainer._fnAjaxUpdate();
					$('#updateFunction').modal('hide');
					successtoast();
				} else {
					console.log("result.alerts " + result.alerts);
					if (result.alerts != null) {
						alert(result.alerts);
					}
				}
			});
		}
	},
	_deleteFunction : function() {
		var functionIdStr = $("#delFunctionId").val();
		console.log("functionIdStr :" + functionIdStr);
		$.post(appbasepath + "/systemuser/deletefucntion?time="
				+ (new Date().getTime()) + "&functionId=" + functionIdStr,
				function(result) {
					// 返回302时
					deal302(result);
					if (result.status == 'succeed') {
						tableContainer._fnAjaxUpdate();
						$('#delconfirm').modal('hide')
						successtoast();
						$("#delFunctionId").val("");
					} else {
						console.log("result.alerts " + result.alerts);
						if (result.alerts != null) {
							alert(result.alerts);
						}
					}
				});
	}
};
