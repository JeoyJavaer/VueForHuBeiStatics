<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>
<style type="text/css">
.layui-form-item {
	margin-top: -1px;
	margin-left: 60px;
}

.layui-input, .layui-textarea {
	border: none;
}
</style>
<base href="<%=request.getContextPath() + '/'%>" />
</head>
<body>
<form class="layui-form" id="search-form" >
	<div class="layui-form" style="width: 100%; margin-top: 20px;">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<label class="layui-form-label">创建时间</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="createTime1"
						name="beginDate" placeholder="yyyy-MM-dd">
				</div>
			</div>
			至
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="createTime2"
						name="endDate" placeholder="yyyy-MM-dd">
				</div>
			</div>
			<label style="margin-left: 10px;" class="layui-label">时间</label>
			<div style="width: 100px;" class="layui-input-inline">
 				<select name="timeLimit" required lay-verify="required" id="timeLimit" style="width: 100%;"  lay-filter="timeSelect">
					<option value="0">未选择</option>
					<option value="1">当天</option>
					<option value="2">昨天</option>
					<option value="3">前天</option>
					<option value="4">本周</option>
					<option value="5">本月</option>
				</select>
			</div>
			
			<button style="margin-left: 30px;"
				id="searchBtn" class="layui-btn" type="button"
				lay-filter="userForm">
				<i class="layui-icon">&#xe615;</i>搜索
			</button>
			
			<button type="reset" class="layui-btn layui-btn-primary">
				<i class="layui-icon layui-icon-refresh"></i>重置
			</button>
			
			<button type="button" id="export-button" class="layui-btn layui-btn-danger">
                   <i class="layui-icon  layui-icon-file"></i>导出
           	</button>
           	</br>
           	<strong>
			<div id="countDiv" style="width: 100%; height: 10px; padding-top: 20px; text-align:center; font-size:18px"></div>
			<div id="pvuvDiv" style="width: 100%; height: 10px; padding-top: 20px; text-align:center; font-size:18px"></div>
			<!-- 
			<div id="pvDiv" style="width: 100%; height: 10px; padding-top: 20px; text-align:center; font-size:18px"></div>
			<div id="uvDiv" style="width: 100%; height: 10px; padding-top: 20px; text-align:center; font-size:18px"></div> 
			-->
			<strong>
			
			
			<div id="customer" style="width: 100%; height: 500px; padding-top: 20px;"></div>
			</br>
			</br>
			<div id="customer2" style="width: 100%; height: 500px; padding-top: 20px;"></div>
			</br>
			</br>
			<div id="customer3" style="width: 100%; height: 500px; padding-top: 20px;"></div>
		</blockquote>
	</div>
