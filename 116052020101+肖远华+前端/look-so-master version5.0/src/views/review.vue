<template>
  <div class="review">
    
    <div class="review-left">
      <review-content></review-content>
    </div>
  </div>
</template>

<script>
import ReviewContent from './review/reviewContent'
export default {
  components: {
    ReviewContent
  },
  data () {
    return {
    }
  }
}

</script>
<style lang="less" scoped>
.review {
  
  .review-left {
    // margin-top:20px;
    margin: 20px auto;
    width: 75%;
  }
}

.el-radio-group{
  margin-top:20px;
}
.review-top {
        margin-left:126px;
        .top-title {
            margin: 12px 0 12px 0;
            font-size: 20px;
        }
    }
</style>
