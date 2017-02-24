angular.module('app').service("userService", ["$http", "$q", function($http, $q) {
    this.register = function(user) {
        var deferred = $q.defer();
        $http({
            url: "/security/register",
            method: "POST",
            data: $.param({
                firstName : user.firstName,
                lastName : user.lastName,
                email : user.email,
                password : user.password
            }),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function(data) {
            deferred.resolve(data);
        }, function(reason) {
            deferred.error(reason);
        });
        return deferred.promise;
    }

    this.getUser = function()
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

    this.login = function(username,password) {
        var deferred = $q.defer();
        $http({
            url: "/authenticate",
            method: "POST",
            data: $.param({
                username :username,
                password : password
            }),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function(data) {
            deferred.resolve(data);
        }, function(reason) {
            deferred.reject(reason);
        });
        return deferred.promise;
    }
}]);
