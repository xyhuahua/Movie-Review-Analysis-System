function pageJump(url){
    $("#homepage_iframe").attr("src", url);
}

function logout(){
    location.href = '../user.do?operate=logout'
}