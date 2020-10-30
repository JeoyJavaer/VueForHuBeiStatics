<template>
  <div>
    <table-echart :options="amountChartOption" elid="amountchart" ref="amountcharttable">
      <div id="amountchart"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "AmountChart",
    components: {TableEchart},

    props: {
      pricelist: {
        type: Array,
        default() {
          return [];
        }
      }
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
              data: [],
              type: 'line'
            }
          ],

        },
      }
    },

    watch: {
      pricelist(newValue, oldValue) {
        this.amountChartOption.series[0].data = newValue
        this.$refs.amountcharttable.reload()
      }
    }
  }
</script>

<style scoped>
  #amountchart {
    width: 100%;
    height: 100%;
  }
</style>