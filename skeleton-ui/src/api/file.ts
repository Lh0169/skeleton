import request from './request'
import type { ApiResult } from '@/types/api'

export function uploadFile(file: File) {
  const fd = new FormData(); fd.append('file', file)
  return request.post<ApiResult<string>>('/file/upload', fd, {
    headers: { 'Content-Type': 'multipart/form-data' },
  })
}
export function deleteFile(path: string) { return request.delete<ApiResult<null>>('/file', { params: { path } }) }
