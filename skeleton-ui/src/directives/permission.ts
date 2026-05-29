import type { Directive } from 'vue'
import { usePermissionStore } from '@/stores/permission'

export const vPermission: Directive = {
  mounted(el, binding) {
    const perm = binding.value as string
    const store = usePermissionStore()
    if (!store.hasPermission(perm)) el.remove()
  },
}
