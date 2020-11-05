<template>
  <div>
    <table-echart :options="options" elid="recclicktableid" ref="recclicktableref">
      <div id="recclicktableid" class="rec-click-table-container"></div>
    </table-echart>
  </div>
</template>

<script>
  import TableEchart from "../../../components/common/TableEchart";
  export default {
    name: "RecClickTable",
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
            text : '推荐位PvUv统计'
          },
          tooltip : { //鼠标经过显示提示框
            show : true,
            trigger : "axis"
          },
          legend : { //不同的线的类型
            data : [ "播放总次数" ]
          },
          xAxis : {
            name : "推荐位名称",
            type : 'category',
            axisLabel : {
              interval : 0,//代表显示所有x轴标签显示
              rotate : 25, //代表逆时针旋转45度
              formatter : function(value) {
                return value.split("/")[1];
              }
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
        this.$refs.recclicktableref.reload()
      },
      uvlist(newValue,oldValue){
        this.options.series[1].data=newValue
        this.$refs.recclicktableref.reload()
      },
    }
  }
</script>

<style scoped>
  .rec-click-table-container{
    width: 1358px;
    height: 480px;
  }
</style>