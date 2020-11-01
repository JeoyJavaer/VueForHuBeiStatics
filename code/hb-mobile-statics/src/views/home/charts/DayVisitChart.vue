<template>
  <div>
    <table-echart :options="visitChartOption" elid="dayvisitchart" ref="dayvisitcharttable">
      <div id="dayvisitchart"></div>
    </table-echart>
  </div>
</template>

<script>

  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "DayVisitChart",
    components: {TableEchart},

    props: {
      pvlist: {
        type: Array,
        default() {
          return [];
        }
      },
      uvlist: {
        type: Array,
        default() {
          return [];
        }
      },
      orderlist: {
        type: Array,
        default() {
          return {
            type: Array,
            default() {
              return [];
            }
          };
        }
      },
      ratelist: {
        type: Array,
        default() {
          return [];
        }
      }
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
            data: [],
            type: 'line'
          }, {
            name: "UV",
            data: [],
            type: 'line'
          }, {
            name: "订购量",
            data: [],
            type: 'line'
          }, {
            name: "转化率",
            data: [],
            type: 'line'
          }],

        },
      }
    },

    watch: {
      pvlist(newValue, oldValue) {
        this.visitChartOption.series[0].data = newValue
        this.$refs.dayvisitcharttable.reload()
      },

      uvlist(newValue, oldValue) {
        this.visitChartOption.series[1].data = newValue
        this.$refs.dayvisitcharttable.reload()
      },

      orderlist(newValue, oldValue) {
        this.visitChartOption.series[2].data = newValue
        this.$refs.dayvisitcharttable.reload()
      },

      ratelist(newValue, oldValue) {
        this.visitChartOption.series[3].data = newValue
        this.$refs.dayvisitcharttable.reload()
      },


    }
  }
</script>

<style scoped>

  #dayvisitchart {
    width: 580px;
    height: 320px;
  }
</style>
