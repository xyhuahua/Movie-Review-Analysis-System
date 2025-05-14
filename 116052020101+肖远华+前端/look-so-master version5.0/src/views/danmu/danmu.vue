<template>
  <div class="barrages-drop">
    <vue-baberrage
      :isShow="barrageIsShow"
      :barrageList="barrageList"
      :throttleGap="throttleGap"
      :loop="barrageLoop"
      :boxHeight="boxHeight"
      :messageHeight="messageHeight"
      :maxWordCount="maxWordCount"
    >
    </vue-baberrage>
  </div>
</template>
<script>
import Vue from "vue";
import { vueBaberrage, MESSAGE_TYPE } from "vue-baberrage";
Vue.use(vueBaberrage);
export default {
  name: "Barrages",
  props: ['movieId'],
  data() {
    return {
      barrageIsShow: true,
      messageHeight: 2,
      boxHeight: 200,
      barrageLoop: true,
      maxWordCount: 20,   
      lanesCount: 3,			//泳道数量
      throttleGap: 12000,
      barrageList: [],
      hotReviews:[]
    };
  },
  mounted() {
    console.log(this.movieId);
    var nowId = this.movieId
      this.$axios.get('http://localhost:8080/getMovieDetailReviews',{
      params: {
        movieId: nowId,
        page:1
      }
      }).then((res) => {
        console.log(res)
        // this.hotReviews = res.data.hotReviews
        //this.addToList(res.data);
        this.addToList(res.data.hotReviews);
      })

    
  },
  methods: {
    addToList(hotReviews) {
      let list = [
        {
          id: 1,
          avatar:hotReviews[0].photoUrl,
          msg: hotReviews[0].commentContent,
          time: 20,
          barrageStyle: "red",
        },
        {
          id: 2,
          avatar:hotReviews[1].photoUrl,
          msg: hotReviews[1].commentContent,
          time: 10,
          barrageStyle: "green",
        },
        {
          id: 3,
          avatar:hotReviews[2].photoUrl,
          msg: hotReviews[2].commentContent,
          time: 30,
          barrageStyle: "normal",
        },
        {
          id: 4,
          avatar:hotReviews[3].photoUrl,
          msg: hotReviews[3].commentContent,
          time: 24,
          barrageStyle: "blue",
        },
        {
          id: 5,
          avatar:hotReviews[4].photoUrl,
          msg: hotReviews[4].commentContent,
          time: 39,
          barrageStyle: "red",
        },
        // {
        //   id: 6,
        //   avatar:hotReviews[5].photoUrl,
        //   msg: hotReviews[5].commentContent,
        //   time: 22,
        //   barrageStyle: "normal",
        // },
        // {
        //   id: 7,
        //   avatar:hotReviews[6].photoUrl,
        //   msg: hotReviews[6].commentContent,
        //   time: 25,
        //   barrageStyle: "red",
        // },
        // {
        //   id: 8,
        //   avatar:hotReviews[7].photoUrl,
        //   msg: hotReviews[7].commentContent,
        //   time: 21,
        //   barrageStyle: "normal",
        // },
        // {
        //   id: 9,
        //   avatar:hotReviews[8].photoUrl,
        //   msg: hotReviews[8].commentContent,
        //   time: 28,
        //   barrageStyle: "red",
        // },
        // {
        //   id: 10,
        //   avatar:hotReviews[9].photoUrl,
        //   msg: hotReviews[9].commentContent,
        //   time: 30,
        //   barrageStyle: "yellow",
        // },
      ];
      list.forEach((v) => {
        this.barrageList.push({
          id: v.id,
          avatar: v.avatar,
          msg: v.msg,
          time: v.time,
          type: MESSAGE_TYPE.NORMAL,
          barrageStyle: v.barrageStyle,
        });
      });
    },
  },
};
</script>
<style lang="less">
.barrages-drop {
  .blue {
    border-radius: 100px;
    background: #e6ff75be;
    color: #fff;
    max-width: 300px;
    overflow: hidden;
  }
  .green {
    border-radius: 100px;
    background: #75ffccb4;
    color: #fff;
    max-width: 300px;
    overflow: hidden;
  }
  .red {
    border-radius: 100px;
    background: #e68fbbbd;
    color: #fff;
    max-width: 300px;
    overflow: hidden;
  }
  .yellow {
    border-radius: 100px;
    background: #dfc895b0;
    color: #fff;
    max-width: 300px;
    overflow: hidden;
  }
  .baberrage-stage {
    position: relative;
    //position: absolute;
    width: 100%;
    height: 212px;
    overflow: hidden;
    top: 0;
    margin-top: -120px;
    margin-left: 150px;
  }
  .baberrage-lane{
    max-width: 300px;
    overflow: hidden;
  }
}
</style>