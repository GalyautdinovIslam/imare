document.addEventListener("DOMContentLoaded", () =>{
    const username = document.getElementById("username");
    const name = document.getElementById("name");
    const information = document.getElementById("information");
    const posts = document.getElementById("posts");
    const followers = document.getElementById("followers");
    const following = document.getElementById("following");
    const content = document.getElementById("content");
    const postsBtn =document.getElementById("posts-btn");
    const albumsBtn =document.getElementById("albums-btn");
    const followBtn =document.getElementById("follow-btn");
    const unfollowBtn =document.getElementById("unfollow-btn");
    const followersBtn =document.getElementById("followers-btn");
    const followingBtn =document.getElementById("following-btn");

    let page = 0;
    let maxPage = 0;
    const left = document.getElementById("left");
    const right = document.getElementById("right");
    let lastBtn = postsBtn;

    function getData() {
        $.ajax("/api" + window.location.pathname, {
            type: "GET",
            statusCode: {
                200: function (response) {
                    username.innerText = response.username;
                    name.innerText = response.name;
                    information.innerText = response.information;
                    posts.innerText = response.postsAmount;
                    followers.innerText = response.followersAmount;
                    following.innerText = response.followingsAmount;
                }
            }
        });
    }
    postsBtn.onclick = () => {
        $.ajax("/api" + window.location.pathname + "/posts?page=" + page, {
            type: "GET",
            statusCode: {
                200: function (response) {
                    content.innerHTML = "<h3>Посты:</h3>";
                    response.posts.forEach(function (post) {
                        content.innerHTML += "<div class=\"card col-4\">\n" +
                            "  <img src=\"/files/" + post.photoStorageFileName + "\" class=\"card-img-top\" alt=\"Фотография\">\n" +
                            "  <div class=\"card-body\">\n" +
                            "   <a href=\"/posts/" + post.id + "\" class=\"btn btn-primary\">Перейти к посту</a>\n" +
                            "  </div>\n" +
                            "</div>";
                    });
                    maxPage = response.totalPage;
                    lastBtn = postsBtn;
                }
            }
        });
    };
    albumsBtn.onclick = () => {
        $.ajax("/api" + window.location.pathname + "/albums?page=" + page, {
            type: "GET",
            statusCode: {
                200: function (response) {
                    content.innerHTML = "<h3>Альбомы:</h3>";
                    response.albums.forEach(function (album) {
                        content.innerHTML += "<div class=\"card col-4\">\n" +
                            "  <div class=\"card-body\">\n" +
                            "    <h5 class=\"card-title\">"+ album.title + "</h5>\n" +
                            "    <p class=\"card-text\">" + album.photosAmount + " фотографий.</p>\n" +
                            "    <a href=\"/albums/" + album.id + "\" class=\"btn btn-primary\">Перейти к альбому</a>\n" +
                            "  </div>\n" +
                            "</div>";
                    });
                    maxPage = response.totalPage;
                    lastBtn = albumsBtn;
                }
            }
        });
    };
    followBtn.onclick = () => {
        $.ajax("/api" + window.location.pathname + "/follow", {
            type: "POST",
            statusCode: {
                201: function (response) {
                    getData();
                }
            }
        });
    };
    unfollowBtn.onclick = () => {
        $.ajax("/api" + window.location.pathname + "/unfollow", {
            type: "POST",
            statusCode: {
                202: function (response) {
                    getData();
                }
            }
        });
    };
    followersBtn.onclick = () => {
        $.ajax("/api" + window.location.pathname + "/followers?page=" + page, {
            type: "GET",
            statusCode: {
                200: function (response) {
                    content.innerHTML = "<h3>Подписчики:</h3><ul class=\"list-group\">";
                    response.accounts.forEach(function (account) {
                       content.innerHTML += "<a href=\"/accounts/" + account.username +
                           "\"<li class=\"list-group-item\"><b>" + account.username + "</b> " +
                           account.name +"</li></a>"
                    });
                    content.innerHTML += "</ul>";
                    maxPage = response.totalPage;
                    lastBtn = followersBtn;
                }
            }
        });
    };
    followingBtn.onclick = () => {
        $.ajax("/api" + window.location.pathname + "/followings?page=" + page, {
            type: "GET",
            statusCode: {
                200: function (response) {
                    content.innerHTML = "<h3>Подписки:</h3><ul class=\"list-group\">";
                    response.accounts.forEach(function (account) {
                        content.innerHTML += "<a href=\"/accounts/" + account.username +
                            "\"<li class=\"list-group-item\"><b>" + account.username + "</b> " +
                            account.name +"</li></a>"
                    });
                    content.innerHTML += "</ul>";
                    maxPage = response.totalPage;
                    lastBtn = followingBtn;
                }
            }
        });
    };
    left.onclick = () => {
        if (page > 0) {
            page -= 1;
            lastBtn.onclick();
        }
    };
    right.onclick = () => {
        if (page + 1 < maxPage) {
            page += 1;
            lastBtn.onclick();
        }
    };

    getData();
    postsBtn.onclick();
});

