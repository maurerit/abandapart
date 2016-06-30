class NavigationController {
	constructor() {
		this.name = 'navigation';

		//TODO: I think I want this to be gathered from the backend but I could be an idiot?
		this.navItems = [
			{
				"link": "/home",
				"class": "active",
				"name": "Home"
			},
			{
				"link": "/about",
				"class": "none",
				"name": "About"
			}
		];

		this.activeItem = this.navItems[0];
	}

	click ( navItem ) {
		this.activeItem = navItem;
	}
}

export default NavigationController;
