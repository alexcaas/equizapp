(function () {

    brite.registerView("GroupsView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            return render("tmpl-GroupsView");
        },

        postDisplay: function (data, config) {

            // Side menu
            $(".mobile-menu").prop("disabled", false);

            // Back
            $("#top-menu-back-button").addClass("hide").removeClass("show");
            // Groups
            $("#side-menu-groups-button").addClass("disabled");
            // Profile
            $("#top-menu-profile-button").addClass("show").removeClass("hide");
            $("#side-menu-profile-button").removeClass("disabled");
            // Close
            $("#top-menu-sesclose-button").addClass("show").removeClass("hide");
            $("#side-menu-sesclose-button").removeClass("disabled");
            // Sync
            $("#side-menu-sync-button").removeClass("disabled");

        },

        events: {


        }

    });


})(jQuery);