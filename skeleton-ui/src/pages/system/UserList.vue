<template>
  <div>
    <div class="flex items-center justify-between mb-4">
      <h2 class="text-xl font-bold">用户管理</h2>
      <button class="px-4 py-2 bg-primary-600 text-white rounded-lg text-sm hover:bg-primary-700">新增用户</button>
    </div>

    <div class="bg-white dark:bg-gray-800 rounded-xl border border-gray-200 dark:border-gray-700 overflow-hidden">
      <table class="w-full text-sm">
        <thead class="bg-gray-50 dark:bg-gray-700">
          <tr>
            <th class="px-4 py-3 text-left">ID</th>
            <th class="px-4 py-3 text-left">用户名</th>
            <th class="px-4 py-3 text-left">昵称</th>
            <th class="px-4 py-3 text-left">邮箱</th>
            <th class="px-4 py-3 text-left">状态</th>
            <th class="px-4 py-3 text-left">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in data" :key="user.id" class="border-t border-gray-100 dark:border-gray-700">
            <td class="px-4 py-3">{{ user.id }}</td>
            <td class="px-4 py-3">{{ user.username }}</td>
            <td class="px-4 py-3">{{ user.nickname }}</td>
            <td class="px-4 py-3">{{ user.email || '-' }}</td>
            <td class="px-4 py-3">
              <span :class="user.status === 1 ? 'text-green-600' : 'text-red-600'">
                {{ user.status === 1 ? '正常' : '禁用' }}
              </span>
            </td>
            <td class="px-4 py-3">
              <button class="text-primary-600 hover:text-primary-700 mr-2">编辑</button>
              <button class="text-red-600 hover:text-red-700">删除</button>
            </td>
          </tr>
        </tbody>
      </table>

      <div class="flex items-center justify-between px-4 py-3 border-t border-gray-100 dark:border-gray-700">
        <span class="text-sm text-gray-500">共 {{ total }} 条</span>
        <div class="flex gap-1">
          <button v-for="p in Math.ceil(total / query.size)" :key="p"
                  :class="['px-3 py-1 rounded text-sm', p === query.page ? 'bg-primary-600 text-white' : 'hover:bg-gray-100 dark:hover:bg-gray-700']"
                  @click="onPageChange(p)">{{ p }}</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
import { useTable } from '@/hooks/useTable'
import { getUserPage } from '@/api/user'
import type { UserInfo } from '@/types/user'

const { data, total, query, load, onPageChange } = useTable<UserInfo>(getUserPage)
onMounted(() => load())
</script>
