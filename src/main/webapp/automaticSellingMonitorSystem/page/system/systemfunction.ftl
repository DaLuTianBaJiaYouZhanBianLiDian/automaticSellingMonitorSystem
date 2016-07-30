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
						<label class="col-sm-1 control-label">菜单名称:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control"  id="functionName" name="function_name" >
						</div>
						
						<div class="col-sm-1">
							<button class="btn btn-success" type="button" onclick="insert_function_table();">
								<i class="entypo-search">查询</i>
							</button>
						</div>
						<div class="col-sm-1">
							<button class="btn btn-success" type="button" onclick="Modal._updateFunctionShow('');">
								<i class="entypo-user-add">添加</i>
							</button>
						</div>
					</div>
				</form>
			</div>
			
			<div class="row">
				<div class="col-sm-12" >
					<table class="table table-hover table-bordered table-responsive" id="table_systemfunction">
						<thead>
							<tr>
								 <th width="5%">序号</th> 
							     <th width="10%">菜单名称</th> 
							     <th width="10%">菜单类型</th> 
							     <th width="10%">链接</th>
							     <th width="10%">创建操作员</th> 
							     <th width="10%">更新操作员</th> 
							     <th width="15%">创建时间</th> 
							     <th width="15%">更新时间</th> 
							     <th width="15%">操作</th>
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
	
	
	
	<script type="text/javascript"  src="${base}/lib/neon/html/assets/js/daterangepicker/moment-with-langs.js"></script>
	<script type="text/javascript"  src="${base}/lib/neon/html/assets/js/daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript"  src="${base}/lib/neon/html/assets/js/zurb-responsive-tables/responsive-tables.js"></script>
	<script src="${base}/lib/neon/html/assets/js/selectboxit/jquery.selectBoxIt.min.js"></script>
	<script src="${base}/automaticSellingMonitorSystem/js/system/sidebaract.js"></script>
	<script src="${base}/automaticSellingMonitorSystem/js/system/systemfunction.js"></script>
	
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
	<!-- 预览菜单 -->
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
	<!-- 更新菜单 -->
	<div id="updateFunction" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">
		<div class="modal-dialog" style="width:600px;">
	      	<div class="modal-content">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="updateFunctionTitle">
		            </h4>
		         </div>
		         <div class="modal-body" id="functionUpdateContent" style="min-height:400px;">
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-info"  data-dismiss="modal">关闭
		            </button>
		         </div>
		  	</div>
		</div>
	</div>
	
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