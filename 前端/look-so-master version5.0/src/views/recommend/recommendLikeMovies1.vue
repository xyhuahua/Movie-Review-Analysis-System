<template>
  <div class="recommend-movies">
    <el-card class="reviews-wrapper box-card" v-loading="isLoading">
        <div slot="header" class="reviews-header">
            <span class="title">猜你喜欢</span>
            <el-button style="float: right; padding: 3px 0" type="text"  @click="_goReview">更多</el-button>
        </div>
        <div class="card-body">
          <div class="info-wrapper">
                <div data-v-e2f21ee4="" data-v-fd87590a="" class="movie-slot movie-info"><div data-v-fd87590a="" data-v-e2f21ee4="" class="cover-wrap"><img data-v-fd87590a="" data-v-e2f21ee4="" src="/static/cover/cover1.png" alt="" class="movie-cover"></div> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-name">我不是药神</span> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-score">10</span></div>
                <div data-v-e2f21ee4="" data-v-fd87590a="" class="movie-slot movie-info"><div data-v-fd87590a="" data-v-e2f21ee4="" class="cover-wrap"><img data-v-fd87590a="" data-v-e2f21ee4="" src="/static/cover/cover1.png" alt="" class="movie-cover"></div> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-name">我不是药神</span> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-score">10</span></div>
                <div data-v-e2f21ee4="" data-v-fd87590a="" class="movie-slot movie-info"><div data-v-fd87590a="" data-v-e2f21ee4="" class="cover-wrap"><img data-v-fd87590a="" data-v-e2f21ee4="" src="/static/cover/cover1.png" alt="" class="movie-cover"></div> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-name">我不是药神</span> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-score">10</span></div>
                <div data-v-e2f21ee4="" data-v-fd87590a="" class="movie-slot movie-info"><div data-v-fd87590a="" data-v-e2f21ee4="" class="cover-wrap"><img data-v-fd87590a="" data-v-e2f21ee4="" src="/static/cover/cover1.png" alt="" class="movie-cover"></div> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-name">我不是药神</span> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-score">10</span></div>
                <div data-v-e2f21ee4="" data-v-fd87590a="" class="movie-slot movie-info"><div data-v-fd87590a="" data-v-e2f21ee4="" class="cover-wrap"><img data-v-fd87590a="" data-v-e2f21ee4="" src="/static/cover/cover1.png" alt="" class="movie-cover"></div> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-name">我不是药神</span> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-score">10</span></div>
                <div data-v-e2f21ee4="" data-v-fd87590a="" class="movie-slot movie-info"><div data-v-fd87590a="" data-v-e2f21ee4="" class="cover-wrap"><img data-v-fd87590a="" data-v-e2f21ee4="" src="/static/cover/cover1.png" alt="" class="movie-cover"></div> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-name">我不是药神</span> <span data-v-fd87590a="" data-v-e2f21ee4="" class="movie-score">10</span></div>
            </div>
        </div>
    </el-card>
  </div>
</template>

<script>
import {getRecommendReviews} from '@/js/api'
import {goReview} from '@/js/router'
import ReviewSlot from '@/components/reviewSlot'

export default {
  components: {
    ReviewSlot
  },
  data () {
    return {
      recommendReviews: [],
      isLoading: true
    }
  },
  created () {
    this._getRecommendReviews()
  },
  methods: {
    _getRecommendReviews () {
      getRecommendReviews().then((res) => {
        this.recommendReviews = res.recommendReviews
        this.isLoading = false
        // console.log(res.recommendReviews)
      })
    },
    _goReview () {
      goReview()
    }
  }
}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';
.box-card{
  width:718px;
}
/deep/.el-card__header{
  padding: 8px 20px;
}
/deep/.el-card__body{
  padding: 0px;
}
.info-wrapper{
  display: flex;
  flex-direction: row;
}
.movie-slot {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 115px;
            padding-left: 1px;
            // margin-right: 20px;
            .cover-wrap {
                height: 162px;
                .movie-cover {
                    width: 100%;
                    height: 100%;
                }
            }
            .movie-name {
                margin-top: 12px;
                .all-font(PingFangSC-Regular, 14px, #333333, -0.34px);
                cursor: pointer;
                &:hover {
                    color: #409EFF;
                }
            }
            .movie-score {
                margin-top: 8px;
                .all-font(PingFangSC-Regular, 12px, #EF990F, -0.29px);
            }
        }
.card-body{
  padding: 15px;
  display: flex;
  flex-direction: row;
}
.recommend-reviews {
    .reviews-wrapper {
      // width:100px;
      .card-commend{
        width: 47%;
        padding-left: 30px;
      }
    }
}
.title {
      .all-font(MicrosoftYaHei-Bold, 18px, #409EFF, -0.43px);
}
</style>
