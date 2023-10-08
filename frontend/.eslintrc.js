module.exports = {
  root: true,
  env: {
    browser: true,
    node: true
  },
  extends: [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
    '@vue/typescript/recommended',
    'plugin:prettier/recommended'
  ],
  parserOptions: {
    ecmaVersion: 2020,
    requireConfigFile: false
  },
  rules: {
    'max-len': 'off',
    'no-param-reassign': 'off',
    'no-void': 'off',
    'no-nested-ternary': 'off',
    'max-classes-per-file': 'off',

    'no-shadow': 'off',
    'linebreak-style': 'off',

    'import/first': 'off',
    'import/extensions': 'off',
    'import/no-unresolved': 'off',
    'import/no-extraneous-dependencies': 'off',
    'import/prefer-default-export': 'off',

    'prefer-promise-reject-errors': 'off',
    'no-underscore-dangle': 'off',
    'vue/multi-word-component-names': 'off',
    'class-methods-use-this': 'off',
    'vue/no-deprecated-events-api': 'off',
    'vue/no-mutating-props': 'off',
    'no-plusplus': 'off',
    'prefer-destructuring': ['error', {'object': false, 'array': false}],

    quotes: ['warn', 'single', {avoidEscape: true}],

    // The core 'no-unused-vars' rules (in the eslint:recommended ruleset)
    // does not work with type definitions
    'no-unused-vars': 'off',

    // allow debugger during development only
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',

    'prettier/prettier': ['error'],

    '@typescript-eslint/no-explicit-any': 'off'
  }
}