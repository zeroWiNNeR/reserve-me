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
  //position: sticky;
  position: fixed;
  z-index: $Zheader;
  top: 0;
  width: 100%;
  @include tablet-desktop {
    padding: 2rem 10rem;
  }
  @include mobile {
    padding: 1rem 2rem 0;
    background-color: $white;
  }
}

.wrapper {
  position: relative;
}
</style>
