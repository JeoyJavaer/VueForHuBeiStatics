<template>
  <div>
    <table-echart :options="options" elid="cptotalfeepieid" ref="cptotalfeepieref">
      <div id="cptotalfeepieid" class="cp-totalfee-pie-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "CPTotalFeePie",
    components: {TableEchart},
    props: {

      feepielist:{
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
            text: '每日订购金额统计',
            subtext: '订购金额',
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
            /*
              //自定义
                formatter:function (params){
                  for(var i=res.data.length-1;i>=0;i--){
                    if(params == res.data[i].countDay){
                      return params +" : ("+ res.data[i].countPV+")";
                    //	return params +" : ("+ "{a} <br/>{b} : {c} ({d}%)"+")";
                    //	return params +" : ("+ "{b} : {c} ({d}%)"+")";
                    }
                  }
                  return params;
                },
             */
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
      feepielist(newValue,oldValue){
        this.options.series[0].data=newValue
        this.$refs.cptotalfeepieref.reload()
      },

    }

  }
</script>

<style scoped>
.cp-totalfee-pie-container{
  width: 1358px;
  height: 480px;
}
</style>
