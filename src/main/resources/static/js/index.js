$(function () {
    //初始化菜单
    initMenu();
    //初始化加载首页
    var tab_home = {id: 'home', title: '首页', icon: 'menu-icon fa fa-home', url: '/app/menu/home'};
    addTabs(tab_home);
});

var initMenu = function() {
    $.ajax({
        url: '/app/menus',
        type: 'post',
        data: {},
        dataType: "json",
        success: function(result){
            console.log(result.data);
            $('#menu').sidebarMenu(result);
        },
        error:function(e){
            console.log("请求失败"+e);
        }
    });
};
