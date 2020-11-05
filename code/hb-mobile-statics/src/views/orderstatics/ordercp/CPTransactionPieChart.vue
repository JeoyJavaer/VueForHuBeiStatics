<template>
  <div>
    <table-echart :options="options" elid="cptransactionpiechartid" ref="cptransactionpiechartref">
      <div id="cptransactionpiechartid" class="cp-transaction-pie-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "CPTransactionPieChart",
    components: {TableEchart},
    props: {
      transactionlist: {
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
            subtext: '订购发起量',
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
      transactionlist(newValue, oldValue) {
        this.options.series[0].data = newValue
        this.$refs.cptransactionpiechartref.reload()
      }
    }
  }


</script>

<style scoped>
  .cp-transaction-pie-container {
    width: 1358px;
    height: 480px;
  }
</style>