import Vue from 'vue'
import Router from 'vue-router'
import Root from '@/components/Root'

Vue.use(Router)
const login = r => require.ensure([], () => r(require('../pages/test/test.js')), 'test')
export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: Root
    },
    {
      path: '/test',
      name: 'test',
      component: login
    }
  ]
})
