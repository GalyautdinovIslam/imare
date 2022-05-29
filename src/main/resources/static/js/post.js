document.addEventListener("DOMContentLoaded", () =>{
    const owner = document.getElementById("owner");
    const date = document.getElementById("date");
    const content = document.getElementById("content");
    const deleteBtn = document.getElementById("delete-btn");

    let page = 0;
    let maxPage = 0;
    const left = document.getElementById("left");
    const right = document.getElementById("right");

    function getData() {
        $.ajax("/api" + window.location.pathname, {
            type: "GET",
            statusCode: {
                200: function (response) {
                    owner.innerHTML = "<a href=\"/accounts/" + response.owner.username + "\"><b>" +
                        response.owner.username +"</b> " + response.owner.name + "</a>";
                    date.innerText = response.createdAt;
                    content.innerHTML = "<h3>Фотографии:</h3>";
                    response.photoCards.forEach(function (photo) {
                        content.innerHTML += "<div class=\"card col-4\">\n" +
                            "  <img src=\"/files/" + photo.fileName + "\" class=\"card-img-top\" alt=\"Фотография\">\n" +
                            "  <div class=\"card-body\">\n" +
                            "    <a href=\"/photos/" + photo.id + "\" class=\"btn btn-primary\">Перейти к фотографии</a>\n" +
                            "  </div>\n" +
                            "</div>";
                    });
                    maxPage = response.totalPage;
                }
            }
        });
    }
    deleteBtn.onclick = function deletePost(){
        $.ajax("/api" + window.location.pathname + "/delete", {
            type: "POST",
            statusCode: {
                202: function (response) {
                    window.location.replace("/");
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    }
    left.onclick = () => {
        if (page > 0) {
            page -= 1;
            getData();
        }
    };
    right.onclick = () => {
        if (page + 1 < maxPage) {
            page += 1;
            getData();
        }
    };

    getData();
});

