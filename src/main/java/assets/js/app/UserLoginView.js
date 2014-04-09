(function () {

    brite.registerView("UserLoginView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            return render("tmpl-UserLoginView");
        },

        postDisplay: function (data, config) {

            // Side menu
            $(".mobile-menu").prop("disabled", true);

            // Back
            $("#top-menu-back-button").addClass("hide").removeClass("show");
            // Groups
            $("#side-menu-groups-button").addClass("disabled");
            // Profile
            $("#top-menu-profile-button").addClass("hide").removeClass("show");
            $("#side-menu-profile-button").addClass("disabled");
            // Close
            $("#top-menu-sesclose-button").addClass("hide").removeClass("show");
            $("#side-menu-sesclose-button").addClass("disabled");
            // Sync
            $("#side-menu-sync-button").addClass("disabled");

        },

        destroy: function () {

        },


        events: {

            // on OK
            "btap; [data-action='Loginuser']": doLogin,

            // on Enter key press
            "keypress; .form-horizontal": function (event) {
                if (event.which == 13) {
                    $("#login-user-button").trigger("btap");
                }
            },

            // on Submit
            "submit; .form-horizontal": function (event) {
                event.preventDefault();
            },

            // on Register
            "btap; #register-button": function (event) {
                $(".MainView-subView").bEmpty();
                brite.display("RegisterUserView", $(".MainView-subView"));
            }

        }

    });

    // Login function
    function doLogin() {

        var view = this;
        var $inputemail = view.$el.find("#user-email");
        var $inputpass = view.$el.find("#user-password");
        return main.userDao.userLogin({
            useremail: $inputemail.val(),
            password: $inputpass.val()
        }).pipe(function (result) {

            // Login error
            if ($.cookie("EQUIZ_FLASH") == "error=postloginfail") {
                main.showError(result);
            } else { //Groups View
                main.currentUserEmail = result.useremail;
                main.currentUserAdmin = result.useradmin;
                $(".MainView-subView").bEmpty();
                brite.display("GroupsView", $(".MainView-subView"));
            }
        })

    };

})(jQuery);