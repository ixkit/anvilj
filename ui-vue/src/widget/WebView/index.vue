<template>
    <div style="width: 100%; height: 100%;"> 
      <iframe allow="xr-spatial-tracking" id="webView" :src="url" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
    </div>
  </template>
  <script lang="ts" setup name="webView">
    import { onMounted,ref, reactive, unref, nextTick, defineExpose } from 'vue';
   
    import { CollapseContainer } from '/@/components/Container';
  
    import { useUserStore } from '/@/store/modules/user';
    import { getToken } from '/@/utils/auth';
    import { useRoute } from 'vue-router'; 
    
    import { useGlobSetting } from '/@/hooks/setting';
    import { useMessage } from '/@/hooks/web/useMessage';
    import { Modal } from 'ant-design-vue';
    import { useDesign } from '/@/hooks/web/useDesign';
    
  
    const { prefixCls } = useDesign('j-user-tenant-setting-container');
  
  
    const userStore = useUserStore();
  
    const route = useRoute();
    const params = route.query;
    const paramsStr = new URLSearchParams(params).toString();
  
    const glob = useGlobSetting(); 
    // call api server, spring mvc redirect to target static html file
    const url = ref(`${glob.domainUrl}/website/view/?${paramsStr}`);
    
    console.log('target url:', url.value);
  
    const { createMessage } = useMessage();
    //windows对象，用于关闭窗口事件
    const windowsIndex = ref<any>('');
    //窗口监听事件
    const receiveMessage = ref<any>('');
    
  
    function getHostPath(){
      var hostOrigin = window.location.origin;
      return hostOrigin;  
    }
    
   
    /**
     *
     * @param params
     */
    function open(params) {
      
      const host = getHostPath();
      let url = `${host}/webview?${params}`;
      //窗口为不空关闭
      console.log("unref(windowsIndex) ::",unref(windowsIndex))
      if(unref(windowsIndex)){
        //确保只有一个窗口
        windowsIndex.value.close();
        //确保只有一个监听
        window.removeEventListener('message', unref(receiveMessage),false);
      }
      
      windowsIndex.value = window.open(
        url,
        `arguments ${params}`
       //, 'height=500, width=500, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no'
      );
   
      receiveMessage.value = async function (event) {
        console.log('receiveMessage',event);
        let token = event.data;
        if (typeof token === 'string') {
          //如果是字符串类型 说明是token信息
           
        } else {
          cmsFailed();
        }
        window.removeEventListener('message', unref(receiveMessage),false);
        windowsIndex.value = "";
      };
      window.addEventListener('message', unref(receiveMessage), false);
    }
   
  
 
  
    onMounted(() => {
      
    });
    defineExpose({
      open
    });
  </script>
  <style lang="less">
    
  </style>
  