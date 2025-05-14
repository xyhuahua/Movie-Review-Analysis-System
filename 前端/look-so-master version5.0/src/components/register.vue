<template>
  <div class="register">
    <el-dialog title="注册" :visible.sync="registerVisible"  width="500px" center>
        <el-form :model="form" label-width="80px" label-position="left" :rules="rules" ref="form">
            <el-form-item label="用户名" prop="userName">
                <el-input v-model="form.userName" auto-complete="off" ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
              <el-input type="password" v-model="form.pass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input type="password" v-model="form.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
              <el-input v-model.number="form.age"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="tel" >
                <el-input v-model="form.tel" auto-complete="off" ></el-input>
            </el-form-item>            
             <el-form-item label="地址" prop="address">
                <el-input v-model="form.address" auto-complete="off"></el-input>
            </el-form-item>
             <el-form-item label="性别" >
                <el-radio v-model="form.sex" label="男">男</el-radio>
                <el-radio v-model="form.sex" label="女">女</el-radio>
            </el-form-item>    
            <el-form-item label="偏好">
              <el-checkbox-group v-model="form.checkList" :min="1">
                <el-checkbox v-for="type in form.types" :label="type" :key="type" border size="medium">{{type}}</el-checkbox>
              </el-checkbox-group>    
            </el-form-item>
              <el-form-item label="简介" >
                <el-input v-model="form.info" auto-complete="off"></el-input>
            </el-form-item>                                       
        </el-form>
                    <span style="text-align:center; color:blue">{{msg}}</span>
        <div slot="footer" class="dialog-footer">
            <el-button @click="registerVisible = false">取 消</el-button>
            <el-button type="primary" @click="_register">确 定</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
import bus from '@/js/bus'
import { register } from "@/js/api";
const movieSorts_type = ['剧情', '喜剧', '科幻', '悬疑', '爱情', '治愈', '动作', '惊悚', '战争', '动画']
export default {
  data () {
     var checkAge = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('年龄不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
              callback();
          }    
        }, 1000);
      };

      var check1 = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('不能为空'));
        }
      };

      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.form.checkPass !== '') {
            this.$refs.form.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      }
    return {
      registerVisible: false,
      form: {
        userName: '',
        password: '',
        age:'',
        address:'',
        info:'',
        sex:'女',   
        tel:'',
        checkList: ['剧情'],
        types:movieSorts_type,
        options: [],
        msg:''
      },
       rules: {
          userName: [
            { message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
          ],
          pass: [
            { message: '不少于8个字符', validator: validatePass, trigger: 'blur' },
            { min: 8, message: '不少于8个字符', trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          age: [
            { validator: checkAge, trigger: 'blur' }
          ],
          address:[
            {validator: check1, trigger: 'blur'}
          ],
          tel:[
            {validator: check1, trigger: 'blur'}
          ],
        },
        
    }
  },
  created () {
    bus.$on('showRegister', (msg) => {
      this.registerVisible = msg
    })
  },
  methods: {
     submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },

    _register(){
      console.log(this.form);
      register(this.form).then((res) => {
        this.msg = res
        if(res=='用户名已经存在了'){
          this.$message({
              message: res,
              type: 'warning'
          });
        }else if(res=='success'){
          this.$message({
              message: res,
              type: 'success'
          });
        }else{
          this.$message.error("发生错误了");
        }
        
        console.log(res)

      });
    }
  
  
}
}
</script>

<style lang="less" scoped>

</style>


