// Here we are telling brite to load the /tmpl/ViewName.tmpl and /css/ViewName.css
// on demand. Very useful for development, could be turn off for production (where files could 
// be pre-compile and concatenated).
brite.config.jsPath = "/assets/js/app/";
brite.config.css = "/assets/css/";
brite.config.tmplPath = "/assets/tmpl/";
brite.viewDefaultConfig.loadTmpl = true;
brite.viewDefaultConfig.loadCss = false;

// When the document is ready, we display the MainView  
$(document).ready(function () {

    brite.display("MainView", "#pageBody");

});

// Just a little indirection to render a template using handlebars.
// This simple indirection allows much flexibility later one, 
// when using pre-compiling or other templating engine are needed.
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
// Best-Practice: always scope your code in function (here we use immediate javascript function)
(function () {

    // Current View
    main.currentView = null;

    // CurrentUserEmail
    main.currentUserEmail = null;

    // CurrentUserAdmin
    main.currentUserAdmin = null;

    // DAOs init
    main.userDao = brite.registerDao(new brite.RemoteUserDaoHandler());

    main.showError = function showError(message) {

        if (!($(".alert").hasClass('in'))) {
            $(".alert").append(message);
            $(".alert").addClass(' in ');
        }
    }



})(jQuery);