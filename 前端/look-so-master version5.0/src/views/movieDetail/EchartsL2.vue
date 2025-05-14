<template>
  <div class="echarts-l2">
    <el-card class="box-card" v-loading="">
      <div slot="header" class="clearfix">
        <span class="title">主要城市评论数vs评分</span>
      </div>
      <div>
        <e-charts class="chart1" :option="option"></e-charts>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getCountAndRate } from "@/js/api";

export default {
  data() {
    return {
      
      option: {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            crossStyle: {
              color: "#999",
            },
          },
        },
        grid: {
          left: 0,
          right: 0,
          top: 80,
          bottom: 0,
          containLabel: true
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line", "bar"] },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        },
        legend: {
          data: ["评论数", "评分"],
          left: "%0",
          itemWidth: 15,
          width: "36%",
        },
        xAxis: [
          {
            type: "category",
            data: (function () {
              var movieId = JSON.parse(localStorage.getItem("movieId"));
              console.log("woc");
              console.log(movieId);
              var city = []
              getCountAndRate(movieId).then((res) => {
                console.log(res.iprate)
                var iprate = res.iprate
                for (var i = 0; i < iprate.length; i++) {
                    if(iprate[i].key){
                      city.push(iprate[i].key);
                    }else{
                      city.push("全国");
                    }
                }
                console.log(city)
              });
              console.log(city)
              return city
            })(),
            axisPointer: {
              type: "shadow",
            },
            axisLabel: { //设置x轴的字
              show:true,
              interval:0,
              rotate: 60, 
              inside: false, 
              margin: 6,
            },
          },
        ],
        yAxis: [
          {
            type: "value",
            name: "评分",
            min: 0,
            max: 10,
            interval: 2,
            axisLabel: {
              formatter: "{value} 分",
            },
          },
          {
            type: "value",
            name: "评论数",
            min: 0,
            max: 2000,
            interval: 500,
            axisLabel: {
              formatter: "{value} 个",
            },
          },
        ],
        series: [
          {
            name: "评分",
            type: "bar",
            tooltip: {
              valueFormatter: function (value) {
                return value + " 分";
              },
            },
            data: (function () {
              var movieId = JSON.parse(localStorage.getItem("movieId"));
              console.log("woc");
              console.log(movieId);
              var rate = []
              getCountAndRate(movieId).then((res) => {
                console.log(res.iprate)
                var iprate = res.iprate
                for (var i = 0; i < iprate.length; i++) {
                    rate.push(iprate[i].value/10*2);
                }
                console.log(rate)
              });
              console.log(rate)
              return rate
            })(),
          },
          {
            name: "评论数",
            type: "line",

            tooltip: {
              valueFormatter: function (value) {
                return value + " 个";
              },
            },
            data: (function () {
              var movieId = JSON.parse(localStorage.getItem("movieId"));
              console.log("woc");
              console.log(movieId);
              var count = []
              getCountAndRate(movieId).then((res) => {
                console.log(res.ipcount)
                var ipcount = res.ipcount
                for (var i = 0; i < ipcount.length; i++) {
                    count.push(ipcount[i].value);
                }
                console.log(count)
              });
              console.log(count)
              return count
            })(),
          },
        ],
      },
    };
  },
  created() {
    localStorage.setItem("movieId", JSON.stringify(this.movieId));
  },
  computed: {
    movieId() {
      return JSON.parse(localStorage.getItem("currentMovie"));
    },
  },
};
</script>
<style lang="less">
@import "~@/style/font.less";
.echarts-l2 {
  .box-card {
    .clearfix {
      .title {
        .all-font(MicrosoftYaHei-Bold, 18px, #005198, -0.43px);
      }
    }
  }
}
.chart1 {
  width: 330px;
  height: 305px;
  margin-left: 10px;
  margin-top: -15px;
}
</style>
