	<title>${Session["SYS_NAME"]!'网络流量态势感知系统'}</title>
	<meta charset="UTF-8">
	<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
	<meta name="renderer" content="webkit">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="云端卫士 攻击检测 流量分析" />
	<meta name="author" content="" />
	<link rel="stylesheet" href="${base}/lib/neon/html/assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
	<link rel="stylesheet" href="${base}/lib/neon/html/assets/css/font-icons/entypo/css/entypo.css">
	<link rel="stylesheet" href="${base}/lib/component/googleapisfonts.css">
	<!--
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">
	-->
    <link rel="stylesheet" href="${base}/lib/neon/html/assets/css/neon.css">
	<link rel="stylesheet" href="${base}/lib/neon/html/assets/css/custom.css">
	<script src="${base}/lib/neon/html/assets/js/jquery-1.10.2.min.js"></script>
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	  <script src="${base}/lib/component/html5shiv.js"></script>
	  <script src="${base}/lib/component/respond.min.js"></script>
	<![endif]-->

	<script src="${base}/lib/echarts/build/dist/echarts-all.js"></script>
	<script type="text/javascript">
		var appbasepath="${base}";
		//console.log("head中的采样比,在下个版本中去掉,下个版本不再需要采样比--完成,现在是1,不再使用.");
		var routesimple = 1;//没有采样比,后台计算
		var trafficUnit = 8;//流量显示 b * 8  
		
		var samplingrate = 1;//给攻击告警用-->告警后台已经计算,改为没有采样比
		var dstCityName = '南宁';//给攻击溯源地图用
		
		var totaltime = 600; //idc 数据存储时间间隔
		
			function deal302(result){
			//应该要判断返回码的,既然使用了post就按以下判断
				if (typeof result == 'string' || ((result + "").indexOf("<!DOCTYPE html>") != -1)) {
					var opts = {
					"closeButton" : true,
					"debug" : false,
					"positionClass" : "toast-top-full-width",
					"onclick" : null,
					"showDuration" : "300",
					"hideDuration" : "1000",
					"timeOut" : "5000",
					"extendedTimeOut" : "1000",
					"showEasing" : "swing",
					"hideEasing" : "linear",
					"showMethod" : "fadeIn",
					"hideMethod" : "fadeOut"
					};
				toastr.warning("<center><span style='font-size:18px'>由于长时间没有操作， 您的网站会话已过期，5秒后返回登录页面。</span></center>", null, opts);
				setTimeout("window.location.href=appbasepath+\"/index\"", 5000);
				}
			}
			
			function successtoast(){
				var opts = {
					"closeButton" : true,
					"debug" : false,
					"positionClass" : "toast-top-full-width",
					"onclick" : null,
					"showDuration" : "300",
					"hideDuration" : "1000",
					"timeOut" : "5000",
					"extendedTimeOut" : "1000",
					"showEasing" : "swing",
					"hideEasing" : "linear",
					"showMethod" : "fadeIn",
					"hideMethod" : "fadeOut"
				};
				toastr.success("<center><span style='font-size:18px'>操作成功!</span></center>", null, opts);
			}
			
			

		
	</script>