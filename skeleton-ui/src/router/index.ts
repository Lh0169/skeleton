import { createRouter, createWebHistory } from 'vue-router'
import { constantRoutes } from './routes'
import { setupPermissionGuard } from './permission'

const router = createRouter({
  history: createWebHistory(),
  routes: constantRoutes,
})

setupPermissionGuard(router)

export default router
