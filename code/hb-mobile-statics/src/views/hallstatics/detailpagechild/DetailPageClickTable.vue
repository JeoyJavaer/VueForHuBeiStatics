<template>
  <div>
    <table-echart :options="options" elid="detailpagetableid" ref="detailpagetableref">
      <div id="detailpagetableid" class="detail-page-table-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";
  export default {
    name: "DetailPageClickTable",
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


    },
    data() {
      return {

        options : {
          title : {
            text : '详情页PvUv统计'
          },
          tooltip : { //鼠标经过显示提示框
            show : true,
            trigger : "axis"
          },
          legend : { //不同的线的类型
            data : [ "播放总次数" ]
          },
          xAxis : {
            name : "日期",
            type : 'category',
            axisLabel : {
              interval : 0,//代表显示所有x轴标签显示
              rotate : 25, //代表逆时针旋转45度
            }
          },
          yAxis : {
            name : "PV/UV",
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
          } ]
        },



      }
    },
    watch:{
      pvlist(newValue,oldValue){
        this.options.series[0].data=newValue
        this.$refs.detailpagetableref.reload()
      },
      uvlist(newValue,oldValue){
        this.options.series[1].data=newValue
        this.$refs.detailpagetableref.reload()
      },
    }
  }
</script>

<style scoped>
  .detail-page-table-container{
    width: 1358px;
    height: 480px;
  }
</style>