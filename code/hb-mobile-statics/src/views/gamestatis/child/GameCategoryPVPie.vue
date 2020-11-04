<template>
  <div >
    <table-echart :options="options" elid="gamecategorypvpie" ref="gamecategorypvpieref">
      <div id="gamecategorypvpie" class="game-category-pv-pie-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";
  export default {
    name: "GameCategoryPVPie",
    components: {TableEchart},
    props:{
      pvdata:{
        type:Array,
        default(){
          return []
        }
      }
    },
    data(){
      return{
        options:{
          title : {
            text: '游戏分类PvVu统计',
            subtext: 'PV',
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
              name: '页面访问',
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
      pvdata(newValue,oldValue){
        this.options.series[0].data=newValue
        this.$refs.gamecategorypvpieref.reload()
      }
    }
  }
</script>

<style scoped>
.game-category-pv-pie-container{
  width: 1358px;
  height: 480px;
}
</style>