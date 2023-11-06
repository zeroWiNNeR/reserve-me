<template>
  <aside class="sidebar">
    <ul class="sidebar__list fx-cl">
      <li
        v-for="(item, i) in props.list"
        :key="i"
        @click="changeTab(item)"
        :class="i === currentId ? 'active' : ''"
        class="sidebar__list-item"
      >
        {{ item.name }}
      </li>
      <li class="sidebar__list-item" @click="logout">Log out</li>
    </ul>
  </aside>
</template>

<script setup>
import { defineEmits, ref } from 'vue'
const emit = defineEmits(['changeTab'])
const currentId = ref(0)

const props = defineProps({
  list: {
    type: Array,
    default: () => {
      return []
    }
  }
})
const changeTab = (item) => {
  currentId.value = item.id
  emit('changeTab', `${item.name}`)
}
function logout() {
  console.log('add method for logout')
}
</script>

<style lang="scss" scoped>
.sidebar {
  &__list {
    row-gap: 1rem;
    &-item {
      padding: 0.5rem 0.5rem;
      font-family: 'Montserrat Regular';
      @include adaptive-font(1.6, 1.4);
      cursor: pointer;
      transition: all 0.3s ease;
      &.active {
        font-family: 'Montserrat Bold';
      }
      @include hover {
        &:hover {
          border-radius: $base-border-radius;
          background-color: $dark-blue-clear;
        }
      }
    }
  }
}
</style>
