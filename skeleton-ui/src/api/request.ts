import axios, { type AxiosInstance, type AxiosResponse, type InternalAxiosRequestConfig } from 'axios'
import { getToken, removeToken } from '@/utils/storage'
import type { ApiResult } from '@/types/api'

const instance: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: 15000,
})

instance.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    const token = getToken()
    if (token) config.headers.Authorization = `Bearer ${token}`
    return config
  },
  (error) => Promise.reject(error),
)

instance.interceptors.response.use(
  (response: AxiosResponse<ApiResult>) => {
    const { code, message } = response.data
    if (code === 401) { removeToken(); window.location.href = '/login'; return Promise.reject(new Error(message)) }
    return response
  },
  (error) => {
    if (error.response?.status === 401) { removeToken(); window.location.href = '/login' }
    return Promise.reject(error)
  },
)

export default instance
