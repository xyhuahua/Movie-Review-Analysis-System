<template>
  <div class="chat">
    <div class="top">
      <div class="top-left">
        <el-input type="text" id="roomName"  v-model="roomId" disabled><template slot="prepend">房间号：</template></el-input>
      </div>
      <div class="top-right">
        <!-- 昵称:<input type="text" id="roomName" :value="roomId"/>  -->
        <el-button size="medium" round @click="conectWebSocket()">连接WebSocket</el-button>
        <el-button size="medium" round @click="closeWebSocket()">断开连接</el-button>
        <!-- <button @click="conectWebSocket()">连接WebSocket</button>
        <button @click="closeWebSocket()">断开连接</button> -->
      </div>
      </div>
    			
    <div class="content-top">

      <div class="detail-left">
				<el-card class="box-card">
          <div slot="header" class="clearfix">
            <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
          </div>
          <div class="message-container">
                  <iframe
                    id="iframeid"
                    src="../../../static/danmu.html"
                    width="100%"
                    height="500px"
                    ref="fIframe"
                    name="myiframe"
                  ></iframe>
              </div>
        </el-card>
    </div>
    <div class="detail-right">
			<el-tabs type="border-card">
                  <el-tab-pane label="实时" >
                    <div class="chatDiv">
                        <div id="message" v-for="(msg,index) in message" :key="index">
                           <div class="con_msg">
                           <div class="leftDiv">
                                <div>
                                   <img :src="msg.headId" class="name">
                                </div>
                                <div class="message">
                                    <div class="time">{{msg.username}} {{msg.time}}</div>
                                    <div class="content">{{msg.message}}</div>
                                </div>
                            </div>
                            </div>
                          <!-- <p>{{msg}}</p> -->
                        </div>
                    </div>

                  <div class="message-bottom">
                    <div style="float:left;margin-left: 10px;">
                      <el-input type="text" id="text"  v-model="messageContent" placeholder="请输入消息"></el-input>
                    </div>
                    <div style="float:left;margin-left:10px">
                      <el-button type="primary" @click="chat2" round>发送消息</el-button>
                      <!-- <button @click="chat2()">发送消息</button> -->
                    </div>
                    <!-- 消息：<input id="text" type="text" v-model="messageContent" /> -->
                  </div>
                  </el-tab-pane>
                  <!-- <el-tab-pane label="历史">
                    <div style="height:480px">
                      1
                    </div>
                    <div id="message" v-for="(msg,index) in message" :key="index" style="height:200px">
                    <p>{{msg}}</p>
                  </div>
                  <div class="message-bottom">
                    <div style="float:left;margin-left: 10px;">
                      <el-input type="text" id="roomName" :value="roomId" v-model="input" placeholder="请输入消息"></el-input>
                    </div>
                    <div style="float:left;margin-left:10px">
                      <el-button type="primary" @click="chat2()" round>发送消息</el-button>

                    </div>

                  </div>
                  </el-tab-pane> -->
                  
                  <!-- <el-tab-pane label="角色管理">角色管理</el-tab-pane>
                  <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane> -->
                </el-tabs>
      <!-- <echarts-r1></echarts-r1>
      <similar-movie :similarMovies="similarMovies" class="similar-movie"></similar-movie> -->
    </div>
    </div>
  </div>
</template>

