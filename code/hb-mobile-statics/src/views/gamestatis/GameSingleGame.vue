<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>游戏数据查询</el-breadcrumb-item>
      <el-breadcrumb-item>单个游戏</el-breadcrumb-item>
      <el-breadcrumb-item>游戏列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row>
        <el-col :span="4">
          <span>游戏名称:</span>
          <el-input
              class="input-username"
              clearable
              placeholder="游戏名称"
              v-model="query.gameName">
          </el-input>
        </el-col>

        <!--目标-->
        <el-col :span="4">
          <span>包名:</span>
          <el-input
              class="input-username"
              clearable
              placeholder="包名"
              v-model="query.packageName">
          </el-input>
        </el-col>


        <el-col :span="15">
          <!--用户行为选择器-->
          <span>所属CP:</span>
          <el-select class="left-margin15" v-model="query.cpId" placeholder="请选择" @change="optionChange">
            <el-option
                v-for="item in cpList"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
          <span class="left-margin30">时间:</span>
          <!--日期选择框-->
          <span>查询日期:</span>
          <el-date-picker
              class="left-margin15"
              v-model="query.beginDate"
              type="datetime"
              placeholder="开始日期"
              default-time="12:00:00">
          </el-date-picker>
          <span> - </span>
          <el-date-picker
              v-model="query.endDate"
              type="datetime"
              default-time="12:00:00"
              placeholder="结束日期">
          </el-date-picker>

        </el-col>

      </el-row>
      <el-row class="top-margin30">
        <el-col :span="8">
          <el-button class="left-margin15" type="success" icon="el-icon-search" @click="searchClick">查询</el-button>
          <el-button class="left-margin15" type="warning" icon="el-icon-error" @click="resetClick">重置</el-button>
          <el-button class="left-margin15" type="primary" icon="el-icon-error" @click="exportClick">导出</el-button>
        </el-col>
      </el-row>

      <div class="data-container">
        <!-- 数据的表格展示-->
        <el-table
            stripe
            border
            :data="gameList">
          <el-table-column
              type="index"
              indent="#"
              width="50">
          </el-table-column>
          <el-table-column
              prop="gameName"
              label="游戏名称">
          </el-table-column>
          <el-table-column
              prop="packageName"
              label="包名">
          </el-table-column>
          <el-table-column
              prop="cpName"
              width="80"
              label="CP">
          </el-table-column>
          <el-table-column
              prop="yesterdayCountPV"
              label="订购量">
          </el-table-column>
          <el-table-column
              prop="countPV"
              label="用户点击量">
          </el-table-column>
          <el-table-column
              prop="countUV"
              label="用户量">
          </el-table-column>
          <el-table-column
              prop="yesterdayCountUV"
              label="订购发起量">
          </el-table-column>

        </el-table>
        <el-pagination
            class="user-list-pagination"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="this.query.page"
            :page-sizes="[10,  20, 50]"
            :page-size="this.query.limit"
            layout="total,prev, pager, next, jumper,sizes"
            :total="total">
        </el-pagination>
      </div>
    </el-card>

  </div>
</template>

<script>
  export default {
    name: "GameSingle",
    data() {
      return {
        query:{
          page:1,
          limit:10,
          gameName:'',
          packageName:'',
          beginDate:'',
          endDate:'',
          cpId:''
        },
        gameList:[],
        cpList:[],
        total:0
      }
    },

    created() {
      this.loadData()
    },
    methods:{
      loadData(){
        this.getCpInfo()
        this.getGameListInfo()
      },

      async getCpInfo(){
        const res= await  this.$http.get(`/hbydGame/gameCp/getCpCombobox.do`)
        this.cpList=res
      },

      async getGameListInfo(){
        const  res= await this.$http.get(`/hbydGame/leadboardGame/leadboardGameDay?page=${this.query.page}&limit=${this.query.limit}&gameName=${this.query.gameName}&packageName=${this.query.packageName}&cpId=${this.query.cpId}`)
        this.gameList=res.data
        this.total=res.count
      },
      handleSizeChange(pageSize){
        this.query.limit=pageSize
      },
      handleCurrentChange(pageNum){
        this.query.page=pageNum
      },
      optionChange(option){
        console.log(option);
      },
      searchClick(){

      },
      resetClick(){

      },
      exportClick(){

      }
    }
  }
</script>

<style scoped>

  .input-username {
    width: 60%;
    margin-left: 15px;
  }
</style>
