import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    requireNativeComponent,
    findNodeHandle,
    UIManager,
} from 'react-native';
import {ViewPropTypes,PropTypes} from './Util'
const RCTClassicsHeader = requireNativeComponent('RCTClassicsHeader', RCTClassicsHeader);

class ClassicsHeader extends Component {

    render() {
        return (
            <RCTClassicsHeader
                ref="refreshLayout"
                {...this.props}
            />

        )
    }
}

ClassicsHeader.propTypes = {
    primaryColor:PropTypes.string,
    accentColor:PropTypes.string,
    ...ViewPropTypes,
}
export default ClassicsHeader;