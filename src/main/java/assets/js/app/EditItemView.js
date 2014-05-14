(function () {

    brite.registerView("EditItemView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            view.item = data;
            view.answera = data.tanswerCollection[0];
            view.answerb = data.tanswerCollection[1];
            view.answerc = data.tanswerCollection[2];
            return render("tmpl-EditItemView", {
                item: view.item,
                answera: view.answera,
                answerb: view.answerb,
                answerc: view.answerc
            });
        },

        init: function (data, config) {

        },

        postDisplay: function (data, config) {

            $('textarea').autosize();

            $("#bread-crumb").text("Editar Item");

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

            $.each(data.tanswerCollection, function (index, value) {
                if (value.answercorrect == true) {
                    $("#edit-radio-" + index).prop('checked', true);
                }
            });

            $("#edit-difficulty").val(data.itemdifficulty);

        },

        destroy: function () {
            $(".close").trigger("btap");
        },

        events: {

            // on Edit item
            "btap; [data-action='edititem']": doEditItem,

            // on Submit
            "submit; .form-horizontal": function (event) {
                event.preventDefault();
            }

        }

    });

    // Edit Item function
    function doEditItem() {

        var view = this;
        var $question = view.$el.find("#edit-question");
        var $answera = view.$el.find("#edit-answer-a");
        var $answerb = view.$el.find("#edit-answer-b");
        var $answerc = view.$el.find("#edit-answer-c");
        var $radioa = view.$el.find("#edit-radio-0");
        var $radiob = view.$el.find("#edit-radio-1");
        var $radioc = view.$el.find("#edit-radio-2");
        var $difficulty = view.$el.find("#edit-difficulty");

        if ($question[0].validity.valid && $answera[0].validity.valid && $answerb[0].validity.valid && $answerc[0].validity.valid && $radioa[0].validity.valid && $radiob[0].validity.valid && $radioc[0].validity.valid && $difficulty[0].validity.valid) {

            daos.itemDao.updateItem({
                itemid: view.item.itemid,
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
                if ($.cookie("EQUIZ_FLASH") == "error=postupdateitemandanswersfail") {
                    main.showError(result);
                } else {
                    $(document).trigger("ITEMS_CHANGE");
                }
            })
        }
    };


})(jQuery);