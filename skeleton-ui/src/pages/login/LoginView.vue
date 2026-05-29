<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-100 dark:bg-gray-900 px-4">
    <div class="w-full max-w-md">
      <div class="text-center mb-8">
        <h1 class="text-3xl font-bold text-primary-600 dark:text-primary-400">Skeleton Admin</h1>
        <p class="mt-2 text-gray-500 dark:text-gray-400">企业级全栈脚手架</p>
      </div>

      <div class="bg-white dark:bg-gray-800 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700 p-6">
        <form @submit.prevent="handleLogin">
          <div class="mb-4">
            <label class="block text-sm font-medium mb-1.5 text-gray-700 dark:text-gray-300">用户名</label>
            <input v-model="form.username" type="text" required
                   class="w-full px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-lg
                          bg-white dark:bg-gray-900 text-gray-900 dark:text-gray-100
                          focus:ring-2 focus:ring-primary-500 focus:border-transparent outline-none transition-colors"
                   placeholder="请输入用户名" />
          </div>

          <div class="mb-6">
            <label class="block text-sm font-medium mb-1.5 text-gray-700 dark:text-gray-300">密码</label>
            <input v-model="form.password" type="password" required
                   class="w-full px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-lg
                          bg-white dark:bg-gray-900 text-gray-900 dark:text-gray-100
                          focus:ring-2 focus:ring-primary-500 focus:border-transparent outline-none transition-colors"
                   placeholder="请输入密码" />
          </div>

          <p v-if="errorMsg" class="mb-4 text-sm text-red-500">{{ errorMsg }}</p>

          <button type="submit" :disabled="loading"
                  class="w-full py-2.5 bg-primary-600 hover:bg-primary-700 text-white rounded-lg
                         font-medium transition-colors disabled:opacity-50">
            {{ loading ? '登录中...' : '登 录' }}
          </button>
        </form>
      </div>

      <div class="flex justify-center mt-6">
        <button class="text-sm text-gray-400 hover:text-primary-500" @click="theme.toggle()">
          {{ theme.isDark ? '☀️ 亮色模式' : '🌙 暗黑模式' }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { useThemeStore } from '@/stores/theme'

const router = useRouter()
const route = useRoute()
const auth = useAuthStore()
const theme = useThemeStore()

const form = reactive({ username: 'admin', password: 'admin123' })
const loading = ref(false)
const errorMsg = ref('')

async function handleLogin() {
  loading.value = true; errorMsg.value = ''
  try {
    await auth.login(form)
    router.push((route.query.redirect as string) || '/')
  } catch (e: any) {
    errorMsg.value = e?.response?.data?.message || e?.message || '登录失败'
  } finally { loading.value = false }
}
</script>
