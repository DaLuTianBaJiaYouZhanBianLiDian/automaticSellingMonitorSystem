var mapChart, flowChart, packChart, maxChart, avgChart, nowChart, webSocket, myCharts = [];
$(document).ready(function () {
    $(".login-container").height((document.documentElement.clientHeight) * 0.8 + "px");
    mapChart = echarts.init(document.getElementById('map'));
    myCharts.push(mapChart);
    mapChart.setOption(loginMapOpt);

    flowChart = echarts.init(document.getElementById("flowTotal"));
    myCharts.push(flowChart);
    flowChart.setOption(flowOption);

    packChart = echarts.init(document.getElementById("packTotal"));
    myCharts.push(packChart);
    packChart.setOption(packOption);

    maxChart = echarts.init(document.getElementById("maxChart"));
    myCharts.push(maxChart);
    maxChart.setOption(flowOption);

    avgChart = echarts.init(document.getElementById("avgChart"));
    myCharts.push(avgChart);
    avgChart.setOption(flowOption);

    nowChart = echarts.init(document.getElementById("nowChart"));
    myCharts.push(nowChart);
    nowChart.setOption(flowOption);

    httpGetData();
    initData();

    window.onresize = function () {
        for (var i = 0; i < myCharts.length; i++) {
            myCharts[i].resize && myCharts[i].resize();
        }
    }
});
function initData() {
    var count = 0;
    for (var k in mapData.initData) {
        count++;
        mapChart.addMarkLine(0, {
            data: [
                [{name: LOCATION}, {name: mapData.initData[k], value: 0}]
            ]
        });

        mapChart.addMarkPoint(0, {
            data: [
                {name: mapData.initData[k], value: 0}
            ]
        });

    }
}

function httpGetData() {
    $.ajax({
        url: appbasepath + "/distribute/queryflowdirectionstate/out",
        dataType: 'json',
        success: function (result) {
            console.log(result);
            if (result.status == "succeed") {
                $.each(result.datas, function (index, value) {
                    if (value.type == "in_flow_total" || value.type == "out_flow_total") {
                        setGaugeChart(((value.data.gbit) / 1024 / 1024).toFixed(2), flowChart, "Pbit", 'x1024\n\n\nTbit');
                        setGaugeChart((value.data.mpackage / 1000 / 1000).toFixed(2), packChart, "Tpks", 'x1000\n\n\nGpks');
                    } else if (value.type == "in_flow_speed" || value.type == "out_flow_speed") {
                        setGaugeChart(value.data[0].maxValue.gbps[1], maxChart, 'Gbps', '\n\n\nGbps');
                        setGaugeChart(value.data[0].avgValue.gbps[1], avgChart, 'Gbps', '\n\n\nGbps');
                        setGaugeChart(value.data[0].nowValue.gbps[1], nowChart, 'Gbps', '\n\n\nGbps');
                    }
                });
            }
        },
        error: function (e) {
            //alert("获取数据错误...请稍后重试!");
        }
    });
}

function setGaugeChart(result, chart, type, name) {
    chart.setSeries([{
        name: type,
        type: 'gauge',
        radius: 75,
        min: Math.floor(result - (parseInt(Math.random() * 5) + 10)) < 0 ? 0 : (Math.floor(result - (parseInt(Math.random() * 5) + 10))),
        max: Math.ceil(result + (parseInt(Math.random() * 5) + 10)) < 0 ? 0 : (Math.ceil(result + (parseInt(Math.random() * 5) + 10))),
        splitNumber: 5, // 分割段数，默认为5
        axisLine: {            // 坐标轴线
            lineStyle: {       // 属性lineStyle控制线条样式
                color: "#ffffff",
                width: 0
            }
        },
        axisTick: {            // 坐标轴小标记
            splitNumber: 5,   // 每份split细分多少段
            length: 5,        // 属性length控制线长
            lineStyle: {       // 属性lineStyle控制线条样式
                color: 'rgba(150,150,150,.5)'
            }
        },
        axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
            interval: 0,
            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                color: 'rgba(150,150,150,.8)',
                fontSize: 12
            }
        },
        splitLine: {           // 分隔线
            show: true,        // 默认显示，属性show控制显示与否
            length: 8,         // 属性length控制线长
            lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                color: 'rgba(150,150,150,1)'
            }
        },
        pointer: {
            width: 5,
            color: 'rgba(150,150,150,.8)'
        },
        title: {
            show: true,
            offsetCenter: [0, '5%'],       // x, y，单位px
            textStyle: {
                fontSize: 12,
                color: 'rgba(150,150,150,.8)'
            }
        },
        detail: {
            offsetCenter: [0, '60%'],
            formatter: function (value) {
                var res = 0;
                if (type == "Pbit") {
                    res = value * 1024;
                } else if (type == "Tpks") {
                    res = value * 1000;
                } else {
                    res = value;
                }
                return Number(res).toFixed(2);
            },
            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                color: 'rgba(150,150,150,.8)',
                fontSize: 14,
                fontWeight: 'bolder'
            }
        },
        data: [{value: result, name: name}]
    }], true);
}