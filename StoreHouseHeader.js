import React, {Component} from 'react';
import {requireNativeComponent} from 'react-native';
import {ViewPropTypes} from "./Util";
import PropTypes from 'prop-types';

const RCTStoreHouseHeader = requireNativeComponent('RCTStoreHouseHeader', RCTStoreHouseHeader);

export default class StoreHouseHeader extends Component {
    static propTypes = {
        textColor: PropTypes.string,
        text: PropTypes.string,//暂时只支持英文
        fontSize: PropTypes.number,
        lineWidth: PropTypes.number,
        dropHeight: PropTypes.number,
        ...ViewPropTypes,
    }

    render() {
        return (<RCTStoreHouseHeader {...this.props}/>)
    }
}