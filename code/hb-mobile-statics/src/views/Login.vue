<template>
  <div>login</div>
</template>

<script>
  export default {
    name: "Login",

    created() {
      this.getVerifyCode()
    },

    methods: {
      async getVerifyCode() {
        const {data : res } = await this.$http.get("/hubeistatics/user/getVerifyCode")
        console.log(res);
      }
    }


  }
</script>

<style scoped>

</style>