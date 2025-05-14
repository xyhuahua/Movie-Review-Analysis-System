<template>
  <div class="home-header">
    <div class="content-wrapper">
      <span class="header-title">电影推荐</span>
      <div class="nav-left">
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          background-color="#409eff"
          text-color="#ffffff"
          active-text-color="#ffffff"
          mode="horizontal"
          @select="handleSelect"
          router
        >
          <el-menu-item index="/homepage/recommend">首页</el-menu-item>
          <el-menu-item index="/homepage/movie">所有电影</el-menu-item>
          <el-menu-item index="/homepage/review">热门影评</el-menu-item>
          <el-menu-item index="/homepage/rank">排行榜</el-menu-item>
          <el-menu-item index="/homepage/role">演员榜</el-menu-item>
        </el-menu>
      </div>
      <div class="nav-right">
        <el-autocomplete
          v-model="searchContent"
          :fetch-suggestions="_getAllMovieName"
          placeholder="请输入内容"
          @select="handleSelect2"
        ></el-autocomplete>
        <el-button
          icon="el-icon-search"
          circle
          type="submit"
          @click="search"
        ></el-button>
      </div>
      <el-button
        class="personal"
        type="primary"
        icon="el-icon-user"
        @click="_goPersonalCenter()"
        >个人中心</el-button
      >
      <ul class="header-ul" v-if="!username">
        <li class="header-login" v-show="!showHeader" @click="showLogin">
          登录
        </li>
        <li class="header-register" v-show="!showHeader" @click="showRegister">
          注册
        </li>
        <li>
          <img
            class="header-head"
            src="/static/img/head.JPG"
            alt=""
            v-show="showHeader"
          />
        </li>
      </ul>
      <ul class="header-ul" v-if="username">
        <li class="header-login" v-show="!showHeader">{{ username }}</li>
        <li class="header-login" v-show="!showHeader" @click="unlogin">退出</li>
      </ul>
    </div>
  </div>
</template>

<script>
import bus from "@/js/bus";
import { getAllMovieName } from "@/js/api";
import { goPersonalCenter } from "@/js/router";

export default {
  data() {
    return {
      showHeader: false,
      // activeIndex: this.$route.name,
      movies: [],
      restaurants: [],
      tiemout: null,
      searchContent: "",
      username: "",
      resultInfo: {
        resultContent: [],
        number: 200,
      },
    };
  },
  created() {
    this._getAllMovieName();
    this.username = sessionStorage.getItem("username");
    // console.log(this.activeIndex)
  },
  methods: {
    showLogin() {
      bus.$emit("showLogin", true);
    },
    showRegister() {
      bus.$emit("showRegister", true);
    },
    unlogin() {
      sessionStorage.removeItem("username");
      this.username = sessionStorage.getItem("username");
    },
    search() {
      //localStorage.removeItem("searchResult");
      console.log(localStorage.getItem("searchPage"));
      localStorage.setItem("searchContent", JSON.stringify(this.searchContent));
      //后台搜索
      this.$axios
        .post("http://localhost:8080/search", {
          page: 1,
          searchContent: this.searchContent,
        })
        .then((res) => {
          if (res.status === 200) {
            console.log(res);
            this.$router.push("searchResult");
            localStorage.setItem("searchResult", JSON.stringify(res.data));
          }
          location.reload();
        });

      // // 测试搜索
      // this.$router.push('searchResult')
      // localStorage.setItem('searchResult', JSON.stringify(this.resultInfo))
    },
    //------------------------------------------------------------------------------
    handleSelect(key, keyPath) {
      console.log("111111111111111");
      console.log(key, keyPath);
    },
    createStateFilter(queryString) {
      return (state) => {
        return (
          state.title.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    handleSelect2(item) {
      // console.log(item)
    },
    _getAllMovieName(queryString, cb) {
      getAllMovieName(queryString, cb).then((res) => {
        // console.log(res.allMovieName)

        var restaurants = res.allMovieName;
        //console.log(restaurants)
        var result = queryString
          ? restaurants.filter(this.createStateFilter(queryString))
          : restaurants;
        var result_len = result.length;
        //console.log(result_len)
        var json_res = [];
        for (var i = 0; i < result_len; i++) {
          var value = result[i].title;
          json_res.push({ value: value });
        }
        var results = JSON.parse(JSON.stringify(json_res));
        //console.log(results)
        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          cb(results);
        }, 1000 * Math.random());
      });
      // this.restaurants = this.loadAll()
    },
    //------------------------------------------------------------------------------
    _goPersonalCenter() {
      goPersonalCenter();
      // this.$router.push({path:'/personalCenter.vue'})
    },
  },
  computed: {
    activeIndex() {
      return this.$route.name;
    },
  },
};
</script>
<style lang="less" scoped>
@import "~@/style/font.less";
.personal {
  position: absolute;
  right: 155px;
  height: 38px;
}
.el-menu-demo {
  border-bottom: none;
  // text-decoration: none;
}
.el-menu-item {
  height: 50px;
}
.home-header {
  width: 100%;
  padding: 12px 0 12px 0;
  background: #409eff;
  .content-wrapper {
    display: flex;
    align-items: center;
    width: 1500px;
    margin: 0 auto;
    .header-title {
      .all-font(MicrosoftYaHei-Bold, 18px, #ffffff, 0);
      margin-left: 20px;
    }
    .nav-left {
      width: 40%;
      position: absolute;
      top: -7px;
      left: 189px;
    }
    .nav-right {
      width: 300px;
      position: absolute;
      right: 228px;
      .search-text {
        width: 500px;
      }
    }
    .header-ul {
      display: flex;
      align-items: center;
      position: absolute;
      right: 40px;
      & > li {
        margin-right: 12px;
        .all-font(MicrosoftYaHei-Bold, 14px, #ffffff, 0);
        cursor: pointer;
      }
      .header-head {
        width: 24px;
        height: 24px;
        border-radius: 24px;
      }
    }
  }
}
</style>
