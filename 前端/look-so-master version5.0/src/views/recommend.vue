<template>
  <div class="recommend">
    <div class="content-top">
      <div class="center-body">
        <div class="center-bodytop">
          <recommend-slider class="recommend-slider"></recommend-slider>
        </div>
        <div class="center-bodycenter">
          <div style="width:100%">
          <recommend-movies class="recommend-movies"></recommend-movies>
          <RecommendLikeMovies class="like-movies"
                               id="Like-movies"></RecommendLikeMovies>
          </div>
          
        </div>
        <div class="center-bodybottom">
          <recommend-reviews class="recommend-reviews"></recommend-reviews>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import RecommendSlider from '@/views/recommend/recommendSlider'
import RecommendMovies from '@/views/recommend/recommendMovies'
import RecommendLikeMovies from '@/views/recommend/recommendLikeMovies'
import RecommendReviews from '@/views/recommend/recommendReviews'
export default {
  components: {
    RecommendSlider,
    // RecommendRank,
    RecommendMovies,
    RecommendLikeMovies,
    RecommendReviews
  },
  data () {
    return {
    }
  }
}

</script>
<style lang="less" scoped>
.recommend {
  display: flex;
  flex-direction: column;
  // width: 100%;
  // margin: 0;
  // width: 1200px;
  // margin: 0 auto;
  .content-top {
    display: flex;
    width: 100%;
    margin-top: 20px;
    .center-body {
      .recommend-slider {
        width: 1334px;
        text-align: center;
      }
      .center-bodytop {
        display: flex;
        justify-content: center;
        align-items: center;
      }
      
      .center-bodycenter {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        // width: 94%;
        // justify-content: center;
        .like-movies .recommend-movies{
        // width:45%;
        display: inline;
      }
        .recommend-movies {
          // position: relative;
          // display:flex;
          float:left;
          margin-left: 51px;
          width: 46%;
          // margin-top: 20px;
        }
        .like-movies {
          // position: relative;
          // right: -23px;
          // top:8px;
          width: 42%;
          margin-right:118px;
          float:right;
        }
      }

      .center-bodybottom {
        display: flex;
        justify-content: center;
        align-items: center;
        .recommend-reviews {
          width: 94%;
          margin-top: 20px;
        }
      }
    }
    .right {
      width: 28%;
      margin-left: auto;
    }
  }
}
</style>
