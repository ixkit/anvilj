### 2024-1-2,sketch

### 2024-1-16,add website moudle, update route logic,support two route endpoints

    * 路由逻辑更新，首页指向网站模块
    * 前端页面分为展示网站、后台管理两个入口

### 2024-1-16,add register via email feature

### 2024-1-16, Route name can not be duplicated, should be unique.
```
export const WebSiteLoginRoute: AppRouteRecordRaw = {
  path: '/login',
  name: 'Login',
  component: () => import('/@/website/system/loginmini/MiniLogin.vue'),
  meta: {
    title: t('routes.basic.login'),
    ignoreAuth: true,
  },
};
```
the Route name should be unique or else occurs unexpected problem.

### 2024-1-17, Tow Modules: WebSite and AdminConsole, login entry & logout split
-  Tow Modules: WebSite and AdminConsole  
  User roles: website user and enterprise inner user 

    Web vistior ---> WebSite entry: http://localhost:3100/  
    Admisitor/Employee ---> AdminConsole: http://localhost:3100/admin/login

### bundle into main project
