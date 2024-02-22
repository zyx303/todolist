import { defineStore } from 'pinia'

export const useStore = defineStore({
  id: 'main',
  state: () => ({
    base_url: 'localhost:9696/'
  }),
})