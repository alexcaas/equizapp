(function () {

    brite.registerView("MainView", {
        emptyParent: true
    }, {

        create: function () {
            return render("tmpl-MainView");
        },

        // Called after the view is displayed to the user
        postDisplay: function (data, config) {
            var view = this;

            $('#sidrlink').sidr();

            $(document).swipe({
                //Generic swipe handler for all directions
                swipeLeft: function (event, direction, distance, duration, fingerCount) {
                    // Close
                    $.sidr("close");
                },
                swipeRight: function (event, direction, distance, duration, fingerCount) {
                    // Open
                    $.sidr("open");
                },

                //Default is 75px
                threshold: 75
            });

            // Display the two sub-views
            brite.display("UserLoginView", view.$el.find(".MainView-subView"));
        },

        docEvents: {
            // METER CUANDO SALGA EL TECLADO
            //    $.sidr("close");

        },

        events: {

            // on alert close
            "btap; .close": function (event) {
                $(".alert").bEmpty();
                $(".alert").append('<button type="button" class="close">×</button>');
                $(".alert").removeClass(' in ');
            },

            // on Back button
            "btap; #top-menu-back-button": goBack,

            // on Groups side menu
            "btap; #side-menu-groups-button": function (event) {
                if (!($(".groups").hasClass("disabled"))) {
                    $(".MainView-subView").bEmpty();
                    $.sidr("close");
                    brite.display("GroupsView", $(".MainView-subView"));
                }
            },

            // on Sincro
            "btap; #side-menu-sync-button": doSync,

            // on Profile
            "btap; .profile": function (event) {
                if (!($(".profile").hasClass("disabled"))) {
                    $(".MainView-subView").bEmpty();
                    $.sidr("close");
                    brite.display("UpdateUserView", $(".MainView-subView"));
                }
            },

            // on Close session
            "btap; .sesclose": function (event) {
                if (!($(".sesclose").hasClass("disabled"))) {
                    $.sidr("close");
                    main.userDao.userLogout().pipe(function (result) {

                        // Login error
                        if ($.cookie("EQUIZ_FLASH") == "success=getlogoutok") {

                            main.currentUserEmail = null;
                            main.currentUserAdmin = null;
                            //Login View
                            $(".MainView-subView").bEmpty();
                            brite.display("UserLoginView", $(".MainView-subView"));

                        } else {
                            main.showError(result);
                        }

                    })
                }
            }

        }

    });

    // Back
    function goBack() {
        var currentViewName = main.currentView.name;
        var nextView = null;

        if (currentViewName == "RegisterUserView") {
            nextView = "UserLoginView";

        } else if (currentViewName == "UpdateUserView") {
            nextView = "GroupsView";
        } else if (currentViewName == "NewGroupView") {
            nextView = "GroupsView";
        } else if (currentViewName == "JoinGroupView") {
            nextView = "GroupsView";
        } else if (currentViewName == "ItemsView") {
            nextView = "GroupsView";
        } else if (currentViewName == "NewItemView") {
            nextView = "ItemsView";
        } else if (currentViewName == "EditItemView") {
            nextView = "ItemsView";
        } else if (currentViewName == "ResultItemView") {
            nextView = "GroupsView";
        }

        $(".MainView-subView").bEmpty();
        brite.display(nextView, $(".MainView-subView"));

    };

    // Manual Sync Function
    function doSync() {
        var view = this;
        if (!($(".syncro").hasClass("disabled"))) {
            $.sidr("close");
            // DO SYNC
        }

    };

})(jQuery);