
<script src="${base}/lib/neon/html/assets/js/jquery.inputmask.bundle.min.js"></script>
	
<!-- 增加和修改商品的信息页面-->
<form role="form" id="updateForm" class="form-horizontal" class="validate">
	<div class="form-group">
		<label for="field-1" class="col-sm-3 control-label">菜单名称<span style="color:red">*<span></label>
		<div class="col-sm-5">
			<input type="hidden" name="id" id="id" class="form-control" value="${(sysfunction.id)!''}"  >
			<input type="text" name="function_name" id="function_name" class="form-control required" value="${(sysfunction.function_name)!''}" >
		</div>
	</div>
	<div class="form-group">
		<label for="field-1" class="col-sm-3 control-label">菜单链接<span style="color:red">*<span></label>
		<div class="col-sm-5">
			<input type="text" name="function_url" class="form-control" value="${(sysfunction.function_url)!''}">
		</div>
	</div>
	<div class="form-group">
		<label for="field-1" class="col-sm-3 control-label">菜单图标</label>
		<div class="col-sm-5">
			<input type="text" name="function_icon" class="form-control" value="${(sysfunction.function_icon)!''}">
		</div>
	</div>
	<#assign typeList = {"1":"一级菜单", "2":"二级菜单", "3":"三级菜单"}>
	<div class="form-group">
		<label class="col-sm-3 control-label">菜单类型<span style="color:red">*<span></label>
		<div class="col-sm-5">
			<select class="form-control"  name="function_type">
			<#list typeList?keys as k >
				<#if sysfunction??>
					<option value="${k}" <#if (sysfunction.function_type!"") == k >selected</#if>>${typeList[k]}</option>
				<#else>
					<option value="${k}">${typeList[k]}</option>
				</#if>
			</#list>
			</select>
		</div>
	</div>
	<div class="form-group">
    	<label class="col-sm-3 control-label">选择上级菜单<span style="color:red">*<span></label>
		<div class="col-sm-5">
			<input type="text"  name="nameMenuSel" id="nameMenuSel" class="form-control" readonly onclick="showMenu();">
			<input type="hidden" id="super_id" name="super_id" value="" />
			<div id="menuContent" class="menuContent" style=" display:none; position: absolute;left: 15px;top: 30px;z-index: 1;">
				<ul id="treeMenu" class="ztree" style="margin-top:0; width:206px; height: 200px;"></ul>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label">菜单排序<span style="color:red">*<span></label>
		<div class="col-sm-5">
			<input type="text" name="showOrder" id="showOrder" class="form-control"  value="${(sysfunction.showOrder)!''}">
		</div>
	</div>
	<!--
	<div class="form-group">
    	<label class="col-sm-3 control-label">菜单描述</label>
		<div class="col-sm-5">
			<textarea data-rule-maxlength="200" name="function_desc" id="function_desc" class="form-control" rows="3">${(sysfunction.function_desc)!''}</textarea>
		</div>
	</div>
	-->
	<div class="form-group">
		<center>
			<button type="button" class="btn btn-success" onclick="Modal._updateFunction();">保存</button>
		</center>
	</div>
	
</form>
<script>
</script>