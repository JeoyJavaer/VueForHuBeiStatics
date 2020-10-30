<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" class="user-breadcrumb">
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-row>
        <!--用户名输入框-->
        <el-col :span="6">
          <div class="demo-input-suffix">
            <span>职位名:</span>
            <el-input
                class="input-username"
                clearable
                placeholder="职位名称"
                v-model="query.name">
            </el-input>
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
          :data="roleList">
        <el-table-column
            type="index"
            indent="#"
            width="50">
        </el-table-column>
        <el-table-column
            prop="id"
            label="编号"
        >
        </el-table-column>
        <el-table-column
            prop="name"
            label="名称"
        >
        </el-table-column>
        <el-table-column
            prop="description"
            label="职位描述">
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
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
          :total="total">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "RoleManager",
    data(){
      return {
        query:{
          name:'',
          page:1,
          limit:10
        },
        total:0,
        roleList:[]
      }
    },

    created() {
      this.getRoleList()
    },

    methods:{
      async getRoleList(){
        //http://localhost:8090/hbydGame/role/findRoles?page=1&limit=10
        const  res=await this.$http.get(`/hbydGame/role/findRoles?page=${this.query.page}&limit=${this.query.limit}&name=${this.query.name}`)
        this.roleList=res.data
        this.total=res.count
      },

      handleSizeChange(pagesize){
        this.query.limit=pagesize
        this.getRoleList()
      },

      handleCurrentChange(pagenum){
        this.query.page=pagenum
        this.getRoleList()
      },

      editClick(row){

      },

      deleteClick(row){

      },

      searchClick(){
        this.getRoleList()
      },
      addClick(){

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