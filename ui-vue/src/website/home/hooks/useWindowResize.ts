import { onMounted, onUnmounted, ref } from "vue";

function useWindowResize() {
  const width = ref(0);
  const height = ref(0);
  const listeners =[];

  function onResize() {
    width.value = window.innerWidth;
    height.value = window.innerHeight;
    notifyListener();
  }

  onMounted(() => {
    window.addEventListener("resize", onResize);
    onResize();
  });

  onUnmounted(() => {
    window.removeEventListener("resize", onResize);
  });

  function notifyListener(){
    for (const item in listeners) {
      console.log('try notifyListener',item);
      item.apply(null,[width.value,height.value]);
    }
  }

  function addListener(listener){
    console.log(' addListener ', listener);
    listeners.push(listener.value);
  }
  function removeListener(listener){
    let index = -1;
    for (const item in listeners) {
        if (item === listener){
          break;
        }
        index = index + 1;
    }
    if (index < 0 ) return false;
    console.log(' removeListener ', index);
    delete listeners[index];
    return true;
  }
  return {
    width,
    height,
    addListener,
    removeListener,
  };
}

export default useWindowResize;