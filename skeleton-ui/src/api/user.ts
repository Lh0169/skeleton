import request from './request'
import type { ApiResult, PageResult, PageParams } from '@/types/api'
import type { UserInfo } from '@/types/user'

export function getUserPage(params: PageParams) {
  return request.get<ApiResult<PageResult<UserInfo>>>('/system/user/page', { params })
}
export function getUserById(id: number) { return request.get<ApiResult<UserInfo>>(`/system/user/${id}`) }
export function addUser(data: Partial<UserInfo>) { return request.post<ApiResult<null>>('/system/user', data) }
export function updateUser(id: number, data: Partial<UserInfo>) {
  return request.put<ApiResult<null>>(`/system/user/${id}`, data)
}
export function deleteUser(id: number) { return request.delete<ApiResult<null>>(`/system/user/${id}`) }
