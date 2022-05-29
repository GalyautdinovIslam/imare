document.addEventListener("DOMContentLoaded", () => {
    function validUsername(username) {
        return username != null && true && username.length > 5 && username.length < 33;
    }

    function validPassword(password) {
        return password != null && true && password.length > 7;
    }

    function validRepeatPassword(password, repeatPassword) {
        return password === repeatPassword;
    }

    function validName(name) {
        return name != null && true && name.length > 1;
    }

    function validAll(data) {
        return validUsername(data.username) && validPassword(data.password)
            && validRepeatPassword(data.password, data.repeatPassword)
            && validName(data.name);
    }

    const forToast = document.getElementById("forToast");
    const button = document.getElementById("signUp");
    button.onclick = function signUp() {
        let data = {
            "username": $("#username").val(),
            "password": $("#password").val(),
            "repeatPassword": $("#repeatPassword").val(),
            "name": $("#name").val(),
            "information": $("#information").val()
        };
        if (!validAll(data)) {
            forToast.innerHTML = "";
            if (!validUsername(data.username)) {
                forToast.innerHTML += "<div class=\"position-fixed bottom-0 end-0 p-3\" style=\"z-index: 11\">" +
                    "<div class=\"toast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">\n" +
                    "  <div class=\"toast-header\">\n" +
                    "    <img src=\"...\" class=\"rounded me-2\" alt=\"...\">\n" +
                    "    <strong class=\"me-auto\">Имя пользователя</strong>\n" +
                    "    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>\n" +
                    "  </div>\n" +
                    "  <div class=\"toast-body\">\n" +
                    "Имя пользователя не может быть пустым и должно быть не меньше 6 и не больше 32 символов" +
                    "  </div>\n" +
                    "</div>" +
                    "</div>";
            }
            if (!validPassword(data.password)) {
                forToast.innerHTML += "<div class=\"toast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">\n" +
                    "  <div class=\"toast-header\">\n" +
                    "    <img src=\"...\" class=\"rounded me-2\" alt=\"...\">\n" +
                    "    <strong class=\"me-auto\">Пароль</strong>\n" +
                    "    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>\n" +
                    "  </div>\n" +
                    "  <div class=\"toast-body\">\n" +
                    "Пароль не может быть пустым и не должен быть меньше 8 символов" +
                    "  </div>\n" +
                    "</div>";
            }
            if (!validRepeatPassword(data.password, data.repeatPassword)) {
                forToast.innerHTML += "<div class=\"toast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">\n" +
                    "  <div class=\"toast-header\">\n" +
                    "    <img src=\"...\" class=\"rounded me-2\" alt=\"...\">\n" +
                    "    <strong class=\"me-auto\">Пароль</strong>\n" +
                    "    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>\n" +
                    "  </div>\n" +
                    "  <div class=\"toast-body\">\n" +
                    "Пароли не совпадают" +
                    "  </div>\n" +
                    "</div>";
            }
            if (!validName(data.name)) {
                forToast.innerHTML += "<div class=\"toast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">\n" +
                    "  <div class=\"toast-header\">\n" +
                    "    <img src=\"...\" class=\"rounded me-2\" alt=\"...\">\n" +
                    "    <strong class=\"me-auto\">Имя</strong>\n" +
                    "    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>\n" +
                    "  </div>\n" +
                    "  <div class=\"toast-body\">\n" +
                    "Имя пользователя не может быть пустым" +
                    "  </div>\n" +
                    "</div>";
            }
        } else {
            $.ajax("/api/accounts/create", {
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json; charset=UTF-8",
                statusCode: {
                    201: function (response) {
                        window.location.replace("/signIn");
                    }
                },
                error: function (response) {
                    forToast.innerHTML = "";
                    response.errors.forEach(function (error) {
                        forToast.innerHTML += "<div class=\"toast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">\n" +
                            "  <div class=\"toast-header\">\n" +
                            "    <img src=\"...\" class=\"rounded me-2\" alt=\"...\">\n" +
                            "    <strong class=\"me-auto\">" + error.field + "</strong>\n" +
                            "    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>\n" +
                            "  </div>\n" +
                            "  <div class=\"toast-body\">\n" +
                            error.message +
                            "  </div>\n" +
                            "</div>";
                    });
                }
            });
        }
    }
});

