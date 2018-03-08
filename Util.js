import {PropTypes as ReactPropTypes} from 'react';
import * as PTPropTypes from 'prop-types';
import {
    View,
    BackHandler,
    ViewPropTypes as RNViewPropTypes,
    BackAndroid as DeprecatedBackAndroid,
} from 'react-native';

export const ViewPropTypes = RNViewPropTypes || View.propTypes;
export const BackAndroid = BackHandler || DeprecatedBackAndroid;
export const PropTypes=PTPropTypes || PropTypes;

