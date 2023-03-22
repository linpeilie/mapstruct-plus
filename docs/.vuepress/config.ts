import {defineUserConfig} from "vuepress";
import {shikiPlugin} from "@vuepress/plugin-shiki";
import {searchPlugin} from "@vuepress/plugin-search";
import {googleAnalyticsPlugin} from "@vuepress/plugin-google-analytics"
import { componentsPlugin } from "vuepress-plugin-components"

import theme from "./theme.js";

export default defineUserConfig({
    base: "/",

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
        componentsPlugin({
              rootComponents: {
                notice: [
                  {
                    path: "/",
                    title: "推荐最新开源项目 EasyRelation",
                    content: "EasyRelation 是一个简单、高效的自动关联数据框架，可以通过一行代码，自动关联查询并填充需要的数据，对于性能影响极小，且省略了大量冗余代码。",
                    actions: [
                      {
                        text: "GitHub",
                        link: "https://github.com/linpeilie/easy-relation",
                        type: "default",
                      },
                      {
                        text: "Gitee",
                        link: "https://gitee.com/easii/easy-relation",
                        "type": "default"
                      },
                    ],
                    fullscreen: false,
                  }
                ],
              },
            }),
    ]
});
