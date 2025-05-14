<template>
  <div class="search-result">
    <div class="result-left">
      <div class="result-title"><h2>搜索结果</h2></div>
      <div class="result-content">
        <div
          class="result-item"
          v-for="(result, index) in resultInfo"
          :key="index"
        >
          <div class="cover-wrap">
            <img
              class="cover"
              :src="result.cover_url"
              alt=""
              @click="_goMovieDetail(result.movieId)"
            />
          </div>
          <div class="movie-info" style="width:1000px">
            <div class="score">
              <el-rate
                class="score-icon"
                v-model="scoreIcon[index]"
                allow-half
                disabled
                text-color="#ff9900"
                score-template="{value}"
              >
              </el-rate>
              <div class="score-num">{{ result.score }}</div>
            </div>
            <div class="name" @click="_goMovieDetail(result.movieId)">
              {{ result.title }}
            </div>
            <div style="width:800px">
              <danmu v-bind:movieId="result.movieId"></danmu>
              </div>
            
          </div>
        </div>
            <div class="result-footer">
        <el-pagination
          class="page"
          background
          layout="prev, pager, next"
          :page-size="pageSize"
          :total="number"
          :current-page="currentPage"
          @current-change="currentChange"
        >
        </el-pagination>
      </div>  
    </div>
  </div>

      
    </div>
  </div>
</template>

<script>
import { goMovieDetail } from "@/js/router";

import danmu from "./danmu/danmu.vue";
export default {
  components: { danmu },
  data() {
    return {
      resultInfo: JSON.parse(localStorage.getItem("searchResult")).data,
      pageSize: 10,
      number: JSON.parse(localStorage.getItem("searchResult")).number,
      currentPage: 1,
      
    };
  },
  created() {
    console.log("resultInfo");
    console.log(this.resultInfo);
    // console.log(this.scoreIcon)
    // console.log(this.number)
  },
  mounted(){
    //this.addToList();
    this.nowpage()
  },
  methods: {
    nowpage(){
      if(localStorage.getItem("searchPage")!=null){
        this.currentPage = localStorage.getItem("searchPage")
      }
    },
    currentChange(page) {
      this.currentPage = page;
      localStorage.setItem("searchPage", JSON.stringify(this.currentPage));
      // console.log(this.currentPage)
      
      this.$axios
        .post("http://localhost:8080/search", {
          page: this.currentPage,
          searchContent: localStorage.getItem("searchContent"),
        })
        .then((res) => {
          if (res.status === 200) {
            console.log(res);
            this.$router.push("searchResult");
            localStorage.setItem("searchResult", JSON.stringify(res.data));
            location.reload();
          }
          
        });
    },

    _goMovieDetail(movieId) {
      goMovieDetail(movieId);
    },
   
    
  },
  computed: {
    scoreIcon() {
      let temp = JSON.parse(localStorage.getItem("searchResult")).data;
      let arr = [];
      for (let i = 0; i < temp.length; i++) {
        arr.push(temp[i].score / 2);
      }
      //   console.log(this.resultInfo.lenth)
      //   for (let i = 0; i < this.resultInfo.lenth; i++) {
      //     console.log(this.resultInfo[i].movieScore)
      //     arr.push(this.resultInfo[i].movieScore)
      //   }
      return arr;
    },
  },
};
</script>
<style lang="less" scoped>
@import "~@/style/font.less";
.search-result {
  .result-left {
    margin: 0 auto;
    width: 70%;
    .result-title {
      margin: 25px 0;
      .bold-title;
    }
    .result-content {
      .result-item {
        display: flex;
        align-items: flex-start;
        margin-top: 12px;
        .cover-wrap {
          width: 112px;
          height: 162px;
          .cover {
            width: 100%;
            height: 100%;
          }
        }
        .movie-info {
          margin-left: 20px;
          .name {
            margin-top: 12px;
            .all-font(Microsoft YaHei, 14px, #494949, 0);
            cursor: pointer;
            &:hover {
              color: #409eff;
            }
          }
          .director,
          .actors,
          .show {
            margin-top: 12px;
            .all-font(Microsoft YaHei, 12px, #666666, 0);
          }
          .score {
            display: flex;
            align-items: center;
            margin-top: 12px;
            .score-num {
              .all-font(Microsoft YaHei, 14px, #F7BA2A, 0);
            }
          }
        }
      }
      .barrage-container {
        position: absolute;
        width: 200px;
        height: 200px;
  }
  .barrage-items {
    background: rgb(0, 0, 0, 0.7);
    border-radius: 100px;
    color: #fff;
    padding: 5px 10px 5px 5px;
    align-items: center;
    display: flex;
  }
    }
    .result-footer {
      display: flex;
      justify-content: flex-end;
      margin: 30px 0 100px 0;
    }
  }
}
</style>
