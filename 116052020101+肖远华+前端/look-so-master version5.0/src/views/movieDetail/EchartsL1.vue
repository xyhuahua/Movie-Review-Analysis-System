<template>
  <div class="echarts-l1">
    <el-card class="box-card">
      <div slot="header"
           class="clearfix">
        <span class="title">评论词云图</span>
      </div>
      <div id="mywordcloud" :style="{width: '100%', height: '290px'}" :data="worddata"></div>
    </el-card>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import 'echarts-wordcloud/dist/echarts-wordcloud'
import 'echarts-wordcloud/dist/echarts-wordcloud.min'
export default {
  name: 'VueWordCloud',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      worddata: []
    }
  },
  props: {
    worddata: {
      type:Array,
      default:null
    }
  },
  mounted () {
    this.getAllCiYun(this.movieId)
  },
  created () {
    
    
  },
  methods: {
    getAllCiYun(movieId){
      localStorage.removeItem('ciyun')
      console.log("ppp")
      console.log(movieId) 
      this.$axios.get('http://localhost:8080/getAllCiYun',{
      params: {
        movieId: movieId
      }
      }).then((res) => {
        if (res.status == 200) {
          //console.log(res.data.allCiYun[0].ciyun)
          localStorage.setItem('ciyun', JSON.stringify(res.data.allCiYun[0].ciyun))
          this.worddata = JSON.parse(localStorage.getItem("ciyun"));
          console.log(this.worddata)
          this.initChart(this.worddata)
        }
      })
      
    },
    initEcharts () {
      // 新建一个promise对象
      let newPromise = new Promise((resolve) => {
        resolve()
      })
      // 然后异步执行echarts的初始化函数
      newPromise.then(() => {
        // 此dom为echarts图标展示dom
        echarts.init(document.getElementById('mywordcloud'))
      })
    },
    initChart (worddata) {
      console.log("ppp2")
      console.log(worddata)
      this.chart = echarts.init(document.getElementById('mywordcloud'))
      const option = {
        backgroundColor: '#fff',
        // tooltip: {
        //   pointFormat: "{series.name}: <b>{point.percentage:.1f}%</b>"
        // },
        series: [
          {
            type: 'wordCloud',
            // 用来调整词之间的距离
            gridSize: 11,
            // 用来调整字的大小范围
            // Text size range which the value in data will be mapped to.
            // Default to have minimum 12px and maximum 60px size.
            sizeRange: [10, 50],
            // Text rotation range and step in degree. Text will be rotated randomly in range [-90,                                                                             90] by rotationStep 45
            // 用来调整词的旋转方向，，[0,0]--代表着没有角度，也就是词为水平方向，需要设置角度参考注释内容
            rotationRange: [-45, 15, 45, 90],
            // rotationRange: [ 0,90],
            // rotationRange: [0, 0],
            // 随机生成字体颜色
            // maskImage: maskImage,
            textStyle: {
              normal: {
                color: function () {
                  return (
                    'rgb(' +
                    Math.round(Math.random() * 255) +
                    ', ' +
                    Math.round(Math.random() * 255) +
                    ', ' +
                    Math.round(Math.random() * 255) +
                    ')'
                  )
                }
              }
            },
            // 位置相关设置
            // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
            // Default to be put in the center and has 75% x 80% size.
            left: 'center',
            top: 'center',
            right: null,
            bottom: null,
            width: '200%',
            height: '200%',
            // 数据
            data: worddata
          }
        ]
      }
      this.chart.setOption(option)
    }
  },
  computed: {
    movieId () {
      return JSON.parse(localStorage.getItem('currentMovie'))
    }
  }
}

</script>
<style lang='less'>
@import '~@/style/font.less';
.echarts-l1 {
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
.chart {
  width: 330px;
  height: 305px;
  left: -22px;
  top: -20px;
  margin-top: 10px;
}
</style>
