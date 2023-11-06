<template>
  <v-layout>
    <template #page-header>
      <base-header />
    </template>
    <template #page-content>
      <router-view />
      <Popup-mobile-menu v-show="popupMobileMenu.status" />
    </template>
    <template #page-footer></template>
  </v-layout>
</template>

<script setup>
import { storeToRefs } from 'pinia'
import { usePopupStore } from '@/store/popups/popups'
import BaseHeader from '@/components/base/base-header.vue'
import PopupMobileMenu from '@/components/popups/PopupMobileMenu.vue'

const popupsStore = usePopupStore()

const { popupMobileMenu } = storeToRefs(popupsStore)
</script>
