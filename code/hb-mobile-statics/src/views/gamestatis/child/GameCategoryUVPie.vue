<template>
  <div >
    <table-echart :options="options" elid="gamecategoryuvpie" ref="gamecategoryuvpieref">
      <div id="gamecategoryuvpie" class="game-category-uv-pie-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";
  export default {
    name: "GameCategoryUVPie",
    components: {TableEchart},
    props:{
      uvdata:{
        type:Array,
        default(){
          return []
        }
      }
    },
    data(){
      return{
         options :{
          title : {
            text: '游戏分类单日PvVu统计',
            subtext: 'UV',
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

    watch:{
      uvdata(newValue,oldValue){
        this.options.series[0].data=newValue
        this.$refs.gamecategoryuvpieref.reload()
      }
    }

  }
</script>

<style scoped>
.game-category-uv-pie-container{
  width: 1358px;
  height: 480px;
}
</style>