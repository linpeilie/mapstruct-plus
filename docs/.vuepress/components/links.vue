<template>
  <div class="demo-container">
    <div class="card card-side bg-base-100 shadow-xl" v-for="item in firendLinks" @click="toLink(item.link)">
      <figure>
        <img
          :src="item.logo"
          style="height: 80px"
          alt="Logo" />
      </figure>
      <div class="card-body">
        <div class="card-title">{{ item.title }}</div>
        <div>{{ item.desc }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { links } from "./links.ts";

const firendLinks = links;

function toLink(link) {
    window.open(link, '_blank');
}
</script>

<style>
.demo-container {
}

.shadow-xl {
    --tw-shadow-colored: 0 20px 25px -5px 0 0 #0000, 0 8px 10px -6px 0 0 #0000;
    box-shadow: 0 0 #0000,0 0 #0000,0 20px 25px -5px rgb(0 0 0 / .1), 0 8px 10px -6px rgb(0 0 0 / .1)
}

.bg-base-100 {
    --tw-bg-opacity: 1;
    background-color: var(--fallback-b1,oklch(100% 0 0/1))
}

.card :where(figure:first-child) {
    overflow: hidden;
    border-start-start-radius: inherit;
    border-start-end-radius: inherit;
    border-end-start-radius: unset;
    border-end-end-radius: unset;
}

.card-side :where(figure:first-child) {
    overflow: hidden;
    border-start-start-radius: inherit;
    border-start-end-radius: unset;
    border-end-start-radius: inherit;
    border-end-end-radius: unset;
}

.card-side {
    align-items: stretch;
    flex-direction: row;
}

.card figure {
    display: flex;
    align-items: center;
    justify-content: center;
    padding-left: 2rem;
}

.card {
    position: relative;
    display: flex;
    border-radius: 1rem 1rem;
    margin-bottom: 1rem;
    cursor: pointer;
}

.card-body {
    display: flex;
    flex: 1 1 auto;
    flex-direction: column;
    padding: 2rem;
    gap: .5rem;
    justify-content: space-around;
}

.card-title {
    display: flex;
    align-items: center;
    gap: .5rem;
    font-size: 1.25rem;
    line-height: 1.75rem;
    font-weight: 600;
}
</style>