
/*#######################################################################
  Normally like to break AngularJS apps into the following folder structure
  at a minimum:

  /app
      /controllers      
      /directives
      /services
      /views

  #######################################################################*/

var mainApp = angular.module('mainApp', ['ngRoute', 'customerModule']);

//This configures the routes and associates each route with a view and a controller
mainApp.config(function ($routeProvider) {
    $routeProvider
        .when('/customers',
            {
                controller: 'customersController',
                templateUrl: 'app/customer/view/customers.html'
            })
        //Define a route that has a route parameter in it (:customerID)
        .when('/customerorders/:customerID',
            {
                controller: 'customerOrdersController',
                templateUrl: 'app/customer/view/customerOrders.html'
            })
        //Define a route that has a route parameter in it (:customerID)
        .when('/orders',
            {
                controller: 'ordersController',
                templateUrl: 'app/customer/view/orders.html'
            })
        .otherwise({ redirectTo: '/customers' });
});



