<template>
  <div>
    <table-echart :options="options" elid="hallpageclicktableid" ref="hallpagetableref">
      <div id="hallpageclicktableid" class="hall-page-click-table-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";
  export default {
    name: "HallPageClickTable",
    components:{TableEchart},
    props: {

      pvlist:{
        type:Array,
        default(){
          return []
        }
      },

      uvlist:{
        type:Array,
        default(){
          return []
        }
      },
      orderlist:{
        type:Array,
        default(){
          return []
        }
      },

      ratelist:{
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
            text : '大厅PvUv统计'
          },
          tooltip : { //鼠标经过显示提示框
            show : true,
            trigger : "axis",
            formatter(params){
              return params[0].axisValue+
                '<br>'+
                '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#c23531;"></span>'+
                "PV: "+params[0].data[1]+
                '<br>'+
                '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#2f4554;"></span>'+
                "UV: "+params[1].data[1]+
                '<br>'+
                '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#61a0a8;"></span>'+
                "订购量: "+params[2].data[1]+
                '<br>'+
                '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#d48265;"></span>'+
                "转化率: "+params[3].data[1]+"%";
            }
          },
          legend : { //不同的线的类型
            data : [ "播放总次数" ]
          },
          xAxis : {
            name : "日期",
            type : 'category',
            axisLabel : {
              interval : 0,//代表显示所有x轴标签显示
              rotate : 45, //代表逆时针旋转45度
            }
          },
          yAxis : {
            name : "PV/UV/订购量/转化率",
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
            name : "PV",
            data : [],
            type : 'line'
          },{
            name : "UV",
            data : [],
            type : 'line'
          },{
            name : "订购量",
            data : [],
            type : 'line'
          },{
            name : "转化率",
            data : [],
            type : 'line'
          } ]
        },



      }
    },
    watch:{
      pvlist(newValue,oldValue){
        this.options.series[0].data=newValue
        this.$refs.hallpagetableref.reload()
      },
      uvlist(newValue,oldValue){
        this.options.series[1].data=newValue
        this.$refs.hallpagetableref.reload()
      },

      orderlist(newValue,oldValue){
        this.options.series[2].data=newValue
        this.$refs.hallpagetableref.reload()
      },
      ratelist(newValue,oldValue){
        this.options.series[3].data=newValue
        this.$refs.hallpagetableref.reload()
      },
    }
  }
</script>

<style scoped>
  .hall-page-click-table-container{
    width: 1358px;
    height: 480px;
  }
</style>