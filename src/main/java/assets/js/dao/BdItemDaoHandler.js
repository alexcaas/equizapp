(function ($) {

    function BdItemDaoHandler() {
        this._entityType = "Item";
    }

    // --------- DAO Info Methods --------- //

    BdItemDaoHandler.prototype.entityType = function () {
        return this._entityType;
    }

    // --------- /DAO Info Methods --------- //


    // --------- DAO Interface Implementation --------- //

    BdItemDaoHandler.prototype.insertItem = function (itemid, groupcode, itemstring, itemdifficulty) {

        return database.execSQL("INSERT INTO titem VALUES (?, ?, ?, ?)", [itemid, groupcode, itemstring, itemdifficulty]);

    }

    BdItemDaoHandler.prototype.deleteItem = function (itemid) {

        return database.execSQL("DELETE FROM titem WHERE itemid=?", [itemid]);

    }

    BdItemDaoHandler.prototype.getItems = function (groupcode) {

        var promise = $.Deferred();
        var count = 0;
        var items = [];

        // itemid, groupcode, itemstring, itemdifficulty
        database.execSQL("SELECT * FROM titem WHERE groupcode=" + groupcode, []).done(function (resultitem) {

            count = resultitem.rows.length;

            if (count == 0) {
                promise.resolve(undefined);
            };

            for (var i = 0; i < resultitem.rows.length; ++i) {
                var rowitem = resultitem.rows.item(i);

                var item = {};

                item["itemid"] = rowitem['itemid'];
                item["groupcode"] = rowitem['groupcode'];
                item["itemstring"] = rowitem['itemstring'];
                item["itemdifficulty"] = rowitem['itemdifficulty'];
                item["tanswerCollection"] = null;

                items.push(item);

                daos.answerDao.getAnswers(rowitem['itemid']).done(function (result) {

                    $.each(items, function (index, value) {
                        if (value.itemid == result[0].itemid) {
                            value["tanswerCollection"] = result;
                            count--;
                            if (count == 0) {
                                promise.resolve(items);
                            }
                        }
                    });
                });

            };
        });

        return promise;
    }


    // --------- /DAO Interface Implementation --------- //

    brite.BdItemDaoHandler = BdItemDaoHandler;

})(jQuery);