<template>
  <div class="review-content">
    <div class="review-top">
      <div class="top-title">热门影评</div>
      <el-radio-group v-model="currentType" @change="changeType">
      <el-radio label="欢迎">最受欢迎的</el-radio>
      <el-radio label="最新">最新影评</el-radio>
      </el-radio-group>
    </div>
    <div class="review-center"  v-loading="isLoading">
        <review-slot class="review-info"  v-for="(review,index) in reviews" :key="index" :review= review></review-slot>
    </div>
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
import ReviewSlot from '@/components/reviewSlot'
import {getAllReviews} from '@/js/api'
export default {
  components: {
    ReviewSlot,
  },
  data () {
    return {
      currentType: '欢迎',
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
        this.reviews = res.allReviews
        this.number = res.number
        this.isLoading = false
        // console.log(1)
      })
    }
  }
}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';
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
