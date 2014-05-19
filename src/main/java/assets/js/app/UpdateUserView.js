(function () {

    brite.registerView("UpdateUserView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            view.user = main.currentUser;
            return render("tmpl-UpdateUserView", {
                user: view.user
            });
        },

        init: function (data, config) {

        },

        postDisplay: function (data, config) {

            $("#bread-crumb").text("Actualizar Usuario");

            // Back
            $("#top-menu-back-button").addClass("show").removeClass("hide");
            $("#side-menu-back-button").removeClass("disabled");
            // Groups
            $("#side-menu-groups-button").removeClass("disabled");
            // Profile
            $("#top-menu-profile-button").addClass("hide").removeClass("show");
            // Close
            $("#top-menu-sesclose-button").addClass("show").removeClass("hide");
            $("#side-menu-sesclose-button").removeClass("disabled");
            // Sync
            $("#side-menu-sync-button").removeClass("disabled");

        },

        destroy: function () {
            $(".close").trigger("btap");
        },

        events: {

            // on Register
            "btap; [data-action='updateuser']": doUpdateUser,

            // on Submit
            "submit; .form-horizontal": function (event) {
                event.preventDefault();
            }


        }

    });

    // Update User function
    function doUpdateUser() {

        var view = this;

        var $inputusername = view.$el.find("#updu-user-name");
        var $inputuserlastnames = view.$el.find("#updu-user-lastnames");
        var $inputuserpasword = view.$el.find("#updu-user-password");
        var $inputuserretypepass = view.$el.find("#updu-user-retypepassword");

        if (($inputusername.val() == '') && ($inputuserlastnames.val() == '') && ($inputuserpasword.val() == '') && ($inputuserretypepass.val() == '')) {

            main.showInfo("Nada que actualizar");

        } else {

            if ($inputusername[0].validity.valid && $inputuserlastnames[0].validity.valid && $inputuserpasword[0].validity.valid && $inputuserretypepass[0].validity.valid) {

                if (($inputuserpasword.val() != '') || ($inputuserretypepass.val() != '')) {

                    if ($inputuserpasword.val() != $inputuserretypepass.val()) {

                        main.showError("Las contraseñas han de ser iguales");
                        return;
                    }

                }

                return daos.userDao.updateUser({
                    useremail: main.currentUser.useremail,
                    username: $inputusername.val(),
                    userlastnames: $inputuserlastnames.val(),
                    userpassword: $inputuserpasword.val()
                }).done(function (result) {
                    if (result == "postUpdateUserFail") {
                        main.showError("Se ha producido un error actualizando los datos del usuario");
                    } else {
                        main.showInfo("Actualizado correctamente");
                        $(document).trigger("USER_CHANGE", result);
                    }
                })

            }
        }
    };

})(jQuery);