<template>
  <div class="echarts-r1">
    <el-card class="box-card"
             v-loading="">
      <div slot="header"
           class="clearfix">
        <span class="title">电影提及词频</span>
      </div>
      <div>
        <e-charts class="chart" :option="option"></e-charts>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      option: {
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
            name: 'Nightingale Chart',
            type: 'pie',
            radius: [35, 90],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: JSON.parse(localStorage.getItem("ciyun"))
          }
        ]
      }
    }
  }
}

</script>
<style lang="less">
@import '~@/style/font.less';
.echarts-r1 {
  .box-card {
    .clearfix {
      .title {
        .all-font(MicrosoftYaHei-Bold, 18px, #005198, -0.43px);
      }
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
