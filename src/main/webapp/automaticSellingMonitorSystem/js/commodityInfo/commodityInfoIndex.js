var tableContainer;
var traceDetailModal;
$(function() {
	moment.lang('zh-cn');
	insert_function_table();
	Modal._modalListener(); //模态框监听,修改body,防止页面抖动
	$('#table_systemfunction_filter').hide();
	$('#table_systemfunction_length').hide();
});

//初始化表单，向表单插入数据
function insert_function_table() {
	if (tableContainer == null) {
		content_table.init(7, "desc"); //默认按开始时间倒序 排序
	} else {
		tableContainer.fnDraw();
	}
}

//创建表单
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
			"aoColumns" : [ 
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false },
				{ "bSortable" : false }
			],
			"bStateSave" : true,
			"bProcessing" : true,
			"bServerSide" : true, //指定从服务器端获取数据
			"sAjaxSource" : appbasepath + "/commodityInfo/queryCommodityInfoList?time="
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
		//商品编码
		var commodity_code = $('#commodity_code').val();
		if (commodity_code != "") {
			alertCondition.data.commodity_code = commodity_code;
		} else {
			alertCondition.data.commodity_code = "";
		}
		//商品编码
		var commodity_code = $('#commodity_code').val();
		if (commodity_code != "") {
			alertCondition.data.commodity_code = commodity_code;
		} else {
			alertCondition.data.commodity_code = "";
		}
		//商品名称
		var commodity_name = $('#commodity_name').val();
		if (commodity_name != "") {
			alertCondition.data.commodity_name = commodity_name;
		} else {
			alertCondition.data.commodity_name = "";
		}
		//商品种类
		var commodity_type = $('#commodity_type').val();
		if (commodity_type != "") {
			alertCondition.data.commodity_type = commodity_type;
		} else {
			alertCondition.data.commodity_type = "";
		}
		//商品供应商
		var commodity_network_supply = $('#commodity_network_supply').val();
		if (commodity_network_supply != "") {
			alertCondition.data.commodity_network_supply = commodity_network_supply;
		} else {
			alertCondition.data.commodity_network_supply = "";
		}
		//商品创建人
		var commodity_create_by = $('#commodity_create_by').val();
		if (commodity_create_by != "") {
			alertCondition.data.commodity_create_by = commodity_create_by;
		} else {
			alertCondition.data.commodity_create_by = "";
		}
		//商品更新人
		var commodity_update_by = $('#commodity_update_by').val();
		if (commodity_update_by != "") {
			alertCondition.data.commodity_update_by = commodity_update_by;
		} else {
			alertCondition.data.commodity_update_by = "";
		}
		//商品备注
		var commodity_remark = $('#commodity_remark').val();
		if (commodity_remark != "") {
			alertCondition.data.commodity_remark = commodity_remark;
		} else {
			alertCondition.data.commodity_remark = "";
		}
		//整合条件
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
	_buildResultData : function(data, sEcho) {			//根据异步请求数据，创建表单
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
	_buildAaData : function(datas, aaData) {			//创建表格，填充数据
		for (var i = 0; i < datas.length; i++) {
			var data = datas[i];
			var startIndex = alertCondition.data.pagingCondition.startIndex;
			var rowData = this._buildRowData(data, (startIndex + i + 1));
			aaData.push(rowData);
		}
	},
	_buildRowData : function(data, index) {			//创建行,填充数据
		var rowData = [];
		rowData.push(index);
		rowData.push(data.commodity_code);
		rowData.push(data.commodity_name);
		rowData.push(data.commodity_type);
		rowData.push(data.commodity_unit_price);
		rowData.push(data.commodity_vip_price);
		rowData.push(data.commodity_image_path);
		rowData.push(data.commodity_quality_guarantee_period);
		rowData.push(data.commodity_eche_one_total_num);
		rowData.push(data.commodity_network_quotation);
		rowData.push(data.commodity_network_url);
		rowData.push(data.commodity_network_supply);
		rowData.push(data.commodity_create_date);
		rowData.push(data.commodity_create_by);
		rowData.push(data.commodity_update_date);
		rowData.push(data.commodity_update_by);
		rowData.push(data.commodity_remark);
		rowData.push('<a class="btn btn-default entypo-eye"  href=\'javascript: Modal._viewFunctionShow("'
				+ data.commodity_code
				+ '");\' title="查看菜单"></a>'
				+ '&nbsp;&nbsp;'
				+ '<a class="btn btn-default entypo-cog"  href=\'javascript: Modal._updateFunctionShow("'
				+ data.commodity_code
				+ '");\' title="修改菜单"></a>'
				+ '&nbsp;&nbsp;'
				+ '<a class="btn btn-default entypo-minus-circled"  href=\'javascript: Modal._delConfirm("'
				+ data.commodity_code 
				+ '");\' title="删除菜单"></a>');
		return rowData;
	}
};
var Modal = {
	_viewFunctionShow : function(functionId) {
		$('#viewFunctionTitle').html('商品查看');
		$.post(appbasepath + "/systemuser/viewfunction?functionId="
				+ functionId + "&time=" + (new Date().getTime()), function(
				result) {
			$('#functionViewContent').html(result);
			$('#viewFunction').modal('show', {
				backdrop : 'static'
			});
		});
	},
	_updateFunctionShow : function(functionId) {			//商品修改和商品添加
		console.log("商品添加");
		$('#addAndEditCommodityInfoTitle').html('商品添加');
		$('#addAndEditCommodityInfoView').modal('show', {
			backdrop : 'static'
		});
		initFunctionTree(functionId);
		$.post(appbasepath + "/commodityInfo/addAndEditCommodityInfo", 
			function(result) {
				$('#addAndEditCommodityInfoContent').html(result);
			}
		);
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
