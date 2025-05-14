<template>
  <div>
    <el-container style="height: 800px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: white">
        <el-menu :default-openeds="['1','2','3']" :default-active="activeIndex">

          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-postcard"></i>信息修改</template>
            <el-menu-item-group>

              <router-link class="alink"
                           to="/homepage/personalCenter/info">
                <el-menu-item index="1-1">
                  基本信息
                </el-menu-item>

              </router-link>

              <router-link class="alink"
                           to="/homepage/personalCenter/modifyInfo">
                <el-menu-item index="1-2">
                  信息修改
                </el-menu-item>
              </router-link>

              <router-link class="alink"
                           to="/homepage/personalCenter/modifyHeader">
                <el-menu-item index="1-3">

                  头像修改

                </el-menu-item>
              </router-link>

              <router-link class="alink"
                           to="/homepage/personalCenter/accountManagement">
                <el-menu-item index="1-4">

                  账号管理

                </el-menu-item>
              </router-link>

            </el-menu-item-group>

          </el-submenu>

          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-upload"></i>历史记录</template>
            <el-menu-item-group>
              <router-link class="alink"
                           to="/homepage/personalCenter/playRecord">
                <el-menu-item index="2-1">
                  播放记录
                </el-menu-item>
              </router-link>

              <!-- <router-link class="alink"
                           to="/myspace/publicArticle">
                <el-menu-item index="2-2">
                  PUBLIC
                </el-menu-item>
              </router-link>

              <router-link class="alink"
                           to="/myspace">
                <el-menu-item index="2-3">
                  全部文章
                </el-menu-item>
              </router-link>

              <router-link class="alink"
                           to="/myspace/columnArticle">
                <el-menu-item index="2-4">
                  我的专栏
                </el-menu-item>
              </router-link>

              <router-link class="alink"
                           to="/myspace/statusArticle">
                <el-menu-item index="2-5">
                  审核状态
                </el-menu-item>
              </router-link>-->
            </el-menu-item-group>

          </el-submenu>

          <!-- <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-edit"></i>文章书写
            </template>
            <el-menu-item-group>
              <router-link class="alink"
                           to="/writeblog">
                <el-menu-item index="3-1">
                  文章书写
                </el-menu-item>
              </router-link>
            </el-menu-item-group>

          </el-submenu> -->

          <!-- <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-postcard"></i>社区</template>

            <el-menu-item-group>

              <router-link class="alink"
                           to="/myspace/myjoincolums">
                <el-menu-item index="4-1">
                  我的社区
                </el-menu-item>
              </router-link>

              <router-link class="alink"
                           to="/myspace/mycolums">
                <el-menu-item index="4-2">
                  我创建的社区
                </el-menu-item>
              </router-link>

            </el-menu-item-group>
          </el-submenu> -->

          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-star-on"></i>收藏</template>

            <el-menu-item-group>

              <!-- <router-link class="alink"
                           to="/myspace/collectionAns">
                <el-menu-item index="5-1">
                  问答收藏
                </el-menu-item>
              </router-link> -->

              <router-link class="alink"
                           to="/homepage/personalCenter/myCollect">
                <el-menu-item index="5-2">
                  我的收藏
                </el-menu-item>
              </router-link>

              <!-- <router-link class="alink"
                           to="/myspace/collectionColums">
                <el-menu-item index="5-3">
                  社区收藏
                </el-menu-item>
              </router-link> -->

            </el-menu-item-group>

          </el-submenu>
        </el-menu>
        <!-- <router-view class="router-wrap"></router-view> -->
      </el-aside>
      <el-container style="background-image: url(/static/logo/noticesbg.jpg);
        background-repeat: no-repeat;
        background-size:100% 100%;
        ">
        <el-main>
          <div style="width: 80%;margin: 0 auto">
            <router-view></router-view>
          </div>
        </el-main>
      </el-container>

    </el-container>

  </div>
</template>

<script>
export default {
  name: "user",
  data () {

    return {

    }
  },
  computed: {
    activeIndex () {
      return this.$route.name
    }
  }
}
</script>

<style scoped>
.el-header {
  background-color: #e5efe2;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
.router-link-active {
  text-decoration: none;
}

.alink {
  text-decoration: none;
}
</style>
