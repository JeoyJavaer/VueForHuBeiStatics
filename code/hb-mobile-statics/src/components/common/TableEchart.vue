<template>
  <div class="chart-container">
    <slot></slot>
  </div>
</template>

<script>

  import echarts from 'echarts'

  let cusEchart

  export default {
    name: "TableEchart",
    props: {
      elid: 0,
      options: {}
    },
    mounted() {
      this.initChart()
    },

    methods: {
      initChart() {
        var cusEchart
        if (cusEchart) {
          cusEchart.setOption(this.options)
        } else {
          cusEchart = echarts.init(document.getElementById(this.elid));
          cusEchart.setOption(this.options)
        }


      },

      reload() {
        this.initChart()
      }
    }
  }
</script>

<style scoped>
  .chart-container {
    width: 580px;
    height: 320px;
  }
</style>