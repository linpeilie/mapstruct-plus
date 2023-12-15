import { defineUserConfig } from "vuepress";
import type { DefaultThemeOptions } from "vuepress";
import recoTheme from "vuepress-theme-reco";
import { themeConfig } from './config/index'

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
  }

  // debug: true,
});
