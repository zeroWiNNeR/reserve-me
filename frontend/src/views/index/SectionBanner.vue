<template>
  <v-section class="section-banner">
    <div class="section-banner-wrapper">
      <h1 class="section-banner__title">We<br />make<br />it easy</h1>
    </div>
  </v-section>
</template>

<script setup></script>

<style lang="scss" scoped>
.section-banner {
  padding: 10rem 12rem;
  display: flex;
  //background: url('@/assets/images/banners/index-banner-background.png') center no-repeat;
  //background-size: cover;
  // background-color: #e9e2d8;
  @include desktop {
    min-height: 40rem;
  }
  &__title {
    font-family: 'Montserrat SemiBold';
    @include adaptive-font(8.8, 2.4);
    color: $dark-blue;
  }
}
</style>
