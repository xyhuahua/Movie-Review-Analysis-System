<template>
  <div class="awards">
    <el-card>
      <div slot="header" class="name clearfix">
        <span class="title">获奖情况</span>
      </div>
      <div class="info-wrap" v-for="item in roleInfo.award" :key="item">
        <div class="director"   >
            <p>{{item.year}}&nbsp;{{item.awards1}}&nbsp;{{item.awards2}}&nbsp;电影：&laquo;{{item.awards3}}&raquo;</p>
        </div>
        
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  props: {
    roleInfo: {
      type: Object,
      default: null
    }
  },
  methods:{
  }
}
</script>
<style lang="less" scoped>
@import '~@/style/font.less';
.awards {
  .name {
    // .bold-title;
    .all-font(MicrosoftYaHei-Bold, 18px, #005198, -0.43px);
  }
  .info-wrap {
    display: flex;
    font-size:12px;
    font-weight: 500;
    line-height: 20px;
    font-family: 'Courier New', Courier, monospace;
    color: #004183;

  }
}
</style>

