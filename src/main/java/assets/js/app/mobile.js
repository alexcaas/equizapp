var mobile = mobile || {};
var conf = conf || {};

(function () {

    // Config
    conf.mobile = true;
    conf.assets = "/assets/";
    conf.host = "";

    // Last sync
    mobile.lastSync = "15/04/2014 22:03";

    // TODO mobile.showSync
    mobile.showSync = function showSync() {};

})(jQuery);

var daos = daos || {};

(function () {

    // DAOs init
    daos.userDao = brite.registerDao(new brite.BdUserDaoHandler(conf.host));
    daos.groupDao = brite.registerDao(new brite.BdGroupDaoHandler());
    daos.itemDao = brite.registerDao(new brite.BdItemDaoHandler());
    daos.answerDao = brite.registerDao(new brite.BdAnswerDaoHandler());

    // TODO Inicializar en otro sitio y quitar
    //database.deleteTablesDb();
    //database.initializeDb();


})(jQuery);

// TODO QUITAR
//sync.sync("jimena@email.com").pipe(function (result) {
//    console.log(result);
//});