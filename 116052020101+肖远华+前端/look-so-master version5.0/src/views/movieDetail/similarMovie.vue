<template>
  <div class="similar-movie">
    <el-card class="box-card" >
        <div slot="header" class="clearfix">
            <span class="similarMovie-title">相似电影推荐</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="_goRank">更多</el-button>
        </div>
        <!-- <div v-for="(movies,index) in similarMovies" :key=index >
          <similarMovie-slot :movies="movies"></similarMovie-slot>
        </div> -->
        <div v-for="(movies,index) in similarMovies.slice(0,15)" :key="index" class="rank-item" @click="_goMovieDetail(item.movieId)" >
            <similarMovie-slot :movies="movies"></similarMovie-slot>
        </div>
    </el-card>
  </div>
</template>

<script>
import {getSimilarMovies} from '@/js/api'
import {goMovieDetail, goRank} from '@/js/router'
import similarMovieSlot from '@/components/similarMovieSlot'
export default {
  components: {
    similarMovieSlot
  },
  data () {
    return {
      similarMovies: [],
      isLoading: false
    }
  },
  created () {
    
    
  },
  methods: {
    _goRank () {
      goRank()
    },
    _goMovieDetail (movieId) {
      goMovieDetail(movieId)
    },
    
  },
  props: {
    similarMovies: {
      type: Array,
      default: null
    }
  },
}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';
    .similar-movie {
        .box-card {
            .clearfix {
                .similarMovie-title {
                    .all-font(MicrosoftYaHei-Bold, 18px, #005198, -0.43px);
                }
            }
        }
    }
    .el-divider{
          width: 20px;
          margin: 8px 0;
          background: 0 0;
          border-top: 1px solid #E6EBF5;
        }
</style>
