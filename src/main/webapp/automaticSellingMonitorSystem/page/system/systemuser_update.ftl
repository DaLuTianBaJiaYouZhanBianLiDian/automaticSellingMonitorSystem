<script src="${base}/lib/neon/html/assets/js/jquery.inputmask.bundle.min.js"></script>
<script src="${base}/lib/component/js-md5/js/md5.min.js"></script>
<script src="${base}/lib/component/bootbox.js"></script>
<!-- 更新用户 -->
<form role="form" id="updateForm" class="form-horizontal" class="validate">
		<div class="form-group">
			<label for="field-1" class="col-sm-3 control-label">用户名<span style="color:red">*<span></label>
			<div class="col-sm-5">
				<input type="hidden" name="userId" id="userId" class="form-control" value="${(sysuser.userId)!''}"  >
				<input type="text" name="userName" id="username" class="form-control" value="${(sysuser.userName)!''}"  >
			</div>
		</div>
		<#if "${(sysuser.userId)!''}" == "">
		<div class="form-group">
			<label for="field-3" class="col-sm-3 control-label">密码<span style="color:red">*<span></label>
			<div class="col-sm-5">
				<input type="password" id="password" class="form-control" placeholder="密码"  data-validate="required" data-message-required="This is custom message for required field.">
			</div>
		</div>
		<div class="form-group">
			<label for="field-3" class="col-sm-3 control-label">密码确认<span style="color:red">*<span></label>
			<div class="col-sm-5">
				<input type="password" class="form-control" id="passwordcheck" placeholder="密码确认"  data-validate="required" data-message-required="This is custom message for required field.">
			</div>
		</div>
		</#if>
		<#assign typeList = {"1":"管理员", "0":"普通用户"}>
		<div class="form-group">
			<label class="col-sm-3 control-label"  >用户类型<span style="color:red">*<span></label>
			<div class="col-sm-5">
				<select class="form-control"  name="userType">
				<#list typeList?keys as k >
					<#if (sysuser.userType!"") == k >
						<option value="${k}" selected>${typeList[k]}</option>
					<#else>
						<option value="${k}">${typeList[k]}</option>
					</#if>
				</#list>
				</select>
			</div>
		</div>
		
		<#assign statusList = {"1":"启用", "0":"禁用"}>
		<div class="form-group">
			<label class="col-sm-3 control-label">用户状态<span style="color:red">*<span></label>
			<div class="col-sm-5">
				<#list statusList?keys as k >
					<#if (sysuser.status!"") == k >
						<div class="radio">
							<label>
								<input type="radio" name="status"  id="status1" value="${k}" checked>${statusList[k]}
							</label>
						</div>
					<#else>
						<div class="radio">
							<label>
								<input type="radio" name="status"  id="status1" value="${k}" >${statusList[k]}
							</label>
						</div>
					</#if>
				</#list>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">选择角色<span style="color:red">*<span></label>
			<div class="col-sm-5">
				<select class="form-control"  name="selectRoleId">
				<option disabled="disabled">选择角色</option>
				<#if sysuser??>
					<#list sysuser.roles as role >
						<option value="${role.id}" <#if "${(role.isSelect)!''}" == "1">selected</#if>>${role.roleName}</option>
					</#list>
					</select>
				</#if>
			</div>
		</div>
		<div class="form-group">
        	<label class="col-sm-3 control-label">QQ号</label>
			<div class="col-sm-5">
				<input type="text" name="qq" class="form-control"  value="${(sysuser.qq)!''}" maxlength="11"  />
			</div>
		</div>
		<div class="form-group">
        	<label class="col-sm-3 control-label">微信</label>
			<div class="col-sm-5">
				<input type="text" name="weixin" class="form-control"  value="${(sysuser.weixin)!''}"   />
			</div>
		</div>
		
		<div class="form-group">
        	<label class="col-sm-3 control-label">手机号</label>
			<div class="col-sm-5">
				<input type="text" name="mobile"  class="form-control"  value="${(sysuser.mobile)!''}" maxlength="11" />
			</div>
		</div>
		<div class="form-group">
        	<label class="col-sm-3 control-label">固定电话</label>
			<div class="col-sm-5">
				<input type="text" name="phone" class="form-control"  value="${(sysuser.phone)!''}" maxlength="20" />
			</div>
		</div>
		
		<div class="form-group">
        	<label class="col-sm-3 control-label">电子邮件</label>
			<div class="col-sm-5">
				<input type="text" name="email"   class="form-control" value="${(sysuser.email)!''}" maxlength="100" />
			</div>
		</div>
		
		<div class="form-group">
			<center>
				<button type="button" class="btn btn-success" onclick="Modal._updateUser();">保存</button>
			</center>
		</div>
</form>