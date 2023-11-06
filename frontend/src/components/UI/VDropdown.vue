<template>
  <div class="dropdown">
    <ul class="fx-cl">
      <li v-for="(item, i) in props.list" :key="i">
        {{ item.drop }}
      </li>
    </ul>
  </div>
</template>

<script setup>
const props = defineProps({
  list: {
    type: Array,
    default: () => {
      return []
    }
  }
})

console.log(props.list)
</script>

<style lang="scss" scoped>
.dropdown {
  position: absolute;
  right: 0;
  width: 20rem;
  background-color: $white;
  border-radius: $base-border-radius;
}
</style>
