import type { Router } from 'vue-router';
import { configureDynamicParamsMenu } from '../helper/menuHelper';
import { Menu } from '../types';
import { PermissionModeEnum } from '/@/enums/appEnum';
import { useAppStoreWithOut } from '/@/store/modules/app';

import { usePermissionStoreWithOut } from '/@/store/modules/permission';



export function onParamMenuGuardNext(router: Router) {
  //@@step
  console.log('🧐 onParamMenuGuardNext, router?', router);
 
}

/*
{
    "fullPath": "/admin/login?redirect=/xxx",
    "hash": "",
    "query": {
        "redirect": "/xxx"
    },
    "name": "Login",
    "path": "/admin/login",
    "params": {},
    "matched": [
        {
            "path": "/admin/login",
            "name": "Login",
            "meta": {
                "title": "routes.basic.login"
            },
            "props": {
                "default": false
            },
            "children": [],
            "instances": {
                "default": {}
            },
            "leaveGuards": {},
            "updateGuards": {},
            "enterCallbacks": {},
            "components": {
                "default": {
                    "name": "login-mini",
                    "props": {
                        "sessionTimeout": {}
                    },
                    "__hmrId": "da43466d",
                    "__scopeId": "data-v-da43466d",
                    "__file": "/Users/icoco/WorkSpace/2023/prj/ixkit/anvilj-space/anvilj-frontend/src/views/system/loginmini/MiniLogin.vue"
                }
            }
        }
    ],
    "meta": {
        "title": "routes.basic.login",
        "loaded": false
    },
    "redirectedFrom": {
        "fullPath": "/xxx",
        "path": "/xxx",
        "query": {},
        "hash": "",
        "name": "PageNotFound",
        "params": {
            "path": [
                "xxx"
            ]
        },
        "matched": [
            {
                "path": "/:path(.*)*",
                "name": "PageNotFound",
                "meta": {
                    "title": "ErrorPage",
                    "hideBreadcrumb": true,
                    "hideMenu": true
                },
                "props": {
                    "default": false
                },
                "children": [
                    {
                        "path": "/:path(.*)*",
                        "name": "PageNotFound",
                        "meta": {
                            "title": "ErrorPage",
                            "hideBreadcrumb": true,
                            "hideMenu": true
                        }
                    }
                ],
                "instances": {},
                "leaveGuards": {},
                "updateGuards": {},
                "enterCallbacks": {},
                "components": {}
            },
            {
                "path": "/:path(.*)*",
                "name": "PageNotFound",
                "meta": {
                    "title": "ErrorPage",
                    "hideBreadcrumb": true,
                    "hideMenu": true
                },
                "props": {
                    "default": false
                },
                "children": [],
                "instances": {},
                "leaveGuards": {},
                "updateGuards": {},
                "enterCallbacks": {},
                "components": {}
            }
        ],
        "meta": {
            "title": "ErrorPage",
            "hideBreadcrumb": true,
            "hideMenu": true,
            "loaded": false
        },
        "href": "/xxx"
    },
    "href": "/admin/login?redirect=/xxx"
}
*/
function forceToHome(router){
   const redirectedFrom = router.redirectedFrom;
   if (!redirectedFrom){
    return router;
   }
   if (redirectedFrom.name === "PageNotFound"){

   }
    
}