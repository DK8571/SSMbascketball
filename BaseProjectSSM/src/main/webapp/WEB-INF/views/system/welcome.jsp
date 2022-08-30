<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>欢迎页面</title>
</head>
<body>
<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " ></div>
<div id="container" style="width:100%;height:100%;">11</div>
<script type="text/javascript">
    var dom = document.getElementById('container');
    var myChart = echarts.init(dom, null, {
        renderer: 'canvas',
        useDirtyRect: false
    });
    var app = {};

    var option;

    option = {
        title: {
            text: '${title }'
        },
        xAxis: {
            text: '日期',
            type: 'category',
            data: [${date }]
        },
        yAxis: {
            text: '金额',
            type: 'value'
        },
        series: [
            {
                data: [${price }],
                type: 'line'
            }
        ]
    };

    if (option && typeof option === 'object') {
        myChart.setOption(option);
    }

    window.addEventListener('resize', myChart.resize);

</script>
</body>
</html>
