<script>
    <%--data table --%>
    $(document).ready( function () {
        $('#tb-transaction').DataTable();
    } );

    // date format
    $(document).ready(function () {
        $('.datepicker').datepicker({
            format: 'dd-mm-yyyy'
        });
    });

    // form registrasi
    $('#register-form').validate({
        rules : {
            fname:"required",
            brithDate:"required",
            username:{
                required: true,
                max: 10
            },
            password:{
                required: true,
                max: 6
            },
            password2:{
                required: true,
                equalto: '#password'
            }
        },
        messages: {
            fname:{
                required: "First Name Canot Empty"
            },
            brithDate:{
                required: "Birth Date Canot Empty"
            },
            username:{
                required: "Username Canot Empty",
                max:"Max 10"
            },
            password:{
                required: "Password Canot Empty",
                max:"Max 6"
            },
            password2:{
                required: "Please Confirm Your Password",
                equalTo: "Password Not Match"
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

    //    ------------------- unreg ---------------
    $(function () {
        $('.btn-unreg').on('click', function () {
            var id = $(this).data('key');
            $('#yes-unreg').remove();
            $('#button-grup').append("<a href='http://localhost:8888/wallet/unreg?id="+id+"' id='yes-unreg' class='btn btn-primary'>Yes</a>");
        });
    });
</script>