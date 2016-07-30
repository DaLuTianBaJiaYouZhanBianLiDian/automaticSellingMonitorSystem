<!-- 添加角色-->
<form role="form" id="viewSave" class="form-horizontal" class="validate">
	<div class="form-group">
		<label for="field-1" class="col-sm-3 control-label">角色名称</label>
		
		<div class="col-sm-5">
			<input type="text" name="userName" class="form-control" value="${(sysrole.roleName)!''}" readOnly="readOnly" id="field-1">
		</div>
	</div>
	<div class="form-group">
    	<label class="col-sm-3 control-label">选择权限</label>
		<div class="col-sm-5">
			<textarea data-rule-maxlength="200" name="" id="nameSel_view" class="form-control"rows="12" readonly onclick="showMenu();"></textarea>
		</div>
	</div>
	<!--
	<div class="form-group">
    	<label class="col-sm-3 control-label">角色描述</label>
		<div class="col-sm-5">
			<textarea data-rule-maxlength="200" name="roleDesc" id="roleDesc" readOnly="readOnly" class="form-control"rows="3">${(sysrole.roleDesc)!''}</textarea>
		</div>
	</div>
	-->
	<div class="form-group">
    	<label class="col-sm-3 control-label">创建操作员</label>
		<div class="col-sm-5">
			<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${(sysrole.createUser)!''}" data-mask="9999999999" />
		</div>
	</div>
	<div class="form-group">
    	<label class="col-sm-3 control-label">更新操作员</label>
		<div class="col-sm-5">
			<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${(sysrole.updateUser)!''}" data-mask="9999999999" />
		</div>
	</div>
	<div class="form-group">
    	<label class="col-sm-3 control-label">创建时间</label>
		<div class="col-sm-5">
			<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${sysrole.createTime?string("yyyy-MM-dd HH:mm:ss")}"/>
		</div>
	</div>
	<div class="form-group">
    	<label class="col-sm-3 control-label">更新时间</label>
		<div class="col-sm-5">
			<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${sysrole.updateTime?string("yyyy-MM-dd HH:mm:ss")}"/>
		</div>
	</div>
</form>