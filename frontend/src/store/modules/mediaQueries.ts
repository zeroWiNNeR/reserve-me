import { defineStore } from 'pinia'
import { ref, computed, onMounted, onUnmounted } from 'vue'

export const useMediaQueriesStore = defineStore('mediaQueries', () => {
  const isDesktopData = ref(false)
  const isTabletData = ref(false)
  const isTabletDesktopData = ref(false)
  const isMobileData = ref(false)
  const isMobileTabletData = ref(false)
  const windowWidthData = ref(0)

  const checkQueries = () => {
    const width = window.innerWidth
    windowWidthData.value = width
    isDesktopData.value = width >= 1024
    isTabletData.value = width >= 768 && width <= 1023
    isTabletDesktopData.value = width >= 768
    isMobileData.value = width <= 767
    isMobileTabletData.value = width <= 1023
  }

  onMounted(() => {
    checkQueries()
    window.addEventListener('resize', checkQueries)
  })

  onUnmounted(() => {
    window.addEventListener('resize', checkQueries)
  })

  const isDesktop = computed(() => isDesktopData.value)
  const isTablet = computed(() => isTabletData.value)
  const isTabletDesktop = computed(() => isTabletDesktopData.value)
  const isMobile = computed(() => isMobileData.value)
  const isMobileTablet = computed(() => isMobileTabletData.value)
  const windowWidth = computed(() => windowWidthData.value)

  const deviceSliderSettings = {
    desktop: { breakpoint: 'lg', columns: 12 },
    tablet: { breakpoint: 'md', columns: 8 },
    mobile: { breakpoint: 'sm', columns: 2 }
  }

  const isDeviceBreakpoint = (breakpoint: any) => {
    return (
      (breakpoint === 'lg' && isDesktop.value) ||
      (breakpoint === 'md' && isTablet.value) ||
      (breakpoint === 'sm' && isMobile.value)
    )
  }

  const getDeviceSettings = () => {
    if (isDesktop.value) {
      return deviceSliderSettings.desktop
    }

    if (isTablet.value) {
      return deviceSliderSettings.tablet
    }

    if (isMobile.value) {
      return deviceSliderSettings.mobile
    }

    return deviceSliderSettings.desktop
  }

  return {
    isDesktop,
    isTablet,
    isTabletDesktop,
    isMobile,
    isMobileTablet,
    windowWidth,
    getDeviceSettings,
    isDeviceBreakpoint
  }
})
