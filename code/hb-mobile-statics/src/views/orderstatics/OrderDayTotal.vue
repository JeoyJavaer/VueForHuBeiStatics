<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>订购数据查询</el-breadcrumb-item>
      <el-breadcrumb-item>每日订购金额总计</el-breadcrumb-item>
      <el-breadcrumb-item>订购数据</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row>
        <!--日期选择框-->
        <el-col :span="24">
          <div>
            <!--cp选择器-->
            <span>CP:</span>
            <el-select class="left-margin15" v-model="query.cp" placeholder="请选择CP" @change="cpOptionChange">
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
            <el-select class="left-margin15" v-model="query.time" placeholder="日期" @change="timeOptionChange">
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
      <!--折线图-->
      <c-p-total-fee-chart class="top-margin30" :feelist="feeList"/>
      <!--饼图-->
      <c-p-total-fee-pie class="top-margin30" :feepielist="feepielist"/>


      <!-- 数据的表格展示-->
      <el-table
          stripe
          border
          :data="feeData">
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
            label="CP名称">
        </el-table-column>
        <el-table-column
            prop="countTotalFee"
            label="实际消费金额">
        </el-table-column>
        <el-table-column
            prop="countDay"
            label="日期">
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
  import CPTotalFeeChart from "./daytotal/CPTotalFeeChart";
  import CPTotalFeePie from "./daytotal/CPTotalFeePie";

  export default {
    name: "OrderDayTotal",
    components: {CPTotalFeeChart, CPTotalFeePie},
    data() {
      return {
        cpList: [],
        feeData:[],
        feeList: [],
        feepielist: [],

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

        total: 0
      }
    },

    methods: {
      loadData() {
        this.getCpListInfo()
        this.getFeeInfo()
      },

      //cp 列表
      async getCpListInfo() {
        const res = await this.$http.get(`/hbydGame/gameCp/getCpCombobox.do`)
        this.cpList = res
      },

      async getFeeInfo() {
        const res = await this.$http.get(`/hbydGame/billMonth/getCountTotalFee?page=${this.query.page}&limit=${this.query.limit}`)
        this.feeData = res.data
        this.total = res.count


        var countDayArray = [];
        var countTotalFeeArray = [];


        for (let item of res.data) {
          var key = item.countDay + "/" + item.cpName
          countDayArray.push([key, item.countTotalFee]);

          var json={}
          json.name = key;
          json.value = item.countTotalFee;
          countTotalFeeArray.push(json);
        }

        this.feeList = countDayArray
        this.feepielist=countTotalFeeArray
      },
      timeOptionChange(opt) {

      },

      cpOptionChange(opt) {

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
      handleSizeChange(pageSize) {
        this.query.limit = pageSize
        this.loadData()
      },

      handleCurrentChange(pageNum) {
        this.query.page = pageNum
        this.loadData()
      },

    },
    created() {
      this.loadData()
    },


  }
</script>

<style scoped>

</style>