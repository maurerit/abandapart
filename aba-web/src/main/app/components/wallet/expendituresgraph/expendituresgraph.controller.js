class ExpendituresgraphController {
  constructor($scope) {
    this.name = 'expendituresgraph';
    $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
    $scope.data = [65, 59, 80, 81, 56, 55, 40];
  }
}

ExpendituresgraphController.$inject = ['$scope'];

export default ExpendituresgraphController;
