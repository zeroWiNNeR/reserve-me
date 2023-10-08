/* eslint-disable */
import App from '@/App.vue'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from '@/router'
import { packages } from '@/packages/'

import '@/assets/scss/reserve-me/index.scss'
import VInput from '@/components/UI/VInput.vue'
import VIcon from '@/components/UI/VIcon.vue'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.component('VInput', VInput)
app.component('VIcon', VIcon)

packages.forEach((packageItem: any) => {
    app.component(packageItem.name, packageItem.component)
})

app.mount('#app')
