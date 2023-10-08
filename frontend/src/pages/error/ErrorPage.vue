<template>
  <section class="error fx-ctr">
    <div class="error-container fx-rw">
      <h1 class="error-code">{{ props.errorCode }}</h1>
      <p class="error-text">{{ props.text }}</p>
    </div>
  </section>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
  errorCode: {
    type: Number,
    default: 404
  },
  text: {
    type: String,
    default: 'This page could not be found '
  }
})
</script>

<style lang="scss" scoped>
.error {
  height: 100vh;
  @include mobile {
    height: 75rem;
  }
  &-container {
    column-gap: 2rem;
  }
  &-code {
    position: relative;
    font-family: 'Montserrat SemiBold';
    @include adaptive-font(3.4, 2.4);
    &::after {
      content: '';
      position: absolute;
      right: -1rem;
      width: 0.1rem;
      height: 3.4rem;
      background-color: $dark-blue;
      @include mobile {
        height: 2.4rem;
      }
    }
  }
  &-text {
    @include adaptive-font(1.6, 1.4);
  }
}
</style>
