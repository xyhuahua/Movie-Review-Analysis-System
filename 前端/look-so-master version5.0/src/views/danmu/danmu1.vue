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
  props: ["checkList"],
  data() {
    return {
      barrageIsShow: true,
      messageHeight: 2,
      boxHeight: 600,
      barrageLoop: false,
      maxWordCount: 20,
      lanesCount: 5, //泳道数量
      throttleGap: 20000,
      barrageList: [],
      hotReviews: [],
      page: 1,
    };
  },
  mounted() {
    console.log(this.checkList);
    const that = this;
    this.timer = setInterval(() => {
      that.getdanmu();
    }, 10000);
  },
  methods: {
    getdanmu() {
      for (var i = 0; i < this.checkList.length; i++) {
        var nowId = this.checkList[i];
        this.$axios
          .get("http://localhost:8080/getMovieDetailReviews", {
            params: {
              movieId: nowId,
              page: this.page,
            },
          })
          .then((res) => {
            console.log(res);
            // this.hotReviews = res.data.hotReviews
            //this.addToList(res.data);
            this.addToList(res.data.hotReviews);
          });
      }
      if (this.page < 25) {
        this.page = this.page + 1;
      } else {
        clearInterval(this.timer);
      }
    },
    addToList(hotReviews) {
      let list = [
        {
          id: 1,
          avatar: hotReviews[0].photoUrl,
          msg: hotReviews[0].commentContent,
          time: 20,
          barrageStyle: "red",
        },
        {
          id: 2,
          avatar: hotReviews[1].photoUrl,
          msg: hotReviews[1].commentContent,
          time: 10,
          barrageStyle: "green",
        },
        {
          id: 3,
          avatar: hotReviews[2].photoUrl,
          msg: hotReviews[2].commentContent,
          time: 30,
          barrageStyle: "normal",
        },
        {
          id: 4,
          avatar: hotReviews[3].photoUrl,
          msg: hotReviews[3].commentContent,
          time: 24,
          barrageStyle: "blue",
        },
        {
          id: 5,
          avatar: hotReviews[4].photoUrl,
          msg: hotReviews[4].commentContent,
          time: 39,
          barrageStyle: "red",
        },
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
    addToList1(message) {
        this.barrageList.push({
          id: message.id,
          avatar: message.avatar,
          msg: message.msg,
          time: message.time,
          type: MESSAGE_TYPE.NORMAL,
          barrageStyle: message.barrageStyle,
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
    overflow: hidden;
  }
  .green {
    border-radius: 100px;
    background: #75ffccb4;
    color: #fff;
    overflow: hidden;
  }
  .red {
    border-radius: 100px;
    background: #e68fbbbd;
    color: #fff;
    overflow: hidden;
  }
  .yellow {
    border-radius: 100px;
    background: #dfc895b0;
    color: #fff;
    overflow: hidden;
  }
  .baberrage-stage {
    position: relative;
    //position: absolute;
    width: 100%;
    height: 612px;
    overflow: hidden;
    top: 0;
  }
  .baberrage-lane {
    max-width: 800px;
    overflow: hidden;
  }
}
</style>