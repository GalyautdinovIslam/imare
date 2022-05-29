<#import "_layout.ftlh" as layout>
<@layout.layout>
    <script src="/js/photo.js"></script>
    <div class="row">
        <div class="col-6">
            <img id="img" src="" alt="photo">
        </div>
        <div class="col-6">
            <ul class="list-group">
                <li class="list-group-item">Владелец: <span id="owner"></span></li>
                <li class="list-group-item">Широта: <span id="latitude"></span></li>
                <li class="list-group-item">Долгота: <span id="longitude"></span></li>
                <li class="list-group-item">
                    <div id="tags"></div>
                </li>
                <li class="list-group-item">
                    <div class="accordion accordion-flush" id="accordionFlushExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="flush-headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#flush-collapseOne" aria-expanded="false"
                                        aria-controls="flush-collapseOne">
                                    Добавить в альбом
                                </button>
                            </h2>
                            <#list albums as album>
                                <div id="flush-collapseOne" class="accordion-collapse collapse"
                                     aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                    <div class="accordion-body">
                                        <form action="/api/photos/${photoId}/add/${album.id}" method="post">
                                            <input type="submit" value="${album.title}">
                                        </form>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="accordion accordion-flush" id="accordionFlushExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="flush-headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#flush-collapseOne" aria-expanded="false"
                                        aria-controls="flush-collapseOne">
                                    Удалить из альбома
                                </button>
                            </h2>
                            <#list albums as album>
                                <div id="flush-collapseOne" class="accordion-collapse collapse"
                                     aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                    <div class="accordion-body">
                                        <form action="/api/photos/${photoId}/delete/${album.id}" method="post">
                                            <input type="submit" value="${album.title}">
                                        </form>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </div>
                </li>
                <li class="list-group-item">
                    <button id="deleteBtn" type="button" class="btn btn-primary">Удалить фото</button>
                </li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div id="comments" class="col-6"></div>
        <div class="col-6">
            <h3>Оставить комментарий:</h3>
            <form action="/comments/add/${photoId}" method="post">
                <textarea class="form-control" name="content" placeholder="Новый комментарий"></textarea><br>
                <input class="btn btn-primary" type="submit" value="Отправить комментарий">
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <h3>Изменить описание:</h3>
            <form action="/photos/${photoId}/update" method="post">
                <input class="form-control" type="text" name="latitude" placeholder="Широта"/><br>
                <input class="form-control" type="text" name="longitude" placeholder="Долгота"/><br>
                <input class="form-control" type="text" name="usernames" placeholder="Отметки"/><br>
                <input class="btn btn-primary" type="submit" value="Изменить описание">
            </form>
        </div>
    </div>
</@layout.layout>
