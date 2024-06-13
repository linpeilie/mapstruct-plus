import { defineUserConfig } from "vuepress";
import type { DefaultThemeOptions } from "vuepress";
import recoTheme from "vuepress-theme-reco";
import { themeConfig } from './config/index'
import { sitemapPlugin } from '@vuepress/plugin-sitemap'
import { webpackBundler } from '@vuepress/bundler-webpack'
import { viteBundler } from '@vuepress/bundler-vite'

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
    sitemapPlugin({
        hostname: 'mapstruct.plus'
    }),
  ],
  bundler: webpackBundler(),
});
