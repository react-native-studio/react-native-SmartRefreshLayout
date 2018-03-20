import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    requireNativeComponent,
    findNodeHandle,
    UIManager,
} from 'react-native';
import ClassicsHeader from "./ClassicsHeader";
import {ViewPropTypes,PropTypes} from './Util'
import DefaultHeader from "./DefaultHeader";

const SmartRefreshLayout = requireNativeComponent('SmartRefreshLayout', SmartRefreshControl);

class SmartRefreshControl extends Component {


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
        UIManager.dispatchViewManagerCommand(this.findNode(), UIManager.SmartRefreshLayout.Commands[commandName], params);
    }
    findNode=()=>{

        return findNodeHandle(this.refs.refreshLayout);
    }
    renderHeader=()=>{
        const {HeaderComponent}=this.props;
        if(HeaderComponent){
            return React.cloneElement(HeaderComponent,{
                key:'header'
            });
        }
        return <DefaultHeader/>
    }
    _onSmartRefresh=()=>{
        this.props.onRefresh && this.props.onRefresh();
    }
    //TODO://还未实现
    renderFooter=()=>{
        return null;
    }
    render() {
        const nativeProps ={...this.props,...{
            onSmartRefresh:this._onSmartRefresh,
        }}
        return (
            <SmartRefreshLayout
                ref="refreshLayout"
                {...nativeProps}
            >
                {this.renderHeader()}
                {React.cloneElement(
                    this.props.children,
                    {
                        key:'content'
                    }
                )}
            </SmartRefreshLayout>

        )
    }
}

SmartRefreshControl.propTypes = {
    onRefresh: PropTypes.func,
    onLoadMore: PropTypes.func,
    enableRefresh: PropTypes.bool,//是否启用下拉刷新功能
    HeaderComponent:PropTypes.object,
    autoRefresh:PropTypes.shape({
        refresh:PropTypes.bool,
        time:PropTypes.number,
    }),//是否启动自动刷新
    ...ViewPropTypes,
}
export default SmartRefreshControl;