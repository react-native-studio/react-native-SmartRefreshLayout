# React Native SmartRefreshLayout  [![npm version](https://badge.fury.io/js/react-native-smartrefreshlayout.svg)](https://badge.fury.io/js/react-native-smartrefreshlayout)

>React-Native-SmartRefreshLayout是基于[Android SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout) 开发的插件
>,<br>可提供类似ios的弹性刷新,该插件可完全使用React Native进行自定义
>
>HeaderComponent现在支持任意的RN组件，但是需要放在AnyHeader的组件中，<br>其中onHeaderPulling、onHeaderReleasing和onHeaderMoving的参数为{nativeEvent:{percent,offset,headerHeight}},可用来控制下拉和释放过程中更为精细的动画,<br>
> 如果下拉和释放过程不需要过程动画，则使用onPullDownToRefresh和onReleaseToRefresh即可实现。
><br>请看示例：Example <br/> [HuaweiRefreshControl](https://github.com/react-native-studio/react-native-SmartRefreshLayout/blob/master/Example/HuaWeiRefreshControl.js)
>、 [LottieRefreshControl](https://github.com/react-native-studio/react-native-SmartRefreshLayout/blob/master/Example/LottieRefreshControl.js)
>
>IOS自定义下拉刷新组件见[React-Native-MJRefresh](https://github.com/react-native-studio/react-native-MJRefresh)
>
>


>建议:该组件与[lottie-react-native](https://github.com/react-community/lottie-react-native)配合使用可获得绝佳的下拉动画效果

## 安装
#### 第一步
工程目录下运行:
<br>
```bash
npm install --save react-native-smartrefreshlayout
```

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
其他组件查看[AnyHeader](./docs/AnyHeader.md)、[DefaultHeader](./docs/DefaultHeader.md)、[ClassicsHeader](./docs/DefaultHeader.md)、[StoreHouseHeader](./docs/StoreHouse.md)
#### 查看属性
- [`HeaderComponent`](README.md#headercomponent)
- [`renderHeader`](README.md#renderHeader)
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

#### `renderHeader`

用于渲染SmartRefreshLayout组件的header,默认为DefaultHeader。

>**NOTE**
>
>必须传入插件中给出的Header组件，如AnyHeader,DefaultHeader等

| Type | Required |
| ---- | -------- |
| Element/func | No       |

---

#### `enableRefresh`

是否启用下拉刷新,默认为true

| Type | Required |
| ---- | -------- |
| boolean | No       |

---

#### `headerHeight`

设定header的高度

>**NOTE**
>
>自定义 header 时应指定headerHeight。

| Type | Required |
| ---- | -------- |
| number | No       |

---

#### `primaryColor`

设置刷新组件的主调色

| Type | Required |
| ---- | -------- |
| string | No       |

---

#### `autoRefresh`
>***NOTE***
>
>time字段含义：延迟time毫秒后自动刷新

是否自动刷新

| Type | Required |
| ---- | -------- |
| object:{refresh:boolean, time:number} | No       |

---

#### `pureScroll`

是否启用纯滚动

| Type | Required |
| ---- | -------- |
| boolean | No       |

---

#### `overScrollBounce`

是否允许越界回弹

| Type | Required |
| ---- | -------- |
| boolean | No       |

---

#### `overScrollDrag`

是否启用越界拖动，类似IOS样式。

| Type | Required |
| ---- | -------- |
| boolean | No       |

---

#### `dragRate`

设置组件下拉高度与手指真实下拉高度的比值,默认为0.5。

| Type | Required |
| ---- | -------- |
| number | No       |

---

#### `maxDragRate`

设置最大显示下拉高度与header标准高度的比值，默认为2.0。

| Type | Required |
| ---- | -------- |
| number | No       |

---

#### `onPullDownToRefresh`

可下拉刷新时触发

| Type | Required |
| ---- | -------- |
| function | No       |

---

#### `onReleaseToRefresh`

可释放刷新时触发

| Type | Required |
| ---- | -------- |
| function | No       |

---

#### `onRefresh`

刷新时触发

| Type | Required |
| ---- | -------- |
| function | No       |

---

#### `onHeaderReleased`

Header释放时触发

| Type | Required |
| ---- | -------- |
| function | No       |

---

#### `onHeaderPulling`

```javascript
   ({nativeEvent: {percent:number, offset:number, headerHeight:number}})=>void;
```
header下拉过程中触发

| Type | Required |
| ---- | -------- |
| function | No       |

---

#### `onHeaderReleasing`

```javascript
   ({nativeEvent: {percent:number, offset:number, headerHeight:number}})=>void;
```
header释放过程中触发

| Type | Required |
| ---- | -------- |
| function | No       |

---

#### `onHeaderMoving`

```javascript
   ({nativeEvent: {percent:number, offset:number, headerHeight:number}})=>void;
```
header移动过程中触发,包括下拉过程和释放过程。

| Type | Required |
| ---- | -------- |
| function | No       |

### Methods

#### `finishRefresh`

```javascript
   finishRefresh([params]);
```

完成刷新

| Name | Type | Required|
| ---- | -------- |-----|
| params | object | NO |

Valid `params` keys are：
* `delayed` (number) - 延迟完成刷新的时间
* `success` (boolean) - 是否刷新成功,暂时没有影响


## 示例
<!--![image](https://github.com/2534290808/react-native-android-danmaku/blob/master/images/Screenshot_1513176625.png)-->
<div align=center>
<img src="https://github.com/react-native-studio/react-native-smartrefreshlayout/blob/master/images/lottierefresh.gif" width = "280" alt="图片名称" align=center />
<img src="https://github.com/react-native-studio/react-native-smartrefreshlayout/blob/master/images/Screenshot_1520489605.png" width = "280"  alt="图片名称" align=center />
<img src="https://github.com/react-native-studio/react-native-smartrefreshlayout/blob/master/images/Screenshot_1520489593.png" width = "280"  alt="图片名称" align=center />
  </div>
  

