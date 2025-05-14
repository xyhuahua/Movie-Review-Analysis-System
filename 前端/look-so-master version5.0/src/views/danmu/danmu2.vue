<template>
  <div id="root">
      <div class="app">
        <div class="danmu-content">
          <div class="screen"></div>
          <div class="opeate">
            <button id="pauseAll">暂停全部</button>
            <button id="continueAll">继续全部</button>
          </div>
          <div class="opeate">
            <button class="close-time">暂停计时器</button>
            <button class="open-time">继续计时器</button>
          </div>
          <div class="opeate">
            <input id="d-input" type="text" placeholder="手动发送的弹幕" />
            <button id="sendDanmu">发送弹幕</button>
          </div>
        </div>
      </div>
    </div>
</template>
<script>
const screen = new BulletJs('.screen', { 
                      trackHeight: 35 
                    });
</script>
<style>
      html,
      body {
        margin: 0;
        padding: 0;
      }

      .danmu-content {
        margin: 30px 10px;
        height: 180px;
        border: 1px solid;
      }

      .opeate button {
        margin: 10px;
        border: 1px solid;
      }

      .screen {
        /* width: 100vw; */
        height: 100%;
      }

      .danmu-item {
        margin: 5px;
        border: 1px solid;
        overflow: hidden;
        display: inline-block;
        word-break: keep-all;
        white-space: nowrap;
      }
      .screen {
        will-change: 'auto';
      }
    </style>