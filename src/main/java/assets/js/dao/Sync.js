var sync = sync || {};

(function () {

    // 
    sync.sync = function (useremail) {

        var promise = $.Deferred();

        var user = {};
        user["useremail"] = useremail;

        var groups = [];

        database.execSQL("SELECT lastsync FROM tlastsync", []).done(function (resultlastsync) {
            var row = resultlastsync.rows.item(0);

            user["lastsyncdate"] = row['lastsync'];

            database.execSQL("SELECT * FROM tgroupsync", []).done(function (resultgroupssync) {

                for (var i = 0; i < resultgroupssync.rows.length; ++i) {
                    var rowgroupsync = resultgroupssync.rows.item(i);

                    groups.push({
                        "groupcodestr": rowgroupsync['groupcodestr'],
                        "operation": rowgroupsync['operation'],
                        "usertrait": -1,
                        "usertraitlastmodif": -1
                    });

                };

                database.execSQL("SELECT * FROM tgroup", []).done(function (resultgroup) {

                    for (var i = 0; i < resultgroup.rows.length; ++i) {
                        var rowgroup = resultgroup.rows.item(i);

                        groups.push({
                            "groupcodestr": rowgroup['groupcodestr'],
                            "operation": "",
                            "usertrait": rowgroup['usertrait'],
                            "usertraitlastmodif": rowgroup['usertraitlastmodif']
                        });
                    };

                    user["SyncGroups"] = groups;

                    var ajaxPromise = $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: conf.host + "/api/syncuser",
                        headers: {
                            "Accept": "*/*"
                        },
                        data: JSON.stringify(user)
                    });

                    ajaxPromise.fail(function (XHR, textStatus) {
                        promise.reject(textStatus);
                        throw "Something failed getting data from the server - " + textStatus;
                    });

                    ajaxPromise.done(function (result) {

                        var count = 0;

                        // Login error
                        if (result == "postSyncFail") {
                            main.showError("Error sincronizando");
                            promise.reject(textStatus);
                        } else {

                            var groups = result.SyncGroups;

                            $.each(groups, function (key, obj) {
                                if (obj.hasOwnProperty("tgroup")) {
                                    count++;
                                }
                            });

                            if (count == 0) {
                                promise.resolve("ok");
                            } else {
                                daos.groupDao.deleteAllGroups();
                            }

                            $.each(groups, function (key, obj) {

                                daos.groupDao.insertGroup(obj).done(function () {
                                    count--;

                                    if (count == 0) {
                                        // 
                                        database.execSQL('DELETE FROM tlastsync').done(function () {
                                            database.execSQL("INSERT INTO tlastsync VALUES (?)", [result.lastsyncdate]).done(function () {

                                                database.execSQL('DELETE FROM tgroupsync').done(function () {
                                                    mobile.showSync();
                                                    promise.resolve("ok");

                                                });
                                            });
                                        });
                                    }
                                });
                            });
                        }
                    });

                });
            });
        });

        return promise;
    };

    sync.getLastSync = function () {
        var promise = $.Deferred();
        database.execSQL('SELECT * FROM tlastsync').done(function (result) {
            if (result.rows.length > 0) {
                var row = result.rows.item(0);
                promise.resolve(row.lastsync);
            } else {
                promise.resolve(0);
            }
        })
        return promise;
    }

})(jQuery);