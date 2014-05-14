(function () {

    brite.registerView("ItemsView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            view.items = main.currentGroupItems;
            return render("tmpl-ItemsView", {
                items: view.items
            });
        },

        init: function (data, config) {

        },

        postDisplay: function (data, config) {

            $("#bread-crumb").text(main.currentGroup.groupname);

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

            $('.items-section').readmore({
                moreLink: '<a class="readmore-js-toggle" href="#"><span class="pull-right">Más..</span></a>',
                lessLink: '<a class = "readmore-js-toggle"href="#"><span class="pull-right">Menos..</a>',
                heightMargin: 0,
                maxHeight: 25
            });

        },

        destroy: function () {
            $(".close").trigger("btap");
        },

        events: {

            // on New Item
            "btap; #new-item-li": function (event) {

                $(".MainView-subView").bEmpty();
                brite.display("NewItemView", $(".MainView-subView"));
            },

            // on Item
            "btap; #itemclick": function (event) {
                var itemid = $(event.currentTarget).bEntity("Item").id;

                var item = main.getObjects(main.currentGroupItems, "itemid", itemid)[0];

                $(".MainView-subView").bEmpty();
                brite.display("EditItemView", $(".MainView-subView"), item);
            },

            // on Delete-Unlink
            "btap; #delete-item": function (event) {
                var view = this;
                // Show Confirmation and delete
                BootstrapDialog.confirm('¿Seguro que quiere borrar el Ítem? ¡No se puede deshacer!', function (result) {
                    if (result) {
                        deleteItem(event, view);
                    }
                });
            }

        }

    });

    function deleteItem(event, view) {

        var item = $(event.currentTarget).bEntity("Item");

        daos.itemDao.deleteItem(item.id).done(function (result) {
            // error
            if ($.cookie("EQUIZ_FLASH") == "error=deleteitemfail") {
                main.showError(result);
            } else {
                $(document).trigger("ITEMS_CHANGE");
            }
        })

    }

})(jQuery);