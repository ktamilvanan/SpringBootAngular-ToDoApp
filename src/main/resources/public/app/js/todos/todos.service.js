angular.module('app').service("todosService", ["$http", "$q", function($http, $q) {
    

    this.todos = function()
    {
    	var deferred = $q.defer();
        $http({
            url: "/security/account",
            method: "GET"
        }).then(function(data) {
            deferred.resolve(data);
        }, function(reason) {
            deferred.error(reason);
        });
        return deferred.promise;
    }

    this.createTodo = function(text,done) {
        var deferred = $q.defer();
        $http({
            url: "/todos",
            method: "POST",
            data: $.param({
                text :text,
                done : done
            }),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function(data) {
            deferred.resolve(data);
        }, function(reason) {
            deferred.error(reason);
        });
        return deferred.promise;
    }
}]);
