<template>
  <div>
    <table-echart :options="amountChartOption"/>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "AmountChart",
    components: {TableEchart},

    props: {
      pricelist: []
    },
    data() {
      return {
        amountChartOption: {
          title: {
            text: '金额新增趋势'
          },
          tooltip: {
            show: true,
            trigger: "axis",
          },
          legend: {  //不同的线的类型
            data: ["订购金额数"],
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
              name: "订购金额",
              data: this.pricelist,
              type: 'line'
            }
          ],

        },
      }
    }
  }
</script>

<style scoped>

</style>