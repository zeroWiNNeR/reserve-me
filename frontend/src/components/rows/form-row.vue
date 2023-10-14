<template>
  <div class="form-row" :class="'form-row-' + props.size">
    <slot></slot>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
  size: {
    type: String,
    default: 'm'
  }
})
</script>

<style lang="scss" scoped>
.form-row {
  &-m {
    display: flex;
    flex-direction: row;
    column-gap: 1rem;
    @include mobile {
      flex-direction: column;
      column-gap: 0;
    }
  }
}
</style>
