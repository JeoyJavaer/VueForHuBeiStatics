<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">

      <el-row>
        <!--用户名输入框-->
        <el-col :span="5">
          <div class="demo-input-suffix">
            <span>用户名:</span>
            <el-input
                class="input-username"
                clearable
                placeholder="请输入用户名"
                v-model="query.account">
            </el-input>
          </div>
        </el-col>

        <!--真实名称输入框-->
        <el-col :span="5">
          <div class="demo-input-suffix">
            <span>真实姓名:</span>
            <el-input
                class="input-username"
                clearable
                placeholder="请输入真实姓名"
                v-model="query.realName">
            </el-input>
          </div>
        </el-col>
        <!--职位选择器-->
        <el-col :span="5">
          <div class="demo-input-suffix">
            <span>职位:</span>
            <el-select v-model="query.roleId" placeholder="请选择" @change="optionChange" class="input-username">
              <el-option
                  v-for="item in roleList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>

          </div>
        </el-col>
        <el-col :span="6">
          <el-button type="success" icon="el-icon-circle-plus-search" @click="searchUserClick">搜索</el-button>
          <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addUserClick">添加</el-button>
        </el-col>

      </el-row>
      <el-table
          stripe
          border
          :data="userList">
        <el-table-column
            type="index"
            indent="#"
            width="50">
        </el-table-column>
        <el-table-column
            prop="account"
            label="用户名"
        >
        </el-table-column>
        <el-table-column
            prop="realName"
            label="真名"
        >
        </el-table-column>
        <el-table-column
            prop="phoneNumber"
            label="电话">
        </el-table-column>
        <el-table-column
            prop="email"
            label="邮箱">
        </el-table-column>
        <el-table-column
            prop="role.name"
            label="职位">
        </el-table-column>
        <el-table-column
            prop="lastLoginTime"
            label="上一次登录时间">
        </el-table-column>
        <el-table-column label="状态" width="80">
          <template slot-scope="scope">
            <el-switch
                v-model="!scope.row.status" @change="userStateChange(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="primary"
                @click="editClick( scope.row)"
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
          :page-sizes="[10,  20, 50]"
          :page-size="this.query.limit"
          layout="total,prev, pager, next, jumper,sizes"
          :total="totalItem">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "UserManager",
    data() {
      return {
        query: {
          page: 1,
          limit: 10,
          account:'',
          realName:'',
          roleId:''
        },
        totalItem: 0,
        userList: [],
        roleList: [],
        username: '',


      }

    },

    created() {
      this.getRoleList()
      this.getUserList()
    },
    methods: {
      async getRoleList() {
        const res = await this.$http.post('/hbydGame/user/findRoles');
        this.roleList = res.list
      },

      async getUserList() {
        const res = await this.$http.get(`/hbydGame/user/findUser?page=${this.query.page}&limit=${this.query.limit}`)
        this.userList = res.data;
        this.totalItem = res.count

      },

      userStateChange(row) {

      },

      editClick(row) {

      },
      deleteClick(row) {

      },

      handleSizeChange(pageSize) {
        this.query.limit = pageSize
        this.getUserList()
      },

      handleCurrentChange(page) {
        this.query.page = page
        this.getUserList()
      },

      searchUserClick(){
        console.log(this.query);
      },
      addUserClick(){

      },
      optionChange(option){
        console.log(option);
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