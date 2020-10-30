<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>权限列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-row>
        <!--用户名输入框-->
        <el-col :span="5">
          <div class="demo-input-suffix">
            <span>权限名:</span>
            <el-input
                class="input-username"
                clearable
                placeholder="权限名"
                v-model="query.title">
            </el-input>
          </div>
        </el-col>

        <!--状态选择器-->
        <el-col :span="5">
          <div class="demo-input-suffix">
            <span>权限状态:</span>
                        <el-select v-model="query.status" placeholder="请选择"  class="input-username">
                          <el-option
                              v-for="item in options"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value">
                          </el-option>
                        </el-select>

          </div>
        </el-col>
        <el-col :span="6">
          <el-button type="success" icon="el-icon-circle-plus-search" @click="searchClick">搜索</el-button>
          <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addClick">添加</el-button>
        </el-col>

      </el-row>
      <el-table
          stripe
          border
          :data="rightsList">
        <el-table-column
            type="index"
            indent="#"
            width="50">
        </el-table-column>
        <el-table-column
            prop="id"
            width="80"
            label="权限ID">
        </el-table-column>
        <el-table-column
            prop="title"
            label="权限名称">
        </el-table-column>
        <el-table-column
            prop="url"
            label="请求地址">
        </el-table-column>
        <el-table-column
            prop="code"
            label="权限编码">
        </el-table-column>
        <el-table-column
            prop="type"
            label="类型">
          <template slot-scope="scope">
            <div v-if="scope.row.type===1">功能</div>
            <div v-else="scope.row.type===1">功能</div>
          </template>
        </el-table-column>
        <el-table-column
            prop="description"
            label="描述">
        </el-table-column>
        <el-table-column label="状态" width="80">
          <template slot-scope="scope">
            <el-switch
                inactive-color="#666666"
                v-model="scope.row.status===0" @change="statusChange">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                @click="editClick(scope.row)"
                icon="el-icon-edit"
            >编辑
            </el-button>
            <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="deleteClick(scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
          class="user-list-pagination"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="this.query.page"
          :page-sizes="[10,50,100]"
          :page-size="this.query.limit"
          layout="total,prev, pager, next, jumper,sizes"
          :total="total">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "RightsManager",
    data() {
      return {
        query: {
          page: 1,
          limit: 10,
          title: '',
          status: '',
        },
        rightsList: [],
        total: 0,
        options: [{
          value: -1,
          label: '全部'
        }, {
          value: 0,
          label: '正常状态'
        }, {
          value: 1,
          label: '禁用状态'
        }]

      }
    },

    created() {
      this.getRightsList()
    },

    methods: {
      async getRightsList() {
        //  http://localhost:8090/hbydGame/permiss/findPermission?page=1&limit=10&title=%E7%94%A8%E6%88%B7%E7%AE%A1%E7%90%86&status=0
        let res
        if (this.query.status === -1) {
          res = await this.$http.get(`/hbydGame/permiss/findPermission?page=${this.query.page}&limit=${this.query.limit}&title=${this.query.title}`)
        } else {
          res = await this.$http.get(`/hbydGame/permiss/findPermission?page=${this.query.page}&limit=${this.query.limit}&title=${this.query.title}&status=${this.query.status}`)
        }
        this.rightsList = res.data
        this.total = res.count
      },

      searchClick() {
        this.getRightsList()
      },
      addClick() {

      },

      editClick(row) {

      },
      deleteClick(row) {

      },

      handleSizeChange(pagesize) {
        this.query.limit = pagesize
        this.getRightsList()
      },

      handleCurrentChange(pagesize) {
        this.query.page = pagesize
        this.getRightsList()
      },

      statusChange(newStatus){

      }

    }
  }
</script>

<style scoped>
  .demo-input-suffix {
    display: flex;
  }

  .input-username {
    width: 60%;
    margin-left: 15px;
  }

  .demo-input-suffix span {
    line-height: 44px;
  }
</style>