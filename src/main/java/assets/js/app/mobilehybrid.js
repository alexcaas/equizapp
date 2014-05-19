$(document).ready(function () {

    webshims.polyfill('forms forms-ext');

    brite.display("MainView", "#pageBody");
});


////The "deviceready" event is sent when the system
////has finished loading.
//document.addEventListener(
//    "deviceready",
//    function () {
//        
//    },
//    true);
//

//Close the application when the back key is pressed.
document.addEventListener(
    "backbutton",
    function () {

        if ($("#side-menu-back-button").hasClass("disabled")) {
            BootstrapDialog.confirm('¿Seguro que quiere salir?', function (result) {
                if (result) {
                    mosync.app.exit();
                }
            });
        } else {
            $("#side-menu-back-button").trigger("btap");
        }
    },
    true);

var mobile = mobile || {};
var conf = conf || {};

(function () {

    // Config
    conf.mobile = true;
    conf.mobilehybrid = true;
    conf.assets = "";
    conf.host = "http://192.168.1.8:8080";

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