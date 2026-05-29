# Skeleton 全栈脚手架

Java 17 + Spring Boot 3 + Vue 3 + TypeScript + Tailwind CSS 企业级全栈单体架构脚手架，开箱即用，面向生产。

## 目录

- [技术栈](#技术栈)
- [快速开始](#快速开始)
- [项目结构](#项目结构)
- [架构设计](#架构设计)
- [模块说明](#模块说明)
- [开发指南](#开发指南)
- [部署](#部署)

---

## 技术栈

### 后端

| 技术 | 版本 | 说明 |
|:---|:---|:---|
| Java | 17 | LTS |
| Spring Boot | 3.2.5 | 核心框架 |
| Spring Security | 6 | 认证授权 |
| Spring Cloud | 2023.0.1 | BOM 引入，微服务扩展预留 |
| JWT | 0.12.5 | 无状态认证 |
| MyBatis Plus | 3.5.6 | ORM + 分页 |
| MySQL | 8.3.0 | 关系数据库 |
| Redis | — | 缓存 / Token 管理 |
| Lombok | — | 代码简化 |
| MapStruct | 1.5.5 | 对象映射 |
| Hutool | 5.8.27 | 工具集 |
| Knife4j | 4.3.0 | API 文档 |

### 前端

| 技术 | 说明 |
|:---|:---|
| Vue 3 | Composition API |
| TypeScript | 类型安全 |
| Vite 5 | 构建工具 |
| Tailwind CSS 3 | 原子化样式 |
| Pinia 2 | 状态管理 |
| Vue Router 4 | 路由 + 权限守卫 |
| Axios | HTTP 封装 + 拦截器 |
| Day.js | 日期处理 |

---

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 8.0+
- Redis 7+
- Node.js 18+
- pnpm 8+

### 1. 初始化数据库

```bash
# 执行建库建表脚本
mysql -u root -p < apps/app-admin/src/main/resources/db/schema.sql
```

### 2. 启动后端

```bash
# 确保 MySQL 和 Redis 已启动
# 修改 apps/app-admin/src/main/resources/application-dev.yml 中的连接信息

mvn clean install -DskipTests
cd apps/app-admin
mvn spring-boot:run
```

启动后访问 http://localhost:8080/doc.html 查看 API 文档。

默认管理员账户由 `DataInitializer` 自动创建：**admin / admin123**

### 3. 启动前端

```bash
cd skeleton-ui
pnpm install
pnpm dev
```

访问 http://localhost:3000

### 一键 Docker 部署

```bash
docker-compose -f deploy/docker/docker-compose.yml up -d
```

---

## 项目结构

```text
skeleton/
├── pom.xml                                        # 父工程（版本/依赖管理）
│
├── apps/                                          # 应用入口层
│   └── app-admin/                                 # 管理后台入口
│       ├── src/main/java/com/skeleton/admin/
│       │   ├── Application.java                   # 启动类
│       │   ├── config/OpenApiConfig.java          # Knife4j 配置
│       │   └── init/DataInitializer.java          # 初始化管理员
│       ├── src/main/resources/
│       │   ├── application.yml                    # 公共配置
│       │   ├── application-dev.yml                # 开发环境
│       │   ├── application-prod.yml               # 生产环境
│       │   ├── banner.txt
│       │   └── db/
│       │       ├── schema.sql                     # 建表
│       │       └── data.sql                       # 参考数据
│       └── pom.xml
│
├── commons/                                       # 基础公共能力层
│   ├── common-core/                               # 核心：常量/枚举/响应/异常/工具
│   ├── common-web/                                # Web层：全局异常/AOP/CORS
│   ├── common-security/                           # 安全：Spring Security/JWT/Token
│   ├── common-db/                                 # 数据：MyBatis Plus/BaseService
│   ├── common-redis/                              # 缓存：Redis配置/服务
│   └── common-log/                                # 日志：操作日志事件/监听
│
├── modules/                                       # 业务模块层
│   ├── module-auth/                               # 认证：登录/注册/Token刷新
│   ├── module-system/                             # 系统：用户/角色/菜单/部门/权限
│   ├── module-file/                               # 文件：本地/MinIO/OSS 多策略
│   ├── module-ai/                                 # AI 模块（预留）
│   ├── module-task/                               # 任务调度（预留）
│   ├── module-message/                            # 消息中心（预留）
│   └── module-workflow/                           # 工作流（预留）
│
├── deploy/                                        # 部署资源
│   ├── docker/                                    # Dockerfile + docker-compose
│   ├── k8s/                                       # K8s deployment + service
│   └── nginx/                                     # Nginx 反向代理配置
│
├── docs/                                          # 文档
│   ├── architecture/                              # 架构文档
│   ├── api/                                       # 接口文档
│   ├── database/                                  # 数据库文档
│   └── deployment/                                # 部署文档
│
└── skeleton-ui/                                   # Vue3 + TS + Tailwind 前端
    ├── package.json / vite.config.ts / tsconfig.json
    ├── tailwind.config.ts / postcss.config.js
    └── src/
        ├── api/              # Axios 封装 + 业务 API
        │   ├── request.ts    # 实例 + 拦截器（Token/401）
        │   ├── auth.ts       # 认证 API
        │   ├── user.ts       # 用户 API
        │   └── file.ts       # 文件 API
        │
        ├── router/
        │   ├── index.ts      # 路由实例
        │   ├── routes.ts     # 路由表（动态路由）
        │   └── permission.ts # 权限守卫（白名单/Token）
        │
        ├── stores/           # Pinia 状态管理
        │   ├── auth.ts       # 认证状态
        │   ├── theme.ts      # 暗黑模式
        │   ├── app.ts        # 侧边栏/Tab页
        │   └── permission.ts # 权限/按钮
        │
        ├── layouts/          # 布局组件
        │   ├── AppLayout.vue # 主布局（侧边栏+顶栏+内容）
        │   ├── Sidebar.vue   # 侧边栏菜单
        │   ├── Topbar.vue    # 顶栏（面包屑/用户/暗黑切换）
        │   └── TabsView.vue  # 多Tab标签页
        │
        ├── pages/            # 页面
        │   ├── login/        # 登录
        │   ├── dashboard/    # 仪表盘
        │   ├── system/       # 系统管理（用户/角色）
        │   ├── profile/      # 个人中心
        │   └── exception/    # 403/404
        │
        ├── hooks/            # 组合式函数
        │   ├── useAuth.ts / useTheme.ts
        │   ├── useTable.ts   # 分页查询封装
        │   └── usePermission.ts
        │
        ├── components/       # 组件
        │   ├── ui/           # 基础 UI 组件（预留）
        │   ├── common/       # 通用组件（预留）
        │   └── business/     # 业务组件（预留）
        │
        ├── directives/       # 指令
        │   └── permission.ts # v-permission 按钮权限
        │
        ├── plugins/          # 插件
        │   └── dayjs.ts
        │
        ├── utils/            # 工具函数
        │   ├── auth.ts / storage.ts
        │   ├── download.ts / tree.ts
        │
        ├── types/            # TS 类型定义
        │   ├── api.ts / auth.ts
        │   ├── user.ts / route.ts
        │
        ├── styles/           # 样式
        │   ├── tailwind.css  # Tailwind + 组件样式
        │   ├── variables.css # CSS 变量
        │   └── dark.css      # 暗黑模式
        │
        ├── constants/app.ts  # 应用常量
        ├── App.vue / main.ts # 入口
        └── env.d.ts
```

---

## 架构设计

### 1. 领域模块化

```text
module-auth    →  认证域
module-system  →  系统域
module-file    →  文件域
module-ai      →  AI 域（预留）
```

按业务领域拆分模块，每个模块内聚 controller/dto/domain/mapper/service。未来可独立拆分为微服务。

### 2. 基础能力拆分

```text
common-core     →  零框架依赖的核心模型
common-web      →  Web 层通用能力
common-security →  安全认证独立模块
common-db       →  数据库操作封装
common-redis    →  缓存操作封装
common-log      →  日志异步采集
```

每个 common 模块职责单一，按需引入。

### 3. 前后端解耦

- 前端独立部署（`skeleton-ui`），开发时 Vite proxy 到后端
- 后端纯 API，返回统一 `Result<T>` 信封
- JWT 无状态认证，前后端仅通过 Token 通信

### 4. 微服务友好

未来模块可直接拆为独立服务：

```text
module-ai   →  skeleton-ai-service
module-file →  skeleton-file-service
```

父 POM 已引入 Spring Cloud BOM，后续只需添加注册中心即可。

### 5. 关键设计决策

| 决策 | 选择 | 理由 |
|:---|:---|:---|
| 认证方式 | JWT + Redis | 无状态 + 可控失效 |
| ORM | MyBatis Plus | Lambda 查询 + 分页 + 逻辑删除 |
| 权限模型 | RBAC + 动态路由 | `@PreAuthorize` + 前端路由守卫 |
| 响应格式 | `Result<T>` 统一信封 | code/message/data 三段式 |
| 前端状态 | Pinia | Composition API 友好，Tree-shaking |
| 前端样式 | Tailwind CSS | 设计系统一致，暗黑模式无缝 |
| API 文档 | Knife4j + OpenAPI3 | 零注解侵入，即开即用 |

---

## 模块说明

### common-core

基础核心，**不依赖任何业务模块**，所有其他模块的底层依赖。

| 文件 | 说明 |
|:---|:---|
| `constant/` | CacheConstants, SecurityConstants, HttpConstants |
| `enums/` | ResultCode, UserStatus, DeletedFlag |
| `result/` | Result（统一响应）, PageResult（分页）, PageQuery |
| `exception/` | BusinessException, UnauthorizedException, ForbiddenException |
| `utils/` | JwtUtils, ServletUtils |
| `model/` | LoginUser, BaseEntity |
| `validation/` | CreateGroup, UpdateGroup |

### common-web

Web 层通用能力，依赖 common-core。

- `GlobalExceptionHandler` — 统一异常捕获，Business/Unauthorized/Forbidden/Unknown
- `ResponseAdvice` — 自动包装返回值为 `Result`
- `LogAspect` — Controller 层性能日志
- `CorsConfig` / `WebMvcConfig` — 跨域 + 静态资源

### common-security

安全模块，依赖 common-core + common-redis。

- `SecurityConfig` — 放行 `/api/auth/**` 和 Knife4j，其余需认证
- `JwtAuthenticationFilter` — 从 Header 提取 Token，恢复上下文
- `TokenManager` — Token 签发/刷新/Redis 存储
- `AuthenticationEntryPointImpl` / `AccessDeniedHandlerImpl` — 401/403 JSON 响应

### common-db

数据库模块，依赖 common-core + MyBatis Plus。

- `MybatisPlusConfig` — 分页插件 + MapperScan
- `MetaObjectHandlerConfig` — `create_time` / `update_time` 自动填充
- `BaseService` / `BaseServiceImpl` — 通用 Service 抽象

### common-redis

缓存模块，依赖 common-core + Spring Data Redis。

- `RedisConfig` — JSON 序列化
- `RedisService` — get/set/delete/hasKey 封装
- `RedisKeys` — Key 常量

### common-log

日志模块，使用 Spring 事件异步采集。

- `OperationLogEvent` — 操作日志事件
- `OperationLogListener` — 异步消费（`@Async`）

### module-auth

认证业务模块。

- `POST /api/auth/login` — 登录，返回 Token + 用户信息
- `POST /api/auth/register` — 注册

### module-system

系统管理模块，按子域分包（user/role/menu/dept/permission）。

- `GET /api/system/user/page` — 分页查询用户
- `GET /api/system/user/{id}` — 用户详情
- `POST /api/system/user` — 新增用户
- `PUT /api/system/user/{id}` — 修改用户
- `DELETE /api/system/user/{id}` — 删除用户
- 角色管理同上模式

> 权限注解使用 `@PreAuthorize("hasAuthority('sys:user:list')")`

### module-file

文件管理模块，策略模式支持多种存储。

- `POST /api/file/upload` — 上传
- `DELETE /api/file?path=` — 删除
- `FileService` 接口 → `LocalStorageService`（默认）/ MinIO / OSS

---

## 开发指南

### 添加新业务模块

1. 复制 `modules/module-auth` 结构
2. 修改 `pom.xml` 中的 artifactId 和依赖
3. 在父 `pom.xml` 的 `<modules>` 中注册
4. 在 `apps/app-admin/pom.xml` 中添加依赖
5. 在 `skeleton-ui/src/router/routes.ts` 中添加前端路由

### 后端开发规范

```
module-{name}/
└── src/main/java/com/skeleton/module/{name}/
    ├── controller/     → @RestController, 只做参数校验和路由
    ├── service/        → 业务逻辑
    ├── dto/            → 入参对象
    ├── vo/             → 出参对象
    ├── domain/entity/  → @TableName 实体
    └── mapper/         → extends BaseMapper<T>
```

- Controller 只转接请求，业务逻辑在 Service
- 使用 `Result.success(data)` 统一返回
- 使用 `BusinessException(ResultCode.xxx)` 抛出业务异常
- 权限用 `@PreAuthorize` 注解控制

### 前端开发规范

- 页面放在 `pages/`，按功能域分包
- 复用逻辑放在 `hooks/`，如 `useTable` 已封装分页查询
- 状态在 `stores/` 用 Pinia 管理
- API 请求在 `api/` 按模块分包
- 全局类型在 `types/` 定义
- 路由在 `router/routes.ts` 注册

### 环境变量

后端环境变量（`application-{profile}.yml`）：

| 变量 | 默认值 | 说明 |
|:---|:---|:---|
| `DB_URL` | — | 数据库连接（prod） |
| `DB_USERNAME` | — | 数据库用户名 |
| `DB_PASSWORD` | root | 数据库密码 |
| `REDIS_HOST` | localhost | Redis 地址 |
| `REDIS_PORT` | 6379 | Redis 端口 |
| `REDIS_PASSWORD` | — | Redis 密码 |
| `JWT_SECRET` | — | JWT 签名密钥 |

前端环境变量（`.env`）：

| 变量 | 说明 |
|:---|:---|
| `VITE_APP_TITLE` | 应用标题 |
| `VITE_API_BASE_URL` | API 前缀 |

---

## 部署

### Docker

```bash
# 构建并启动（MySQL + Redis + App）
cd deploy/docker
docker-compose up -d

# 查看日志
docker-compose logs -f app
```

### Kubernetes

```bash
kubectl apply -f deploy/k8s/deployment.yaml
kubectl apply -f deploy/k8s/service.yaml
```

> 生产环境请先创建 `skeleton-secret` 资源，配置数据库密码等敏感信息。

### 前端独立部署

```bash
cd skeleton-ui
pnpm build              # 产出 dist/
# 将 dist/ 部署到 Nginx 或 CDN
```

Nginx 配置参考 `deploy/nginx/nginx.conf`：

```nginx
location /api/ { proxy_pass http://backend:8080; }
location / { try_files $uri /index.html; }
```

---

## License

MIT
