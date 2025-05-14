// //柱状图模块1
// (function () {
//   var myChart = echarts.init(document.querySelector(".bar .chart"));
//   var option = {
//     //cloor=['#2f89cf'];
//     tooltip: {
//       trigger: "axis",
//       axisPointer: {
//         type: "shadow",
//       },
//     },
//     grid: {
//       left: "0%",
//       top: "10px",
//       right: "0%",
//       bottom: "4%",
//       containLabel: true,
//     },
//     xAxis: [
//       {
//         type: "category",
//         data: [
//           "旅游行业",
//           "教育培训",
//           "游戏行业",
//           "医疗行业",
//           "电商行业",
//           "社交行业",
//           "金融行业",
//         ],
//         axisTick: {
//           alignWithLabel: true,
//         },
//         axisLabel: {
//           color: "rgba(255,255,255,.6)",
//           fontSize: "8",
//         },
//         axisLine: {
//           show: false,
//         },
//       },
//     ],
//     yAxis: [
//       {
//         type: "value",
//         axisLabel: {
//           color: "rgba(255,255,255,.6)",
//           fontSize: "12",
//         },
//         axisLine: {
//           lineStyle: {
//             color: "rgba(255,255,255,.1)",
//             width: 1,
//             type: "solid",
//           },
//         },
//         splitLine: {
//           lineStyle: {
//             color: "rgba(255,255,255,.1)",
//           },
//         },
//       },
//     ],
//     series: [
//       {
//         name: "直接访问",
//         type: "bar",
//         // 修改柱子宽度
//         barWidth: "35%",
//         data: [200, 300, 300, 900, 1500, 1200, 600],
//         itemStyle: {
//           // 修改柱子圆角
//           barBorderRadius: 5,
//         },
//       },
//     ],
//   };
//   myChart.setOption(option);
//   window.addEventListener("resize", function () {
//     myChart.resize();
//   });
// })();

// //柱状图模块2
// (function () {
//   var myChart = echarts.init(document.querySelector(".bar2 .chart"));
//   var myColor = ["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
//   var option = {
//     grid: {
//       top: "10%",
//       left: "22%",
//       bottom: "10%",
//     },
//     //不显示x轴线条
//     xAxis: {
//       show: false,
//     },
//     yAxis: [
//       {
//       type: "category",
//       inverse:true,
//       data: ["HTML5", "CSS3", "javascript", "VUE", "NODE"],
//       // 不显示y轴的线
//       axisLine: {
//         show: false
//       },
//       // 不显示刻度
//       axisTick: {
//         show: false
//       },
//       // 把刻度标签里面的文字颜色设置为白色
//       axisLabel: {
//         color: "#fff"
//       }
//     },
//       {
//         show: true,
//         inverse:true,
//         data: [702, 350, 610, 793, 664],
//            // 不显示y轴的线
//       axisLine: {
//         show: false
//       },
//       // 不显示刻度
//       axisTick: {
//         show: false
//       },
//         axisLabel: {
//           textStyle: {
//             fontSize: 12,
//             color: "#fff"
//           }
//         }
//       }
//     ],
//     series: [
//       {
//         name: "条",
//         type: "bar",
//         data: [70, 34, 60, 78, 69],
//         barCategoryGap:50,
//         barWidth:10,
//         itemStyle:{
//           barBorderRadius:20,
//           color:function(params){
//             return myColor[params.dataIndex];
//           },
//         yAxisIndex: 0,

//         },
//         label:{
//           show:true,
//           position:"inside",
//           formatter:"{c}%"
//         }
//       },
//       {
//         name: "框",
//         type: "bar",
//         barCategoryGap: 50,
//         barWidth: 15,
//         itemStyle: {
//             color: "none",
//             borderColor: "#00c1de",
//             borderWidth: 3,
//             barBorderRadius: 15
//         },
//         data: [100, 100, 100, 100, 100],
//         yAxisIndex: 1,
//       },
//     ],
//   };
//   myChart.setOption(option);
//   window.addEventListener("resize", function () {
//     myChart.resize();
//   });
// })();

