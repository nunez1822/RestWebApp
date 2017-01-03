var restWebApp = angular.module('restWebApp', []);

restWebApp.controller('InventoryController', function InventoryController($scope, $http) {
	var vm = $scope;
	
	vm.getAllActors = function() {
	    $http({
	        method : "GET",
	        headers: {
	            'Authorization':'kevin'
	        },
	        url : vm.url,
	    }).then(function mySucces(response) {
	        vm.data = response.data;
	    }, function myError(response) {
	        vm.error = response.statusText;
	    });
	}
	
	vm.resetData = function() {
		vm.data = "";
	}
});