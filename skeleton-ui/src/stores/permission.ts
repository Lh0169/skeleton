import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { AppRoute } from '@/types/route'

export const usePermissionStore = defineStore('permission', () => {
  const routes = ref<AppRoute[]>([])
  const buttonPermissions = ref<string[]>([])

  function setRoutes(r: AppRoute[]) { routes.value = r }
  function hasPermission(perm: string): boolean { return buttonPermissions.value.includes(perm) }

  return { routes, buttonPermissions, setRoutes, hasPermission }
})
