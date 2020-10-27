<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>
<style type="text/css">
.layui-form-item{
    margin-top: -1px;
    margin-left:60px;
}
.layui-input , .layui-textarea{
    border: none;
}
</style>
<base href="<%=request.getContextPath() + '/'%>" />
</head>
<body>
<form class="layui-form" id="search-form">
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
				<label class="layui-form-label">时间</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="createTime1"
						name="dayDate" placeholder="yyyy-MM-dd">
				</div>
			</div>
			
			<br>
			<br>
			
			<div class="layui-input-inline" style="width: 500px;">
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
			</div>
		</blockquote>
	</div>
</form>

	<script type="text/html" id="btnGroup">
		<shiro:hasPermission name="1002">
			<i class="layui-icon layui-icon-picture" lay-event="picture"></i> 
		</shiro:hasPermission>
    </script>
    
	<table class="layui-hide" id="leadboardGameTable"
		lay-filter="leadboardGameTable"></table>


	<script type="text/javascript">
	layui.use([ 'table', 'form', 'laydate'], function() {
		var layer = layui.layer;
		var tableload = layer.load(3); //加载等待样式
		var table = layui.table;
		var $ = layui.$;
		var form = layui.form;
		var laydate = layui.laydate;
		
		//时间下拉窗口
		laydate.render({
			elem: '#createTime1'
		});
		
		table.render({
			elem : '#leadboardGameTable',
			url : 'leadboardGame/leadboardGameDay',
		//	toolbar: 'default' ,
		/* 
			defaultToolbar: [
				'filter', 
				'print', 
				'exports'
			],
		 */	
			cellMinWidth : 50,//全局定义常规单元格的最小宽度，layui 2.2.1 新增
			cols : [ [ 
				{
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
				/* 
				{
					field : 'id',
					title : '编号',
					align : 'center',
					width : 60,
					templet : function(data) {
						return data.id;
					}
				},
				 */
				{
					field : 'gameRank',
					title : '排名',
					align : 'center',
					width : 70,
					templet : function(data) {
						return data.gameRank;
					}
				},{
					field : 'rankChange',
					title : '趋势',
					align : 'center',
					width : 80,
					templet : function(data) {
						if(data.rankChange>0){
							return "<i class='layui-icon layui-icon-up'  style='color:green; margin-right:5px'> </i>" + data.rankChange;
						}
						if(data.rankChange<0){
							return "<i class='layui-icon layui-icon-down' style='color:red; margin-right:5px'> </i>" + data.rankChange;
						}
						if(data.rankChange==0){
							return data.rankChange;
						}
						if(data.rankChange=="new"){
							return data.rankChange;
						}
						
					}
				},{
					field : 'gameName',
					title : '游戏名称',
					align : 'center',
					width : 200,
					templet : function(data) {
						return data.gameName;
					}
				},{
					field : 'packageName',
					title : '包名',
					align : 'center',
					templet : function(data) {
						return data.packageName;
					}
				},{
					field : 'cpName',
					title : 'CP',
					align : 'center',
					width : 100,
					templet : function(data) {
						return data.cpName;
					}
				},{
					field : 'countPV',
					title : 'PV',
					align : 'center',
					width : 100,
					templet : function(data) {
						return data.countPV;
					}
				},{
					field : 'countUV',
					title : 'UV',
					align : 'center',
					width : 100,
					templet : function(data) {
						return data.countUV;
					}
				},{
					field : 'yesterdayCountPV',
					title : '前一日PV',
					align : 'center',
					width : 100,
					templet : function(data) {
						return data.yesterdayCountPV;
					}
				},{
					field : 'yesterdayCountUV',
					title : '前一日UV',
					align : 'center',
					width : 100,
					templet : function(data) {
						return data.yesterdayCountUV;
					}
				},{
					field : 'countDayTime',
					title : '时间',
					align : 'center',
					width : 150,
					templet : function(data) {
						return data.countDayTime;
					}
				}
			] ],
			loading : true //分页加载样式
			,
			page : true//是否开启分页
			,
			loading : true //分页加载样式
			,
			done : function() {
				layer.close(tableload);
			}
		});
		
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
				var dayDate = $("input[name='dayDate']");
				var cpId = $("select[name='cpId']");
				//执行重载
				table.reload('leadboardGameTable', {
					page : {
						curr : 1
					//重新从第 1 页开始
					},
					where : {
						"gameName" : gameName.val(),
						"packageName" : packageName.val(),
						"dayDate" : dayDate.val(),
						"cpId" : cpId.val(),
					}
				});
			}
		};
		$('#searchBtn').on('click', function() {
			active['reload'] ? active['reload'].call(this) : '';
		});
		
		
		$("#export-button").click(function () {
			var url ='leadboardGame/leadboardGameDayExport.do';
			document.getElementById("search-form").action=url;
			document.getElementById("search-form").submit();
		});
		

	})
		
		
	</script>
</body>
</html>