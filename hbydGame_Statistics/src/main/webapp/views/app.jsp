<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<style>
body{
    background: white;
}
.layui-card-header{
    border-left: 5px solid #009688;
}
.layui-container{
    margin-top:20px;
}

</style>
<body>

<div class="layui-container">
    <div class="layui-row">
           
    	<!-- 近n天公司的客户增加量/损失量 -->
        <div class="layui-col-md12">
            <div class="layui-card">
               <div class="layui-card-header" >数据概览</div>
               <strong>
				<div id="countDiv" style="width: 100%; height: 10px; padding-top: 20px; text-align:center; font-size:18px"></div>
				<div id="pvuvDiv" style="width: 100%; height: 10px; padding-top: 20px; text-align:center; font-size:18px"></div>
			<strong>
               <div class="layui-card-body" >
		            <form class="layui-form" action="" style="margin-top:30px;">
				        <div class="layui-form-item" style="text-align:center;">
			                <div class="layui-inline">
<!-- 				               <label class="layui-form-label">统计</label> -->
				              <label class="layui-form-label">时间</label>
		                        <div class="layui-input-inline" style="width:150px;">
									 <input type="text" class="layui-input" id="start_Time"  name ='beginDate'>
		                        </div>
		                        <div class="layui-form-mid">-</div>
		                        <div class="layui-input-inline" style="width:150px;">
		                        	<input type="text" class="layui-input" id="end_Time"  name ='endDate' >
		                        </div>
		                        
		                        
				               
				               
<!-- 					            <div class="layui-input-inline" style="width:100px;"> -->
<!-- 					                <select name="day" > -->
<!-- 					                    <option value="7" selected>7天</option> -->
<!-- 					                    <option value="30">30天</option> -->
<!-- 					                    <option value="60">60天</option> -->
<!-- 					                    <option value="90">90天</option> -->
<!-- 					                    <option value="180">180天</option> -->
<!-- 					                    <option value="365">365天</option> -->
<!-- 					                </select> -->
<!-- 					            </div> -->
<!-- 					            <button id="countuserButton" class="layui-btn layui-btn-primary" type="button">确定</button> -->
					            
					            
					            <button style="margin-left: 30px;" id="countuserButton" class="layui-btn" type="button"  lay-filter="userForm">
									<i class="layui-icon">&#xe615;</i>搜索
								</button>
				
					            
					            <button type="reset" class="layui-btn layui-btn-primary">
									<i class="layui-icon layui-icon-refresh"></i>重置
								</button>
					        </div>
					    </div>
				    </form>
				    <div  style=" white-space: nowrap;">
				    	<div id="countpvuv" style="width:50%;height:300px;display: inline-block;">
						</div>
						<div id="countuser" style="width:50%;height:300px;display: inline-block;">
						</div>
					</div>
					 <div  style=" white-space: nowrap;">
					 	<div id="countorder" style="width:50%;height:300px;display: inline-block;">
						</div>
						<div id="countprice" style="width:50%;height:300px;display: inline-block;">
						</div>
					</div>
				</div>
			</div>
		</div><!-- 近n天公司的客户增加量/损失量 -->
		
    </div>
</div>

