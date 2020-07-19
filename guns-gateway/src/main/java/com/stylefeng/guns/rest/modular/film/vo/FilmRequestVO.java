package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

/**
 * @ClassName FilmRequestVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 11:50
 * @Vertion 1.0
 **/
@Data
public class FilmRequestVO {

    private Integer showType;

    private Integer sortId;

    private Integer catId;

    private Integer sourceId;

    private Integer yearId;

    private Integer nowPage;

    private Integer pageSize;

    /**
     * 初始化为默认值，需要手动调用避免一些问题
     */
    public void init() {
        if (this.showType == null) {
            this.showType = 1;
        }
        if (this.sortId == null) {
            this.sortId = 1;
        }
        if (this.catId == null) {
            this.catId = 5;
        }
        if (this.sourceId == null) {
            this.sourceId = 5;
        }
        if (this.yearId == null) {
            this.yearId = 5;
        }
        if (this.nowPage == null) {
            this.nowPage = 1;
        }
        if (this.pageSize == null) {
            this.pageSize = 18;
        }
    }
}
