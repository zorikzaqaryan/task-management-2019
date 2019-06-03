var FILE_FORMAT_ERROR = 'File format should be jpeg, gif, png';
var FILE_SIZE_ERROR = 'File size should be less then 5 MB';
var ERROR_SELECTOR = '#preview_error';
var REQUIRED_FIELDS_ERROR = 'Fill required fields for preview';

$(document).on("click", "#preview_button", function (event) {
    var task = getTaskFields();
    if (task.email !== '' && task.content !== '' && task.username !== '') {
        $(ERROR_SELECTOR).text("");
        //Prepare data
        var formData = new FormData();
        var file = $("input[type='file']")[0].files[0];
        formData.append("pic", file);
        formData.append("email", task.email);
        formData.append("username", task.username);
        formData.append("content", task.content);
// 5 mb file limit
        if (file.size > 5242880) {
            $(ERROR_SELECTOR).text(FILE_SIZE_ERROR);
            return;
        }
        if (!isImage(file.name)) {
            $(ERROR_SELECTOR).text(FILE_FORMAT_ERROR);
            return;
        }
        $.ajax({
            url: '/preview',
            cache: false,
            contentType: false,
            processData: false,
            data: formData,
            type: 'POST',
            success: function (data) {
                OpenPreview(data);
            },
            error: function (err) {
                $('#preview_error').text(err.responseText);
            }
        });
    } else {
        $('#preview_error').text(REQUIRED_FIELDS_ERROR);
    }

});

getTaskFields = function () {
    var task = {};
    task.file = $('#file').val();
    task.email = $('#email').val();
    task.content = $('#content').val();
    task.username = $('#username').val();
    return task;
};

/**
 * set modal fields and open modal
 */
OpenPreview = function (data) {
    $('#previewUsername').text(data.username);
    $('#previewEmail').text(data.email);
    $('#previewContent').text(data.content);
    $("#previewImage").attr("src", 'data:image/jpg;base64,' + data.image);
    $('#myModal').modal('show');
};

/**
 * Getting image extension
 */
function getExtension(filename) {
    var parts = filename.split('.');
    return parts[parts.length - 1];
}

/**
 * Check image extension
 * @param filename name of file
 * @returns {boolean}
 */
function isImage(filename) {
    var ext = getExtension(filename);
    switch (ext.toLowerCase()) {
        case 'jpeg':
        case 'gif':
        case 'png':
            return true;
    }
    return false;
}

/**
 * Validate form submit before sending request to server
 * Will be called during submit
 */
$(function () {
    $('#taskForm').submit(function () {
        var file = $("input[type='file']")[0].files[0];
        if (file && !isImage(file.name)) {
            $(ERROR_SELECTOR).text(FILE_FORMAT_ERROR);
            return false;
        }
    });
});