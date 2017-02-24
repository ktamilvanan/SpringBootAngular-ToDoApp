angular
    .module('app')
    .controller('RegisterController', ['$location', '$rootScope','userService', 
        function($location, $rootScope, userService) {
            var vm = this;

            vm.register = register;

            function register() {
                vm.dataLoading = true;
                userService.register(vm.user)
                    .then(function(response) {
                        if (response.data.id) {
                            $location.path('/login');
                        } else {
                            vm.dataLoading = false;
                        }
                    });
            }
        }
    ]);
