<template>
<div class="el-tabs--border-card">
  <div class="review-slot" ref="reviewSlot">
    <div class="review-left">
      <movie-slot v-show="isDetail">
        <div class="cover-wrap">
            <img class="movie-cover" :src="review.movie.cover_url" alt="" @click="_goMovieDetail(review.comment.movieId)">
        </div>
        <span class="movie-name" @click="_goMovieDetail(review.comment.movieId)">{{review.comment.movieName}}</span>
      </movie-slot>
    </div>
    <div class="review-right">
      <div class="top">
        <img class="head" :src="review.comment.photoUrl" alt="">
        <span class="nick">{{review.comment.commentUser}}</span>
        <span class="score">
          <el-rate
            v-model="score"
            disabled>
          </el-rate>
          <span class="score-num">{{review.comment.rating}}</span>
        </span>
        <!-- <span class="level">{{review.userLevel}}</span> -->
        <span class="time">{{review.comment.time}}</span>
        <div class="zan">
          <img class="zan-icon" src="/static/img/zan.png" alt="" v-show="false" @click="userZan">
          <img class="zan-icon" src="/static/img/zan2.png" alt="" v-show="false" @click="userZan">
          <span class="zan-num">{{review.comment.attitude}}</span>
      </div>
      </div>
      <div class="content" ref="content">{{contentBrief}}</div>
      <!-- <span class="open" @click="showAllCont(review.reviewCont)">{{openInfo}}</span> -->
    </div>
  </div>
</div>
</template>

<script>
import MovieSlot from './movieSlot'
import {goMovieDetail} from '@/js/router'
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
    review: {
      type: Object,
      default: null
    }
  },
  created () {
    // console.log(this.review)
  },
  computed: {
    score () {
      let r = this.review.comment.rating;
      let rate = 8; //
      if (r !== "['comment-time']") {
        rate = ((r.replace(/[^0-9]/gi, "") / 10) * 2) ; //将rating转换为对应评分
      }

      this.review.comment.rating = rate;
      return rate / 2;
    },
    contentBrief () {
      // return this.review.reviewCont.substring(0, 220) + '...'
      return this.review.comment.commentContent
    },
    isDetail () {
      if (this.review.comment.movieId) {
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
.el-tabs--border-card{
  margin-bottom: 20px;
}
.review-slot {
  display: flex;
  padding: 15px;
  flex-direction: row;
  /*.review-left {

  }*/
  .review-right {
    display: flex;
    flex-direction: column;
    margin-left: 20px;
    .top {
      display: flex;
      align-items: center;
      .nick {
        margin-right: 12px;
        .all-font(PingFangSC-Regular, 14px, #409EFF, -0.34px);
      }
      .head {
        margin-right: 12px;
        width: 24px;
        height: 24px;
        border-radius: 24px;
      }
      .score {
        display: flex;
        align-items: center;
        margin-right: 12px;
        .score-num {
          .all-font(Microsoft YaHei, 14px, #F7BA2A, 0)
        }
      }
      .level {
        margin-right: 12px;
        .all-font(PingFangSC-Regular, 14px, #333333, -0.34px);
      }
      .time {
        margin-right: 12px;
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
      float: right;
      .all-font(PingFangSC-Regular, 14px, #409EFF, 0.50px);
      &:hover {
        color: #97B1FF;
      }
    }
  }
}
</style>
