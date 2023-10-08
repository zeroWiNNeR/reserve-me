/* eslint-disable */
import App from '@/App.vue'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from '@/router'
import { packages } from '@/packages/'

import '@/assets/scss/reserve-me/index.scss'
import VInput from '@/components/UI/VInput.vue'
import VIcon from '@/components/UI/VIcon.vue'
import VButton from '@/components/UI/VButton.vue'
import VCheckbox from '@/components/UI/VCheckbox.vue'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.component('VInput', VInput)
app.component('VIcon', VIcon)
app.component('VButton', VButton)
app.component('VCheckbox', VCheckbox)

packages.forEach((packageItem: any) => {
    app.component(packageItem.name, packageItem.component)
})

app.mount('#app')
