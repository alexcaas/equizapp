(function ($) {

    function BdGroupDaoHandler(host) {
        this._entityType = "Group";
        this._host = host;
    }

    // --------- DAO Info Methods --------- //

    BdGroupDaoHandler.prototype.entityType = function () {
        return this._entityType;
    }

    // --------- /DAO Info Methods --------- //


    // --------- DAO Interface Implementation --------- //

    BdGroupDaoHandler.prototype.insertGroup = function (obj) {

        var group = obj.tgroup;

        database.execSQL("INSERT INTO tgroup VALUES (?, ?, ?, ?, ?)", [group.groupcode, group.groupname, group.groupcodestr, group.groupitemsnumber, obj.usertrait]).done(function (result) {

            $.each(group.titemCollection, function (key1, item) {

                daos.itemDao.insertItem(item.itemid, group.groupcode, item.itemstring, item.itemdifficulty).done(function (result) {

                    $.each(item.tanswerCollection, function (key2, answer) {

                        daos.answerDao.insertAnswer(answer.answerid, group.groupcode, item.itemid, answer.answerstring, answer.answercorrect);

                    });

                });

            });

        });

    }

    BdGroupDaoHandler.prototype.deleteGroup = function (groupcode) {

        return database.execSQL("DELETE FROM tgroup WHERE groupcode=?", [groupcode]).done(function (result) {
            database.execSQL("DELETE FROM titem WHERE groupcode=?", [groupcode]).done(function (result) {
                database.execSQL("DELETE FROM tanswer WHERE groupcode=?", [groupcode]);
            });
        });

    }

    BdGroupDaoHandler.prototype.linkGroup = function (groupcode) {

        return database.execSQL("INSERT INTO tgroupsync VALUES (?, ?, ?)", [groupcodestr, "link", null]);

    }

    BdGroupDaoHandler.prototype.unlinkGroup = function (groupcode) {

        return database.execSQL("INSERT INTO tgroupsync VALUES (?, ?, ?)", [groupcodestr, "unlink", null]);

    }

    BdGroupDaoHandler.prototype.userTraitGroup = function (groupcode, usertrait) {

        return database.execSQL("INSERT INTO tgroupsync VALUES (?, ?, ?)", [groupcodestr, null, usertrait]);

    }

    BdGroupDaoHandler.prototype.getGroup = function (groupcode) {

        var promise = $.Deferred();

        var group = null;

        // groupcode, groupname, groupcodestr, groupitemsnumber, usertrait
        database.execSQL("SELECT * FROM tgroup WHERE groupcode=" + groupcode, []).done(function (resultgroup) {

            var rowgroup = resultgroup.rows.item(0);

            group = $.extend({}, {
                groupcode: rowgroup['groupcode'],
                groupname: rowgroup['groupname'],
                groupcodestr: rowgroup['groupcodestr'],
                groupitemsnumber: rowgroup['groupitemsnumber'],
                usertrait: rowgroup['usertrait'],
                titemCollection: null
            });

            daos.itemDao.getItems(groupcode).done(function (result) {
                group["titemCollection"] = result;
                promise.resolve(group);
            });

        });

        return promise;
    }


    // --------- /DAO Interface Implementation --------- //

    brite.BdGroupDaoHandler = BdGroupDaoHandler;


})(jQuery);