(function() {
    var app = angular.module('myApp', []);

    app.controller('loginController' , function ($scope, $http) {

        $scope.user = {};
        var username;
        var password;

        //    Our GET request function
        $scope.getRequest = function () {
            console.log("I've been pressed!");  
            $http.get("http://urlforapi.com/get?name=Elliot")
                .then(function successCallback(response){
                    $scope.response = response;
                }, function errorCallback(response){
                    console.log("Unable to perform get request");
                });
            };

        //    Our POST request function
        $scope.postRequest = function () {
            console.log("Making Post Request")
            console.log($scope.user.username)
            $http({
                method: 'POST',
                url: 'http://localhost:8080/api/v1/user/login',
                data: {"userName": $scope.user.username, "password": $scope.user.password},
                headers: {'Content-Type': 'application/json'}
            });
            
        }
    });

    app.controller('registerController' , function ($scope, $http) {

        $scope.user = {};

        //    Our GET request function
        $scope.getRequest = function () {
            console.log("I've been pressed!");  
            $http.get("http://urlforapi.com/get?name=Elliot")
                .then(function successCallback(response){
                    $scope.response = response;
                }, function errorCallback(response){
                    console.log("Unable to perform get request");
                });
            };

        //    Our POST request function
        $scope.postRequest = function () {
            console.log("Making Post Request")
            console.log($scope.user.username)
            $http({
                method: 'POST',
                url: 'http://localhost:8080/api/v1/user',
                data: {"userName": $scope.user.username, "password": $scope.user.password, "gender": $scope.user.gender},
                headers: {'Content-Type': 'application/json'}
            });
            
        }
    });

    
  })();