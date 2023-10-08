<template>
  <div class="container">
    <slot />
  </div>
</template>

<style lang="scss" scoped>
$containerWidthDesktop: $container-width-desktop;

.container {
  width: 100%;
  height: inherit;
  min-height: 100%;
  margin-right: auto;
  margin-left: auto;
}
</style>
