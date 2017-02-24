(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                controller: 'ToDosController',
                templateUrl: 'views/todos.view.html',
                controllerAs: 'vm'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'views/login.view.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'views/register.view.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/login' });
    }

    run.$inject = ['$rootScope', '$location', '$cookies', '$http','userService'];
    function run($rootScope, $location, $cookies, $http,userService) {
       

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            userService.getUser().then(function(data)
            {
                if(data.data.error)
                {
                  $rootScope.currentUser = undefined;
                }
                if(data.data.id)
                {
                  $rootScope.currentUser = data.data;
                }

                var loggedIn = $rootScope.currentUser;
                if (restrictedPage && !loggedIn) {
                    $location.path('/login');
                }
                else
                  $location.path("/");
            },function(error){
              console.log(error);
              $location.path('/login');
            }).catch(function(reason) {
               console.log("Error during login");
                $location.path('/login');
            });
            
        });
    }

})();