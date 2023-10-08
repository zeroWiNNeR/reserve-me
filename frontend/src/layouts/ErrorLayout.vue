<template>
  <router-view />
</template>

<script setup>
import { defineProps } from 'vue'

defineProps({
  error: {
    type: Object,
    default: () => ({})
  }
})
</script>
