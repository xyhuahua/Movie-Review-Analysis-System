<template>
  <div class="magnumOpus">
    <el-card class="box-card"
             type="border-card"
             @tab-click="isMore"
             v-loading="isLoading">
      <div slot="header"
           class="clearfix">
        <span class="magnumOpus-title">最受好评</span>
        <el-button style="float: right; padding: 3px 0" type="text" >换一换</el-button>
      </div>
      <div class="info-wrapper"
           @click="_goMovieDetail(item.movieId)">
        <magnumOpus-slot class="magnumOpus-info"
                    v-for="(item,index) in goodMovies"
                    :key="index">
          <div class="cover-wrap">
            <img class="magnumOpus-head"
                 :src="item.cover_url"
                 alt=""
                 @click="_goMovieDetail(item.movieId)">
          </div>
          <span class="magnumOpus-nick"
                @click="_goMovieDetail(item.title)">{{item.title}}</span>
        </magnumOpus-slot>
      </div>
    </el-card>
  </div>
</template>

<script>
import magnumOpusSlot from '@/components/magnumOpusSlot'
import { getGoodMovies } from '@/js/api'
import { goMovie, goMovieDetail } from '@/js/router'
const MORE_INDEX = 'tab-7'
export default {
  components: {
    magnumOpusSlot
  },
  data () {
    return {
      actroId:'',
      isLoading: true
    }
  },
  props: {
    goodMovies: Array,
    default:[]
  },
  created () {
    this.actroId = localStorage.getItem("actorId")
    this._getGoodMoives(this.actroId)
  },
  methods: {
    isMore (tab, event) {
      if (event.target.getAttribute('id') === MORE_INDEX) {
        goMovie()
      }
    },
    _getGoodMoives (actroId) {
      getGoodMovies(actroId).then((res) => {
        console.log(actroId)
        this.goodMovies = res
        this.isLoading = false
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
.magnumOpus {
  .box-card {
    .clearfix {
      .magnumOpus-title {
        .all-font(MicrosoftYaHei-Bold, 18px, #005198, -0.43px);
      }
    }
    .info-wrapper {
      display: flex;
      justify-content: space-around;
      align-items: center;
    }
  }
}
</style>
