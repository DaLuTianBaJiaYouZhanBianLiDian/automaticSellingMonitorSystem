<!DOCTYPE html>
<html lang="en">
<head>
	<#include "../system/neonhead.ftl"/>
	<link rel="stylesheet" href="${base}/lib/neon/html/assets/js/zurb-responsive-tables/responsive-tables.css">
	<link rel="stylesheet" href="${base}/lib/neon/html/assets/js/selectboxit/jquery.selectBoxIt.css">
	<link rel="stylesheet" href="${base}/lib/neon/html/assets/js/daterangepicker/daterangepicker-bs3.css">
	<link rel="stylesheet" href="${base}/automaticSellingMonitorSystem/css/system/ztree.css" type="text/css">
	<link rel="stylesheet" href="${base}/lib/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body class="page-body  page-left-in">
	<div class="page-container horizontal-menu sidebar-collapsed">
		<#include "../system/sidebar.ftl"/>
		<div class="main-content">
			<div class="row">
				<form role="form" id="queryAlertform" class="form-horizontal form-groups-bordered">
					<div class="">
						<label class="col-sm-1 control-label">商品编码:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control"  id="commodity_code" name="commodity_code" >
						</div>
						<label class="col-sm-1 control-label">商品名称:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control"  id="commodity_name" name="commodity_name" >
						</div>
						<label class="col-sm-1 control-label">商品类型:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control"  id="commodity_type" name="commodity_type" >
						</div>
					</div>
					
					<div class="clear"></div>
					<br />
					
					<div class="">
						<label class="col-sm-1 control-label">供应商:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control"  id="commodity_network_supply" name="commodity_network_supply" >
						</div>
						<label class="col-sm-1 control-label">创建人:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control"  id="commodity_create_by" name="commodity_create_by" >
						</div>
						<label class="col-sm-1 control-label">更新人:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control"  id="commodity_update_by" name="commodity_update_by" >
						</div>
					</div>
					
					<div class="clear"></div>
					<br />
					
					<div class="">
						<label class="col-sm-1 control-label">商品备注:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control"  id="commodity_remark" name="commodity_remark" >
						</div>

						<div class="col-sm-1">
							<button class="btn btn-success" type="button" onclick="insert_function_table();">
							<i class="entypo-search">查询</i>
							</button>
						</div>
						<div class="col-sm-1">
							<button class="btn  btn-success" type="button" onclick="Modal._updateFunctionShow('');">								
							<i class="entypo-plus-circled">添加</i>
							</button>
						</div>
					</div>
				</form>
			</div>
			
			<div class="row">
				<div class="col-sm-12" >
					<table class="table table-hover table-bordered table-responsive" id="table_systemfunction">
						<thead>
							<tr width="100%">
								 <th width="5%">序号</th> 
							     <th width="5%">编码</th> 
							     <th width="5%">名称</th> 
							     <th width="5%">种类</th>
							     <th width="5%">单价</th> 
							     <th width="5%">会员价</th> 
							     <th width="5%">图片</th> 
							     <th width="5%">保质期</th> 
							     <th width="5%">每件总数</th> 
							     <th width="5%">网络报价</th> 
							     <th width="5%">网络url地址</th> 
							     <th width="5%">网络供应商</th> 
							     <th width="5%">创建时间</th> 
							     <th width="5%">创建人</th> 
							     <th width="5%">更新时间</th> 
							     <th width="5%">更新人</th> 
							     <th width="5%">备注说明</th>   
							     <th width="5%">操作</th>   
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			
		<!-- 网页底部 -->
		<#include "../system/neonfoot.ftl"/>
		</div>
	</div>
	
	<script src="${base}/automaticSellingMonitorSystem/js/system/sidebaract.js"></script>
	<script src="${base}/automaticSellingMonitorSystem/js/commodityInfo/commodityInfoIndex.js"></script>
	
	<script type="text/javascript"  src="${base}/lib/neon/html/assets/js/daterangepicker/moment-with-langs.js"></script>
	<script type="text/javascript"  src="${base}/lib/neon/html/assets/js/daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript"  src="${base}/lib/neon/html/assets/js/zurb-responsive-tables/responsive-tables.js"></script>
	<script src="${base}/lib/neon/html/assets/js/selectboxit/jquery.selectBoxIt.min.js"></script>
	
	<!-- Bottom Scripts -->
	<script src="${base}/lib/neon/html/assets/js/jquery.dataTables.min.js"></script>
	<script src="${base}/lib/neon/html/assets/js/datatables/TableTools.min.js"></script>
	<script src="${base}/lib/neon/html/assets/js/dataTables.bootstrap.js"></script>
	<script src="${base}/lib/neon/html/assets/js/datatables/jquery.dataTables.columnFilter.js"></script>
	<script src="${base}/lib/neon/html/assets/js/datatables/lodash.min.js"></script>
	<script src="${base}/lib/neon/html/assets/js/datatables/responsive/js/datatables.responsive.js"></script>
	<script src="${base}/lib/neon/html/assets/js/select2/select2.min.js"></script>
	<script src="${base}/lib/neon/html/assets/js/neon-chat.js"></script>
	<script src="${base}/lib/neon/html/assets/js/neon-demo.js"></script>
	<script src="${base}/lib/neon/html/assets/js/jquery.inputmask.bundle.min.js"></script>
	<script src="${base}/lib/neon/html/assets/js/jquery.validate.min.js"></script>
	
	<script type="text/javascript" src="${base}/lib/zTree_v3/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${base}/lib/zTree_v3/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${base}/lib/zTree_v3/js/jquery.ztree.exedit.js"></script>
	<script src="${base}/automaticSellingMonitorSystem/js/system/ztree_function.js"></script>
	
	<script>
		activeDiv();
	</script>
	<!-- 商品查看 -->
	<div id="viewFunction" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
		<div class="modal-dialog" style="width:600px;">
	      	<div class="modal-content">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="viewFunctionTitle">
		            </h4>
		         </div>
		         <div class="modal-body" id="functionViewContent" style="min-height:400px;">
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-info"  data-dismiss="modal">关闭
		            </button>
		         </div>
		  	</div>
		</div>
	</div>
	<!-- 商品添加和修改 -->
	<div id="addAndEditCommodityInfoView" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">
		<div class="modal-dialog" style="width:600px;">
	      	<div class="modal-content">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="addAndEditCommodityInfoTitle">
		            </h4>
		         </div>
		         <div class="modal-body" id="addAndEditCommodityInfoContent" style="min-height:400px;">
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-info"  data-dismiss="modal">关闭</button>
		         </div>
		  	</div>
		</div>
	</div>
	<!-- 商品删除 -->
	<div class="modal fade" id="delconfirm">
 		 <div class="modal-dialog">
  		  <div class="modal-content">
   		   <div class="modal-header">
     		   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       		 <h4 class="modal-title">用户删除确认</h4>
     		 </div>
     		 <div class="modal-body">
     		   <p>是否确认删除用户？</p>
     		   <input type="hidden" id="delFunctionId" name="functionId" />
      		</div>
     		 <div class="modal-footer">
     		 	
     		   <button type="button" class="btn btn-primary" onclick="Modal._deleteFunction()">确认</button>
     		   <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
     		 </div>
    		</div><!-- /.modal-content -->
 		 </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</body>
</html>