// 折线图 优秀作品
(function() {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.querySelector(".line1 .chart"));

  option = {
    tooltip: {
      trigger: "axis",
      axisPointer: {
        lineStyle: {
          color: "#dddc6b"
        }
      }
    },
    legend: {
      top: "0%",
      textStyle: {
        color: "rgb(22, 20, 20)",
        fontSize: "12"
      }
    },
    grid: {
      left: "10",
      top: "30",
      right: "10",
      bottom: "10",
      containLabel: true
    },

    xAxis: [
      {
        type: "category",
        boundaryGap: false,
        axisLabel: {
          textStyle: {
            color: "rgb(22, 20, 20)",
            fontSize: 12
          }
        },
        axisLine: {
          lineStyle: {
            color: "rgba(255,255,255,.2)"
          }
        },

        data: [
          "01",
          "02",
          "03",
          "04",
          "05",
          "06",
          "07",
          "08",
          "09",
          "11",
          "12",
          "13",
          "14",
          "15",
          "16",
          "17",
          "18",
          "19",
          "20",
          "21",
          "22",
          "23",
          "24",
          "25",
          "26",
          "27",
          "28",
          "29",
          "30"
        ]
      },
      {
        axisPointer: { show: false },
        axisLine: { show: false },
        position: "bottom",
        offset: 20
      }
    ],

    yAxis: [
      {
        type: "value",
        axisTick: { show: false },
        axisLine: {
          lineStyle: {
            color: "rgba(255,255,255,.1)"
          }
        },
        axisLabel: {
          textStyle: {
            color: "rgb(22, 20, 20)",
            fontSize: 12
          }
        },

        splitLine: {
          lineStyle: {
            color: "rgba(255,255,255,.1)"
          }
        }
      }
    ],
    series: [
      {
        name: "播放量",
        type: "line",
        smooth: true,
        symbol: "circle",
        symbolSize: 5,
        showSymbol: false,
        lineStyle: {
          normal: {
            color: "#0184d5",
            width: 2
          }
        },
        areaStyle: {
          normal: {
            color: new echarts.graphic.LinearGradient(
              0,
              0,
              0,
              1,
              [
                {
                  offset: 0,
                  color: "rgba(1, 132, 213, 0.4)"
                },
                {
                  offset: 0.8,
                  color: "rgba(1, 132, 213, 0.1)"
                }
              ],
              false
            ),
            shadowColor: "rgba(0, 0, 0, 0.1)"
          }
        },
        itemStyle: {
          normal: {
            color: "#0184d5",
            borderColor: "rgba(221, 220, 107, .1)",
            borderWidth: 12
          }
        },
        data: [
          30,
          40,
          30,
          40,
          30,
          40,
          30,
          60,
          20,
          40,
          20,
          40,
          30,
          40,
          30,
          40,
          30,
          40,
          30,
          60,
          20,
          40,
          20,
          40,
          30,
          60,
          20,
          40,
          20,
          40
        ]
      },
      {
        name: "转发量",
        type: "line",
        smooth: true,
        symbol: "circle",
        symbolSize: 5,
        showSymbol: false,
        lineStyle: {
          normal: {
            color: "#00d887",
            width: 2
          }
        },
        areaStyle: {
          normal: {
            color: new echarts.graphic.LinearGradient(
              0,
              0,
              0,
              1,
              [
                {
                  offset: 0,
                  color: "rgba(0, 216, 135, 0.4)"
                },
                {
                  offset: 0.8,
                  color: "rgba(0, 216, 135, 0.1)"
                }
              ],
              false
            ),
            shadowColor: "rgba(0, 0, 0, 0.1)"
          }
        },
        itemStyle: {
          normal: {
            color: "#00d887",
            borderColor: "rgba(221, 220, 107, .1)",
            borderWidth: 12
          }
        },
        data: [
          50,
          30,
          50,
          60,
          10,
          50,
          30,
          50,
          60,
          40,
          60,
          40,
          80,
          30,
          50,
          60,
          10,
          50,
          30,
          70,
          20,
          50,
          10,
          40,
          50,
          30,
          70,
          20,
          50,
          10,
          40
        ]
      }
    ]
  };

  // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option);
  window.addEventListener("resize", function() {
    myChart.resize();
  });
})();

// 饼形图定制
(function() {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.querySelector(".pie .chart"));

  option = {
    tooltip: {
      trigger: "item",
      formatter: "{a} <br/>{b}: {c} ({d}%)",
      position: function(p) {
        //其中p为当前鼠标的位置
        return [p[0] + 10, p[1] - 10];
      }
    },
    legend: {
      top: "90%",
      itemWidth: 10,
      itemHeight: 10,
      data: ["正面情绪", "负面情绪"],
      textStyle: {
        color: "rgb(22, 20, 20)",
        fontSize: "12"
      }
    },
    series: [
      {
        name: "情绪分布",
        type: "pie",
        center: ["50%", "42%"],
        radius: ["40%", "60%"],
        color: [
          "#065aab",
          "#066eab",
          "#0682ab",
          "#0696ab",
          "#06a0ab",
          "#06b4ab",
          "#06c8ab",
          "#06dcab",
          "#06f0ab"
        ],
        label: { show: false },
        labelLine: { show: false },
        data: [
          { value: 7, name: "正面情绪" },
          { value: 3, name: "负面情绪" },
        ]
      }
    ]
  };

  // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option);
  window.addEventListener("resize", function() {
    myChart.resize();
  });
})();