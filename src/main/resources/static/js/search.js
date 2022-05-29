document.addEventListener("DOMContentLoaded", () =>{
    const input = document.getElementById("search");
    const btn = document.getElementById("search-btn");
    const content = document.getElementById("content");

    let page = 0;
    let maxPage = 0;
    const left = document.getElementById("left");
    const right = document.getElementById("right");

    function search() {
        console.log(input.value)
        $.ajax("/api/search/account?page=" + page + "&search=" + input.value, {
            type: "GET",
            statusCode: {
                200: function (response) {
                    content.innerHTML = "Пользователи: <ul class=\"list-group\">";
                    response.accounts.forEach(function (account) {
                        content.innerHTML += "<a href=\"/accounts/" + account.username + "\"<li class=\"list-group-item\">" +
                            "<b>" + account.username + "</b> " + account.name + "</li></a>";
                    });
                    content.innerHTML += "</ul>";
                    maxPage = response.totalPage;
                }
            }
        });
    }
    input.onkeyup = search;
    btn.onclick = search;

    left.onclick = () => {
        if (page > 0) {
            page -= 1;
            search();
        }
    };
    right.onclick = () => {
        if (page + 1 < maxPage) {
            page += 1;
            search();
        }
    };

    getData();
});

