<template>
  <div class="actor-slot">
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
.actor-slot {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 115px;
            // margin-right: 20px;
            .cover-wrap {
                height: 162px;
                .actor-head {
                    width: 100%;
                    height: 100%;
                }
            }
            .actor-nick {
                margin-top: 12px;
                .all-font(PingFangSC-Regular, 14px, #333333, -0.34px);
                cursor: pointer;
                &:hover {
                    color: #409EFF;
                }
            }
        }
</style>
