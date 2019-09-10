package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSamOrgaInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamOrgaInfoExample() {
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

        public Criteria andOrgaNameIsNull() {
            addCriterion("ORGA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgaNameIsNotNull() {
            addCriterion("ORGA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgaNameEqualTo(String value) {
            addCriterion("ORGA_NAME =", value, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameNotEqualTo(String value) {
            addCriterion("ORGA_NAME <>", value, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameGreaterThan(String value) {
            addCriterion("ORGA_NAME >", value, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORGA_NAME >=", value, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameLessThan(String value) {
            addCriterion("ORGA_NAME <", value, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameLessThanOrEqualTo(String value) {
            addCriterion("ORGA_NAME <=", value, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameLike(String value) {
            addCriterion("ORGA_NAME like", value, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameNotLike(String value) {
            addCriterion("ORGA_NAME not like", value, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameIn(List<String> values) {
            addCriterion("ORGA_NAME in", values, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameNotIn(List<String> values) {
            addCriterion("ORGA_NAME not in", values, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameBetween(String value1, String value2) {
            addCriterion("ORGA_NAME between", value1, value2, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaNameNotBetween(String value1, String value2) {
            addCriterion("ORGA_NAME not between", value1, value2, "orgaName");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdIsNull() {
            addCriterion("ORGA_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdIsNotNull() {
            addCriterion("ORGA_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdEqualTo(String value) {
            addCriterion("ORGA_TYPE_ID =", value, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdNotEqualTo(String value) {
            addCriterion("ORGA_TYPE_ID <>", value, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdGreaterThan(String value) {
            addCriterion("ORGA_TYPE_ID >", value, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORGA_TYPE_ID >=", value, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdLessThan(String value) {
            addCriterion("ORGA_TYPE_ID <", value, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ORGA_TYPE_ID <=", value, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdLike(String value) {
            addCriterion("ORGA_TYPE_ID like", value, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdNotLike(String value) {
            addCriterion("ORGA_TYPE_ID not like", value, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdIn(List<String> values) {
            addCriterion("ORGA_TYPE_ID in", values, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdNotIn(List<String> values) {
            addCriterion("ORGA_TYPE_ID not in", values, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdBetween(String value1, String value2) {
            addCriterion("ORGA_TYPE_ID between", value1, value2, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaTypeIdNotBetween(String value1, String value2) {
            addCriterion("ORGA_TYPE_ID not between", value1, value2, "orgaTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgaStateIsNull() {
            addCriterion("ORGA_STATE is null");
            return (Criteria) this;
        }

        public Criteria andOrgaStateIsNotNull() {
            addCriterion("ORGA_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgaStateEqualTo(String value) {
            addCriterion("ORGA_STATE =", value, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateNotEqualTo(String value) {
            addCriterion("ORGA_STATE <>", value, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateGreaterThan(String value) {
            addCriterion("ORGA_STATE >", value, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateGreaterThanOrEqualTo(String value) {
            addCriterion("ORGA_STATE >=", value, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateLessThan(String value) {
            addCriterion("ORGA_STATE <", value, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateLessThanOrEqualTo(String value) {
            addCriterion("ORGA_STATE <=", value, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateLike(String value) {
            addCriterion("ORGA_STATE like", value, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateNotLike(String value) {
            addCriterion("ORGA_STATE not like", value, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateIn(List<String> values) {
            addCriterion("ORGA_STATE in", values, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateNotIn(List<String> values) {
            addCriterion("ORGA_STATE not in", values, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateBetween(String value1, String value2) {
            addCriterion("ORGA_STATE between", value1, value2, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaStateNotBetween(String value1, String value2) {
            addCriterion("ORGA_STATE not between", value1, value2, "orgaState");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeIsNull() {
            addCriterion("ORGA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeIsNotNull() {
            addCriterion("ORGA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeEqualTo(String value) {
            addCriterion("ORGA_CODE =", value, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeNotEqualTo(String value) {
            addCriterion("ORGA_CODE <>", value, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeGreaterThan(String value) {
            addCriterion("ORGA_CODE >", value, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORGA_CODE >=", value, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeLessThan(String value) {
            addCriterion("ORGA_CODE <", value, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeLessThanOrEqualTo(String value) {
            addCriterion("ORGA_CODE <=", value, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeLike(String value) {
            addCriterion("ORGA_CODE like", value, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeNotLike(String value) {
            addCriterion("ORGA_CODE not like", value, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeIn(List<String> values) {
            addCriterion("ORGA_CODE in", values, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeNotIn(List<String> values) {
            addCriterion("ORGA_CODE not in", values, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeBetween(String value1, String value2) {
            addCriterion("ORGA_CODE between", value1, value2, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaCodeNotBetween(String value1, String value2) {
            addCriterion("ORGA_CODE not between", value1, value2, "orgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeIsNull() {
            addCriterion("SUPER_ORGA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeIsNotNull() {
            addCriterion("SUPER_ORGA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeEqualTo(String value) {
            addCriterion("SUPER_ORGA_CODE =", value, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeNotEqualTo(String value) {
            addCriterion("SUPER_ORGA_CODE <>", value, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeGreaterThan(String value) {
            addCriterion("SUPER_ORGA_CODE >", value, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SUPER_ORGA_CODE >=", value, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeLessThan(String value) {
            addCriterion("SUPER_ORGA_CODE <", value, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeLessThanOrEqualTo(String value) {
            addCriterion("SUPER_ORGA_CODE <=", value, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeLike(String value) {
            addCriterion("SUPER_ORGA_CODE like", value, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeNotLike(String value) {
            addCriterion("SUPER_ORGA_CODE not like", value, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeIn(List<String> values) {
            addCriterion("SUPER_ORGA_CODE in", values, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeNotIn(List<String> values) {
            addCriterion("SUPER_ORGA_CODE not in", values, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeBetween(String value1, String value2) {
            addCriterion("SUPER_ORGA_CODE between", value1, value2, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andSuperOrgaCodeNotBetween(String value1, String value2) {
            addCriterion("SUPER_ORGA_CODE not between", value1, value2, "superOrgaCode");
            return (Criteria) this;
        }

        public Criteria andOrgaDescIsNull() {
            addCriterion("ORGA_DESC is null");
            return (Criteria) this;
        }

        public Criteria andOrgaDescIsNotNull() {
            addCriterion("ORGA_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andOrgaDescEqualTo(String value) {
            addCriterion("ORGA_DESC =", value, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescNotEqualTo(String value) {
            addCriterion("ORGA_DESC <>", value, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescGreaterThan(String value) {
            addCriterion("ORGA_DESC >", value, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescGreaterThanOrEqualTo(String value) {
            addCriterion("ORGA_DESC >=", value, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescLessThan(String value) {
            addCriterion("ORGA_DESC <", value, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescLessThanOrEqualTo(String value) {
            addCriterion("ORGA_DESC <=", value, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescLike(String value) {
            addCriterion("ORGA_DESC like", value, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescNotLike(String value) {
            addCriterion("ORGA_DESC not like", value, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescIn(List<String> values) {
            addCriterion("ORGA_DESC in", values, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescNotIn(List<String> values) {
            addCriterion("ORGA_DESC not in", values, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescBetween(String value1, String value2) {
            addCriterion("ORGA_DESC between", value1, value2, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andOrgaDescNotBetween(String value1, String value2) {
            addCriterion("ORGA_DESC not between", value1, value2, "orgaDesc");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
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