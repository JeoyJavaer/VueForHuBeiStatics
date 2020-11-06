<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>大厅统计</el-breadcrumb-item>
      <el-breadcrumb-item>大厅访问统计</el-breadcrumb-item>
      <el-breadcrumb-item>访问列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-row>
        <el-col :span="4">
          <span>用户id:</span>
          <el-input
              class="input-username"
              clearable
              placeholder="用户id"
              v-model="query.userId">
          </el-input>
        </el-col>

        <el-col :span="4">
          <span>来源页面:</span>
          <el-input
              class="input-username"
              clearable
              placeholder="来源页面"
              v-model="query.pageName">
          </el-input>
        </el-col>


        <el-col :span="10">
          <span class="left-margin30">时间:</span>
          <!--日期选择框-->
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

        </el-col>

      </el-row>
      <el-row class="top-margin30">
        <el-col :span="8">
          <el-button class="left-margin15" type="success" icon="el-icon-search" @click="searchClick">查询</el-button>
          <el-button class="left-margin15" type="warning" icon="el-icon-error" @click="resetClick">重置</el-button>
          <el-button class="left-margin15" type="primary" icon="el-icon-error" @click="exportClick">导出</el-button>
        </el-col>
      </el-row>
      <div class="data-container">
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
              prop="userId"
              label="用户id"
          >
          </el-table-column>

          <el-table-column
              prop="pageName"
              label="来源页面">
          </el-table-column>

          <el-table-column
              prop="visitTimeFmt"
              label="创建时间">
          </el-table-column>

          <el-table-column
              prop="ip"
              label="IP">
          </el-table-column>

          <el-table-column
              prop="province"
              label="省">
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
  export default {
    name: "HallVisit",

    data() {
      return {
        query: {
          page: 1,
          limit: 10,
          userId: '',
          startDate: '',
          endDate: '',
          object: '',
          action: ''
        },
        total: 0,

        dataList:[]
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
        const res = await this.$http.get(`/hbydGame/gameVisit/getListByPage?page=${this.query.page}&limit=${this.query.limit}`)
        this.dataList = res.data
        this.total = res.count
      },
      searchClick() {

      },
      resetClick() {
        this.query.userId = ''
        this.query.pageName = ''
        this.query.recName = ''
        this.query.startDate = ''
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
  .input-username {
    width: 60%;
    margin-left: 15px;
  }
</style>