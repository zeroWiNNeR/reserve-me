<template>
  <swiper :slidesPerView="3" :spaceBetween="15" :pagination="true" :modules="modules" class="mySwiper">
    <swiper-slide v-for="(item, i) in mocData" :key="i" class="slide fx-jsb-cl">
      <div class="slide__top">
        <v-picture :alt="item.alt" :folder="item.folder" :image-name="item.image" />
      </div>
      <div class="slide__bottom fx-cl">
        <div class="slide__bottom-title">{{ item.title }}</div>
        <p class="slide__bottom-text">{{ item.text }}</p>
      </div>
    </swiper-slide>
  </swiper>
</template>

<script setup>
import { Swiper, SwiperSlide } from 'swiper/vue'
import 'swiper/css'
import VPicture from '@/components/UI/VPicture'

const mocData = [
  {
    title: 'Lorem ipsum dolor',
    text: 'sit amet, consectetur sit amet',
    folder: 'mocdata/slider',
    image: '6',
    alt: 'Изображение'
  },
  {
    title: 'Lorem ipsum dolor',
    text: 'sit amet, consectetur',
    folder: 'mocdata/slider',
    image: '5',
    alt: 'Изображение'
  },
  {
    title: 'Lorem ipsum dolor',
    text: 'sit amet, consectetur',
    folder: 'mocdata/slider',
    image: '4',
    alt: 'Изображение'
  },
  {
    title: 'Lorem ipsum dolor',
    text: 'sit amet, consectetur',
    folder: 'mocdata/slider',
    image: '3',
    alt: 'Изображение'
  },
  {
    title: 'Lorem ipsum dolor',
    text: 'sit amet, consectetur sit amet',
    folder: 'mocdata/slider',
    image: '2',
    alt: 'Изображение'
  },
  {
    title: 'Lorem ipsum dolor',
    text: 'sit amet, consectetur',
    folder: 'mocdata/slider',
    image: '1',
    alt: 'Изображение'
  }
]
</script>

<style lang="scss" scoped>
.swiper {
  &:deep(.swiper-slide) {
    width: 22rem !important;
    height: 26rem !important;
    background-color: $white;
  }
}
.slide {
  cursor: pointer;
  position: relative;
  border-radius: $base-border-radius;
  overflow: hidden;
  &__top {
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    max-height: 17rem;
  }
  &__bottom {
    row-gap: 0.4rem;
    height: 9rem;
    padding: 1rem 2rem;
    background-color: $green-light;
    &-title {
      font-family: 'Montserrat SemiBold';
      @include adaptive-font(1.8, 1.6);
      color: $white;
    }
    &-text {
      font-family: 'Montserrat Regular';
      @include adaptive-font(1.4, 1.4);
      color: $white;
    }
  }
}
</style>
