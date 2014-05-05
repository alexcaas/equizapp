brite.viewDefaultConfig.loadTmpl = true;
brite.viewDefaultConfig.loadCss = false;
brite.config.jsPath = conf.assets + "js/app/";
brite.config.tmplPath = conf.assets + "tmpl/";
//brite.config.css = conf.assets + "css/";


$(document).ready(initialize);

function initialize() {

    brite.display("MainView", "#pageBody");
};

Handlebars.templates = Handlebars.templates || {};

function render(templateName, data) {
    var tmpl = Handlebars.templates[templateName];

    if (!tmpl) {
        var tmplContent = $("#" + templateName).html();
        tmpl = Handlebars.compile(tmplContent);
        Handlebars.templates[templateName] = tmpl;
    }
    return tmpl(data).trim();
}

var main = main || {};

(function () {

    // Current View
    main.currentView = null;
    // CurrentUser
    main.currentUser = null;
    // CurrentUserGroups
    main.currentUserGroups = function (user) {
        var groups = [];

        if (user.tusergroupCollection) {
            $.each(user.tusergroupCollection, function (key, value) {
                groups.push(value.tgroup);
            });
        }

        return groups;
    };
    // currentGroup
    main.currentGroup = null;
    // currentGroupItems
    main.currentGroupItems = null;

    // Popover
    main.popover = false;

    // getObjects
    main.getObjects = function getObjects(obj, key, val) {
        var objects = [];
        for (var i in obj) {
            if (!obj.hasOwnProperty(i)) continue;
            if (typeof obj[i] == 'object') {
                objects = objects.concat(main.getObjects(obj[i], key, val));
            } else if (i == key && obj[key] == val) {
                objects.push(obj);
            }
        }
        return objects;
    }

    // Dialogs and alerts
    main.showError = function showError(message) {

        $(".alert").removeClass('alert-success').addClass('alert-danger');
        if (!($(".alert").hasClass('in'))) {
            $(".alert").append(message);
            $(".alert").addClass('in');
        }
        window.setTimeout(function () { // hide alert message
            $(".close ").trigger("btap");
        }, 3000);
    }

    main.showInfo = function showInfo(message) {

        $(".alert").removeClass('alert-danger').addClass('alert-success');
        if (!($(".alert ").hasClass('in'))) {
            $(".alert").append(message);
            $(".alert").addClass('in');
        }
        window.setTimeout(function () { // hide alert message
            $(".close").trigger("btap");
        }, 3000);
    }

    BootstrapDialog.confirm = function (message, callback) {
        new BootstrapDialog({
            title: 'Confirmación',
            message: message,
            closable: false,
            data: {
                'callback': callback
            },
            buttons: [{
                label: 'Cancelar',
                action: function (dialog) {
                    typeof dialog.getData('callback') === 'function' && dialog.getData('callback')(false);
                    dialog.close();
                }
                    }, {
                label: 'Aceptar',
                cssClass: 'btn-primary',
                action: function (dialog) {
                    typeof dialog.getData('callback') === 'function' && dialog.getData('callback')(true);
                    dialog.close();
                }
                    }]
        }).open();
    };

    BootstrapDialog.alert = function (message, callback) {
        new BootstrapDialog({
            title: 'Información',
            message: message,
            data: {
                'callback': callback
            },
            closable: false,
            buttons: [{
                label: 'Aceptar',
                action: function (dialog) {
                    typeof dialog.getData('callback') === 'function' && dialog.getData('callback')(true);
                    dialog.close();
                }
                    }]
        }).open();
    };

})(jQuery);