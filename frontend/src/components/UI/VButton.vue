<template>
  <button class="button" :type="props.type" :aria-label="props.ariaLabel">
    <slot></slot>
    {{ props.text }}
    <v-icon v-if="props.icon" :icon="props.icon" />
  </button>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
  type: {
    type: String,
    default: 'button'
  },
  ariaLabel: {
    type: String,
    default: 'button'
  },
  disabled: {
    type: Boolean,
    default: false
  },
  icon: {
    type: String,
    default: ''
  },
  text: {
    type: String,
    default: ''
  }
})
</script>

<style lang="scss" scoped>
.button {
  width: 20rem;
  padding: 1rem;
  border-radius: 0.4rem;
  background-color: $brand;
  color: $white;
  font-family: 'Montserrat Medium';
  @include adaptive-font(1.6, 1.4);
  line-height: 100%;
  cursor: pointer;
  @include hover {
    &:hover {
      background-color: $brand-drk;
    }
  }
  &:focus {
    background-color: $brand-drk;
  }
}
</style>
