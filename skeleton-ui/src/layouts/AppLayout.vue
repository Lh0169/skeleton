<template>
  <div class="flex h-screen overflow-hidden">
    <Sidebar :collapsed="app.sidebarCollapsed" />
    <div class="flex flex-1 flex-col overflow-hidden">
      <Topbar />
      <TabsView />
      <main class="flex-1 overflow-auto p-4">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useAppStore } from '@/stores/app'
import Sidebar from './Sidebar.vue'
import Topbar from './Topbar.vue'
import TabsView from './TabsView.vue'

const app = useAppStore()
</script>
