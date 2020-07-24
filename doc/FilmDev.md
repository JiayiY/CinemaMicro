# 影片模块开发

## 构建

在项目中复制一份`guns-user`模块并重命名为`guns-film`（修改子模块和主模块的`pom.xml`文件），并在`application.yml`中关闭其鉴权机制（仅网关需要开启），修改端口避免冲突。

## 开发

### API网关功能聚合

`guns-gateway`中的获取首页信息接口，将`filmServiceApi`的多个方法所获得的对象聚合为`FilmIndexVO`对象返回给前端，将原本需要请求的多个接口聚合为一个`/film/getIndex`接口，避免了前端对不同接口的多次调用。

```java
@RequestMapping(value = "getIndex", method = RequestMethod.GET)
public ResponseVO<?> getIndex() {
    FilmIndexVO filmIndexVO = new FilmIndexVO();
    // 获取banner信息
    filmIndexVO.setBanners(filmServiceApi.getBanners());
    // 获取热映的影片
    filmIndexVO.setHotFilms(filmServiceApi.getHotFilms(true, 99, 99, 99, 99, 1, 8));
    // 获取即将上映的影片
    filmIndexVO.setSoonFilms(filmServiceApi.getSoonFilms(true, 99, 99, 99, 99, 1, 8));
    // 获取票房排行榜
    filmIndexVO.setBoxRanking(filmServiceApi.getBoxRanking());
    // 获取人气榜单
    filmIndexVO.setExpectRanking(filmServiceApi.getExpectRanking());
    // 获取排行前100影片
    filmIndexVO.setTop100(filmServiceApi.getTop());
    return ResponseVO.success(filmIndexVO);
}
```

优点：

+ 同一个接口对外暴露，降低了前后端分离开发的难度和复杂度；
+ 六个接口，一次请求，同一时刻节省了5次HTTP请求；

缺点：

+ 一次获取数据过多，可能出现预期外的问题；

