const { defineConfig } = require('@vue/cli-service')

const isProduction = true

module.exports = defineConfig({
  transpileDependencies: ['quasar'],

  productionSourceMap: false,
  publicPath: isProduction ? '/app-online' : '/',
  assetsDir: './assets',

  css: {
    loaderOptions: {
      scss: {
        additionalData: `
          @import "@/assets/scss/app-online/global/colors.scss";
          @import "@/assets/scss/app-online/global/mixins.scss";
          @import "@/assets/scss/app-online/global/vars.scss";
        `
      }
    }
  },

  configureWebpack: {
    optimization: {
      splitChunks: {
        cacheGroups: {
          default: false,
          // Merge all the CSS into one file
          styles: {
            name: 'app',
            test: (m) => m.constructor.name === 'CssModule',
            chunks: 'all',
            minChunks: 1,
            enforce: true
          }
        }
      }
    }
  },

  chainWebpack(config) {
    config.optimization.delete('splitChunks')
  },

  pluginOptions: {
    quasar: {
      importStrategy: 'kebab',
      rtlSupport: false
    }
  }
})
