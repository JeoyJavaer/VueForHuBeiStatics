<template>
  <div>
    <table-echart :options="options" elid="cporderpiechartid" ref="cporderpiechartref">
      <div id="cporderpiechartid" class="cp-order-pie-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "CPOrderPieChart",
    components: {TableEchart},
    props: {
      orderlist: {
        type: Array,
        default() {
          return []
        }
      },

    },

    data() {
      return {
        options: {
          title: {
            text: 'CP统计',
            subtext: '订购量',
            x: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          legend: {
            type: "scroll",
            orient: 'vertical',
            left: 'left',
          },
          series: [
            {
              name: 'PV',
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: [],
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                },
                normal: {
                  label: {
                    show: true,
                    formatter: '{b} : {c}  ({d}%)'
                  },
                  labelLine: {show: true}
                }
              }
            }
          ]
        }
      }
    },
    watch: {
      orderlist(newValue, oldValue) {
        this.options.series[0].data = newValue
        this.$refs.cporderpiechartref.reload()
      }
    }
  }


</script>

<style scoped>
  .cp-order-pie-container {
    width: 1358px;
    height: 480px;
  }
</style>