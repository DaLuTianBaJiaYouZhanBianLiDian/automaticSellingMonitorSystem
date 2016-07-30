var myCharts = new Array();
$(function() {
	moment.lang('zh-cn');
	$('#reportrange').daterangepicker({
		timePicker : true,
		timePickerIncrement : 5,
		format : 'YYYY/MM/DD HH:mm',
		locale : {
			applyLabel : '确认',
			cancelLabel : '取消',
			fromLabel : '从',
			toLabel : '至',
			weekLabel : '周',
			customRangeLabel : '选择日期',
		}
	});
	$("#reportrange_div").hide();
	$("#Period").change(function(event) {
		if ($(this).val() == 0) {
			$("#reportrange_div").show();
		} else {
			$("#reportrange_div").hide();
		}
	});
	routequery();
});

function routequery() {
	myCharts = new Array();
	query_route_info();
	query_route_charts();
//	myCharts.push(query_route_traffic());
}
function query_route_info(){
	$.post(appbasepath+"/report/routeStatus?time=" + (new Date().getTime()), $("#queryform").serialize(), function(result) {
		if (result.status == 'succeed') {
			$("#maxdatatime").html(result.datas['maxdatatime']==null?"没有查询到此路由的数据时间":result.datas['maxdatatime']);
			$("#route_runningday").html(result.datas['runningday']==null?0:result.datas['runningday']);
			$("#route_flowcount").html(result.datas['flowcount']==null?0:result.datas['flowcount']);
			$("#route_fps").html(result.datas['fps']==null?0:result.datas['fps']);
			$("#route_GBps").html(result.datas['GBps']==null?0:(result.datas['GBps'] * trafficUnit * routesimple /1024/1024/1024 ).toFixed(2));
		}
	});
}
function query_route_charts(){
	$("#route_flow_chart").height(280);
	$("#route_traffic_chart").height(280);
	var myChart1 = echarts.init(document.getElementById('route_flow_chart'));
	myChart1.showLoading({
		text : "努力加载中...",
		effect : "dynamicLine",
		textStyle : {
			fontSize : 20
		}
	});
	var myChart2 = echarts.init(document.getElementById('route_traffic_chart'));
	myChart2.showLoading({
		text : "努力加载中...",
		effect : "dynamicLine",
		textStyle : {
			fontSize : 20
		}
	});
	// 请求数据
	$.post(appbasepath+"/report/getRouteCharts?time=" + (new Date().getTime()), $("#queryform").serialize(), function(result) {
		if (result.status == 'succeed') {
//			console.log("result.datas -- " + result.datas);
			if (result.datas.length == 0) {
				myChart1.showLoading({
					text : "没有查询到数据...",
					effect : "bubble",
					textStyle : {
						fontSize : 20
					}
				});
				myChart2.showLoading({
					text : "没有查询到数据...",
					effect : "bubble",
					textStyle : {
						fontSize : 20
					}
				});
				return;
			}
			var datatimes = new Array();
			var fps = new Array();
			var GBps = new Array();
			var Mpps = new Array();
			for ( var i = 0; i < result.datas.length; i++) {
				var data = result.datas[i];
				datatimes.push(data['datatime'].substr(0, 16));
				fps.push(data['fps'].toFixed(2));
				GBps.push((data['bps']  *trafficUnit *routesimple /1024/1024/1024 ).toFixed(2));
				Mpps.push((data['pps'] *routesimple /1000/1000).toFixed(2));
			}
			var option1 = {
				backgroundColor : "#FCFCFC",
				animation : false,
				tooltip : {
					trigger : "axis"
				},
//				toolbox: {
//			        show : true,
//			        feature : {
//			            mark : {show: true},
//			            magicType : {show: true, type: ['line', 'bar']},
//			            restore : {show: true},
//			            saveAsImage : {show: true},
//			            dataZoom : {show : true},
//			        }
//			    },
				dataZoom : {
					show : true,
					start : 70,
					end : 100
				},
				grid : {
					x : 80,// 左边距
					y : 20,// 上边距
					y2 : 55,// 下边距
					x2 : 80
				// 右边距
				},
				xAxis : [ {
					type : "category",
					boundaryGap : false,
					data : datatimes,
					splitLine : {
						show : false,
						lineStyle : {
							type : "solid"
						}
					}
				} ],
				yAxis : [ {
					name : "Flow",
					type : "value"
				} ],
				series : [ {
					name : "处理能力",
					type : "line",
					smooth : true,
					itemStyle : {
						normal : {
							areaStyle : {
								type : "default"
							}
						}
					},
					data : fps
				} ]
			};
			myChart1.setOption(option1);
			myChart1.hideLoading();
			myCharts.push(myChart1);
			var option2 = {
					backgroundColor : "#FCFCFC",
					animation : false,
					tooltip : {
						trigger : "axis"
					},
					dataZoom : {
						show : true,
						start : 70,
						end : 100
					},
					grid : {
						x : 80,// 左边距
						y : 20,// 上边距
						y2 : 55,// 下边距
						x2 : 80
					// 右边距
					},
					xAxis : [ {
						type : "category",
						boundaryGap : false,
						data : datatimes,
						splitLine : {
							show : false,
							lineStyle : {
								type : "solid"
							}
						}
					} ],
					yAxis : [ {
						name : "数据包",
						type : "value",
						axisLabel : {
							formatter : '{value} Mpps'
						},
						splitLine : {
							show : false,
							lineStyle : {
								type : "solid"
							}
						}
					}, {
						name : "流量",
						type : "value",
						axisLabel : {
							formatter : '{value} Gbps'
						}
					} ],
					series : [ {
						name : "包速",
						type : "line",
						smooth : true,
						itemStyle : {
							normal : {
								areaStyle : {
									type : "default"
								}
							}
						},
						data : Mpps
					}, {
						name : "流量",
						type : "line",
						smooth : true,
						yAxisIndex : 1,
						itemStyle : {
							normal : {
								areaStyle : {
									type : "default"
								}
							}
						},
						data : GBps
					} ]
				};
				myChart2.setOption(option2);
				myChart2.hideLoading();
				myCharts.push(myChart2);
		}
	});
}
 
window.onresize = function() {
	// sidebar_collapsed();
	for ( var i = 0; i < myCharts.length; i++) {
		myCharts[i].resize && myCharts[i].resize();
	}
}