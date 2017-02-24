angular
    .module('app')
    .controller('ToDosController', ['todosService',
        function(todosService) {
            var vm = this;
            vm.todosarray = [];
            vm.todos = todos;
            vm.createTodo = createTodo;
            vm.done = false;

            function todos() {
                vm.dataLoading = true;
                todosService.todos()
                    .then(function(data) {
                        vm.todos = data.data;
                    });
            }

            function createTodo()
            {
                todosService.createTodo(vm.text,vm.done)
                    .then(function(data) {
                        vm.todosarray.push(data.data);
                    });
            }

            todos();

        }
    ]);
