import { getAdminConsoleDef } from '/@/webrouter/routes/index';

 
/*
  format route path, add new root prefix, eg /dashboard => /admin/dashboard
*/
export function format2AdminRoute(route) {
  if (!route.meta.frontend && !route.formatted){
    const root = getAdminConsoleDef().root;
    route.path = root + route.path;
    route.formatted = 1;
    console.log('🧐🛡️🛡️🛡️ format2AdminRoute, router?', route); 
  }
}
