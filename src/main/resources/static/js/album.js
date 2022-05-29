document.addEventListener("DOMContentLoaded", () =>{
    const owner = document.getElementById("owner");
    const title = document.getElementById("title");
    const content = document.getElementById("content");
    const deleteBtn = document.getElementById("delete-btn");

    let page = 0;
    let maxPage = 0;
    const left = document.getElementById("left");
    const right = document.getElementById("right");

    function getData() {
        $.ajax("/api" + window.location.pathname + "?page=" + page, {
            type: "GET",
            statusCode: {
                200: function (response) {
                    owner.innerText = response.owner;
                    title.innerText = response.title;
                    content.innerHTML = "<h3>Фотографии:</h3>";
                    response.photos.forEach(function (photo) {
                        content.innerHTML += "<div class=\"card col-4\">\n" +
                            "  <img src=\"/files/" + photo.filename + "\" class=\"card-img-top\" alt=\"Фотография\">\n" +
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

    deleteBtn.onclick = function deleteAlbum(){
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

