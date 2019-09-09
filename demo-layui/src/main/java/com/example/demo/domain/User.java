package com.example.demo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_layuiuser.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_layuiuser.username
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_layuiuser.sex
     *
     * @mbg.generated
     */
    private String sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_layuiuser.city
     *
     * @mbg.generated
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_layuiuser.sign
     *
     * @mbg.generated
     */
    private String sign;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_layuiuser.experience
     *
     * @mbg.generated
     */
    private String experience;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_layuiuser.logins
     *
     * @mbg.generated
     */
    private String logins;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_layuiuser.score
     *
     * @mbg.generated
     */
    private BigDecimal score;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_layuiuser.wealth
     *
     * @mbg.generated
     */
    private String wealth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_layuiuser.classify
     *
     * @mbg.generated
     */
    private String classify;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_layuiuser
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_layuiuser.id
     *
     * @return the value of t_layuiuser.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_layuiuser.id
     *
     * @param id the value for t_layuiuser.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_layuiuser.username
     *
     * @return the value of t_layuiuser.username
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_layuiuser.username
     *
     * @param username the value for t_layuiuser.username
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_layuiuser.sex
     *
     * @return the value of t_layuiuser.sex
     *
     * @mbg.generated
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_layuiuser.sex
     *
     * @param sex the value for t_layuiuser.sex
     *
     * @mbg.generated
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_layuiuser.city
     *
     * @return the value of t_layuiuser.city
     *
     * @mbg.generated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_layuiuser.city
     *
     * @param city the value for t_layuiuser.city
     *
     * @mbg.generated
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_layuiuser.sign
     *
     * @return the value of t_layuiuser.sign
     *
     * @mbg.generated
     */
    public String getSign() {
        return sign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_layuiuser.sign
     *
     * @param sign the value for t_layuiuser.sign
     *
     * @mbg.generated
     */
    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_layuiuser.experience
     *
     * @return the value of t_layuiuser.experience
     *
     * @mbg.generated
     */
    public String getExperience() {
        return experience;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_layuiuser.experience
     *
     * @param experience the value for t_layuiuser.experience
     *
     * @mbg.generated
     */
    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_layuiuser.logins
     *
     * @return the value of t_layuiuser.logins
     *
     * @mbg.generated
     */
    public String getLogins() {
        return logins;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_layuiuser.logins
     *
     * @param logins the value for t_layuiuser.logins
     *
     * @mbg.generated
     */
    public void setLogins(String logins) {
        this.logins = logins == null ? null : logins.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_layuiuser.score
     *
     * @return the value of t_layuiuser.score
     *
     * @mbg.generated
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_layuiuser.score
     *
     * @param score the value for t_layuiuser.score
     *
     * @mbg.generated
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_layuiuser.wealth
     *
     * @return the value of t_layuiuser.wealth
     *
     * @mbg.generated
     */
    public String getWealth() {
        return wealth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_layuiuser.wealth
     *
     * @param wealth the value for t_layuiuser.wealth
     *
     * @mbg.generated
     */
    public void setWealth(String wealth) {
        this.wealth = wealth == null ? null : wealth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_layuiuser.classify
     *
     * @return the value of t_layuiuser.classify
     *
     * @mbg.generated
     */
    public String getClassify() {
        return classify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_layuiuser.classify
     *
     * @param classify the value for t_layuiuser.classify
     *
     * @mbg.generated
     */
    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_layuiuser
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", sex=").append(sex);
        sb.append(", city=").append(city);
        sb.append(", sign=").append(sign);
        sb.append(", experience=").append(experience);
        sb.append(", logins=").append(logins);
        sb.append(", score=").append(score);
        sb.append(", wealth=").append(wealth);
        sb.append(", classify=").append(classify);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}