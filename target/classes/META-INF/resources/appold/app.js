var app = angular.module('myApp', ['ngRoute', 'ngAnimate', 'toaster']);

app.config(['$routeProvider',
  function ($routeProvider) {
        $routeProvider.
        when('/login', {
            title: 'Login',
            templateUrl: 'admin/login.html',
            controller: 'authCtrl'
        })
            .when('/logout', {
                title: 'Logout',
                templateUrl: 'admin/login.html',
                controller: 'logoutCtrl'
            })
            .when('/signup', {
                title: 'Signup',
                templateUrl: 'admin/register.html',
                controller: 'authCtrl'
            })
            .when('/dashboard', {
                title: 'Dashboard',
                templateUrl: 'admin/indexmain.html',
                controller: 'authCtrl'
            })
            .when('/', {
                title: 'Login',
                templateUrl: 'admin/login.html',
                controller: 'authCtrl',
                role: '0'
            })
            .otherwise({
                redirectTo: '/login'
            });
  }])
    .run(function ($rootScope, $location, Data) {
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            $rootScope.authenticated = false;
            Data.get('session').then(function (results) {
                if (results.uid) {
                    $rootScope.authenticated = true;
                    $rootScope.uid = results.uid;
                    $rootScope.name = results.name;
                    $rootScope.email = results.email;
                } else {
                    var nextUrl = next.$$route.originalPath;
                    if (nextUrl == '/signup' || nextUrl == '/login') {

                    } else {
                        $location.path("/login");
                    }
                }
            });
        });
    });