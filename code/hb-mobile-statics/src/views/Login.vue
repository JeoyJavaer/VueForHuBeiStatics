<template>
  <div>

    <el-button type="info"  >退出登录</el-button>
    <el-button type="primary"  @click="login">主要按钮</el-button>
  </div>
</template>

<script>
  export default {
    name: "Login",

    created() {
      // this.getVerifyCode()
    },

    methods: {
      async getVerifyCode() {
        const {data : res } = await this.$http.get("/hubeistatics/user/getVerifyCode")
        console.log(res);
      },

      login(){
        this.$router.push('/main')
      }
    }


  }
</script>

<style scoped>

</style>