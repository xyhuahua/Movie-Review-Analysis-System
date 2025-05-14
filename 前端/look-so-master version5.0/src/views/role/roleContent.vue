<template>
  <div class="role-content">
    <el-tabs v-model="activeName" @tab-click="handleClick" style="margin-left:auto;">
      <el-tab-pane label="高分排行榜" name="first">
        <span slot="label" class="fontClass" @click="currentType('averageScore')">高分排行榜</span>
        <div class="role-header"></div>
      <div class="role-center" v-loading="isLoading">
          <div class="role-item" v-for="(role,index) in roles" :key="index">
              <div class="item-left">
                <div class="num">{{index+1}}</div>
                <div class="cover-wrap">
                    <img class="cover" :src="nameRomoveFL(role.role.actortouxiang)" alt="" @click="_goRoleDetail(role.role.actorId)">
                </div>
              </div>
              <div class="item-center">
                <div class="name" @click="_goRoleDetail(role.role.actorId)">角色：{{nameRomoveFL(role.role.actorname)}}</div>
                <div class="director">来源剧集：&laquo;{{role.movies[0].title}}&raquo;</div>
                <div class="actors">演员：{{nameRomoveFL(role.role.actorname)}}</div>
              </div>
              <div class="item-right">{{role.role.averageScore}}</div>
          </div>
      </div>
      </el-tab-pane>
      <el-tab-pane label="获奖排行榜" name="second">
        <span slot="label" class="fontClass"  @click="currentType('awardCount')">获奖排行榜</span>
        <div class="role-header"></div>
      <div class="role-center" v-loading="isLoading">
          <div class="role-item" v-for="(role,index) in roles" :key="index">
              <div class="item-left">
                <div class="num">{{index+1}}</div>
                <div class="cover-wrap">
                    <img class="cover" :src="nameRomoveFL(role.role.actortouxiang)" alt="" @click="_goRoleDetail(role.role.actorId)">
                </div>
              </div>
              <div class="item-center">
                <div class="name" @click="_goRoleDetail(role.role.actorId)">角色：{{nameRomoveFL(role.role.actorname)}}</div>
                <div class="director">来源剧集：&laquo;{{role.movies[0].title}}&raquo;</div>
                <div class="actors">演员：{{nameRomoveFL(role.role.actorname)}}</div>
              </div>
              <div class="item-right">{{role.role.averageScore}}</div>
          </div>
      </div>
      </el-tab-pane>
      <el-tab-pane label="片量排行榜" name="third">
        <span slot="label" class="fontClass"  @click="currentType('movieCount')">片量排行榜</span>
        <div class="role-header"></div>
      <div class="role-center" v-loading="isLoading">
          <div class="role-item" v-for="(role,index) in roles" :key="index">
              <div class="item-left">
                <div class="num">{{index+1}}</div>
                <div class="cover-wrap">
                    <img class="cover" :src="nameRomoveFL(role.role.actortouxiang)" alt="" @click="_goRoleDetail(role.role.actorId)">
                </div>
              </div>
              <div class="item-center">
                <div class="name" @click="_goRoleDetail(role.role.actorId)">角色：{{nameRomoveFL(role.role.actorname)}}</div>
                <div class="director">来源剧集：&laquo;{{role.movies[0].title}}&raquo;</div>
                <div class="actors">演员：{{nameRomoveFL(role.role.actorname)}}</div>
              </div>
              <div class="item-right">{{role.role.averageScore}}</div>
          </div>
      </div>
      </el-tab-pane>
    </el-tabs>
      
      <div class="role-footer">
        <el-pagination
            background
            layout="prev, pager, next"
            :page-size="pageSize"
            :total="number"
            :current-page="currentPage"
            @current-change="currentChange">
        </el-pagination>
      </div>
  </div>
</template>

<script>
import { getAllRole } from "@/js/api";
import { goRoleDetail } from "@/js/router";
export default {
  data() {
    return {
      roles: [],
      currentPage: 1,
      isLoading: true,
      pageSize: 10,
      number: 0,
      activeName: "first",
      type:"averageScore"
    };
  },
  created() {
    this._getAllRole();
  },
  methods: {
    currentChange(page) {
      this.currentPage = page;
      this._getAllRole();
    },
    currentType(type) {
      this.type = type;
      this._getAllRole();
    },
    _goRoleDetail(roleId) {
      goRoleDetail(roleId);
    },
    _getAllRole() {
      this.isLoading = true;
      getAllRole(this.currentPage,this.type).then(res => {
        this.roles = res;
        console.log(res)
        this.number = 100;
        this.isLoading = false;
      });
    },
    nameRomoveFL(str) {
      str = str.substring(1);
      str = str.substring(0, str.length - 1);
      return str;
    },
    getMovieId(str) {
      str = str.substring(str.length - 9, str.length - 1);
      return str;
    }
  }
};
</script>
<style lang="less" scoped>
@import "~@/style/font.less";
.fontClass {
  font-size: 20px;
}
.role-content {
  margin-top: 20px;
  .role-header {
    margin-top: 20px;
    .bold-title;
  }
  .role-center {
    .role-item:nth-child(2) {
      .item-left {
        .num {
          .rank-num(#db524b);
        }
      }
    }
    .role-item:nth-child(3) {
      .item-left {
        .num {
          .rank-num(#539cdb);
        }
      }
    }
    .role-item:nth-child(4) {
      .item-left {
        .num {
          .rank-num(#58b957);
        }
      }
    }
    .role-item {
      display: flex;
      // justify-content: space-between;
      align-items: center;
      margin-top: 20px;

      .item-left {
        display: flex;
        align-items: center;
        margin-right: 20px;
        .num {
          // margin-right: 20px;
          width: 50px;
          .rank-num(#4a4a4a);
        }
        .cover-wrap {
          width: 80px;
          height: 112px;
          .cover {
            width: 100%;
            height: 100%;
          }
        }
      }
      .item-center {
        .name {
          .all-font(Microsoft YaHei, 14px, #494949, 0);
          cursor: pointer;
          &:hover {
            color: #409eff;
          }
        }
        .director,
        .actors,
        .show {
          .all-font(Microsoft YaHei, 12px, #666666, 0);
        }
      }
      .item-right {
        margin-left: auto;
        .rank-score;
      }
    }
  }
  .role-footer {
    display: flex;
    justify-content: center;
    margin: 30px 0 100px 0;
  }
}
</style>
