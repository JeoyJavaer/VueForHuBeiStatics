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
			<label style="margin-left: 10px;" class="layui-label">用户Id</label>
			<div style="width: 100px;" class="layui-inline">
				<input type="text" name="userId" lay-verify="required"
					style="width: 100%;" placeholder="" autocomplete="off"
					class="layui-input" />
			</div>
			
			<label style="margin-left: 10px;" class="layui-label">页面名称</label>
			<div style="width: 100px;" class="layui-inline">
				<input type="text" name="activityName" lay-verify="required"
					style="width: 100%;" placeholder="" autocomplete="off"
					class="layui-input" />
			</div>
			
			<label style="margin-left: 10px;" class="layui-label">类型</label>
			<div style="width: 100px;" class="layui-inline">
				<select  name="type" style="width: 100%;" lay-verify="required">
					<option value=""></option>
					<option value="0">轮播位</option>
					<option value="1">小游戏</option>
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
    
	<table class="layui-hide" id="activityGameTable"
		lay-filter="activityGameTable"></table>


	<script type="text/javascript">
	layui.use([ 'table', 'form', 'laydate'], function() {
		var layer = layui.layer;
		var tableload = layer.load(3); //加载等待样式
		var table = layui.table;
		var $ = layui.$;
		var form = layui.form;
		var laydate = layui.laydate;
		
		form.render("select");
		
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
			elem : '#activityGameTable',
			url : 'activityGame/getListByPage',
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
					field : 'userId',
					title : '用户Id',
					align : 'center',
					width : 150,
					templet : function(data) {
						return data.userId;
					}
				},{
					field : 'activityName',
					title : '页面名称',
					align : 'center',
					templet : function(data) {
						return data.activityName;
					}
				},{
					field : 'typeFmt',
					title : '类型',
					align : 'center',
					templet : function(data) {
						return data.typeFmt;
					}
				},{
					field : 'createDateTimeFmt',
					title : '创建时间',
					align : 'center',
					width : 200,
					templet : function(data) {
						return data.createDateTimeFmt;
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
		
		//搜索功能
		var active = {
			reload : function() {
				var userId = $("input[name='userId']");
				var activityName = $("input[name='activityName']");
				var type = $("select[name='type']");
				var beginDate = $("input[name='beginDate']");
				var endDate = $("input[name='endDate']");
				//执行重载
				table.reload('activityGameTable', {
					page : {
						curr : 1
					//重新从第 1 页开始
					},
					where : {
						"userId" : userId.val(),
						"activityName" : activityName.val(),
						"type" : type.val(),
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
			var url ='activityGame/getListExport.do';
			document.getElementById("search-form").action=url;
			document.getElementById("search-form").submit();
		});
		
		
	})
		
		
	</script>
</body>
</html>