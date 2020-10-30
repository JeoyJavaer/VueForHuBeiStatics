<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
      <el-breadcrumb-item>个人资料</el-breadcrumb-item>
      <el-breadcrumb-item>基本资料</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-alert
          title="为了让公司更好地进行管理，以下信息将公开地显示在个人资料页，请确保信息的准确性"
          type="info"
          center
          show-icon>
      </el-alert>
      <el-form :rules="rules" ref="profile" :model="profileInfo" label-width="180px" class="top-margin15 profile-table">
        <el-form-item label="账户:" prop="account">
          <el-input class="input-profile" :disabled="true" v-model="profileInfo.account"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名:" prop="realName">
          <el-input class="input-profile" v-model="profileInfo.realName"></el-input>
        </el-form-item>
        <el-form-item label="手机号码:" prop="phoneNumber">
          <el-input class="input-profile" v-model="profileInfo.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱:" prop="email">
          <el-input class="input-profile" v-model="profileInfo.email"></el-input>
        </el-form-item>
        <el-form-item label="职位" prop="roleName">
          <el-input class="input-profile" :disabled="true" v-model="role.name"></el-input>
        </el-form-item>
        <el-form-item label="创建时间:" prop="createTime">
          <el-input class="input-profile" :disabled="true" v-model="profileInfo.createTime"></el-input>
        </el-form-item>
        <el-form-item label="上一次登录时间:" prop="lastLoginTime">
          <el-input class="input-profile" :disabled="true" v-model="profileInfo.lastLoginTime"></el-input>
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
    name: "Profile",
    data() {
      return {
        role: {},
        profileInfo: {},
        rules: {
          account: [
            {required: true, message: '请输入账户名称', trigger: 'blur'},
            {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
          ],
          realName: [
            {required: true, message: '真实姓名', trigger: 'change'},
            {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
          ],
          phoneNumber: [
            {required: true, message: '手机号码', trigger: 'change'},
            {min: 7, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
          ],
          email: [
            {required: true, message: '电子邮箱', trigger: 'change'},
            {min: 3, max: 15, message: '长度在 3 到 50 个字符', trigger: 'blur'}
          ],
          roleName: [

            {min: 2, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
          ],
          createTime: [
            {required: true, message: '创建时间', trigger: 'change'},
            {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
          ],
          lastLoginTime: [
            {required: true, message: '上一次登录时间', trigger: 'change'},
            {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
          ]
        }
      }

    },
    created() {
      this.getCurrentUserInfo()
    },

    methods: {
      async getCurrentUserInfo() {
        let param = {
          id: 1
        }

        const res = await this.$http({
          method: 'post',
          url: '/hbydGame/user/findCurrentUser',
          // 利用 transformRequest 进行转换配置
          transformRequest: [
            function (oldData) {
              let newStr = ''
              for (let item in oldData) {
                newStr += encodeURIComponent(item) + '=' + encodeURIComponent(oldData[item]) + '&'
              }
              newStr = newStr.slice(0, -1)
              return newStr
            }
          ],
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data: param,
        })

        this.profileInfo = res.data
        this.role= res.data.role
      },

      submit() {

      },

      reset() {
        this.getCurrentUserInfo()
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