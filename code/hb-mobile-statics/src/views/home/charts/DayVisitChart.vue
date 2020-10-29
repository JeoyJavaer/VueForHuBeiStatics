<template>
  <div>
    <table-echart :options="visitChartOption"/>
  </div>
</template>

<script>

  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "DayVisitChart",
    components: {TableEchart},

    props: {
      pvlist: [],
      uvlist: [],
      orderlist: [],
      ratelist: []
    },
    data() {
      return {
        visitChartOption: {
          title: {
            text: '每日PV/UV订购量转化率'
          },
          tooltip: {
            show: true,
            trigger: "axis",
            axisPointer: {
              type: 'cross',
              label: {
                backgroundColor: '#E9EEF3'
              }
            }
          },
          legend: {  //不同的线的类型
            data: ["订购订单数", "订购人数"],
          },

          xAxis: {
            name: "时间",
            type: 'category',
          },
          yAxis: {
            name: "数量",
            type: 'value',
          },

          series: [{
            name: "PV",
            data: this.pvlist,
            type: 'line'
          }, {
            name: "UV",
            data: this.uvlist,
            type: 'line'
          }, {
            name: "订购量",
            data: this.orderlist,
            type: 'line'
          }, {
            name: "转化率",
            data: this.ratelist,
            type: 'line'
          }],

        },
      }
    }
  }
</script>

<style scoped>

</style>