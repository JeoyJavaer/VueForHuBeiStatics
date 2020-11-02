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
        <el-col :span="16">
          <div>
            <span class="cp-span">CP:</span>
            <el-select class="left-margin15" v-model="query.cp" placeholder="游戏分类" @change="optionChange">
              <el-option
                  v-for="item in cpList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
            <span>查询日期:</span>
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
            <span class="cp-span">CP:</span>
            <el-select class="left-margin15" v-model="query.cp" placeholder="时间" @change="optionChange">
              <el-option
                  v-for="item in cpList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </div>
        </el-col>
        <el-col :span="8">
          <el-button class="left-margin15" type="success" icon="el-icon-search" @click="searchClick">查询</el-button>
          <el-button class="left-margin15" type="warning" icon="el-icon-error" @click="resetClick">重置</el-button>
          <el-button class="left-margin15" type="primary" icon="el-icon-error" @click="exportClick">导出</el-button>
        </el-col>
      </el-row>


      <!--    -->
      <!-- 数据的表格展示-->
      <el-table
          stripe
          border
          show-summary
          :data="staticList">
        <el-table-column
            type="index"
            indent="#"
            width="50">
        </el-table-column>
        <el-table-column
            prop="cpId"
            label="cpid">
        </el-table-column>
        <el-table-column
            prop="cpName"
            label="CP">
        </el-table-column>
        <el-table-column
            prop="orderCount"
            label="订购量">
        </el-table-column>
        <el-table-column
            prop="gameCount"
            label="游戏点击量">
        </el-table-column>
        <el-table-column
            prop="orderAllCount"
            label="订购发起量">
        </el-table-column>
      </el-table>

    </el-card>
  </div>
</template>

<script>
  export default {
    name: "GameCategory",
    data() {
      return {
        query: {
          page: 1,
          limit:10
        },

        categoryList:[],
        visitList:[]
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

      getDayVisitList() {

      },
      async getCategoryList() {
          const  res= await  this.$http.get(`/hbydGame/gameCategory/getGameCategory.do`)
        this.categoryList=res
      }
    }

  }
</script>

<style scoped>

</style>