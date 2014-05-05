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

            // Mobile
            if (conf.mobile == true) {
                $(".mobileappshow").removeClass("hide");
                $(".mobileapphide").addClass("hide");
            }


            $('#sidrlink').sidr({
                speed: 0,
                displace: false,
                onOpen: function () {
                    $("#mobilemenu").removeClass('btnmenu-left').addClass('btnmenu-right');
                },
                onClose: function () {
                    $("#mobilemenu").removeClass('btnmenu-right').addClass('btnmenu-left');
                }
            });

            $(window).swipe({

                //                swipeRight: function (event, direction, distance, duration, fingerCount) {
                //                    // Open
                //                    $.sidr("open");
                //                },
                //
                //                swipeLeft: function (event, direction, distance, duration, fingerCount) {
                //                    // Close
                //                    $.sidr("close");
                //                },

                tap: function (event, direction, distance, duration, fingerCount) {
                    // Close
                    $.sidr("close");
                }

                //                excludedElements: "input, button, .noSwipe", //"input, button, a, li, ul, .noSwipe",
                //
                //                //Default is 75px
                //                threshold: 50
            });

            //$(document).swipe("disable");

            if ($.cookie("SESSION_OK")) {
                daos.userDao.getUserbyEmail($.cookie("SESSION_OK")).done(function (result) {
                    main.currentUser = result;
                    $(".MainView-subView").bEmpty();
                    brite.display("GroupsView", $(".MainView-subView"));
                })
            } else {
                $(".MainView-subView").bEmpty();
                brite.display("UserLoginView", $(".MainView-subView"));
            }

        },

        winEvents: {

            "resize": function (event) {
                if ($(window).width() > 768) {
                    //$(document).swipe("disable");
                }
            }

        },

        docEvents: {

            "USER_CHANGE": function (event, data) {
                main.currentUser = data;
                $("#side-menu-useremail").text(main.currentUser.useremail);
            },

            "GROUPS_CHANGE": function (event, data) {
                var user_email;
                if (data) {
                    user_email = data;
                } else {
                    user_email = main.currentUser.useremail;
                }
                daos.userDao.getUserbyEmail(user_email).done(function (result) {
                    main.currentUser = result;
                    $(".MainView-subView").bEmpty();
                    brite.display("GroupsView", $(".MainView-subView"));
                })
            },

            "ITEMS_CHANGE": function (event, data) {
                daos.groupDao.getGroup(main.currentGroup.groupcode).done(function (result) {
                    if (result) {
                        // error
                        if ($.cookie("EQUIZ_FLASH") == "error=getgroupbygroupcodefail") {
                            main.showError(result);
                        } else {
                            main.currentGroup = result;
                            main.currentGroupItems = main.currentGroup.titemCollection;
                            $(".MainView-subView").bEmpty();
                            brite.display("ItemsView", $(".MainView-subView"));
                        }

                    } else {
                        BootstrapDialog.alert("Este grupo no tiene preguntas");
                        $(".MainView-subView").bEmpty();
                        brite.display("GroupsView", $(".MainView-subView"));
                    }
                })

            },

            "NEXT_ITEM": function (event, data) {
                if (data) {
                    $(".MainView-subView").bEmpty();
                    brite.display("ItemView", $(".MainView-subView"), main.currentGroupItems[0]);
                } else {
                    BootstrapDialog.alert("Este grupo no tiene preguntas");
                    $(".MainView-subView").bEmpty();
                    brite.display("GroupsView", $(".MainView-subView"));
                }

            },

            "FINISH_ITEMS": function (event, data) {
                $(".MainView-subView").bEmpty();
                brite.display("ResultItemView", $(".MainView-subView"));
            }

        },

        events: {

            "btap; #side-menu": function (event) {
                $.sidr("close");
            },

            // on alert close
            "btap; .close": function (event) {
                $(".alert").bEmpty();
                $(".alert").append('<button type="button" class="close">×</button>');
                $(".alert").removeClass('in');
            },

            // on Back button
            "btap; .back": function (event) {
                if (!($(".back").hasClass("disabled"))) {
                    goBack();
                }
            },

            // on Groups side menu
            "btap; #side-menu-groups-button": function (event) {
                if (!($(".groups").hasClass("disabled"))) {
                    $(".MainView-subView").bEmpty();
                    brite.display("GroupsView", $(".MainView-subView"));
                }
            },

            // on Sincro
            "btap; #side-menu-sync-button": doSync,

            // on Profile
            "btap; .profile": function (event) {
                if (!($(".profile").hasClass("disabled"))) {
                    $(".MainView-subView").bEmpty();
                    brite.display("UpdateUserView", $(".MainView-subView"));
                }
            },

            // on Close session
            "btap; .sesclose": doLogOff
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
        } else if (currentViewName == "ItemView") {
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

    // LogOff function
    function doLogOff() {
        if (!($(".sesclose").hasClass("disabled"))) {
            daos.userDao.userLogout().done(function (result) {

                // Login error
                if ($.cookie("EQUIZ_FLASH") == "success=getlogoutok") {

                    $.removeCookie("SESSION_OK")

                    //$(document).swipe("disable");

                    //Login View
                    $(".MainView-subView").bEmpty();
                    brite.display("UserLoginView", $(".MainView-subView"));

                } else {
                    main.showError(result);
                }

            })
        }
    };

    // Manual Sync Function
    function doSync() {
        var view = this;
        if (!($(".syncro").hasClass("disabled"))) {
            // DO SYNC
            sync.sync(main.currentUser.useremail).pipe(function (result) {
                console.log(result);
                mobile.lastSync = "15/04/2014 22:03";
            });
        }
    };

})(jQuery);