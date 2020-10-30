<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" >
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
      <el-breadcrumb-item>修改密码</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-alert
          title="请妥善保管好您的密码"
          type="error"
          center
          :closable="false"
          >
      </el-alert>
      <el-form :rules="rules" ref="profile" :model="passwordInfo" label-width="180px" class="top-margin15 profile-table">
        <el-form-item label="原密码:" prop="originPassword">
          <el-input type="password" class="input-profile" v-model="passwordInfo.originPassword"></el-input>
        </el-form-item>
        <el-form-item label="新密码:" prop="newPassword">
          <el-input type="password"  class="input-profile" v-model="passwordInfo.newPassword"></el-input>
        </el-form-item>
        <el-form-item label="确认密码:" prop="confirmPassword">
          <el-input type="password"  class="input-profile" v-model="passwordInfo.confirmPassword"></el-input>
        </el-form-item>


        <el-form-item>
          <el-button type="primary" @click="submit">保存</el-button>
          <el-button @click="reset">重置</el-button>
        </el-form-item>

      </el-form>
    </el-card>

  </div>
</template>

<script>
  export default {
    name: "ChangerPassword",
    data() {
      return {

        passwordInfo: {
          originPassword:'',
          newPassword:'',
          confirmPassword:'',
        },
        rules: {
          originPassword: [
            {required: true, message: '请输入原密码', trigger: 'blur'},
            {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
          ],
          newPassword: [
            {required: true, message: '请输入新密码', trigger: 'change'},
            {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
          ],
          confirmPassword: [
            {required: true, message: '请输入确认密码', trigger: 'change'},
            {min: 7, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
          ],


        }
      }

    },

    methods:{
      submit(){

      },
      reset(){

      }
    }
  }
</script>

<style scoped>
  .input-profile {
    width: 250px;
    margin-left: 15px;
  }

  .profile-table {
    justify-content: center;
    width: 700px;
    alignment: center;
    margin-left: calc(50% - 350px);
  }
</style>