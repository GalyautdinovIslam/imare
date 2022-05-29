<#import "_layout.ftlh" as layout>
<@layout.layout>
    <script src="/js/search.js"></script>
    <div class="row">
        <div class="col-11">
            <input class="form-control" type="text" id="search" name="search" placeholder="Search"/>
        </div>
        <div class="col-1">
            <button id="search-btn" type="button" class="btn btn-primary">Search</button>
        </div>
    </div>
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
    <div id="content"></div>
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

