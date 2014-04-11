// --------- RemoteGroupDaoHandler --------- //

(function ($) {

    /**
     * Create a RemoteGroupDaoHandler type
     *
     */
    function RemoteGroupDaoHandler() {
        this._entityType = "Group";
    }

    // --------- DAO Info Methods --------- //

    RemoteGroupDaoHandler.prototype.entityType = function () {
        return this._entityType;
    }

    // --------- /DAO Info Methods --------- //


    // --------- DAO Interface Implementation --------- //

    /**
     * DAO Interface. New Group.
     * @param {object} groupname groupitemsnumber
     * @return the entity or String (Promise)
     */
    RemoteGroupDaoHandler.prototype.newGroup = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: "/api/newgroup",
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
     * DAO Interface. Join Group.
     * @param {object} useremail codestr
     * @return the entity or String (Promise)
     */
    RemoteGroupDaoHandler.prototype.joinGroup = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: "/api/joingroup",
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
     * @param {} groupcode
     * @return String (Promise)
     */
    RemoteGroupDaoHandler.prototype.deleteGroup = function (groupcode) {

        var ajaxPromise = $.ajax({
            type: "DELETE", // Not supported in all browsers, better change it in beforeSend
            url: "/api/deletegroup/" + groupcode,
            headers: {
                "Accept": "*/*"
            }
        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    /**
     * DAO Interface. Get Group
     * @param {object} groupcode
     * @return the entity or String (Promise)
     */
    RemoteGroupDaoHandler.prototype.getGroup = function (groupcode) {

        var ajaxPromise = $.ajax({
            type: "GET",
            url: "/api/getgroup/" + groupcode,
            headers: {
                "Accept": "*/*"
            }

        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    /**
     * DAO Interface. Get Group by hashed group code
     * @param {object} hashedgroupcode
     * @return the entity
     */
    RemoteGroupDaoHandler.prototype.getGroupByHashedGroupCode = function (hashedgroupcode) {

        var ajaxPromise = $.ajax({
            type: "GET",
            url: "/api/gethashedgroup/" + hashedgroupcode,
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

    brite.RemoteGroupDaoHandler = RemoteGroupDaoHandler;

})(jQuery);

// --------- /RemoteGroupDaoHandler --------- //