</form>
	<table class="layui-hide" id="gameVisitTable"
		lay-filter="gameVisitTable"></table>

	<script type="text/html" id="btnGroup"> 
        <a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="details"><i class="layui-icon layui-icon-about"></i>详情</a>
		<shiro:hasPermission name="9003">
        <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		</shiro:hasPermission>
    </script>

	<script type="text/javascript">
		layui.use([ 'table', 'form', 'laydate' ], function() {
			var layer = layui.layer;
			var tableload = layer.load(3); //加载等待样式
			var table = layui.table;
			var $ = layui.$;
			var form = layui.form;
			var laydate = layui.laydate;
			
			form.render('select');

			//时间下拉窗口
			laydate.render({
				elem : '#createTime1'
			});
			laydate.render({
				elem : '#createTime2'
			});

			table.render({
				elem : '#gameVisitTable',
				url : 'gameVisit/getListDayCountPvUv',
				cellMinWidth : 50,//全局定义常规单元格的最小宽度，layui 2.2.1 新增
				cols : [ [ {
					type : 'checkbox'
				},
				/* 
				{
					field : 'id',
					title : '',
					width : '3%',
					align : 'center'
				},
				 */
				{
					field : 'countDayTime',
					title : '时间',
					align : 'center',
					templet : function(data) {
						return data.countDayTime;
					}
				},{
					field : 'countPV',
					title : 'PV',
					align : 'center',
					templet : function(data) {
						return data.countPV;
					}
				},{
					field : 'countUV',
					title : 'UV',
					align : 'center',
					templet : function(data) {
						return data.countUV;
					}
				},{
					field : 'countOrder',
					title : '订购量',
					align : 'center',
					templet : function(data) {
						return data.countOrder;
					}
				},{
					field : 'countOrderRate',
					title : '转化率',
					align : 'center',
					templet : function(data) {
						return data.countOrderRate;
					}
				}

				] ],
				loading : true //分页加载样式
				,
				page : true//是否开启分页
				,
				loading : true //分页加载样式
				,
				done : function(res) {
			//		document.getElementById("pvDiv").
					createLinePicture(res);
					createCirclePvOrigin(res);
					createCircleUvOrigin(res);
					
				}
			});

			var customerIncrease = echarts.init(document.getElementById("customer"));
			var customerOriginPv = echarts.init(document.getElementById("customer2"));
			var customerOriginUv = echarts.init(document.getElementById("customer3"));
			console.info("");
			//线型图
			function createLinePicture(res){
				var countPVArray = [];
				var countUVArray = [];
				var countOrderArray = [];
				var countOrderRateArray = [];
				//同时画出柱状图
				var res_data = res.data;
				//构建json对象
				var data = {};
		//		var dataJson = {};  //构建json对象，日期为key 统计数值为value
			//	for(var i=0; i < res_data.length;i++ ){
				//倒序排列
				for(var i=res_data.length-1; i >=0;i-- ){
	//				dataJson[res_data[i].gameName] = res_data[i].countPV;
					countPVArray.push([res_data[i].countDayTime, res_data[i].countPV]);
					countUVArray.push([res_data[i].countDayTime, res_data[i].countUV]);
					countOrderArray.push([res_data[i].countDayTime, res_data[i].countOrder]);
					countOrderRateArray.push([res_data[i].countDayTime, res_data[i].countOrderRate.split("%")[0]]);
				}
				//设置PV UV数据
			/* 	
				var option = {
					title : {
						text : '单日PvUv统计'
					},
					tooltip : { //鼠标经过显示提示框
						show : true,
						trigger : "axis"
					},
					legend : { //不同的线的类型
						data : [ "播放总次数" ]
					},
					xAxis : {
						name : "日期",
						type : 'category',
						axisLabel : {
							interval : 0,//代表显示所有x轴标签显示
							rotate : 45, //代表逆时针旋转45度
						}
					},
					yAxis : {
						name : "PV/UV/订购量",
						type : 'value'
					},
					toolbox : { //工具栏
						show : true,
						feature : {
							dataView : {
								readOnly : false
							},
							magicType : {
								type : [ 'line', 'bar' ]
							}, //图形切换
							restore : {},
							saveAsImage : {}
						}
					},
					dataZoom : [ //滚动条
					{
						type : 'slider',
						show : true,
						start : 0,
						end : 100
					} ],
					series : [ {
						name : "PV",
						data : [],
						type : 'line'
					},{
						name : "UV",
						data : [],
						type : 'line'
					} ]
				};
			 */	
				var option = {
					title : {
						text : '大厅PvUv统计'
					},
					tooltip : { //鼠标经过显示提示框
						show : true,
						trigger : "axis",
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
					legend : { //不同的线的类型
						data : [ "播放总次数" ]
					},
					xAxis : {
						name : "日期",
						type : 'category',
						axisLabel : {
							interval : 0,//代表显示所有x轴标签显示
							rotate : 45, //代表逆时针旋转45度
						}
					},
					yAxis : {
						name : "PV/UV/订购量/转化率",
						type : 'value'
					},
					toolbox : { //工具栏
						show : true,
						feature : {
							dataView : {
								readOnly : false
							},
							magicType : {
								type : [ 'line', 'bar' ]
							}, //图形切换
							restore : {},
							saveAsImage : {}
						}
					},
					dataZoom : [ //滚动条
					{
						type : 'slider',
						show : true,
						start : 0,
						end : 100
					} ],
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
					} ]
				};
				option.series[0].data = countPVArray;
				option.series[1].data = countUVArray;
				option.series[2].data = countOrderArray;
				option.series[3].data = countOrderRateArray;
				customerIncrease.setOption(option);
				layer.close(tableload);
			}
		
			//圆型图PV
			function createCirclePvOrigin(res){
				var data = [];
				//倒序
				for(var i=res.data.length-1;i>=0;i--){
					var json = {};
					json.name = res.data[i].countDayTime;
					json.value = res.data[i].countPV;
					data.push(json);
				}
				var option2 = {
				    title : {
				        text: '大厅PvVu统计',
				        subtext: 'PV',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				    	type: "scroll",
				        orient: 'vertical',
				        left: 'left',
				    /*  
				    	//自定义
				        formatter:function (params){
				        	for(var i=res.data.length-1;i>=0;i--){
				        		if(params == res.data[i].countDay){
				        			return params +" : ("+ res.data[i].countPV+")";
				        		//	return params +" : ("+ "{a} <br/>{b} : {c} ({d}%)"+")";
				        		//	return params +" : ("+ "{b} : {c} ({d}%)"+")";
				        		}
				        	}
				        	return params;
				        },
				     */
				    },
				    series : [
				        {
				            name: 'PV',
				            type: 'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:data,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                },
				                normal:{ 
			                        label:{ 
			                            show: true, 
			                            formatter: '{b} : {c}  ({d}%)' 
			                        }, 
			                        labelLine :{show:true} 
			                    } 
				            }
				        }
				    ]
				};
				customerOriginPv.setOption(option2);
			}
			
			//圆型图UV
			function createCircleUvOrigin(res){
				var data = [];
				//倒序
				for(var i=res.data.length-1;i>=0;i--){
					var json = {};
					json.name = res.data[i].countDayTime;
					json.value = res.data[i].countUV;
					data.push(json);
				}
				var option2 = {
				    title : {
				        text: '大厅PvVu统计',
				        subtext: 'UV',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				    	type: "scroll",
				        orient: 'vertical',
				        left: 'left',
				    /*  
				    	//自定义
				        formatter:function (params){
				        	for(var i=res.data.length-1;i>=0;i--){
				        		if(params == res.data[i].countDay){
				        			return params +" : ("+ res.data[i].countPV+")";
				        		//	return params +" : ("+ "{a} <br/>{b} : {c} ({d}%)"+")";
				        		//	return params +" : ("+ "{b} : {c} ({d}%)"+")";
				        		}
				        	}
				        	return params;
				        },
				     */
				    },
				    series : [
				        {
				            name: 'UV',
				            type: 'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:data,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                },
				                normal:{ 
			                        label:{ 
			                            show: true, 
			                            formatter: '{b} : {c}  ({d}%)' 
			                        }, 
			                        labelLine :{show:true} 
			                    } 
				            }
				        }
				    ]
				};
				customerOriginUv.setOption(option2);
			}
			
			layer.ready(function(){
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
						limit:10
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
			
			//搜索功能
			var active = {
				reload : function() {
					var beginDate = $("input[name='beginDate']");
					var endDate = $("input[name='endDate']");
					//执行重载
					table.reload('gameVisitTable', {
						page : {
							curr : 1
						//重新从第 1 页开始
						},
						where : {
							"beginDate" : beginDate.val(),
							"endDate" : endDate.val()
						}
					});
				}
			};
			$('#searchBtn').on('click', function() {
				timeAllCount($("input[name='beginDate']").val(),$("input[name='endDate']").val());
				active['reload'] ? active['reload'].call(this) : '';
			});
			

			$("#export-button").click(function () {
				var url ='gameVisit/getListDayCountPvUvExport.do';
				document.getElementById("search-form").action=url;
				document.getElementById("search-form").submit();
			});
			
			//监听 时间select  select(timeSelect)是根据 lay-filter="timeSelect"
			form.on('select(timeSelect)',function(data){
				//获取当天时间
				var now = new Date();	//当前日期
				var nowDayOfWeek = now.getDay();	//今天本周的第几天
			    var nowDay = now.getDate();	//当前日
			    var nowMonth = now.getMonth();	//当前月
			    var nowYear = now.getFullYear();	//当前年
				
				if(data.value =="1"){
		    		//当天
		    		now.setDate(now.getDate());
					var s1 = now.format("yyyy-MM-dd");
		    		$('input[name=beginDate]').val(s1);
		    		$('input[name=endDate]').val("");
		    	}else if(data.value =="2"){
		    		//昨天
		    		now.setDate(now.getDate());
					var s2 = now.format("yyyy-MM-dd");
		    		now.setDate(now.getDate()-1);
					var s1 = now.format("yyyy-MM-dd");
		    		$('input[name=beginDate]').val(s1);
		    		$('input[name=endDate]').val(s2);
		    	}else if(data.value =="3"){
		    		//前天
		    		now.setDate(now.getDate()-1);
					var s2 = now.format("yyyy-MM-dd");
		    		now.setDate(now.getDate()-1);
					var s1 = now.format("yyyy-MM-dd");
		    		$('input[name=beginDate]').val(s1);
		    		$('input[name=endDate]').val(s2);
		    	}else if(data.value =="4"){
		    		//本周
		    		var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek + 1)
		    		var s1 = weekStartDate.format("yyyy-MM-dd");
		    		$('input[name=beginDate]').val(s1);
		    		$('input[name=endDate]').val("");
		    	}else if(data.value =="5"){
		    		//本月
		    		var weekStartDate = new Date(nowYear, nowMonth, 1)
		    		var s1 = weekStartDate.format("yyyy-MM-dd");
		    		$('input[name=beginDate]').val(s1);
		    		$('input[name=endDate]').val("");
		    	}
		    });

			//对Date的扩展，将 Date 转化为指定格式的String
			Date.prototype.format = function(format) {
				var date = {
					"M+": this.getMonth() + 1,
					"d+": this.getDate(),
					"h+": this.getHours(),
					"m+": this.getMinutes(), 
					"s+": this.getSeconds(),
					"q+": Math.floor((this.getMonth() + 3) / 3),
					"S+": this.getMilliseconds()
				};
				if (/(y+)/i.test(format)) {
					format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
				}
				for (var k in date) {
					if (new RegExp("(" + k + ")").test(format)) {
						format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
					}
				}
				return format;
			}

		})
	</script>
</body>
</html>