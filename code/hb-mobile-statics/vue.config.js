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
}