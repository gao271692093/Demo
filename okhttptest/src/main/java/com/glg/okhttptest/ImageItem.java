package com.glg.okhttptest;

/**
 * Description:
 *
 * @package: com.glg.mygif
 * @className: ImageItem
 * @author: gao
 * @date: 2020/8/10 18:45
 */
public class ImageItem {

    private String copyright;
    private String copyrightLink;
    private String hsh;
    private String quiz;
    private String url;
    private String urlBase;
    private String startDate;
    private String endDate;
    private String CDNUrl;

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCopyrightLink() {
        return copyrightLink;
    }

    public void setCopyrightLink(String copyrightLink) {
        this.copyrightLink = copyrightLink;
    }

    public String getHsh() {
        return hsh;
    }

    public void setHsh(String hsh) {
        this.hsh = hsh;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCDNUrl() {
        return CDNUrl;
    }

    public void setCDNUrl(String CDNUrl) {
        this.CDNUrl = CDNUrl;
    }
}
