<#import "_layout.ftlh" as layout>
<@layout.layout>
    <form id="form" action="/albums/add" method="post" enctype="multipart/form-data">
        <input class="form-control" type="text" name="title" placeholder="Название альбома" required/>
        <br>
        <button type="submit" class="btn btn-primary">Создать альбом</button>
    </form>
</@layout.layout>
