var $url = window.location.pathname;
var navigation;
var currentUser;
var musicTypes = [];

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

    FillTable();
    AddActionToAddUserButton();
    AddActionToUpdateUserButton();
    FillRoles();
    FillMusicTypes();
});

function FillMusicTypes() {
    var tableMusic = $('#tableMusic');
    tableMusic.find('.removableMusic').remove();
    musicTypes = [];
    $.get("musicTypes", function (responseJson) {
        $.each(responseJson, function (index, music) {
            musicTypes.push(music.musicType);
            var tableRow = $("<tr>").addClass("removableMusic").appendTo(tableMusic);
            $("<td>").text(music.id).addClass("musicId").css('display', 'none').appendTo(tableRow);
            $("<td>").text(music.musicType).appendTo(tableRow);
            var tdDeleteMusicButton = $("<td>").appendTo(tableRow);
            $("<button>").on('click', DeleteMusicType).text("delete music").addClass("deleteMusic btn btn-danger").appendTo(tdDeleteMusicButton);
        });
    });
}

function DeleteMusicType() {
    var musicId = $(this).closest("tr").find(".musicId").text();
    $.ajax({
        type: 'GET',
        url: $url + 'musicType/delete',
        data: {'musicId': musicId},
        async: false,
        success: function () {
            FillMusicTypes();
        }
    })
}

function AddMusicType() {
    var musicType = $('#newMusicType').val();
    $.ajax({
        type: 'POST',
        url: $url + 'musicType/add',
        data: {'newMusicType': musicType},
        async: false,
        success: function () {
            FillMusicTypes();
        }
    })
}

function SendToAddUser() {
    $('.content').hide();
    $('#addUser').show();
}

function AddActionToUpdateUserButton() {
    $('#updateUserBtn').click(function () {
        $('#updateName').val(currentUser.name);
        $('#updateLogin').val(currentUser.login);
        $('#updatePassword').val(currentUser.password);
        $('#updateRole').val(currentUser.role);
        $('#updateAddress').val(currentUser.address);
        $('.content').hide();
        $('#updateUser').show();
    })
}

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

function FillRoles() {
    $.get("roles", function (responseJson) {
        var $select = $(".roleSelect");
        $select.find("option").remove();
        $.each(responseJson, function (index, value) {
            $("<option>").val(value).text(value).appendTo($select);
        });
    });
}

function HideContentAndShowFirst() {
    $('.content').hide();
    navigation.find('.nav-item:first').addClass('active').show();
    $('.content:first').show();
}

function AddActionToInfoUserButton(table) {
    table.find('.infoUser').click(function () {
        var userId = $(this).closest("tr").find('.userId').text();
        ShowUserInfo(userId);
        $('.content').hide();
        $('#infoUser').show();
    });
}

function ShowUserInfo(userId) {
    $.ajax({
        type: 'GET',
        url: $url + 'user/get',
        data: {'userId': userId},
        dataType: 'JSON',
        async: false,
        success: function (user) {
            currentUser = user;
            var infoDiv = $('#infoUser');
            infoDiv.find('#infoId').val(user.id);
            infoDiv.find('#infoName').val(user.name);
            infoDiv.find('#infoLogin').val(user.login);
            infoDiv.find('#infoPassword').val(user.password);
            infoDiv.find('#infoRole').val(user.role);
            infoDiv.find('#infoAddress').val(user.address);
            FillFavoriteMusic(user);
            FillMusicSelectForUser();
        }
    });
}

function FillMusicSelectForUser() {
    if (currentUser !== undefined) {
        var $select = $(".musicTypesSelect");
        $select.find("option").remove();
        $.each(musicTypes, function (index, musicType) {
            var result = true;
            $.each(currentUser.favoriteMusic, function (index, userMusicType) {
                if (musicType === userMusicType) {
                    result = false;
                }
            });
            if (result) {
                $("<option>").val(musicType).text(musicType).appendTo($select);
            }
        })
    }
}

function FillFavoriteMusic(user) {
    var musicPref = $('#userMusic');
    musicPref.find('.music').remove();
    var removeMusic = $('.removeUserMusic');
    removeMusic.find("option").remove();
    $.each(user.favoriteMusic, function (index, music) {
        $('<li>').addClass('music').text(music).appendTo(musicPref);
        $("<option>").val(music).text(music).appendTo(removeMusic);
    })
}

function FillTable(selectedCategory, whatNeedToFind) {
    if (selectedCategory === undefined) {
        selectedCategory = 'all';
        whatNeedToFind = 'none';
    }
    var $select = $("#tableUsers");
    var data = {'selectedCategory': selectedCategory, 'whatNeedToFind': whatNeedToFind};
    $select.find('.removable').remove();
    $.post("users", data, function (responseJson) {
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
            $('<button>').addClass("infoUser btn btn-secondary").text("info").appendTo($tdButtonUpdate);
            $("<td>").addClass("userId").css('display', 'none').text(user.id).appendTo(tableRaw);
        });
        AddActionToDeleteButton($select);
        AddActionToInfoUserButton($select);
    });
}

function AddActionToDeleteButton(table) {
    table.find('.delete').click(function () {
        var userId = $(this).closest("tr").find(".userId").text();
        $.ajax({
            type: 'GET',
            url: $url + 'user/delete',
            data: {'userId': userId},
            async: false,
            success: function () {
                FillTable();
            },
            error: function () {
                alert("error receive data");
            }
        })
    })
}

function ConfirmUpdateAction() {
    var userToUpdateJson = {
        'id': currentUser.id,
        'name': $('#updateName').val(),
        'login': $('#updateLogin').val(),
        'password': $('#updatePassword').val(),
        'role': $('#updateRole').val(),
        'address': $('#updateAddress').val()
    };
    $.ajax({
        type: 'POST',
        url: $url + 'user/update',
        contentType: 'application/json',
        data: JSON.stringify(userToUpdateJson),
        async: false
    });
    FillTable();
    HideContentAndShowFirst();
}

function AddMusicToUser() {
    var musicType = $('#addUserMusic').val();
    $.ajax({
        type: 'POST',
        url: $url + 'user/addMusic',
        data: {'userId': currentUser.id, 'musicType': musicType},
        async: false,
        success: function () {
            ShowUserInfo(currentUser.id);
        }
    })
}

function DeleteMusicFromUser() {
    var musicType = $('#removeMusic').val();
    $.ajax({
        type: 'GET',
        url: $url + 'user/deleteMusic',
        data: {'userId': currentUser.id, 'musicType': musicType},
        async: false,
        success: function () {
            ShowUserInfo(currentUser.id);
        }
    });
}