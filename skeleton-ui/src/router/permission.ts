import type { Router } from 'vue-router'
import { getToken } from '@/utils/storage'

const WHITE_LIST = ['/login', '/403', '/404']

export function setupPermissionGuard(router: Router) {
  router.beforeEach((to, _from, next) => {
    document.title = `${to.meta.title || ''} - ${import.meta.env.VITE_APP_TITLE}`
    const token = getToken()

    if (token) {
      if (to.path === '/login') { next('/'); return }
      next()
    } else {
      if (WHITE_LIST.includes(to.path)) { next(); return }
      next(`/login?redirect=${to.path}`)
    }
  })
}
