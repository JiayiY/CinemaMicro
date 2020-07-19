package com.stylefeng.guns.rest.modular.vo;

/**
 * @ClassName ResponseVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/17 22:55
 * @Vertion 1.0
 **/
public class ResponseVO<M> {
    /**
     * 返回状态
     */
    private int status;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据实体
     */
    private M data;

    /**
     * 图片前缀
     */
    private String imgPre;

    /**
     * 分页使用
     */
    private int nowPage;
    private int totalPage;

    private ResponseVO() {

    }

    public static <M> ResponseVO<?> success(int nowPage, int totalPage, String imgPre, M m) {
        ResponseVO<M> responseVO = new ResponseVO<>();
        responseVO.setNowPage(nowPage);
        responseVO.setImgPre(imgPre);
        responseVO.setTotalPage(totalPage);
        responseVO.setData(m);
        return responseVO;
    }

    public static<M> ResponseVO<?> success(String imgPre, M m) {
        ResponseVO<M> responseVO = new ResponseVO<>();
        responseVO.setStatus(0);
        responseVO.setData(m);
        responseVO.setImgPre(imgPre);
        return responseVO;
    }

    public static <M> ResponseVO<?> success(M m) {
        ResponseVO<M> responseVO = new ResponseVO<>();
        responseVO.setStatus(0);
        responseVO.setData(m);
        return responseVO;
    }

    public static <M> ResponseVO<?> success(String msg) {
        ResponseVO<M> responseVO = new ResponseVO<>();
        responseVO.setStatus(0);
        responseVO.setMsg(msg);
        return responseVO;
    }

    // 业务异常
    public static <M> ResponseVO serviceFail(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);
        return responseVO;
    }

    // 系统异常
    public static <M> ResponseVO appFail(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setMsg(msg);
        return responseVO;
    }

    public String getImgPre() {
        return imgPre;
    }

    public void setImgPre(String imgPre) {
        this.imgPre = imgPre;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }
}
