<template>
  <header class="flex items-center justify-between h-14 px-4 bg-white dark:bg-gray-800
                 border-b border-gray-200 dark:border-gray-700 shrink-0">
    <div class="flex items-center gap-3">
      <button class="p-1.5 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
              @click="app.toggleSidebar()">
        <span class="text-lg">☰</span>
      </button>
      <span class="text-sm text-gray-500 dark:text-gray-400">{{ route.meta.title || '' }}</span>
    </div>

    <div class="flex items-center gap-2">
      <button class="p-1.5 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
              :title="theme.isDark ? '亮色模式' : '暗黑模式'" @click="theme.toggle()">
        <span class="text-lg">{{ theme.isDark ? '☀️' : '🌙' }}</span>
      </button>

      <div class="relative group">
        <button class="flex items-center gap-2 p-1.5 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-700">
          <span class="w-8 h-8 rounded-full bg-primary-500 flex items-center justify-center text-white text-sm font-medium">
            {{ auth.nickname?.charAt(0)?.toUpperCase() || 'U' }}
          </span>
        </button>
        <div class="absolute right-0 top-full mt-1 w-48 bg-white dark:bg-gray-800 rounded-lg shadow-lg
                    border border-gray-200 dark:border-gray-700 py-1 opacity-0 invisible
                    group-hover:opacity-100 group-hover:visible transition-all z-50">
          <div class="px-4 py-2 border-b border-gray-100 dark:border-gray-700">
            <p class="text-sm font-medium">{{ auth.nickname || auth.username }}</p>
          </div>
          <button class="w-full text-left px-4 py-2 text-sm hover:bg-gray-50 dark:hover:bg-gray-700"
                  @click="router.push('/profile')">个人中心</button>
          <button class="w-full text-left px-4 py-2 text-sm text-red-600 hover:bg-red-50 dark:hover:bg-red-900/20"
                  @click="handleLogout">退出登录</button>
        </div>
      </div>
    </div>
  </header>
</template>

<script setup lang="ts">
import { useRouter, useRoute } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { useAppStore } from '@/stores/app'
import { useThemeStore } from '@/stores/theme'

const router = useRouter()
const route = useRoute()
const auth = useAuthStore()
const app = useAppStore()
const theme = useThemeStore()

function handleLogout() { auth.logout(); router.push('/login') }
</script>
