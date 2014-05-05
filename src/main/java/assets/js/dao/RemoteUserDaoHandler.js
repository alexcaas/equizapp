// --------- RemoteUserDaoHandler --------- //

(function ($) {

    function RemoteUserDaoHandler(host) {
        this._entityType = "User";
        if (host) {
            this._host = host;
        } else {
            this._host = "";
        }
    }

    // --------- DAO Info Methods --------- //

    RemoteUserDaoHandler.prototype.entityType = function () {
        return this._entityType;
    }

    // --------- /DAO Info Methods --------- //


    // --------- DAO Interface Implementation --------- //

    RemoteUserDaoHandler.prototype.getUserbyEmail = function (useremail) {
        var ajaxPromise = $.ajax({
            type: "GET",
            url: this._host + "/api/getuser/" + useremail
        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    RemoteUserDaoHandler.prototype.userLogin = function (data) {
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

    RemoteUserDaoHandler.prototype.userLogout = function () {
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

    RemoteUserDaoHandler.prototype.registerUser = function (data) {

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

    RemoteUserDaoHandler.prototype.updateUser = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: this._host + "/api/updateuser",
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

    brite.RemoteUserDaoHandler = RemoteUserDaoHandler;


})(jQuery);

// --------- /RemoteUserDaoHandler --------- //