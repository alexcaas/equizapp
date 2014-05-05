(function ($) {

    function BdAnswerDaoHandler(host) {
        this._entityType = "Answer";
        this._host = host;
    }

    // --------- DAO Info Methods --------- //

    BdAnswerDaoHandler.prototype.entityType = function () {
        return this._entityType;
    }

    // --------- /DAO Info Methods --------- //


    // --------- DAO Interface Implementation --------- //

    BdAnswerDaoHandler.prototype.insertAnswer = function (answerid, groupcode, itemid, answerstring, answercorrect) {

        return database.execSQL("INSERT INTO tanswer VALUES (?, ?, ?, ?, ?)", [answerid, groupcode, itemid, answerstring, answercorrect]);

    }

    BdAnswerDaoHandler.prototype.deleteAnswer = function (answerid) {

        return database.execSQL("DELETE FROM tanswer WHERE answerid=?", [answerid]);

    }

    BdAnswerDaoHandler.prototype.getAnswers = function (itemid) {

        var promise = $.Deferred();

        var answers = [];

        // answerid, itemid, answerstring, answercorrect
        database.execSQL("SELECT * FROM tanswer WHERE itemid=" + itemid, []).done(function (resultanswer) {

            for (var j = 0; j < resultanswer.rows.length; ++j) {
                var rowanswer = resultanswer.rows.item(j);

                var answer = {};

                answer["answerid"] = rowanswer['answerid'];
                answer["itemid"] = rowanswer['itemid'];
                answer["answerstring"] = rowanswer['answerstring'];
                answer["answercorrect"] = rowanswer['answercorrect'];

                answers.push(answer);
            };

            promise.resolve(answers);
        });

        return promise;

    }


    // --------- /DAO Interface Implementation --------- //

    brite.BdAnswerDaoHandler = BdAnswerDaoHandler;


})(jQuery);