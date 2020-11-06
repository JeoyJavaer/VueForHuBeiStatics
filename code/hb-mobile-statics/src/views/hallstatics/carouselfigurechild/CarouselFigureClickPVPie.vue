<template>
  <div>
    <table-echart :options="options" elid="carouselfigureclickpvpieid" ref="carouselfigureclickpvpieref">
      <div id="carouselfigureclickpvpieid" class="carousel-figure-click-pv-pie-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";
  export default {
    name: "CarouselFigureClickPVPie",
    components:{TableEchart},
    props: {

      pvlist:{
        type:Array,
        default(){
          return []
        }
      },

    },
    data() {
      return {

        options : {
          title : {
            text: '轮播位PvVu统计',
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
              name: 'PV',
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
        },



      }
    },
    watch:{
      pvlist(newValue,oldValue){
        this.options.series[0].data=newValue
        this.$refs.carouselfigureclickpvpieref.reload()
      },

    }
  }
</script>

<style scoped>
  .carousel-figure-click-pv-pie-container{
    width: 1358px;
    height: 480px;
  }
</style>