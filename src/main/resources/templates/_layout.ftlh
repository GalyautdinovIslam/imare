<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#macro layout>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Imare</title>
    <link rel="stylesheet" href="/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Imare</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                 <@security.authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/posts/add">Добавить пост</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/albums/add">Создать альбом</a>
                    </li>
                     <li class="nav-item">
                         <a class="nav-link active" aria-current="page" href="/search">Искать</a>
                     </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">${auth}</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/accounts/${auth}">Профиль</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/logout">Выйти</a></li>
                        </ul>
                    </li>
                 </@security.authorize>
                <@security.authorize access="! isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/signIn">Войти</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/signUp">Зарегистрироваться</a>
                    </li>
                </@security.authorize>
            </ul>
        </div>
    </div>
</nav>
<main>
    <div class="container-xxl">
    <#nested>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
</#macro>
