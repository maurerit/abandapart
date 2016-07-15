class WalletController {
  constructor($scope) {
    this.name = 'wallet';
    $scope.salesPieLabels = ["Jita", "Amarr", "Rens", "Dodixie"];
    $scope.salesPieData = [15000000000,8000000000,600000000,400000000];

    $scope.expensesPieLabels = ["Jita", "Amarr", "Rens", "Dodixie"];
    $scope.expensesPieData = [10000000000,7000000000,300000000,800000000];

    $scope.barLabels = ['Jita', 'Amarr', 'Rens', 'Dodixie'];
    $scope.barSeries = ['Sales', 'Expenses'];
    $scope.barData = [
      [15000000000, 8000000000, 600000000, 400000000],
      [12000000000, 1000000000, 100000000, 200000000],
    ];
  }
}

WalletController.$inject = ["$scope"];

export default WalletController;
