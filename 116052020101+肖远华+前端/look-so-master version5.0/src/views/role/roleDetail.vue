<template>
  <div class="role-detail">
    <div class="content-top">
      <div class="detail-left">
      <role-info :roleInfo="roleInfo" class="role-info"></role-info>
      <recently-released :recentMovies="recentMovies"  class="magnum-opus" ></recently-released>
      <most-acclaimed :actorId="actorId" :goodMovies="goodMovies" class="magnum-opus" ></most-acclaimed>
    </div>
    <div class="detail-right">
      <awards class="echarts-l1" :roleInfo="roleInfo"></awards>
    </div>
    </div>
  </div>
</template>

<script>
import { getRecentMovies } from "@/js/api";
import { getGoodMovies } from "@/js/api";
import { getRoleDetail } from "@/js/api";
import RoleInfo from "../role/roleInfo.vue";
import RecentlyReleased from "@/views/role/RecentlyReleased";
import MostAcclaimed from "@/views/role/MostAcclaimed";
import Awards from "./Awards.vue";
export default {
  components: {
    RoleInfo,
    RecentlyReleased,
    MostAcclaimed,
    Awards
  },
  data() {
    return {
      roleInfo: {},
      hotReviews: [],
      goodMovies: [],
      recentMovies:[],
      actorId:''
    };
  },
  created() {
    this._getRoleDetail();
    
  },
  
  methods: {
    _getRoleDetail() {
      getRoleDetail(this.roleId).then(res => {
        this.actorId = res.actorId;
        localStorage.setItem("actorId",res.actorId)
        this.roleInfo = res;
        /*this.hotReviews = res.hotReviews;
        this.allReviews = res.allReviews;*/
      });
    },
    _getGoodMovies() {
      getGoodMovies().then(res => {
        this.goodMovies = res;

      });
    },
    _getRecentMovies() {
      getRecentMovies(this.roleInfo.actorId).then(res => {
        this.recentMovies = res;
      });
    }
  },
  computed: {
    roleId() {
      return JSON.parse(localStorage.getItem("currentMovie"));
    },
    score: {
      get: function() {
        return this.roleInfo.reviewScore / 2;
      },
      set: function() {}
    }
  }
};
</script>
<style lang="less" scoped>
.role-detail {
  display: flex;
  flex-direction: column;
  width: 85%;
  margin: 0 auto;
  .content-top {
    display: flex;
    width: 100%;
    margin-top: 20px;
    .detail-left {
      width: 70%;
      .write-review,
      .recommend-reviews,
      .magnum-opus {
        margin-top: 20px;
      }
    }
    .detail-right {
      width: 28%;
      margin-left: auto;
      .similar-role {
        margin-top: 20px;
      }
    }
  }
}
</style>
