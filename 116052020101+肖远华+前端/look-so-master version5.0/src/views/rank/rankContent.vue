<template>
  <div class="rank-content">
    <div class="content-left">
      <el-tabs v-model="activeName"  style="margin-left:auto;">
      <el-tab-pane label="高分排行榜" name="first" >
        <span slot="label" class="fontClass" @click="change_type('score')">高分排行榜</span>
        <span>年份：</span>
        <el-select v-model="value"  @change="change_year(value)">
          <el-option
            v-for="item in years"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
        <div class="rank-header">{{this.year}}高分排行榜</div>
        <div class="rank-center" v-loading="isLoading">
            <div class="rank-item" v-for="(rank,index) in ranks" :key="index">
                <div class="item-left">
                  <div class="num">{{index+1}}</div>
                  <div class="cover-wrap">
                      <img class="cover" :src="rank.cover_url" alt="" @click="_goMovieDetail(rank.movieId)">
                  </div>
                </div>
                <div class="item-center">
                  <div class="name" @click="_goMovieDetail(rank.movieId)">{{rank.title}}</div>
                  <div class="director"  v-html="getDirectors(rank.actors)">导演：</div>
                  <div class="actors" v-html="getActors(rank.actors)">主演：</div>
                  <div class="show">上映日期：{{rank.release_date}}</div>
                </div>
                <div class="item-right">{{rank.score}}</div>
            </div>
        </div>
      </el-tab-pane>

      <el-tab-pane label="综合排行榜" name="second" >
        <span slot="label" class="fontClass" @click="change_type('rank')">综合排行榜</span>
        <div class="rank-header">综合排行榜</div>
        <div class="rank-center" v-loading="isLoading">
            <div class="rank-item" v-for="(rank,index) in ranks" :key="index">
                <div class="item-left">
                  <div class="num">{{index+1}}</div>
                  <div class="cover-wrap">
                      <img class="cover" :src="rank.cover_url" alt="" @click="_goMovieDetail(rank.movieId)">
                  </div>
                </div>
                <div class="item-center">
                  <div class="name" @click="_goMovieDetail(rank.movieId)">{{rank.title}}</div>
                  <div class="director"  v-html="getDirectors(rank.actors)">导演：</div>
                  <div class="actors" v-html="getActors(rank.actors)">主演：</div>
                  <div class="show">上映日期：{{rank.release_date}}</div>
                </div>
                <div class="item-right">{{rank.score}}</div>
            </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="投票排行榜" name="third" >
        <span slot="label" class="fontClass" @click="change_type('vote_count')">投票排行榜</span>
        <div class="rank-header">投票排行榜</div>
        <div class="rank-center" v-loading="isLoading">
            <div class="rank-item" v-for="(rank,index) in ranks" :key="index">
                <div class="item-left">
                  <div class="num">{{index+1}}</div>
                  <div class="cover-wrap">
                      <img class="cover" :src="rank.cover_url" alt="" @click="_goMovieDetail(rank.movieId)">
                  </div>
                </div>
                <div class="item-center">
                  <div class="name" @click="_goMovieDetail(rank.movieId)">{{rank.title}}</div>
                  <div class="director"  v-html="getDirectors(rank.actors)">导演：</div>
                  <div class="actors" v-html="getActors(rank.actors)">主演：</div>
                  <div class="show">上映日期：{{rank.release_date}}</div>
                </div>
                <div class="item-right">{{rank.score}}</div>
            </div>
        </div>
      </el-tab-pane>
    </el-tabs>
      <div class="rank-footer">
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
    
    <div class="content-right">
       <div class="echarts-r1">
          <el-card class="box-card1"
                  v-loading="">
            <div slot="header"
                class="clearfix">
              <span class="title">前十排行国家比例</span>
            </div>
            <div>
              <e-charts class="chart" :option="option"></e-charts>
            </div>
          </el-card>
          
          <el-card class="box-card2"
                  v-loading="">
            <div slot="header"
                class="clearfix">
              <span class="title">前百评分比例</span>
            </div>
            <div v-for="(scored,index) in scoredata" class="body">
              >>{{scored.score}}分  &nbsp;&nbsp;({{scored.num * 8 +index * 2}})部
            </div>
          </el-card>
          
        </div>
    </div>
    
  </div>

</template>

