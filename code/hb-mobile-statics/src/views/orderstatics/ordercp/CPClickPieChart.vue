<template>
  <div>
    <table-echart :options="options" elid="cpclickpiechartid" ref="cpclickpiechartref">
      <div id="cpclickpiechartid" class="cp-click-pie-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "CPClickPieChart",
    components: {TableEchart},
    props: {
      clicklist: {
        type: Array,
        default() {
          return []
        }
      },

    },

    data() {
      return {
        options: {
          title : {
            text: 'CP统计',
            subtext: '游戏点击量',
            x:'center'
          },
          tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          legend: {
            type: "scroll",
            orient: 'vertical',
            left: 'left',

          },
          series : [
            {
              name: 'UV',
              type: 'pie',
              radius : '55%',
              center: ['50%', '60%'],
              data:[],
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                },
                normal:{
                  label:{
                    show: true,
                    formatter: '{b} : {c}  ({d}%)'
                  },
                  labelLine :{show:true}
                }
              }
            }
          ]
        }
      }
    },
    watch: {
      clicklist(newValue, oldValue) {
        this.options.series[0].data = newValue
        this.$refs.cpclickpiechartref.reload()
      }
    }
  }


</script>

<style scoped>
  .cp-click-pie-container {
    width: 1358px;
    height: 480px;
  }
</style>