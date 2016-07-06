class WalletController {
  constructor() {
    this.name = 'wallet';
	this.someData = {
		labels: [
        'Apr', 
        'May', 
        'Jun'
      ],
      datasets: [
        {
          data: [1, 7, 15, 19, 31, 40]
        },
        {
          data: [6, 12, 18, 24, 30, 36]
        }
      ]
	};

	this.someOptions = {
		segementStrokeWidth: 20,
		segmentStrokeColor: '#000'
	};
  }
}

export default WalletController;
