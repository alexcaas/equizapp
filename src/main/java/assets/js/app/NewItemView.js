(function () {

    brite.registerView("NewItemView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            return render("tmpl-NewItemView");
        },

        init: function (data, config) {

        },

        postDisplay: function (data, config) {

            $('textarea').autosize();

            $("#bread-crumb").text("Nuevo Item");

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

            // on New item
            "btap; [data-action='newitem']": doNewItem,

            // on Submit
            "submit; .form-horizontal": function (event) {
                event.preventDefault();
            }

        }

    });

    // New Item function
    function doNewItem() {

        var view = this;
        var $question = view.$el.find("#question");
        var $answera = view.$el.find("#answer-a");
        var $answerb = view.$el.find("#answer-b");
        var $answerc = view.$el.find("#answer-c");
        var $radioa = view.$el.find("#radio-0");
        var $radiob = view.$el.find("#radio-1");
        var $radioc = view.$el.find("#radio-2");
        var $difficulty = view.$el.find("#difficulty");

        if ($question[0].validity.valid && $answera[0].validity.valid && $answerb[0].validity.valid && $answerc[0].validity.valid && $radioa[0].validity.valid && $radiob[0].validity.valid && $radioc[0].validity.valid && $difficulty[0].validity.valid) {

            daos.itemDao.newItem({
                itemstring: $question.val(),
                itemdifficulty: $difficulty.val(),
                groupcode: main.currentGroup.groupcodestr,
                answerstring1: $answera.val(),
                answercorrect1: $radioa.is(':checked'),
                answerstring2: $answerb.val(),
                answercorrect2: $radiob.is(':checked'),
                answerstring3: $answerc.val(),
                answercorrect3: $radioc.is(':checked')
            }).done(function (result) {
                if ($.cookie("EQUIZ_FLASH") == "error=postnewitemandanswersfail") {
                    main.showError(result);
                } else {
                    $(document).trigger("ITEMS_CHANGE");
                }
            })
        }
    };

})(jQuery);