// import { getAdminConsoleDef } from './index';

// const root = getAdminConsoleDef().name;
//export enum PageEnumDiscard {
export enum PageEnumAdmin_proxy {  
  // basic login path
  BASE_LOGIN = '/login',
  // basic home path
  BASE_HOME = '/dashboard/analysis',
  // error page path
  ERROR_PAGE = '/exception',
  // error log page path
  ERROR_LOG_PAGE = '/error-log/list',
  // auth2登录路由路径
  OAUTH2_LOGIN_PAGE_PATH = '/oauth2-app/login',
  //文件路由
  SYS_FILES_PATH = '/file/share',
  // 邮件中的跳转地址
  TOKEN_LOGIN = '/tokenLogin'
}

export enum PageEnumAdmin {
  // basic login path
  BASE_LOGIN = '/admin/login',
  // basic home path
  BASE_HOME = '/admin/dashboard/analysis',
  // error page path
  ERROR_PAGE =  '/admin/exception',
  // error log page path
  ERROR_LOG_PAGE =  '/admin/error-log/list',
  // auth2登录路由路径
  OAUTH2_LOGIN_PAGE_PATH =  '/admin/oauth2-app/login',
  //文件路由
  SYS_FILES_PATH = '/admin/file/share',
  // 邮件中的跳转地址
  TOKEN_LOGIN = '/admin/tokenLogin',

  //匿名用户 试图打开无效路径，则返回首页
  ANONYM_NO_MATCH_ROUTE = '/', 
}
