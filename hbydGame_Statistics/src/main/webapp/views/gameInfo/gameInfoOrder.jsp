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
			<label style="margin-left: 10px;" class="layui-label">游戏名称</label>
			<div style="width: 100px;" class="layui-inline">
				<input type="text" name="gameName" lay-verify="required"
					style="width: 100%;" placeholder="" autocomplete="off"
					class="layui-input" />
			</div>
			<label style="margin-left: 10px;" class="layui-label">包名</label>
			<div style="width: 100px;" class="layui-inline">
				<input type="text" name="packageName" lay-verify="required"
					style="width: 100%;" placeholder="" autocomplete="off"
					class="layui-input" />
			</div>
			<label style="margin-left: 10px;" class="layui-label">所属CP</label>
			<div style="width: 100px;" class="layui-inline">
				<select  name="cpId" style="width: 100%;" lay-verify="required">
					<option value=""></option>
				</select>
			</div>
			
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
			
			<br>
			<br>
		
			<div class="layui-input-inline" style="width: 500px;">
				<button style="margin-left: 30px; "
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
			</div>
			<div id="customer" style="width: 100%; height: 500px; padding-top: 20px;"></div>
			</br>
			</br>
			<div id="customer2" style="width: 100%; height: 500px; padding-top: 20px;"></div>
			</br>
			</br>
			<div id="customer3" style="width: 100%; height: 500px; padding-top: 20px;"></div>
			</br>
			</br>
			<div id="customer4" style="width: 100%; height: 500px; padding-top: 20px;"></div>
		</blockquote>
	</div>
