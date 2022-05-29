document.addEventListener("DOMContentLoaded", () =>{
    const owner = document.getElementById("owner");
    const latitude = document.getElementById("latitude");
    const longitude = document.getElementById("longitude");
    const tags = document.getElementById("tags");
    const comments = document.getElementById("comments");
    const img = document.getElementById("img");
    const deleteBtn = document.getElementById("deleteBtn");

    function getData() {
        $.ajax("/api" + window.location.pathname, {
            type: "GET",
            statusCode: {
                200: function (response) {
                    owner.innerHTML = "<a href=\"/accounts/" + response.owner.username + "\"><b>" +
                        response.owner.username +"</b> " + response.owner.name + "</a>";
                    latitude.innerHTML = response.latitude ? response.latitude : "Отсутствует";
                    longitude.innerHTML = response.longitude ? response.longitude : "Отсутствует";
                    tags.innerHTML = "<div class=\"accordion accordion-flush\" id=\"accordionFlushExample\">\n" +
                        "  <div class=\"accordion-item\">\n" +
                        "    <h2 class=\"accordion-header\" id=\"flush-headingOne\">\n" +
                        "      <button class=\"accordion-button collapsed\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#flush-collapseOne\" aria-expanded=\"false\" aria-controls=\"flush-collapseOne\">\n" +
                        "        Отметки:\n" +
                        "      </button>\n" +
                        "    </h2>\n";
                    response.tags.forEach(function (tag) {
                       tags.innerHTML += "<div id=\"flush-collapseOne\" class=\"accordion-collapse collapse\" aria-labelledby=\"flush-headingOne\" data-bs-parent=\"#accordionFlushExample\">\n" +
                        "      <a href=\"/accounts/" + tag.username + "\"><li class=\"list-group-item\"><div class=\"accordion-body\"><b>" + tag.username + "</b> " + tag.name + "</div></li></a>\n" +
                        "    </div>\n";
                    });
                    tags.innerHTML += "</div>\n</div>";

                    comments.innerHTML = "<h3>Комментарии:</h3>";
                    response.comments.forEach(function (comment) {
                       comments.innerHTML += "<div>" +
                           "<b>" + comment.user.username + "</b> " + comment.createdAt +
                           "<br>" +
                           comment.content +
                           "</div>"
                    });

                    img.src = "/files/" + response.fileName;
                }
            }
        });
    }
    deleteBtn.onclick = () => {
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

    getData();
});

