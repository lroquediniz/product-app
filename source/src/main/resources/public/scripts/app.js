var app = angular.module('productsapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/search.html',
        controller: 'SeachProductController'
    }).when('/create', {
        templateUrl: 'views/detail.html',
        controller: 'NewProductController'
    }).when('/edit/:id', {
        templateUrl: 'views/detail.html',
        controller: 'EditProductController'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('SeachProductController', function ($scope, $http) {
    $http.get('/rest/products').success(function (data) {
        $scope.products = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

});

app.controller('NewProductController', function ($scope, $http, $location) {
    
	$scope.save = function () {
        console.log($scope.product);
        $http.post('/rest/products', $scope.product).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        });
    }
	
	$scope.cancel = function () {
		 $location.path('/');
	}
});


app.controller('EditProductController', function ($scope, $routeParams, $http, $location) {
	
	$http.get('/rest/products/'+ $routeParams.id).success(function (data) {
        $scope.product = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
    
	
	$scope.cancel = function () {
		 $location.path('/');
	}
	
	$scope.remove = function () {
		$http.delete('/rest/products/' + $scope.product.id).success(function (data) {
			$location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        });
	}
	
	
	$scope.update = function () {
		$http.put('/rest/products/' + $scope.product.id, $scope.product).success(function (data) {
			$location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        });
	}

    //TODO - UPDATE AND DELETE.
});