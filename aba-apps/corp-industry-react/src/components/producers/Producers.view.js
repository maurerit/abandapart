import React, {Component} from 'react';
import { connect } from 'react-redux';
import ProducerCard from '../ProducerCard';

import {loadProducersIfNecessary, refreshProducers} from './Producers.actions';

import {GridList, GridTile} from 'material-ui/GridList';
import FloatingActionButton from 'material-ui/FloatingActionButton';
import CircularProgress from 'material-ui/CircularProgress';
import Paper from 'material-ui/Paper';

import KeyBoardUpArrow from 'material-ui/svg-icons/hardware/keyboard-arrow-up';
import Refresh from 'material-ui/svg-icons/navigation/refresh';

const styles = {
    root: {
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'space-around',
    },
    gridList: {
        overflowY: 'auto'
    },
};

class ProducersView extends Component {
    componentDidMount ( ) {
        const {dispatch} = this.props;
        dispatch(loadProducersIfNecessary());
    }

    refreshProducers = ( ) => {
        const {dispatch} = this.props;
        dispatch(refreshProducers());
        dispatch(loadProducersIfNecessary());
    };

    render ( ) {
        return (
            <div style={styles.root}>
                {
                    this.props.isFetching === true
                        ?
                        <CircularProgress size={120} thickness={10}/>
                        :
                        <div>
                            {/* TODO: Seems like it'll be common enough for a component of it's own */}
                            <Paper style={{height: "100px", margin: 20, textAlign: 'center',}} zDepth={1}>
                                <span>Current Producer's</span><br/>
                                <FloatingActionButton style={{position: "absolute", right: 40}} onTouchTap={this.refreshProducers}>
                                    <Refresh />
                                </FloatingActionButton>
                            </Paper>
                            <GridList
                                cellHeight="auto"
                                style={styles.gridList}
                                cols={2}>
                                {this.props.producers.map(function (producer, index) {
                                        producer.children = [];
                                        producer.children.push(<KeyBoardUpArrow key={index}/>);
                                        return (
                                            <GridTile key={index}>
                                                <ProducerCard key={index} {...producer} />
                                            </GridTile>
                                        )
                                    }
                                )}
                            </GridList>
                        </div>
                }
            </div>
        )
    }
}

function mapStateToProps ( state ) {
    return {
        isFetching: state.isFetching,
        producers: state.producers,
        receivedAt: state.receivedAt
    }
}

export default connect(mapStateToProps)(ProducersView);