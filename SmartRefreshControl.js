import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    requireNativeComponent,
    findNodeHandle,
    UIManager,
    NativeModules,
    Platform,
    PanResponder,
} from 'react-native';
import {ViewPropTypes} from './Util'
import DefaultHeader from "./DefaultHeader";
import PropTypes from 'prop-types';
import processColor from 'react-native/Libraries/StyleSheet/processColor';
import deprecatedPropType from 'react-native/Libraries/Utilities/deprecatedPropType'

const SPModule =Platform.OS === 'android' ? NativeModules.SpinnerStyleModule : {};

const SmartRefreshLayout = requireNativeComponent('SmartRefreshLayout', SmartRefreshControl);

class SmartRefreshControl extends Component {
    static constants = {
        "TRANSLATE":SPModule.translate,
        "SCALE":SPModule.scale,
        "FIX_BEHIND":SPModule.fixBehind,
        "FIX_FRONT":SPModule.fixFront,
        "MATCH_LAYOUT":SPModule.matchLayout,
    }


    /**
     * 参数格式为{delayed:number,success:bool}
     * delayed:延迟刷新
     * success:是否刷新成功
     * @param params
     */
    finishRefresh=({delayed=-1,success=true}={delayed:-1,success:true})=>{
        this.dispatchCommand('finishRefresh',[delayed,success])
    }
    dispatchCommand=(commandName, params)=>{
        UIManager.dispatchViewManagerCommand(this.findNode(),
            (UIManager.getViewManagerConfig ? UIManager.getViewManagerConfig("SmartRefreshLayout"): UIManager.SmartRefreshLayout).Commands[commandName],
            params);
    }
    findNode=()=>{

        return findNodeHandle(this.refs.refreshLayout);
    }
    componentWillMount() {
        this._panResponder = PanResponder.create({
            onMoveShouldSetPanResponderCapture: (evt, gestureState) => {
                if(this.shiftPercent >= 0.039 || this.footerShiftPercent >= 0.068){//满足条件捕获事件
                    return true
                }
                return false;
            }
        });
    }

    shiftPercent = 0;//header位移百分比，默认为0

    footerShiftPercent = 0; // footer位移百分比
    /**
     * 渲染Header
     * @return {*}
     */
    renderHeader=()=>{
        const {HeaderComponent,renderHeader}=this.props;
        if(renderHeader){
            return React.isValidElement(renderHeader)?renderHeader:renderHeader();
        }
        if(HeaderComponent){
            return HeaderComponent;
        }
        return <DefaultHeader/>
    }
    /**
     * 刷新时触发
     * @private
     */
    _onSmartRefresh=()=>{
        let {onRefresh} = this.props;
        onRefresh && onRefresh();
    }
    /**
     * 下拉过程
     * @param event
     * @private
     */
    _onHeaderPulling=(event)=>{
        this.shiftPercent = event.nativeEvent.percent;
        let {onHeaderPulling,onHeaderMoving} = this.props;
        onHeaderMoving && onHeaderMoving(event);
        onHeaderPulling && onHeaderPulling(event);
    }
    /**
     * 释放过程
     * @param event
     * @private
     */
    _onHeaderReleasing=(event)=>{
        this.shiftPercent = event.nativeEvent.percent;
        let {onHeaderReleasing,onHeaderMoving} = this.props;
        onHeaderMoving && onHeaderMoving(event);
        onHeaderReleasing && onHeaderReleasing(event);
    }
    /**
     * 底部位移过程
     * @param event
     * @private
     */
    _onFooterMoving=(event)=>{
        this.footerShiftPercent = event.nativeEvent.percent;
    }

    render() {
        const nativeProps ={...this.props,...{
                onSmartRefresh:this._onSmartRefresh,
                onHeaderPulling:this._onHeaderPulling,
                onHeaderReleasing:this._onHeaderReleasing,
                onFooterMoving:this._onFooterMoving,
                primaryColor: processColor(this.props.primaryColor),
            }}
        return (
            <SmartRefreshLayout
                ref="refreshLayout"
                {...nativeProps}
                {...this._panResponder.panHandlers}
            >
                {this.renderHeader()}
                {this.props.children}
            </SmartRefreshLayout>

        )
    }
}

SmartRefreshControl.propTypes = {
    onRefresh: PropTypes.func,
    onLoadMore: PropTypes.func,
    onHeaderPulling:PropTypes.func,
    onHeaderReleasing:PropTypes.func,
    onHeaderMoving:PropTypes.func,//向外提供的接口
    onPullDownToRefresh:PropTypes.func,
    onReleaseToRefresh:PropTypes.func,
    onHeaderReleased:PropTypes.func,
    enableRefresh: PropTypes.bool,//是否启用下拉刷新功能
    HeaderComponent:deprecatedPropType(PropTypes.object,'Use the `renderHeader` prop instead.'),
    renderHeader:PropTypes.oneOfType([
        PropTypes.func,
        PropTypes.element,
    ]),
    headerHeight:PropTypes.number,
    overScrollBounce:PropTypes.bool,//是否使用越界回弹
    overScrollDrag:PropTypes.bool,//是否使用越界拖动，类似IOS样式
    pureScroll:PropTypes.bool,//是否使用纯滚动模式
    dragRate:PropTypes.number,// 显示下拉高度/手指真实下拉高度=阻尼效果
    maxDragRate:PropTypes.number,//最大显示下拉高度/Header标准高度
    primaryColor:PropTypes.string,
    autoRefresh:PropTypes.shape({
        refresh:PropTypes.bool,
        time:PropTypes.number,
    }),//是否启动自动刷新
    ...ViewPropTypes,
}

SmartRefreshControl.defaultProps={
    overScrollBounce:false
}
export default SmartRefreshControl;