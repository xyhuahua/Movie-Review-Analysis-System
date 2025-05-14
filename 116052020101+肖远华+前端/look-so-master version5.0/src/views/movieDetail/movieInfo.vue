<template>
  <div class="movie-info">
    <el-card>
      <div slot="header" class="name clearfix">
        <a :href="movieInfo.url" target="_blank" style="text-decoration: none"><span class="title">{{movieInfo.title}}</span></a>
      </div>
      <div class="info-wrap">
        <div class="cover-wrap">
         <a :href="movieInfo.url" target="_blank"> <img class="cover"
               :src="movieInfo.cover_url"
               alt=""></a>
        </div>
        <div class="text-wrap" style="">

          <div class="director">
            导演：<span v-for="name1 in nameDirector">{{name1}} </span>
          </div>
          <div class="actors">
            主演：<span v-for="name2 in nameActor">{{name2}} </span>
          </div>
        
          
          <div class="sort">类别：{{movieInfo.types}}</div>
          <div class="show">上映时间：{{movieInfo.release_date}}</div>
          <div class="time">片长：{{movieInfo.pianchang}}</div>
          <div class="country">国家：{{movieInfo.regions}}</div>
          <el-button type="warning" class="addcar" @click="addCollect(movieInfo)"
                     round>{{isCollect}}</el-button>
          <!-- <el-button type="success" 
                     round><a :href=href+movieInfo.title>跳转播放</a></el-button> -->
          <el-button  type="success"
                     round>
            <a :href=href+movieInfo.title target="_blank" style="text-decoration: none;color:white">
              观看电影
            </a>
          </el-button>
                     
        </div>
        <div class="score-wrap">
          <div class="title">评分</div>
          <div class="star-wrap">
            <div class="score-num">{{movieInfo.score}}</div>
            <el-rate v-model="score"
                     disabled>
            </el-rate>
          </div>
          <div class="people-num">{{movieInfo.vote_count}}人评价</div>
        </div>
      </div>
      <div class="intro-wrap">
        <div class="intro-title">{{movieInfo.title}}的内容简介</div>
        <p class="intro-content">{{movieInfo.description}}</p>
      </div>
    </el-card>

  </div>
</template>

<script>
// import {getMovieDetail} from '@/js/api'
import {getMovieDetail} from '@/js/api'
export default {
  data () {
    return {
      movieInfo: {},
      isCollect: '收藏',
      href:'https://so.iqiyi.com/so/q_',
      nameDirector:[],
      nameActor:[]
    }
  },
  created(){
   this._getMovieDetail()
  },
  methods: {
    _getMovieDetail () {
    getMovieDetail(this.movieId).then((res) => {
        this.movieInfo = res.movie
        console.log(this.movieInfo)
        this.getActorId(this.movieInfo)
      })
      },
    addCollect(movieInfo){
      console.log(1)
      console.log(movieInfo)
      var username = sessionStorage.getItem("username")
      var movieId = movieInfo.movieId
      var movieName = movieInfo.title
      var movieUrl = movieInfo.url
      var imageUrl = movieInfo.cover_url
      var actors = movieInfo.actors
      
      console.log(movieId,movieName,movieUrl,imageUrl,actors)
      
      this.$axios.get('http://localhost:8080/user/addOneCollect',{
      params: {
        username: username,
        movieId: movieId,
        movieName: movieName,
        movieUrl: movieUrl,
        imageUrl: imageUrl,
        actors: actors
      }
      }).then((res) => {
           return Promise.resolve(res.data)
      })
      },
      getActorId(movieInfo){
        console.log("------------------")
        let actorIdD = []
        let actorIdA = []
        let actor = movieInfo.actors
        console.log(actor)  
        
        for(let i = 0;i < actor.length;i++) {
          if(actor[i].actortype==='导演 Director')
          actorIdD.push(actor[i].actorid)
        }
        for(let i = 0;i < actor.length;i++) {
          if(actor[i].actortype==='演员 Cast')
          actorIdA.push(actor[i].actorid)
        }
        
        console.log("??????????")
        console.log(actorIdD)
        console.log(actorIdA)
        for(let j =0;j<actorIdD.length;j++){
          console.log("!!!")
          this.getActor(actorIdD[j])
        }
        for(let j =0;j<actorIdA.length;j++){
          console.log("!!!")
          this.getActor2(actorIdA[j])
        }
        
      },
      async getActor(actorId){
        //console.log(actorId)
        var name
        await this.$axios.get("http://localhost:8080/getActor",{
          params:{
            actorId: actorId
          }
        }).then(res =>{
          console.log(res.data.role)
          name = res.data.role.actorname
        }
        )
        this.nameDirector.push(name)
      },
      async getActor2(actorId){
        //console.log(actorId)
        var name
        await this.$axios.get("http://localhost:8080/getActor",{
          params:{
            actorId: actorId
          }
        }).then(res =>{
          console.log(res.data.role)
          name = res.data.role.actorname
          //console.log(name)
        }
        )
        //console.log(name)
        this.nameActor.push(name)
      }
  },

  props: {
    movieInfo: {
      type: Object,
      default: null
    }
  },
  computed: {
    movieId () {
      return JSON.parse(localStorage.getItem('currentMovie'))
    }
  }
}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';
.movie-info {
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
      }
    }
    .text-wrap {
      width: 560px;
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
    margin-top: 8px;
    .intro-title {
      .all-font(Microsoft YaHei, 18px, #409eff, 0);
    }
    .intro-content {
      margin-top: 8px;
      text-indent: 2em;
      line-height: 18px;
      .all-font(Microsoft YaHei, 12px, #666666, 1px);
    }
  }
}
</style>
