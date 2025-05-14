<template>
  <div style="" class="box">
    <el-cascader
      :key="new Date()"
      ref="cascaderAddr"
      :options="options"
      :props="props"
      :style="{ width: inputwidth + 'px' }"
      v-model="chooseAddressList"
      :placeholder="defaultaddress"
      :collapse-tags="iscollapsetag"
      @change="visibleChange"
    ></el-cascader>
    <div v-if="isshowtitle" style="color: red; margin-left: 10px">
      {{ title }}
    </div>
  </div>
</template>
<script>
/* 
  props参数：
           inputwidth:               输入框的宽度
           addresstype:              地址填写要求,4: 省市区街道四级必填，3：省市区三级必填，2：省市必填， 0:没有限制
           addresslevel:             请求接口的地址级别，PROVINCE：省份，CITY： 省市，DISTRICT：省市区，STREET：省市区街道
           currentaddresscode:       地址反填，传入当前地区的code，如：'安徽省安庆市大观区大观开发区' 要传入 '340803400000',
           isshowtitle:              是否显示提示信息,
           ismultiple:               是否可以多选,
           iscollapsetag:            多选的标签是否折叠,
           currentaddresscodelist:   多选时地址反填。如："安徽省/安庆市，安徽省/蚌埠市"，要传入['340800000000','340300000000']
  $emit事件：
           getaddressinfo       将所选地址信息传递到父组件
*/
import 'element-ui/lib/theme-chalk/index.css'
export default {
  props: {
    inputwidth: {
      type: Number,
      default: 260,
    },
    addresstype: {
      type: Number,
      default: 0,
    },
    addresslevel: {
      type: String,
      default: 'STREET',
    },
    isshowtitle: {
      type: Boolean,
      default: true,
    },
    currentaddresscode: {
      type: String,
      default: '',
    },
    currentaddresscodelist: {
      type: Array,
      default: [],
    },
    ismultiple: {
      type: Boolean,
      default: false,
    },
    iscollapsetag: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      options: [],
      title: '',
      chooseAddressList: [],
      props: {},
    }
  },
  mounted() {
    this.getTitle()
    // this.init()
  },
  methods: {
    visibleChange() {
      let areaInfo = this.$refs['cascaderAddr'].getCheckedNodes()
      if (areaInfo.length !== 0) {
        if (this.addresstype !== 0 && areaInfo[0].level < this.addresstype) {
          _.ui.notify({
            type: 'warning',
            message: this.getLevelInfo(),
          })
          this.chooseAddressList = []
          this.$refs.cascaderAddr.dropDownVisible = false
          console.log(this.$refs.cascaderAddr.dropDownVisible)
        } else {
          let addressInfo
          if (this.ismultiple) {
            addressInfo = []
            areaInfo.forEach((e) => {
              addressInfo.push(e.data)
            })
          } else {
            addressInfo = areaInfo[0].data
          }
          if (this.ismultiple) {
            addressInfo.forEach((e, i) => {
              e.parentList = []
              areaInfo[i].pathNodes.forEach((t, j) => {
                if (j < areaInfo[i].pathNodes.length - 1)
                  e.parentList.push(t.data)
              })
            })
          } else {
            addressInfo.parentList = []
            areaInfo[0].pathNodes.forEach((t, j) => {
              if (j < areaInfo[0].pathNodes.length - 1)
                addressInfo.parentList.push(t.data)
            })
          }
          this.$emit('getaddressinfo', addressInfo)
          console.log(
            this.chooseAddressList,
            areaInfo,
            addressInfo,
            'selectedOptions'
          )
        }
      }
    },
    handleChange(value) {
      // console.log(value)
      // this.$refs.cascader.dropDownVisible = false //监听值发生变化就关闭它
    },
    init() {
      console.log(this.currentaddresscode)
      // this.$forceUpdate()
      let that = this
      if (this.ismultiple) {
        this.props = {
          checkStrictly: true,
          value: 'code',
          label: 'name',
          multiple: true,
        }
      } else {
        this.props = { checkStrictly: false, value: 'code', label: 'name' }
      }
      // if (!sessionStorage.getItem('addressList')) {
      _.util.ajax_post_factory(
        'gw/gis-district-serve/district/getRegionNode',
        {
          parentCode: '0',
          level: this.addresslevel,
        },
        function (res) {
          if (res.success == true) {
            that.options = res.data
            // 由于接口有时会很慢，第一次请求成功后缓存起来，之后用缓存的数据
            // sessionStorage.setItem('addressList', JSON.stringify(res.data))
            if (
              that.currentaddresscode !== '' &&
              that.currentaddresscode !== undefined
            ) {
              that.chooseAddressList = that.getTreeDeepArr(
                that.currentaddresscode,
                that.options
              )
            } else {
              that.chooseAddressList = []
            }
            if (that.ismultiple) {
              if (that.currentaddresscodelist.length !== 0) {
                that.chooseAddressList = []
                that.currentaddresscodelist.forEach((e) => {
                  that.chooseAddressList.push(
                    that.getTreeDeepArr(e, that.options)
                  )
                })
              } else {
                that.chooseAddressList = []
              }
            }
          }
        }
      )
      // } else {
      //   this.options = JSON.parse(sessionStorage.getItem('addressList'))
      //   if (this.currentaddresscode !== '') {
      //     this.chooseAddressList = this.getTreeDeepArr(
      //       this.currentaddresscode,
      //       this.options
      //     )
      //   }
      //   if (that.currentaddresscodelist.length !== 0) {
      //     that.chooseAddressList = []
      //     that.currentaddresscodelist.forEach((e) => {
      //       that.chooseAddressList.push(that.getTreeDeepArr(e, that.options))
      //     })
      //   }
      //   console.log(this.options)
      // }
    },
    getTitle() {
      switch (this.addresstype) {
        case 4:
          this.title = '（省、市、区、街道为必选项）'
          break
        case 3:
          this.title = '（省、市、区为必选项）'
          break
        case 2:
          this.title = '（省、市为必选项）'
          break
      }
    },
    getLevelInfo() {
      switch (this.addresstype) {
        case 4:
          return '请选择省、市、区、街道'
        case 3:
          return '请选择省、市、区'
        case 2:
          return '请选择省、市'
      }
    },
    // 反填地址
    getTreeDeepArr(key, treeData) {
      let arr = [] // 在递归时操作的数组
      let returnArr = [] // 存放结果的数组
      let depth = 0 // 定义全局层级
      // 定义递归函数
      function childrenEach(childrenData, depthN) {
        for (var j = 0; j < childrenData.length; j++) {
          depth = depthN // 将执行的层级赋值 到 全局层级
          arr[depthN] = childrenData[j].code
          if (childrenData[j].code == key) {
            returnArr = arr.slice(0, depthN + 1) //将目前匹配的数组，截断并保存到结果数组，
            break
          } else {
            if (childrenData[j].children) {
              depth++
              childrenEach(childrenData[j].children, depth)
            }
          }
        }
        return returnArr
      }
      return childrenEach(treeData, depth)
    },
  },
}
</script>
<style>
.el-cascader-panel .el-radio {
  width: 100%;
  height: 100%;
  z-index: 10;
  position: absolute;
  top: 10px;
  right: 10px;
}
.el-cascader-panel .el-radio__input {
  visibility: hidden;
}
.el-cascader-panel .el-cascader-node__postfix {
  top: 10px;
}
.el-cascader .el-input .el-input__inner {
  height: 30px;
}
</style>
