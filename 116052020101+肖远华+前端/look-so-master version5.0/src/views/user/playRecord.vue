<template>
  <div class="play-record">
    <div class="block">
  <el-timeline v-loading="isLoading">
    <el-timeline-item timestamp="最近一周" placement="top">
      <div class="record">
        <movie-slot class="movie-info" v-for="(info,index) in movies1" :key="index">
          <div class="cover-wrap">
              <img class="movie-cover" :src="info.imageUrl" alt="" @click="_goMovieDetail(info.movieId)">
          </div>
          <span class="movie-name" @click="_goMovieDetail(info.movieId)">{{info.movieName}}</span>
          <span class="movie-score">{{info.dateTime}}</span>
        </movie-slot>
      </div>
    </el-timeline-item>
    <el-timeline-item timestamp="最近一个月" placement="top">
      <div class="record">
        <movie-slot class="movie-info" v-for="(info,index) in movies2" :key="index">
          <div class="cover-wrap">
              <img class="movie-cover" :src="info.imageUrl" alt="" @click="_goMovieDetail(info.movieId)">
          </div>
          <span class="movie-name" @click="_goMovieDetail(info.movieId)">{{info.movieName}}</span>
          <span class="movie-score">{{info.dateTime}}</span>
        </movie-slot>
      </div>
    </el-timeline-item>
    <el-timeline-item timestamp="更早" placement="top">
      <div class="record" v-loading="isLoading">
        <movie-slot class="movie-info" v-for="(info,index) in movies3" :key="index">
          <div class="cover-wrap">
              <img class="movie-cover" :src="info.imageUrl" alt="" @click="_goMovieDetail(info.movieId)">
          </div>
          <span class="movie-name" @click="_goMovieDetail(info.movieId)">{{info.movieName}}</span>
          <span class="movie-score">{{info.dateTime}}</span>
        </movie-slot>
      </div>
    </el-timeline-item>
  </el-timeline>
</div>
  </div>
</template>

<script>
import MovieSlot from '@/components/movieSlot'
import { goMovieDetail } from "@/js/router";
import {getRecordMovies} from '@/js/api'
export default {
  components: {
    MovieSlot
  },
  data () {
    return {
      movies1: [],
      movies2: [],
      movies3: [],
      currentSort: '所有',
      currentPage: 1,
      number: 0,
      pageSize: 20,
      username : sessionStorage.getItem("username"),
      isLoading: true
    }
  },
  created () {
    this._getRecordMovies()
  },
  mounted () {
  },
  methods: {
    _getRecordMovies () {
      this.isLoading = true
      getRecordMovies(this.username, 1).then((res) => {
        this.movies1 = res.movies
      })
      getRecordMovies(this.username, 2).then((res) => {
        this.movies2 = res.movies
      })
      getRecordMovies(this.username, 3).then((res) => {
        console.log(res);
        this.movies3 = res.movies
        this.isLoading = false
      })
      
    },
    _goMovieDetail(movieId) {
      goMovieDetail(movieId);
    }
  },
  computed: {

  }
}

</script>
<style lang="less" scoped>
.play-record {
  margin-top:20px;
  width:80%;
  margin-left:34px;
  .record {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    margin-top: 12px;
    .movie-info {
      margin: 0 30px 12px 0;
    }
  }
  .movie-footer {
    display: flex;
    justify-content: center;
    margin-bottom: 100px;
  }
}
</style>
