# React Native SmartRefreshLayout[![npm version](https://badge.fury.io/js/react-native-smartrefreshlayout.svg)](https://badge.fury.io/js/react-native-smartrefreshlayout)

>React-Native-SmartRefreshLayout是基于[Android SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout) 开发的插件，可提供类似ios的弹性刷新,该插件可完全使用React Native进行自定义

>IOS自定义下拉刷新组件见[React-Native-MJRefresh](https://github.com/react-native-studio/react-native-MJRefresh)

## 安装
#### 第一步
工程目录下运行:
<br>
```bash
npm install --save react-native-smartrefreshlayout
```
<br>
or (已经安装了yarn)
<br>

```bash
yarn add react-native-smartrefreshlayout
```


#### 第二步
工程目录下运行:<br>
```bash
react-native link react-native-smartrefreshlayout
```

## 使用
在工程中导入：
```js
import {SmartRefreshControl,DefaultHeader} from 'react-native-smartrefreshlayout';
//使用方法和RN官方的RefreshControl类似，
            <ScrollView
                refreshControl={<SmartRefreshControl
                    ref={ref => this.rc = ref}
                    HeaderComponent={<DefaultHeader/>}
                    onRefresh={() => {
                        setTimeout(() => {
                            this.rc && this.rc.finishRefresh();
                        }, 1000)
                    }}
                />}
            >
            </ScrollView>
```
## 组件
### SmartRefreshControl
#### 查看属性
- [`HeaderComponent`](README.md#headercomponent)
- [`enableRefresh`](README.md#enablerefresh)
- [`headerHeight`](README.md#headerHeight)
- [`primaryColor`](README.md#primarycolor)
- [`autoRefresh`](README.md#autorefresh)
- [`pureScroll`](README.md#purescroll)
- [`overScrollBounce`](README.md#overscrollbounce)
- [`overScrollDrag`](README.md#overscrolldrag)
- [`dragRate`](README.md#dragrate)
- [`maxDragRate`](README.md#maxdragrate)
- [`onRefresh`](README.md#onrefresh)
- [`onPullDownToRefresh`](README.md#onpulldowntorefresh)
- [`onReleaseToRefresh`](README.md#onreleasetorefresh)
- [`onHeaderPulling`](README.md#onheaderpulling)
- [`onHeaderReleasing`](README.md#onheaderreleasing)
- [`onHeaderReleased`](README.md#onheaderreleased)
- [`onHeaderMoving`](README.md#onheadermoving)

#### 查看方法

- [`finishRefresh`](README.md#finishrefresh)

## 文档

### Props

#### `HeaderComponent`

用于渲染SmartRefreshLayout组件的header,默认为DefaultHeader。

>**NOTE**
>
>必须传入插件中给出的Header组件，如AnyHeader,DefaultHeader等

| Type | Required |
| ---- | -------- |
| Element | No       |

---

#### `enableRefresh`

是否启用下拉刷新,默认为true

| Type | Required |
| ---- | -------- |
| bool | No       |

#### `headerHeight`

设定header的高度

>**NOTE**
>
>自定义 header 时应指定headerHeight。

| Type | Required |
| ---- | -------- |
| number | No       |

#### 属性
#### 属性表格
|属性名|类型|描述|
|:---:|:---:|:---:|
|onRefresh|func|刷新触发|
|onPullDownToRefresh|func|可下拉刷新时触发|
|onReleaseToRefresh|func|可释放刷新时触发|
|onHeaderPulling|func|header下拉过程触发|
|onHeaderReleasing|func|header释放过程触发|
|onHeaderReleased|func|header释放时触发|
|onHeaderMoving|func|header移动时触发(包括下拉和释放过程)|
|enableRefresh|bool|是否启用刷新|
|headerHeight|number|设置Header的高度|
|primaryColor|string|刷新控件主调色|
|autoRefresh|{refresh,timeout}|设置自动刷新|
|HeaderComponent|Component|refreshcontrol的header|
|pureScroll|bool|是否纯滚动|
|overScrollBounce|bool|是否越界回弹|
|overScrollDrag|bool|是否使用越界拖动，类似IOS样式|
|dragRate|number|为(显示下拉高度/手指真实下拉高度=阻尼效果)|
|maxDragRate|number|最大显示下拉高度/Header标准高度|

注意：HeaderComponet现在支持任意的RN组件，但是需要放在AnyHeader的组件中，其中onHeaderPulling、onHeaderReleasing和onHeaderMoving的参数为{nativeEvent:{percent,offset,headerHeight}},可用来控制下拉和释放过程中更为精细的动画
如果下拉和释放过程不需要过程动画，则使用onPullDownToRefresh和onReleaseToRefresh即可实现，请看示例：Example <br/> [HuaweiRefreshControl](https://github.com/react-native-studio/react-native-SmartRefreshLayout/blob/master/Example/HuaWeiRefreshControl.js)

[LottieRefreshControl](https://github.com/react-native-studio/react-native-SmartRefreshLayout/blob/master/Example/LottieRefreshControl.js)


建议:该组件与[lottie-react-native](https://github.com/react-community/lottie-react-native)配合使用可获得绝佳的下拉动画效果
#### 方法表格
|方法名|参数|描述|
|:---:|:---:|:---:|
|finishRefresh|{delayed:number,success:bool}|完成刷新|

### AnyHeader
#### 属性表格
|属性名|类型|描述|
|:---:|:---:|:---:|
|primaryColor|string|主题色|

### ClassicsHeader/DefaultHeader
#### 属性表格
|属性名|类型|描述|
|:---:|:---:|:---:|
|primaryColor|string|主题色|
|accentColor|string|强调色|

### StoreHouseHeader
#### 属性表格
|属性名|类型|描述|
|:---:|:---:|:---:|
|text|string|文字(目前只支持英文)|
|textColor|string|文字颜色|
|lineWidth|number|线宽|
## 示例
<!--![image](https://github.com/2534290808/react-native-android-danmaku/blob/master/images/Screenshot_1513176625.png)-->
<div align=center>
<img src="https://github.com/react-native-studio/react-native-smartrefreshlayout/blob/master/images/lottierefresh.gif" width = "280" alt="图片名称" align=center />
<img src="https://github.com/react-native-studio/react-native-smartrefreshlayout/blob/master/images/Screenshot_1520489605.png" width = "280"  alt="图片名称" align=center />
<img src="https://github.com/react-native-studio/react-native-smartrefreshlayout/blob/master/images/Screenshot_1520489593.png" width = "280"  alt="图片名称" align=center />
  </div>
  

