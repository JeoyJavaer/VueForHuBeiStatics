<template>
<!--  游戏总PV/uV-->

  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>大厅统计</el-breadcrumb-item>
      <el-breadcrumb-item>游戏总PV/UV统计</el-breadcrumb-item>
      <el-breadcrumb-item>数据列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-row>
        <el-col :span="14">
          <span class="left-margin15">时间:</span>
          <!--日期选择框-->
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

          <span class="left-margin15">快速选择:</span>
          <el-select class="left-margin15" v-model="query.time" placeholder="日期" @change="timeOptionChange">
            <el-option
                v-for="item in quickTimeList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="8" class="left-margin30">
          <el-button class="left-margin15" type="success" icon="el-icon-search" @click="searchClick">查询</el-button>
          <el-button class="left-margin15" type="warning" icon="el-icon-error" @click="resetClick">重置</el-button>
          <el-button class="left-margin15" type="primary" icon="el-icon-error" @click="exportClick">导出</el-button>
        </el-col>


      </el-row>
      <div >
        <!-- 数据的表格展示-->
        <game-total-table :pvlist="tablePvList" :uvlist="tableUvList"  class="top-margin30"/>
        <game-total-click-p-v-pie :pvlist="pvPieList" class="top-margin15"/>
        <game-total-click-u-v-pie :uvlist="uvPieList" class="top-margin15"/>

        <el-table
            stripe
            border
            :data="dataList">
          <el-table-column
              type="index"
              indent="#"
              width="50">
          </el-table-column>
          <el-table-column
              prop="countDayTime"
              label="时间">
          </el-table-column>
          <el-table-column
              prop="countPV"
              label="PV">
          </el-table-column>
          <el-table-column
              prop="countUV"
              label="UV">
          </el-table-column>

        </el-table>
        <el-pagination
            class="user-list-pagination"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="this.query.page"
            :page-sizes="[10,  20, 50,100]"
            :page-size="this.query.limit"
            layout="total,prev, pager, next, jumper,sizes"
            :total="total">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
  import GameTotalTable from "./gametotalchild/GameTotalTable";
  import GameTotalClickPVPie from "./gametotalchild/GameTotalClickPVPie";
  import GameTotalClickUVPie from "./gametotalchild/GameTotalClickUVPie";
  export default {
    name: "GameTotal",
    components:{GameTotalTable,GameTotalClickPVPie,GameTotalClickUVPie},
    data() {
      return {
        query: {
          page: 1,
          limit: 10,
          startDate: '',
          endDate: '',
          time:'',

        },
        total: 0,

        quickTimeList: [
          {
            label: '今天',
            value: '1'
          },
          {
            label: '昨天',
            value: '2'
          },
          {
            label: '前天',
            value: '3'
          },
          {
            label: '本周',
            value: '4'
          },
          {
            label: '本月',
            value: '5'
          },
        ],

        dataList:[],
        tablePvList:[],
        tableUvList:[],
        tableOrderList:[],
        tableRateList:[],
        pvPieList:[],
        uvPieList:[]
      }
    },
    created() {
      this.loadData()
    },

    methods: {
      loadData() {
        this.getDataList()
      },

      async getDataList() {
        const res = await this.$http.get(`/hbydGame/count/getListDayCountPvUv?page=${this.query.page}&limit=${this.query.limit}`)
        this.dataList = res.data
        this.total = res.count



        var countPVArray = [];
        var countUVArray = [];

        //同时画出柱状图
        var res_data = res.data;

        var pvPieData = [];
        var uvPieData = [];

        for(var i=0; i < res_data.length;i++ ){


          countPVArray.push([res_data[i].countDayTime, res_data[i].countPV]);
          countUVArray.push([res_data[i].countDayTime, res_data[i].countUV]);


          var pvJson = {};
          pvJson.name = res_data[i].countDayTime;
          pvJson.value = res.data[i].countPV;
          pvPieData.push(pvJson);

          var uvJson = {};
          uvJson.name = res_data[i].countDayTime;
          uvJson.value = res.data[i].countUV;
          uvPieData.push(uvJson);
        }


        this.tablePvList=countPVArray
        this.tableUvList=countUVArray


        this.pvPieList=pvPieData
        this.uvPieList=uvPieData
      },
      searchClick() {

      },
      resetClick() {
        this.query.userId = ''
        this.query.pageName = ''
        this.query.recName = ''
        this.query.startDate = ''
        this.query.endDate = ''

      },
      exportClick() {

      },
      optionChange(option) {
        console.log(option);
      },

      handleSizeChange(pageSize) {
        this.query.limit = pageSize
        this.loadData()
      },
      handleCurrentChange(pageNum) {
        this.query.page = pageNum
        this.loadData()
      },

      timeOptionChange(opt){

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