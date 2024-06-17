<!-- @@ card style -->
<template>
  <Tooltip placement="top" v-bind="getBindProps">
    <template #title>
      <span>{{ t('切换视图') }}</span>
    </template>
    <span v-if="getViewStyle == 'list'|| getViewStyle == null "> 
      <UnorderedListOutlined  @click="switchViewStyle" />
    </span>
    <span v-else>
      <CreditCardOutlined @click="switchViewStyle" /> 
    </span>
   
  </Tooltip>
</template>
<script lang="ts">
  import { computed, defineComponent } from 'vue';
  import { Tooltip } from 'ant-design-vue';
  import { UnorderedListOutlined, CreditCardOutlined } from '@ant-design/icons-vue';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useTableContext } from '../../hooks/useTableContext';

  export default defineComponent({
    name: 'ViewStyleSetting',
    props: {
      isMobile: Boolean,
    },
    components: {
      UnorderedListOutlined,
      CreditCardOutlined,
      Tooltip,
    },
    emits: ['switch-view-style'],

    setup(props, { emit, attrs }) { 
      const table = useTableContext();
      const { t } = useI18n();

      const getBindProps = computed(() => {
        let obj = {};
        if (props.isMobile) {
          obj['visible'] = false;
        }
        return obj;
      });
      const getViewStyle = computed(() => { 
        return table.getBindValues.value.viewStyle;
      });

      function switchViewStyle() {
        console.log('on switchViewStyle?', table);
        if (1>0){
          emit('switch-view-style', 'xxx');
          return ;
        }
        table.reload();
        
        table.emit!('switch-view-style');
        console.log('on switchViewStyle?', table);
        
      }

      return { getBindProps, getViewStyle, switchViewStyle, t };
    },
  });
</script>
