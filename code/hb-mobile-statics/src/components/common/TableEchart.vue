<template>
  <div class="chart-container">
    <div ref="tablechart"></div>
  </div>
</template>

<script>

  import echarts from 'echarts/lib/echarts'

  export default {
    name: "TableEchart",
    props: {
      options: {}
    },
    mounted() {
      this.initChart()
    },

    methods: {
      initChart() {
        let cusEchart = echarts.init(this.$refs.tablechart, this.options);
      }
    }
  }
</script>

<style scoped>
  .chart-container {
    width: 100%;
    height: 100%;
  }
</style>