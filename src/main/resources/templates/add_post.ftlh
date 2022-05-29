<#import "_layout.ftlh" as layout>
<@layout.layout>
    <form id="form" action="/posts/add" method="post" enctype="multipart/form-data">
        <input class="form-control" type="file" name="files" multiple required/>
        <br>
        <button type="submit" class="btn btn-primary">Создать пост</button>
    </form>
</@layout.layout>
