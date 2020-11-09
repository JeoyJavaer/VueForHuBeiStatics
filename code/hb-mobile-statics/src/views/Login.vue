<template>
  <div>

    <div class="login-card">
      <div class="login-header">
        <img src="~assets/image/logo.png" class="login-img">
      </div>
      <div class="login-body">
        <div class="content-left">
        </div>
        <div class="login-container">
          <el-form :model="loginData" :rules="rules" ref="ruleForm" label-width="100px" class="login-form">
            <p class="login-p">用户登录</p>
            <el-form-item label="账户名:" prop="account" class="login-form-item">
              <el-input clearable class="input-username" v-model="loginData.account"></el-input>
            </el-form-item>
            <el-form-item label="密码:" prop="password" class="login-form-item">
              <el-input clearable class="input-username" type="password" v-model="loginData.password"></el-input>
            </el-form-item>
            <el-form-item label="验证码:" prop="verifyCode" class="code-form-item">
              <el-input clearable class="input-code" v-model="loginData.verifyCode"></el-input>
              <img src="http://localhost:8090/hbydGame/verify/getVerifyCode" class="code-img" @click="reloadCode">
            </el-form-item>
            <el-form-item class="login-form-btn">
              <el-button type="primary" @click="login()">登录</el-button>
              <el-button @click="resetForm()">忘记密码</el-button>
            </el-form-item>
          </el-form>
        </div>

      </div>
      <!--      底部链接可以直接到游戏大厅页面-->
      <div class="login-bottom">
        <el-link underline href="https://cmcchb.opcd.com.cn:9528/hbyd_gameHall/index.html" type="info" class="link-aijia">爱家游戏大厅</el-link>
        <el-link underline href="https://cmcchb.opcd.com.cn:9528/hbyd_gameHall/index.html" type="info">爱家游戏管理后台</el-link>
      </div>
    </div>
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
      this.$router.push('/main')
    },
    resetForm() {

    },

    reloadCode(){

    }


  }


}
</script>

<style scoped>
.el-form-item{
  /*height: 55px;*/
}

.login-header {
  height: 8%;
  background: #4A5064;
}

.login-container {
  flex: 1;
}

.input-username {
  width: 60%;
}

.input-code {
  width: 30%;
}

.login-form {
  border: #189b8e solid 1px;
  height: 400px;
  width: 65%;
  margin-top: 25%;
  margin-left: 5%;
}

.login-form-item {
  margin-left: 10%;
  margin-top: 20px;
  alignment: center;
}

.login-card {
  width: 100%;
  height: 100vh;
  padding: 0;
}


.el-card__body {
  padding: 0;
}

.login-img {
  margin-top: 10px;
  width: 240px;
  height: 60px;
  alignment: center;
}

.code-img {
  width: 110px;
  height: 25px;
  /*padding-left: 15px;*/
  padding: 0;


}

.code-form-item {
  padding: 0;
  margin-left: 10%;
  margin-top: 20px;
  /*display: flex;*/
  height: 40px;
}

.login-form-btn {
  margin-top: 40px;
  padding: 0;
  margin-left: 10%;
  alignment: center;
  height: 40px;
}

.login-bottom {
  position: absolute;
  height: 8%;
  left: 0;
  right: 0;
  bottom: 0;
  font-size: 48px;
  display: flex;
  align-content: center;
  justify-content: center;
  /*alignment: center;*/
  /*align-content: center;*/
}

.login-body {
  height: 80vh;
  display: flex;
  background-image: url("~assets/image/bg-3.jpg");
}

.content-left {
  width: 25%;
  margin: 10%;
  margin-left: 20%;
  height: 63%;
  /*border: 1px solid red;*/
  /*background: url("~assets/image/d.png") no-repeat;*/
  background-size: 100% 100%;
  box-sizing: border-box;
  float: left;
  -webkit-transform: rotate(360deg);
  animation: rotation 15s linear infinite;
  -moz-animation: rotation 15s linear infinite;
  -webkit-animation: rotation 15s linear infinite;
  -o-animation: rotation 15s linear infinite;

}

/*@-webkit-keyframes rotation {*/
/*  from {*/
/*    -webkit-transform: rotate(0deg);*/
/*  }*/
/*  to {*/
/*    -webkit-transform: rotate(360deg);*/
/*  }*/
/*}*/

.login-p{
  margin-top: 15px;
  padding-left: 15px;
  padding-bottom: 15px;
  font-size: 24px;
  text-align: left;
  border-bottom: #189b8e dashed 1px;

}


  .link-aijia{
    margin: 0  30px;
  }



</style>
