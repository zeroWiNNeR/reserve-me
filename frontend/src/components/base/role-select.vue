<template>
  <span @click="business()" :class="{ active: isBusiness }">business</span>
  <span @click="client()" :class="{ active: isClient }"> client</span>
</template>

<script setup>
import { ref, defineEmits } from 'vue'

const emit = defineEmits(['changeType'])

const isClient = ref(true)
const isBusiness = ref(false)

const business = () => {
  isBusiness.value = true
  isClient.value = false
  emit('changeType', 'business')
}

const client = () => {
  isClient.value = true
  isBusiness.value = false
  emit('changeType', 'client')
}
</script>

<style lang="scss" scoped>
span {
  cursor: pointer;
  opacity: 0.25;
  transition: all 0.3s ease;
  &.active {
    color: $brand;
    opacity: 1;
    @include hover {
      &:hover {
        opacity: 1;
      }
    }
  }
  @include hover {
    &:hover {
      color: $brand;
      opacity: 0.5;
    }
  }
}
</style>
