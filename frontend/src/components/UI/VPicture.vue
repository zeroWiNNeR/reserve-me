<template>
  <picture>
    <source type="image/webp" :srcset="require(`@/assets/images/${folder}/${imageName}.webp`)" />
    <source type="image/png" :srcset="require(`@/assets/images/${folder}/${imageName}.png`)" />
    <img
      class="img"
      :class="imageCustomClass"
      :src="require(`@/assets/images/${folder}/${imageName}.png`)"
      :srcset="require(`@/assets/images/${folder}/${imageName}.png`)"
      :alt="alt"
    />
  </picture>
</template>

<script setup>
import { defineProps } from 'vue'

defineProps({
  imageName: {
    type: String,
    default: '',
    required: true
  },
  folder: {
    type: String,
    default: '',
    required: true
  },
  alt: {
    type: String,
    default: '',
    required: true
  },
  imageCustomClass: {
    type: String,
    default: ''
  }
})
</script>

<style lang="scss" scoped>
.img {
  width: 100%;
}
</style>
