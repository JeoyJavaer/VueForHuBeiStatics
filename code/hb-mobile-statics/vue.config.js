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
      proxy: {
        '/api': {
          target: 'http://localhost:8090',
          changeOrigin: true,
          pathRewrite: {
            '^/api' : ''
          }
        },

      }
    }

}