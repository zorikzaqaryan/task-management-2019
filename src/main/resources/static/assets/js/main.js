$(document).on("click", "#preview_button", function (event) {
    var task = getTaskFields();

    if (task.email !== '' && task.content !== '' && task.username !== '') {
        $('#preview_error').text("");
        //Prepare data
        var formData = new FormData();
        var file = $("input[type='file']")[0].files[0];
        formData.append("pic", file);
        formData.append("email", task.email);
        formData.append("username", task.username);
        formData.append("content", task.content);
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
                // alert(err.responseText);
            }
        });
    } else {
        $('#preview_error').text("Fill required fields for preview");
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

OpenPreview = function (data) {
    $('#previewUsername').text(data.username);
    $('#previewEmail').text(data.email);
    $('#previewContent').text(data.content);
    $("#previewImage").attr("src", 'data:image/jpg;base64,' + data.image);
    $('#myModal').modal('show');
};