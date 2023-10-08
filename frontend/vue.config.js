const { defineConfig } = require('@vue/cli-service')

const isProduction = true

module.exports = defineConfig({
  transpileDependencies: true,
  productionSourceMap: false,
  publicPath: isProduction ? '/reserve-me' : '/',
  assetsDir: './assets',
  css: {
    loaderOptions: {
      scss: {
        additionalData: `
          @import "@/assets/scss/reserve-me/global/colors.scss";
          @import "@/assets/scss/reserve-me/global/mixins.scss";
          @import "@/assets/scss/reserve-me/global/vars.scss";
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
  }
})
