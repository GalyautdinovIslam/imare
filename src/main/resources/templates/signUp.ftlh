<#import "_layout.ftlh" as layout>
<@layout.layout>
<script src="/js/signUp.js"></script>
    Имя пользователя: <input class="form-control" type="text" id= "username" name="username" placeholder="Username" />
    <br>
    Пароль: <input class="form-control" type="password" id= "password" name="password" placeholder="Password" />
    <br>
    Повторите пароль: <input class="form-control" type="password" id= "repeatPassword" name="repeatPassword" placeholder="Repeat password" />
    <br>
    Имя: <input class="form-control" type="text" id= "name" name="name" placeholder="Name" />
    <br>
    Информация: <input class="form-control" type="text" id= "information" name="information" placeholder="Information" />
    <br>
    <button id="signUp" class="btn btn-primary">Зарегистрироваться</button>
    <div id="forToast" class="toast-container"></div>
</@layout.layout>

