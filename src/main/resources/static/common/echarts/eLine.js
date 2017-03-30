function echartLine (id,legend,item,datas,unit) {
    var option = {
        tooltip : {
            show : false,
            trigger : 'axis'
        },
        legend : {
            data : legend,
            y: 'bottom'
        },
        grid : {
            x : 30,
            y : 30,
            x2 : 10,
            y2 : 80
        },
        calculable : false,
        xAxis : [ {
            type : 'category',
            boundaryGap : false,
            data : item,
            axisLabel : {
                show : true,
                interval : 0,
                formatter : function(params) {
                    var oneLineLength = 2;
                    var newParamsName = '';
                    var paramsNameNumber = params.length;
                    var provideNumber = oneLineLength;
                    var rowNumber = Math.ceil(paramsNameNumber
                        / provideNumber);
                    if (paramsNameNumber > provideNumber) {
                        for (var p = 0; p < rowNumber; p++) {
                            var tempStr = '';
                            var start = p * provideNumber;
                            var end = start + provideNumber;
                            if (p === rowNumber - 1) {
                                tempStr = params.substring(start,
                                    paramsNameNumber);
                            } else {
                                tempStr = params.substring(start,
                                        end)
                                    + '\n';
                            }
                            newParamsName += tempStr;
                        }
                    } else {
                        newParamsName = params;
                    }
                    return newParamsName;
                }
            }
        } ],
        yAxis : [ {
            type : 'value',
            name : unit ? ('单位：' + unit) : ''
        } ],
        series : (function() {
            var series = [];
            for (var l = 0; l < datas.length; l++) {
                var d = {};
                d.name = '';
                d.data = [];
                d.name = legend[l];
                d.type = 'line';
                d.smooth = false;
                d.data = datas[l];
                series.unshift(d);
            }
            return series;
        }()),
        color : [ '#C0392B', '#F39C12', '#8E44AD', '#2980B9',
            '#1ABC9C' ]
    };
    var line = echarts.init(document.getElementById(id), 'macarons');
    line.setOption(option);
    window.onresize = function() {
        var timeout;
        timeout = setTimeout(function() {
            var alarmTendency = echarts.init(document.getElementById(id), 'macarons');
            alarmTendency.setOption(option);
        }, 200);
    }
}
