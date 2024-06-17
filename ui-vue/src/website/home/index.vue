<template> 
  <a-layout :class="prefixCls">
    <a-layout-header v-show="true" class="header" >
      <!-- <AppLocalePicker class="absolute top-4 right-4 enter-x xl:text-gray-600" :showText="false"/>
      <AppDarkModeToggle class="absolute top-3 right-7 enter-x" /> -->
      <a-tooltip placement="left" overlayClassName="bgc_tooltip"> 
       <template #title>
        <span v-html="guideTip"> </span>
       </template>
        <a-icon type="menu-unfold" style="color:white;" class="absolute top-3 right-7 enter-x" @click="onTapMenu" />
      </a-tooltip>
      
    </a-layout-header>
    <a-layout-content > 
     <landing :sayHi="sayHi"></landing>
    </a-layout-content>
    <a-layout-footer v-show="false" class="footer" >
    </a-layout-footer> 
  </a-layout> 
</template>
<script lang="ts" setup>
  import { ref, unref, computed } from 'vue';
  import { AppLogo } from '/@/components/Application';
  import { AppLocalePicker, AppDarkModeToggle } from '/@/components/Application';
  import { SettingOutlined, EditOutlined, DeleteOutlined, PlayCircleOutlined,EllipsisOutlined } from '@ant-design/icons-vue';
  import { router } from "/@/router";
  import { useGlobSetting } from '/@/hooks/setting';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useDesign } from '/@/hooks/web/useDesign';
  import { useLocaleStore } from '/@/store/modules/locale';
  import { useLoginState, LoginStateEnum } from '../env/useLogin';
  import { useUserStoreWithOut } from '/@/store/modules/user';
  import { PageEnum } from '/@/enums/pageEnum';

  import  landing  from './landing/index.vue';
  
  defineProps({
    sessionTimeout: {
      type: Boolean,
    },
  });

  const globSetting = useGlobSetting();
  const { prefixCls } = useDesign('website');
  const { t } = useI18n();
  const localeStore = useLocaleStore();
  const showLocale = localeStore.getShowPicker;
  const title = computed(() => globSetting?.title ?? '');
  const { handleBackLogin } = useLoginState();
  //handleBackLogin(); 
  const guideTip = ref('<span>Login🔑</span>');

  //@step user
  const userStore = useUserStoreWithOut();
  const token = userStore.getToken;
  const userName = ref(null);
  const sayHi = ref(null);
  if (token){
    userName.value = userStore.getUserInfo.username;
    sayHi.value = `Hello, ${userName.value} !`;
  }


  const onTapMenu = function(){
    let nextPath = '/login';
    if (token){
      const userInfo = unref(userStore.getUserInfo);
      console.log('getUserInfo',userInfo );
      nextPath = userStore.getUserInfo.homePath || PageEnum.BASE_HOME
    }
    router.push({ path: nextPath, query: { plan: 'private' }})
  }

</script>
<style lang="less">
  @prefix-cls: ~'@{namespace}-website';
  @website-prefix-cls: ~'@{namespace}-app-logo';
  @countdown-prefix-cls: ~'@{namespace}-countdown-input';
  @dark-bg: #293146;
  @light-bg: #fff;
  @themeColor: #43A047;
  @themeFontColor: white;
  html[data-theme='dark'] {
    .@{prefix-cls} {
      background-color: @dark-bg;


      &::before {
       // background-image: url(/@/assets/svg/login-bg-dark.svg);
      }

      .ant-input,
      .ant-input-password {
        background-color: #232a3b;
      }

      .ant-btn:not(.ant-btn-link):not(.ant-btn-primary) {
        border: 1px solid #4a5569;
      }

      &-form {
        background: transparent !important;
      }

      .app-iconify {
        color: #fff;
      }
    }

    input.fix-auto-fill,
    .fix-auto-fill input {
      -webkit-text-fill-color: #c9d1d9 !important;
      box-shadow: inherit !important;
    }
  }

  .header {
      background: transparent !important;
     
  }
  
  .anticon:hover {
    color: yellow;
    cursor:  pointer;
  }
  .@{prefix-cls} {
    min-height: 100%;
    overflow: hidden;
    background-color: @themeColor;//@light-bg;
    @media (max-width: @screen-xl) {
      background-color: @themeColor;

      .@{prefix-cls}-header {
        background-color: @themeColor;// @light-bg;
      }
    }
 

    &::before {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      margin-left: -48%;
   //    background-image: url(/@/assets/svg/login-bg.svg);
      background-position: 100%;
      background-repeat: no-repeat;
      background-size: auto 100%;
      content: '';
      @media (max-width: @screen-xl) {
        display: none;
      }
    }

    .@{website-prefix-cls} {
      position: absolute;
      top: 12px;
      height: 30px;

      &__title {
        font-size: 16px;
        color: #fff;
      }

      img {
        width: 32px;
      }
    }

 

    
  }
</style>
