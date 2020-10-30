<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 服务器信息 -->
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header" >服务器</div>
          <div class="layui-card-body" >
            <table class="layui-table">
              <colgroup>
                <col width="30%">
                <col width="70%">
              </colgroup>
              <tbody >
              <tr>
                <td>服务器IP</td>
                <td >{{systemInfo.serverIP}}</td>
              </tr>
              <tr>
                <td>服务器URL</td>
                <td >{{systemInfo.serverURL}}</td>
              </tr>
              <tr>
                <td>服务器类型</td>
                <td  id="server-type"></td>
              </tr>
              <tr>
                <td>服务器时间</td>
                <td  id="server-time"></td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- 系统概览 -->

      <!-- 操作系统 -->
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header" >操作系统</div>
          <div class="layui-card-body" >
            <table class="layui-table">
              <colgroup>
                <col width="30%">
                <col width="70%">
              </colgroup>
              <tbody >
              <tr>
                <td>操作系统名称</td>
                <td  id="system-name"></td>
              </tr>
              <tr>
                <td>操作系统版本</td>
                <td   id="system-version"></td>
              </tr>
              <tr>
                <td>用户名称</td>
                <td   id="user-name"></td>
              </tr>
              <tr>
                <td>用户主目录</td>
                <td   id="user-home"></td>
              </tr>
              <tr>
                <td>操作系统时区</td>
                <td   id="system-timezone"></td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- 操作系统-->

      <!-- 物理内存 -->
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header" >物理内存</div>
          <div class="layui-card-body" >
            <table class="layui-table">
              <colgroup>
                <col width="30%">
                <col width="70%">
              </colgroup>
              <thead>
              <tr>
                <td>物理内存总量</td>
                <td>内存使用率<!-- <button id="btn-memory" class="layui-btn" style="float:right">动态刷新</button> --></td>
              </tr>
              </thead>
              <tbody >
              <tr>
                <td  id="memory-total"></td>
                <td  id="memory-used" style="line-height: 40px;"></td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- 物理内存-->

      <!-- CPU -->
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header" >CPU信息</div>
          <div class="layui-card-body" >
            <table class="layui-table">
              <colgroup>
                <col width="30%">
                <col width="70%">
              </colgroup>
              <thead>
              <tr>
                <td>总频率</td>
                <td>使用率<!-- <button id="btn-cpu" class="layui-btn" style="float:right">动态刷新</button> --></td>
              </tr>
              </thead>
              <tbody id="cpu-tbody">
              <tr>
                <td>1000Mhz</td>
                <td>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- 操作系统-->

      <!-- 网卡 -->
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header" >网卡信息</div>
          <div class="layui-card-body" >
            <table class="layui-table">
              <colgroup>
                <col width="30%">
                <col width="70%">
              </colgroup>
              <thead>
              <tr>
                <td>网卡名称</td>
                <td>IP</td>
              </tr>
              </thead>
              <tbody id="net-tbody">
              <tr>
                <td>操作系统名称</td>
                <td>0.0.0.0</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- 网卡-->

      <!-- Java虚拟机-->
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header" >Java虚拟机</div>
          <div class="layui-card-body" >
            <table class="layui-table">
              <colgroup>
                <col width="30%">
                <col width="70%">
              </colgroup>
              <tbody >
              <tr>
                <td>JDK安装路径</td>
                <td id="java-path"></td>
              </tr>
              <tr>
                <td>JDK供应商</td>
                <td id="java-vendor"></td>
              </tr>
              <tr>
                <td>JDK版本</td>
                <td id="java-version"></td>
              </tr>
              <tr>
                <td>JDK名称</td>
                <td id="java-name"></td>
              </tr>
              <tr>
                <td>虚拟机版本</td>
                <td id="jvm-version"></td>
              </tr>
              <tr>
                <td>虚拟机总内存</td>
                <td id="jvm-total-memory"></td>
              </tr>
              <tr>
                <td>虚拟机剩余内存</td>
                <td id="jvm-free-memory"></td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- java虚拟机-->

      <!-- 数据库 -->
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header" >数据库</div>
          <div class="layui-card-body" >
            <table class="layui-table">
              <colgroup>
                <col width="30%">
                <col width="70%">
              </colgroup>
              <tbody >
              <tr>
                <td>数据库类型</td>
                <td id="database-type"></td>
              </tr>
              <tr>
                <td>数据库版本</td>
                <td id="database-version"></td>
              </tr>
              <tr>
                <td>数据库驱动</td>
                <td id="database-driver"></td>
              </tr>
              <tr>
                <td>数据库驱动版本</td>
                <td id="driver-version"></td>
              </tr>
              <tr>
                <td>数据库URL</td>
                <td id="jdbc-url"></td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "SystemInfo",
    data(){
      return{
          systemInfo:{}
      }
    },
    created() {
      this.getSystemInfo()
    },

    methods:{
      async getSystemInfo(){

        const  res= await this.$http.get('hbydGame/system/info')

        this.systemInfo=res.serverInfo

      }
    }
  }
</script>

<style scoped>

</style>