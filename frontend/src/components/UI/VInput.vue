<template>
  <div class="row" :class="{ error: errorMessage }">
    <label class="label-top hidden" :class="{ active: isFocused || isMouseEnter }" :for="props.name">
      {{ props.placeholder }}
    </label>
    <input
      :type="props.type"
      :value="props.modelValue"
      :placeholder="props.placeholder"
      :name="props.name"
      :required="props.required"
      :disabled="props.disabled"
      v-maska="props.mask"
      :class="{ active: isFocused || isMouseEnter, password: props.type === 'password' && props.name === 'password' }"
      @input="onInput"
      @paste="onPaste"
      @blur="onBlur"
      @mouseenter="isMouseEnter = true"
      @mouseleave="isMouseEnter = false"
    />
    <button
      v-if="props.type === 'password' && props.name === 'password'"
      class="password-visibility"
      @click="showPassword"
    >
      <v-icon :icon="isPasswordVisible ? 'eye-cross' : 'eye'" />
    </button>
    <p class="error-msg">
      {{ errorMessage }}
    </p>
  </div>
</template>

<script setup>
import { defineEmits, defineProps, ref } from 'vue'
import { vMaska } from 'maska'

const isFocused = ref(false)
const isMouseEnter = ref(false)
const isPasswordVisible = ref(false)

const props = defineProps({
  required: {
    type: Boolean,
    default: true
  },
  type: {
    type: String,
    default: 'text'
  },
  modelValue: {
    type: [String, Number],
    default: ''
  },
  name: {
    type: String,
    default: 'text',
    required: true
  },
  placeholder: {
    type: String,
    default: 'placeholder'
  },
  errorMessage: {
    type: String,
    default: ''
  },
  disabled: {
    type: Boolean,
    default: false
  },
  mask: {
    type: [String, Object],
    default: null
  }
})

const emit = defineEmits(['update:modelValue', 'get-event-input', 'paste', 'blur'])

const onInput = (event) => {
  isFocused.value = true
  emit('update:modelValue', event.target.value)
  getEventInput(event)
}

const getEventInput = (event) => {
  emit('get-event-input', event)
}
const onPaste = (event) => {
  emit('paste', event)
}
const onBlur = (event) => {
  emit('blur', event)
  event.target.value === '' ? (isFocused.value = false) : ''
}
const showPassword = () => {
  const inputPassword = document.querySelector('.password')
  if (inputPassword.getAttribute('type') === 'password') {
    inputPassword.setAttribute('type', 'text')
  } else {
    inputPassword.setAttribute('type', 'password')
  }
}
</script>

<style lang="scss" scoped>
.row {
  display: flex;
  column-gap: 1rem;
  align-items: center;
  &:has(input:disabled) {
    .label-top {
      display: none;
    }
  }
  input,
  input::placeholder {
    width: 100%;
    border-top-left-radius: 0.4rem;
    border-top-right-radius: 0.4rem;
    font-family: 'Montserrat Medium';
    @include adaptive-font(1.6, 1.4);
    line-height: 100%;
    transition: all 0.4s ease;
    outline: none;
  }

  input {
    width: 100%;
    padding: 1rem;
    border: none;
    color: $dark-blue;
    border-bottom: 0.1rem solid $brand;

    &.active {
      &::placeholder {
        visibility: hidden;
        opacity: 0;
      }
    }

    &::placeholder {
      color: $grey-clear;
    }

    &:disabled {
      // cursor: no-drop;
      background-color: $grey-clear;
      border-bottom: none;
      &::placeholder {
        visibility: visible;
        opacity: 1;
        color: $white !important;
      }
    }

    &:focus {
      box-shadow:
        rgba(0, 0, 0, 0.05) 0px 6px 24px 0px,
        rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
    }
  }
  .label-top {
    position: absolute;
    top: -0.9rem;
    left: 0.9rem;
    padding: 0.2rem 0.4rem;
    border-radius: 0.4rem;
    background-color: $brand-clear;
    font-family: 'Montserrat Medium';
    @include adaptive-font(1, 1.4);
    transition:
      opacity 0.3s,
      visibility 0.3s;
    &.active {
      visibility: visible;
      opacity: 1;
    }
  }
  &.error {
    input {
      border: 0.1rem solid $red;
    }
  }

  .error-msg {
    display: block;
    font-family: 'Montserrat Medium';
    font-size: 1.2rem;
    color: $red;
  }
  .password-visibility {
    width: 2.4rem;
    height: 2.4rem;
    border: none;
    background-color: transparent;
    color: $dark-blue;
    cursor: pointer;
    transition: all 0.2s ease;
    @include hover {
      &:hover {
        scale: 1.1;
      }
    }
  }
}
</style>
