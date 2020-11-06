<template>
  <div>
    <table-echart :options="options" elid="gamesingleclickpvpieid" ref="gamesingleclickpvpieref">
      <div id="gamesingleclickpvpieid" class="detail-page-click-uv-pie-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "GameSingleClickPVPie",
    components: {TableEchart},
    props: {

      pvlist: {
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
            text: '游戏单日PvVu统计',
            subtext: 'PV',
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
        },
      }
    },
    watch: {
      pvlist(newValue, oldValue) {
        this.options.series[0].data = newValue
        this.$refs.gamesingleclickpvpieref.reload()
      },

    }
  }
</script>

<style scoped>
  .detail-page-click-uv-pie-container {
    width: 1358px;
    height: 480px;
  }
</style>