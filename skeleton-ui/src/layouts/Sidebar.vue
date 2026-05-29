<template>
  <aside
    :class="[
      'flex flex-col bg-white dark:bg-gray-800 border-r border-gray-200 dark:border-gray-700 transition-all duration-300',
      collapsed ? 'w-16' : 'w-60',
    ]"
  >
    <div class="flex items-center h-14 px-4 border-b border-gray-200 dark:border-gray-700 shrink-0">
      <span v-show="!collapsed" class="text-lg font-bold text-primary-600 dark:text-primary-400 truncate">
        Skeleton
      </span>
    </div>

    <nav class="flex-1 overflow-y-auto p-2 space-y-1">
      <template v-for="item in menuItems" :key="item.path">
        <router-link :to="item.path" class="sidebar-link" :class="{ active: isActive(item.path) }">
          <span class="text-lg shrink-0">{{ item.meta?.icon || '📄' }}</span>
          <span v-show="!collapsed" class="truncate">{{ item.meta?.title }}</span>
        </router-link>
      </template>
    </nav>

    <div class="p-2 border-t border-gray-200 dark:border-gray-700">
      <button class="sidebar-link w-full" @click="app.toggleSidebar()">
        <span class="text-lg shrink-0">{{ collapsed ? '▶' : '◀' }}</span>
        <span v-show="!collapsed" class="truncate">收起</span>
      </button>
    </div>
  </aside>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { useAppStore } from '@/stores/app'
import { constantRoutes } from '@/router/routes'

defineProps<{ collapsed: boolean }>()
const route = useRoute()
const app = useAppStore()

const menuItems = computed(() => {
  const layout = constantRoutes.find(r => r.path === '/')
  return (layout?.children || []).filter(r => !r.meta?.hidden)
})

function isActive(path: string) {
  return route.path === path || route.path.startsWith(path + '/')
}
</script>
