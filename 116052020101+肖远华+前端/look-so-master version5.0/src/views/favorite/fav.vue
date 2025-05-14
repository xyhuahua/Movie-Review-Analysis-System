<template>
  <div :class="container">
        <i id="end"></i>
    <button class="more-button" id="more-button" aria-label="Menu Button" @click="active">
      <div class="menu-icon-wrapper">
        <div class="menu-icon-line half first"></div>
        <div class="menu-icon-line"></div>
        <div class="menu-icon-line half last"></div>
      </div>
    </button>
    <ul class="more-button-list" v-loading="isLoading">
      <li class="more-button-list-item">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="20"
          height="20"
          viewBox="0 0 24 24"
          fill="none"
          stroke="currentColor"
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
          class="feather feather-settings"
        >
          <circle cx="12" cy="12" r="3" />
          <path
            d="M19.4 15a1.65 1.65 0 00.33 1.82l.06.06a2 2 0 010 2.83 2 2 0 01-2.83 0l-.06-.06a1.65 1.65 0 00-1.82-.33 1.65 1.65 0 00-1 1.51V21a2 2 0 01-2 2 2 2 0 01-2-2v-.09A1.65 1.65 0 009 19.4a1.65 1.65 0 00-1.82.33l-.06.06a2 2 0 01-2.83 0 2 2 0 010-2.83l.06-.06a1.65 1.65 0 00.33-1.82 1.65 1.65 0 00-1.51-1H3a2 2 0 01-2-2 2 2 0 012-2h.09A1.65 1.65 0 004.6 9a1.65 1.65 0 00-.33-1.82l-.06-.06a2 2 0 010-2.83 2 2 0 012.83 0l.06.06a1.65 1.65 0 001.82.33H9a1.65 1.65 0 001-1.51V3a2 2 0 012-2 2 2 0 012 2v.09a1.65 1.65 0 001 1.51 1.65 1.65 0 001.82-.33l.06-.06a2 2 0 012.83 0 2 2 0 010 2.83l-.06.06a1.65 1.65 0 00-.33 1.82V9a1.65 1.65 0 001.51 1H21a2 2 0 012 2 2 2 0 01-2 2h-.09a1.65 1.65 0 00-1.51 1z"
          />
        </svg>
        <span>我的片单</span>
        <el-button round size="mini" @click="chat" style="margin-left:140px">去聊天</el-button>
      </li>

      <li class="more-button-list-item" v-for="(info,index) in FavList" :key="index">
         <el-checkbox-group v-model="checkList">

        <div class="item-left">
          <div class="num" v-if="index<9">
            <p>0{{index+1}}</p>
          </div>
          <div class="num" v-if="index>=9">
            <p>{{index+1}}</p>
          </div>
          <div class="cover-wrap">
            <img
              class="cover"
              :src="info.imageUrl"
              @click="_goMovieDetail(info.movieId)"
            />
          </div>
        </div>
        <div class="item-center">
          <a :href=href+info.movieName><p>{{info.movieName}}</p></a>
          
          <p>{{info.dateTime}}</p>
          <el-checkbox :label=info.movieId>GoChat</el-checkbox>
        </div>
        <div class="item-right" @click="_deleteFav(info.movieId)">
          <svg 
            t="1668590607765"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="2708"
            width="200"
            height="200"
          >
            <path
              d="M928.16 144H736V64a32 32 0 0 0-32-32H320a32 32 0 0 0-32 32v80H95.84a32 32 0 0 0 0 64H129.6l77.92 698.656A96 96 0 0 0 302.912 992h418.144a96.032 96.032 0 0 0 95.424-85.344L894.4 208h33.728a32 32 0 0 0 0.032-64zM352 96h320v48H352V96z m400.896 803.552a32 32 0 0 1-31.808 28.448H302.912a32 32 0 0 1-31.808-28.448L193.984 208h636.032l-77.12 691.552z"
              p-id="2709"
              fill="#1c3991"
            ></path>
            <path
              d="M608 820.928a32 32 0 0 0 32-32V319.104a32 32 0 0 0-64 0v469.824a32 32 0 0 0 32 32zM432 820.928a32 32 0 0 0 32-32V319.104a32 32 0 0 0-64 0v469.824a32 32 0 0 0 32 32z"
              p-id="2710"
              fill="#1c3991"
            ></path>
          </svg>
        </div>     
        </el-checkbox-group>
      </li>

    </ul>
  </div>
</template>

