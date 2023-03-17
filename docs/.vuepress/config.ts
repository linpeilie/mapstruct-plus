import {defineUserConfig} from "vuepress";
import {shikiPlugin} from "@vuepress/plugin-shiki";
import {searchPlugin} from "@vuepress/plugin-search";
import {googleAnalyticsPlugin} from "@vuepress/plugin-google-analytics"
import { searchConsolePlugin } from 'vuepress-plugin-china-search-console'

import theme from "./theme.js";

export default defineUserConfig({
    base: "/",

    head: [
//         ['script', {}, `
//             var _hmt = _hmt || [];
//             (function() {
//               var hm = document.createElement("script");
//               hm.src = "https://hm.baidu.com/hm.js?fab881821b3db8a7c460db1c91ea0f3a";
//               var s = document.getElementsByTagName("script")[0];
//               s.parentNode.insertBefore(hm, s);
//             })();
//         `],
        ['meta', {name: 'baidu-site-verification', content: 'codeva-OceTRzMGJ2'}],
        ['meta', {name: 'bytedance-verification-code', content: 'fXSJ80ILSanHaf/RIZGQ'}],
    ],

    locales: {
        "/": {
            lang: "zh-CN",
            title: "MapstructPlus",
            description: "MapstructPlus指南",
        },
    },

    theme,

    plugins: [
        shikiPlugin({
            theme: 'one-dark-pro'
        }),
        searchPlugin({
            isSearchable: (page) => page.path !== '/'
        }),
        googleAnalyticsPlugin({
            id: 'G-SXEZVNR8FZ'
        }),
        searchConsolePlugin({
            baiduId: 'fab881821b3db8a7c460db1c91ea0f3a'
        })
    ]
});
