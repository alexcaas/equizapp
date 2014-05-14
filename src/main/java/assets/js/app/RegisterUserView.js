(function () {

    brite.registerView("RegisterUserView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            return render("tmpl-RegisterUserView");
        },

        init: function (data, config) {

        },

        postDisplay: function (data, config) {

            $("#bread-crumb").text("Registrar Usuario");

            // Mobile
            if (conf.mobile == true) {
                $(".mobileapphide").addClass("hide");
            }

            // Back
            $("#top-menu-back-button").addClass("show").removeClass("hide");
            $("#side-menu-back-button").removeClass("disabled");
            // Groups
            $("#side-menu-groups-button").addClass("disabled");
            // Profile
            $("#top-menu-profile-button").addClass("hide").removeClass("show");
            // Close
            $("#top-menu-sesclose-button").addClass("hide").removeClass("show");
            $("#side-menu-sesclose-button").addClass("disabled");
            // Sync
            $("#side-menu-sync-button").addClass("disabled");

        },

        destroy: function () {
            $(".close").trigger("btap");
        },

        events: {

            // on Register
            "btap; [data-action='registeruser']": doRegister,

            // on Submit
            "submit; .form-horizontal": function (event) {
                event.preventDefault();
            }
        }

    });

    // Register function
    function doRegister() {

        var view = this;
        var $useremail = view.$el.find("#regu-user-email");
        var $inputusername = view.$el.find("#regu-user-name");
        var $inputuserlastnames = view.$el.find("#regu-user-lastnames");
        var $inputuserpasword = view.$el.find("#regu-user-password");
        var $inputuserretypepass = view.$el.find("#regu-user-retypepassword");
        var $inputuseradmin = view.$el.find("#regu-user-admin");

        if ($useremail[0].validity.valid && $inputusername[0].validity.valid && $inputuserlastnames[0].validity.valid && $inputuserpasword[0].validity.valid && $inputuserretypepass[0].validity.valid) {

            if ($inputuserpasword.val() == $inputuserretypepass.val()) {
                main.checkInternetConnection().done(function (result) {
                    daos.userDao.registerUser({
                        useremail: $useremail.val(),
                        username: $inputusername.val(),
                        userlastnames: $inputuserlastnames.val(),
                        userpassword: $inputuserpasword.val(),
                        useradmin: $inputuseradmin.is(':checked')
                    }).done(function (result) {
                        if (($.cookie("EQUIZ_FLASH") == "error=postregisteruserfail") || ($.cookie("EQUIZ_FLASH") == "error=postregisteruseralreadyexists")) {
                            main.showError(result);
                        } else {
                            $(document).trigger("USER_CHANGE", result);
                            $(".MainView-subView").bEmpty();
                            brite.display("GroupsView", $(".MainView-subView"));
                        }
                    })
                });
            } else {
                main.showError("Las contraseñas han de ser iguales");
            }
        }
    };


})(jQuery);