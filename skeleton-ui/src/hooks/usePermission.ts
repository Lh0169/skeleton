import { usePermissionStore } from '@/stores/permission'
export function usePermission() {
  const store = usePermissionStore()
  return { hasPermission: (perm: string) => store.hasPermission(perm) }
}
