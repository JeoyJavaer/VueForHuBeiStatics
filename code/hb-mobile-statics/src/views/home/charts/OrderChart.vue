<template>
  <div>
    <table-echart :options="orderChartOption"/>
  </div>
</template>

<script>

  import TableEchart from "../../../components/common/TableEchart";
  export default {
    name: "OrderChart",
    components: {TableEchart},
    props:{
      neworderlist:[],
      userorderlist:[]
    },
    data(){
      return{
        orderChartOption: {
          title: {
            text: '订单新增趋势'
          },
          tooltip: {
            show: true,
            trigger: "axis",
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
              data: this.neworderlist,
              type: 'line'
            },
            {
              name: "订购人数",
              data: this.userorderlist,
              type: "line",
            }
          ],

        },
      }
    }
  }
</script>

<style scoped>

</style>