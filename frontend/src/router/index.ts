import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  { path: '/:catchAll(.*)', redirect: '/error' },
  {
    path: '/error',
    component: () => import(/* webpackChunkName: "about" */ '@/pages/error/ErrorPage.vue'),
    meta: {
      layout: 'ErrorLayout',
      errorCode: 404
    }
  },
  {
    path: '/',
    name: 'index',
    component: () => import(/* webpackChunkName: "about" */ '@/pages/IndexPage.vue'),
    meta: {
      layout: 'DefaultLayout'
    }
  },
  {
    path: '/auth',
    name: 'auth',
    component: () => import(/* webpackChunkName: "about" */ '@/pages/auth/Auth.vue'),
    meta: {
      layout: 'AuthLayout'
    }
  }
]

const router = createRouter({
  routes,
  history: createWebHistory(process.env.BASE_URL),
  scrollBehavior: async (to: any, from: any, savedPosition: any) => {
    if (savedPosition) {
      return savedPosition
    }

    const findEl = async (hash: any, x: any) => {
      const element = document.querySelector(hash)

      return (
        element ||
        // eslint-disable-next-line consistent-return
        new Promise<void>((resolve) => {
          if (x > 50) {
            // eslint-disable-next-line no-promise-executor-return
            return resolve()
          }
          setTimeout(() => {
            // eslint-disable-next-line no-plusplus, no-param-reassign
            resolve(findEl(hash, ++x || 1))
          }, 100)
        })
      )
    }

    // if (to.hash) {
    //   const element = await findEl(to.hash, 1)
    //   const headerHeight = document.querySelector('HEADERCLASS')?.getBoundingClientRect()?.height || 0
    //
    //   return window.scrollTo({
    //     top: element?.offsetTop ? element?.offsetTop + headerHeight + 5 : 0,
    //     behavior: 'smooth'
    //   })
    // }

    return window.scrollTo({ top: 0, behavior: 'smooth' })
  }
})

export default router
