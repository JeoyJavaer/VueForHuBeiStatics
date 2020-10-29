module.exports={
  configureWebpack:{
    resolve:{
      alias:{
        'assets':'@/assets',
        'components':'@/components',
        'network':'@/network',
        'views':'@/views',
        'plugins':'@/plugins'
      }
    }
  },

  lintOnSave:false,
    devServer: {


      // proxy:"http://127.0.0.1:9090",
      proxy:"http://127.0.0.1:8090",
      port: 8080
      // proxy: {
      //   // '/api': {
      //   //   target: 'http://localhost:9090',
      //   //   secure:false,
      //   //   ws:true,
      //   //   changeOrigin: true,
      //   //   pathRewrite: {
      //   //     '^/api' : ''
      //   //   }
      //   // },
      //
      // }
    }

}