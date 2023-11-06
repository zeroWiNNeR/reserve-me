import { defineStore } from 'pinia'
import { ref } from 'vue'

export const usePopupStore = defineStore('popups', () => {
  const popupMobileMenu = ref({
    status: false
  })

  const togglePopupMobileMenu = (payload: any) => {
    const html: any = document.querySelector('html')
    document.body.classList[payload.status ? 'add' : 'remove']('open-menu')
    html.classList[payload.status ? 'add' : 'remove']('open-menu')

    popupMobileMenu.value = {
      status: payload.status || false
    }
  }

  return {
    popupMobileMenu,
    togglePopupMobileMenu
  }
})
