<template>
  <v-section class="profile">
    <div class="profile-wrapper fx-jfs-rw">
      <profile-sidebar :list="sidebarList" @changeTab="currentTab($event)" class="profile__sidebar" />
      <div class="profile__content">
        <div v-if="defaultTab === 'Edit profile'" class="profile-edit">
          <div class="profile-edit__title">
            <h2>Edit profile</h2>
            <p>
              Keep your personal details private. Information you add here is visible to any who can view your profile.
            </p>
          </div>
          <form class="profile-edit__form">
            <v-input name="first_name" type="text" placeholder="First Name" />
            <v-input name="last_name" type="text" placeholder="Last Name" />
            <v-input name="phone" type="text" placeholder="Phone number" />
            <v-input name="email" type="text" placeholder="E-mail" />
            <v-button text="Save changes" />
          </form>
        </div>
      </div>
    </div>
  </v-section>
</template>

<script setup>
import ProfileSidebar from '@/components/profile/profile-sidebar'
import { ref } from 'vue'

const sidebarList = [
  {
    name: 'Edit profile',
    id: 0
  }
]

const defaultTab = ref('Edit profile')

const currentTab = (tab) => {
  defaultTab.value = tab
  console.log(defaultTab.value)
}
</script>

<style lang="scss" scoped>
.profile {
  padding: 7.4rem 10rem;
  &-wrapper {
    column-gap: 3rem;
  }
  &__sidebar {
    width: 20%;
  }
  &__content {
    width: 50%;
  }

  &-edit {
    max-width: 80%;
    &__title {
      h2 {
        margin-bottom: 1rem;
      }
      p {
        margin-bottom: 3rem;
      }
    }
    &__form {
      width: 70%;
    }
  }
}
</style>
