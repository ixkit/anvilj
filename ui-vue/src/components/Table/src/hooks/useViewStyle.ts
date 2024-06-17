//@@ card style
import { ref, ComputedRef, unref, computed, watch } from 'vue';
import type { BasicTableProps } from '../types/table';

export function useViewStyle(props: ComputedRef<BasicTableProps>) {
  const viewStyleRef = ref(unref(props).viewStyle);

  watch(
    () => unref(props).viewStyle,
    (viewStyle) => {
      viewStyleRef.value = viewStyle;
    }
  );

  const getViewStyle = computed(() => unref(viewStyleRef));

  function setViewStyle(viewStyle: string) {
    viewStyleRef.value = viewStyle;
  }

  return { getViewStyle, setViewStyle };
}
