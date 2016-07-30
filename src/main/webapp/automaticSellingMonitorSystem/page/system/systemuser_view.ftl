<!-- 添加用户 -->
<form role="form" id="viewSave" class="form-horizontal" class="validate">
		<div class="form-group">
			<label for="field-1" class="col-sm-3 control-label">用户名${(sysuser.userName)!''}</label>
			
			<div class="col-sm-5">
				<input type="text" name="userName" class="form-control" value="${(sysuser.userName)!''}" readOnly="readOnly" id="field-1">
			</div>
		</div>
		
		<#assign typeList = {"1":"管理员", "0":"普通用户"}>
		<div class="form-group">
			<label class="col-sm-3 control-label"  >用户类型</label>
			<div class="col-sm-5">
				<select class="form-control" disabled="disabled" name="userType">
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
			<label class="col-sm-3 control-label">用户状态</label>
			<div class="col-sm-5">
				<#list statusList?keys as k >
					<#if (sysuser.status!"") == k >
						<div class="radio">
							<label>
								<input type="radio" name="status" disabled="disabled" id="status1" value="${k}" checked>${statusList[k]}
							</label>
						</div>
					<#else>
						<div class="radio">
							<label>
								<input type="radio" name="status" disabled="disabled" id="status1" value="${k}" >${statusList[k]}
							</label>
						</div>
					</#if>
				</#list>
			</div>
		</div>
		
		<div class="form-group">
        	<label class="col-sm-3 control-label">QQ号</label>
			<div class="col-sm-5">
				<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${(sysuser.qq)!''}" data-mask="9999999999" />
			</div>
		</div>
		<div class="form-group">
        	<label class="col-sm-3 control-label">微信</label>
			<div class="col-sm-5">
				<input type="text" name="weixin" class="form-control" readOnly="readOnly" value="${(sysuser.weixin)!''}" data-mask="9999999999"  />
			</div>
		</div>
		
		<div class="form-group">
        	<label class="col-sm-3 control-label">手机号</label>
			<div class="col-sm-5">
				<input type="text" name="mobile"  class="form-control" readOnly="readOnly" value="${(sysuser.mobile)!''}" data-mask="99999999999" />
			</div>
		</div>
		<div class="form-group">
        	<label class="col-sm-3 control-label">固定电话</label>
			<div class="col-sm-5">
				<input type="text" name="phone" class="form-control" readOnly="readOnly" value="${(sysuser.phone)!''}" data-mask="phone" />
			</div>
		</div>
		
		<div class="form-group">
        	<label class="col-sm-3 control-label">电子邮件</label>
			<div class="col-sm-5">
				<input type="text" name="email"  readOnly="readOnly" class="form-control" value="${(sysuser.email)!''}" data-mask="email" />
			</div>
		</div>
		<div class="form-group">
	    	<label class="col-sm-3 control-label">创建时间</label>
			<div class="col-sm-5">
				<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${sysuser.dateTime?string("yyyy-MM-dd HH:mm:ss")}"/>
			</div>
		</div>
		<div class="form-group">
	    	<label class="col-sm-3 control-label">更新时间</label>
			<div class="col-sm-5">
				<input type="text" name="qq" class="form-control" readOnly="readOnly" value="${sysuser.updateTime?string("yyyy-MM-dd HH:mm:ss")}"/>
			</div>
		</div>
</form>