<script>
import EchartsR1 from "@/views/rank/EchartsR1";
import { getAllRank } from "@/js/api";
import { goMovieDetail } from "@/js/router";
export default {
  components: {
    EchartsR1
  },
  data() {
    return {
      type: "score",
      ranks: [],
      currentPage: 1,
      year: "2022",
      isLoading: true,
      pageSize: 10,
      number: 0,
      activeName: "first",
      years: [
        "2022",
        "2021",
        "2020",
        "2019",
        "2018",
        "2017",
        "2016",
        "2015",
        "2014",
        "2013",
        "2012",
        "2011",
        "2010",
        "2009",
        "2008",
        "2007",
        "2006",
        "2005",
        "2004",
        "2003"
      ],
      value: "2022",
      option: {
        legend: {
          top: "bottom"
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: "Nightingale Chart",
            type: "pie",
            radius: [35, 90],
            center: ["50%", "50%"],
            roseType: "area",
            itemStyle: {
              borderRadius: 8
            },
            data: []
          }
        ]
      },
      scoredata: []
    };
  },
  created() {
    this._getAllRank();
  },
  methods: {
    currentChange(page) {
      this.currentPage = page;
      this._getAllRank();
    },
    _goMovieDetail(movieId) {
      goMovieDetail(movieId);
    },
    _getAllRank() {
      this.isLoading = true;
      getAllRank(this.currentPage, this.year, this.type).then(res => {
        // console.log(res)
        this.ranks = res.movieRank;
        this.option.series[0].data = res.TopTenMovie;
        this.scoredata = res.HundredScoreMovie
        this.number = 100;
        this.isLoading = false;
      });
    },
    getDirectors(directors) {
      let d = [];
      let t = "";
      let length = 0;
      try {
        directors.forEach(function(item, index, self) {
          if (length === 5) {
            t = "...";
            throw Error();
          }
          if (item.actortype === "导演 Director") {
            d.push(item.actorid);
            length = length + 1;
          }
        });
      } catch (err) {}
      return "导演：" + d + t;
    },
    getActors(actors) {
      let d = [];
      let t = "";
      let length = 0;
      try {
        actors.forEach(function(item, index, self) {
          if (length === 6) {
            t = "...";
            throw Error();
          }
          if (item.actortype === "演员 Cast") {
            d.push(item.actorid);
            length = length + 1;
          }
        });
      } catch (err) {}
      return "演员：" + d + t;
    },
    change_year(year) {
      this.year = year;
      this._getAllRank();
    },
    change_type(type) {
      console.log(type);
      this.type = type;
      this._getAllRank();
    }
  }
};
</script>
<style lang="less" scoped>
@import "~@/style/font.less";
.fontClass {
  font-size: 20px;
}
.rank-content {
  .content-left {
    display: inline-block;
    vertical-align: top;
    width: 50%;
    margin-top: 20px;
    .rank-header {
      margin-top: 20px;
      .bold-title;
    }
    .rank-center {
      .rank-item:nth-child(2) {
        .item-left {
          .num {
            .rank-num(#db524b);
          }
        }
      }
      .rank-item:nth-child(3) {
        .item-left {
          .num {
            .rank-num(#539cdb);
          }
        }
      }
      .rank-item:nth-child(4) {
        .item-left {
          .num {
            .rank-num(#58b957);
          }
        }
      }
      .rank-item {
        display: flex;
        // justify-content: space-between;
        align-items: center;
        margin-top: 20px;

        .item-left {
          display: flex;
          align-items: center;
          margin-right: 20px;
          .num {
            // margin-right: 20px;
            width: 50px;
            .rank-num(#4a4a4a);
          }
          .cover-wrap {
            width: 80px;
            height: 112px;
            .cover {
              width: 100%;
              height: 100%;
            }
          }
        }
        .item-center {
          .name {
            .all-font(Microsoft YaHei, 14px, #494949, 0);
            cursor: pointer;
            &:hover {
              color: #409eff;
            }
          }
          .director,
          .actors,
          .show {
            .all-font(Microsoft YaHei, 12px, #666666, 0);
          }
        }
        .item-right {
          margin-left: auto;
          .rank-score;
        }
      }
    }
    .rank-footer {
      display: flex;
      justify-content: center;
      margin: 30px 0 100px 0;
    }
  }
  .content-right {
    display: inline-block;
    vertical-align: top;
    margin: 170px 0 0 290px;
    
  }
}
</style>
<style lang="less">
@import "~@/style/font.less";
.echarts-r1 {
  .box-card1 {
    .clearfix {
      .title {
        .all-font(MicrosoftYaHei-Bold, 18px, #005198, -0.43px);
      }
    }
  }
  .box-card2 {
    margin: 50px 0 0 0;
    .clearfix {
      .title {
        .all-font(MicrosoftYaHei-Bold, 18px, #005198, -0.43px);
      }
    }
    .body {
      .all-font(MicrosoftYaHei-Bold, 18px, grey, -0.43px);
      line-height: 60px;
    }
  }
}
.chart {
  width: 330px;
  height: 354px;
  margin-left: -23px;
  margin-top: -15px;
}
</style>
