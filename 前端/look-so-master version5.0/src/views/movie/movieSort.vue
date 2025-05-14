<template>
  <div class="movie-sort">
      <span class="sort-title">分类</span>
      <div class="sort-wrap">
        <div class="sort-wrap-inner">
          <div v-for="(sort,index) in movieSorts_type"
            :key="index" class="sort-tag"
            @click="changeSort_type(sort)">
          <el-tag  :class="{'sort-active': sort === currentSort_type}">{{sort}}</el-tag>
        </div>
        </div>
        <div class="sort-wrap-inner">
          <div v-for="(sort,index) in movieSorts_country"
            :key="index" class="sort-tag"
            @click="changeSort_country(sort)"
            >
          <el-tag  :class="{'sort-active': sort === currentSort_country}">{{sort}}</el-tag>
        </div>
        </div>
        <div class="sort-wrap-inner">
          <div v-for="(sort,index) in movieSorts_time"
            :key="index" class="sort-tag"
            @click="changeSort_time(sort)"
            >
          <el-tag  :class="{'sort-active': sort === currentSort_time}">{{sort}}</el-tag>
        </div>
        </div>
        <div class="sort-wrap-inner">
          <div v-for="(sort,index) in movieSorts_hot"
            :key="index" class="sort-tag"
            @click="changeSort_hot(sort)"
            >
          <el-tag  :class="{'sort-active': sort === currentSort_hot}">{{sort}}</el-tag>
        </div>
        </div>
      </div>
  </div>
</template>

<script>
import bus from '@/js/bus'
export default {
  data () {
    return {
      movieSorts_type: ['全部','剧情', '喜剧', '科幻', '悬疑', '爱情', '治愈', '动作', '惊悚', '战争', '动画'],
      movieSorts_country: ['全部', '中国大陆', '中国香港', '美国', '英国','法国', '韩国', '日本'],
      movieSorts_time: ['全部', '2022', '2021', '2020', '2019', '2018', '2017', '2016', '2015', '更早'],  
      movieSorts_hot: ['高分好评','最新','最热',],
      currentSort_type: '全部',
      currentSort_country: '全部',
      currentSort_time: '全部',
      currentSort_hot: '高分好评',
    }
  },
  created () {
    // this.changeSort()
  },
  methods: {
    changeSort_type (sort) {
      // console.log(sort)
      this.currentSort_type = sort
      bus.$emit('sort', this.currentSort_type, this.currentSort_country, this.currentSort_time, this.currentSort_hot)
    },
    changeSort_country (sort) {
      // console.log(sort)
      this.currentSort_country = sort
      bus.$emit('sort', this.currentSort_type, this.currentSort_country, this.currentSort_time, this.currentSort_hot)
    },
    changeSort_time(sort){
      // console.log(sort)
      this.currentSort_time = sort
      bus.$emit('sort', this.currentSort_type, this.currentSort_country, this.currentSort_time, this.currentSort_hot)
    },
    changeSort_hot(sort){
      // console.log(sort)
      this.currentSort_hot = sort
      bus.$emit('sort', this.currentSort_type, this.currentSort_country, this.currentSort_time, this.currentSort_hot)
    }
  }
}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';
.sort-wrap{
  display: flex;
  flex-direction: column;
}
.sort-wrap-inner{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.movie-sort {
    display: flex;
    padding-top: 43px;
    margin-left:71px;
    .sort-title {
        margin-right: 30px;
        font-weight: bold;
        .bold-title;
    }
    .sort-wrap {
        display: flex;
        flex-wrap: wrap;
        .sort-tag {
            margin: 0 20px 12px 0;
            cursor: pointer;
            &:hover {
              opacity: 0.7;
            }
        }
        .sort-active {
          background-color: rgba(103,194,58,.1);
          border-color: rgba(103,194,58,.2);
          color: #67c23a;
        }
    }
}
</style>
