<template>
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
        >>{{scored.score}}分  &nbsp;&nbsp;({{scored.num}})部
      </div>
    </el-card>
    
  </div>
</template>

<script>
import { getAllRank } from "@/js/api";
export default {
  created() {
    this._getAllRank();
    console.log(this.option.series[0].data);
  },
  data() {
    return {
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
            data: [
              { value: 4, name: "中国" },
              { value: 3, name: "美国" },
              { value: 3, name: "法国" },
              { value: 3, name: "英国" },
              { value: 2, name: "德国" },
              { value: 2, name: "俄罗斯" },
              { value: 2, name: "日本" },
              { value: 1, name: "韩国" }
            ]
          }
        ]
      },
      scoredata: [
        { score: 9.8, num: 1 },
        { score: 9.4, num: 3 },
        { score: 9.3, num: 4 },
        { score: 9.2, num: 2 },
        { score: 9.1, num: 3 },
        { score: 8.8, num: 1 },
        { score: 8.3, num: 2 }
      ]
    };
  },
  methods: {
    _getAllRank() {
      this.isLoading = true;
      getAllRank(this.currentPage, this.year, this.type).then(res => {
        // console.log(res)
        this.option.series[0].data = res.TopTenMovie;
        this.number = 100;
        this.isLoading = false;
      });
    }
  }
};
</script>
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
