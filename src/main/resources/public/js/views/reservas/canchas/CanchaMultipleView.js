define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/reservas/canchas/canchaMultiple.html'],
    function ($, _, Backbone, App, canchaMultiple) {

        var canchaMultipleView = Backbone.View.extend({

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(canchaMultiple);
                App.lanzarEventoLoad();
            }

        });

        return canchaMultipleView;

    });