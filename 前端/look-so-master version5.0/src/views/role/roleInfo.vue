<template>
  <div class="role-info">
    <el-card>
      <div slot="header" class="name clearfix">
        <span class="title" v-html="nameRomoveFL(roleInfo.actorname)"></span>
      </div>
      <div class="info-wrap">
        <div class="cover-wrap">
          <img class="cover"
               :src="nameRomoveFL(roleInfo.actortouxiang)"
               alt="">
        </div>
        
        <div class="score-wrap">
          <!-- <div class="title">Look-So评分</div> -->
          <div class="star-wrap">
            <div class="text-wrap">
          <div class="director" v-html="'中文名：' + getChinaName(roleInfo.actorname)">中文名：</div>
          <div class="actors" v-html="'外文名：' + getEnglishName(roleInfo.actorname)">外文名：</div>
          <div class="sort">性别：{{roleInfo.sex}}</div>
          <div class="show">职业：{{roleInfo.occupation}}</div>
          <div class="time">星座：{{roleInfo.constellation}}分钟</div>
          <div class="country">出生地：{{roleInfo.birthplace}}</div>
          <div class="country">出生日期：{{roleInfo.birthday}}</div>
          <div class="country">imdb：{{roleInfo.imdb}}</div>    
        </div>
          </div>
        </div>
      </div>
      <div class="intro-wrap">
        <div class="intro-title">{{getChinaName(roleInfo.actorname)}}的简介</div>
        <p class="intro-content">{{roleInfo.actorinfo}}</p>
      </div>
    </el-card>
  </div>
</template>

<script>
// import {getMovieDetail} from '@/js/api'
export default {
  data() {
    return {
      //   movieInfo: {},
      isCollect: "收藏"
    };
  },
  props: {
    roleInfo: {
      type: Object,
      default: null
    }
  },
  computed: {
    score: {
      get: function() {
        return this.roleInfo.reviewScore / 2;
      },
      set: function() {}
    }
  },
  methods: {
    nameRomoveFL(str) {
      str = str.substring(1);
      str = str.substring(0, str.length - 1);
      return str;
    },
    getChinaName(name){
      name = name.substring(1, name.indexOf(" ") )
      return name;
    },
    getEnglishName(name){
      name = name.substring(name.indexOf(" "),name.length-1)
      return name;
    }
  }
};
</script>
<style lang="less" scoped>
@import "~@/style/font.less";
.role-info {
  .name {
    .bold-title;
  }
  .info-wrap {
    display: flex;
    .cover-wrap {
      width: 135px;
      height: 189px;
      .cover {
        width: 100%;
        height: 100%;
        margin-left: 27px;
      }
    }
    .text-wrap {
      margin-left: 12px;
      .director,
      .actors,
      .sort,
      .show,
      .time,
      .country {
        line-height: 24px;
        .all-font(Microsoft YaHei, 14px, #666666, 0);
      }
    }
    .score-wrap {
      margin-left: auto;
      padding-left: 20px;
      border-left: 1px solid #eaeaea;
      .title {
        .all-font(Microsoft YaHei, 12px, #9b9b9b, 0);
      }
      .star-wrap {
        display: flex;
        align-items: center;
        margin-right: 402px;
        .score-num {
          font-weight: bold;
          .all-font(Microsoft YaHei, 24px, #494949, 0);
        }
      }
      .people-num {
        .all-font(Microsoft YaHei, 12px, #409eff, 0);
      }
    }
  }
  .intro-wrap {
    margin-top: 11px;
    width: 94%;
    margin-left: 29px;
    .intro-title {
      .all-font(Microsoft YaHei, 18px, #409eff, 0);
    }
    .intro-content {
      margin-top: 11px;
      text-indent: 2em;
      line-height: 19px;
      .all-font(Microsoft YaHei, 13px, #666666, 1px);
    }
  }
}
</style>
