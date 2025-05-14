<template>
  <div class="review-content">
    <div class="review-top">
      <!-- <div class="top-title">热门影评</div> -->
      <el-radio-group v-model="currentType" @change="changeType">
      <el-radio label="ifUse" border checked>最受欢迎的</el-radio>
      <el-radio label="time" border>最新影评</el-radio>
      </el-radio-group>
    </div>
    <el-card class="reviews-wrapper" v-loading="isLoading">
        <div class="card-body">
          <div class="card-commend" v-for="(review,index) in reviews" :key=index >
          <allReview-slot :review="review" ></allReview-slot>
          <!-- <review-slot :review="review" ></review-slot>
          <review-slot :review="review" ></review-slot>  -->
        </div>
        </div>
    </el-card>
    <div class="review-footer">
      <el-pagination
        background
        layout="prev, pager, next"
        :page-size="pageSize"
        :total="number"
        :current-page="currentPage"
        @current-change="currentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {getAllReviews} from '@/js/api'
// import {goReview} from '@/js/router'
import AllReviewSlot from '@/components/allReviewSlot'
export default {
  components: {
    AllReviewSlot
  },
  data () {
    return {
      currentType: 'ifUse',
      reviews: [],
      currentPage: 1,
      pageSize: 10,
      number: 0,
      isLoading: true
    }
  },
  created () {
    this._getAllReview()
  },
  methods: {
    changeType (type) {
      this.currentType = type
      this.currentPage = 1
      this._getAllReview()
    },
    currentChange (page) {
      this.currentPage = page
      this._getAllReview()
    },
    _getAllReview () {
      this.isLoading = true
      getAllReviews(this.currentType, this.currentPage).then((res) => {
        this.reviews = res
        this.number = 100
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
.card-body{
  flex-wrap: wrap;
  display: flex;
  flex-direction: column;
  height: 3270px;
  // align-items:center;
}
.review-content {
    .reviews-wrapper {
      margin-top:20px;
      // width:1190px;
      // margin:0 auto; 
      .card-commend{
        // max-height:auto;
        // margin-top:10px;
        width: 47%;
        padding-left: 22px;
      }
        .reviews-header {
            .title {
                .all-font(MicrosoftYaHei-Bold, 18px, #409EFF, -0.43px);
            }
        }
    }
}

.el-radio-group{
  margin-top:20px;
}
.review-content {
  position:relative;
  left:-76px;
    .review-top {
        .top-title {
            margin: 12px 0 12px 0;
            .bold-title;
        }
    }
    .review-center {
        width:1250px;
        margin-top: 20px;
        display:flex;
        flex-direction: row;
        flex-wrap:wrap;
        justify-content:center;
        // margin-top:12px;
        .review-info{
          width:47%;
          height: auto;
          margin:0 30px 12px 0;
        }
      
    }
    .review-footer {
        display: flex;
        justify-content: center;
        margin-bottom: 100px;
        margin-top:20px;
    }
    
}
</style>
