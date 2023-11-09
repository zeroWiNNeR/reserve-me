<template>
  <div
    :status="popupMobileMenu.status"
    class="mobile-menu-wrapper"
    @click.self="togglePopupMobileMenu({ status: false })"
  >
    <nav class="mobile-menu">
      <ul class="mobile-menu__list">
        <span class="mobile-menu__list-title">Account</span>
        <li v-if="!isAuth" class="mobile-menu__list-item">
          <v-link url="auth">Sign in</v-link>
        </li>
        <li v-if="isAuth" class="mobile-menu__list-item">
          <v-link url="profile">Profile</v-link>
        </li>
        <li v-if="isAuth" @click="logout" class="mobile-menu__list-item">Logout</li>
      </ul>
    </nav>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { storeToRefs } from 'pinia'
import { usePopupStore } from '@/store/popups/popups'

const popupStore = usePopupStore()
const { togglePopupMobileMenu } = usePopupStore()
const { popupMobileMenu } = storeToRefs(popupStore)

const isAuth = ref(false)

function logout() {
  console.log('add method for logout')
}
</script>

<style lang="scss" scoped>
.mobile-menu-wrapper {
  position: absolute;
  top: 0;
  width: 100%;
  height: 100vh;
  background-color: $white;
}
.mobile-menu {
  margin-top: 7rem;
  padding: 0 2rem;
  &__list {
    &-title {
      display: block;
      margin-bottom: 0.5rem;
      font-family: 'Montserrat Medium';
      font-size: 1.2rem;
      line-height: 120%;
      opacity: 0.7;
    }
    &-item {
      padding: 0.5rem 0;
      font-family: 'Montserrat Medium';
      font-size: 1.8rem;
      line-height: 100%;
    }
  }
}
</style>
