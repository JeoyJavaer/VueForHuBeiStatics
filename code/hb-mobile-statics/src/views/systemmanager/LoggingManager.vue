<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>日志管理</el-breadcrumb-item>
      <el-breadcrumb-item>日志列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>

      <el-row>
        <!--日期选择框-->
        <el-col :span="20">
          <div class="demo-input-suffix">
            <span>日期范围:</span>
            <el-date-picker
                class="left-margin15"
                v-model="query.timeMin"
                type="date"
                placeholder="开始日期">
            </el-date-picker>
            <span>  -  </span>
            <el-date-picker

                v-model="query.timeMax"
                type="date"
                placeholder="结束日期">
            </el-date-picker>
            <el-button class="left-margin15" type="success" icon="el-icon-search" @click="searchClick">搜索</el-button>
            <el-button class="left-margin15" type="info" icon="el-icon-error" @click="resetClick">重置时间</el-button>
            <el-button class="left-margin15" type="danger" icon="el-icon-folder-delete" @click="deleteClick">
              删除日志(指定时间)
            </el-button>
            <el-button class="left-margin15" type="danger" icon=" el-icon-delete" @click="clearClick">清空日志</el-button>
          </div>

        </el-col>

      </el-row>
      <el-table
          stripe
          border
          :data="logList">

        <el-table-column
            prop="eventId"
            label="日志ID"
            width="180">
        </el-table-column>
        <el-table-column
            prop="timestamp"
            label="操作时间"
            width="180">
        </el-table-column>
        <el-table-column
            prop="formattedMessage"
            label="操作内容">
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
  export default {
    name: "LoggingManager",
    data() {
      return {
        logList: [],
        query: {
          page: 1,
          limit: 20,
          timeMax: '',
          timeMin: '',
        },
        total: 0
      }
    },
    created() {
      this.getLogList()
    },
    methods: {
      async getLogList() {
        const res = await this.$http.get(`/hbydGame/log/findLogging?page=${this.query.page}&limit=${this.query.limit}&timeMax=${this.query.timeMax}&timeMin=${this.query.timeMin}`)
        this.logList = res.data
        this.total = res.count
      },
      handleSizeChange(pageSize) {
        this.query.limit = pageSize
        this.getLogList()
      },

      handleCurrentChange(page) {
        this.query.page = page
        this.getLogList()
      },

      searchClick() {
        console.log(this.query);
      },
      resetClick() {
        this.query.timeMin = ''
        this.query.timeMax = ''

      },

      deleteClick() {

      },
      clearClick() {

      }


    }
  }

</script>

<style scoped>





</style>