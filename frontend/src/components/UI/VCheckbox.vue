<template>
  <div class="chbx">
    <div class="chbx__checkbox">
      <input type="checkbox" class="chbx__radio chbx__radio-second" :id="props.name" :name="props.name" />
      <label :for="props.name" v-html="props.text"></label>
    </div>
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
.chbx {
  &__radio {
    vertical-align: top;
    width: 1.6rem;
    height: 1.6rem;
    margin: 0 0.3rem 0 0;
  }
  &__radio + label {
    cursor: pointer;
  }
  &__radio:not(checked) {
    position: absolute;
    opacity: 0;
    border-radius: 0.3rem;
  }
  &__radio:not(checked) + label {
    position: relative;
    padding: 0 0 0 3.6rem;
    display: block;
  }
  &__radio-first:not(checked) + label:before {
    top: 3.6rem;
  }
  &__radio-second:not(checked) + label:before {
    top: 1rem;
  }
  &__radio:not(checked) + label:before {
    content: '';
    position: absolute;
    left: 0;
    width: 1.5rem;
    height: 1.5rem;
    border: 0.1rem solid $grey !important;
    border-radius: 0.3rem !important;
    background: $grey !important;
    transform: translateY(-50%);
  }
  &__radio-first:not(checked) + label:after {
    top: 3.6rem;
  }
  &__radio-second:not(checked) + label:after {
    top: 1rem;
  }
  &__radio:not(checked) + label:after {
    content: '';
    position: absolute;
    left: 0.588rem;
    width: 1rem !important;
    height: 1rem !important;
    border-radius: 0 !important;
    background-image: url('@/assets/icons/checkbox.svg') !important;
    background-repeat: no-repeat;
    background-position: center;
    opacity: 0;
    transition: all 0.3s ease-in-out;
    transform: translateY(-50%) !important;
  }
  &__radio:checked + label:after {
    opacity: 1;
  }
  label {
    font-family: 'Montserrat Regular';
    @include adaptive-font(1.4, 1.2);
    color: $dark-blue;
  }
  .error-message {
    display: block;
    font-family: 'Montserrat Regular';
    @include adaptive-font(1.2, 1);
    color: $dark-blue;
  }
}
.chbx__checkbox {
  .chbx__radio:not(checked) + label:before {
    border: none;
    background: $grey;
    border-radius: 0.3rem;
  }
  .chbx__radio-first:not(checked) + label:after {
    top: 3.6rem;
  }
  .chbx__radio-second:not(checked) + label:after {
    top: 1rem;
  }
  .chbx__radio:not(checked) + label:after {
    position: absolute;
    left: 0.288rem;
    border-radius: unset;
    transform: translateY(-50%);
  }
}
</style>
