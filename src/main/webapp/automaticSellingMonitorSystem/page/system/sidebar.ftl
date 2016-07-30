<header class="navbar navbar-fixed-top"><!-- set fixed position by adding class "navbar-fixed-top" -->
	<div class="navbar-inner">
		<!-- logo -->
		<div class="navbar-brand">
			<font style="color:#fff;font-size: 18px;font-weight: bold;">
				${Session["SYS_NAME"]!'aaaa'}
			</font>
		</div>
		<!-- menu -->
		<ul class="navbar-nav" style="margin-left: 120px;">
			<#list Session["SYS_PERMISSION_MENUS"] as firstlevelmenus>
				<#if '${firstlevelmenus.function_type}'=='1'>
					<li id="${firstlevelmenus.id}">
						<a href="${base}${firstlevelmenus.function_url}"  class="${firstlevelmenus.function_icon!''}">
							<span>${firstlevelmenus.function_name}</span>
						</a>
						<#if (firstlevelmenus.childFunctions?size > 0)>
						<ul>
							<#list firstlevelmenus.childFunctions as secondlevelmenus>
								<#if '${secondlevelmenus.function_type}'=='2'>
									<li id="${secondlevelmenus.id}">
										<a href="${base}${secondlevelmenus.function_url!''}">
										 	<i class="${secondlevelmenus.function_icon!''}"></i>
											<span>${secondlevelmenus.function_name!''}</span>
										</a>
										<#if (secondlevelmenus.childFunctions?size > 0)>
										<ul>
											<#list secondlevelmenus.childFunctions as thirdlevelmenus>
												<li id="${thirdlevelmenus.id}">
													<a href="${base}${thirdlevelmenus.function_url!''}">
														<i class="${thirdlevelmenus.function_icon!''}"></i>
														<span>${thirdlevelmenus.function_name!''}</span>
													</a>
												<li>
											</#list>
										</ul>
										</#if>
									</li>
								</#if>
							</#list>
						</ul>
						</#if>
					</li>
				</#if>
			</#list>
		</ul>
		
		<ul class="nav navbar-right pull-right">
			<li>
				<a href="${base}/systemuser/userlist">
				欢迎您: ${Session["SYS_USER"].userName}
				</a>
			</li>
			<li class="sep"></li>
			<li>
				<a href="${base}/logout">
					登出<i class="entypo-logout right"></i>
				</a>
			</li>
		</ul>
	</div>
</header>
<!-- 产品提议去除 -->
<!--
<div class="breadnav">
	<ol class="breadcrumb bc-3" id="breadDiv" style="margin-left: 20px;">
		<i class="entypo-folder"></i>
		${(Session['SYS_ACTIVE_MENUS'])!'<li><a href="${base}/distribute/flowdirectionstate">实时态势</a></li>'}
		<i class="entypo-back-in-time"style="margin-left: 50px;"><i id="maxdatatime">${.now}</i></i>
	</ol>
	<input type="hidden" id="acctive_menu_ids" class="form-control" value="${(Session['SYS_ACTIVE_MENUS_IDS'])!''}">
</div>
<hr/>
-->
<input type="hidden" id="acctive_menu_ids" class="form-control" value="${(Session['SYS_ACTIVE_MENUS_IDS'])!''}">