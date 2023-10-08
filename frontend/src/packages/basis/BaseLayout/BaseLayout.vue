<template>
  <header class="header">
    <slot name="page-header" />
  </header>
  <main class="wrapper">
    <slot name="page-content" />
  </main>
  <footer class="footer">
    <slot name="page-footer" />
  </footer>
</template>

<style lang="scss" scoped>
.page {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}

.header {
  position: sticky;
  z-index: $Zheader;
  top: 0;
  width: 100%;
}

.wrapper {
  position: relative;
}
</style>
