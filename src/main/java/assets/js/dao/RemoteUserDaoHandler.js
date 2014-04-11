// --------- RemoteUserDaoHandler --------- //

(function ($) {

    /**
     * Create a RemoteUserDaoHandler type
     *
     */
    function RemoteUserDaoHandler() {
        this._entityType = "User";
    }

    // --------- DAO Info Methods --------- //

    RemoteUserDaoHandler.prototype.entityType = function () {
        return this._entityType;
    }

    // --------- /DAO Info Methods --------- //


    // --------- DAO Interface Implementation --------- //

    /**
     * DAO Interface. Get User by email
     * @param {String} useremail
     * @return the entity or String (Promise)
     */
    RemoteUserDaoHandler.prototype.getUserbyEmail = function (useremail) {
        var ajaxPromise = $.ajax({
            type: "GET",
            url: "/api/getuser/" + useremail
        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    /**
     * DAO Interface. Login user.
     * @param {object} useremail, password
     * @return the entity or String (Promise)
     */
    RemoteUserDaoHandler.prototype.userLogin = function (data) {
        var ajaxPromise = $.ajax({
            type: "POST",
            url: "/api/login",
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
     * DAO Interface. Logout user.
     * @param {}
     * @return String (Promise)
     */
    RemoteUserDaoHandler.prototype.userLogout = function () {
        var ajaxPromise = $.ajax({
            type: "GET",
            headers: {
                "Accept": "*/*"
            },
            url: "/api/logout"
        });

        ajaxPromise.fail(function (XHR, textStatus) {
            throw "Something failed getting data from the server - " + textStatus;
        });

        return ajaxPromise;
    }

    /**
     * DAO Interface. Register user.
     * @param {object} useremail username userlastnames userpassword useradmin
     * @return the entity or String (Promise)
     */
    RemoteUserDaoHandler.prototype.registerUser = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: "/api/registeruser",
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
     * DAO Interface. Update user.
     * @param {object} username userlastnames userpassword
     * @return the entity or String (Promise)
     */
    RemoteUserDaoHandler.prototype.updateUser = function (data) {

        var ajaxPromise = $.ajax({
            type: "POST",
            url: "/api/updateuser",
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