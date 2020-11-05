<template>
  <div>
<!--    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">-->
<!--      <el-breadcrumb-item>大厅统计</el-breadcrumb-item>-->
<!--      <el-breadcrumb-item>推荐位PV/UV统计</el-breadcrumb-item>-->
<!--      <el-breadcrumb-item>数据列表</el-breadcrumb-item>-->
<!--    </el-breadcrumb>-->
<!--    <el-card>-->
<!--      <el-row>-->
<!--        &lt;!&ndash;日期选择框&ndash;&gt;-->

<!--        &lt;!&ndash;目标&ndash;&gt;-->
<!--        <el-col :span="4">-->
<!--          <span>推荐位名称:</span>-->
<!--          <el-input-->
<!--              class="input-username"-->
<!--              clearable-->
<!--              placeholder="推荐位名称"-->
<!--              v-model="query.recName">-->
<!--          </el-input>-->
<!--        </el-col>-->
<!--        <el-col :span="12">-->
<!--          <div>-->
<!--            <span class="left-margin15">日期:</span>-->
<!--            <el-date-picker-->
<!--                class="left-margin15"-->
<!--                v-model="query.beginDate"-->
<!--                type="datetime"-->
<!--                placeholder="开始日期"-->
<!--                default-time="12:00:00">-->
<!--            </el-date-picker>-->
<!--            <span> - </span>-->
<!--            <el-date-picker-->
<!--                v-model="query.endDate"-->
<!--                type="datetime"-->
<!--                default-time="12:00:00"-->
<!--                placeholder="结束日期">-->
<!--            </el-date-picker>-->

<!--            <span class="left-margin15">快速选择:</span>-->
<!--            <el-select class="left-margin15" v-model="query.time" placeholder="日期" @change="optionChange">-->
<!--              <el-option-->
<!--                  v-for="item in quickTimeList"-->
<!--                  :key="item.value"-->
<!--                  :label="item.label"-->
<!--                  :value="item.value">-->
<!--              </el-option>-->
<!--            </el-select>-->

<!--          </div>-->
<!--        </el-col>-->

<!--        <el-col :span="6">-->
<!--          <el-button class="left-margin30" type="success" icon="el-icon-search" @click="searchClick">查询</el-button>-->
<!--          <el-button class="left-margin15" type="warning" icon="el-icon-error" @click="resetClick">重置</el-button>-->
<!--          <el-button class="left-margin15" type="primary" icon="el-icon-error" @click="exportClick">导出</el-button>-->

<!--        </el-col>-->
<!--      </el-row>-->

<!--      &lt;!&ndash;  折线图  &ndash;&gt;-->
<!--      <c-p-visit-chart class="top-margin30" :clicklist="clickList"  :orderlist="orderlist" :transactionlist="transactionlist"/>-->
<!--      &lt;!&ndash;订购量饼图&ndash;&gt;-->
<!--      <c-p-order-pie-chart class="top-margin30" :orderlist="pieorderlist"/>-->
<!--      &lt;!&ndash;点击量饼图&ndash;&gt;-->
<!--      <c-p-click-pie-chart class="top-margin30" :clicklist="pieclicklist"/>-->
<!--      &lt;!&ndash;订购发起量饼图&ndash;&gt;-->
<!--      <c-p-transaction-pie-chart class="top-margin30" :transactionlist="pietransactionlist"/>-->

<!--      &lt;!&ndash; 数据的表格展示&ndash;&gt;-->
<!--      <el-table-->
<!--          stripe-->
<!--          border-->
<!--          :data="dataList">-->
<!--        <el-table-column-->
<!--            type="index"-->
<!--            indent="#"-->
<!--            width="50">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="cpId"-->
<!--            label="推荐位名称">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="cpName"-->
<!--            label="CP">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="orderCount"-->
<!--            label="订购量">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="gameCount"-->
<!--            label="游戏点击量">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="orderAllCount"-->
<!--            label="订购发起量">-->
<!--        </el-table-column>-->
<!--      </el-table>-->

<!--      <el-pagination-->
<!--          class="user-list-pagination"-->
<!--          @size-change="handleSizeChange"-->
<!--          @current-change="handleCurrentChange"-->
<!--          :current-page="this.query.page"-->
<!--          :page-sizes="[10,  20, 50]"-->
<!--          :page-size="this.query.limit"-->
<!--          layout="total,prev, pager, next, jumper,sizes"-->
<!--          :total="total">-->
<!--      </el-pagination>-->
<!--    </el-card>-->
  </div>
</template>

<script>
  export default {
    name: "RecommendVisit",
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
        dataList: []
      }

    },
    methods: {
      loadData() {

      },

      async getDataList() {
        const res = await this.$http.get(`/hbydGame/recommendGame/getListPvUvByPage?page=${this.query.page}&limit=${this.query.limit}&beginDate=${this.query.beginDate}&recName=${this.query.recName}&endDate=${this.query.endDate}`)
        this.dateList = res.data
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