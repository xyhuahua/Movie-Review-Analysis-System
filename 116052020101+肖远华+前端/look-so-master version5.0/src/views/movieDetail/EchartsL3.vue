<template>
  <div class="echarts-l3">
    <el-card class="box-card"
             v-loading="">
      <div slot="header"
           class="clearfix">
        <span class="title">情感分析雷达图</span>
      </div>
      <div style="width:380px;">
        <e-charts class="chart2"
                  :option="option"></e-charts>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getCountAndRate } from "@/js/api";
export default {
  data () {
    return {
      option: {
        legend: {
          data: ['人数'],
          itemWidth: 16,
          textStyle: {
            fontSize: 10
          },
          left: '3%',
          width: '30%'
        },
        radar: {
          // shape: 'circle',
          indicator: [
            { name: '非常喜欢', max: 50 },
            { name: '喜欢', max: 50 },
            { name: '还行', max: 1000 },
            { name: '可接受', max: 3000 },
            { name: '不喜欢', max: 1000 },
            { name: '极度不喜欢', max: 50 }
          ],
          radius: ['0%', '80%'],
          axisName: {
            fontSize: 9
          },
          nameGap: 8,
          center: ['50%', '54%']
        },
        series: [
          {
            name: 'Like Number',
            type: 'radar',
            data: [
              // {
              //   value: [4200, 3000, 20000, 35000, 50000, 18000],
              //   name: '人数'
              // },
              {
                value:  (function () {
              var movieId = JSON.parse(localStorage.getItem("movieId"));
              console.log("woc");
              console.log(movieId);
              var result = []
              var E1=[]
              var E2=[]
              var E3=[]
              var E4=[]
              var E5=[]
              var E6=[]
              getCountAndRate(movieId).then((res) => {
                console.log(res.emotionanalyse)
                var emotionanalyse = res.emotionanalyse
                console.log(emotionanalyse)
                for (var i = 0; i < emotionanalyse.length; i++) {
                  
                    if(emotionanalyse[i].key<=-10){
                      E6.push(emotionanalyse[i].value)
                    }
                    else if(emotionanalyse[i].key>-10 && emotionanalyse[i].key<0){
                      E5.push(emotionanalyse[i].value)
                    }
                    else if(emotionanalyse[i].key>=0 && emotionanalyse[i].key<5){
                      E4.push(emotionanalyse[i].value)
                    }
                    else if(emotionanalyse[i].key>=5 && emotionanalyse[i].key<10){
                      E3.push(emotionanalyse[i].value)
                    }
                    else if(emotionanalyse[i].key>=10 && emotionanalyse[i].key<=20){
                      E2.push(emotionanalyse[i].value)
                    }
                    else if(emotionanalyse[i].key>20 ){
                      E1.push(emotionanalyse[i].value)
                    }
                }
                result.push(eval(E1.join("+")))
                result.push(eval(E2.join("+")))
                result.push(eval(E3.join("+")))
                result.push(eval(E4.join("+")))
                result.push(eval(E5.join("+")))
                result.push(eval(E6.join("+")))
                console.log(result)
                
                
              });
              
              return result
            })(),
                name: '人数'
              }
            ]
          }
        ]
      }
    }
  },
  created() {
    localStorage.setItem("movieId", JSON.stringify(this.movieId));
  },
  computed: {
    movieId() {
      return JSON.parse(localStorage.getItem("currentMovie"));
    },
  },
}

</script>
<style lang="less">
@import '~@/style/font.less';
.echarts-l3 {
  .box-card {
    .clearfix {
      .title {
        .all-font(MicrosoftYaHei-Bold, 18px, #005198, -0.43px);
      }
    }
    .rank-item {
      margin-bottom: 12px;
      .all-font(MicrosoftYaHei,14px, #333333, -0.34px);
      cursor: pointer;
      &:hover {
        color: #409eff;
      }
    }
  }
}
.chart2 {
  width: 330px;
  height: 305px;
  margin-left: 10px;
  margin-top: -15px;
}
</style>
