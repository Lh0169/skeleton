export interface ApiResult<T = any> { code: number; message: string; data: T }
export interface PageResult<T> { total: number; page: number; size: number; rows: T[] }
export interface PageParams { page: number; size: number; [key: string]: any }
