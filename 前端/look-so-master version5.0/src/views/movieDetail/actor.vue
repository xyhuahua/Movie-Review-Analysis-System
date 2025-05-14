<template>
  <div class="actors">
    <el-card class="box-card"
             type="border-card"
             @tab-click="isMore"
             v-loading="isLoading">
      <div slot="header"
           class="clearfix">
        <span class="actor-title">演员列表</span>
      </div>
      <div class="info-wrapper"
           @click="_goMovieDetail(item.movieId)">
        <actor-slot class="actor-info"
                    v-for="(info,index) in Actor.slice(0,7)"
                    :key="index">
          <div class="cover-wrap">
            <img class="actor-head"
                 :src="info.actortouxiang"
                 alt=""
                 @click="_goMovieDetail(info.movieId)">
          </div>
          <span class="actor-nick"
                @click="_goMovieDetail(info.actorNick)">{{info.name}}</span>
          <!-- <span class="movie-score">{{info.reviewScore}}</span> -->
        </actor-slot>
      </div>
    </el-card>
  </div>
</template>

<script>
import actorSlot from '@/components/actorSlot'
import { getActors } from '@/js/api'
import { goMovie, goMovieDetail } from '@/js/router'
import {getMovieDetail} from '@/js/api'
const MORE_INDEX = 'tab-7'
export default {
  components: {
    actorSlot
  },
  data () {
    return {
      actors: [],
      isLoading: true,
      movieInfo:{},
      urlDirector:[],
      Actor:[]

    }
  },
  created () {
    this._getActors()
    this._getMovieDetail()
  },
  methods: {
    _getMovieDetail () {
    getMovieDetail(this.movieId).then((res) => {
      
    console.log("]]]]]]]")
        this.movieInfo = res.movie
        console.log(this.movieInfo)
        this.getActorId(this.movieInfo)
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
        var actortouxiang
        await this.$axios.get("http://localhost:8080/getActor",{
          params:{
            actorId: actorId
          }
        }).then(res =>{
          console.log(res.data.role)
          actortouxiang = res.data.role.actortouxiang
        }
        )
        actortouxiang = actortouxiang.replace(/\[|]/g,'')
        this.urlDirector.push(actortouxiang)
        console.log(this.urlDirector)
      },
      async getActor2(actorId){
        //console.log(actorId)
        var actortouxiang
        var name
        await this.$axios.get("http://localhost:8080/getActor",{
          params:{
            actorId: actorId
          }
        }).then(res =>{
          console.log(res.data.role)
          name = res.data.role.actorname
          actortouxiang = res.data.role.actortouxiang
          //console.log(name)
        }
        )
        //console.log(name)
        name = name.replace(/\[|]/g,'')
        actortouxiang = actortouxiang.replace(/\[|]/g,'')
        this.Actor.push({"name":name,"actortouxiang":actortouxiang})
        console.log(this.Actor)
      },
    isMore (tab, event) {
      if (event.target.getAttribute('id') === MORE_INDEX) {
        goMovie()
      }
    },
    _getActors () {
      getActors().then((res) => {
        this.actors = res.actors
        this.isLoading = false
        // console.log(this.recommendMovies)
      })
    },
    _goMovieDetail (movieId) {
      goMovieDetail(movieId)
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
.actors {
  .box-card {
    .clearfix {
      .actor-title {
        .all-font(MicrosoftYaHei-Bold, 18px, #005198, -0.43px);
      }
    }
    .info-wrapper {
      display: flex;
      justify-content: space-around;
      align-items: center;
    }
  }
}
</style>
