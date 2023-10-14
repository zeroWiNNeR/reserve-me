<template>
  <v-container>
    <div class="auth-wrapper">
      <div class="reg">
        <h1 class="reg__title">
          Sign up /
          <role-select @changeType="userType($event)" />
        </h1>
        <div v-if="signUpShow">
          <div class="reg__sign-in top">Already have an account? <span @click="changeTabs()">Sign in</span></div>
          <form :name="defaultUserType" class="reg-form">
            <form-row>
              <v-input name="first_name" type="text" placeholder="First Name" />
              <v-input name="last_name" type="text" placeholder="Last Name" />
            </form-row>
            <v-input name="email" type="email" placeholder="E-mail" />
            <v-input name="password" type="password" placeholder="Password" />
            <v-checkbox name="policy" class="mt25-mb">
              I agree with the <v-link url="terms-of-use">Terms of Use</v-link>. I acknowledge the
              <v-link url="policy">Privacy Policy</v-link>.
            </v-checkbox>
            <v-button type="submit" class="mt25" text="Sign up" aria-label="Sign up button" />
          </form>
        </div>
        <div v-if="!signUpShow">
          <form :name="defaultUserType" class="reg-form">
            <v-input name="email" type="email" placeholder="E-mail" />
            <v-input name="password" type="password" placeholder="Password" />
            <v-button type="submit" text="Sign in" aria-label="Sign in button" />
          </form>
          <div class="reg__sign-in bottom">Not using ReserveMe yet? <span @click="changeTabs()">Sign up</span></div>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script setup>
import { ref } from 'vue'
import FormRow from '@/components/rows/form-row.vue'
import RoleSelect from '@/components/base/role-select.vue'

const signUpShow = ref(false)

const defaultUserType = ref('client')

const userType = (type) => {
  defaultUserType.value = type
}
const changeTabs = () => {
  signUpShow.value === false ? (signUpShow.value = true) : (signUpShow.value = false)
}
</script>

<style lang="scss" scoped>
.auth {
  &-wrapper {
    height: 100vh;
    display: flex;
    justify-content: end;
    align-items: center;
    @include mobile-tablet {
      padding: 0 2rem;
    }
    .reg {
      width: 50%;
      @include mobile-tablet {
        width: 100%;
      }
      &-form {
        width: 56%;
        @include mobile-tablet {
          width: 100%;
        }
      }
      &__title {
        margin-bottom: 3rem;
        @include adaptive-font(3.4, 2.2);
      }
      &__sign-in {
        @include adaptive-font(1.5, 1.3);
        span {
          font-family: 'Montserrat Medium';
          text-decoration: underline;
          text-decoration-color: $dark-blue;
          text-underline-offset: 0.2rem;
          cursor: pointer;
        }
        &.top {
          margin-bottom: 2rem;
        }
        &.bottom {
          margin-top: 2rem;
        }
      }
    }
  }
}
</style>
