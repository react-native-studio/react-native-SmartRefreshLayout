import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    requireNativeComponent,
    ViewPropTypes,
    findNodeHandle,
    UIManager,
} from 'react-native';
import PropTypes from 'prop-types';

const RCTStoreHouseHeader = requireNativeComponent('RCTStoreHouseHeader', RCTStoreHouseHeader);

class StoreHouseHeader extends Component {

    render() {
        return (
            <RCTStoreHouseHeader
                ref="refreshLayout"
                {...this.props}
            />

        )
    }
}

StoreHouseHeader.propTypes = {
    textColor:PropTypes.string,
    text:PropTypes.string,//暂时只支持英文
    fontSize:PropTypes.number,
    lineWidth:PropTypes.number,
    dropHeight:PropTypes.number,
    ...ViewPropTypes,
}
export default StoreHouseHeader;