<template>
  <section class="section">
    <v-container>
      <div class="seciton-content">
        <slot />
      </div>
    </v-container>
  </section>
</template>

<style lang="scss" scoped>
$containerSideOffsetDesktop: $section-side-offset-desktop;
$containerSideOffsetMobile: $section-side-offset-mobile;

$verticalOffsetMobile: $vertical-offset-mobile;
$verticalOffsetDesktop: $vertical-offset-desktop;

$black: black;

.section {
  overflow: hidden;
  @include tablet-desktop {
    padding: $verticalOffsetDesktop $containerSideOffsetDesktop;
  }
  @include mobile {
    padding: $verticalOffsetMobile $containerSideOffsetMobile;
  }

  .container {
    display: flex;
    flex-direction: column;
    @include tablet-desktop {
      row-gap: 5rem;
    }
    @include mobile {
      row-gap: 2.5rem;
    }
  }
}
</style>
