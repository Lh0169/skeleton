const PREFIX = 'skeleton_'
export function getToken(): string | null { return localStorage.getItem(PREFIX + 'token') }
export function setToken(v: string): void { localStorage.setItem(PREFIX + 'token', v) }
export function removeToken(): void { localStorage.removeItem(PREFIX + 'token') }
export function getTheme(): 'dark' | 'light' {
  return (localStorage.getItem(PREFIX + 'theme') as 'dark' | 'light') || 'light'
}
export function setTheme(v: 'dark' | 'light'): void { localStorage.setItem(PREFIX + 'theme', v) }
