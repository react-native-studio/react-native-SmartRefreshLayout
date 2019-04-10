import react, { Component } from "react"
import {ViewProps} from "react-native";
interface SmartRefreshLayoutProps extends ViewProps{
    onRefresh?:()=>void,
    onHeaderPulling?:(p: RefreshEvent)=>void,
    onHeaderReleasing?:(p: RefreshEvent)=>void,
    onHeaderMoving?:(p: RefreshEvent)=>void,//向外提供的接口
    onPullDownToRefresh?:()=>void,
    onReleaseToRefresh?:()=>void,
    onHeaderReleased?:()=>void,
    enableRefresh?:boolean,//是否启用下拉刷新功能
    renderHeader?:()=>React.ReactElement | React.ReactElement,
    headerHeight?:number,
    overScrollBounce?:boolean,//是否使用越界回弹
    overScrollDrag?:boolean,//是否使用越界拖动，类似IOS样式
    pureScroll?:boolean,//是否使用纯滚动模式
    dragRate?:number,// 显示下拉高度/手指真实下拉高度=阻尼效果
    maxDragRate?:number,//最大显示下拉高度/Header标准高度
    primaryColor?:string,
    autoRefresh?: AutoRefresh,//是否启动自动刷新
}
type RefreshEvent = {
    nativeEvent: RefreshNativeEvent
}
type RefreshNativeEvent = { percent: number, offset:number, headerHeight: number}
type AutoRefresh = { refresh?:boolean,time?:number }
type FinishRefreshParams = { delayed?: number, success?:boolean}
export class SmartRefreshLayout extends Component<SmartRefreshLayoutProps>{
    finishRefresh:(params?:FinishRefreshParams)=>void
}

interface ClassicsHeaderProps extends ViewProps{
    primaryColor?: string,
    accentColor?: string,
}
export class ClassicsHeader extends Component<ClassicsHeaderProps>{}

interface DefaultHeaderProps extends ClassicsHeaderProps{}
export class DefaultHeader extends Component<DefaultHeaderProps>{}

interface StoreHouseHeaderProps extends ViewProps{
    textColor?: string,
    text?: string,//暂时只支持英文
    fontSize?: number,
    lineWidth?: number,
    dropHeight?: number,
}
export class StoreHouseHeader extends Component<StoreHouseHeaderProps>{}

interface AnyHeaderProps extends ViewProps{}
export class AnyHeader extends Component<AnyHeaderProps>{}

