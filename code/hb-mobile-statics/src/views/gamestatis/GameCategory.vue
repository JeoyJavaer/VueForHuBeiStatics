<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>游戏数据</el-breadcrumb-item>
      <el-breadcrumb-item>游戏分类</el-breadcrumb-item>
      <el-breadcrumb-item>分类列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-row>
        <!--日期选择框-->
        <el-col :span="18">
          <div>
            <span class="cp-span">分类:</span>
            <el-select class="left-margin15" v-model="query.cp" placeholder="游戏分类" @change="optionChange">
              <el-option
                  v-for="item in categoryList"
                  :key="item.id"
                  :label="item.categoryName"
                  :value="item.id">
              </el-option>
            </el-select>
            <span class="left-margin15">查询日期:</span>
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
            <!--选择器-->
            <span class="left-margin15">快速选择:</span>
            <el-select class="left-margin15" v-model="query.time" placeholder="日期" @change="optionChange">
              <el-option
                  v-for="item in quickTimeList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </div>
        </el-col>
        <el-col>
        </el-col>
        <el-col :span="8" class="top-margin30">
          <el-button class="left-margin15" type="success" icon="el-icon-search" @click="searchClick">查询</el-button>
          <el-button class="left-margin15" type="warning" icon="el-icon-error" @click="resetClick">重置</el-button>
          <el-button class="left-margin15" type="primary" icon="el-icon-error" @click="exportClick">导出</el-button>
        </el-col>
      </el-row>

      <div class="top-margin15">
        <game-category-trend-table :pvlist="pvlist"  :uvlist="uvlist"></game-category-trend-table>
      </div>

      <div class="top-margin15">
        <game-category-p-v-pie :pvdata="pvdata" ></game-category-p-v-pie>
      </div>
      <div class="top-margin15">
        <game-category-u-v-pie :uvdata="uvdata" ></game-category-u-v-pie>
      </div>

      <!--    -->
      <!-- 数据的表格展示-->
      <el-table
          stripe
          border
          :data="visitList">
        <el-table-column
            prop="categoryName"
            label="分类名称">
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
  import GameCategoryTrendTable from "./child/GameCategoryTrendTable";
  import GameCategoryPVPie from "./child/GameCategoryPVPie";
  import GameCategoryUVPie from "./child/GameCategoryUVPie";
  export default {
    name: "GameCategory",
    components: {GameCategoryTrendTable,GameCategoryPVPie,GameCategoryUVPie},
    data() {
      return {
        query: {
          page: 1,
          limit: 7,
          beginDate: '',
          endDate: '',
          time: '',
        },

        categoryList: [],
        visitList: [],
        total: 0,
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
        pvlist:[],
        uvlist:[],
        pvdata:[],//饼状图里面的数据
        uvdata:[],//饼状图里面的数据
      }


    },
    created() {
      this.loadData()
    },

    methods: {
      loadData() {
        this.getDayVisitList()
        this.getCategoryList()
      },

      async getDayVisitList() {
        const res = await this.$http.get(`/hbydGame/gameCategory/getListDayCountPvUv?page=${this.query.page}&limit=${this.query.limit}`)
        this.visitList = res.data
        this.total = res.count
        this.initTableData(res)
      },

      initTableData(res){
        var countPVArray = [];
        var countUVArray = [];


        var pvdata =[];
        var uvdata = [];
        for (let  item of res.data){
          let s = item.countDay+"/"+item.categoryName;
          var jsonuv = {};
          var jsonpv = {};
          countPVArray.push([s, item.countPV]);
          countUVArray.push([s, item.countUV]);
          jsonpv.name = item.countDay+" / "+item.categoryName;
          jsonpv.value = item.countPV;
          pvdata.push(jsonpv)

          jsonuv.name = item.countDay+" / "+item.categoryName;
          jsonuv.value = item.countUV;
          uvdata.push(jsonuv);
        }
        this.pvlist=countPVArray
        this.uvlist=countUVArray

        this.pvdata=pvdata
        this.uvdata=uvdata


      },
      async getCategoryList() {
        const res = await this.$http.get(`/hbydGame/gameCategory/getGameCategory.do`)
        this.categoryList = res
      },

      handleSizeChange(pageSize) {
        this.query.limit = pageSize
        this.loadData()
      },

      handleCurrentChange(pageNum) {
        this.query.page = pageNum
        this.loadData()
      },

      optionChange(opt) {

      },

      searchClick(){

      },

      exportClick(){

      },

      resetClick(){

      }


    }

  }
</script>

<style scoped>
.game-category-trend-table-container{
  width: 100%;
  height: 100%;
}
</style>