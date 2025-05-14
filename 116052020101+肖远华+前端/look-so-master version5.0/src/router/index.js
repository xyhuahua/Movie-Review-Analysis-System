import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/views/homePage'
import Recommend from '@/views/recommend'
import Movie from '@/views/movie'
import Review from '@/views/review'
import Rank from '@/views/rank'
import Role from '@/views/role'
import RoleDetail from '@/views/role/roleDetail'
import MovieDetail from '@/views/movieDetail'
import SearchResult from '@/views/searchResult'
import PersonalCenter from '@/views/personalCenter'
import Info from '@/views/user/info'
import ModifyInfo from '@/views/user/modifyInfo'
import ModifyHeader from '@/views/user/modifyHeader'
import AccountManagement from '@/views/user/accountManagement'
import MyCollect from '@/views/user/myCollect'
import PlayRecord from '@/views/user/playRecord'
import Chat from '@/views/user/chat'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/homepage/recommend'
    },
    {
      path: '/homepage',
      name: 'homePage',
      component: HomePage,
      children: [
        {
          path: 'recommend',
          name: 'recommend',
          component: Recommend
        },
        {
          path: 'movie',
          name: 'movie',
          component: Movie
        },
        {
          path: 'review',
          name: 'review',
          component: Review
        },
        {
          path: 'rank',
          name: 'rank',
          component: Rank
        },
        {
          path:'role',
          name:'role',
          component: Role
        },
        {
          path: 'movieDetail',
          name: 'movieDetail',
          component: MovieDetail
        },
        {
          path: 'roleDetail',
          name: 'roleDetail',
          component: RoleDetail
        },
        {
          path: 'searchResult',
          name: 'searchResult',
          component: SearchResult
        },
        {
          path: 'chat',
          name: 'chat',
          component: Chat
        },
        {
          path: 'personalCenter',
          name: 'personalCenter',
          component: PersonalCenter,
          children:[
            {
              path: 'info',
              name: 'info',
              component: Info,
            },
            {
              path: 'modifyInfo',
              name: 'modifyInfo',
              component: ModifyInfo
            },
            {
              path: 'modifyHeader',
              name: 'modifyHeader',
              component: ModifyHeader
            },
            {
              path: 'accountManagement',
              name: 'accountManagement',
              component: AccountManagement
            },
            {
              path: 'myCollect',
              name: 'myCollect',
              component: MyCollect
            },
            {
              path: 'playRecord',
              name: 'playRecord',
              component: PlayRecord
            },
          ]
        },
      ]
    }
  ]
})
