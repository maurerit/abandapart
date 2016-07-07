class ExpendituresgraphController {
  constructor($scope) {
    this.name = 'expendituresgraph';
    $scope.labels = ["Jita", "Amarr", "Rens", "Dodixie"];
    $scope.data = [12000000000,1000000000,100000000,200000000];
  }
}

ExpendituresgraphController.$inject = ['$scope'];

export default ExpendituresgraphController;
