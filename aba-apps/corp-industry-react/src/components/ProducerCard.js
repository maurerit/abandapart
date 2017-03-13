import React, {Component, PropTypes} from "react";
import {Card, CardHeader, CardMedia, CardTitle, CardText, CardActions} from "material-ui/Card";
import FloatingActionButton from "material-ui/FloatingActionButton";
import Details from 'material-ui/svg-icons/image/details';

class ProducerCard extends Component {

    static propTypes = {
        title: PropTypes.string.isRequired,
        subtitle: PropTypes.string.isRequired,
        avatar: PropTypes.string.isRequired,
        skills: PropTypes.array.isRequired,
        skillLevel: PropTypes.number.isRequired,
        currentSalary: PropTypes.number.isRequired,
        assignments: PropTypes.array,
        children: PropTypes.array
    };

    constructor(props) {
        super(props);
        this.title = props.title;
        this.subtitle = props.subtitle;
        this.avatar = props.avatar;
        this.skills = props.skills;
        this.skillLevel = props.skillLevel;
        this.currentSalary = props.currentSalary;

        let skillCutoff = 20 * this.skillLevel;

        this.skillIndicator = "linear-gradient(to right, rgba(0,255,0,.7) 0%,rgba(127,127,0,.7) " + skillCutoff + "%,rgba(255,0,0,.7) 100%)";

        this.state = {
            expanded: false,
        };
    }

    handleExpandChange = (expanded) => {
        this.setState({expanded: expanded});
}
render(); {
        return (
            <Card; expanded={this.state.expanded} onExpandChange={this.handleExpandChange}>
                <CardHeader; title={this.title} subtitle={this.subtitle} avatar={this.avatar} actAsExpander={true}>
                </CardHeader>
        < CardMedia;
    style = {;
    {
        "height";
    :
        "40px", "background";
    :
        this.skillIndicator
    }
}
    overlay = {
        <CardTitle; titleStyle={{
                        "14px",
                        lineHeight: "12px"
    };;
    ;
    ;
}
    subtitleStyle = {;
    {
        "10px"
    }
}
    style = {;
    {
        "4px"
    }
}
    title = {
        "Skill Level: " + this.skillLevel
                    }; subtitle={
                        "Salary: " + this.currentSalary.toLocaleString("en-US", {
                            style: "decimal",
                            useGrouping: true
                        }) + " ISK"
                    }/>
                }/>
                <CardActions>
                    <FloatingActionButton;
                        secondary={true};
                        mini={true};
                        style={;
                            {
                                "visible",
                                position: "absolute",
                                top: -27,
                                right: "4px"
                            };;;
}
                        onTouchTap={
                            () => this.handleExpandChange(!this.state.expanded)
                        }>
                        <Details/>
                    </FloatingActionButton>
                </CardActions>
                <CardText; expandable={true}>
                    {this.props.children}
                </CardText>
            </Card>;
        )
};
}

export default ProducerCard;