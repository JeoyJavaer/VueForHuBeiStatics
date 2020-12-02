<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>大厅统计</el-breadcrumb-item>
      <el-breadcrumb-item>推荐位PV/UV统计</el-breadcrumb-item>
      <el-breadcrumb-item>数据列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row>
        <!--日期选择框-->

        <!--目标-->
        <el-col :span="4">
          <span>推荐位名称:</span>
          <el-input
              class="input-username"
              clearable
              placeholder="推荐位名称"
              v-model="query.recName">
          </el-input>
        </el-col>
        <el-col :span="14">
          <div>
            <span class="left-margin15">日期:</span>
            <el-date-picker
                class="left-margin15"
                v-model="query.beginDate"
                type="datetime"
                placeholder="开始日期"
                default-time="12:00:00">
            </el-date-picker>
            <span> - </span>
            <el-date-picker
                v-model="query.endDate"
                type="datetime"
                default-time="12:00:00"
                placeholder="结束日期">
            </el-date-picker>

            <span class="left-margin15">快速选择:</span>
            <el-select class="left-margin15" v-model="query.time" placeholder="日期" @change="timeOptionChange">
              <el-option
                  v-for="item in quickTimeList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>

          </div>
        </el-col>

        <el-col :span="6">
          <el-button class="left-margin30" type="success" icon="el-icon-search" @click="searchClick">查询</el-button>
          <el-button class="left-margin15" type="warning" icon="el-icon-error" @click="resetClick">重置</el-button>
          <el-button class="left-margin15" type="primary" icon="el-icon-error" @click="exportClick">导出</el-button>

        </el-col>
      </el-row>

      <!--  折线图  -->
      <rec-click-table class="top-margin30" :pvlist="pvList"  :uvlist="uvList" />
      <!--PV饼图-->
      <rec-click-p-v-pie class="top-margin30" :pvlist="pvPieList"/>
      <!--UV饼图-->
      <rec-click-u-v-pie class="top-margin30" :uvlist="uvPieList"/>


      <!-- 数据的表格展示-->
      <el-table
          stripe
          border
          :data="dataList">
        <el-table-column
            type="index"
            indent="#"
            width="50">
        </el-table-column>
        <el-table-column
            prop="recName"
            label="推荐位名称">
        </el-table-column>
        <el-table-column
            prop="countDay"
            label="时间">
        </el-table-column>
        <el-table-column
            prop="countPV"
            label="PV">
        </el-table-column>
        <el-table-column
            prop="countUV"
            label="UV">
        </el-table-column>

      </el-table>

      <el-pagination
          class="user-list-pagination"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="this.query.page"
          :page-sizes="[10,  20, 50]"
          :page-size="this.query.limit"
          layout="total,prev, pager, next, jumper,sizes"
          :total="total">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
  import RecClickTable from "./recommendclickchild/RecClickTable";
  import RecClickPVPie from "./recommendclickchild/RecClickPVPie";
  import RecClickUVPie from "./recommendclickchild/RecClickUVPie";
  export default {
    name: "RecommendClick",
    components:{RecClickTable,RecClickPVPie,RecClickUVPie},
    data() {
      return {
        query: {
          page: 1,
          limit: 7,
          beginDate: null,
          endDate: null,
          recName: ''
        },
        quickTimeList: [
          {
            label: '今天',
            value: '1'
          },
          {
            label: '昨天',
            value: '2'
          },
          {
            label: '前天',
            value: '3'
          },
          {
            label: '本周',
            value: '4'
          },
          {
            label: '本月',
            value: '5'
          },
        ],
        dataList: [],
        total:0,
        pvList:[],
        uvList:[],
        pvPieList:[],
        uvPieList:[],

      }

    },
    created() {
      this.loadData()
    },
    methods: {
      loadData() {
        this.getDataList()
      },

      async getDataList() {
        // const res = await this.$http.get(`/hbydGame/recommendGame/getListPvUvByPage?page=${this.query.page}&limit=${this.query.limit}&beginDate=${this.query.beginDate}&recName=${this.query.recName}&endDate=${this.query.endDate}`)
        const res = await this.$http.get(`/hbydGame/recommendGame/getListPvUvByPage?page=${this.query.page}&limit=${this.query.limit}`)
        this.dataList = res.data
        this.total=res.count


        var countPVArray = [];
        var countUVArray = [];
        //同时画出柱状图
        var res_data = res.data;

        var pvPieData = [];
        var uvPieData = [];

        for(var i=0; i < res_data.length;i++ ){


          countPVArray.push([res_data[i].countDay+"/"+res_data[i].recName, res_data[i].countPV]);
          countUVArray.push([res_data[i].countDay+"/"+res_data[i].recName, res_data[i].countUV]);


          var pvJson = {};
          pvJson.name = res.data[i].recName;
          pvJson.value = res.data[i].countPV;
          pvPieData.push(pvJson);

          var uvJson = {};
          uvJson.name = res.data[i].recName;
          uvJson.value = res.data[i].countUV;
          uvPieData.push(uvJson);
        }






        this.pvList=countPVArray
        this.uvList=countUVArray

        this.pvPieList=pvPieData
        this.uvPieList=uvPieData


      },

      timeOptionChange(opt){

      },
      searchClick() {

      },
      resetClick(){

      },

      exportClick(){

      },

      handleSizeChange(size){
        this.query.limit=size
        this.loadData()
      },

      handleCurrentChange(pageNum){
        this.query.page=pageNum
        this.loadData()
      }


    }
  }
</script>

<style scoped>
  .input-username {
    width: 60%;
    margin-left: 15px;
  }
</style>