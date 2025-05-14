<template>
  <div class="user-slot">
    <slot></slot>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  }
}

</script>
<style lang="less" scoped>
@import '~@/style/font.less';
.user-slot {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 67px;
            margin-top: 8px;
            // margin-right: 20px;
            .cover-wrap {
                height: 65px;
                .user-head {
                    width: 100%;
                    height: 100%;
                }
            }
            .movie-score {
                margin-top: 8px;
                .all-font(PingFangSC-Regular, 12px, #EF990F, -0.29px);
            }
        }
</style>
