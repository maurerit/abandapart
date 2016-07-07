class SalesandexpendituresController {
  constructor($scope) {
    this.name = 'salesandexpenditures';
    $scope.labels = ['Jita', 'Amarr', 'Rens', 'Dodixie'];
    $scope.series = ['Sales', 'Expenses'];
    $scope.data = [
    	[15000000000, 8000000000, 600000000, 400000000],
    	[12000000000, 1000000000, 100000000, 200000000],
    ];
  }
}

SalesandexpendituresController.$inject = ['$scope'];

export default SalesandexpendituresController;
