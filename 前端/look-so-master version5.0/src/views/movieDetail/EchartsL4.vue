<template>
  <div class="echarts-l4">
    <el-card class="box-card">
      <div slot="header"
           class="clearfix">
        <span class="title">评论信息热力图</span>
      </div>
      <div id="china_map" :style="{ width: '100%', height: '290px' }"
      :data="mapdata">
      </div>
    </el-card>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import china from './china.json'
echarts.registerMap('china', china)
export default {
  name: "index",
  props: {
    className: {
      type: String,
      default: "chart",
    },
    width: {
      type: String,
      default: "100%",
    },
  },
  data () {
    return {
      iprate:[],
      mapdata: []
    }
  },
  mounted () {
    this.getIprate(this.movieId)
  },
  methods: {
    getIprate(movieId){
      localStorage.removeItem('now_iprate')
        
      console.log("Iprate")
      console.log(movieId) 
      this.$axios.get('http://localhost:8080/getMovieDetail',{
      params: {
        movieId: movieId
      }
      }).then((res) => {
        if (res.status == 200) {
          localStorage.setItem('now_ipcount',JSON.stringify(res.data.movie.ipcount))
          var total1 = 0;
          for (var i = 1; i < res.data.movie.ipcount.length; i++) {
            var mapItem = new Object();
            mapItem.name = res.data.movie.ipcount[i].key;
            mapItem.value = res.data.movie.ipcount[i].value;
            total1 = total1 + parseInt(mapItem.value = res.data.movie.ipcount[i].value);
            mapItem.perf = 5;
            this.mapdata.push(mapItem)
          }
          for (var i = 0; i < res.data.movie.ipcount.length-1; i++) {
            this.mapdata[i].perf=Math.round((res.data.movie.ipcount[i+1].value/total1)*100);
        }
        console.log(this.mapdata);
          this.setMapData();
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
        echarts.init(document.getElementById('china_map'))
      })
    },
    // 地图
    setMapData () {
      // 初始化echarts实例 #D8E9FD
      this.chinachart = echarts.init(document.getElementById("china_map"));
      // 进行相关配置
      const chartOption = {
        tooltip: {
          // 鼠标移到图里面的浮动提示框
          // formatter详细配置： https://echarts.baidu.com/option.html#tooltip.formatter
          formatter (params, ticket, callback) {
            console.log(params);
            // params.data 就是series配置项中的data数据遍历
            let localValue, perf, downloadSpeep, usability, point;
            if (params.data) {
              localValue = params.data.value;
              perf = params.data.perf;
              downloadSpeep = params.data.downloadSpeep;
              usability = params.data.usability;
              point = params.data.point;
            } else {
              // 为了防止没有定义数据的时候报错写的
              localValue = 0;
              perf = 0;
            }

            let htmlStr = `
          <div style='font-size:18px;'> ${params.name}</div>
          <p style='text-align:left;margin-top:-4px;'>
              项目数量：${localValue}<br/>
              占比：${perf}%<br/>
          </p>
        `;
            return htmlStr;
          },
          backgroundColor: "#ff7f50", //提示标签背景颜色
          textStyle: { color: "#fff" }, //提示标签字体颜色
        },
        visualMap: {
          show: true,
          bottom: 30,
          left: 10,
          text: ["高", "低"],
          //  min:0,
          //   precision:0,
          itemHeight: 200,
          color: [
            "#5475f5",
            "#9feaa5",
            "#85daef",
            "#74e2ca",
            "#e6ac53",
            "#9fb5ea",
          ],
        },
        // geo配置详解： https://echarts.baidu.com/option.html#geo
        geo: {
          // 地理坐标系组件用于地图的绘制
          map: "china", // 表示中国地图
          roam: true, // 是否开启鼠标缩放和平移漫游
          zoom: 1.5, // 当前视角的缩放比例（地图的放大比例）
          label: {
            show: false
          },
          center: [100.97, 35.71],
          itemStyle: {
            // 地图区域的多边形 图形样式。
            normal: {
              //未选中的状态
              borderColor: "#ffffff",
              areaColor: "#D8E9FD", //背景颜色
              label: {
                // show: true, //显示名称
              },
            },
            emphasis: {
              //选中的状态
              // 高亮状态下的多边形和标签样式
              shadowBlur: 20,
              shadowColor: "rgba(0, 0, 0, 0.5)",
              borderColor: "#fff",
              areaColor: "#DA3A3A",
            },
          },
        },
        series: [
          {
            name: "地图", // 浮动框的标题（上面的formatter自定义了提示框数据，所以这里可不写）
            type: "map",
            geoIndex: 0,
            label: {
              show: true,
            },
            // 这是需要配置地图上的某个地区的数据，根据后台的返回的数据进行拼接（下面是我定义的假数据）
            data: this.mapdata
          },
        ],
      };
      // 使用刚指定的配置项和数据显示地图数据
      this.chinachart.setOption(chartOption);
    }, 
  },
  computed: {
    movieId () {
      return JSON.parse(localStorage.getItem('currentMovie'))
    }
  }
};

</script>
<style lang="less">
@import '~@/style/font.less';
.echarts-l4 {
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
