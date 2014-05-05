(function () {

    brite.registerView("NewGroupView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            return render("tmpl-NewGroupView");
        },

        init: function (data, config) {

        },

        postDisplay: function (data, config) {

            $("#bread-crumb").text("Nuevo Grupo");

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
            "btap; [data-action='newgroup']": doNewGroup,

            // on Submit
            "submit; .form-horizontal": function (event) {
                event.preventDefault();
            }
        }

    });

    // New Group function
    function doNewGroup() {

        var view = this;
        var $groupname = view.$el.find("#group-name");
        var $groupitemsnumber = view.$el.find("#group-num-items");


        if ($groupname[0].validity.valid && $groupitemsnumber[0].validity.valid) {

            daos.groupDao.newGroup({
                groupname: $groupname.val(),
                groupitemsnumber: $groupitemsnumber.val()
            }).done(function (result) {
                if ($.cookie("EQUIZ_FLASH") == "error=postnewgroupfail") {
                    main.showError(result);
                } else {
                    daos.groupDao.linkGroup({
                        useremail: main.currentUser.useremail,
                        codestr: result.groupcodestr
                    }).done(function (result) {
                        if ($.cookie("EQUIZ_FLASH") == "error=postlinkgroupfail") {
                            main.showError(result);
                        } else {
                            // refresh user-groups
                            view.$el.trigger("GROUPS_CHANGE");
                        }
                    })
                }
            })


        }

    };

})(jQuery);