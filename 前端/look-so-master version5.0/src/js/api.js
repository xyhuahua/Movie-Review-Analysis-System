import axios from 'axios'
export function getCountAndRate(movieId) {
  return axios.get("http://localhost:8080/rateAndCount", {
                  params: {
                    movieId: movieId,
                  },
                })
                .then((res) => {
                  if (res.status == 200) {
                    var res = res.data.CountAndRate[0];
                  }
                  console.log(res)
                  return Promise.resolve(res)
                });
}
export function getEmotionAnalyse(movieId) {
  return axios.get("http://localhost:8080/rateAndCount", {
                  params: {
                    movieId: movieId,
                  },
                })
                .then((res) => {
                  if (res.status == 200) {
                    var res = res.data.CountAndRate[0];
                  }
                  //console.log(count)
                  return Promise.resolve(res)
                });
}
export function getRecommendLikeMovies (userName) {
  return axios.get('http://localhost:8080/getRecommendLikeMovies',{
    params: {
      userName:userName
    }
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}
export function getGoodMovies(actorId) {
  return axios
    .get("http://localhost:8080/role/getRecentMovie", {
      params: {
        actorId: actorId
      }
    })
    .then(res => {
      return Promise.resolve(res.data);
    });
}

export function getRecentMovies(actorId) {
  return axios
    .get("http://localhost:8080/role/getRecentMovie", {
      params: {
        actorId: actorId
      }
    })
    .then(res => {
      return Promise.resolve(res.data);
    });
}
export function getRecommendSlider () {
  return axios.get('/static/json/recommendSlider.json').then((res) => {
    return Promise.resolve(res.data)
  })
}
export function getRecommendRank () {
  return axios.get('/static/json/recommendRank.json').then((res) => {
    return Promise.resolve(res.data)
  })
}
export function getRecommendMovies () {
  return axios.get('/static/json/recommendMovies.json').then((res) => {
    return Promise.resolve(res.data)
  })
}
export function getRecommendReviews () {
  return axios.get('/static/json/recommendReviews.json').then((res) => {
    return Promise.resolve(res.data)
  })
}

// 新增
export function getSimilarMovies (type,regions) {
  return axios.get('http://localhost:8080/getSimilarMovies',{
    params: {
      type:type,
      regions:regions
    }
  }).then((res) => {
    console.log("-------------------------")
    console.log(res.data)
    return Promise.resolve(res.data)
  })
}

// 新增
export function getActors () {
  return axios.get('/static/json/actor.json').then((res) => {
    return Promise.resolve(res.data)
  })
}
// 新增
export function getMagnumOpus () {
  return axios.get('/static/json/magnumOpus.json').then((res) => {
    return Promise.resolve(res.data)
  })
}
// 新增
export function getPlayRecord () {
  return axios.get('/static/json/playRecord.json').then((res) => {
    return Promise.resolve(res.data)
  })
}

export function getAllMovieName (queryString) {
  console.log("........")
  console.log(queryString)
  return axios.get('http://localhost:8080/getAllMovieName',{
      params: {
        queryString: queryString
      }
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}


export function getAllReviews (type, page) {
  return axios.get('http://localhost:8080/comment/getHotComments', {
    params: {
      type: type,
      page: page
    }
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}
export function getAllRank (page,year,type) {
  return axios.get('http://localhost:8080/movie/getMoiveRank', {
    params: {
      page: page,
      year:year,
      type:type
    }
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}
export function getAllRole(page, type) {
  return axios
    .get("http://localhost:8080/role/getRoleRank", {
      params: {
        page: page,
        type: type
      }
    })
    .then(res => {
      return Promise.resolve(res.data);
    });
}

export function getRoleDetail(roleId) {
  return axios
    .get("http://localhost:8080/role/getRoleDetail", {
      params: {
        roleId: roleId
      }
    })
    .then(res => {
      return Promise.resolve(res.data);
    });
}

export function getHomepageComments(type, page) {
  return axios
    .get("http://localhost:8080/comment/getHomepageComments", {
      params: {
        type: type,
        page: page
      }
    })
    .then(res => {
      return Promise.resolve(res.data);
    });
}

// 新增
export function getMovieReviews (page,movieId) {
  return axios.get('http://localhost:8080/getMovieDetailReviews', {
    params: {
      movieId: movieId,
      page: page
    }
  }).then((res) => {
    console.log("res.data.hotReviews")
    console.log(res.data.hotReviews)
    return Promise.resolve(res)
  })
}

export function getMovieDetail (movieId) {
  console.log("666666666666666666666666666666")
  console.log(sessionStorage.getItem("username"))
  return axios.get('http://localhost:8080/getMovieDetail', {
    params: {
      username:sessionStorage.getItem("username"),
      movieId: movieId
    }
  }).then((res) => {
    console.log("detail")
    console.log(res.data.movie)
    return Promise.resolve(res.data)
  })
}


//寻常
export function getFavCar (username) {
  return axios.get('http://localhost:8080/user/favList/getAll', {
    params: {
      username: username
    }
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function deleteFav (movieId,username) {
  return axios.get('http://localhost:8080/user/favList/deleteOne', {
    params: {
      username: username,
      movieId: movieId
    }
  }).then((res) => {
    return Promise.resolve(res.msg)
  })
}

export function register (form) {
  return axios.get('http://localhost:8080/user/register', {
    params: {
      userName: form.userName,
      password: form.password,
      age:form.age,
      address:form.address,
      info:form.info,
      sex:form.sex,
      tel:form.tel,
      checkList: form.checkList + '',
    }
  }).then((res) => {
    console.log(res);
    return Promise.resolve(res.data.msg)
  })
}

// 用户登录，前端测试暂时用json返回
export function login (userName, password) {
  return axios.get('http://localhost:8080/user/login', {
    params: {
      userName: userName,
      password: password
    }
  }).then((res) => {
    if (res.status === 200) {
      // 登录成功返回data的一个user对象，失败返回空
      return Promise.resolve(res.data.msg)
    }
  })
}

export function getSortMovies (sort, page, radio,currentSort_type, currentSort_country, currentSort_time, currentSort_hot) {
  return axios.get('http://localhost:8080/movie/findAll', {
    params: {
      movieSort: sort,
      page: page,
      radio: radio,
      currentSort_type: currentSort_type,
      currentSort_country: currentSort_country,
      currentSort_time: currentSort_time,
      currentSort_hot: currentSort_hot
    }
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}

// 寻常
export function getUser (userName) {
  return axios.get('http://localhost:8080/user/info', {
    params: {
      userName: userName,
    }
  }).then((res) => {
    if (res.status === 200) {
      // 登录成功返回data的一个user对象，失败返回空
      console.log(res.data.info);
      return Promise.resolve(res.data.info)
    }
  })
}

export function modifyInfo (form) {
  return axios.get('http://localhost:8080/user/modifyInfo', {
    params: {
      userName: form.userName,
      password: form.password,
      age:form.age,
      address:form.address,
      info:form.info,
      sex:form.sex,
      tel:form.tel,
      checkList: form.checkList + '',
    }
  }).then((res) => {
    console.log(res);
    return Promise.resolve(res.data.msg)
  })
}

export function getRecordMovies (username, type) {
  return axios.get('http://localhost:8080/user/playRecord', {
    params: {
      username: username,
      type : type
    }
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}