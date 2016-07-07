class SalesgraphController {
  constructor($scope) {
    this.name = 'salesgraph';
    $scope.labels = ["Jita", "Amarr", "Rens", "Dodixie"];
    $scope.data = [15000000000,8000000000,600000000,400000000];
  }
}

SalesgraphController.$inject = ['$scope'];

export default SalesgraphController;
