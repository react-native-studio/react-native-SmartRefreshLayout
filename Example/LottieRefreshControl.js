/**
 * 类似华为手机的下拉刷新
 */
import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    Animated,
    Easing
} from 'react-native';
import PropTypes from 'prop-types';
import LottieView from 'lottie-react-native'
import {SmartRefreshControl, AnyHeader} from 'react-native-smartrefreshlayout';
import Icon from 'react-native-vector-icons/Ionicons';
import {SkypeIndicator} from 'react-native-indicators';

const AnimatedIcon = Animated.createAnimatedComponent(Icon);
export default class LottieRefreshControl extends Component {
    _onRefresh = () => {
        let {onRefresh} = this.props;
        onRefresh && onRefresh();
    }
    finishRefresh=(params)=>{
        this._refreshc && this._refreshc.finishRefresh(params)
    }
    render() {
        return (
            <SmartRefreshControl
                style={{flex:1}}
                ref={ref => this._refreshc = ref}
                children={this.props.children}
                onRefresh={this._onRefresh}
                headerHeight={100}
                HeaderComponent={
                    <AnyHeader style={{height:100,justifyContent:'center',alignItems:'center'}}>
                        <LottieView style={{width:100,height:100}} hardwareAccelerationAndroid  autoPlay source={require('./loop.json')} speed={1.5} />
                    </AnyHeader>
                }
            />
        )
    }
}