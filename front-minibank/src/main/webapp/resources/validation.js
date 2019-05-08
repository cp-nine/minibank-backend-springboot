$('#register-form').validate({
    rules : {
        fname:{
            required: true
        }
    },
    messages: {
        fname:{
            required: "Masukan Nama Depan"
        }
    },
    errorElement: 'div',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.grup-input').append(error);
    },
    highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
    }
});