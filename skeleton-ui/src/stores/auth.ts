import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { getToken, setToken, removeToken } from '@/utils/storage'
import { login as loginApi } from '@/api/auth'
import type { LoginRequest } from '@/types/user'

export const useAuthStore = defineStore('auth', () => {
  const token = ref<string | null>(getToken())
  const username = ref('')
  const nickname = ref('')
  const avatar = ref('')
  const permissions = ref<string[]>([])
  const roles = ref<string[]>([])

  const isLoggedIn = computed(() => !!token.value)

  async function login(data: LoginRequest) {
    const res = await loginApi(data)
    const d = res.data.data
    token.value = d.token; username.value = d.username
    nickname.value = d.nickname; avatar.value = d.avatar
    setToken(d.token)
  }

  function logout() {
    token.value = null; username.value = ''; nickname.value = ''
    avatar.value = ''; permissions.value = []; roles.value = []
    removeToken()
  }

  return { token, username, nickname, avatar, permissions, roles, isLoggedIn, login, logout }
})
