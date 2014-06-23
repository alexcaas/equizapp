$(document).ready(function () {
    brite.display("MainView", "#pageBody");
});

var mobile = mobile || {};
var conf = conf || {};

(function () {

    // Config
    conf.mobile = true;
    conf.assets = "/assets/"; // ""
    conf.host = "http://54.76.6.10:8080"; // "http://192.168.1.8:8080";

    // Last sync
    mobile.lastSync = "";

    // showSync
    mobile.showSync = function showSync() {
        sync.getLastSync().done(function (result) {
            var date = new Date(result);
            var dt = date.toLocaleDateString();
            var hr = date.toLocaleTimeString();
            mobile.lastSync = dt + "-" + hr;
            $("#last-sync").text(mobile.lastSync);
        })
    };

})(jQuery);

var daos = daos || {};

(function () {

    // DAOs init
    daos.userDao = brite.registerDao(new brite.BdUserDaoHandler(conf.host));
    daos.groupDao = brite.registerDao(new brite.BdGroupDaoHandler());
    daos.itemDao = brite.registerDao(new brite.BdItemDaoHandler());
    daos.answerDao = brite.registerDao(new brite.BdAnswerDaoHandler());

})(jQuery);