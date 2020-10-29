<template>
  <div>
    <table-echart :options="userChartOption"/>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "UserChart",
    components: {TableEchart},

    props: {
      newlist: [],
      activelist: []
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
    }
  }
</script>

<style scoped>

</style>