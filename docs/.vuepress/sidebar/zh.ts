import {sidebar} from "vuepress-theme-hope";

export const zhSidebar = sidebar({
    "/": [
        {
            text: '介绍',
            prefix: 'introduction/',
            link: 'introduction/',
            children: 'structure'
        },
        {
            text: "指南",
            icon: "note",
            prefix: "guide/",
            children: "structure",
        },
        {
            text: '更新日志',
            prefix: 'release/',
            children: 'structure'
        }
    ],
});
