import BaseApp from './BaseApp/BaseApp.vue'
import BaseLayout from './BaseLayout/BaseLayout.vue'
import BaseContainer from './BaseContainer/BaseContainer.vue'
import BaseSection from './BaseSection.vue'
import BaseLink from './BaseLink/BaseLink.vue'

export const basisComponents = [
  { name: 'VApp', component: BaseApp },
  { name: 'VLayout', component: BaseLayout },
  { name: 'VContainer', component: BaseContainer },
  { name: 'VSection', component: BaseSection },
  { name: 'VLink', component: BaseLink }
]
