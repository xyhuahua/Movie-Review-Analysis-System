<template>
  <div class="recommend-movies">
    <el-tabs type="border-card" @tab-click="isMore" v-loading="isLoading">
        <el-tab-pane v-for="(movie,index) in recommendMovies" :key="index" :label="movie.sort" lazy>
            <div class="info-wrapper">
                <movie-slot class="movie-info" v-for="(info,index) in movies.slice(0,5)" :key="index">
                    <div class="cover-wrap">
                        <img class="movie-cover" :src="info.cover_url" alt="" @click="_goMovieDetail(info.movieId)">
                    </div>
                    <span class="movie-name" @click="_goMovieDetail(info.movieId)">{{info.title}}</span>
                    <span class="movie-score">{{info.score}}</span>
                </movie-slot>
            </div>

        </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import MovieSlot from '@/components/movieSlot'
import {getSortMovies} from '@/js/api'
import {getRecommendMovies} from '@/js/api'
import {goMovie, goMovieDetail} from '@/js/router'
const MORE_INDEX = 'tab-7'
export default {
  components: {
    MovieSlot
  },
  data () {
    return {
      recommendMovies: [
        {
          sort: '热门',
          info: []
        },
        {
          sort: '动画',
          info: []
        },
        {
          sort: '喜剧',
          info: []
        },
        {
          sort: '科幻',
          info: []
        },
        {
          sort: '悬疑',
          info: []
        },
        {
          sort: '爱情',
          info: []
        },
        {
          sort: '动作',
          info: []
        },
        {
          sort: '更多',
          info: []
        }
      ],
      currentSort: '所有',
      currentPage: 1,
      currentSort_type: '全部',
      currentSort_country: '全部',
      currentSort_time: '全部',
      currentSort_hot: '最热',
      isLoading: true

    }
  },
  created () {
    this._getRecommendMovies()
  },
  methods: {
    isMore (tab, event) {
      console.log(tab.label);
      if(tab.label == '热门'){
        this.currentSort_type = '全部';
      }else{
        this.currentSort_type = tab.label;
      }

      if (event.target.getAttribute('id') === MORE_INDEX) {
        goMovie()
      }else{
        this._getRecommendMovies()
      }
    },
    _getRecommendMovies () {
      // getRecommendMovies().then((res) => {
      //   let inner = this.recommendMovies
      //   let out = res.recommendMovies
      //   // console.log(inner)
      //   for (let i = 0; i < inner.length; i++) {
      //     for (let j = 0; j < out.length; j++) {
      //       if (inner[i].sort === out[j].movieSort) {
      //         inner[i].info.push(out[j])
      //       }
      //     }
      //   }
      //   this.isLoading = false
      //   // console.log(this.recommendMovies)
      // })
      this.isLoading = true
      getSortMovies(this.currentSort, this.currentPage, this.radio,this.currentSort_type, this.currentSort_country, this.currentSort_time, this.currentSort_hot).then((res) => {
        this.movies = res.movies
        this.number = res.count
        this.isLoading = false
        console.log(this.movies)
      })
    },
    _goMovieDetail (movieId) {
      goMovieDetail(movieId)
    }
  }
}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';

.recommend-movies {
    .info-wrapper {
        display: flex;
        justify-content: space-around;
        align-items: center;
    }
    .el-tabs--border-card>.el-tabs__header{
        background-color: #f5f7fa00;
    }
}

</style>
