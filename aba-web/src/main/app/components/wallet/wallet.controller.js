class WalletController {
  constructor($scope) {
    this.name = 'wallet';
    $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
    $scope.series = ['Series A', 'Series B'];
    $scope.data = [
      [65, 59, 80, 81, 56, 55, 40],
      [28, 48, 40, 19, 86, 27, 90]
    ];
  }
}

WalletController.$inject = ['$scope'];

export default WalletController;
