var Tai = Tai || {};

(function ($) {


    Tai = function Tai(traitLevelRange) {
        this._traitLevelRange = [];
        this._range = (traitLevelRange - 1) / 2;
        for (var i = 0; i < traitLevelRange; i++) {
            var traitLevel = i - this._range;
            this._traitLevelRange.push(traitLevel);
        }

    }

    // conditional maximum likelihood estimation
    Tai.prototype.cmle = function (items) {
        // items = [{b: X, success: 1 or 0}, {b: Y, success: 1 or 0}, ..]

        var range = this._range;
        var finalL = 0;
        var oFinal;

        $.each(this._traitLevelRange, function (keytrait, valuetrait) {
            var o = valuetrait;
            var l = 1;

            $.each(items, function (key, value) {
                //alert(key + ": " + value);
                //console.log(key + ": " + value['b'] + " -- " + value['success']);

                var b = (value['b'] - range);
                var success = value['success'];

                var p = Tai.calculateRasch(o, b);


                if (success == 1) {
                    l = l * p
                } else {
                    l = l * (1 - p)
                }

            });

            if (finalL < l) {
                finalL = l;
                oFinal = o + range;
            }

        });

        return oFinal;

    }

    Tai.calculateRasch = function (o, b) {
        var aux = Math.pow(2.71828182, (o - b));
        return (aux / (1 + aux));
    }

    Tai.prototype.nextItem = function (itemsCollection, b) {

        var count = 0;
        var baux = b;
        var auxArray = [];

        while (auxArray.length < 1) {
            auxArray = Tai.nextItemHelper(itemsCollection, baux);
            count++;
            if (Tai.isEven(count)) {
                baux = baux + count;
            } else {
                baux = baux - count;
            }
        }

        if (auxArray.length > 0) {
            var key = Math.floor(Math.random() * auxArray.length);
            var elem = auxArray[key];
            main.currentGroupItems = jQuery.grep(itemsCollection, function (n, i) {
                return (n !== elem);
            });
            return elem;

        } else {
            return null;
        }
    }

    Tai.nextItemHelper = function (itemsCollection, b) {
        var auxArray = [];

        $.each(itemsCollection, function (key, value) {
            if (value.itemdifficulty == b) {
                auxArray.push(value);
            }
        });
        return auxArray;
    }

    Tai.isEven = function (someNumber) {
        return (someNumber % 2 == 0) ? true : false;
    };

    Tai.prototype.userTrait = function () {

    }

})(jQuery);