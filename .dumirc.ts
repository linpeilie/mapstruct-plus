import { defineConfig } from 'dumi';

export default defineConfig({
  themeConfig: {
    name: 'mapstruct-plus',
    logo: false,
    prefersColor: { default: 'auto' },
    github: 'https://github.com/linpeilie',
    footer: false
  },
  base: '/',
  publicPath: '/',
  sitemap: { hostname: 'https://mapstruct.plus' }
});
