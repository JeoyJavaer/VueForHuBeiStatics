<template>
  <div>
    <table-echart :options="userChartOption" elid="userchart" ref="usercharttable">
      <div id="userchart"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "UserChart",
    components: {TableEchart},

    props: {
      newlist:  {
        type:Array,
        default() {
          return [];
        }
      },
      activelist:  {
        type:Array,
        default() {
          return [];
        }
      }
    },
    data() {
      return {
        userChartOption: {
          title: {
            text: '用户新增趋势'
          },
          tooltip: {
            show: true,
            trigger: "axis",
          },
          legend: {  //不同的线的类型
            data: ["首次登录人数", "活跃用户人数"],
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
              name: "首次登录人数",
              data: this.newlist,
              type: 'line'
            },
            {
              name: "活跃用户人数",
              data: this.activelist,
              type: "line",
            }
          ],
        },
      }
    },

    watch:{
      newlist(newValue,oldValue){
        this.userChartOption.series[0].data=newValue
        this.$refs.usercharttable.reload()
      },
      activelist(newValue,oldValue){
        this.userChartOption.series[1].data=newValue
        this.$refs.usercharttable.reload()
      },

    }
  }
</script>

<style scoped>
  #userchart {
    width: 100%;
    height: 100%;
  }
</style>