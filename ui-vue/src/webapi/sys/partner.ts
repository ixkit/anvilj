import { defHttp } from '/@/utils/http/axios';
import { LoginParams, LoginResultModel, GetUserInfoModel } from '/@/api/sys/model/userModel';

import { ErrorMessageMode } from '/#/axios';
import { useMessage } from '/@/hooks/web/useMessage';
import { useUserStoreWithOut } from '/@/store/modules/user';
import { setAuthCache } from '/@/utils/auth';
import { TOKEN_KEY } from '/@/enums/cacheEnum';
import { router } from '/@/router';
import { PageEnum } from '/@/enums/pageEnum';

const { createErrorModal } = useMessage();
enum Api {
  register = '/api/v1/partner/account/register',
  policy = '/api/v1/partner/account/policy',
}
 

/**
 * @description: 注册接口
 */
export function register(params) {
  return defHttp.post({ url: Api.register, params }, { isReturnNativeResponse: true });
}

/**
 * @description: 隐私协议接口
 */
 export function policy(params) {
  return defHttp.post({ url: Api.policy, params }, { isReturnNativeResponse: true });
}