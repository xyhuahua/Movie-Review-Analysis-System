<template>
	
	<div v-loading="isLoading">
		
			<div  style="width:10%;height: 10%;border-radius: 100px;display:inline-block;margin-top:20px">
				<img
					style="width:100%;height: 100%;border-radius: 100px"
					:src= "imgsrc"
					class="image"
				>
			</div>
	
		<br>
		<br>
		<div style="width:70%;margin-top: 20px; ">
				<el-descriptions :column="2"  border>
	
				<el-descriptions-item label="用户名" v-model="username" ><p class="message">{{userName}}</p></el-descriptions-item>
				<el-descriptions-item label="年龄" v-model="age" class="message"><p class="message">{{age}}</p></el-descriptions-item>
				<el-descriptions-item label="地址" v-model="adress" class="message"><p class="message">{{address}}</p></el-descriptions-item>
				<el-descriptions-item label="性别" v-model="sex" class="message" ><p class="message">{{sex}}</p></el-descriptions-item>
				<el-descriptions-item label="电话" v-model="tel" class="message"><p class="message">{{tel}}</p></el-descriptions-item>
				<el-descriptions-item label="爱好" v-model="like" class="message"><p class="message">{{like}}</p></el-descriptions-item>
				<el-descriptions-item label="简介" v-model="info" class="message"><p class="message">{{info}}</p></el-descriptions-item>

			</el-descriptions>
			</div>
	</div>
	</template>
	
	<script>
	import {getUser} from '@/js/api'
	export default {
		name: "info",
		data(){
			return{
				userName:'',
				age:'',
				address:'',
				sex:'',
				tel:'',
				like:'',
				info:'',
				isLoading:''
			}
		},
		created() {
			this._getUser()
		},
		methods:{
			_getUser(){
				this.isLoading = true
				var userName = sessionStorage.getItem("username");
				getUser(userName).then((res) => {
					
					for(var i = 0;i<res.like.length;i++){
						this.like = this.like + ' ' + res.like[i].like
					}
					
					this.userName = res.userName
					this.age = res.age
					this.address = res.address
					this.sex = res.sex
					this.tel = res.tel
					this.info = res.info
					this.imgsrc = "http://localhost:8080/file/download/" + res.headId
					this.isLoading = false
					console.log(res)
				})
			}
		}
	}
	</script>
	
	<style scoped>
	.message{
		width: 20em;
		overflow: hidden;
		text-overflow:ellipsis;
		white-space: nowrap;
	}
	</style>
	