import { defineStore } from 'pinia'
import { ref, watchEffect } from 'vue'
import { getTheme, setTheme } from '@/utils/storage'

export const useThemeStore = defineStore('theme', () => {
  const isDark = ref(getTheme() === 'dark')
  function toggle() { isDark.value = !isDark.value }
  watchEffect(() => {
    document.documentElement.classList.toggle('dark', isDark.value)
    setTheme(isDark.value ? 'dark' : 'light')
  })
  return { isDark, toggle }
})
