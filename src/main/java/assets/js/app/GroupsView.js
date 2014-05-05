(function () {

    brite.registerView("GroupsView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            main.currentGroup = null;
            view.groups = main.currentUserGroups(main.currentUser);
            return render("tmpl-GroupsView", {
                groups: view.groups
            });
        },

        init: function (data, config) {

        },

        postDisplay: function (data, config) {

            $("#bread-crumb").text("Grupos");

            // Side menu
            $(".mobile-menu").prop("disabled", false);
            if ($(window).width() < 768) {
                //$(document).swipe("enable");
            }

            // Back
            $("#top-menu-back-button").addClass("hide").removeClass("show");
            $("#side-menu-back-button").addClass("disabled");
            // Groups
            $("#side-menu-groups-button").addClass("disabled");
            // Profile
            $("#top-menu-profile-button").addClass("show").removeClass("hide");
            // Close
            $("#top-menu-sesclose-button").addClass("show").removeClass("hide");
            $("#side-menu-sesclose-button").removeClass("disabled");
            // Sync
            $("#side-menu-sync-button").removeClass("disabled");

            $("#side-menu-useremail").text(main.currentUser.useremail);

            if (main.currentUser.useradmin == true) {
                $(".admin").addClass("show").removeClass("hide");
                $(".user").addClass("hide").removeClass("show");
                $(".copy-groupcodestr").each(function () {
                    $(this).zclip({
                        path: conf.assets + 'js/lib/ZeroClipboard.swf',
                        copy: function () {
                            return $(this).text();
                        },
                        afterCopy: function (event) {
                            main.showInfo("Código copiado al portapapeles");
                        }
                    })
                });

            } else {
                $(".admin").addClass("hide").removeClass("show");
                $(".user").addClass("show").removeClass("hide");
            }

            // Popover
            if ((main.popover) || (main.currentUser.useradmin == false)) {
                $("[data-toggle=popover]").attr('data-toggle', '');
            } else {
                $("#groupcodestr").popover('show');
                window.setTimeout(function () {
                    $("#groupcodestr").popover('hide');

                }, 2500);
                main.popover = true;
            }

            $('.items-section').readmore({
                moreLink: '<a class="readmore-js-toggle" href="#"><span class="pull-right">Más..</span></a>',
                lessLink: '<a class = "readmore-js-toggle"href="#"><span class="pull-right">Menos..</a>',
                heightMargin: 0,
                maxHeight: 45
            });

        },

        destroy: function () {
            $(".close").trigger("btap");
            $("#groupcodestr").popover('hide');
        },

        events: {

            // on New Group
            "btap; #new-group-li": function (event) {
                $(".MainView-subView").bEmpty();
                brite.display("NewGroupView", $(".MainView-subView"));
            },

            // on Join Group
            "btap; #join-group-li": function (event) {
                $(".MainView-subView").bEmpty();
                brite.display("JoinGroupView", $(".MainView-subView"));
            },

            // on Group
            "btap; #groupclick": showItems,

            // on Delete-Unlink
            "btap; #delete-group": function (event) {

                var view = this;
                if (main.currentUser.useradmin == true) {
                    // Show Confirmation and delete
                    BootstrapDialog.confirm('¿Seguro que quiere borrar el grupo? ¡No se puede deshacer!', function (result) {
                        if (result) {
                            deleteGroup(event, view);
                        }
                    });
                } else {
                    // Show Confirmation and unlink
                    BootstrapDialog.confirm('¿Seguro que quiere abandonar el grupo?', function (result) {
                        if (result) {
                            unLinkGroup(event, view);
                        }
                    });
                }
            }

        }

    });

    function showItems(event) {

        var view = this;
        var group = $(event.currentTarget).bEntity("Group");

        daos.groupDao.getGroup(group.id).done(function (result) {
            // error
            if ($.cookie("EQUIZ_FLASH") == "error=getgroupbygroupcodefail") {
                main.showError(result);
            } else {
                main.currentGroup = result;
                main.currentGroupItems = main.currentGroup.titemCollection;
                if (main.currentUser.useradmin == true) {
                    $(".MainView-subView").bEmpty();
                    brite.display("ItemsView", $(".MainView-subView"));
                } else {
                    view.$el.trigger("NEXT_ITEM");
                }
            }
        })

    }

    function deleteGroup(event, view) {

        var group = $(event.currentTarget).bEntity("Group");

        daos.groupDao.deleteGroup(group.id).done(function (result) {
            // error
            if ($.cookie("EQUIZ_FLASH") == "error=deletegroupfail") {
                main.showError(result);
            } else {
                view.$el.trigger("GROUPS_CHANGE");
            }
        })

    }

    function unLinkGroup(event, view) {

        var group = $(event.currentTarget).bEntity("Group");

        daos.groupDao.getGroup(group.id).done(function (result) {
            // error
            if ($.cookie("EQUIZ_FLASH") == "error=getgroupbygroupcodefail") {
                main.showError(result);
            } else {
                main.currentGroup = result;
                main.currentGroupItems = main.currentGroup.titemCollection;
                daos.groupDao.unLinkGroup({
                    useremail: main.currentUser.useremail,
                    codestr: main.currentGroup.groupcodestr
                }).done(function (result) {
                    // error
                    if ($.cookie("EQUIZ_FLASH") == "error=postunlinkgroupfail") {
                        main.showError(result);
                    } else {
                        view.$el.trigger("GROUPS_CHANGE");
                    }
                })
            }
        })

    }


})(jQuery);