<template>
  <div class="recommendLike-movies">
    <el-card class="box-card"
             type="border-card"
             @tab-click="isMore"
             v-loading="isLoading">
      <div slot="header"
           class="clearfix">
        <span class="recommendLike-title">猜你喜欢</span>
      </div>
      <div class="info-wrapper"
           @click="_goMovieDetail(item.movieId)">
        <movie-slot class="movie-info"
                    v-for="(info,index) in recommendLikeMovies.slice(0,5)"
                    :key="index">
          <div class="cover-wrap">
            <img class="movie-cover"
                 :src="info.imageUrl"
                 alt=""
                 @click="_goMovieDetail(info.movieId)">
          </div>
          <span class="movie-name"
                @click="_goMovieDetail(info.movieId)">{{info.moveName | ellipsis}}</span>
          <span class="movie-score">{{info.score}}</span>
        </movie-slot>
      </div>
    </el-card>
  </div>
</template>

<script>
import MovieSlot from '@/components/movieSlot'
import { getRecommendLikeMovies } from '@/js/api'
import { goMovie, goMovieDetail } from '@/js/router'
const MORE_INDEX = 'tab-7'
export default {
  components: {
    MovieSlot
  },
  data () {
    return {
      recommendLikeMovies: [],
      isLoading: true

    }
  },
  created () {
    this._getRecommendLikeMovies(sessionStorage.getItem("username"))
  },
  methods: {
    isMore (tab, event) {
      if (event.target.getAttribute('id') === MORE_INDEX) {
        goMovie()
      }
    },
    _getRecommendLikeMovies (userName) {
      getRecommendLikeMovies(userName).then((res) => {
        
        this.recommendLikeMovies = res.guessLike
        //console.log(this.recommendLikeMovies)
        this.isLoading = false
        // console.log(this.recommendMovies)
      })
    },
    _goMovieDetail (movieId) {
      goMovieDetail(movieId)
    }
  },
  filters: {
    // 当标题字数超出时，超出部分显示’...‘。此处限制超出8位即触发隐藏效果
    ellipsis (value) {
        if (!value) return ''
        if (value.length > 7) {
            return value.slice(0, 7) + '...'
        }
        return value
    }
}

}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';
/deep/.el-card__header{
  padding: 8px 20px;
}
/deep/.el-card__body{
  padding: 16px;
}
.recommendLike-movies {
  .box-card {
    width:110%;
    .clearfix {
      .recommendLike-title {
        .all-font(MicrosoftYaHei-Bold, 18px, #005198, -0.43px);
      }
    }
    .info-wrapper {
      display: flex;
      justify-content: space-around;
      align-items: center;
      // flex-direction: row;
    }
  }
}
</style>
