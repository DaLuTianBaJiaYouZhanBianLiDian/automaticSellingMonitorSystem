<!-- 预览菜单 -->
<form role="form" id="viewSave" class="form-horizontal" class="validate">
	<div class="form-group">
		<label for="field-1" class="col-sm-3 control-label">菜单名称</label>
		<div class="col-sm-5">
			<input type="text" name="userName" class="form-control" value="${(sysfunction.function_name)!''}" readOnly="readOnly" id="field-1">
		</div>
	</div>
	<div class="form-group">
		<label for="field-1" class="col-sm-3 control-label">菜单链接</label>
		<div class="col-sm-5">
			<input type="text" name="function_url" class="form-control"  readOnly="readOnly" value="${(sysfunction.function_url)!''}">
		</div>
	</div>
	<div class="form-group">
		<label for="field-1" class="col-sm-3 control-label">菜单图标</label>
		<div class="col-sm-5">
			<input type="text" name="function_icon" class="form-control"  readOnly="readOnly" value="${(sysfunction.function_icon)!''}">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label"  >菜单类型</label>
		<div class="col-sm-5">
			<select class="form-control" disabled="disabled" name="userType">
			<#if '${sysfunction.function_type}' == '1' >
				<option value="${sysfunction.function_type}" selected>一级菜单</option>
			<#elseif '${sysfunction.function_type}' == '2' >
				<option value="${sysfunction.function_type}" selected>二级菜单</option>
			<#else>
				<option value="${sysfunction.function_type}" selected>三级菜单</option>
			</#if>
			</select>
		</div>
	</div>
	
	<div class="form-group">
    	<label class="col-sm-3 control-label">上级菜单</label>
		<div class="col-sm-5">
			<input type="text" name="parentFunctionName" class="form-control" readOnly="readOnly" value="${(sysfunction.parentFunctionName)!''}"  />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label">菜单排序</label>
		<div class="col-sm-5">
			<input type="text" name="showOrder" class="form-control"  readOnly="readOnly" value="${(sysfunction.showOrder)!''}"  ">
		</div>
	</div>
	<!--
	<div class="form-group">
    	<label class="col-sm-3 control-label">菜单描述</label>
		<div class="col-sm-5">
			<textarea data-rule-maxlength="200" name="roleDesc" id="roleDesc" readOnly="readOnly" class="form-control"rows="3">${(sysfunction.function_desc)!''}</textarea>
		</div>
	</div>
	-->
	<div class="form-group">
    	<label class="col-sm-3 control-label">创建操作员</label>
		<div class="col-sm-5">
			<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${(sysfunction.create_user)!''}" data-mask="9999999999" />
		</div>
	</div>
	<div class="form-group">
    	<label class="col-sm-3 control-label">更新操作员</label>
		<div class="col-sm-5">
			<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${(sysfunction.update_user)!''}" data-mask="9999999999" />
		</div>
	</div>
	<div class="form-group">
    	<label class="col-sm-3 control-label">创建时间</label>
		<div class="col-sm-5">
			<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${sysfunction.create_time?string("yyyy-MM-dd HH:mm:ss")}"/>
		</div>
	</div>
	<div class="form-group">
    	<label class="col-sm-3 control-label">更新时间</label>
		<div class="col-sm-5">
			<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${sysfunction.update_time?string("yyyy-MM-dd HH:mm:ss")}"/>
		</div>
	</div>
</form>