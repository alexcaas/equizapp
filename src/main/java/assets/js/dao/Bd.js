var database = database || {};
var db = db || {};

database.open = function (name) {
    try {
        if (!window.openDatabase) {
            return null;
        } else {
            return openDatabase(name, "1", name, 65536);
        }
    } catch (e) {
        return null;
    }
}

db = database.open("equiz");

// Add the execSQL function.
database.execSQL = function (sql, params, successFun, errorFun) {

    var progress = $.Deferred();

    if (!params) {
        params = [];
    }

    if (!successFun) {
        // Default result function reports errors.
        successFun = function (result) {
            var elms = [];
            if (typeof (result.rows) === 'undefined') {
                return elms;
            }

            for (var i = 0; i < result.rows.length; ++i) {
                elms.push(result.rows.item(i));
            }
            return elms;
        }
    };


    if (!errorFun) {
        // Default result function reports errors.
        errorFun = function (error) {
            // Uncomment if you want error messages.
            //alert("Query Error: " + error.message);
        };
    }

    db.transaction(
        function (tx) {
            tx.executeSql(
                sql,
                params,
                function (tx, result) {
                    progress.resolve(result);
                    successFun(result);
                },
                function (tx, error) {
                    progress.reject(error);
                    errorFun(error);
                });
        });

    return progress;
};

database.initializeDb = function () {

    database.execSQL("CREATE TABLE IF NOT EXISTS tgroup (groupcode, groupname, groupcodestr, groupitemsnumber, usertrait, usertraitlastmodif)");
    database.execSQL("CREATE TABLE IF NOT EXISTS titem (itemid, groupcode, itemstring, itemdifficulty)");
    database.execSQL("CREATE TABLE IF NOT EXISTS tanswer (answerid, groupcode, itemid, answerstring, answercorrect)");
    database.execSQL("CREATE TABLE IF NOT EXISTS tgroupsync (groupcodestr, operation)");
    database.execSQL("CREATE TABLE IF NOT EXISTS tlastsync (lastsync TIMESTAMP)");
    database.execSQL("SELECT * FROM tgroup", []).done(function (result) {
        if (result.rows.length < 1) {
            database.execSQL("INSERT INTO tlastsync VALUES (?)", [0]);
        }
    });
};

database.deleteTablesDb = function () {

    database.execSQL("DROP TABLE IF EXISTS tgroup");
    database.execSQL("DROP TABLE IF EXISTS titem");
    database.execSQL("DROP TABLE IF EXISTS tanswer");
    database.execSQL("DROP TABLE IF EXISTS tgroupsync");
    database.execSQL("DROP TABLE IF EXISTS tlastsync");

};


//database.deleteTablesDb();
database.initializeDb();