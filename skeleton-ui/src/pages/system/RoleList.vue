<template>
  <div>
    <div class="flex items-center justify-between mb-4">
      <h2 class="text-xl font-bold">角色管理</h2>
      <button class="px-4 py-2 bg-primary-600 text-white rounded-lg text-sm hover:bg-primary-700">新增角色</button>
    </div>

    <div class="bg-white dark:bg-gray-800 rounded-xl border border-gray-200 dark:border-gray-700 overflow-hidden">
      <table class="w-full text-sm">
        <thead class="bg-gray-50 dark:bg-gray-700">
          <tr>
            <th class="px-4 py-3 text-left">ID</th>
            <th class="px-4 py-3 text-left">角色名称</th>
            <th class="px-4 py-3 text-left">角色编码</th>
            <th class="px-4 py-3 text-left">描述</th>
            <th class="px-4 py-3 text-left">状态</th>
            <th class="px-4 py-3 text-left">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr class="border-t border-gray-100 dark:border-gray-700">
            <td colspan="6" class="px-4 py-8 text-center text-gray-400">暂无数据</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
