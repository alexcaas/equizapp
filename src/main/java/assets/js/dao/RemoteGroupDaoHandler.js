// --------- RemoteGroupDaoHandler --------- //

(function ($) {

    function RemoteGroupDaoHandler(host) {
        this._entityType = "Group";
        this._items = null;
        if (host) {
            this._host = host;
        } else {
            this._host = "";
        }
    }

    // --------- DAO Info Methods --------- //

    RemoteGroupDaoHandler.prototype.entityType = function () {
        return this._entityType;
    }

    // --------- /DAO Info Methods --------- //


    // --------- DAO Interface Implementation --------- //

    RemoteGroupDaoHandler.prototype.newGroup = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: this._host + "/api/newgroup",
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

    RemoteGroupDaoHandler.prototype.linkGroup = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: this._host + "/api/linkgroup",
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

    RemoteGroupDaoHandler.prototype.unLinkGroup = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: this._host + "/api/unlinkgroup",
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

    RemoteGroupDaoHandler.prototype.deleteGroup = function (groupcode) {

        var ajaxPromise = $.ajax({
            type: "DELETE", // Not supported in all browsers, better change it in beforeSend
            url: this._host + "/api/deletegroup/" + groupcode,
            headers: {
                "Accept": "*/*"
            }
        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    RemoteGroupDaoHandler.prototype.getGroup = function (groupcode) {

        var ajaxPromise = $.ajax({
            type: "GET",
            url: this._host + "/api/getgroup/" + groupcode,
            headers: {
                "Accept": "*/*"
            }

        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    RemoteGroupDaoHandler.prototype.getGroupByHashedGroupCode = function (hashedgroupcode) {

        var ajaxPromise = $.ajax({
            type: "GET",
            url: this._host + "/api/gethashedgroup/" + hashedgroupcode,
            headers: {
                "Accept": "*/*"
            }
        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    RemoteGroupDaoHandler.prototype.groupChanges = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: this._host + "/api/groupchanges",
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

    brite.RemoteGroupDaoHandler = RemoteGroupDaoHandler;

})(jQuery);

// --------- /RemoteGroupDaoHandler --------- //