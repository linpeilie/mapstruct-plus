import { defineUserConfig } from "vuepress";
import type { DefaultThemeOptions } from "vuepress";
import recoTheme from "vuepress-theme-reco";
import { themeConfig } from './config/index'
import { umamiAnalyticsPlugin } from 'vuepress-plugin-umami-analytics'

export default defineUserConfig({
  base: '/mapstruct-plus',
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
  head: [
      ['script', {}, `
          var _hmt = _hmt || [];
          (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?fab881821b3db8a7c460db1c91ea0f3a";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
          })();
      `],
      ['meta', {name: 'baidu-site-verification', content: 'codeva-OceTRzMGJ2'}],
  ],
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
        src: 'https://114.115.147.1/script.js'
    })
  ]
});
