// --------- RemoteItemDaoHandler --------- //

(function ($) {

    /**
     * Create a RemoteItemDaoHandler type
     *
     */
    function RemoteItemDaoHandler() {
        this._entityType = "User";
    }

    // --------- DAO Info Methods --------- //

    RemoteItemDaoHandler.prototype.entityType = function () {
        return this._entityType;
    }

    // --------- /DAO Info Methods --------- //


    // --------- DAO Interface Implementation --------- //

    /**
     * DAO Interface. New Item.
     * @param {object} itemstring itemdifficulty groupcode
     *      answerstring1 answercorrect1 answerstring2 answercorrect2 answerstring3= answercorrect3
     * @return the entity or String (Promise)
     */
    RemoteItemDaoHandler.prototype.newItem = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: "/api/newitem",
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

    /**
     * DAO Interface. Update Item.
     * @param {object} itemstring itemdifficulty groupcode
     *      answerstring1 answercorrect1 answerstring2 answercorrect2 answerstring3= answercorrect3
     * @return the entity or String (Promise)
     */
    RemoteItemDaoHandler.prototype.updateItem = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: "/api/updateitem",
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

    /**
     * DAO Interface. Delete Group.
     * @param {} itemid
     * @return String (Promise)
     */
    RemoteGroupDaoHandler.prototype.deleteGroup = function (itemid) {

        var ajaxPromise = $.ajax({
            type: "DELETE", // Not supported in all browsers, better change it in beforeSend
            url: "/api/deleteitem/" + itemid,
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