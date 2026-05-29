import request from './request'
import type { ApiResult } from '@/types/api'
import type { LoginRequest, LoginResponse } from '@/types/user'

export function login(data: LoginRequest) { return request.post<ApiResult<LoginResponse>>('/auth/login', data) }
export function register(data: any) { return request.post<ApiResult<null>>('/auth/register', data) }
