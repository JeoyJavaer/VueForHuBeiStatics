<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>用户行为</el-breadcrumb-item>
      <el-breadcrumb-item>用户行为统计</el-breadcrumb-item>
      <el-breadcrumb-item>行为列表</el-breadcrumb-item>
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

        <!--目标-->
        <el-col :span="3">
          <span>目标:</span>
          <el-input
              class="input-username"
              clearable
              placeholder="目标"
              v-model="query.object">
          </el-input>
        </el-col>


        <el-col :span="15">
          <!--用户行为选择器-->
          <span>用户行为:</span>
          <el-select class="left-margin15" v-model="query.action" placeholder="用户行为" @change="optionChange">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
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
            :data="behaviorList">
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
              prop="object"
              label="目标"
          >
          </el-table-column>
          <el-table-column
              prop="action"
              label="用户行为">
          </el-table-column>
          <el-table-column
              prop="ip"
              label="IP">
          </el-table-column>
          <el-table-column
              prop="createDateTimeFmt"
              label="创建时间">
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
  name: "UserBehavior",
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
      behaviorList: [],
      options: [ {
        value: '启动游戏',
        label: '启动游戏'
      }, {
        value: '大厅访问',
        label: '大厅访问'
      }, {
        value: '页面访问',
        label: '页面访问'
      }, {
        value: '订购产品',
        label: '订购产品'
      }],
    }
  },
  created() {
    this.loadData()
  },

  methods: {
    loadData() {
      this.getBehaviorList()
    },

    async getBehaviorList() {
      //http://localhost:8090/hbydGame/userAction/getListByPage?page=1&limit=10
      const res = await this.$http.get(`/hbydGame/userAction/getListByPage?page=${this.query.page}&limit=${this.query.limit}&object=${this.query.object}&action=${this.query.action}`)
      this.behaviorList = res.data
      this.total = res.count
    },
    searchClick() {

    },
    resetClick() {
      this.query.userId = ''
      this.query.object = ''
      this.query.action = ''
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
