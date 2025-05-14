<template>
  <div class="role">
    <div class="role-left">
      <role-content></role-content>
    </div>
  </div>
</template>
<script>

import RoleContent from './role/roleContent'
export default {
  components: {
    RoleContent
  },
  data () {
    return {
    }
  }
}

</script>
<style lang="less" scoped>
.role {
  .role-left {
    margin: 0 auto;
    width: 70%;
  }
}
</style>
