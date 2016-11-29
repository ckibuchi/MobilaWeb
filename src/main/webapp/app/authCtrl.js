app.controller('authCtrl', function ($scope, $rootScope, $routeParams, $location, $http, Data,$window) {
    //initially set those objects to null to avoid undefined error
    $scope.login = {};
    $scope.signup = {};
    $scope.doLogin = function (user) {
        Data.post('users/login', {
            user: user
        }).then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
               // $location.path('dashboard');
               // $window.location.href = '/Mobila/admin/index.html';
                document.location.href='/Mobila/admin/index.html';
                //scope.$apply(function() { $location.path("/Mobila/admin/indexmain.html"); });
            }
        });
    };
    $scope.signup = {email:'',password:'',name:'',phone:'',address:''};
    $scope.signUp = function (user) {
        Data.post('users/saveorupdate', {
            user: user
        }).then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
                $location.path('dashboard');
            }
        });
    };
    $scope.logout = function () {
        Data.get('logout').then(function (results) {
            Data.toast(results);
            $location.path('login');
        });
    }
});