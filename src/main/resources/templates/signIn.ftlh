<#import "_layout.ftlh" as layout>
<@layout.layout>
    <form action="/signIn" method="post">
        Имя пользователя: <input class="form-control" type="text" id="username" name="username" placeholder="Username"/>
        <br>
        Пароль: <input class="form-control" type="password" id="password" name="password" placeholder="Password"/>
        <br>
        <button type="submit" class="btn btn-primary" id="signIn">Войти</button>
        <label>
            <input type="checkbox" name="rememberMe">Запомнить меня
        </label>
    </form>
</@layout.layout>

