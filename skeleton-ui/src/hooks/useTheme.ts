import { useThemeStore } from '@/stores/theme'
export function useTheme() {
  const store = useThemeStore()
  return { isDark: store.isDark, toggle: () => store.toggle() }
}
