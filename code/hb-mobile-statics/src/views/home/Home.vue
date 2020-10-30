<template>
  <!--主页面中首页的数据-->
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>数据概览</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row>
        <!--日期选择框-->
        <el-col :span="10">
          <div>
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
          </div>
        </el-col>
        <el-col :span="8">
          <el-button class="left-margin15" type="success" icon="el-icon-search" @click="searchClick">查询</el-button>
          <el-button class="left-margin15" type="info" icon="el-icon-error" @click="resetClick">重置</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-card class="no-border">
            <span>总订购量:{{this.totalOrder}}</span>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="no-border">
            <span>总PV:{{this.totalPV}}</span>
          </el-card>
        </el-col>
        <el-col :span="7">
          <el-card class="no-border">
            <span>总UV:{{this.totalUV}}</span>
          </el-card>
        </el-col>
      </el-row>
      <div class="stat-container top-margin30">
        <div class="visit">
          <day-visit-chart
              :pvlist="visitChart.PVList"
              :uvlist="visitChart.UVList"
              :orderlist="visitChart.OrderList"
              :ratelist="visitChart.rateList"/>
        </div>
        <div class="user">
          <user-chart
              :newlist="userChart.newList"
              :activelist="userChart.activeList"/>
        </div>
        <div class="order">
          <order-chart
              :neworderlist="orderChart.newOrderList"
              :userorderlist="orderChart.userOrderList"
          />
        </div>
        <div class="amount">
          <amount-chart
              :pricelist="amountChart.priceList"/>
        </div>
      </div>
    </el-card>

  </div>
</template>

