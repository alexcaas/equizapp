(function () {

    brite.registerView("RegisterUserView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            return render("tmpl-RegisterUserView");
        },

        postDisplay: function (data, config) {

            // Back
            $("#top-menu-back-button").addClass("show").removeClass("hide");
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

        events: {



        }


    });

})(jQuery);