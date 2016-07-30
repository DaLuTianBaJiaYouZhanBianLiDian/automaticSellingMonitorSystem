
<script src="${base}/lib/neon/html/assets/js/jquery.inputmask.bundle.min.js"></script>
	
<!-- 更新角色-->
<form role="form" id="updateForm" class="form-horizontal" class="validate">
	<div class="form-group">
		<label for="field-1" class="col-sm-3 control-label">角色名称<span style="color:red">*<span></label>
		<div class="col-sm-5">
			<input type="hidden" name="id" id="roleId" class="form-control" value="${(sysrole.id)!''}"  >
			<input type="text" name="roleName" id="roleName" class="form-control" value="${(sysrole.roleName)!''}"  >
		</div>
	</div>
	<div class="form-group">
    	<label class="col-sm-3 control-label">选择权限<span style="color:red">*<span></label>
		<div class="col-sm-5">
			<textarea data-rule-maxlength="200" name="nameSel" id="nameSel" class="form-control"rows="12" readonly onclick="showMenu();"></textarea>
			<input type="hidden" id="selectIds" name="selectFuncrionIds" value="" />
			<div id="menuContent" class="menuContent" style="display:none; position: absolute;left: 220px;top: 0px;">
				<ul id="treeDemo" class="ztree" style="margin-top:0; width:206px; height: 220px;"></ul>
			</div>
		</div>
	</div>
	<!--
	<div class="form-group">
    	<label class="col-sm-3 control-label">角色描述</label>
		<div class="col-sm-5">
			<textarea data-rule-maxlength="200" name="roleDesc" id="roleDesc" class="form-control"rows="3">${(sysrole.roleDesc)!''}</textarea>
		</div>
	</div>-->
	 
	<div class="form-group">
		<center>
			<button type="button" class="btn btn-success" onclick="Modal._updateRole();">保存</button>
		</center>
	</div>
	
</form>