<script>
import {getFavCar} from '@/js/api'
import { goMovieDetail,goChat} from "@/js/router";
import { deleteFav } from "@/js/api";
export default {
  data() {
    return {
      ranks: [],
      FavList:[],
      currentPage: 1,
      isLoading: true,
      pageSize: 10,
      number: 0,
      container:"list-container",
      href:'https://so.iqiyi.com/so/q_',
      checkList: []
    };
  },
  created() {
    this._getFavCar();
  },
  methods: {
    active(){
        if(this.container == 'list-container active'){
            this.container = 'list-container';
            this._getFavCar()
        }else{
            this.container = 'list-container active';
            this._getFavCar()
        }
        
    },
    currentChange(page) {
      this.currentPage = page;
      this._getAllRank();
    },
    _goMovieDetail(movieId) {
      goMovieDetail(movieId);
    },
    _getFavCar() {
      this.isLoading = true;
      var username = window.sessionStorage.getItem("username")
      console.log(username);
      getFavCar(username).then((res) => {
        // console.log(res)
        this.FavList = res.FavList;
        this.isLoading = false;
        console.log(this.checkList);
      });
    },
    _deleteFav(movieId){
      console.log(movieId);
      this.isLoading = true;
      var username = window.sessionStorage.getItem("username")
      deleteFav(movieId,username).then((res) => {
        // console.log(res)
        this._getFavCar();
      });


    },
      chat(){
        if(this.checkList.length>0 && this.checkList.length<7){
          var roomId = '';
          this.checkList.sort()
          console.log(this.checkList);
          for(var i=0;i<this.checkList.length;i++){
            roomId = roomId + this.checkList[i]
          }
          console.log(roomId);
          goChat(roomId,this.checkList);
        }else{
          this.$message({
              message: "请选择1-6部电影 进行匹配哦",
              type: 'warning'
          });
        }

      },
      _goMovieDetail (movieId) {
      goMovieDetail(movieId)
    }
  },
};
</script>




<style scoped>
.list-container {
  left: 95%;
  top: 90%;
  position: fixed;
  z-index: 6666;
}
.list-container.active .more-button-list {
  opacity: 1;
  transform: scale(1);
}
.list-container.active .more-button-list-item {
  -webkit-animation: fadeInItem 0.6s 0.2s forwards;
  animation: fadeInItem 0.6s 0.2s forwards;
}
.list-container.active .more-button-list-item:nth-child(2) {
  -webkit-animation-delay: 0.4s;
  animation-delay: 0.4s;
}
.list-container.active .more-button-list-item:nth-child(3) {
  -webkit-animation-delay: 0.6s;
  animation-delay: 0.6s;
}
.list-container.active .more-button-list-item:nth-child(4) {
  -webkit-animation-delay: 0.8s;
  animation-delay: 0.8s;
}
.list-container.active .more-button-list-item:nth-child(5) {
  -webkit-animation-delay: 1.0s;
  animation-delay: 1.0s;
}
.list-container.active .more-button-list-item:nth-child(6) {
  -webkit-animation-delay: 1.2s;
  animation-delay: 1.2s;
}
.list-container.active .more-button-list-item:nth-child(n+7) {
  -webkit-animation-delay: 1.4s;
  animation-delay: 1.4s;
}


.list-container.active .more-button {
  -webkit-animation: onePulse 0.6s forwards linear;
  animation: onePulse 0.6s forwards linear;
}
.list-container.active .menu-icon-wrapper {
  transform: rotate(-45deg);
}
.list-container.active .menu-icon-line.first {
  transform: rotate(-90deg) translateX(1px);
}
.list-container.active .menu-icon-line.last {
  transform: rotate(-90deg) translateX(-1px);
}

.more-button {
  background-color: #5c67ff;
  box-shadow: 0px 0px 0px 4px rgba(92, 103, 255, 0.3);
  border-radius: 50%;
  width: 50px;
  height: 50px;
  border: none;
  padding: 0;
  cursor: pointer;
  transition: 0.2s ease-in;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  position: relative;
  z-index: 2;
}
.more-button:hover,
.more-button:focus {
  box-shadow: 0px 0px 0px 8px rgba(92, 103, 255, 0.3);
  background-color: #4854ff;
}
.more-button:focus {
  outline: 0;
}
.more-button-list {
  background-color: #fff;
  border-radius: 8px;
  list-style-type: none;
  width: 340px;
  height: 450px;
  box-shadow: 0px 0px 4px 4px rgba(150, 157, 249, 0.16);
  padding: 0;
  padding: 6px;
  position: absolute;
  overflow-y: scroll;
  right: 24px;
  bottom: 0;
  opacity: 0;
  transform: scale(0);
  transform-origin: bottom right;
  transition: all 0.3s ease 0.1s;
}
*::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0);
  background-color: rgba(204, 204, 204, 0);
} /* 滚动条的滑轨背景颜色 */

