<template> 
    <div class="intro" v-show="!sayHi">Hello, I'm AnvilJ ! 🚀 </div>
    <div class="intro" v-show="sayHi">{{ sayHi }}</div>
    <div class="tagline">Build Trustable Application 🔨 ☕️ 🚀</div>
    <!-- Find your icons from here - https://fontawesome.com/icons?d=gallery&s=brands -->


  <div class="icons-social" >
        <a target="_blank" href="https://github.com/ixkit/anvilj">
    <i class="fab fa-github" aria-hidden="true" title="Github"></i>
    <span class="sr-only">Github</span>
  </a>
  <a target="_blank" href="https://twitter.com/anvilj">
    <i class="fab fa-twitter" aria-hidden="true" title="Twitter"></i>
    <span class="sr-only">Twitter</span>
  </a>
  <a target="_blank" href="https://dev.to/anvilj">
    <i class="fab fa-dev" aria-hidden="true" title="DEV Community"></i>
    <span class="sr-only">DEV Community</span>
  </a>
  <a target="_blank" href="https://stackoverflow.com/story/anvilj">
    <i class="fab fa-stack-overflow" aria-hidden="true" title="StackOverflow"></i>
    <span class="sr-only">StackOverflow</span>
  </a> 
</div>
</template> 

<script lang="ts"  setup name="landing">
  import { computed } from 'vue';
  import { AppLogo } from '/@/components/Application';
  import { AppLocalePicker, AppDarkModeToggle } from '/@/components/Application';
  import { SettingOutlined, EditOutlined, DeleteOutlined, PlayCircleOutlined,EllipsisOutlined } from '@ant-design/icons-vue';
  
  import { useGlobSetting } from '/@/hooks/setting';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useDesign } from '/@/hooks/web/useDesign';
  import { useLocaleStore } from '/@/store/modules/locale'; 
  const props = defineProps({
    sessionTimeout: {
      type: Boolean,
    },
    sayHi:{
      type: String,
    }
  });

  function importCss(path){ 
    import(path).then(() => {
    });
  }
  function initCss(){
    const cssList = [];
    cssList.push('./css/reset.css')
    cssList.push('./css/styles.css')
    cssList.push('./css/themes/green-white.css');
    cssList.push('./css/fontawesome-free-5.15.4-web/css/all.min.css')
    for (var item of cssList){
        importCss(item);
    }
  }
  initCss();
</script>
<style lang="less"> 
</style>
