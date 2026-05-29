import type { RouteRecordRaw } from 'vue-router'
export interface AppRouteMeta {
  title?: string; icon?: string; hidden?: boolean;
  permissions?: string[]; keepAlive?: boolean;
}
export type AppRoute = RouteRecordRaw & { meta?: AppRouteMeta; children?: AppRoute[] }
