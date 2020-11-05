<template>
  <div>
    <table-echart :options="options" elid="cptotalfeechartid" ref="cptotalfeeref">
      <div id="cptotalfeechartid" class="cp-totalfee-chart-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "CPTotalFeeChart",
    components: {TableEchart},
    props: {

      feelist:{
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
            text : '每日实际消费金额总计'
          },
          tooltip : { //鼠标经过显示提示框
            show : true,
            trigger : "axis"
          },
          legend : { //不同的线的类型
            data : [ "实际消费金额总计" ]
          },
          xAxis : {
            name : "日期/CP",
            type : 'category',
            axisLabel : {
              interval : 0,//代表显示所有x轴标签显示
              rotate : 30, //代表逆时针旋转45度
            }
          },
          yAxis : {
            name : "实际消费金额",
            type : 'value'
          },
          toolbox : { //工具栏
            show : true,
            feature : {
              dataView : {
                readOnly : false
              },
              magicType : {
                type : [ 'line', 'bar' ]
              }, //图形切换
              restore : {},
              saveAsImage : {}
            }
          },
          dataZoom : [ //滚动条
            {
              type : 'slider',
              show : true,
              start : 0,
              end : 100
            } ],
          series : [ {
            name : "实际消费金额",
            data : [],
            type : 'line'
          }]
        },
      }
    },
    watch:{
      feelist(newValue,oldValue){
        this.options.series[0].data=newValue
        this.$refs.cptotalfeeref.reload()
      },

    }

  }
</script>

<style scoped>
.cp-totalfee-chart-container{
  width: 1358px;
  height: 480px;
}
</style>
