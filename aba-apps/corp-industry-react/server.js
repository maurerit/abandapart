const express = require('express');

const app = express();

app.set('port', (process.env.PORT || 3001));

app.get('/producers', (req, res) => {
    res.json(
        [{
            title: "Ele'ele Kelmalu",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/93451896_64.jpg",
            skills: [],
            skillLevel: 5,
            currentSalary: 400000000
        },{
            title: "Aaron Ivy",
            subtitle: "Producer",
            avatar: "https://image.eveonline.com/character/95363598_64.jpg",
            skills: [],
            skillLevel: 4,
            currentSalary: 400000000
        },{
            title: "Anyang Haseo",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/92948959_64.jpg",
            skills: [],
            skillLevel: 3,
            currentSalary: 400000000
        },{
            title: "bad girl55",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/93379497_64.jpg",
            skills: [],
            skillLevel: 2,
            currentSalary: 400000000
        },{
            title: "Clive Wallis",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/94262106_64.jpg",
            skills: [],
            skillLevel: 1,
            currentSalary: 400000000
        },{
            title: "Clyde Talvanen",
            subtitle: "Slacker",
            avatar: "https://image.eveonline.com/character/94218935_64.jpg",
            skills: [],
            skillLevel: 0,
            currentSalary: 400000000
        },{
            title: "Galen Oak",
            subtitle: "Director",
            avatar: "https://image.eveonline.com/character/94169110_64.jpg",
            skills: [],
            skillLevel: 3,
            currentSalary: 400000000
        },{
            title: "Ele'ele Kelmalu",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/93451896_64.jpg",
            skills: [],
            skillLevel: 5,
            currentSalary: 400000000
        },{
            title: "Aaron Ivy",
            subtitle: "Producer",
            avatar: "https://image.eveonline.com/character/95363598_64.jpg",
            skills: [],
            skillLevel: 4,
            currentSalary: 400000000
        },{
            title: "Anyang Haseo",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/92948959_64.jpg",
            skills: [],
            skillLevel: 3,
            currentSalary: 400000000
        },{
            title: "bad girl55",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/93379497_64.jpg",
            skills: [],
            skillLevel: 0,
            currentSalary: 400000000
        },{
            title: "Clive Wallis",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/94262106_64.jpg",
            skills: [],
            skillLevel: 1,
            currentSalary: 400000000
        },{
            title: "Clyde Talvanen",
            subtitle: "Slacker",
            avatar: "https://image.eveonline.com/character/94218935_64.jpg",
            skills: [],
            skillLevel: 0,
            currentSalary: 400000000
        },{
            title: "Galen Oak",
            subtitle: "Director",
            avatar: "https://image.eveonline.com/character/94169110_64.jpg",
            skills: [],
            skillLevel: 3,
            currentSalary: 400000000
        },{
            title: "Ele'ele Kelmalu",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/93451896_64.jpg",
            skills: [],
            skillLevel: 5,
            currentSalary: 400000000
        },{
            title: "Aaron Ivy",
            subtitle: "Producer",
            avatar: "https://image.eveonline.com/character/95363598_64.jpg",
            skills: [],
            skillLevel: 4,
            currentSalary: 400000000
        },{
            title: "Anyang Haseo",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/92948959_64.jpg",
            skills: [],
            skillLevel: 3,
            currentSalary: 400000000
        },{
            title: "bad girl55",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/93379497_64.jpg",
            skills: [],
            skillLevel: 2,
            currentSalary: 400000000
        },{
            title: "Clive Wallis",
            subtitle: "Senior Producer",
            avatar: "https://image.eveonline.com/character/94262106_64.jpg",
            skills: [],
            skillLevel: 1,
            currentSalary: 400000000
        },{
            title: "Clyde Talvanen",
            subtitle: "Slacker",
            avatar: "https://image.eveonline.com/character/94218935_64.jpg",
            skills: [],
            skillLevel: 4,
            currentSalary: 400000000
        },{
            title: "Galen Oak",
            subtitle: "Director",
            avatar: "https://image.eveonline.com/character/94169110_64.jpg",
            skills: [],
            skillLevel: 3,
            currentSalary: 400000000
        }]
    );
});

app.listen(app.get('port'), () => {
    console.log(`Find the server at: http://localhost:${app.get('port')}/`); // eslint-disable-line no-console
});