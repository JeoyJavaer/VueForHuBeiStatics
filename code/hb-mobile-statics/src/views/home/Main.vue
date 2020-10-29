<!--用于展示整个大的页面-->
<template>

  <div class="home">
    <el-container>
      <el-header class="home-header">
        <img src="~assets/image/logo.png" alt="" class="logo-class">
        <div class="home-div">
          <span>统计系统</span>
        </div>
        <div class="logout-btn">
          <el-button type="info" @click="logout" class="logout-btn">注销</el-button>
        </div>
      </el-header>
      <el-container class="home-nav">
        <el-aside :width="isCollapsed ? '64px':'200px'" class="home-aside">
          <div class="collapse-btn" @click="collapseMenu">| | |</div>

          <el-menu
              :default-active="activePath"
              background-color="#3e4653"
              text-color="#fff"
              :unique-opened="false"
              :collapse="isCollapsed"
              :collapse-transition="false"
              router
              active-text-color="#3F9DFE">

            <!--首页的数据-->
            <el-menu-item index="/home"  key="1000">
              <template slot="title">
                <i class="el-icon-s-home"></i>
                <span>首页</span>
              </template>
            </el-menu-item>


            <el-submenu :index="item.id+''" v-for="(item,index) in menuList" :key="item.id">
              <template slot="title">
                <i :class="iconList[index]"></i>
                <span>{{ item.title }}</span>
              </template>
              <el-menu-item @click="saveNavState(subItem.url)"
                            :index="'/'+subItem.url"
                            v-for="subItem in item.childPermission" :key="subItem.id">
                <template slot="title">
                  <!-- 导航开启路由模式：
             将index值作为导航路由 -->
                  <!-- 二级菜单的模板区域 -->
                  <i class="el-icon-menu"></i>
                  {{ subItem.title }}
                </template>
              </el-menu-item>
            </el-submenu>
          </el-menu>

        </el-aside>

        <el-main class="content">
          <router-view></router-view>
        </el-main>

      </el-container>
    </el-container>

  </div>

</template>

<script>
  export default {
    name: "Main",
    data() {
      return {
        isCollapsed: false,
        activePath: '',
        menuList: [],
        iconList: [
          'el-icon-s-tools',
          'el-icon-user-solid',
          'el-icon-pie-chart',
          'el-icon-thumb',
          'el-icon-s-data',
          'el-icon-shopping-cart-full',
          'el-icon-s-check',
          'el-icon-s-marketing',
          'el-icon-s-opportunity',
        ]

      }
    },

    created() {
      this.getMenuList()
      // this.login()
    },

    methods: {
      collapseMenu() {
        this.isCollapsed = !this.isCollapsed
      },

      async getMenuList() {
        const res = await this.$http.post('hbydGame/role/getRolePermissionMenu');
        let permission = res.permission;
        this.menuList = permission

      },

      async login() {
        this.$http.post("hbydGame/user/login", {
          "account": 'admin',
          "password": this.randomString(32) + window.btoa('zte9!@#'),
          "verifyCode": 'GC65'

        })
      },

      randomString(e) {
        e = e || 32;
        var t = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678",
          a = t.length,
          n = "";
        for (let i = 0; i < e; i++) n += t.charAt(Math.floor(Math.random() * a));
        return n
      },

      saveNavState(path) {
        window.sessionStorage.setItem('activePath', path)
      },

      logout() {

      }

    }
  }
</script>

<style scoped>
  .home {
    background: #55585e;
    /*height: 80%;*/
    min-width: 1360px;
  }

  .el-menu {
    border-right: none;
  }

  .el-header {
    display: flex;
    float: left;
    padding: 0px;


  }

  .logout-btn {
    width: 80px;
    height: 36px;
    padding: 2px;
    float: right;
    vertical-align: middle;
    margin: auto 15px auto auto;
  }

  .home-div {

    flex: 1;
    align-items: center;
    text-align: center;
    font-size: 28px;
    margin: auto;
    color: white;
  }

  .collapse-btn {
    background-color: #4A5064;
    font-size: 10px;
    line-height: 24px;
    color: #fff;
    text-align: center;
    letter-spacing: 0.2em;
    cursor: pointer;
  }


  .home-header {
    position: absolute;
    left: 0;
    top: 0;
    right: 0;
    height: 60px;
    background: #3e4653;
  }

  .home-nav {
    position: absolute;
    top: 60px;
    left: 0;
    right: 0;
    bottom: 0;
  }

  .home-aside {
    height: 100%;
    background: #3e4653;
  }

  .home-span{
    padding: 0 20px;
    text-align: center;
    transition: border-color .3s,background-color .3s,color .3s;
    box-sizing: border-box;
    height: 56px;
    line-height: 56px;
    list-style: none;
    position: relative;
    white-space: nowrap;
    font-size: 14px;
    color: #303133;

    cursor: pointer;
  }

  .logo-class{
    width: auto;
    height: 60px;
  }

</style>