$(function () {
    console.log(10)
    var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
    console.log(lineChart)
    var lineoption = {
        xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar'
        }]
    };
    lineChart.setOption(lineoption); 

});
