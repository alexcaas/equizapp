// --------- RemoteItemDaoHandler --------- //

(function ($) {

    function RemoteItemDaoHandler(host) {
        this._entityType = "Item";
        this._answers = null;
        if (host) {
            this._host = host;
        } else {
            this._host = "";
        }
    }

    // --------- DAO Info Methods --------- //

    RemoteItemDaoHandler.prototype.entityType = function () {
        return this._entityType;
    }

    // --------- /DAO Info Methods --------- //


    // --------- DAO Interface Implementation --------- //

    RemoteItemDaoHandler.prototype.newItem = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: this._host + "/api/newitem",
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

    RemoteItemDaoHandler.prototype.updateItem = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: this._host + "/api/updateitem",
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

    RemoteItemDaoHandler.prototype.deleteItem = function (itemid) {

        var ajaxPromise = $.ajax({
            type: "DELETE", // Not supported in all browsers, better change it in beforeSend
            url: this._host + "/api/deleteitem/" + itemid,
            headers: {
                "Accept": "*/*"
            }
        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }


    // --------- /DAO Interface Implementation --------- //

    brite.RemoteItemDaoHandler = RemoteItemDaoHandler;

})(jQuery);

// --------- /RemoteUserDaoHandler --------- //