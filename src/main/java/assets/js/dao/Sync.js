var sync = sync || {};

(function () {

    //
    sync.sync = function (useremail) {




        sync.syncUser(useremail).done(function (result) {


        })


    };

    // 
    sync.sync = function (useremail) {

        //        {
        //            "useremail": "jimena@email.com",
        //            "lastsyncdate": 23432432423,
        //            "SyncGroups": [{
        //                    "groupcode": 40,
        //                    "operation": "link",
        //                    "usertrait": 10
        //                },
        //                {
        //                    "groupcode": 15,
        //                    "operation": "link",
        //                    "usertrait": 7
        //                }]
        //    
        //        }

        var progress = $.Deferred();

        var user = {};
        user["useremail"] = useremail;

        var groups = [];

        database.execSQL("SELECT lastsync FROM tlastsync", [], function (resultlastsync) {
            var row = resultlastsync.rows.item(0);

            user["lastsyncdate"] = row['lastsync'];

            database.execSQL("SELECT * FROM tgroup", [], function (resultgroup) {

                for (var i = 0; i < resultgroup.rows.length; ++i) {
                    var rowgroup = resultgroup.rows.item(i);

                    groups.push({
                        "groupcode": rowgroup['groupcode'],
                        "operation": rowgroup['operation'],
                        "usertrait": rowgroup['usertrait']
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
                    throw "Something failed getting data from the server - " + textStatus;
                });

                ajaxPromise.done(function (result) {

                    var count = 0;

                    // Login error
                    if ($.cookie("EQUIZ_FLASH") == "error=postsyncfail") {
                        main.showError(result);
                    } else {

                        $.each(result, function (key, obj) {
                            if (obj.hasOwnProperty("tgroup")) {
                                count++;
                            }
                        });



                        $.each(result, function (key, obj) {

                            daos.groupDao.deleteGroup(obj.tgroup.groupcode).done(function (result3) {

                                daos.groupDao.insertGroup(obj).done(function (result4) {
                                    count--;
                                    if (count === 0) {
                                        // TODO actualizar lastsyncdate
                                        database.execSQL('UPDATE tlastsync SET lastsync = "' + result.lastsyncdate + '"', []).done(function (result2) {
                                            progress.resolve("ok");

                                        });
                                    }
                                });
                            });

                        });


                    }
                });

            });
        });

        return progress;
    };

})(jQuery);