</form>
	<table class="layui-hide" id="gameInfoOrderTable"
		lay-filter="gameInfoOrderTable"></table>

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
			
			//时间下拉窗口
			laydate.render({
				elem : '#createTime1'
					,type:'datetime'
			});
			laydate.render({
				elem : '#createTime2'
					,type:'datetime'
			});

			table.render({
				elem : '#gameInfoOrderTable',
				url : 'gameInfo/getOrderListByPage',
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
					field : 'gameName',
					title : '游戏名称',
					align : 'center',
					width : 180,
					templet : function(data) {
						return data.gameName;
					}
				},{
					field : 'packageName',
					title : '包名',
					align : 'center',
					width : 250,
					templet : function(data) {
						return data.packageName;
					}
				},{
					field : 'cpName',
					title : 'cp',
					align : 'center',
					width : 120,
					templet : function(data) {
						return data.cpName;
					}
				},{
					field : 'orderCount',
					title : '订购量',
					align : 'center',
					templet : function(data) {
						return data.orderCount;
					}
				},{
					field : 'gameCount',
					title : '游戏点击量',
					align : 'center',
					templet : function(data) {
						return data.gameCount;
					}
				},{
					field : 'orderAllCount',
					title : '订购发起量',
					align : 'center',
					templet : function(data) {
						return data.orderAllCount;
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
					createLinePicture(res);
					createCircleOrderCountOrigin(res);
					createCirclGameCountOrigin(res);
					createCirclOrderAllCountOrigin(res);
					
				}
			});

			var customerIncrease = echarts.init(document.getElementById("customer"));
			var customerOriginOrderCount = echarts.init(document.getElementById("customer2"));
			var customerOriginGameCount = echarts.init(document.getElementById("customer3"));
			var customerOriginOrderAllCount = echarts.init(document.getElementById("customer4"));
			
			//线型图
			function createLinePicture(res){
				var orderCountArray = [];
				var gameCountArray = [];
				var orderAllCountArray = [];
				//同时画出柱状图
				var res_data = res.data;
				//构建json对象
				var data = {};
		//		var dataJson = {};  //构建json对象，日期为key 统计数值为value
				for(var i=0; i < res_data.length;i++ ){
		//			dataJson[res_data[i].gameName] = res_data[i].countPV;
					orderCountArray.push([res_data[i].gameName, res_data[i].orderCount]);
					gameCountArray.push([res_data[i].gameName, res_data[i].gameCount]);
					orderAllCountArray.push([res_data[i].gameName, res_data[i].orderAllCount]);
				}
		
				var option = {
					title : {
						text : '游戏数据统计'
					},
					tooltip : { //鼠标经过显示提示框
						show : true,
						trigger : "axis"
					},
					legend : { //不同的线的类型
						data : [ "总次数" ]
					},
					xAxis : {
						name : "游戏名称",
						type : 'category',
						axisLabel : {
							interval : 0,//代表显示所有x轴标签显示
							rotate : 25, //代表逆时针旋转45度
						}
					},
					yAxis : {
						name : "订购量/游戏点击量/订购发起",
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
						name : "订购量 ",
						data : [],
						type : 'line'
					},{
						name : "游戏点击量",
						data : [],
						type : 'line'
					},{
						name : "订购发起量",
						data : [],
						type : 'line'
					}]
				};
		
				//设置PV UV数据
				option.series[0].data = orderCountArray;
				option.series[1].data = gameCountArray;
				option.series[2].data = orderAllCountArray;
				customerIncrease.setOption(option);
				layer.close(tableload);
			}
			
			//圆型图orderCount订购量
			function createCircleOrderCountOrigin(res){
				var data = [];
				for(var i=0;i<res.data.length;i++){
					var json = {};
					json.name = res.data[i].gameName;
					json.value = res.data[i].orderCount;
					data.push(json);
				}
				var option2 = {
				    title : {
				        text: '游戏统计',
				        subtext: '订购量',
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
				customerOriginOrderCount.setOption(option2);
			}
			
			//圆型图gameCount游戏点击量
			function createCirclGameCountOrigin(res){
				var data = [];
				for(var i=0;i<res.data.length;i++){
					var json = {};
					json.name = res.data[i].gameName;
					json.value = res.data[i].gameCount;
					data.push(json);
				}
				var option2 = {
				    title : {
				        text: '游戏统计',
				        subtext: '游戏点击量',
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
				customerOriginGameCount.setOption(option2);
			}
			
			//圆型图orderAllCount订购发起量
			function createCirclOrderAllCountOrigin(res){
				var data = [];
				for(var i=0;i<res.data.length;i++){
					var json = {};
					json.name = res.data[i].gameName;
					json.value = res.data[i].orderAllCount;
					data.push(json);
				}
				var option2 = {
				    title : {
				        text: '游戏统计',
				        subtext: '订购发起量',
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
				customerOriginOrderAllCount.setOption(option2);
			}
			
			
			//CP下拉框
			layer.ready(function(){
				$.ajax({
					url: 'gameCp/getCpCombobox.do',
					dataType: 'json',
					type: 'get',
					success: function (data) {
						$.each(data, function (index, item) {
							/* 
							for(let key  in item){
						        alert(key+"---"+item[key]);
							}
							 */
						//	$('#cpId').append(new Option(item.name, item.id));// 下拉菜单里添加元素
							$("select[name=cpId]").append(new Option(item.name, item.id));
						});
						form.render("select");
					}
				})
				/* 	
					layui.form.render("select");
				 */
			});  

			
			//搜索功能
			var active = {
				reload : function() {
					var gameName = $("input[name='gameName']");
					var packageName = $("input[name='packageName']");
					var cpId = $("select[name='cpId']");
					var beginDate = $("input[name='beginDate']");
					var endDate = $("input[name='endDate']");
					//执行重载
					table.reload('gameInfoOrderTable', {
						page : {
							curr : 1
						//重新从第 1 页开始
						},
						where : {
							"gameName" : gameName.val(),
							"packageName" : packageName.val(),
							"cpId" : cpId.val(),
							"beginDate" : beginDate.val(),
							"endDate" : endDate.val()
						}
					});
				}
			};
			$('#searchBtn').on('click', function() {
				active['reload'] ? active['reload'].call(this) : '';
			});
			
			$("#export-button").click(function () {
				var url ='gameInfo/getOrderListExport.do';
				document.getElementById("search-form").action=url;
				document.getElementById("search-form").submit();
			});

		})
	</script>
</body>
</html>