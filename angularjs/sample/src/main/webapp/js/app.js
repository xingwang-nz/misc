angular.module('sampleApp',['personApp']);

var personApp = angular.module('personApp',[]);

personApp.controller("personController", function($scope, $http) {
	$scope.welcome = "Welcome to AngularJS!";
	$scope.selectedPerson = null;
	$scope.selectedGenre = null;
	$scope.people = [ {
		id : 1,
		name : 'Leon',
		music : [ 'Rock', 'Metal', 'Dubstep', 'Electro' ],
		live : true
	}, {
		id : 2,
		name : 'Chris',
		music : [ 'Indie', 'Drumstep', 'Dubstep', 'Electro' ],
		live : true
	}, {
		id : 3,
		name : 'Harry',
		music : [ 'Rock', 'Metal', 'Thrash Metal', 'Heavy Metal' ],
		live : false
	}, {
		id : 4,
		name : 'Allyce',
		music : [ 'Pop', 'RnB', 'Hip Hop' ],
		live : true
	} ];

	$scope.newPerson = null;
	$scope.addNew = function() {
		if ($scope.newPerson != null && $scope.newPerson != "") {
			$scope.people.push({
				id : $scope.people.length + 1,
				name : $scope.newPerson,
				live : true,
				music : []
			});
		}
	}
});

personApp.controller("userController", function($scope, $http) {
	$scope.userName = "abc";
});