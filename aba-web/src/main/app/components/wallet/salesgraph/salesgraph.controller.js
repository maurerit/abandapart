class SalesgraphController {
  constructor($scope) {
    this.name = 'salesgraph';
    $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
    $scope.data = [28, 48, 40, 19, 86, 27, 90];
  }
}

SalesgraphController.$inject = ['$scope'];

export default SalesgraphController;
