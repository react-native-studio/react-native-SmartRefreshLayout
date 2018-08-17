import React, {Component} from 'react';
import {requireNativeComponent} from 'react-native';
import {ViewPropTypes} from './Util';
import PropTypes from 'prop-types';

const RCTClassicsHeader = requireNativeComponent('RCTClassicsHeader', RCTClassicsHeader);

export default class ClassicsHeader extends Component {
    static propTypes = {
        primaryColor: PropTypes.string,
        accentColor: PropTypes.string,
        ...ViewPropTypes,
    }

    render() {
        return (<RCTClassicsHeader {...this.props}/>)
    }
}