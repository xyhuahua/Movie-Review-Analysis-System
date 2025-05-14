<template>
  <div class="recommend-reviews">
    <el-card class="reviews-wrapper" v-loading="isLoading">
        <div slot="header" class="reviews-header">
            <span class="title">热门影评</span>
            <el-button style="float: right; padding: 3px 0" type="text"  @click="_goReview">更多</el-button>
        </div>
        <div class="card-body">
          <div class="card-commend" v-for="(review,index) in recommendReviews" :key=index >
          <review-slot :review="review" ></review-slot>
          <!-- <review-slot :review="review" ></review-slot>
          <review-slot :review="review" ></review-slot>  -->
        </div>
        </div>
    </el-card>
  </div>
</template>

<script>
import { getHomepageComments } from "@/js/api";
import { goReview } from "@/js/router";
import ReviewSlot from "@/components/reviewSlot";
export default {
  components: {
    ReviewSlot
  },
  data() {
    return {
      recommendReviews: [],
      isLoading: true,
      currentType: "ifUse",
      currentPage: 1
    };
  },
  created() {
    this._getAllReview();
  },
  methods: {
    _getAllReview() {
      this.isLoading = true;
      getHomepageComments(this.currentType, this.currentPage).then(res => {
        this.recommendReviews = res;
        console.log(res)
        this.isLoading = false;
        console.log(this.recommendReviews);
      });
    },
    _goReview() {
      goReview();
    }
  }
};
</script>
<style lang="less" scoped>
@import "~@/style/font.less";
.card-body {
  flex-wrap: wrap;
  display: flex;
  flex-direction: row;
}
.recommend-reviews {
  .reviews-wrapper {
    .card-commend {
      width: 47%;
      padding-left: 30px;
    }
    .reviews-header {
      .title {
        .all-font(MicrosoftYaHei-Bold, 18px, #409eff, -0.43px);
      }
    }
  }
}
</style>
