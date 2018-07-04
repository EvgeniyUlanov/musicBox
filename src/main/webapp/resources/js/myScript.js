var $url = window.location.pathname;
var navigation;

$(document).ready(function () {
    navigation = $('.navbar');
    HideContentAndShowFirst();

    navigation.find('.nav-item').click(function () {
        $('#nav').find('nav-item').removeClass('active');
        $(this).addClass('active');

        $('.content').hide();
        var activeTab = $(this).attr('href');
        $(activeTab).fadeIn('slow');
        return false;
    });

    $('#sendToAddUser').click(function () {
        $('.content').hide();
        $('#addUser').show();
    });

    FillTable();
    AddActionToAddUserButton();
});

function AddActionToAddUserButton() {
    $('#addUserBtn').click(function () {
        var name = $('#addName').val();
        var login = $('#addLogin').val();
        var password = $('#addPassword').val();
        var role = $('#addRole').val();
        var address = $('#addAddress').val();
        var JSONObject = {
            "name": name,
            "login": login,
            "password": password,
            "role": role,
            "address": address
        };

        $.ajax({
            type: 'POST',
            url: $url + 'user/add',
            contentType: 'application/json',
            data: JSON.stringify(JSONObject),
            async: false
        });
        FillTable();
        HideContentAndShowFirst();
    })
}

function HideContentAndShowFirst() {
    $('.content').hide();
    navigation.find('.nav-item:first').addClass('active').show();
    $('.content:first').show();
}

function FillTable() {
    var $select = $("#tableUsers");
    $select.find('.removable').remove();
    $.post("users", function (responseJson) {
        $.each(responseJson, function (index, user) {
            var tableRaw = $("<tr>").appendTo($select);
            tableRaw.addClass("removable");
            $("<td>").addClass("name").text(user.name).appendTo(tableRaw);
            $("<td>").addClass("login").text(user.login).appendTo(tableRaw);
            $("<td>").addClass("password").text(user.password).appendTo(tableRaw);
            $("<td>").addClass("role").text(user.role).appendTo(tableRaw);
            var $tdButtonDelete = $('<td>').appendTo(tableRaw);
            $('<button>').addClass("delete btn btn-danger").text("delete").appendTo($tdButtonDelete);
            var $tdButtonUpdate = $('<td>').appendTo(tableRaw);
            $('<button>').addClass("updateUser btn btn-warning").text("update").appendTo($tdButtonUpdate);
        });
    });
}

