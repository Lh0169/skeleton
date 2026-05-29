<template>
  <div v-if="app.tabs.length > 0"
       class="flex items-center h-10 px-2 bg-white dark:bg-gray-800 border-b border-gray-200 dark:border-gray-700 gap-1 overflow-x-auto shrink-0">
    <div v-for="tab in app.tabs" :key="tab.path"
         :class="['flex items-center gap-1 px-3 py-1 rounded text-xs cursor-pointer transition-colors whitespace-nowrap',
                  app.activeTab === tab.path ? 'bg-primary-50 text-primary-600 dark:bg-gray-700 dark:text-primary-400' : 'hover:bg-gray-100 dark:hover:bg-gray-700']"
         @click="router.push(tab.path)">
      <span>{{ tab.title }}</span>
      <button v-if="app.tabs.length > 1" class="hover:text-red-500" @click.stop="app.removeTab(tab.path)">✕</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useAppStore } from '@/stores/app'

const router = useRouter()
const app = useAppStore()
</script>