<script>

  import DayVisitChart from "./charts/DayVisitChart";
  import UserChart from "./charts/UserChart";
  import OrderChart from "./charts/OrderChart";
  import AmountChart from "./charts/AmountChart";

  export default {
    name: "Home",
    components: {DayVisitChart, UserChart, OrderChart, AmountChart},
    data() {
      return {
        query: {
          beginDate: '',
          endDate: ''
        },

        visitChart: {
          PVList: [],
          UVList: [],
          OrderList: [],
          rateList: []
        },
        userChart: {
          newList: [],
          activeList: []
        },
        orderChart: {
          newOrderList: [],
          userOrderList: []
        },
        amountChart: {
          priceList: []
        },
        totalPV: 0,
        totalUV: 0,
        totalOrder:0,
      }
    },

    computed: {
      //
      // totalOrder() {
      //   return 0
      // },

    },

    mounted() {
      this.initEcharts()
    },

    methods: {
      searchClick() {
        console.log(this.query.endDate);
        let endDate = this.query.endDate;
        let date = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate());
        console.log(date);

      },

      resetClick() {
        this.query.beginDate = ''
        this.query.endDate = ''
      },

      initEcharts() {
        this.getTotalPVUV()
        this.initVisitChart()
        this.initUserChart()
        this.initOrderChart()
        this.initAmountCharts()
      },

      async getTotalPVUV() {
        //获取指定日期的总计 pv，uv  {"sumUv":"58512","sumPv":"231787"}
        // const res = await this.$http.get(`/hbydGame/gameVisit/getSumPvUv?beginDate=${this.beginDate}&endDate=${this.endDate}`)
        const res = await this.$http.get(`/hbydGame/gameVisit/getSumPvUv`)
        console.log(res);
        this.totalPV = res.sumPv
        this.totalUV = res.sumUv
      },

      async initVisitChart() {
        this.visitChartOption = {
          color: ['#f44'],
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          xAxis: [
            {
              type: 'category',
              data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月',],
              axisTick: {
                alignWithLabel: true
              }
            }
          ],
          yAxis: [
            {
              type: 'value'
            }
          ],
          series: [
            {
              name: '每月花费',
              type: 'bar',
              barWidth: '60%',
              data: [995, 666, 444, 858, 654, 236, 645, 546, 846, 225, 547, 356]
            }
          ]
        };

        //获取每日的pvUV
        // const res = await this.$http.get(`/hbydGame/gameVisit/getListDayCountPvUv?page=1&limit=7&beginDate=${this.beginDate}&endDate=${this.endDate}`)
        const res = await this.$http.get(`/hbydGame/gameVisit/getListDayCountPvUv?page=1&limit=7`)
        this.processVisitCountData(res)
      },
      processVisitCountData(res) {


        //1 总的订单量


        //2每天的pv，UV等数据
        var countPVArray = [];
        var countUVArray = [];
        var countOrderArray = [];
        var countOrderRateArray = [];
        var totalCount=0
        for (let item of res.data) {
          let splits = item.countDayTime.split("-")
          let time = splits[1] + "-" + splits[2]
          countPVArray.push([time, item.countPV]);
          countUVArray.push([time, item.countUV]);
          countOrderArray.push([time, item.countOrder]);
          countOrderRateArray.push([time, item.countOrderRate.split("%")[0]]);
          totalCount+=item.countOrder
        }
        this.visitChart.PVList = countPVArray
        this.visitChart.UVList = countUVArray
        this.visitChart.OrderList = countOrderArray
        this.visitChart.rateList = countOrderRateArray
        this.totalOrder=totalCount

      },

      async initUserChart() {
        //获取活跃用户和新增用户数据
        const res = await this.$http.post(`/hbydGame/homePage/countUser`, {'beginDate': '', 'endDate': ''})
        this.processUserData(res)
      },

      processUserData(res) {
        var countNewUserArray = [];  //客户增加量数组
        var countActiveUserArray = [];  //客户损失量数组


        var countNewUser = res.countNewUser; //客户增加量
        var countActiveUser = res.countActiveUser; //客户损失量
        var betweenDate = res.betweenDate;
        //构建近day天前的日期json对象
        var data = {};
        for (var i = 0; i < betweenDate; i++) {
          var myDate = new Date();
          var lw = new Date(myDate - 1000 * 60 * 60 * 24 * i);//最后一个数字 i天的意思
          var lastY = lw.getFullYear();
          var lastM = lw.getMonth() + 1;
          var lastD = lw.getDate();
          var startdate = (lastM < 10 ? "0" + lastM : lastM) + "-" + (lastD < 10 ? "0" + lastD : lastD);
          data[startdate] = 0;
        }

        var countNewUserJson = {};  //构建json对象，日期为key 统计数值为value
        for (var i = 0; i < countNewUser.length; i++) {
          countNewUserJson[countNewUser[i].date] = countNewUser[i].count;
        }

        var countActiveUserJson = {};//构建json对象，日期为key 统计数值为value
        for (var i = 0; i < countActiveUser.length; i++) {
          countActiveUserJson[countActiveUser[i].date] = countActiveUser[i].count;
        }

        //构建图表data数组
        for (var key in data) {
          if (countNewUserJson[key] != null) {
            countNewUserArray.unshift([key, countNewUserJson[key]]);
          } else {
            countNewUserArray.unshift([key, data[key]]);
          }


          if (countActiveUserJson[key] != null) {
            countActiveUserArray.unshift([key, countActiveUserJson[key]]);
          } else {
            countActiveUserArray.unshift([key, data[key]]);
          }

        }

        this.userChart.newList = countNewUserArray
        this.userChart.activeList = countActiveUserArray
      },

      async initOrderChart() {
        //获取订单新增趋势
        const res = await this.$http.get(`/hbydGame/homePage/countOrder`, {'beginDate': '', 'endDate': ''})

        this.processOrderData(res)
      },

      processOrderData(res) {
        var countNewOrderArray = [];  //订购新增统计
        var countUserOrderArray = [];  //订购总统计


        var countNewOrder = res.countNewOrder; //客户增加量
        var countUserOrder = res.countUserOrder; //客户损失量
        var betweenDate = res.betweenDate;

        //构建近day天前的日期json对象
        var data = {};
        for (var i = 0; i < betweenDate; i++) {
          var myDate = new Date();
          var lw = new Date(myDate - 1000 * 60 * 60 * 24 * i);//最后一个数字 i天的意思
          var lastY = lw.getFullYear();
          var lastM = lw.getMonth() + 1;
          var lastD = lw.getDate();
          var startdate = (lastM < 10 ? "0" + lastM : lastM) + "-" + (lastD < 10 ? "0" + lastD : lastD);
          data[startdate] = 0;
        }

        var countNewOrderJson = {};  //构建json对象，日期为key 统计数值为value
        for (var i = 0; i < countNewOrder.length; i++) {
          countNewOrderJson[countNewOrder[i].date] = countNewOrder[i].count;
        }

        var countUserOrderJson = {};//构建json对象，日期为key 统计数值为value
        for (var i = 0; i < countUserOrder.length; i++) {
          countUserOrderJson[countUserOrder[i].date] = countUserOrder[i].count;
        }

        //构建图表data数组
        for (var key in data) {
          if (countNewOrderJson[key] != null) {
            countNewOrderArray.unshift([key, countNewOrderJson[key]]);
          } else {
            countNewOrderArray.unshift([key, data[key]]);
          }


          if (countUserOrderJson[key] != null) {
            countUserOrderArray.unshift([key, countUserOrderJson[key]]);
          } else {
            countUserOrderArray.unshift([key, data[key]]);
          }

        }

        this.orderChart.userOrderList = countUserOrderArray
        this.orderChart.newOrderList = countNewOrderArray
      },


      async initAmountCharts() {
        //获取总的金额
        const res = await this.$http.post(`/hbydGame/homePage/countPrice`, {'beginDate': '', 'endDate': ''})
        this.processTotalData(res)
      },

      processTotalData(res) {
        var countNewPriceArray = [];
        var countNewPrice = res.countNewPrice; //新增金额
        var betweenDate = res.betweenDate;
        //构建近day天前的日期json对象
        var data = {};
        for (var i = 0; i < betweenDate; i++) {
          var myDate = new Date();
          var lw = new Date(myDate - 1000 * 60 * 60 * 24 * i);//最后一个数字 i天的意思
          var lastY = lw.getFullYear();
          var lastM = lw.getMonth() + 1;
          var lastD = lw.getDate();
          var startdate = (lastM < 10 ? "0" + lastM : lastM) + "-" + (lastD < 10 ? "0" + lastD : lastD);
          data[startdate] = 0;
        }

        var countNewPriceJson = {};  //构建json对象，日期为key 统计数值为value
        for (var i = 0; i < countNewPrice.length; i++) {
          countNewPriceJson[countNewPrice[i].date] = countNewPrice[i].count;
        }


        //构建图表data数组
        for (var key in data) {
          if (countNewPriceJson[key] != null) {
            countNewPriceArray.unshift([key, countNewPriceJson[key]]);
          } else {
            countNewPriceArray.unshift([key, data[key]]);
          }
        }

        this.amountChart.priceList = countNewPriceArray

      }


    }
  }
</script>

<style scoped>
  .no-border {

  }

  .stat-container {
    flex: 1;
    width: 100%;
    height: 630px;
  }

  .visit {
    width: 50%;
    height: 50%;
    float: left;

  }

  .user {
    width: 50%;
    height: 50%;
    float: left;

  }

  .order {
    width: 50%;
    height: 50%;
    float: left;

  }

  .amount {
    width: 50%;
    height: 50%;
    float: left;
  }
</style>