<template>
  <component :is="layout" />
</template>

<script setup lang="ts">
import { shallowRef, watch, computed, defineAsyncComponent } from 'vue'
import { useRoute } from 'vue-router'
const DefaultLayout = defineAsyncComponent(() => import('@/layouts/DefaultLayout.vue'))

const layout: any = shallowRef(null)
const route: any = useRoute()

const currentLayout = computed(() => route.meta.layout || '')
watch(currentLayout, async (value) => {
  try {
    const component = await import(`@/layouts/${value}.vue`)
    layout.value = component?.default || DefaultLayout
  } catch (e) {
    layout.value = DefaultLayout
  }
})
</script>
