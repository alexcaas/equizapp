(function ($) {

    function BdUserDaoHandler(host) {
        this._entityType = "User";
        if (host) {
            this._host = host;
        } else {
            this._host = "";
        }
    }

    // --------- DAO Info Methods --------- //

    BdUserDaoHandler.prototype.entityType = function () {
        return this._entityType;
    }

    // --------- /DAO Info Methods --------- //


    // --------- DAO Interface Implementation --------- //

    BdUserDaoHandler.prototype.userLogin = function (data) {
        var ajaxPromise = $.ajax({
            type: "POST",
            url: this._host + "/api/login",
            headers: {
                "Accept": "*/*"
            },
            data: data
        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    BdUserDaoHandler.prototype.userLogout = function () {
        var ajaxPromise = $.ajax({
            type: "GET",
            headers: {
                "Accept": "*/*"
            },
            url: this._host + "/api/logout"
        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    BdUserDaoHandler.prototype.getUserbyEmail = function (useremail) {

        var promise = $.Deferred();

        var user = {};
        user["useremail"] = useremail;
        user["useradmin"] = false; // always user

        var groups = [];

        // groupcode, groupname, groupcodestr, groupitemsnumber, usertrait
        database.execSQL("SELECT * FROM tgroup", []).done(function (resultgroup) {

            for (var i = 0; i < resultgroup.rows.length; ++i) {
                var rowgroup = resultgroup.rows.item(i);

                var usergroup = {};
                var group = {};

                group["groupcode"] = rowgroup['groupcode'];
                group["groupname"] = rowgroup['groupname'];
                group["groupcodestr"] = rowgroup['groupcodestr'];
                group["groupitemsnumber"] = rowgroup['groupitemsnumber'];
                group["titemCollection"] = null;

                usergroup["usertrait"] = rowgroup['usertrait'];
                usergroup["tgroup"] = group;

                groups.push(usergroup);

            };

            user.tusergroupCollection = groups;

            promise.resolve(user);

        });

        return promise;
    }

    BdUserDaoHandler.prototype.registerUser = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: this._host + "/api/registeruser",
            headers: {
                "Accept": "*/*"
            },
            data: data
        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    // --------- /DAO Interface Implementation --------- //

    brite.BdUserDaoHandler = BdUserDaoHandler;

})(jQuery);