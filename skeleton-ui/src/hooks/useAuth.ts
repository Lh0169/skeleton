import { useAuthStore } from '@/stores/auth'
import type { LoginRequest } from '@/types/user'

export function useAuth() {
  const store = useAuthStore()
  return {
    token: store.token, username: store.username, nickname: store.nickname,
    avatar: store.avatar, permissions: store.permissions, roles: store.roles,
    isLoggedIn: store.isLoggedIn,
    login: (data: LoginRequest) => store.login(data),
    logout: () => store.logout(),
  }
}
