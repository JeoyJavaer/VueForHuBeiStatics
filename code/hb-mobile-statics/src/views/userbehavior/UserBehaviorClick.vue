<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>用户行为</el-breadcrumb-item>
      <el-breadcrumb-item>用户行为PV/UV</el-breadcrumb-item>
      <el-breadcrumb-item>数据信息</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row>
        <el-col :span="16">
          <!--日期选择框-->
          <span>时间:</span>
          <el-date-picker
              class="left-margin15"
              v-model="query.beginDate"

              placeholder="开始日期"
              default-time="12:00:00">
          </el-date-picker>
          <span> - </span>
          <el-date-picker
              v-model="query.endDate"

              default-time="12:00:00"
              placeholder="结束日期">
          </el-date-picker>
          <!--用户行为选择器-->
          <span class="left-margin15">快速选择:</span>
          <el-select class="left-margin15" v-model="query.action" placeholder="时间" @change="optionChange">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="8">
          <el-button class="left-margin15" type="success" icon="el-icon-search" @click="searchClick">查询</el-button>
          <el-button class="left-margin15" type="warning" icon="el-icon-error" @click="resetClick">重置</el-button>
          <el-button class="left-margin15" type="primary" icon="el-icon-error" @click="exportClick">导出</el-button>
        </el-col>
      </el-row>
      <div >
        <!-- echart-->
        <div class="user-behavior-visit-chart">
          <user-behavior-visit-table :pvlist="pvlist" :uvlist="uvlist"></user-behavior-visit-table>
        </div>
        <el-table
            stripe
            border
            :data="visitList">
          <el-table-column
              type="index"
              indent="#"
              width="50">
          </el-table-column>
          <el-table-column
              prop="countDay"
              label="时间">
          </el-table-column>
          <el-table-column
              prop="countPV"
              label="PV"
          >
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
            :page-sizes="[10,  20, 50,100]"
            :page-size="this.query.limit"
            layout="total,prev, pager, next, jumper,sizes"
            :total="total">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
import UserBehaviorVisitTable from "@/views/userbehavior/userbehaviorvisit/UserBehaviorVisitTable";

export default {
  name: "UserBehaviorClick",
  components: {UserBehaviorVisitTable},
  data() {
    return {
      query: {
        beginDate: '',
        endDate: '',
        page: 1,
        limit: 10,
      },
      total: 0,
      options: [{
        value: '',
        label: '当天'
      }, {
        value: '启动游戏',
        label: '昨天'
      }, {
        value: '大厅访问',
        label: '前天'
      }, {
        value: '页面访问',
        label: '本周'
      }, {
        value: '订购产品',
        label: '本月'
      }],
      visitList: [],
      pvlist: [],
      uvlist: [],
    }
  },
  created() {
    this.loadData()
  },
  methods: {
    loadData() {
      this.getVisitList()
    },

    async getVisitList() {
      const res = await this.$http.get(`/hbydGame/userAction/getListPvUvByPage?page=${this.query.page}&limit=${this.query.limit}`)
      this.processData(res)
    },

    processData(res){
      this.visitList = res.data
      this.total = res.count
      let countPVArray = [];
      let countUVArray = [];
      for (let item of res.data){
        countPVArray.push([item.countDay, item.countPV]);
        countUVArray.push([item.countDay, item.countUV]);
      }
      this.pvlist=countPVArray
      this.uvlist=countUVArray
    },

    searchClick() {

    },
    resetClick() {
      this.query.userId = ''
      this.query.object = ''
      this.query.action = ''
      this.query.beginDate = ''
      this.query.endDate = ''

    },
    exportClick() {

    },
    optionChange(option) {
      console.log(option);
    },

    handleSizeChange(pageSize) {
      this.query.limit = pageSize
      this.loadData()
    },
    handleCurrentChange(pageNum) {
      this.query.page = pageNum
      this.loadData()
    }
  }
}
</script>

<style scoped>
.user-behavior-visit-chart{
  margin-top: 30px;
  width: 80%;
  height: 500px;
}
</style>
