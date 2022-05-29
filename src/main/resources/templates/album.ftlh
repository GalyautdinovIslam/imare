<#import "_layout.ftlh" as layout>
<@layout.layout>
    <script src="/js/album.js"></script>
    <div class="row">
        <div class="col-6">
            <ul class="list-group">
                <li class="list-group-item">Владелец: <span id="owner"></span></li>
                <li class="list-group-item">Название альбома: <span id="title"></span></li>
            </ul>
        </div>
        <div class="col-6">
            <ul class="list-group">
                <li class="list-group-item">
                    <button id="delete-btn" type="button" class="btn btn-primary">Удалить альбом</button>
                </li>
            </ul>
        </div>
    </div>
    <hr>
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
    <div id="content" class="row"></div>
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
</@layout.layout>

