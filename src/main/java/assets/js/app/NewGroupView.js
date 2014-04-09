(function () {

    brite.registerView("NewGroupView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            return render("tmpl-NewGroupView");
        },

        postDisplay: function (data, config) {

            // Back
            $("#top-menu-back-button").addClass("show").removeClass("hide");
            // Groups
            $("#side-menu-groups-button").removeClass("disabled");
            // Profile
            $("#top-menu-profile-button").addClass("hide").removeClass("show");
            $("#side-menu-profile-button").addClass("disabled");
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