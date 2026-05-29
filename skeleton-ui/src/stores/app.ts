import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAppStore = defineStore('app', () => {
  const sidebarCollapsed = ref(false)
  const activeTab = ref('')
  const tabs = ref<{ path: string; title: string }[]>([])

  function toggleSidebar() { sidebarCollapsed.value = !sidebarCollapsed.value }
  function addTab(tab: { path: string; title: string }) {
    if (!tabs.value.find(t => t.path === tab.path)) tabs.value.push(tab)
    activeTab.value = tab.path
  }
  function removeTab(path: string) {
    tabs.value = tabs.value.filter(t => t.path !== path)
  }

  return { sidebarCollapsed, activeTab, tabs, toggleSidebar, addTab, removeTab }
})
