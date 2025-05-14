<template>
  <div v-loading="isLoading">
    <div  style="width: 80%;margin-left: 1%" class="main" >
      <el-card shadow="hover" v-for="(record,index) in playRecord" :key="index" >

        <div style="height:150px">
          <div  style="width:14%;height: 100%;border-radius: 100px;display:inline-block;">
            <img style="width:100%;height: 100%;"  :src="record.imageUrl" @click="_goMovieDetail(record.movieId)" class="image">
          </div>
          <div class="item-right">
                <div class="name" style="font-weight:bold;font-size:15px;" @click="_goMovieDetail(record.movieId)">{{record.movieName}}</div>
                <div class="director">添加时间：{{record.dateTime}}</div>
            </div>

          <div  style="width:18%;height: 100%;
          display:inline-block;
          ">
            <div style="display: inline-block;width:80%;height: 60%">
              <el-button type="warning" round style="height: 60%;width: 70%;display: inline-block;position: relative;top:-7px" @click="_deleteFav(record.movieId)">
                <i class="el-icon-delete"></i>
              </el-button>
              <p style="text-align: center">{{record.data}}</p>
              <br>
            </div>

          </div>

        </div>
        <br>
      </el-card>
    </div>
    <br>
    <div class="footer" style="margin: 0 auto;width: 100%;">
      <div class="block" >
        <el-pagination
          background
          layout="total, prev, pager, next, jumper"
          :total=total>
        </el-pagination>
      </div>
    </div>

  </div>

</template>

<script>
import {getFavCar} from '@/js/api'
import { goMovieDetail } from "@/js/router";
import { deleteFav } from "@/js/api";
export default {
  data() {
    return {
      ranks: [],
      playRecord:[],
      currentPage: 1,
      isLoading: true,
      pageSize: 10,
      number: 0,
      container:"list-container",
      href:'https://so.iqiyi.com/so/q_'
    };
  },
  created() {
    this._getPlayRecord();
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
    _getPlayRecord() {
      this.isLoading = true;
      var username = window.sessionStorage.getItem("username")
      console.log(username);
      getFavCar(username).then((res) => {
        console.log(res)
        this.playRecord = res.FavList;
        this.isLoading = false;
      });
    },
    _deleteFav(movieId){
      console.log(movieId);
      this.isLoading = true;
      var username = window.sessionStorage.getItem("username")
      deleteFav(movieId,username).then((res) => {
        // console.log(res)
        this._getPlayRecord();
      });

    }
  },
};
</script>

<style lang="less" scoped>
.record{
  width: 25em;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.item-right{
  display:inline-block;
  margin-left: 5%;
  width: 60%;
  position:relative;
  top:17px;
  font-size: 13px;
  line-height: 16px;
}
</style>
