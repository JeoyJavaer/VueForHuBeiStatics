<template>
  <div >
    <table-echart :options="orderChartOption" elid="orderchart" ref="ordercharttable">
      <div id="orderchart"></div>
    </table-echart>
  </div>
</template>

<script>

  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "OrderChart",
    components: {TableEchart},
    props: {
      neworderlist: {
        type: Array,
        default() {
          return [];
        }
      },
      userorderlist: {
        type: Array,
        default() {
          return [];
        }
      }
    },
    data() {
      return {
        orderChartOption: {
          title: {
            text: '订单新增趋势'
          },

          tooltip: {  //鼠标经过显示提示框
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
          series: [
            {
              name: "订购订单数",
              data: [],
              type: 'line'
            },
            {
              name: "订购人数",
              data: [],
              type: "line",
            }
          ],

        },
      }
    },

    watch:{
      neworderlist(newValue,oldValue){
        this.orderChartOption.series[0].data=newValue
        this.$refs.ordercharttable.reload()
      },
      userorderlist(newValue,oldValue){
        this.orderChartOption.series[1].data=newValue
        this.$refs.ordercharttable.reload()
      },
    },

    methods: {
      refresh() {
        this.orderChartOption.series[0].data = this.neworderlist
        this.orderChartOption.series[1].data = this.userorderlist
      }
    }

  }
</script>

<style scoped>
#orderchart{
  width: 100%;
  height: 100%;
}
</style>