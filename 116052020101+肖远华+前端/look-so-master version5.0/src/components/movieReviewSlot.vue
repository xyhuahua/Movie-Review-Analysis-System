<template>
  <div class="movieReview-slot" ref="movieReviewSlot">
    <div class="review-left">
      <user-slot>
        <div class="cover-wrap">
            <img class="user-head" :src="review.photoUrl" alt="" >
        </div>
      </user-slot>
    </div>
    <div class="review-right">
      <div class="top">
        <span class="nick">{{review.commentUser}}</span>
        <span class="ifSee">{{review.ifSee}}</span>&nbsp;
        <span class="score">
          <el-rate
            v-model="score"
            disabled>
          </el-rate>
          <span class="score-num">{{review.rating}}</span>
        </span>
        <span class="time">{{review.time}}</span>
        <div class="zan">
          <img class="zan-icon" src="/static/img/zan.png" alt="" v-show="review.isZan" @click="userZan">
          <img class="zan-icon" src="/static/img/zan2.png" alt="" v-show="review.isZan === false" @click="userZan">
          <span class="zan-num">{{review.ifUse}}</span>
        </div>
      </div>
      <div class="content" ref="content">{{review.commentContent}}</div>
      <span class="open" @click="showAllCont(review.reviewCont)">{{openInfo}}</span>
      <el-divider class="el-divider"></el-divider>
    </div>
  </div>
</template>

<script>
import UserSlot from './userSlot'
import {goMovieDetail} from '@/js/router'
export default {
  components: {
    UserSlot
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
    // console.log(3)
    //  console.log(this.review)
  },
  computed: {
    score () {
      let r = this.review.rating;
      let rate = 8; //
      if (r !== "['comment-time']") {
        rate = ((r.replace(/[^0-9]/gi, "") / 10) * 2) ; //将rating转换为对应评分
      }
      this.review.rating = rate;
      return rate / 2
    },
    contentBrief () {
      return this.review.reviewCont.substring(0, 220) + '...'
    },
    isDetail () {
      if (this.review.userHead) {
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
  },
  
}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';
.movieReview-slot {
  display: flex;
  margin-bottom: 20px;
  .review-left {

  }
  .review-right {
    display: flex;
    flex-direction: column;
    margin-left: 20px;
    width: 100%;
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
