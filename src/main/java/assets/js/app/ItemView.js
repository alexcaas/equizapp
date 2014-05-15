(function () {

    brite.registerView("ItemView", {

        create: function (data, config) {
            var view = this;
            main.currentView = view;
            if (data) {
                view.item = data;
                view.answera = data.tanswerCollection[0];
                view.answerb = data.tanswerCollection[1];
                view.answerc = data.tanswerCollection[2];
                return render("tmpl-ItemView", {
                    item: view.item,
                    answera: view.answera,
                    answerb: view.answerb,
                    answerc: view.answerc
                });
            } else {
                BootstrapDialog.alert("Este grupo no tiene preguntas");
                $(".MainView-subView").bEmpty();
                brite.display("GroupsView", $(".MainView-subView"));
            }
        },

        init: function (data, config) {

        },

        postDisplay: function (data, config) {

            var view = this;

            $('textarea').autosize();

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

            //
            $("#questionnumber").text(main.itemsnumber + "/" + main.currentGroup.groupitemsnumber);
            $("#questiondific").text("Dificultad: " + view.item.itemdifficulty);

        },

        destroy: function () {
            $(".close").trigger("btap");
        },

        events: {

            // on Ok item
            "btap; [data-action='okitem']": doOkItem,

            // on Next item
            "btap; [data-action='nextitem']": doNextItem,

            // on Finish item
            "btap; [data-action='finitem']": doFinishItem,

            // on Submit
            "submit; .form-horizontal": function (event) {
                event.preventDefault();
            }

        }

    });

    // Ok Item function
    function doOkItem() {

        var view = this;
        var value = getValue();
        view.answerCorrect = false;

        var answer = view.item.tanswerCollection[value].answercorrect

        if (answer == true || answer == "true") {

            view.answerCorrect = true;

            var elem_answer = "answer-" + value;
            var elem_answer_label = "label[for='" + elem_answer + "']";
            var elem_img = "#span-ok-" + value;

            view.$el.find("#" + elem_answer).closest("div").addClass("has-success");
            view.$el.find(elem_answer_label).addClass("green");
            view.$el.find(elem_img).removeClass("hide");

        } else {

            view.answerCorrect = false;

            var elem_correct;
            if (view.item.tanswerCollection[0].answercorrect) {
                elem_correct = 0;
            } else if (view.item.tanswerCollection[1].answercorrect) {
                elem_correct = 1;
            } else {
                elem_correct = 2;
            }
            var elem_answer = "answer-" + value;
            var elem_answer_label = "label[for='" + elem_answer + "']";
            var elem_img = "#span-notok-" + value;

            view.$el.find("#" + elem_answer).closest("div").addClass("has-error");
            view.$el.find(elem_answer_label).addClass("red");
            view.$el.find(elem_img).removeClass("hide");

            var elem_answer_correct = "answer-" + elem_correct;
            var elem_answer_label_correct = "label[for='" + elem_answer_correct + "']";

            view.$el.find("#" + elem_answer_correct).closest("div").addClass("has-success");
            view.$el.find(elem_answer_label_correct).addClass("green");
        }

        if (main.currentGroup.groupitemsnumber == main.itemsnumber) {
            view.$el.find("[data-action='okitem']").addClass("hide");
            view.$el.find("[data-action='nextitem']").addClass("hide");
            view.$el.find("[data-action='finitem']").removeClass("hide");
        } else {
            view.$el.find("[data-action='okitem']").addClass("hide");
            view.$el.find("[data-action='nextitem']").removeClass("hide");
            view.$el.find("[data-action='finitem']").addClass("hide");
        }

        view.$el.find("input[name='answercorrect-radios']").attr("disabled", "disabled");
        $("#questionnumber").text(main.itemsnumber + "/" + main.currentGroup.groupitemsnumber);
        $("#questiondific").text("Dificultad: " + view.item.itemdifficulty);

    };

    function getValue() {


        if ($('input[name=answercorrect-radios]:radio:checked').length > 0) {
            return $('input[name=answercorrect-radios]:radio:checked').attr("id");
        } else {
            return null;
        }
    };

    function doNextItem() {

        var view = this;
        var success;

        if (main.currentGroup.groupitemsnumber > main.itemsnumber) {
            if (view.answerCorrect == true) {
                success = 1;
            } else {
                success = 0;
            }

            // items = [{b: X, success: 1 or 0}, {b: Y, success: 1 or 0}, ..]
            main.taiItems.push({
                b: view.item.itemdifficulty,
                success: success
            });

            main.taiB = main.tai.cmle(main.taiItems);
            var nxtItem = main.tai.nextItem(main.currentGroupItems, main.taiB);
            main.itemsnumber++;

            $(document).trigger("NEXT_ITEM", nxtItem);

        }

    };

    function doFinishItem() {

        var view = this;

        main.itemsnumber = 1;

        if (view.answerCorrect == true) {
            success = 1;
        } else {
            success = 0;
        }

        // items = [{b: X, success: 1 or 0}, {b: Y, success: 1 or 0}, ..]
        main.taiItems.push({
            b: view.item.itemdifficulty,
            success: success
        });

        $(document).trigger("FINISH_ITEMS");

    };

})(jQuery);