import * as zhConfig from './zh'
import * as enConfig from './en'

export const themeConfig = {
  locales: {
    '/': {
      selectLanguageText: 'Languages',
      selectLanguageName: '简体中文',
      lastUpdateText: '最后更新时间',
      navbar: zhConfig.navbar,
      series: zhConfig.series
    },
    '/en/': {
      selectLanguageText: 'Languages',
      selectLanguageName: 'English',
      navbar: enConfig.navbar,
      series: enConfig.series
    }
  },
  logo: '/logo.svg',
  author: 'linpeilie',
  docsRepo: "https://github.com/linpeilie/mapstruct-plus",
  docsBranch: "main",
  docsDir: "/docs",
  
}