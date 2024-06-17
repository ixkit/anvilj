<template>
    <div ref="gridContainer" :class="`${prefixClsCard}__content`" >
      <a-list>
        <a-row :gutter="16">
          <template v-for="item in dataSet" :key="item.id">
            <a-col :span="6">
              <a-list-item> 
                <span v-if="$slots.itemCardRaw" style="width: 100%;">
                    <slot name="itemCardRaw"  v-bind:item="item"></slot>
                </span>  
                <span v-else style="width: 100%;"> 
                  <a-card :hoverable="true" :class="`${prefixClsCard}__card`">
                  <!-- default -->
                    <div :class="`${prefixClsCard}__card-title`"> {{ item.name }}</div>
                    
                    <div :class="`${prefixClsCard}__card-detail`"> {{ item.remark ? item.remark : item}} </div>  
                  </a-card>
                </span> 
              </a-list-item>
            </a-col>
          </template>
        </a-row>
      </a-list>
    </div>   
</template>
<script lang="ts">
  import { log } from 'console';
   
  import type {  PropType  } from 'vue';
  import { defineComponent, computed, ref, unref, toRaw, watchEffect } from 'vue';
  
  
  import { useDesign } from '/@/hooks/web/useDesign';
  import { useTableContext } from '../hooks/useTableContext';



  export default defineComponent({
    name: 'BasicCardGrid',
    components: {
     
    },
    props: {
      
      // prefixClsCard:{
      //   type: [String, Array] as PropType<string | string[]>,
      //   default: null,     
      // },
      titleHelpMessage: {
        type: [String, Array] as PropType<string | string[]>,
        default: '',
      },
      // //@@ by Robin
      // viewStyle: {
      //   type: [String, Array] as PropType<string | string[]>,
      //   default: '',
      // },
    },
    expose: ['cardVisible', 'message','gridContainer'],
    emits: ['columns-change', 'switch-view-style'],
    setup(props, { emit }) {
      const innerPropsRef = ref<Partial<BasicTableProps>>();
      const getProps = computed(() => { 
        return { ...props, ...unref(innerPropsRef) } ;//as BasicTableProps;
      });
       
      const table = useTableContext();
      console.log('table :',table); 
      const dataSet = computed((): Recordable[] => { 
        if (!cardVisible.value){ 
          console.log('on card hide');
          return [];
        }
        console.log('on card show');
        let dataSource = toRaw(unref(table.getDataSource()));
        const list =[];
        dataSource.forEach((item, i) => {
           list.push(item);
        });
        return list;
      });
      const columns = computed((): Recordable[] => { 
        let columns = toRaw(unref(table.getColumns())); 
        return columns;
      });
      
      watchEffect(() => {
        setTimeout(() => {
        
         
         const columnsList = unref(columns);
         console.log('ccc columnsList',columnsList);
         ;
        }, 0);
      });
      const cardVisible = ref<Boolean>(false);
      const message = ref<string>("我是子组件message");

      const gridContainer = ref(null);
       // 该函数可以接受父级传递一个参数，并修改valueRef的值
      const acceptValue = (value: string) => (valueRef.value = value);
       ;
       
      const style = null;//table.getBindValues.value.prefixClsCard;
      console.log('getProps style:',style);
      const prefixClsCard = 'list-card';//style ? style : 'list-card'; // useDesign('basic-table-header');

      const list: any[] = [];
       

      function handleSwitchViewStyle(style: string) {
        //emit('switch-view-style', style);
       throw "?";
      }

      const { getSelectRowKeys, setSelectedRowKeys, getRowSelection } = useTableContext();
      const selectRowKeys = computed(() => getSelectRowKeys());
      const openRowSelection = computed(() => getRowSelection());
      // 是否允许跨页选择
      const isAcrossPage = computed(() => openRowSelection.value?.preserveSelectedRowKeys === true);

      return { 
       
        toRaw,
        columns,
        dataSet, 
        cardVisible,
        message,
        gridContainer,
        acceptValue,
        prefixClsCard,
        list, handleSwitchViewStyle, selectRowKeys, setSelectedRowKeys, openRowSelection, isAcrossPage 
      };
    },
  });
</script>
<style lang="less" scoped>
   
   .list-card {
    
    &__link {
      margin-top: 10px;
      font-size: 14px;

      a {
        margin-right: 30px;
      }

      span {
        margin-left: 5px;
      }
    }

    &__card {
      width: 100%;
      margin-bottom: -8px;

      .ant-card-body {
        padding: 16px;
      }

      &-title {
        margin-bottom: 5px;
        font-size: 16px;
        font-weight: 500;
        color: @text-color;

        .icon {
          margin-top: -5px;
          margin-right: 10px;
          font-size: 38px !important;
        }
      }

      &-detail {
        padding-top: 10px;
        padding-left: 0px;
        font-size: 14px;
        color: @text-color-secondary;
      }
    }
  }
</style>
