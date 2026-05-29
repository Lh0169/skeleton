<template>
  <div>
    <h2 class="text-2xl font-bold mb-6">仪表盘</h2>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
      <div v-for="card in stats" :key="card.label"
           class="bg-white dark:bg-gray-800 rounded-xl p-5 border border-gray-200 dark:border-gray-700">
        <p class="text-sm text-gray-500 dark:text-gray-400">{{ card.label }}</p>
        <p class="text-2xl font-bold mt-1">{{ card.value }}</p>
      </div>
    </div>

    <div class="bg-white dark:bg-gray-800 rounded-xl p-6 border border-gray-200 dark:border-gray-700">
      <h3 class="text-lg font-semibold mb-4">快速开始</h3>
      <ul class="space-y-2 text-sm text-gray-600 dark:text-gray-400">
        <li>1. 配置 <code class="px-1.5 py-0.5 bg-gray-100 dark:bg-gray-700 rounded text-xs">application-dev.yml</code> 数据源</li>
        <li>2. 执行 <code class="px-1.5 py-0.5 bg-gray-100 dark:bg-gray-700 rounded text-xs">db/schema.sql</code> 建表</li>
        <li>3. 启动后端 <code class="px-1.5 py-0.5 bg-gray-100 dark:bg-gray-700 rounded text-xs">mvn spring-boot:run</code></li>
        <li>4. 启动前端 <code class="px-1.5 py-0.5 bg-gray-100 dark:bg-gray-700 rounded text-xs">pnpm dev</code></li>
        <li>5. 登录 <code class="px-1.5 py-0.5 bg-gray-100 dark:bg-gray-700 rounded text-xs">admin / admin123</code></li>
      </ul>
    </div>
  </div>
</template>

<script setup lang="ts">
const stats = [
  { label: '用户数', value: '--' },
  { label: '角色数', value: '--' },
  { label: '今日访问', value: '--' },
  { label: '系统版本', value: '1.0.0' },
]
</script>
