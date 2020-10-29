import Vue from 'vue'
import VueRouter from 'vue-router'


const Login = () => import("@/views/Login");
const Main = () => import("@/views/home/Main");
const Home = () => import("@/views/home/Home");
const CPClickTotal = () => import("@/views/cpinfo/CPClickTotal");
const CPGameClick = () => import("@/views/cpinfo/CPGameClick.vue");
const GameCategory = () => import("@/views/gamestatis/GameCategory");
const GameRanking= () => import("@/views/gamestatis/GameRanking");
const GameSingleGame = () => import("@/views/gamestatis/GameSingleGame");
const CarouselFigureClick = () => import("@/views/hallstatics/CarouselFigureClick");
const CarouselFigureVisit= () => import("@/views/hallstatics/CarouselFigureVisit");
const DetailPageClick = () => import("@/views/hallstatics/DetailPageClick");
const DetailGame = () => import("@/views/hallstatics/DetailGame");
const GameSingle = () => import("@/views/hallstatics/GameSingle");
const GameTotal = () => import("@/views/hallstatics/GameTotal");
const HallPageUser = () => import("@/views/hallstatics/HallPageUser");
const HallVisit = () => import("@/views/hallstatics/HallVisit");
const RecommendClick = () => import("@/views/hallstatics/RecommendClick");
const RecommendVisit = () => import("@/views/hallstatics/RecommendVisit");
const OrderCP= () => import("@/views/orderstatics/OrderCP");
const OrderDayTotal = () => import("@/views/orderstatics/OrderDayTotal");
const ChangerPassword = () => import("@/views/personalcenter/ChangerPassword");
const Profile = () => import("@/views/personalcenter/Profile");
const LoggingManager = () => import("@/views/systemmanager/LoggingManager");
const RightsManager = () => import("@/views/systemmanager/RightsManager");
const RoleManager = () => import("@/views/systemmanager/RoleManager");
const SystemInfo = () => import("@/views/systemmanager/SystemInfo");
const UserManager = () => import("@/views/systemmanager/UserManager");
const UserBehavior = () => import("@/views/userbehavior/UserBehavior");
const UserBehaviorClick = () => import("@/views/userbehavior/UserBehaviorClick");
const UserRetention = () => import("@/views/userretention/UserRetention");


Vue.use(VueRouter);

const routes = [
  {
    path: '',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/main',
    component: Main,
    redirect:'/home',
    children:[
      {path:'/home',component:Home},
      {path:'/views/user/user.jsp',component:UserManager},
      {path:'/views/logging.jsp',component:LoggingManager},
      {path:'/views/permiss/permission.jsp',component:RightsManager},
      {path:'/views/role/role.jsp',component:RoleManager},
      {path:'/views/systeminfo.jsp',component:SystemInfo},

      {path:'/views/user/userInfo.jsp',component:Profile},
      {path:'/views/user/userPasswd.jsp',component:ChangerPassword},

      {path:'/views/count/musicPlayCount.jsp',component:GameSingle},
      {path:'/views/count/CountPvUv.jsp',component:GameTotal},
      {path:'/views/count/GameVisitCountPvUv.jsp',component:HallPageUser},
      {path:'/views/count/GameVisitList.jsp',component:HallVisit},
      {path:'/views/count/recommendGamePvUv.jsp',component:RecommendClick},
      {path:'/views/count/activityGamePvUv.jsp',component:CarouselFigureClick},
      {path:'/views/count/recommendGame.jsp',component:RecommendVisit},
      {path:'/views/count/activityGame.jsp',component:CarouselFigureVisit},
      {path:'/views/gameDetail/gameDetailCountPvUv.jsp',component:DetailPageClick},
      {path:'/views/gameDetail/gameDetailGameCountPvUv.jsp',component:DetailPageClick},
      {path:'/views/gameDetail/gameDetailGameCountPvUv.jsp',component:DetailGame},

      {path:'/views/userAction/userAction.jsp',component:UserBehavior},
      {path:'/views/userAction/userActionPvUv.jsp',component:UserBehaviorClick},

      {path:'/views/gameInfo/gameInfoOrder.jsp',component:GameSingleGame},
      {path:'/views/GameCategory/GameCategoryPvUv.jsp',component:GameCategory},
      {path:'/views/leadboardGame/leadboardGame.jsp',component:GameRanking},

      {path:'/views/billMonth/countTotalFee.jsp',component:OrderDayTotal},
      {path:'/views/gameCp/gameCpCount.jsp',component:OrderCP},


      {path:'/views/gameCp/gameCpDgCount.jsp',component:CPGameClick},
      {path:'/views/gameCp/gameCpDgCount.jsp',component:CPClickTotal},

      {path:'/views/retentionRate/retentionRate.jsp',component:UserRetention},
    ]
  },

]

const router = new VueRouter({
  // mode: 'history',
  routes
});

export default router
