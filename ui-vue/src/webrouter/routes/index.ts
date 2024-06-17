import type { AppRouteRecordRaw, AppRouteModule } from '/@/router/types';

// import { PAGE_NOT_FOUND_ROUTE, REDIRECT_ROUTE } from '/@/router/routes/basic';

//import { mainOutRoutes } from './mainOut';
//import { PageEnum } from '/@/enums/pageEnum';
import { PageEnumAdmin } from './pageEnumAdmin';
import { t } from '/@/hooks/web/useI18n';

import { LoginRoute, Oauth2LoginRoute, TokenLoginRoute } from './indexInherited';

export { LoginRoute, Oauth2LoginRoute, TokenLoginRoute };

export function getAdminConsoleDef(){
  return {
    root: '/admin',
  };
}

// website home
const WebSiteHomeRoute: AppRouteRecordRaw = {
  path: '/',
  name: 'WebSiteHomeRoute',
  component: () => import('/@/website/home/index.vue'),
  meta: {
    title: t('网站首页'),
    ignoreAuth: true,
  },
};

export const RootRoute = WebSiteHomeRoute;

// WebSiteLogin
export const WebSiteLoginRoute: AppRouteRecordRaw = {
  path: '/login',
  name: 'Login',
  //新版后台登录，如果想要使用旧版登录放开即可
  // component: () => import('/@/views/sys/login/Login.vue'),
  component: () => import('/@/website/system/loginmini/MiniLogin.vue'),
  meta: {
    title: t('routes.basic.login'),
    ignoreAuth: true,
  },
};

// webview
export const WebViewRoute: AppRouteRecordRaw = {
  path: '/webview',
  name: 'WebViewRoute',
  component: () => import('/@/widget/WebView/index.vue'),
  meta: {
    title: t('WebView'),
    ignoreAuth: true,
  },
};

// admin
export const AdminConsoleRoute: AppRouteRecordRaw = {
  path: getAdminConsoleDef().root,
  name: 'AdminConsoleRoute',
  //component: () => import('/@/widget/WebView/index.vue'),
  redirect: PageEnumAdmin.BASE_HOME,
  meta: {
    title: t('AdminConsole'),
    adminConsole: true,
  },
};


//Website routing without permission?
export const webSiteRoutes = [WebSiteLoginRoute, WebViewRoute, AdminConsoleRoute];
