<template>
  <div>

    <el-card class="login-card">
      <div  title="登录页面"class="login-header">
        <img src="~assets/image/logo.png" width="240" height="60">
      </div>
      <el-form :model="loginData" :rules="rules" ref="ruleForm" label-width="100px" class="login-form">
        <el-form-item label="账户名:" prop="account" class="login-form-item">
          <el-input class="input-username" v-model="loginData.account"></el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="password">
          <el-input class="input-username" type="password" v-model="loginData.password"></el-input>
        </el-form-item>
        <el-form-item label="验证码:" prop="verifyCode">
          <el-input class="input-username" v-model="loginData.verifyCode"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login()">立即创建</el-button>
          <el-button @click="resetForm()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Login",

  created() {
    // this.getVerifyCode()
  },

  data() {
    return {
      loginData: {
        account: '',
        password: '',
        verifyCode: ''
      },
      rules: {
        account: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'change'},
          {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
        ],
        verifyCode: [
          {required: true, message: '请输入密码', trigger: 'change'},
          {min: 4, max: 4, message: '长度在 4个字符', trigger: 'blur'}
        ],

      }
    }
  },


  methods: {
    async getVerifyCode() {
      const {data: res} = await this.$http.get("/hubeistatics/user/getVerifyCode")
      console.log(res);
    },

    login() {
      // this.$router.push('/main')
    },
    resetForm() {

    }


  }


}
</script>

<style scoped>

.login-header{
  height: 80px;
  background: #4A5064;
}
.input-username {
  width: 60%;

}

.login-form {
  padding-top: 5%;

  height: 600px;
  width: 50%;
  margin-left: 25%;

}

.login-card {
  width: 100vh;
  height: 100vh;
  padding: 0;
}

.login-form-item{
  alignment: center;
}
.el-card__body{
  padding: 0;
}

</style>
