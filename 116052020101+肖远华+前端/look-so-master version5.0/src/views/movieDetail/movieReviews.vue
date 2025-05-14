<template>
  <div class="movie-reviews" >
    <el-card class="reviews-wrapper" v-loading="isLoading">
        <div slot="header" class="reviews-header">
            <a @click="changehot()"  style="text-decoration: none"><span class="title">热门评论 | </span></a><a @click="changenew()"  style="text-decoration: none" ><span class="title">最新评论</span></a>
            <el-button icon="el-icon-s-comment" type="text" style="float: right;padding:3px 0;position:relative;right:66px" @click="dialogVisible = true">我来写评论</el-button>
            <el-button style="float: right; padding: 3px 0;margin-right: -83px;" type="text"  @click="_goReview">更多</el-button>
        </div>
        <div v-show="ishotReviews" v-for="(review,index) in hotReviews" :key=index >
          <movieReview-slot :review="review"></movieReview-slot>
        </div>
        <div v-show="isnewReviews" v-for="(review,index) in newReviews" :key=index >
          <movieReview-slot :review="review"></movieReview-slot>
        </div>
        <div class="movie-footer">
        <el-pagination
          background
          layout="prev, pager, next"
          :page-size="pageSize"
          :total="number"
          :current-page="currentPage"
          @current-change="currentChange">
        </el-pagination>
      </div>
    </el-card>
    <el-dialog
  title="评论"
  :visible.sync="dialogVisible"
  width="45%"
  height="45%"
  :before-close="handleClose">
  <div class="write-review">
      <el-rate
        v-model="reviewScore"
        show-text>
      </el-rate>
      <el-input v-model="reviewCont" type="textarea" :rows="5" placeholder="请输入评论内容..." resize="none"></el-input>
  </div>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="wrightReview();dialogVisible = false">确 定</el-button>
  </span>
</el-dialog>
  </div>
</template>

<script>
import bus from '@/js/bus'
import {getMovieReviews} from '@/js/api'
import {goReview} from '@/js/router'
import movieReviewSlot from '@/components/movieReviewSlot'
export default {
  components: {
    movieReviewSlot
  },
  data () {
    return {
      currentPage: 1,
      pageSize: 10,
      number: 100,
      hotReviews: [],
      newReviews: [],
      isLoading: true,
      dialogVisible: false,
      reviewScore: '',
      reviewCont: '',
      ishotReviews: true,
      isnewReviews: false
    }
  },
   props: {
    
    hotReviews:{
      type:Array,
      default:null
    }
  },
  created () {
    this._getMovieReviews()
  },
  methods: {
    changehot(){
      console.log("------------------")
      this.ishotReviews = true
      this.isnewReviews = false
    },
    changenew(){
      console.log("------------------")
      this.ishotReviews = false
      this.isnewReviews = true
    },
    wrightReview(){
      console.log(this.reviewScore)
      console.log(this.reviewCont)
      console.log(this.hotReviews[0].movieName)
      this.$axios.get('http://localhost:8080/wrightReview',{
      params: {
        movieId: this.movieId,
        movieName: this.hotReviews[0].movieName,
        commentUser: sessionStorage.getItem("username"),
        photoUrl: "https://img1.doubanio.com/icon/u103184704-9.jpg",
        reviewScore: this.reviewScore,
        reviewCont: this.reviewCont
      }
      }).then((res) => {
           return Promise.resolve(res.data)
      })
      location.reload();
    },
    currentChange (page) {
      this.currentPage = page
      this._getMovieReviews()
    },
    handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
    showWriteReview () {
      bus.$emit('showWriteReview', true)
    },
    _getMovieReviews () {
      getMovieReviews(this.currentPage,this.movieId).then((res) => {
        console.log("res.hotReviews--------------------------")
        console.log(res.data.hotReviews)
        console.log(res)
        this.hotReviews = res.data.hotReviews
        this.newReviews = res.data.newReviews
        this.number = res.data.count
        this.isLoading = false
      })
    },
    _goReview () {
      goReview()
    }
  },
  computed: {
    movieId () {
      return JSON.parse(localStorage.getItem('currentMovie'))
    }
  }
}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';
.movie-reviews {
    .reviews-wrapper {
        .reviews-header {
            .title {
                .all-font(MicrosoftYaHei-Bold, 18px, #409EFF, -0.43px);
            }
        }
    }
}

</style>
