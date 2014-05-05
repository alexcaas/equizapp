var conf = conf || {};

(function () {

    // Config
    conf.mobile = false;
    conf.assets = "/assets/";
    conf.host = "";

})(jQuery);

var daos = daos || {};

(function () {

    // DAOs init
    daos.userDao = brite.registerDao(new brite.RemoteUserDaoHandler(conf.host));
    daos.groupDao = brite.registerDao(new brite.RemoteGroupDaoHandler(conf.host));
    daos.itemDao = brite.registerDao(new brite.RemoteItemDaoHandler(conf.host));

})(jQuery);