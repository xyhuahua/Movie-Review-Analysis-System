import router from '@/router'

export function goMovieDetail (movieId) {
  localStorage.removeItem('currentMovie')
  router.push({
    name: 'movieDetail',
    param: {
      movieId: movieId
    }
  })
  localStorage.setItem('currentMovie', movieId)
}
export function goRoleDetail (movieId) {
  localStorage.removeItem('currentMovie')
  router.push({
    name: 'roleDetail',
    param: {
      movieId: movieId
    }
  })
  localStorage.setItem('currentMovie', movieId)
}
export function goRank () {
  router.push({
    name: 'rank'
  })
}
export function goMovie () {
  router.push({
    name: 'movie'
  })
}
export function goReview () {
  router.push({
    name: 'review'
  })
}
export function goRole () {
  router.push({
    name: 'Role'
  })
}
export function goPersonalCenter () {
  router.push({
    name: 'info'
  })
}
export function goChat (roomId,checkList) {
  router.push({
    name: 'chat',
    params: {
      roomId: roomId, 
      checkList:checkList
   }
  })
}