(function () {

    brite.registerView("ResultItemView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            return render("tmpl-ResultItemView");
        },

        init: function (data, config) {

        },

        postDisplay: function (data, config) {

            $("#bread-crumb").text("Resultado");

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

            // on Ok item
            "btap; [data-action='gostart']": function (event) {
                this.$el.trigger("GROUPS_CHANGE");
            }
        }

    });

})(jQuery);