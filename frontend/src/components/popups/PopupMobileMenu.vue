<template>
  <div :status="popupMobileMenu.status" class="popup-container" @click.self="togglePopupMobileMenu({ status: false })">
    <div class="popup-wrapper">
      <div class="popup popup-mobile-menu"></div>
    </div>
  </div>
</template>

<script setup>
import { storeToRefs } from 'pinia'
import { usePopupStore } from '@/store/popups/popups'

const popupStore = usePopupStore()
const { togglePopupMobileMenu } = usePopupStore()
const { popupMobileMenu } = storeToRefs(popupStore)
</script>

<style lang="scss" scoped>
.popup-container {
  position: absolute;
}
</style>
