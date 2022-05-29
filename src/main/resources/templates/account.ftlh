<#import "_layout.ftlh" as layout>
<@layout.layout>
    <script src="/js/account.js"></script>
    <div class="row">
        <div class="col-6">
            <ul class="list-group">
                <li class="list-group-item">
                    Username: <span id="username"></span>
                    <button id="follow-btn" type="button" class="btn btn-primary">Follow</button>
                    <button id="unfollow-btn" type="button" class="btn btn-primary">Unfollow</button>
                </li>
                <li class="list-group-item">Имя: <span id="name"></span></li>
                <li class="list-group-item">Информация: <span id="information"></span></li>
            </ul>
        </div>
        <div class="col-6">
            <ul class="list-group">
                <li class="list-group-item">Постов: <span id="posts"></span></li>
                <li class="list-group-item">
                    <button id="followers-btn" type="button" class="btn btn-primary">
                        Подписчиков: <span id="followers"></span>
                    </button>
                </li>
                <li class="list-group-item">
                    <button id="following-btn" type="button" class="btn btn-primary">
                        Подписок: <span id="following"></span>
                    </button>
                </li>
            </ul>
        </div>
    </div>
    <hr>
    <div class="row justify-content-center">
        <div class="col-6">
            <button id="posts-btn" type="button" class="btn btn-primary">Посты</button>
        </div>
        <div class="col-6">
            <button id="albums-btn" type="button" class="btn btn-primary">Альбомы</button>
        </div>
    </div>
    <br>
    <div class="pagination justify-content-center">
        <ul class="pagination">
            <li class="page-item">
                <button class="page-link" id="left" aria-label="Предыдущая">
                    <span aria-hidden="true">&laquo;</span>
                </button>
            </li>
            <li class="page-item">
                <button class="page-link" id="right" aria-label="Следующая">
                    <span aria-hidden="true">&raquo;</span>
                </button>
            </li>
        </ul>
    </div>
    <br>
    <div id="content" class="row">

    </div>
    <br>
    <div class="pagination justify-content-center">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" id="left" aria-label="Предыдущая">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li class="page-item">
                <a class="page-link" id="right" aria-label="Следующая">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </div>
</@layout.layout>
