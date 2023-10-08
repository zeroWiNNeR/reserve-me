<template>
  <div class="icon">
    <svg class="icon-svg" :class="props.size">
      <use :xlink:href="`#icon-${icon}`" />
    </svg>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
  icon: {
    type: String,
    required: true
  },
  size: {
    type: String,
    required: false,
    default: 'medium'
  }
})
</script>

<style lang="scss" scoped>
.icon {
  position: relative;
  &-svg {
    &.medium {
      width: 2.4rem;
      height: 2.4rem;
    }
  }
}
</style>
