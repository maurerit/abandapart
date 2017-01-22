import React, {Component} from 'react';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';


class ProducerCard extends Component {
    constructor ( props ) {
        super ( props );
        this.title = props.title;
        this.subtitle = props.subtitle;
        this.avatar = props.avatar;
        this.skill = props.skill;
    }

    render ( ) {
        return (
            <Card>
                <CardHeader title={this.title} subtitle={this.subtitle} avatar={this.avatar} />
                {/*<CardMedia*/}
            </Card>
        )
    }
}