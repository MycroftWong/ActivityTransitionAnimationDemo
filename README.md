# Activity 跳转动画

因为非常喜欢原生Android 5.0以上的默认Activity跳转动画，而大部分定制rom都会改变默认的跳转动画，所以找到5.0的原生实现，加入到项目的theme中，实现原生的效果，支持5.0以上。

Android 4.4 默认是从中间的放大动画，个人感觉还没有slide_in, slide_out好看。

## 概念

Activity A 启动 Activity B，实际上有两部分动画：一是 B 的进入动画，二是 A 的退出动画

Activity B 返回到 Activity A，实际上也有两部分动画：一是 A 的进入动画，二是 B 的退出动画

在Animation中，分别将这四种称作：

- android:activityOpenEnterAnimation
- android:activityOpenExitAnimation
- android:activityCloseEnterAnimation
- android:activityCloseExitAnimation

在Transition中，分别将这四种称作：

- android:windowEnterTransition
- android:windowExitTransition
- android:windowReturnTransition
- android:windowReenterTransition

## 实现

Android有两种方式来实现Activity的跳转：Animation和Transition

### Animation

#### xml实现

在对应的Activity的theme中添加属性```android:windowAnimationStyle```(style类型), 再在这个style指定四种动画。

#### 代码实现

A 启动 B:

在调用```Activity.startActivity***```之后，调用```Activity.overridePendingTransition```方法，

B 返回到 A:

在调用```Activity.finish```之后，调用```Activity.overridePendingTransition```方法，

##### 冲突

A/B 分别指定不同的跳转动画。

Q:

A 启动 B, B 返回到 A: 使用的是AB谁定义的动画，还是使用各自的动画。

A:

依照原则：以目标Activity为准。即A启动B，则使用B的动画，B返回A，则使用A的动画。

### Transition

#### xml实现

在对应的Activity的theme中添加属性：

- android:windowEnterTransition
- android:windowExitTransition
- android:windowReturnTransition
- android:windowReenterTransition

同时需要指定属性```android:windowContentTransitions```为```true```。

只在xml中指定是没法完成Transition动画的，需要使用下面的方式启动Activity：
```java
ActivityCompat.startActivity(this,
                new Intent(this, OtherActivity.class),
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, (Pair<View, String>[]) null).toBundle());
```

使用```ActivityCompat```和```ActivityOptionsCompat```是为了兼容5.0以下版本，不然需要自行判断版本。

#### 代码实现

重写```Activity.onCreate```方法时，在```setContentView```之前执行以下代码：
```java
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    getWindow().setEnterTransition(new AutoTransition());
    // 设置四种动画
}
````

### 冲突

Q: 同时在xml中指定Animation和Transition动画，使用哪种？

A: 从启动Activity的方式区别。


## 对比

Android 5.0加入 Activity Transition 动画之后确实提供了一种不一样的实现方式。

不过对于简单的scale, transition, alpha动画，使用animation动画实现则比较简单，不需要在Transition上费工夫。

个人认为 Activity Transition 在实践中的最好的效果是 Explode 和 ShareElement. ShareElement的效果不言而喻，该效果很好的将界面跳转的共有元素结合在一起。让界面跳转看起来更加流畅。而Explode则更多的是让界面更有拼凑感，新的界面由多个元素聚集起来，退出时又四散开。

### Transition动画

实际上，Transition动画在4.4时已经出现，不过当时是用于View的动画。在5.0的时候更加完善，增加到了Activity跳转时的动画。

关于常用的Transition实现，参见[Transition](Transition.md)



## TODO
内容显示在status bar下面





























<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
