/**
 * Created by Lins on 10/12/2015.
 */
/**=========================================================
 * Module: config.js
 * App routes and resources configuration
 =========================================================*/


(function() {
    'use strict';

    angular
        .module('app.routes')
        .config(routesConfig);

    routesConfig.$inject = ['$stateProvider', '$locationProvider', '$urlRouterProvider', 'RouteHelpersProvider'];
    function routesConfig($stateProvider, $locationProvider, $urlRouterProvider, helper){

        // Set the following to true to enable the HTML5 Mode
        // You may have to set <base> tag in index and a routing configuration in your server
        $locationProvider.html5Mode(false);

        // defaults to dashboard
        $urlRouterProvider.otherwise('/app/dashboard');

        //
        // Application Routes
        // -----------------------------------
        $stateProvider
            .state('app', {
                url: '/app',
                abstract: true,
                templateUrl: helper.basepath('app.html'),
                resolve: helper.resolveFor('modernizr', 'icons')
            })
            .state('app.dashboard', {
                url: '/dashboard',
                title: 'Principal',
                templateUrl: helper.basepath('dashboard/dashboard.html')
            })
            .state('app.singleview', {
                url: '/singleview',
                title: 'Single View',
                templateUrl: helper.basepath('singleview.html')
            })
            .state('app.submenu', {
                url: '/submenu',
                title: 'Submenu',
                templateUrl: helper.basepath('submenu.html')
            })
            .state('app.newCliente', {
                url: '/newCliente',
                title: 'NovoCliente',
                templateUrl: helper.basepath('cliente/newClientePessoaFisica.html'),
                resolve: helper.resolveFor('parsley', 'angularFileUpload', 'filestyle','ui.select')
            })
            .state('app.newClientePJ', {
                url: '/newClientePJ',
                title: 'NovoCliente',
                templateUrl: helper.basepath('cliente/newClientePessoaJuridica.html'),
                resolve: helper.resolveFor('parsley', 'angularFileUpload', 'filestyle', 'ui.select')
            })
            .state('app.newContrato', {
                url: '/newContrato',
                title: 'Contrato',
                templateUrl: helper.basepath('contrato/newContrato.html'),
                resolve: helper.resolveFor('parsley', 'angularFileUpload', 'filestyle', 'ui.select')

            })
            .state('app.viewCorretores', {
                url: '/viewCorretores',
                title: 'Corretor',
                templateUrl: helper.basepath('corretor/NewCorretor.html')
            })
            //
            // CUSTOM RESOLVES
            //   Add your own resolves properties
            //   following this object extend
            //   method
            // -----------------------------------
            // .state('app.someroute', {
            //   url: '/some_url',
            //   templateUrl: 'path_to_template.html',
            //   controller: 'someController',
            //   resolve: angular.extend(
            //     helper.resolveFor(), {
            //     // YOUR RESOLVES GO HERE
            //     }
            //   )
            // })
        ;

    } // routesConfig

})();


(function() {
    'use strict';

    angular
        .module('app.settings')
        .run(settingsRun);

    settingsRun.$inject = ['$rootScope', '$localStorage'];

    function settingsRun($rootScope, $localStorage){

        // Global Settings
        // -----------------------------------
        $rootScope.app = {
            name: 'Lins4Tech',
            description: 'LocaImob',
            year: ((new Date()).getFullYear()),
            layout: {
                isFixed: true,
                isCollapsed: false,
                isBoxed: false,
                isRTL: false,
                horizontal: false,
                isFloat: false,
                asideHover: false,
                theme: null,
                asideScrollbar: false
            },
            useFullLayout: false,
            hiddenFooter: false,
            offsidebarOpen: false,
            asideToggled: false,
            viewAnimation: 'ng-fadeInUp'
        };

        // Setup the layout mode
        $rootScope.app.layout.horizontal = ( $rootScope.$stateParams.layout === 'app-h') ;

        // Restore layout settings [*** UNCOMMENT TO ENABLE ***]
        // if( angular.isDefined($localStorage.layout) )
        //   $rootScope.app.layout = $localStorage.layout;
        // else
        //   $localStorage.layout = $rootScope.app.layout;
        //
        // $rootScope.$watch('app.layout', function () {
        //   $localStorage.layout = $rootScope.app.layout;
        // }, true);

        // Close submenu when sidebar change from collapsed to normal
        $rootScope.$watch('app.layout.isCollapsed', function(newValue) {
            if( newValue === false )
                $rootScope.$broadcast('closeSidebarMenu');
        });

    }

})();
