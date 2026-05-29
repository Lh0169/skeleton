import type { AppRoute } from '@/types/route'

export const constantRoutes: AppRoute[] = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/pages/login/LoginView.vue'),
    meta: { title: '登录', hidden: true },
  },
  {
    path: '/',
    component: () => import('@/layouts/AppLayout.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/pages/dashboard/DashboardView.vue'),
        meta: { title: '仪表盘', icon: '📊' },
      },
      {
        path: 'system',
        name: 'System',
        meta: { title: '系统管理', icon: '⚙️' },
        children: [
          {
            path: 'user',
            name: 'UserList',
            component: () => import('@/pages/system/UserList.vue'),
            meta: { title: '用户管理', permissions: ['sys:user:list'] },
          },
          {
            path: 'role',
            name: 'RoleList',
            component: () => import('@/pages/system/RoleList.vue'),
            meta: { title: '角色管理', permissions: ['sys:role:list'] },
          },
        ],
      },
      {
        path: 'profile',
        name: 'Profile',
        component: () => import('@/pages/profile/ProfileView.vue'),
        meta: { title: '个人中心', hidden: true },
      },
    ],
  },
  {
    path: '/403',
    name: 'Forbidden',
    component: () => import('@/pages/exception/403.vue'),
    meta: { title: '403', hidden: true },
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: () => import('@/pages/exception/404.vue'),
    meta: { title: '404', hidden: true },
  },
]
