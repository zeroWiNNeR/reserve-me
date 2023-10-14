<template>
  <div class="chbx__checkbox">
    <label class="chbx__checkbox-label" :for="props.name">
      <input type="checkbox" class="chbx__checkbox-input" :id="props.name" :name="props.name" />
    </label>
    <p v-if="props.text" class="chbx__checkbox-text">{{ props.text }}</p>
    <p v-else class="chbx__checkbox-text">
      <slot></slot>
    </p>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'
const props = defineProps({
  name: {
    type: String,
    default: ''
  },
  text: {
    type: String,
    default: ''
  },
  required: {
    type: Boolean,
    default: true
  },
  disabled: {
    type: Boolean,
    default: false
  }
})
</script>

<style lang="scss" scoped>
.chbx__checkbox {
  position: relative;
  padding-left: 4rem;
  min-height: 2rem;
  width: 100%;
  &-input {
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    &::before {
      content: '';
      width: 1.6rem;
      height: 1.6rem;
      border-radius: $base-border-radius;
      position: absolute;
      background-color: $grey;
      top: 0;
      bottom: 0;
      margin-top: auto;
      margin-bottom: auto;
      left: 0;
      border: 3px solid $grey-clear;
      box-shadow: $base-shadow;
      cursor: pointer;
    }
    &::after {
      content: '';
      width: 1.2rem;
      height: 1.2rem;
      border-radius: $base-border-radius;
      position: absolute;
      background: url('@/assets/icons/checkmark.svg') no-repeat;
      background-size: contain;
      top: 0;
      bottom: 0;
      margin-top: auto;
      margin-bottom: auto;
      left: -0.4rem;
      visibility: hidden;
      opacity: 0;
      transition: all 0.2s ease-in;
      cursor: pointer;
    }
    &:checked {
      &::after {
        content: '';
        width: 1.6rem;
        height: 1.6rem;
        border-radius: $base-border-radius;
        position: absolute;
        background: url('@/assets/icons/checkmark.svg') no-repeat;
        background-size: contain;
        top: 0;
        bottom: 0;
        margin-top: auto;
        margin-bottom: auto;
        left: 3px;
        visibility: visible;
        opacity: 1;
        transition: all 0.1s ease-out;
        cursor: pointer;
      }
    }
  }
  &-label {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    line-height: 100%;
    cursor: pointer;
    user-select: none;
  }
  &-text {
    @include adaptive-font(1.2, 1.1);
    line-height: 100%;
  }
  .error-message {
    display: block;
    font-family: 'Montserrat Regular';
    @include adaptive-font(1.2, 1);
    color: $dark-blue;
  }
}
</style>