*::-webkit-scrollbar {
  width: 6px;
  height: 6px;
  background-color: rgba(204, 204, 204, 0);
}

*::-webkit-scrollbar-thumb {
  background-color: rgba(204, 204, 204, 0.651);
  border-radius: 5px;
} /* 滑块颜色 */

.more-button-list li {
  opacity: 0;
}
.more-button-list-item {
  display: flex;
  align-items: center;
  color: #1c3991;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;
  position: relative;
  transition: 0.2s ease-in;
  transform: translatex(-10px);
}
.more-button-list-item:hover {
  color: #5c67ff;
}
.more-button-list-item:after {
  content: "";
  position: absolute;
  height: 1px;
  width: calc(100% - 24px);
  left: 12px;
  bottom: 0;
  background-color: rgba(132, 160, 244, 0.1);
}
.more-button-list-item:last-child:after {
  display: none;
}
.more-button-list-item svg {
  width: 18px;
  height: 18px;
}
.more-button-list-item span {
  display: inline-block;
  line-height: 20px;
  font-size: 14px;
  margin-left: 8px;
}

@-webkit-keyframes onePulse {
  0% {
    box-shadow: 0px 0px 0px 0px rgba(92, 103, 255, 0.3);
  }
  50% {
    box-shadow: 0px 0px 0px 12px rgba(92, 103, 255, 0.1);
  }
  100% {
    box-shadow: 0px 0px 0px 4px rgba(92, 103, 255, 0.3);
  }
}

@keyframes onePulse {
  0% {
    box-shadow: 0px 0px 0px 0px rgba(92, 103, 255, 0.3);
  }
  50% {
    box-shadow: 0px 0px 0px 12px rgba(92, 103, 255, 0.1);
  }
  100% {
    box-shadow: 0px 0px 0px 4px rgba(92, 103, 255, 0.3);
  }
}
@-webkit-keyframes fadeInItem {
  100% {
    transform: translatex(0px);
    opacity: 1;
  }
}
@keyframes fadeInItem {
  100% {
    transform: translatex(0px);
    opacity: 1;
  }
}
.socials {
  position: fixed;
  bottom: 16px;
  right: 16px;
  display: flex;
  align-items: center;
}

.social-link {
  color: #fff;
  display: flex;
  align-items: center;
  cursor: pointer;
  text-decoration: none;
  margin-right: 12px;
}

.menu-icon-wrapper {
  border-radius: 2px;
  width: 20px;
  height: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  cursor: pointer;
  transition: transform 330ms ease-out;
}

.menu-icon-line {
  background-color: #fff;
  border-radius: 2px;
  width: 100%;
  height: 2px;
}
.menu-icon-line.half {
  width: 50%;
}
.menu-icon-line.first {
  transition: transform 300ms cubic-bezier(0.52, -0.8, 0.52, 0.52);
  transform-origin: right;
}
.menu-icon-line.last {
  align-self: flex-end;
  transition: transform 300ms cubic-bezier(0.52, -0.8, 0.52, 0.52);
  transform-origin: left;
}

.item-left {
  width: 130px;
  height: 100px;
}

.cover-wrap {
  float: left;
  width: 80px;
  height: 100px;
  overflow: hidden;
  border-radius: 5px;
}
.num {
  float: left;
  font-size: 35px;
  margin-right: 10px;
  margin-left: -5px;
}
.num p {
  font-size: 20px;
  line-height: 70px;
}
.cover {
  width: 80px;
  height: 100px;
  height: 100px;
}
.item-center {
  width: 190px;
  float: left;
}
.item-center p {
  display: -webkit-box;
  font-size: 14px;
  line-height: 20px;
  overflow: hidden;
  /*autoprefixer:off*/
  -webkit-box-orient: vertical;
  /*autoprefixer:on*/
  -webkit-line-clamp: 2;
  text-overflow: ellipsis;
  margin-block-start: 0em;
  margin-block-end: 0em;
}

.item-right {
  float: right;
  margin-left: 4px;
  height: 18px;
  width: 18px;
}
.item-right :hover{
   float: right;
   margin-left: 0px;
   margin-right: -4px;
   margin-top: -4px;
   height: 22px;
   width: 22px;
}
.el-checkbox-group{
  display: flex;
  width:320px;
}
.u-flyer{display: block;width: 50px;height: 50px;border-radius: 50px;position: fixed;z-index: 9999;}
</style>