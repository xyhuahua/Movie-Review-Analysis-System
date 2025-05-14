<template>
    <div class="show">
      <div class="show1">
            <el-upload
                class="avatar-uploader"
                action="http://localhost:8080/file/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                :data="uploadData"

              >
                <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
    </div>
  </div>
</template>

<script>
export default {
  name: "imageUp",
  data() {
    return {
      filename: null,
      f64: null,
      loadImage: "",
      imageUrl: '',
      uploadData: {
        username : sessionStorage.getItem("username"),
      }
      
    };
  },
  methods: {
    changepic() {
      document.getElementById("file").onchange = function () {
        var imgFile = this.files[0];
        var fr = new FileReader();

        fr.onload = function () {
          let showing = document.getElementById("showimg");
          let img = fr.result;
          this.f64 = img;
          this.filename = imgFile.name;
          showing.src = img;
          showing.style.height = "220px";
          showing.style.width = "220px";
          showing.style.borderRadius = "200px";
        };
        fr.readAsDataURL(imgFile);
      };
    },
     handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
  },
};
</script>

<style scoped>
.upload {
  margin-left: 20%;
  width: 12%;
  text-align: center;
  color: white;
  height: 32px;
  border-radius: 3px;
  background: #1e90ff;
  cursor: pointer;
  outline: none;
  border-width: 0px;
  font-size: 17px;
  display: inline-block;
  padding: 4px 10px;
  line-height: 30px;
  position: relative;
  text-decoration: none;
}

button {
  margin-left: 70%;
  width: 15%;
  height: 35px;
  border-width: 0px;
  border-radius: 3px;
  background: #1e90ff;
  cursor: pointer;
  outline: none;
  color: white;
  font-size: 17px;
}
.show {
  margin: 100px auto;
  width: 80%;
  height: 450px;
  border: 5px solid #18a0ec;
  transition: all 0.9s;
  border-radius: 10px;
}
.show1 {
  margin: 50px auto;
  width: 178px;
  height: 178px;
  border: 5px solid #18a0ec;
  transition: all 0.9s;
  /* border-radius: 150px; */
}

.show1:hover {
  box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
  margin-top: 45px;
}

.show:hover {
  box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
  margin-top: 45px;
}

.texti {
  border: 1px solid #ccc;
  padding: 13px 14px;
  width: 30%;

  font-size: 14px;
  font-weight: 300;

  border-radius: 5px; /* 边框半径 */
  background: white; /* 背景颜色 */
  cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
  outline: none; /* 不显示轮廓线 */
}
.texti:focus {
  border-color: #1e88e1;
  outline: 0;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
}
textarea {
  resize: none;
}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
