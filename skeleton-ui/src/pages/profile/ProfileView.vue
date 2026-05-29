<template>
  <div>
    <h2 class="text-2xl font-bold mb-6">个人中心</h2>
    <div class="bg-white dark:bg-gray-800 rounded-xl p-6 border border-gray-200 dark:border-gray-700 max-w-lg">
      <div class="flex items-center gap-4 mb-6">
        <span class="w-16 h-16 rounded-full bg-primary-500 flex items-center justify-center text-white text-2xl font-bold">
          {{ auth.nickname?.charAt(0)?.toUpperCase() || 'U' }}
        </span>
        <div>
          <p class="text-lg font-semibold">{{ auth.nickname || auth.username }}</p>
          <p class="text-sm text-gray-500 dark:text-gray-400">{{ auth.username }}</p>
        </div>
      </div>
      <div class="space-y-3 text-sm">
        <div class="flex justify-between py-2 border-b border-gray-100 dark:border-gray-700">
          <span class="text-gray-500">用户名</span><span>{{ auth.username }}</span>
        </div>
        <div class="flex justify-between py-2 border-b border-gray-100 dark:border-gray-700">
          <span class="text-gray-500">昵称</span><span>{{ auth.nickname }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useAuthStore } from '@/stores/auth'
const auth = useAuthStore()
</script>
