import PropTypes from 'prop-types';
import React, { Component } from 'react';
import {
    requireNativeComponent
} from 'react-native';
import { ViewPropTypes } from './Util';

const RCTAnyHeader = requireNativeComponent('RCTAnyHeader', RCTAnyHeader);

class AnyHeader extends Component {

    render() {
        return (
            <RCTAnyHeader
                {...this.props}
            />

        )
    }
}

AnyHeader.propTypes = {
    primaryColor:PropTypes.string,
    ...ViewPropTypes,
}
export default AnyHeader;