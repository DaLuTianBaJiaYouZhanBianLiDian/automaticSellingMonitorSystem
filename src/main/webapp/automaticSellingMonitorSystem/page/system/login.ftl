<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Neon Admin Panel"/>
    <meta name="author" content=""/>
    <title>${SYS_NAME}</title>

	<#include "../system/neonhead.ftl"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${base}/lib/component/html5shiv.js"></script>
    <script src="${base}/lib/component/respond.min.js"></script>
    <![endif]-->
	<style type="text/css">  
	.login-page .login-form .form-group .input-group .form-control {
   		color: #000; 
	}
  	</style>
</head>
<body class="page-body login-page" data-url="http://neon.dev">

<div class="login-container">

<#--<div class="login-header login-caret">-->
<#---->
<#--<div class="login-content">-->
<#---->
<#--<a href="" class="logo">-->
<#--<img src="${base}/lib/images/logo2.gif" width="120" alt="" />-->
<#--</a>-->
<#---->
<#--<p class="description">尊敬的用户，请输入您的账户信息！</p>-->
<#---->
<#--<!-- progress bar indicator &ndash;&gt;-->
<#--<div class="login-progressbar-indicator">-->
<#--<h3>43%</h3>-->
<#--<span>加载中...</span>-->
<#--</div>-->
<#--</div>-->
<#---->
<#--</div>-->

<#--<div class="login-progressbar">-->
<#--<div></div>-->
<#--</div>-->

    <div class="login-form">
        <div class="login-content">

            <p>账户登录</p>
            <form method="post" role="form" id="form_login" name="loginform">

                <div class="form-group">

                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="entypo-user"></i>
                        </div>

                        <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名"
                               autocomplete="off"/>
                    </div>

                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="entypo-key"></i>
                        </div>

                        <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码"
                               autocomplete="off"/>
                    </div>

                </div>

                <div class="form-group forget">
                    <a href="" class="link">忘记登录密码</a>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block btn-login">
                        登录
                    </button>
                </div>

            </form>

        </div>

    </div>
</div>

<!-- Bottom Scripts -->
<script src="${base}/lib/neon/html/assets/js/gsap/main-gsap.js"></script>
<script src="${base}/lib/neon/html/assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"></script>
<script src="${base}/lib/neon/html/assets/js/bootstrap.min.js"></script>
<script src="${base}/lib/neon/html/assets/js/joinable.js"></script>
<script src="${base}/lib/neon/html/assets/js/resizeable.js"></script>
<script src="${base}/lib/neon/html/assets/js/neon-api.js"></script>
<script src="${base}/lib/neon/html/assets/js/jquery.validate.min.js"></script>
<script src="${base}/lib/neon/html/assets/js/neon-login.js"></script>
<script src="${base}/lib/neon/html/assets/js/neon-custom.js"></script>
<script src="${base}/lib/component/js-md5/js/md5.min.js"></script>
<script src="${base}/lib/component/bootbox.js"></script>
<script src="${base}/automaticSellingMonitorSystem/js/ndas/mapData.js"></script>
<script src="${base}/automaticSellingMonitorSystem/js/ndas/chartOption.js"></script>
<script src="${base}/automaticSellingMonitorSystem/js/ndas/application_config.js"></script>
<script src="${base}/automaticSellingMonitorSystem/js/system/login.init.js"></script>
</body>
</html>