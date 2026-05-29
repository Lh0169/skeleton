import { getToken } from './storage'
export function isAuthenticated(): boolean { return !!getToken() }
export function getAuthToken(): string | null { return getToken() }
