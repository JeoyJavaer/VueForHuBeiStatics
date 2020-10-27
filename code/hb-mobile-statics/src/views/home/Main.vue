<!--用于展示整个大的页面-->
<template>
  <div class="main">
    <el-container>
      <!--头部-->
      <el-header class="header-container">Header</el-header>

      <el-container class="aside-main-container">

        <!--侧边栏-->
        <el-aside :width="isCollapsed? '64px':'200px'" class="aside-container">
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
            <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
              <template slot="title">
                <i :class="iconList[item.id]"></i>
                <span>{{ item.title }}</span>
              </template>
              <el-menu-item @click="saveNavState('/'+subItem.path)" :index="'/'+subItem.url.substr(0,subItem.url.indexOf('.jsp'))"
                            v-for="subItem in item.children" :key="subItem.id">
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

        <!--数据展示区域-->
        <el-main class="main-container">
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
        iconList: {
          '72': 'el-icon-s-custom',
          '67': 'el-icon-s-cooperation',
          '128': 'el-icon-s-goods',
          '102': 'el-icon-shopping-cart-1',
          '133': 'el-icon-s-marketing',
          '136': 'el-icon-s-marketing',
          '137': 'el-icon-s-marketing',
          '153': 'el-icon-s-marketing',
          '158': 'el-icon-s-marketing',
        }

      }
    },

    created() {
      this.getMenuList()
    },

    methods: {
      collapseMenu() {

      },

      async getMenuList() {
        const res = await this.$axios.get('/role/getRolePermissionMenu')
        let permission = res.permission;
        this.menuList=permission

      },

      saveNavState(path) {
        window.sessionStorage.setItem('activePath', path)
      }

    }
  }
</script>

<style scoped>
  .main {
    background: var(--color-tint);
  }

  .header-container {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    height: 60px;
    background: #3e4653;
  }

  .aside-main-container {
    position: absolute;
    top: 60px;
    left: 0;
    right: 0;
    bottom: 0;
  }


  .aside-container {

  }

  .main-container {
    background: #ffffff;
  }


</style>