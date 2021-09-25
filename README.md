# Glide

## 什么是Glide

- Glide是一个快速高效的Android图片加载库，具备性能高，流式API语法的特点。

优势：

1. 可配置度高，自适应度高
2. 支持多种数据源，本地，网络，assets，gif在glide都支持
3. 高效缓存，支持memory和disk图片缓存，默认使用二级缓存
4. 高效处理bitmap，使用bitmap pool修复Bitmap
5. 图片加载过程可以监听
6. 生命周期集成到Glide

- 与主流图片缓存框架优缺点比较

ImageLoader的优点：

1. 支持本地缓存文件名规则定义
2. 默认实现多种内存缓存算法
3. 避免内存泄漏，在view滚动的时候暂停

ImageLoader的缺点：

1. 配置麻烦
2. 作者已停止迭代