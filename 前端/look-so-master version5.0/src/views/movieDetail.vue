<template>
  <div class="movie-detail">
    <div class="content-top">
      <div class="detail-left">
      <movie-info :movieInfo="movieInfo" class="movie-info"></movie-info>
      <actor class="actor"></actor>
      <div class="ECharts" style="margin-top: 20px;">
        <echarts-l1 style="width: 48%;float: left;"></echarts-l1>
        <echarts-l2 style="width: 48%;margin-left: 52%;"></echarts-l2>
      </div>
      <div class="ECharts" style="margin-top: 20px;">
        <echarts-l3 style="width: 48%;float: left;"></echarts-l3>
        <echarts-l4 style="width: 48%;margin-left: 52%;"></echarts-l4>
      </div>
      <!-- <write-review class="write-review"></write-review> -->
      <movie-reviews :hotReviews="hotReviews" class="recommend-reviews"></movie-reviews>
    </div>
    <div class="detail-right">
      <echarts-r1></echarts-r1>
      <similar-movie :similarMovies="similarMovies" class="similar-movie"></similar-movie>
    </div>
    </div>
  </div>
</template>

<script>
import {getMovieDetail} from '@/js/api'
import {getSimilarMovies} from '@/js/api'
import MovieInfo from './movieDetail/movieInfo'
import WriteReview from './movieDetail/writeReview'
import MovieReviews from '@/views/movieDetail/movieReviews'
import Actor from '@/views/movieDetail/actor'
import EchartsR1 from '@/views/movieDetail/EchartsR1'
import EchartsL1 from '@/views/movieDetail/EchartsL1'
import EchartsL2 from '@/views/movieDetail/EchartsL2'
import EchartsL3 from '@/views/movieDetail/EchartsL3'
import EchartsL4 from '@/views/movieDetail/EchartsL4'
import SimilarMovie from '@/views/movieDetail/similarMovie'
export default {
  components: {
    MovieInfo,
    WriteReview,
    MovieReviews,
    Actor,
    EchartsR1,
    EchartsL1,
    EchartsL2,
    EchartsL3,
    EchartsL4,
    SimilarMovie
  },
  data () {
    return {
      movieInfo: {},
      hotReviews: [],
      similarMovies:[]
    }
  },
  created () {
    this._getMovieDetail()
   console.log("---------------------------------------------")
  },
  methods: {
    _getMovieDetail () {
      getMovieDetail(this.movieId).then((res) => {
        // console.log("res.hotReviews")
        // console.log(res.hotReviews)
        console.log(res.movie.iprate);
        this.movieInfo = res.movie
        this.hotReviews = res.hotReviews
        this.allReviews = res.allReviews
 var type = this.movieInfo.types[0]
    var regions = this.movieInfo.regions[0]
    console.log("0000000000000")
    this._getSimilarMovies(type,regions)
    console.log("0000000000000")
      })
    },
    _getSimilarMovies(type,regions) {
      getSimilarMovies(type,regions).then((res) => {
        console.log("000000000000")
        console.log(res)
        this.similarMovies = res.SimilarMovies
        
      })
    }
  },
  computed: {
    movieId () {
      return JSON.parse(localStorage.getItem('currentMovie'))
    },
    score: {
      get: function () { return this.movieInfo.reviewScore / 2 },
      set: function () {}
    }
  }
}

</script>
<style lang="less" scoped>
.movie-detail {
  display: flex;
  flex-direction: column;
  width: 85%;
  margin: 0 auto;
  .content-top{
    display: flex;
    width: 100%;
    margin-top: 20px;
    .detail-left {
      width: 70%;
      .write-review,.recommend-reviews,.actor {
        margin-top: 20px;
      }
    }
    .detail-right {
      width: 28%;
      margin-left: auto;
       .similar-movie{
        margin-top: 20px;
       }
      }
  }
}
</style>
