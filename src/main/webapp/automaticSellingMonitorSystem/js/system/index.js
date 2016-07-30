var myCharts = new Array();
$(function() {

	// 为两个图标div设置高度
	// $("#system_count_fps_stats").height(180);
	$("#system_count_fps").height(190);
	// $("#system_count_bps_stats").height(180);
	$("#system_count_bps").height(190);
	myCharts.push(system_count_fps_charts());
	myCharts.push(system_count_bps_charts());
	//定时刷新
	timedRunning();
});

function timedRunning() {
	$.post(appbasepath+"/index/runningState?time=" + (new Date().getTime()), {}, function(result) {
		//debugger;
		if (result.status == 'succeed') {
			//routenum custnum runningday flowcount fps GBps
			$("#maxdatatime").html(result.datas['maxdatatime']);
			$("#routenum").html(result.datas['routenum']);
			$("#custnum").html(result.datas['custnum']);
			$("#runningday").html(result.datas['runningday']);
			$("#flowcount").html(result.datas['flowcount']);
			$("#fps").html(result.datas['fps']);
			$("#GBps").html((result.datas['GBps'] * trafficUnit * routesimple /1024/1024/1024 ).toFixed(2));
		}
	});
	setTimeout("timedRunning()", 60000);
}

function system_count_fps_charts() {
	var myChart = echarts.init(document.getElementById('system_count_fps'));
	myChart.showLoading({
		text : "努力加载中...",
		effect : "dynamicLine",
		textStyle : {
			fontSize : 20
		}
	});
	// 请求数据
	$.post(appbasepath+"/index/systemfpslist?time=" + (new Date().getTime()), {}, function(result) {
		if (result.status == 'succeed') {
			var datatimes = new Array();
			var fps = new Array();
			for ( var i = 0; i < result.datas.length; i++) {
				var data = result.datas[i];
				datatimes.push(data['datatime'].substr(0, 16));
				fps.push(data['fps'].toFixed(2));
			}
			var option = {
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
			myChart.setOption(option);
			myChart.hideLoading();
		}
	});
	return myChart;
}

function system_count_bps_charts() {
	var myChart = echarts.init(document.getElementById('system_count_bps'));
	myChart.showLoading({
		text : "努力加载中...",
		effect : "dynamicLine",
		textStyle : {
			fontSize : 20
		}
	});
	// 请求数据
	$.post(appbasepath+"/index/systembpslist?time=" + (new Date().getTime()), {}, function(result) {
		if (result.status == 'succeed') {
			var datatimes = new Array();
			var GBps = new Array();
			var Mpps = new Array();

			for ( var i = 0; i < result.datas.length; i++) {
				var data = result.datas[i];
				datatimes.push(data['datatime'].substr(0, 16));
				GBps.push((data['GBps']  * trafficUnit *routesimple /1024/1024/1024 ).toFixed(2));
				Mpps.push((data['Mpps'] *routesimple /1000/1000).toFixed(2));
			}
			var option = {
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
			myChart.setOption(option);
			myChart.hideLoading();
		}
	});
	return myChart;
}

window.onresize = function() {
	// sidebar_collapsed();
	for ( var i = 0; i < myCharts.length; i++) {
		myCharts[i].resize && myCharts[i].resize();
	}
}

$(function() {
	// sidebar_collapsed();
});