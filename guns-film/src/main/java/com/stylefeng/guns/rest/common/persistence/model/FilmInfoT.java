package com.stylefeng.guns.rest.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 演员表
 * </p>
 *
 * @author jiayiy
 * @since 2020-07-19
 */
@TableName("film_info_t")
public class FilmInfoT extends Model<FilmInfoT> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "uuid", type = IdType.AUTO)
    private Integer uuid;
    /**
     * 影片编号
     */
    @TableField("film_id")
    private String filmId;
    /**
     * 影片英文名称
     */
    @TableField("film_en_name")
    private String filmEnName;
    /**
     * 评分人数,以万为单位
     */
    @TableField("film_score_num")
    private Integer filmScoreNum;
    /**
     * 播放时长，以分钟为单位，不足取整
     */
    @TableField("film_length")
    private Integer filmLength;
    /**
     * 影片评分
     */
    @TableField("film_score")
    private String filmScore;
    /**
     * 影片图片集地址,多个图片以逗号分隔
     */
    @TableField("film_imgs")
    private String filmImgs;
    /**
     * 导演编号
     */
    @TableField("director_id")
    private Integer directorId;
    /**
     * 影片简介
     */
    @TableField("film_biography")
    private String filmBiography;


    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmEnName() {
        return filmEnName;
    }

    public void setFilmEnName(String filmEnName) {
        this.filmEnName = filmEnName;
    }

    public Integer getFilmScoreNum() {
        return filmScoreNum;
    }

    public void setFilmScoreNum(Integer filmScoreNum) {
        this.filmScoreNum = filmScoreNum;
    }

    public Integer getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(Integer filmLength) {
        this.filmLength = filmLength;
    }

    public String getFilmScore() {
        return filmScore;
    }

    public void setFilmScore(String filmScore) {
        this.filmScore = filmScore;
    }

    public String getFilmImgs() {
        return filmImgs;
    }

    public void setFilmImgs(String filmImgs) {
        this.filmImgs = filmImgs;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public String getFilmBiography() {
        return filmBiography;
    }

    public void setFilmBiography(String filmBiography) {
        this.filmBiography = filmBiography;
    }

    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "FilmInfoT{" +
        "uuid=" + uuid +
        ", filmId=" + filmId +
        ", filmEnName=" + filmEnName +
        ", filmScoreNum=" + filmScoreNum +
        ", filmLength=" + filmLength +
        ", filmScore=" + filmScore +
        ", filmImgs=" + filmImgs +
        ", directorId=" + directorId +
        ", filmBiography=" + filmBiography +
        "}";
    }
}
