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

        database.execSQL("INSERT INTO tgroup VALUES (?, ?, ?, ?, ?, ?)", [group.groupcode, group.groupname, group.groupcodestr, group.groupitemsnumber, obj.usertrait, obj.usertraitlastmodif]).done(function (result) {

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

        var promise = $.Deferred();

        database.execSQL("DELETE FROM tgroup WHERE groupcode=?", [groupcode]).done(function (result) {
            database.execSQL("DELETE FROM titem WHERE groupcode=?", [groupcode]).done(function (result) {
                database.execSQL("DELETE FROM tanswer WHERE groupcode=?", [groupcode]).done(function () {
                    promise.resolve("ok");
                });
            });
        });
        return promise;
    }

    BdGroupDaoHandler.prototype.deleteAllGroups = function () {

        var promise = $.Deferred();

        database.execSQL("DELETE FROM tgroup", []).done(function (result) {
            database.execSQL("DELETE FROM titem", []).done(function (result) {
                database.execSQL("DELETE FROM tanswer", []).done(function () {
                    promise.resolve("ok");
                });
            });
        });

        return promise;

    }

    BdGroupDaoHandler.prototype.linkGroup = function (data) {

        return database.execSQL("INSERT INTO tgroupsync VALUES (?, ?)", [data.codestr, "link"]);


    }

    BdGroupDaoHandler.prototype.unLinkGroup = function (data) {

        var promise = $.Deferred();

        database.execSQL("INSERT INTO tgroupsync VALUES (?, ?)", [data.codestr, "unlink"]).done(function () {
            database.execSQL("SELECT * FROM tgroup WHERE groupcodestr=?", [data.codestr]).done(function (resultgroup) {
                if (resultgroup.rows.length > 0) {
                    var rowgroup = resultgroup.rows.item(0);
                    var groupcode = rowgroup['groupcode'];
                    database.execSQL("DELETE FROM tgroup WHERE groupcode=?", [groupcode]).done(function (result) {
                        database.execSQL("DELETE FROM titem WHERE groupcode=?", [groupcode]).done(function (result) {
                            database.execSQL("DELETE FROM tanswer WHERE groupcode=?", [groupcode]).done(function () {
                                promise.resolve("ok");
                            });
                        });
                    });
                }
            });
        });
        return promise;
    }

    BdGroupDaoHandler.prototype.updateUserTraitGroup = function (data) {
        // data: useremail, groupcode, usertrait

        return database.execSQL("UPDATE tgroup SET usertrait=" + data.usertrait + " WHERE groupcode=" + data.groupcode);

    }

    BdGroupDaoHandler.prototype.getUserTraitGroup = function (data) {

        // data: useremail, groupcode

        var promise = $.Deferred();

        database.execSQL("SELECT * FROM tgroup WHERE groupcode=?", [data.groupcode]).done(function (result) {
            var row = result.rows.item(0);
            promise.resolve(row.usertrait);
        });

        return promise;

    }

    BdGroupDaoHandler.prototype.getGroup = function (groupcode) {

        var promise = $.Deferred();

        var group = null;

        // groupcode, groupname, groupcodestr, groupitemsnumber, usertrait
        database.execSQL("SELECT * FROM tgroup WHERE groupcode=" + groupcode, []).done(function (resultgroup) {

            if (resultgroup.rows.length > 0) {

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

            }

        });

        return promise;
    }


    // --------- /DAO Interface Implementation --------- //

    brite.BdGroupDaoHandler = BdGroupDaoHandler;


})(jQuery);