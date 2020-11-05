<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>订购数据查询</el-breadcrumb-item>
      <el-breadcrumb-item>CP订购统计</el-breadcrumb-item>
      <el-breadcrumb-item>订购数据</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row>
        <!--日期选择框-->
        <el-col :span="24">
          <div>
            <!--cp选择器-->
            <span >CP:</span>
            <el-select class="left-margin15" v-model="query.cp" placeholder="请选择CP" @change="optionChange">
              <el-option
                  v-for="item in cpList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>

            <span class="left-margin15">查询日期:</span>
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
            <el-select class="left-margin15" v-model="query.time" placeholder="日期" @change="optionChange">
              <el-option
                  v-for="item in quickTimeList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </div>
        </el-col>
      </el-row>

      <el-row class="top-margin30">
        <el-col :span="8">
          <el-button class="left-margin15" type="success" icon="el-icon-search" @click="searchClick">查询</el-button>
          <el-button class="left-margin15" type="warning" icon="el-icon-error" @click="resetClick">重置</el-button>
          <el-button class="left-margin15" type="primary" icon="el-icon-error" @click="exportClick">导出</el-button>
        </el-col>
      </el-row>
      <!--  折线图  -->
      <c-p-visit-chart class="top-margin30" :clicklist="clickList"  :orderlist="orderlist" :transactionlist="transactionlist"/>
      <!--订购量饼图-->
      <c-p-order-pie-chart class="top-margin30" :orderlist="pieorderlist"/>
      <!--点击量饼图-->
      <c-p-click-pie-chart class="top-margin30" :clicklist="pieclicklist"/>
      <!--订购发起量饼图-->
      <c-p-transaction-pie-chart class="top-margin30" :transactionlist="pietransactionlist"/>

      <!-- 数据的表格展示-->
      <el-table
          stripe
          border
          :data="staticList">
        <el-table-column
            type="index"
            indent="#"
            width="50">
        </el-table-column>
        <el-table-column
            prop="cpId"
            label="cpid">
        </el-table-column>
        <el-table-column
            prop="cpName"
            label="CP">
        </el-table-column>
        <el-table-column
            prop="orderCount"
            label="订购量">
        </el-table-column>
        <el-table-column
            prop="gameCount"
            label="游戏点击量">
        </el-table-column>
        <el-table-column
            prop="orderAllCount"
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
    </el-card>
  </div>
</template>

<script>

  import CPVisitChart from "./ordercp/CPVisitChart";
  import CPOrderPieChart from "./ordercp/CPOrderPieChart";
  import CPClickPieChart from "./ordercp/CPClickPieChart";
  import CPTransactionPieChart from "./ordercp/CPTransactionPieChart";
  export default {
    name: "OrderCP",
    components: {CPVisitChart,CPOrderPieChart,CPClickPieChart,CPTransactionPieChart},
    data() {
      return {
        cpList: [],
        staticList: [],
        visitList: [],
        query: {
          page: 1,
          limit: 10,
          beginDate: '',
          endDate: '',
          cp: '',
          quickTime: ''
        },
        totalStatic: 0,
        totalVisit: 0,
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

        clickList:[],
        orderlist:[],
        transactionlist:[],
        pieorderlist:[],
        pieclicklist:[],
        pietransactionlist:[],
        total:0
      }
    },

    created() {
      this.loadData()
    },
    methods: {

      loadData() {
        this.getCpListInfo()
        this.getStaticInfo()
        this.getDayVisit()
      },
      //cp的统计数据信息
      async getStaticInfo() {
        const res = await this.$http.get(`/hbydGame/gameCpOrder/getListByPage?page=${this.query.page}&limit=${this.query.limit}`)
        // console.log(res);
        this.staticList = res.data
        this.total = res.count

        var orderCountArray = [];
        var gameCountArray = [];
        var orderAllCountArray = [];

        var  pieOrderArray=[]
        var  pieClickArray=[]
        var  pietransactionArray=[]

        for (let  item of res.data){
          let cpName = item.cpName;
          orderCountArray.push([cpName, item.orderCount]);
          gameCountArray.push([cpName, item.gameCount]);
          orderAllCountArray.push([cpName, item.orderAllCount]);

          var orderjson = {};
          orderjson.name = cpName;
          orderjson.value = item.orderCount;
          pieOrderArray.push(orderjson);

          var clickjson = {};
          clickjson.name = cpName;
          clickjson.value = item.gameCount;
          pieClickArray.push(clickjson);

          var transjson = {};
          transjson.name = cpName;
          transjson.value = item.orderAllCount;
          pietransactionArray.push(transjson);

        }

        this.clickList=gameCountArray
        this.orderlist=orderCountArray
        this.transactionlist=orderAllCountArray

        this.pieorderlist=pieOrderArray
        this.pieclicklist=pieClickArray
        this.pietransactionlist=pietransactionArray

      },

      //cp 列表
      async getCpListInfo() {
        const res = await this.$http.get(`/hbydGame/gameCp/getCpCombobox.do`)
        // console.log(res);
        this.cpList = res
      },

      async getDayVisit() {
        const res = await this.$http.get(`/hbydGame/gameVisit/getListDayCountPvUv?page=1&limit=10`)

        this.visitList = res.data
        this.total=res.count
      },
      optionChange() {

      },

      searchClick() {
        console.log(this.query);
      },
      exportClick() {

      },

      resetClick() {
        this.query.page = 1
        this.query.limit = 10
        this.query.beginDate = ''
        this.query.endDate = ''
        this.query.cp = ''
        this.loadData()
      },
      handleSizeChange(pageSize){
        this.query.page=pageSize
        this.loadData()
      },

      handleCurrentChange(pageNum){
        this.query.limit=pageNum
        this.loadData()
      }

    }
  }
</script>

<style scoped>

</style>
