import type { ComputedRef, Slots } from 'vue';
import type { BasicTableProps, InnerHandlers } from '../types/table';
import { unref, computed, h } from 'vue';
import TableHeader from '../components/TableHeader.vue';
import { isString } from '/@/utils/is';
import { getSlot } from '/@/utils/helper/tsxHelper';

export function useTableHeader(propsRef: ComputedRef<BasicTableProps>, slots: Slots, handlers: InnerHandlers) {
  const getHeaderProps = computed((): Recordable => {
    //@@ card style, add viewStyle viewStyle,
    const { title, showTableSetting, titleHelpMessage, viewStyle, tableSetting } = unref(propsRef);
    const hideTitle = !slots.tableTitle && !title && !slots.toolbar && !showTableSetting;
    if (hideTitle && !isString(title)) {
      return {};
    }
    //@@ card style, add viewStyle
    if (!tableSetting){
      // cause error on draw ? 
     // tableSetting.showViewStyle = !(viewStyle === null);
    }
     
    return {
      title: hideTitle
        ? null
        : () =>
            h(
              TableHeader,
              {
                title,
                titleHelpMessage,
                viewStyle, //@@ card style
                showTableSetting,
                tableSetting,
                onColumnsChange: handlers.onColumnsChange,
                onSwitchViewStyle: handlers.onSwitchViewStyle,
              } as Recordable,
              {
                ...(slots.toolbar
                  ? {
                      toolbar: () => getSlot(slots, 'toolbar'),
                    }
                  : {}),
                ...(slots.tableTitle
                  ? {
                      tableTitle: () => getSlot(slots, 'tableTitle'),
                    }
                  : {}),
                ...(slots.headerTop
                  ? {
                      headerTop: () => getSlot(slots, 'headerTop'),
                    }
                  : {}),
                //添加tableTop插槽
                ...(slots.tableTop
                  ? {
                      tableTop: () => getSlot(slots, 'tableTop'),
                    }
                  : {}),
                // 添加alertAfter插槽
                ...(slots.alertAfter ? { alertAfter: () => getSlot(slots, 'alertAfter') } : {}),
              }
            ),
    };
  });
  return { getHeaderProps };
}
