<template>
  <a v-if="!isVueLink" :href="url" :target="target" class="link">
    <slot><span v-html="text" /></slot>
  </a>
  <router-link v-else :to="mappedUrl" :append="append" :target="target" class="link">
    <slot><span :data-id="mappedUrl" v-html="text" /></slot>
  </router-link>
</template>

<script setup lang="ts">
import { defineProps, computed } from 'vue'

const props = defineProps({
  url: {
    type: String,
    default: ''
  },
  text: {
    type: String,
    default: ''
  },
  target: {
    type: String,
    default: '_self'
  },
  append: {
    type: Boolean,
    default: false
  }
})

const mappedUrl = computed(() =>
  props.url && (props.url.startsWith('/') || props.url.startsWith('#')) ? props.url : `/${props.url}`
)

const isVueLink = computed((): boolean => {
  return !!props.url && !props.url.startsWith('http')
})
</script>

<style lang="scss" scoped>
.link {
  transition: all 0.4s ease;
  text-decoration: underline;
  text-decoration-color: transparent;
  @include hover {
    &:hover {
      text-decoration: underline;
      text-decoration-color: $dark-blue;
      text-underline-offset: 0.2rem;
    }
  }
}
</style>