</body>
<script type="text/javascript">
//请求数据
// layui.use(['layer','form','table'], function(){
layui.use(['form', 'layer','laydate'], function(){
	var $ = layui.$;
	
    layui.form.render("select");
    
    
    layui.laydate.render({
        elem: '#start_Time' //指定元素
       	,type:'datetime'
    });
    
    layui.laydate.render({
        elem: '#end_Time' //指定元素
       	,type:'datetime'
    });
  
    
	var countuserIncrease = echarts.init(document.getElementById("countuser"));
	createCountUserEcharts();
	
	
	var countorderIncrease = echarts.init(document.getElementById("countorder"));
	createCountOrderEcharts();
	
	
	var countpriceIncrease = echarts.init(document.getElementById("countprice"));
	createCountPriceEcharts();
	
	var countpvuvIncrease = echarts.init(document.getElementById("countpvuv"));
	createCountPvUvEcharts();
	
	$("#countuserButton").click(function(){
		timeAllCount($("input[name='beginDate']").val(),$("input[name='endDate']").val());
		createCountUserEcharts();
		createCountOrderEcharts();
		createCountPriceEcharts();
		createCountPvUvEcharts();
	});
	
	layer.ready(function(){
		console.info("11111111");
		$.ajax({
			url: 'gameVisit/getSumPvUv',
			dataType: 'json',
			type: 'get',
			success: function (data) {
				document.getElementById("pvuvDiv").innerHTML="总PV:"+data.sumPv+"&nbsp&nbsp&nbsp&nbsp&nbsp总UV:"+data.sumUv;
	//			document.getElementById("pvDiv").innerHTML="总PV:"+data.sumPv;
	//			document.getElementById("uvDiv").innerHTML="总UV:"+data.sumUv;
			}
		})
		
		$.ajax({
			url: 'gameVisit/getListDayCountPvUv',
			data:{
				page:1,
			//	limit:7,
			},
			dataType: 'json',
			type: 'get',
			success: function (data) {
				var c=0;
				for(var i=0 ;i<data.data.length;i++){
					c=c+data.data[i].countOrder;
				}
				document.getElementById("countDiv").innerHTML="总订购量:"+c;
			}
		})
	});
	
	/**
	   构建新增客户图表统计
	**/
	function createCountUserEcharts(){
		//构建图表data数组
// 		var day = $("select[name=day]").val();
		var beginDate = $("input[name=beginDate]").val();
		var endDate = $("input[name=endDate]").val();
		var countNewUserArray = [];  //客户增加量数组
		var countActiveUserArray = [];  //客户损失量数组
		$.ajax({
	        url:"${pageContext.request.contextPath}/homePage/countUser",
	        type:"post",
	        data:{
// 	            "day": day,
	            "beginDate": beginDate,
				"endDate": endDate,
	        },
	        asnyc:false,
	        dataType:"json",
	        beforeSend:function(){
	            loading = layer.load();
	        },
	        success:function(res){
	            var countNewUser = res.countNewUser; //客户增加量
	            var countActiveUser = res.countActiveUser; //客户损失量
	            var betweenDate= res.betweenDate;
	           //构建近day天前的日期json对象
	            var data = {};
	            for(var i = 0; i < betweenDate; i++){ 
	                var myDate = new Date();
	                var lw = new Date(myDate - 1000 * 60 * 60 * 24 * i);//最后一个数字 i天的意思
	                var lastY = lw.getFullYear();
	                var lastM = lw.getMonth()+1;
	                var lastD = lw.getDate();
	                var startdate=(lastM<10 ? "0" + lastM : lastM)+"-"+(lastD<10 ? "0"+ lastD : lastD);
	                data[startdate] = 0 ;
	            }
	            
	            var countNewUserJson = {};  //构建json对象，日期为key 统计数值为value
	            for(var i=0; i < countNewUser.length;i++ ){
	            	countNewUserJson[countNewUser[i].date] = countNewUser[i].count;
	            }
	            
	            var countActiveUserJson = {};//构建json对象，日期为key 统计数值为value
	            for(var i=0; i < countActiveUser.length; i++){
	            	countActiveUserJson[countActiveUser[i].date] = countActiveUser[i].count;
	            }
	           
	            //构建图表data数组
	            for(var key in data){
	            	if(countNewUserJson[key] != null){
	            		countNewUserArray.unshift([key, countNewUserJson[key]]);
	            	}else{
	            		countNewUserArray.unshift([key, data[key]]);
	            	}
	            	
	            	
	            	if(countActiveUserJson[key] != null){
	            		countActiveUserArray.unshift([key, countActiveUserJson[key]]);
	            	}else{
	            		countActiveUserArray.unshift([key, data[key]]);
	            	}
	            	
	            }
	        },
	        error:function(){
	            layer.msg("服务器开小差了!");
	        },
	        complete:function(){
	            layer.close(loading);
	            
	            var option = {
	                title : {
	                    text: '用户新增趋势',
	                },
	                tooltip:{  //鼠标经过显示提示框
	                	show : true,
	                	trigger: "axis",
	                },
	                legend:{  //不同的线的类型
	                	data: ["首次登录人数", "活跃用户人数"],
	                },
	                xAxis: {
	                    name: "时间",
	                    type: 'category', 
	                },
	                yAxis: {
	                    name: "数量",
	                    type: 'value',
	                },
	                toolbox:{  //工具栏
	                    show: true,
	                    feature:{
	                        dataView: {readOnly: false},  
	                        magicType: {type: ['line', 'bar']},  //图形切换
	                        restore: {},
	                        saveAsImage: {},
	                    },
	                },
	                dataZoom: [  //滚动条
	                    {
	                        type: 'slider',
	                        show: true,
	                        start: 0,
	                        end: 100,
	                    },
	                ],
	                series: [
	                	{
	                		name: "首次登录人数",
		                    data: countNewUserArray, 
		                    type: 'line'
		                },
	                	{
		                	name: "活跃用户人数",
		                	data: countActiveUserArray,
		                	type: "line",
	                	}
	                ],
	            };
	            
	            countuserIncrease.setOption(option);
	        }
	    });
	}
	
	
	
	/**
	   构建新增订购图表统计
	**/
	function createCountOrderEcharts(){
		//构建图表data数组
// 		var day = $("select[name=day]").val();
		var beginDate = $("input[name=beginDate]").val();
		var endDate = $("input[name=endDate]").val();
		var countNewOrderArray = [];  //订购新增统计
		var countUserOrderArray = [];  //订购总统计
		$.ajax({
	        url:"${pageContext.request.contextPath}/homePage/countOrder",
	        type:"post",
	        data:{
// 	            "day": day,
	            "beginDate": beginDate,
				"endDate": endDate,
	        },
	        asnyc:false,
	        dataType:"json",
	        beforeSend:function(){
	            loading = layer.load();
	        },
	        success:function(res){
	            var countNewOrder = res.countNewOrder; //客户增加量
	            var countUserOrder = res.countUserOrder; //客户损失量
	            var betweenDate= res.betweenDate;
	            
	           //构建近day天前的日期json对象
	            var data = {};
	            for(var i = 0; i < betweenDate; i++){ 
	                var myDate = new Date();
	                var lw = new Date(myDate - 1000 * 60 * 60 * 24 * i);//最后一个数字 i天的意思
	                var lastY = lw.getFullYear();
	                var lastM = lw.getMonth()+1;
	                var lastD = lw.getDate();
	                var startdate=(lastM<10 ? "0" + lastM : lastM)+"-"+(lastD<10 ? "0"+ lastD : lastD);
	                data[startdate] = 0 ;
	            }
	            
	            var countNewOrderJson = {};  //构建json对象，日期为key 统计数值为value
	            for(var i=0; i < countNewOrder.length;i++ ){
	            	countNewOrderJson[countNewOrder[i].date] = countNewOrder[i].count;
	            }
	            
	            var countUserOrderJson = {};//构建json对象，日期为key 统计数值为value
	            for(var i=0; i < countUserOrder.length; i++){
	            	countUserOrderJson[countUserOrder[i].date] = countUserOrder[i].count;
	            }
	           
	            //构建图表data数组
	            for(var key in data){
	            	if(countNewOrderJson[key] != null){
	            		countNewOrderArray.unshift([key, countNewOrderJson[key]]);
	            	}else{
	            		countNewOrderArray.unshift([key, data[key]]);
	            	}
	            	
	            	
	            	if(countUserOrderJson[key] != null){
	            		countUserOrderArray.unshift([key, countUserOrderJson[key]]);
	            	}else{
	            		countUserOrderArray.unshift([key, data[key]]);
	            	}
	            	
	            }
	        },
	        error:function(){
	            layer.msg("服务器开小差了!");
	        },
	        complete:function(){
	            layer.close(loading);
	            
	            var option = {
	                title : {
	                    text: '订单新增趋势',
	                },
	                tooltip:{  //鼠标经过显示提示框
	                	show : true,
	                	trigger: "axis",
	                },
	                legend:{  //不同的线的类型
	                	data: ["订购订单数", "订购人数"],
	                },
	                xAxis: {
	                    name: "时间",
	                    type: 'category', 
	                },
	                yAxis: {
	                    name: "数量",
	                    type: 'value',
	                },
	                toolbox:{  //工具栏
	                    show: true,
	                    feature:{
	                        dataView: {readOnly: false},  
	                        magicType: {type: ['line', 'bar']},  //图形切换
	                        restore: {},
	                        saveAsImage: {},
	                    },
	                },
	                dataZoom: [  //滚动条
	                    {
	                        type: 'slider',
	                        show: true,
	                        start: 0,
	                        end: 100,
	                    },
	                ],
	                series: [
	                	{
	                		name: "订购订单数",
		                    data: countNewOrderArray, 
		                    type: 'line'
		                },
	                	{
		                	name: "订购人数",
		                	data: countUserOrderArray,
		                	type: "line",
	                	}
	                ],
	            };
	            
	            countorderIncrease.setOption(option);
	        }
	    });
	}
	
	
	
	/**
	   构建新增金额图表统计
	**/
	function createCountPriceEcharts(){
		//构建图表data数组
// 		var day = $("select[name=day]").val();
		var beginDate = $("input[name=beginDate]").val();
		var endDate = $("input[name=endDate]").val();
		var countNewPriceArray = [];  //订购新增金额
		$.ajax({
	        url:"${pageContext.request.contextPath}/homePage/countPrice",
	        type:"post",
	        data:{
// 	            "day": day,
	            "beginDate": beginDate,
				"endDate": endDate,
	        },
	        asnyc:false,
	        dataType:"json",
	        beforeSend:function(){
	            loading = layer.load();
	        },
	        success:function(res){
	            var countNewPrice = res.countNewPrice; //新增金额
	            var betweenDate= res.betweenDate;
	           //构建近day天前的日期json对象
	            var data = {};
	            for(var i = 0; i < betweenDate; i++){ 
	                var myDate = new Date();
	                var lw = new Date(myDate - 1000 * 60 * 60 * 24 * i);//最后一个数字 i天的意思
	                var lastY = lw.getFullYear();
	                var lastM = lw.getMonth()+1;
	                var lastD = lw.getDate();
	                var startdate=(lastM<10 ? "0" + lastM : lastM)+"-"+(lastD<10 ? "0"+ lastD : lastD);
	                data[startdate] = 0 ;
	            }
	            
	            var countNewPriceJson = {};  //构建json对象，日期为key 统计数值为value
	            for(var i=0; i < countNewPrice.length;i++ ){
	            	countNewPriceJson[countNewPrice[i].date] = countNewPrice[i].count;
	            }
	            
	           
	            //构建图表data数组
	            for(var key in data){
	            	if(countNewPriceJson[key] != null){
	            		countNewPriceArray.unshift([key, countNewPriceJson[key]]);
	            	}else{
	            		countNewPriceArray.unshift([key, data[key]]);
	            	}
	            }
	        },
	        error:function(){
	            layer.msg("服务器开小差了!");
	        },
	        complete:function(){
	            layer.close(loading);
	            
	            var option = {
	                title : {
	                    text: '金额新增趋势',
	                },
	                tooltip:{  //鼠标经过显示提示框
	                	show : true,
	                	trigger: "axis",
	                },
	                legend:{  //不同的线的类型
	                	data: ["订购金额数"],
	                },
	                xAxis: {
	                    name: "时间",
	                    type: 'category', 
	                },
	                yAxis: {
	                    name: "数量",
	                    type: 'value',
	                },
	                toolbox:{  //工具栏
	                    show: true,
	                    feature:{
	                        dataView: {readOnly: false},  
	                        magicType: {type: ['line', 'bar']},  //图形切换
	                        restore: {},
	                        saveAsImage: {},
	                    },
	                },
	                dataZoom: [  //滚动条
	                    {
	                        type: 'slider',
	                        show: true,
	                        start: 0,
	                        end: 100,
	                    },
	                ],
	                series: [
	                	{
	                		name: "订购金额",
		                    data: countNewPriceArray, 
		                    type: 'line'
		                }
	                ],
	            };
	            countpriceIncrease.setOption(option);
	        }
	    });
	}
	
	
	/**
	   构建每日PvUv订购量转换率图表统计
	**/
	function createCountPvUvEcharts(){
		//构建图表data数组
//		var day = $("select[name=day]").val();
		var beginDate = $("input[name=beginDate]").val();
		var endDate = $("input[name=endDate]").val();
		var countNewOrderArray = [];  //订购新增统计
		var countUserOrderArray = [];  //订购总统计
		
		var countPVArray = [];
		var countUVArray = [];
		var countOrderArray = [];
		var countOrderRateArray = [];
		
		$.ajax({
	        url:"${pageContext.request.contextPath}/gameVisit/getListDayCountPvUv",
	        type:"post",
	        data:{
//	            "day": day,
	            "beginDate": beginDate,
				"endDate": endDate,
				"page": 1,
				"limit": 7,
	        },
	        asnyc:false,
	        dataType:"json",
	        beforeSend:function(){
	            loading = layer.load();
	        },
	        success:function(res){
/* 
	        	var countPV = res.countNewOrder; //客户增加量
	            var countUVArray = res.countUserOrder; //客户损失量
	            var countOrder= res.betweenDate;
	            var countOrderRate= res.betweenDate;
 */	            
	            
				//同时画出柱状图
				var res_data = res.data;
				
				for(var i=res_data.length-1; i >=0;i-- ){
			//		for(var i=0; i <res_data.length;i++ ){
			//		dataJson[res_data[i].gameName] = res_data[i].countPV;
					countPVArray.push([res_data[i].countDayTime.split("-")[1]+"-"+res_data[i].countDayTime.split("-")[2], res_data[i].countPV]);
					countUVArray.push([res_data[i].countDayTime.split("-")[1]+"-"+res_data[i].countDayTime.split("-")[2], res_data[i].countUV]);
					countOrderArray.push([res_data[i].countDayTime.split("-")[1]+"-"+res_data[i].countDayTime.split("-")[2], res_data[i].countOrder]);
					countOrderRateArray.push([res_data[i].countDayTime.split("-")[1]+"-"+res_data[i].countDayTime.split("-")[2], res_data[i].countOrderRate.split("%")[0]]);
				}
	 
		/* 		
	           //构建近day天前的日期json对象
	            var data = {};
	            for(var i = 0; i < betweenDate; i++){ 
	                var myDate = new Date();
	                var lw = new Date(myDate - 1000 * 60 * 60 * 24 * i);//最后一个数字 i天的意思
	                var lastY = lw.getFullYear();
	                var lastM = lw.getMonth()+1;
	                var lastD = lw.getDate();
	                var startdate=(lastM<10 ? "0" + lastM : lastM)+"-"+(lastD<10 ? "0"+ lastD : lastD);
	                data[startdate] = 0 ;
	            }
	     */        
	/*             var countNewOrderJson = {};  //构建json对象，日期为key 统计数值为value
	            for(var i=0; i < countNewOrder.length;i++ ){
	            	countNewOrderJson[countNewOrder[i].date] = countNewOrder[i].count;
	            }
	            
	            var countUserOrderJson = {};//构建json对象，日期为key 统计数值为value
	            for(var i=0; i < countUserOrder.length; i++){
	            	countUserOrderJson[countUserOrder[i].date] = countUserOrder[i].count;
	            }
	           
	            //构建图表data数组
	            for(var key in data){
	            	if(countNewOrderJson[key] != null){
	            		countNewOrderArray.unshift([key, countNewOrderJson[key]]);
	            	}else{
	            		countNewOrderArray.unshift([key, data[key]]);
	            	}
	            	
	            	
	            	if(countUserOrderJson[key] != null){
	            		countUserOrderArray.unshift([key, countUserOrderJson[key]]);
	            	}else{
	            		countUserOrderArray.unshift([key, data[key]]);
	            	}
	            	
	            } */
	//            console.log(countNewOrderArray);
	  //          console.log(countUserOrderArray);
	        },
	        error:function(){
	            layer.msg("服务器开小差了!");
	        },
	        complete:function(){
	            layer.close(loading);
	            
	            var option = {
	                title : {
	                    text: '每日PvUv订购量转换率',
	                },
	                tooltip:{  //鼠标经过显示提示框
	                	show : true,
	                	trigger: "axis",
	                	formatter(params){
							return params[0].axisValue+
									'<br>'+
									'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#c23531;"></span>'+
									"PV: "+params[0].data[1]+
									'<br>'+
									'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#2f4554;"></span>'+
									"UV: "+params[1].data[1]+
									'<br>'+
									'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#61a0a8;"></span>'+
									"订购量: "+params[2].data[1]+
									'<br>'+
									'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#d48265;"></span>'+
									"转化率: "+params[3].data[1]+"%";
				       }
	                },
	                legend:{  //不同的线的类型
	                	data: ["订购订单数", "订购人数"],
	                },
	                xAxis: {
	                    name: "时间",
	                    type: 'category', 
	                },
	                yAxis: {
	                    name: "数量",
	                    type: 'value',
	                },
	                toolbox:{  //工具栏
	                    show: true,
	                    feature:{
	                        dataView: {readOnly: false},  
	                        magicType: {type: ['line', 'bar']},  //图形切换
	                        restore: {},
	                        saveAsImage: {},
	                    },
	                },
	                dataZoom: [  //滚动条
	                    {
	                        type: 'slider',
	                        show: true,
	                        start: 0,
	                        end: 100,
	                    },
	                ],
	                series : [ {
						name : "PV",
						data : [],
						type : 'line'
					},{
						name : "UV",
						data : [],
						type : 'line'
					},{
						name : "订购量",
						data : [],
						type : 'line'
					},{
						name : "转化率",
						data : [],
						type : 'line'
					} ],
	            };
	           	option.series[0].data = countPVArray;
				option.series[1].data = countUVArray;
				option.series[2].data = countOrderArray;
				option.series[3].data = countOrderRateArray;
	            countpvuvIncrease.setOption(option);
	        }
	    });
	}
		
	
	
	function timeAllCount(beginDate,endDate){
		$.ajax({
			url: 'gameVisit/getListDayCountPvUv',
			data:{
				page:1,
		//		limit:10,
				beginDate:beginDate,
				endDate:endDate
			},
			dataType: 'json',
			type: 'get',
			success: function (data) {
				var c=0;
				for(var i=0 ;i<data.data.length;i++){
					c=c+data.data[i].countOrder;
				}
				document.getElementById("countDiv").innerHTML="当前筛选时间总订购量:"+c;
			}
		})
		
		$.ajax({
			url: 'gameVisit/getSumPvUv',
			data:{
				beginDate:beginDate,
				endDate:endDate
			},
			dataType: 'json',
			type: 'get',
			success: function (data) {
				document.getElementById("pvuvDiv").innerHTML="当前筛选时间总PV:"+data.sumPv+"&nbsp&nbsp&nbsp&nbsp&nbsp当前筛选时间总UV:"+data.sumUv;
			}
		})
		
	}
	
	
	
});


</script>

</html>