<template>
  <div>
    <table-echart :options="options" elid="cpvisitchartid" ref="cpvisitchartref">
      <div id="cpvisitchartid" class="cp-visit-chart-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";

  export default {
    name: "CPVisitChart",
    components: {TableEchart},
    props: {

      orderlist:{
        type:Array,
        default(){
          return []
        }
      },

      clicklist:{
        type:Array,
        default(){
          return []
        }
      },

      transactionlist:{
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
            text : 'CP数据统计'
          },
          tooltip : { //鼠标经过显示提示框
            show : true,
            trigger : "axis"
          },
          legend : { //不同的线的类型
            data : [ "播放总次数" ]
          },
          xAxis : {
            name : "CP",
            type : 'category',
            axisLabel : {
              interval : 0,//代表显示所有x轴标签显示
              rotate : 45, //代表逆时针旋转45度
            }
          },
          yAxis : {
            name : "订购量/游戏点击量/订购发起",
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
            name : "订购量 ",
            data : [],
            type : 'line'
          },{
            name : "游戏点击量",
            data : [],
            type : 'line'
          },{
            name : "订购发起量",
            data : [],
            type : 'line'
          }]
        },



      }
    },
    watch:{
      orderlist(newValue,oldValue){
        this.options.series[0].data=newValue
        this.$refs.cpvisitchartref.reload()
      },
      clicklist(newValue,oldValue){
        this.options.series[1].data=newValue
        this.$refs.cpvisitchartref.reload()
      },

      transactionlist(newValue,oldValue){
        this.options.series[2].data=newValue
        this.$refs.cpvisitchartref.reload()
      }
    }

  }
</script>

<style scoped>
.cp-visit-chart-container{
  width: 1358px;
  height: 480px;
}
</style>
