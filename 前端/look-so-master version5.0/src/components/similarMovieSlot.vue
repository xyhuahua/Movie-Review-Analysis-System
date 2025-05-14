<template>
  <div class="similarMovie-slot" ref="similarMovieSlot">
    <div class="left">
      <movie-slot >
        <div class="cover-wrap">
            <img class="movie-cover" :src="movies.cover_url" alt=""  @click="_goMovieDetail(movies.movieId)">
        </div>
      </movie-slot>
    </div>
    <div class="right">
      <span class="content" >{{movies.title}}</span>
      <span class="content" style="margin-top:10px;">上映时间：{{movies.release_date}}</span>
      <el-divider class="el-divider" style="margin-top:85px;"></el-divider>
    </div>
  </div>
</template>

<script>
import MovieSlot from './movieSlot'
import { goMovieDetail } from "@/js/router";
export default {
  components: {
    MovieSlot
  },
  data () {
    return {
      
      isOpen: false,
      openInfo: '展开'
    }
  },
  props: {
    movies: {
      type: Object,
      default: null
    }
  },
  created () {
    // console.log(this.movies.movieId)
  },
  computed: {
    score () {
      return this.review.reviewScore / 2
    },
    contentBrief () {
      return this.review.reviewCont.substring(0, 220) + '...'
    },
    isDetail () {
      if (this.movies.movieCover) {
        return true
      } else {
        return false
      }
    }
  },
  methods: {
    userZan () {

    },
    showAllCont (content) {
      if (this.isOpen) {
        this.$refs.content.innerHTML = this.contentBrief
        this.openInfo = '展开'
        this.isOpen = false
      } else {
        let that = this
        let str = content.split('\n')
        this.$refs.content.innerHTML = ''
        this.openInfo = '收起'
        this.isOpen = true
        for (let i = 0; i < str.length; i++) {
          that.$refs.content.innerHTML += str[i] + '<br>'
        }
      }
    },
    _goMovieDetail (movieId) {
      goMovieDetail(movieId)
    }
  }
}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';
.similarMovie-slot {
  display: flex;
  margin-bottom: 20px;
  .left {

  }
  .right {
    display: flex;
    flex-direction: column;
    margin-left: 20px;
    .top {
      display: flex;
      align-items: center;
      .nick {
        margin-right: 20px;
        .all-font(PingFangSC-Regular, 14px, #409EFF, -0.34px);
      }
      .head {
        margin-right: 20px;
        width: 24px;
        height: 24px;
        border-radius: 24px;
      }
      .score {
        display: flex;
        align-items: center;
        margin-right: 20px;
        .score-num {
          .all-font(Microsoft YaHei, 14px, #F7BA2A, 0)
        }
      }
      .level {
        margin-right: 20px;
        .all-font(PingFangSC-Regular, 14px, #333333, -0.34px);
      }
      .time {
        margin-right: 20px;
        .all-font(PingFangSC-Regular, 12px, #999999, -0.34px);
      }
      .zan {
        display: flex;
        align-items: center;
        .zan-icon {
          width: 24px;
          height: 24px;
          cursor: pointer;
        }
        .zan-num {
          .all-font(PingFangSC-Regular, 12px, #999999, -0.34px);
        }
      }
    }
    .content {
      margin-top: 12px;
      line-height: 20px;
      .all-font(PingFangSC-Regular, 14px, #333333, 0.50px);
    }
    .open {
      cursor: pointer;
      align-self: flex-end;
      .all-font(PingFangSC-Regular, 14px, #409EFF, 0.50px);
      &:hover {
        color: #97B1FF;
      }
    }
    .el-divider{
      margin: 8px 0;
      background: 0 0;
      border-top: 1px solid #E6EBF5;
  }
  }
}

</style>
