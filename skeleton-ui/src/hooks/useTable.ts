import { ref, reactive } from 'vue'
import type { PageParams, PageResult } from '@/types/api'

export function useTable<T>(fetchFn: (params: PageParams) => Promise<{ data: { data: PageResult<T> } }>) {
  const loading = ref(false)
  const data = ref<T[]>([])
  const total = ref(0)
  const query = reactive<PageParams>({ page: 1, size: 10 })

  async function load() {
    loading.value = true
    try {
      const res = await fetchFn(query)
      const result = res.data.data
      data.value = result.rows; total.value = result.total
    } finally {
      loading.value = false
    }
  }

  async function onPageChange(p: number) { query.page = p; await load() }
  async function onSearch() { query.page = 1; await load() }

  return { loading, data, total, query, load, onPageChange, onSearch }
}
