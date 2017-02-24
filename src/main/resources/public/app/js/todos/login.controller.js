angular
    .module('app')
    .controller('LoginController', ['$location', '$rootScope','userService', 
        function($location, $rootScope, userService) {
            var vm = this;

            vm.login = login;

            function login() {
                vm.dataLoading = true;
                userService.login(vm.username,vm.password)
                    .then(function(response) {
                        if (response.data.id) {
                            $location.path('/');
                        } else {
                            vm.dataLoading = false;
                        }
                    });
            }
        }
    ]);