<script type="text/javascript">
import {getUser} from '@/js/api'
import Vue from "vue";
import { ref, nextTick, onMounted } from 'vue'
import { vueBaberrage, MESSAGE_TYPE } from "vue-baberrage";
Vue.use(vueBaberrage);
    export default {
    // components: { danmu2 },
    data() {
        return {
         websocket:null,
         message:[],
         roomId:this.$route.params.roomId,
         checkList:this.$route.params.checkList,
         show: true,
         messageNickname: "",
         messageContent: "",
         barrageList: [],
         avatar: '',
         userName:'',
         age:'',
         address:'',
         sex:'',
         tel:'',
         like:'',
         info:'',
         imgsrc:'',
         headId:'',
         status:false,
         userVo: null,
        };
    },
    mounted(){    
      this._getUser()
    },
    created() {
        console.log(84516141);

    },
    methods: {
      _getUser(){
				this.isLoading = true
				var userName = sessionStorage.getItem("username");
				getUser(userName).then((res) => {
          console.log(res);
					this.messageNickname = res.userName
					this.userName = res.userName
					this.age = res.age
					this.address = res.address
					this.sex = res.sex
					this.tel = res.tel
					this.info = res.info
          this.headId = res.headId
					this.imgsrc = "http://localhost:8080/file/download/" + res.headId
					this.isLoading = false
					console.log(res)

          var iframe = document.getElementById("iframeid");
          var that = this
          let user = {          
              roomId:this.roomId,
              username: this.userName,
              content:this.messageContent,
              headId:this.headId,
              type : 1
              }
          iframe.onload = function() {
            // 向domain2发送跨域数据
            iframe.contentWindow.postMessage({checkList:that.$route.params.checkList,userinfo:user}, 'http://localhost:8081/static/danmu.html');
        };
				})
			},
    conectWebSocket(){
	    var roomName = document.getElementById("roomName").value;
          if(roomName===""){
                 alert("请输入昵称");
                 return;
        }
        
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket'in window) {
            this.websocket = new WebSocket("ws://localhost:8080/websocket/chat/"+roomName+"/"+this.userName);  
        } else {
            alert('Not support websocket')
        }
        var that = this;
        //连接发生错误的回调方法
        this.websocket.onerror = function() {
            that.setMessageInnerHTML("error");
        };
        //连接成功建立的回调方法
        this.websocket.onopen = function(event) {
            that.status = true
            // that.setMessageInnerHTML("Loc MSG: 成功建立连接");
        }
        //接收到消息的回调方法
        this.websocket.onmessage = function(event) {
          console.log(JSON.parse(event.data).msg);
            that.setMessageInnerHTML(JSON.parse(event.data).msg);
        }
        //连接关闭的回调方法
        this.websocket.onclose = function() {
            that.status = false
            // that.setMessageInnerHTML("Loc MSG:关闭连接");
        }
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function() {
            websocket.close();
        }
    },
    //将消息显示在网页上
    setMessageInnerHTML(innerHTML) {
        this.message.push(innerHTML);
        console.log(this.message);
    },
    //关闭连接
    closeWebSocket() {
        this.websocket.close();
    },
    //发送消息
    send() {
        var message = {
          headId: this.headId,
          content: this.messageContent.trim()
        };
        message = JSON.stringify(message);
        this.websocket.send(message);
        this.messageContent = ''
    },
    //消息上弹幕墙
     chat1() {
      var iframe = document.getElementById("iframeid");
      var that = this
       console.log(this.roomId);
       console.log(this.$route.params.roomId);
       var message = {
          avatar: this.imgsrc,
          msg: this.messageContent.trim(),
          barrageStyle: "blue",
          time: Math.floor(Math.random() * (10 - 7)) + 7,
        //   userVo: this.userVo
      };      

      if (this.messageNickname.trim() == ""){
        this.$message.error("昵称不能为空")
        return false;
      }
      if (this.messageContent.trim() == ""){
        this.$message.error("留言不能为空")
        return false;
      }

      this.$axios.get('http://localhost:8080/chat/add', {
        params: {
          roomId:this.roomId,
          username: this.userName,
          content:this.messageContent,
          headId:this.headId,
          type : 1
        }
      }).then((res) => {
        return Promise.resolve(res.data)
      })

    },
    //消息进群组
    chat2() {
    if(this.status){
      if (this.messageContent.trim() == ""){
        this.$message.error("留言不能为空")
        return false;
      }
      this.send();
      this.$axios.get('http://localhost:8080/chat/add', {
        params: {
          roomId:this.roomId,
          username: this.userName,
          content:this.messageContent,
          headId:this.headId,
          type : 2
        }
      }).then((res) => {
        return Promise.resolve(res.data)
      })
    }else{
        this.$message.error("您掉线了，请重新连接哦")
    }
    },

}
}
</script>
<style lang="less" scoped>
/deep/.el-tabs__item {
      text-align:center;
      font-family:Verdana, Geneva, Tahoma, sans-serif;
      font-size: 20px;
      // color: #D6D6D6;
      // padding: 22px 0;
      // border-radius: 0px 64px 65px 0px;
      height: 50px;
      // text-align: center;
      width: 180px;
      // margin: 60px 0;
    }

.chatDiv{
    height: 480px;
    overflow-y: scroll;
}
.leftDiv{
    width: 360px;
    background-color: lavender;
}
.name{
    background-color: lightcoral;
    border-radius: 50%;
    width: 40px;
    line-height: 40px;
    text-align: center;
    float: left;
    margin: 10px;
}
.message{
    float: left;

}
.content{
    background-color: lightskyblue;
    border-radius: 0px 10px 10px 10px;
    padding: 15px;
    max-width: 260px;
}
.con_msg{
    float: left;
}

.top{
  margin-top: 20px;
  width: 100%;
  .top-left{
    float: left;
    margin-left: 10px;
  }
  .top-right{
    float: left;
    margin-left:20px;
  }
}
.chat {
  display: flex;
  flex-direction: column;
  width: 85%;
  margin: 0 auto;

  .content-top{
    display: flex;
    width: 100%;
    margin-top: 20px;
    .detail-left {
      width: 70%;
      .write-review,.recommend-reviews,.actor {
        margin-top: 20px;
      }
    }
    .detail-right {
      width: 28%;
      margin-left: auto;
       .similar-movie{
        margin-top: 20px;
       }
      }
  }
}
</style>
