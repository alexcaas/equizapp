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
            // TODO: Uncomment if you want error messages.
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

    database.execSQL("CREATE TABLE tgroup (groupcode, groupname, groupcodestr, groupitemsnumber, usertrait)");
    database.execSQL("CREATE TABLE titem (itemid, groupcode, itemstring, itemdifficulty)");
    database.execSQL("CREATE TABLE tanswer (answerid, groupcode, itemid, answerstring, answercorrect)");
    database.execSQL("CREATE TABLE tlastsync (lastsync TIMESTAMP)");
    database.execSQL("INSERT INTO tlastsync VALUES (?)", [0]);

};

database.deleteTablesDb = function () {

    database.execSQL("DROP TABLE IF EXISTS tgroup");
    database.execSQL("DROP TABLE IF EXISTS titem");
    database.execSQL("DROP TABLE IF EXISTS tanswer");
    database.execSQL("DROP TABLE IF EXISTS tlastsync");

};