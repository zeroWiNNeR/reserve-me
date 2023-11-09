<template>
  <div class="header-wrapper fx-jsb-rw-ac">
    <div class="header__logo">
      <v-link url="/">apponline</v-link>
    </div>
    <nav v-if="isTabletDesktop" class="navigation">
      <ul class="navigation-list">
        <li class="navigation-list__item search">
          <v-link url="search"><v-icon icon="search" /></v-link>
        </li>
        <li v-if="isAuth" class="navigation-list__item auth">
          <v-link url="auth">Sign in</v-link>
        </li>
        <li v-if="!isAuth" class="navigation-list__item profile">
          <v-link url="profile"><v-icon icon="profile" /></v-link>
        </li>
      </ul>
    </nav>
    <button v-if="isMobile" class="navigation__burger" @click="menuHandler">
      <v-icon :icon="popupMobileMenu.status ? 'close' : 'burger'" />
    </button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { storeToRefs } from 'pinia'
import VIcon from '@/components/UI/VIcon'
import { usePopupStore } from '@/store/popups/popups'
import { useMediaQueriesStore } from '@/store/modules/mediaQueries.ts'
const { togglePopupMobileMenu } = usePopupStore()

const isAuth = ref(false)
const popupsStore = usePopupStore()
const { popupMobileMenu } = storeToRefs(popupsStore)
const { isMobile, isTabletDesktop } = storeToRefs(useMediaQueriesStore())
const menuHandler = () => {
  popupMobileMenu.value.status === false
    ? togglePopupMobileMenu({ status: true })
    : togglePopupMobileMenu({ status: false })
}
</script>

<style lang="scss" scoped>
.header-wrapper {
  padding-bottom: 0.6rem;
  border-bottom: 1px solid $dark-blue-clear;
}
.header {
  &__logo {
    &:deep(a) {
      color: $dark-blue;
      font-family: 'Montserrat Bold';
      @include adaptive-font(2, 1.6);
      text-transform: uppercase;
      text-decoration: none !important;
    }
  }
}
.navigation {
  display: flex;
  justify-content: flex-end;
  width: 50%;
  &-list {
    display: flex;
    column-gap: 2rem;
    list-style-type: none;
    &__item {
      &:deep(a) {
        color: $dark-blue;
        font-family: 'Montserrat Regular';
        @include adaptive-font(1.6, 1.4);
        @include hover {
          &:hover {
            text-decoration: none;
            text-decoration-color: transparent;
          }
        }
      }
      &.auth {
        &:deep(a) {
          font-family: 'Montserrat SemiBold';
        }
      }
      &.profile,
      &.search {
        position: relative;
        color: $dark-blue;
        transition: transform 0.3s ease;
        @include hover {
          &:hover {
            transform: scale(1.1);
          }
        }
      }
    }
  }
}
</style>
