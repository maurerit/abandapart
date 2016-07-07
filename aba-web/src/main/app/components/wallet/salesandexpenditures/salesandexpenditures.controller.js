class SalesandexpendituresController {
  constructor($scope) {
    this.name = 'salesandexpenditures';
    $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
    $scope.series = ['Series A', 'Series B'];
    $scope.data = [
      [65, 59, 80, 81, 56, 55, 40],
      [28, 48, 40, 19, 86, 27, 90]
    ];
  }
}

SalesandexpendituresController.$inject = ['$scope'];

export default SalesandexpendituresController;
