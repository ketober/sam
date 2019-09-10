package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSamStaffInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamStaffInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andStaffIdIsNull() {
            addCriterion("STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(String value) {
            addCriterion("STAFF_ID =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(String value) {
            addCriterion("STAFF_ID <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(String value) {
            addCriterion("STAFF_ID >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_ID >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(String value) {
            addCriterion("STAFF_ID <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(String value) {
            addCriterion("STAFF_ID <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLike(String value) {
            addCriterion("STAFF_ID like", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotLike(String value) {
            addCriterion("STAFF_ID not like", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<String> values) {
            addCriterion("STAFF_ID in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<String> values) {
            addCriterion("STAFF_ID not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(String value1, String value2) {
            addCriterion("STAFF_ID between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(String value1, String value2) {
            addCriterion("STAFF_ID not between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffNameIsNull() {
            addCriterion("STAFF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStaffNameIsNotNull() {
            addCriterion("STAFF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStaffNameEqualTo(String value) {
            addCriterion("STAFF_NAME =", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotEqualTo(String value) {
            addCriterion("STAFF_NAME <>", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameGreaterThan(String value) {
            addCriterion("STAFF_NAME >", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_NAME >=", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLessThan(String value) {
            addCriterion("STAFF_NAME <", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLessThanOrEqualTo(String value) {
            addCriterion("STAFF_NAME <=", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLike(String value) {
            addCriterion("STAFF_NAME like", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotLike(String value) {
            addCriterion("STAFF_NAME not like", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameIn(List<String> values) {
            addCriterion("STAFF_NAME in", values, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotIn(List<String> values) {
            addCriterion("STAFF_NAME not in", values, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameBetween(String value1, String value2) {
            addCriterion("STAFF_NAME between", value1, value2, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotBetween(String value1, String value2) {
            addCriterion("STAFF_NAME not between", value1, value2, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffStateIsNull() {
            addCriterion("STAFF_STATE is null");
            return (Criteria) this;
        }

        public Criteria andStaffStateIsNotNull() {
            addCriterion("STAFF_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStaffStateEqualTo(String value) {
            addCriterion("STAFF_STATE =", value, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateNotEqualTo(String value) {
            addCriterion("STAFF_STATE <>", value, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateGreaterThan(String value) {
            addCriterion("STAFF_STATE >", value, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_STATE >=", value, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateLessThan(String value) {
            addCriterion("STAFF_STATE <", value, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateLessThanOrEqualTo(String value) {
            addCriterion("STAFF_STATE <=", value, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateLike(String value) {
            addCriterion("STAFF_STATE like", value, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateNotLike(String value) {
            addCriterion("STAFF_STATE not like", value, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateIn(List<String> values) {
            addCriterion("STAFF_STATE in", values, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateNotIn(List<String> values) {
            addCriterion("STAFF_STATE not in", values, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateBetween(String value1, String value2) {
            addCriterion("STAFF_STATE between", value1, value2, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffStateNotBetween(String value1, String value2) {
            addCriterion("STAFF_STATE not between", value1, value2, "staffState");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusIsNull() {
            addCriterion("STAFF_ID_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusIsNotNull() {
            addCriterion("STAFF_ID_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusEqualTo(String value) {
            addCriterion("STAFF_ID_STATUS =", value, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusNotEqualTo(String value) {
            addCriterion("STAFF_ID_STATUS <>", value, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusGreaterThan(String value) {
            addCriterion("STAFF_ID_STATUS >", value, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_ID_STATUS >=", value, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusLessThan(String value) {
            addCriterion("STAFF_ID_STATUS <", value, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusLessThanOrEqualTo(String value) {
            addCriterion("STAFF_ID_STATUS <=", value, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusLike(String value) {
            addCriterion("STAFF_ID_STATUS like", value, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusNotLike(String value) {
            addCriterion("STAFF_ID_STATUS not like", value, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusIn(List<String> values) {
            addCriterion("STAFF_ID_STATUS in", values, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusNotIn(List<String> values) {
            addCriterion("STAFF_ID_STATUS not in", values, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusBetween(String value1, String value2) {
            addCriterion("STAFF_ID_STATUS between", value1, value2, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andStaffIdStatusNotBetween(String value1, String value2) {
            addCriterion("STAFF_ID_STATUS not between", value1, value2, "staffIdStatus");
            return (Criteria) this;
        }

        public Criteria andDlevelIdIsNull() {
            addCriterion("DLEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andDlevelIdIsNotNull() {
            addCriterion("DLEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDlevelIdEqualTo(String value) {
            addCriterion("DLEVEL_ID =", value, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdNotEqualTo(String value) {
            addCriterion("DLEVEL_ID <>", value, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdGreaterThan(String value) {
            addCriterion("DLEVEL_ID >", value, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("DLEVEL_ID >=", value, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdLessThan(String value) {
            addCriterion("DLEVEL_ID <", value, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdLessThanOrEqualTo(String value) {
            addCriterion("DLEVEL_ID <=", value, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdLike(String value) {
            addCriterion("DLEVEL_ID like", value, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdNotLike(String value) {
            addCriterion("DLEVEL_ID not like", value, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdIn(List<String> values) {
            addCriterion("DLEVEL_ID in", values, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdNotIn(List<String> values) {
            addCriterion("DLEVEL_ID not in", values, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdBetween(String value1, String value2) {
            addCriterion("DLEVEL_ID between", value1, value2, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDlevelIdNotBetween(String value1, String value2) {
            addCriterion("DLEVEL_ID not between", value1, value2, "dlevelId");
            return (Criteria) this;
        }

        public Criteria andDutyIdIsNull() {
            addCriterion("DUTY_ID is null");
            return (Criteria) this;
        }

        public Criteria andDutyIdIsNotNull() {
            addCriterion("DUTY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDutyIdEqualTo(String value) {
            addCriterion("DUTY_ID =", value, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdNotEqualTo(String value) {
            addCriterion("DUTY_ID <>", value, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdGreaterThan(String value) {
            addCriterion("DUTY_ID >", value, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdGreaterThanOrEqualTo(String value) {
            addCriterion("DUTY_ID >=", value, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdLessThan(String value) {
            addCriterion("DUTY_ID <", value, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdLessThanOrEqualTo(String value) {
            addCriterion("DUTY_ID <=", value, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdLike(String value) {
            addCriterion("DUTY_ID like", value, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdNotLike(String value) {
            addCriterion("DUTY_ID not like", value, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdIn(List<String> values) {
            addCriterion("DUTY_ID in", values, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdNotIn(List<String> values) {
            addCriterion("DUTY_ID not in", values, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdBetween(String value1, String value2) {
            addCriterion("DUTY_ID between", value1, value2, "dutyId");
            return (Criteria) this;
        }

        public Criteria andDutyIdNotBetween(String value1, String value2) {
            addCriterion("DUTY_ID not between", value1, value2, "dutyId");
            return (Criteria) this;
        }

        public Criteria andSecondDutyIsNull() {
            addCriterion("SECOND_DUTY is null");
            return (Criteria) this;
        }

        public Criteria andSecondDutyIsNotNull() {
            addCriterion("SECOND_DUTY is not null");
            return (Criteria) this;
        }

        public Criteria andSecondDutyEqualTo(String value) {
            addCriterion("SECOND_DUTY =", value, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyNotEqualTo(String value) {
            addCriterion("SECOND_DUTY <>", value, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyGreaterThan(String value) {
            addCriterion("SECOND_DUTY >", value, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_DUTY >=", value, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyLessThan(String value) {
            addCriterion("SECOND_DUTY <", value, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyLessThanOrEqualTo(String value) {
            addCriterion("SECOND_DUTY <=", value, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyLike(String value) {
            addCriterion("SECOND_DUTY like", value, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyNotLike(String value) {
            addCriterion("SECOND_DUTY not like", value, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyIn(List<String> values) {
            addCriterion("SECOND_DUTY in", values, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyNotIn(List<String> values) {
            addCriterion("SECOND_DUTY not in", values, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyBetween(String value1, String value2) {
            addCriterion("SECOND_DUTY between", value1, value2, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andSecondDutyNotBetween(String value1, String value2) {
            addCriterion("SECOND_DUTY not between", value1, value2, "secondDuty");
            return (Criteria) this;
        }

        public Criteria andOrgaIdIsNull() {
            addCriterion("ORGA_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgaIdIsNotNull() {
            addCriterion("ORGA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgaIdEqualTo(String value) {
            addCriterion("ORGA_ID =", value, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdNotEqualTo(String value) {
            addCriterion("ORGA_ID <>", value, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdGreaterThan(String value) {
            addCriterion("ORGA_ID >", value, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORGA_ID >=", value, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdLessThan(String value) {
            addCriterion("ORGA_ID <", value, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdLessThanOrEqualTo(String value) {
            addCriterion("ORGA_ID <=", value, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdLike(String value) {
            addCriterion("ORGA_ID like", value, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdNotLike(String value) {
            addCriterion("ORGA_ID not like", value, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdIn(List<String> values) {
            addCriterion("ORGA_ID in", values, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdNotIn(List<String> values) {
            addCriterion("ORGA_ID not in", values, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdBetween(String value1, String value2) {
            addCriterion("ORGA_ID between", value1, value2, "orgaId");
            return (Criteria) this;
        }

        public Criteria andOrgaIdNotBetween(String value1, String value2) {
            addCriterion("ORGA_ID not between", value1, value2, "orgaId");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNull() {
            addCriterion("POST_ID is null");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNotNull() {
            addCriterion("POST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPostIdEqualTo(String value) {
            addCriterion("POST_ID =", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotEqualTo(String value) {
            addCriterion("POST_ID <>", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThan(String value) {
            addCriterion("POST_ID >", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThanOrEqualTo(String value) {
            addCriterion("POST_ID >=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThan(String value) {
            addCriterion("POST_ID <", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThanOrEqualTo(String value) {
            addCriterion("POST_ID <=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLike(String value) {
            addCriterion("POST_ID like", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotLike(String value) {
            addCriterion("POST_ID not like", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdIn(List<String> values) {
            addCriterion("POST_ID in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotIn(List<String> values) {
            addCriterion("POST_ID not in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdBetween(String value1, String value2) {
            addCriterion("POST_ID between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotBetween(String value1, String value2) {
            addCriterion("POST_ID not between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andSecondPostIsNull() {
            addCriterion("SECOND_POST is null");
            return (Criteria) this;
        }

        public Criteria andSecondPostIsNotNull() {
            addCriterion("SECOND_POST is not null");
            return (Criteria) this;
        }

        public Criteria andSecondPostEqualTo(String value) {
            addCriterion("SECOND_POST =", value, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostNotEqualTo(String value) {
            addCriterion("SECOND_POST <>", value, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostGreaterThan(String value) {
            addCriterion("SECOND_POST >", value, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_POST >=", value, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostLessThan(String value) {
            addCriterion("SECOND_POST <", value, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostLessThanOrEqualTo(String value) {
            addCriterion("SECOND_POST <=", value, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostLike(String value) {
            addCriterion("SECOND_POST like", value, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostNotLike(String value) {
            addCriterion("SECOND_POST not like", value, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostIn(List<String> values) {
            addCriterion("SECOND_POST in", values, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostNotIn(List<String> values) {
            addCriterion("SECOND_POST not in", values, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostBetween(String value1, String value2) {
            addCriterion("SECOND_POST between", value1, value2, "secondPost");
            return (Criteria) this;
        }

        public Criteria andSecondPostNotBetween(String value1, String value2) {
            addCriterion("SECOND_POST not between", value1, value2, "secondPost");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeIsNull() {
            addCriterion("UPDA_TETIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeIsNotNull() {
            addCriterion("UPDA_TETIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeEqualTo(Date value) {
            addCriterion("UPDA_TETIME =", value, "updaTetime");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeNotEqualTo(Date value) {
            addCriterion("UPDA_TETIME <>", value, "updaTetime");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeGreaterThan(Date value) {
            addCriterion("UPDA_TETIME >", value, "updaTetime");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDA_TETIME >=", value, "updaTetime");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeLessThan(Date value) {
            addCriterion("UPDA_TETIME <", value, "updaTetime");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDA_TETIME <=", value, "updaTetime");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeIn(List<Date> values) {
            addCriterion("UPDA_TETIME in", values, "updaTetime");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeNotIn(List<Date> values) {
            addCriterion("UPDA_TETIME not in", values, "updaTetime");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeBetween(Date value1, Date value2) {
            addCriterion("UPDA_TETIME between", value1, value2, "updaTetime");
            return (Criteria) this;
        }

        public Criteria andUpdaTetimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDA_TETIME not between", value1, value2, "updaTetime");
            return (Criteria) this;
        }

        public Criteria andJoinDateIsNull() {
            addCriterion("JOIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andJoinDateIsNotNull() {
            addCriterion("JOIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andJoinDateEqualTo(Date value) {
            addCriterion("JOIN_DATE =", value, "joinDate");
            return (Criteria) this;
        }

        public Criteria andJoinDateNotEqualTo(Date value) {
            addCriterion("JOIN_DATE <>", value, "joinDate");
            return (Criteria) this;
        }

        public Criteria andJoinDateGreaterThan(Date value) {
            addCriterion("JOIN_DATE >", value, "joinDate");
            return (Criteria) this;
        }

        public Criteria andJoinDateGreaterThanOrEqualTo(Date value) {
            addCriterion("JOIN_DATE >=", value, "joinDate");
            return (Criteria) this;
        }

        public Criteria andJoinDateLessThan(Date value) {
            addCriterion("JOIN_DATE <", value, "joinDate");
            return (Criteria) this;
        }

        public Criteria andJoinDateLessThanOrEqualTo(Date value) {
            addCriterion("JOIN_DATE <=", value, "joinDate");
            return (Criteria) this;
        }

        public Criteria andJoinDateIn(List<Date> values) {
            addCriterion("JOIN_DATE in", values, "joinDate");
            return (Criteria) this;
        }

        public Criteria andJoinDateNotIn(List<Date> values) {
            addCriterion("JOIN_DATE not in", values, "joinDate");
            return (Criteria) this;
        }

        public Criteria andJoinDateBetween(Date value1, Date value2) {
            addCriterion("JOIN_DATE between", value1, value2, "joinDate");
            return (Criteria) this;
        }

        public Criteria andJoinDateNotBetween(Date value1, Date value2) {
            addCriterion("JOIN_DATE not between", value1, value2, "joinDate");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNull() {
            addCriterion("MOBILE_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNotNull() {
            addCriterion("MOBILE_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneEqualTo(String value) {
            addCriterion("MOBILE_PHONE =", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotEqualTo(String value) {
            addCriterion("MOBILE_PHONE <>", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThan(String value) {
            addCriterion("MOBILE_PHONE >", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE >=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThan(String value) {
            addCriterion("MOBILE_PHONE <", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE <=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLike(String value) {
            addCriterion("MOBILE_PHONE like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotLike(String value) {
            addCriterion("MOBILE_PHONE not like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIn(List<String> values) {
            addCriterion("MOBILE_PHONE in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotIn(List<String> values) {
            addCriterion("MOBILE_PHONE not in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE not between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andEmailAddressIsNull() {
            addCriterion("EMAIL_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andEmailAddressIsNotNull() {
            addCriterion("EMAIL_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andEmailAddressEqualTo(String value) {
            addCriterion("EMAIL_ADDRESS =", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressNotEqualTo(String value) {
            addCriterion("EMAIL_ADDRESS <>", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressGreaterThan(String value) {
            addCriterion("EMAIL_ADDRESS >", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL_ADDRESS >=", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressLessThan(String value) {
            addCriterion("EMAIL_ADDRESS <", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressLessThanOrEqualTo(String value) {
            addCriterion("EMAIL_ADDRESS <=", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressLike(String value) {
            addCriterion("EMAIL_ADDRESS like", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressNotLike(String value) {
            addCriterion("EMAIL_ADDRESS not like", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressIn(List<String> values) {
            addCriterion("EMAIL_ADDRESS in", values, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressNotIn(List<String> values) {
            addCriterion("EMAIL_ADDRESS not in", values, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressBetween(String value1, String value2) {
            addCriterion("EMAIL_ADDRESS between", value1, value2, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressNotBetween(String value1, String value2) {
            addCriterion("EMAIL_ADDRESS not between", value1, value2, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(String value) {
            addCriterion("CITY_ID =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(String value) {
            addCriterion("CITY_ID <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(String value) {
            addCriterion("CITY_ID >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_ID >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(String value) {
            addCriterion("CITY_ID <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(String value) {
            addCriterion("CITY_ID <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLike(String value) {
            addCriterion("CITY_ID like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotLike(String value) {
            addCriterion("CITY_ID not like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<String> values) {
            addCriterion("CITY_ID in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<String> values) {
            addCriterion("CITY_ID not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(String value1, String value2) {
            addCriterion("CITY_ID between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(String value1, String value2) {
            addCriterion("CITY_ID not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andProviceIdIsNull() {
            addCriterion("PROVICE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProviceIdIsNotNull() {
            addCriterion("PROVICE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProviceIdEqualTo(String value) {
            addCriterion("PROVICE_ID =", value, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdNotEqualTo(String value) {
            addCriterion("PROVICE_ID <>", value, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdGreaterThan(String value) {
            addCriterion("PROVICE_ID >", value, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROVICE_ID >=", value, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdLessThan(String value) {
            addCriterion("PROVICE_ID <", value, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdLessThanOrEqualTo(String value) {
            addCriterion("PROVICE_ID <=", value, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdLike(String value) {
            addCriterion("PROVICE_ID like", value, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdNotLike(String value) {
            addCriterion("PROVICE_ID not like", value, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdIn(List<String> values) {
            addCriterion("PROVICE_ID in", values, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdNotIn(List<String> values) {
            addCriterion("PROVICE_ID not in", values, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdBetween(String value1, String value2) {
            addCriterion("PROVICE_ID between", value1, value2, "proviceId");
            return (Criteria) this;
        }

        public Criteria andProviceIdNotBetween(String value1, String value2) {
            addCriterion("PROVICE_ID not between", value1, value2, "proviceId");
            return (Criteria) this;
        }

        public Criteria andStaffTypeIsNull() {
            addCriterion("STAFF_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andStaffTypeIsNotNull() {
            addCriterion("STAFF_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andStaffTypeEqualTo(String value) {
            addCriterion("STAFF_TYPE =", value, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeNotEqualTo(String value) {
            addCriterion("STAFF_TYPE <>", value, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeGreaterThan(String value) {
            addCriterion("STAFF_TYPE >", value, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_TYPE >=", value, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeLessThan(String value) {
            addCriterion("STAFF_TYPE <", value, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeLessThanOrEqualTo(String value) {
            addCriterion("STAFF_TYPE <=", value, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeLike(String value) {
            addCriterion("STAFF_TYPE like", value, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeNotLike(String value) {
            addCriterion("STAFF_TYPE not like", value, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeIn(List<String> values) {
            addCriterion("STAFF_TYPE in", values, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeNotIn(List<String> values) {
            addCriterion("STAFF_TYPE not in", values, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeBetween(String value1, String value2) {
            addCriterion("STAFF_TYPE between", value1, value2, "staffType");
            return (Criteria) this;
        }

        public Criteria andStaffTypeNotBetween(String value1, String value2) {
            addCriterion("STAFF_TYPE not between", value1, value2, "staffType");
            return (Criteria) this;
        }

        public Criteria andIdCardNoIsNull() {
            addCriterion("ID_CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdCardNoIsNotNull() {
            addCriterion("ID_CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardNoEqualTo(String value) {
            addCriterion("ID_CARD_NO =", value, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoNotEqualTo(String value) {
            addCriterion("ID_CARD_NO <>", value, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoGreaterThan(String value) {
            addCriterion("ID_CARD_NO >", value, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("ID_CARD_NO >=", value, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoLessThan(String value) {
            addCriterion("ID_CARD_NO <", value, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoLessThanOrEqualTo(String value) {
            addCriterion("ID_CARD_NO <=", value, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoLike(String value) {
            addCriterion("ID_CARD_NO like", value, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoNotLike(String value) {
            addCriterion("ID_CARD_NO not like", value, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoIn(List<String> values) {
            addCriterion("ID_CARD_NO in", values, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoNotIn(List<String> values) {
            addCriterion("ID_CARD_NO not in", values, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoBetween(String value1, String value2) {
            addCriterion("ID_CARD_NO between", value1, value2, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andIdCardNoNotBetween(String value1, String value2) {
            addCriterion("ID_CARD_NO not between", value1, value2, "idCardNo");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("CHANNEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("CHANNEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(String value) {
            addCriterion("CHANNEL_ID =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(String value) {
            addCriterion("CHANNEL_ID <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(String value) {
            addCriterion("CHANNEL_ID >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHANNEL_ID >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(String value) {
            addCriterion("CHANNEL_ID <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(String value) {
            addCriterion("CHANNEL_ID <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLike(String value) {
            addCriterion("CHANNEL_ID like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotLike(String value) {
            addCriterion("CHANNEL_ID not like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<String> values) {
            addCriterion("CHANNEL_ID in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<String> values) {
            addCriterion("CHANNEL_ID not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(String value1, String value2) {
            addCriterion("CHANNEL_ID between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(String value1, String value2) {
            addCriterion("CHANNEL_ID not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdIsNull() {
            addCriterion("AUTHEN_RULE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdIsNotNull() {
            addCriterion("AUTHEN_RULE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdEqualTo(Integer value) {
            addCriterion("AUTHEN_RULE_ID =", value, "authenRuleId");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdNotEqualTo(Integer value) {
            addCriterion("AUTHEN_RULE_ID <>", value, "authenRuleId");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdGreaterThan(Integer value) {
            addCriterion("AUTHEN_RULE_ID >", value, "authenRuleId");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("AUTHEN_RULE_ID >=", value, "authenRuleId");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdLessThan(Integer value) {
            addCriterion("AUTHEN_RULE_ID <", value, "authenRuleId");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("AUTHEN_RULE_ID <=", value, "authenRuleId");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdIn(List<Integer> values) {
            addCriterion("AUTHEN_RULE_ID in", values, "authenRuleId");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdNotIn(List<Integer> values) {
            addCriterion("AUTHEN_RULE_ID not in", values, "authenRuleId");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdBetween(Integer value1, Integer value2) {
            addCriterion("AUTHEN_RULE_ID between", value1, value2, "authenRuleId");
            return (Criteria) this;
        }

        public Criteria andAuthenRuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("AUTHEN_RULE_ID not between", value1, value2, "authenRuleId");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNull() {
            addCriterion("INSERT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNotNull() {
            addCriterion("INSERT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeEqualTo(Date value) {
            addCriterion("INSERT_TIME =", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotEqualTo(Date value) {
            addCriterion("INSERT_TIME <>", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThan(Date value) {
            addCriterion("INSERT_TIME >", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("INSERT_TIME >=", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThan(Date value) {
            addCriterion("INSERT_TIME <", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThanOrEqualTo(Date value) {
            addCriterion("INSERT_TIME <=", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIn(List<Date> values) {
            addCriterion("INSERT_TIME in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotIn(List<Date> values) {
            addCriterion("INSERT_TIME not in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeBetween(Date value1, Date value2) {
            addCriterion("INSERT_TIME between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotBetween(Date value1, Date value2) {
            addCriterion("INSERT_TIME not between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortIsNull() {
            addCriterion("STAFF_NM_SHORT is null");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortIsNotNull() {
            addCriterion("STAFF_NM_SHORT is not null");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortEqualTo(String value) {
            addCriterion("STAFF_NM_SHORT =", value, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortNotEqualTo(String value) {
            addCriterion("STAFF_NM_SHORT <>", value, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortGreaterThan(String value) {
            addCriterion("STAFF_NM_SHORT >", value, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_NM_SHORT >=", value, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortLessThan(String value) {
            addCriterion("STAFF_NM_SHORT <", value, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortLessThanOrEqualTo(String value) {
            addCriterion("STAFF_NM_SHORT <=", value, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortLike(String value) {
            addCriterion("STAFF_NM_SHORT like", value, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortNotLike(String value) {
            addCriterion("STAFF_NM_SHORT not like", value, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortIn(List<String> values) {
            addCriterion("STAFF_NM_SHORT in", values, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortNotIn(List<String> values) {
            addCriterion("STAFF_NM_SHORT not in", values, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortBetween(String value1, String value2) {
            addCriterion("STAFF_NM_SHORT between", value1, value2, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andStaffNmShortNotBetween(String value1, String value2) {
            addCriterion("STAFF_NM_SHORT not between", value1, value2, "staffNmShort");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdIsNull() {
            addCriterion("PRSN_CHNL_TYPE_CD is null");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdIsNotNull() {
            addCriterion("PRSN_CHNL_TYPE_CD is not null");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdEqualTo(String value) {
            addCriterion("PRSN_CHNL_TYPE_CD =", value, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdNotEqualTo(String value) {
            addCriterion("PRSN_CHNL_TYPE_CD <>", value, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdGreaterThan(String value) {
            addCriterion("PRSN_CHNL_TYPE_CD >", value, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdGreaterThanOrEqualTo(String value) {
            addCriterion("PRSN_CHNL_TYPE_CD >=", value, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdLessThan(String value) {
            addCriterion("PRSN_CHNL_TYPE_CD <", value, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdLessThanOrEqualTo(String value) {
            addCriterion("PRSN_CHNL_TYPE_CD <=", value, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdLike(String value) {
            addCriterion("PRSN_CHNL_TYPE_CD like", value, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdNotLike(String value) {
            addCriterion("PRSN_CHNL_TYPE_CD not like", value, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdIn(List<String> values) {
            addCriterion("PRSN_CHNL_TYPE_CD in", values, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdNotIn(List<String> values) {
            addCriterion("PRSN_CHNL_TYPE_CD not in", values, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdBetween(String value1, String value2) {
            addCriterion("PRSN_CHNL_TYPE_CD between", value1, value2, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andPrsnChnlTypeCdNotBetween(String value1, String value2) {
            addCriterion("PRSN_CHNL_TYPE_CD not between", value1, value2, "prsnChnlTypeCd");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeIsNull() {
            addCriterion("DEFAULT_SERVICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeIsNotNull() {
            addCriterion("DEFAULT_SERVICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeEqualTo(String value) {
            addCriterion("DEFAULT_SERVICE_TYPE =", value, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeNotEqualTo(String value) {
            addCriterion("DEFAULT_SERVICE_TYPE <>", value, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeGreaterThan(String value) {
            addCriterion("DEFAULT_SERVICE_TYPE >", value, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DEFAULT_SERVICE_TYPE >=", value, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeLessThan(String value) {
            addCriterion("DEFAULT_SERVICE_TYPE <", value, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeLessThanOrEqualTo(String value) {
            addCriterion("DEFAULT_SERVICE_TYPE <=", value, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeLike(String value) {
            addCriterion("DEFAULT_SERVICE_TYPE like", value, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeNotLike(String value) {
            addCriterion("DEFAULT_SERVICE_TYPE not like", value, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeIn(List<String> values) {
            addCriterion("DEFAULT_SERVICE_TYPE in", values, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeNotIn(List<String> values) {
            addCriterion("DEFAULT_SERVICE_TYPE not in", values, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeBetween(String value1, String value2) {
            addCriterion("DEFAULT_SERVICE_TYPE between", value1, value2, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andDefaultServiceTypeNotBetween(String value1, String value2) {
            addCriterion("DEFAULT_SERVICE_TYPE not between", value1, value2, "defaultServiceType");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("TENANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("TENANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("TENANT_ID =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("TENANT_ID <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("TENANT_ID >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("TENANT_ID >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("TENANT_ID <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("TENANT_ID <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("TENANT_ID like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("TENANT_ID not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("TENANT_ID in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("TENANT_ID not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("TENANT_ID between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("TENANT_ID not between", value1, value2, "tenantId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}