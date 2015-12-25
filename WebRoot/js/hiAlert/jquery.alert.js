(function(a) {
    a.alerts = {
        horizontalOffset: 0,
        dialogClass: null,
        overAlert: function(c, b) {
            a.alerts._overShow(c, b)
        },
        _overShow: function(d, c) {
            if (c == null) {
                c = 3000
            }
            var b = c + 600;
            a("body").append('<div id="over_container" style="display:none"><div id="over_message"></div></div>');
            a("#over_message").text(d).html(a("#over_message").text().replace(/\n/g, "<br />"));
            if (a.alerts.dialogClass) {
                a("#over_container").addClass(a.alerts.dialogClass)
            }
            var e = (a.browser.msie && parseInt(a.browser.version) <= 6) ? "absolute" : "fixed";
            a("#over_container").css({
                minWidth: a("#over_container").outerWidth(),
                maxWidth: a("#over_container").outerWidth()
            });
            a.alerts._overReposition();
            a("#over_container").css({
                position: e,
                zIndex: 99999,
                width: 350,
                padding: 0,
                margin: 0
            }).show("fast");
            setTimeout(function() {
                a("#over_container").hide("fast")
            },
			c);
            setTimeout(function() {
                a("#over_container").remove()
            },
			b)
        },
        _overReposition: function() {
            var c = ((a(window).height() / 2));
            var b = ((a(window).width() / 2) - (a("#over_container").outerWidth() / 2)) + a.alerts.horizontalOffset;
            if (c < 0) {
                c = 0
            }
            if (b < 0) {
                b = 0
            }
            if (a.browser.msie && parseInt(a.browser.version) <= 6) {
                c = c + a(window).scrollTop()
            }
            if (a.browser.msie && parseInt(a.browser.version) <= 6) {
                b = b - 175
            }
            a("#over_container").css({
                top: c + "px",
                left: b + "px"
            });
        },
    };
    hiOverAlert = function(c, b) {
        a.alerts.overAlert(c, b)
    }
})(jQuery);
