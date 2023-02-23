import { defineConfig } from 'dumi';

export default defineConfig({
  themeConfig: {
    name: 'mapstruct-plus',
    logo: false,
    nav: [{ title: '指南', link: '/guide' }],
    prefersColor: { default: 'auto' },
    github: 'https://github.com/linpeilie',
    footer: false
  },
  base: '/',
  publicPath: '/'
});