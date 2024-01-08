import { defineUserConfig } from "vuepress";
import type { DefaultThemeOptions } from "vuepress";
import recoTheme from "vuepress-theme-reco";
import { themeConfig } from './config/index'
import { umamiAnalyticsPlugin } from 'vuepress-plugin-umami-analytics'

export default defineUserConfig({
  locales: {
    '/': {
      lang: '中文',
      title: 'MapStructPlus',
      description: '',
    },
    '/en/': {
      lang: 'English',
      title: 'MapStructPlus',
      description: ''
    }
  },
  theme: recoTheme(themeConfig),
  markdown: {
    anchor: {
      permalink: false,
      permalinkBefore: false
    }
  },
  plugins: [
    umamiAnalyticsPlugin({
        id: '50be7a94-e1d7-4d49-a8db-67b17acaa0b3',
        src: 'http://114.115.147.1:3000/script.js'
    })
  ]
});
