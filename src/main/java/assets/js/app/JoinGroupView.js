(function () {

    brite.registerView("JoinGroupView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            return render("tmpl-JoinGroupView");
        },

        init: function (data, config) {

        },

        postDisplay: function (data, config) {

            $("#bread-crumb").text("Unirse a Grupo");

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

            // Ok
            "btap; [data-action='joingroup']": doJoinGroup,

            // on Submit
            "submit; .form-horizontal": function (event) {
                event.preventDefault();
            }

        }

    });

    // Join Group function
    function doJoinGroup() {

        var view = this;
        var $groupcodestr = view.$el.find("#codegroup");

        if ($groupcodestr[0].validity.valid) {

            daos.groupDao.linkGroup({
                useremail: main.currentUser.useremail,
                codestr: $groupcodestr.val().toUpperCase()
            }).done(function (result) {
                if ($.cookie("EQUIZ_FLASH") == "error=postlinkgroupfail") {
                    main.showError(result);
                } else {
                    // refresh user-groups
                    view.$el.trigger("GROUPS_CHANGE");
                }
            })
        }
    };


})(jQuery);