import {
    View,
    BackHandler,
    ViewPropTypes as RNViewPropTypes,
    BackAndroid as DeprecatedBackAndroid,
} from 'react-native';

export const ViewPropTypes = RNViewPropTypes || View.propTypes;
export const BackAndroid = BackHandler || DeprecatedBackAndroid;
