package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.List;

public class TSamPostInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamPostInfoExample() {
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

        public Criteria andPostNameIsNull() {
            addCriterion("POST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPostNameIsNotNull() {
            addCriterion("POST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPostNameEqualTo(String value) {
            addCriterion("POST_NAME =", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotEqualTo(String value) {
            addCriterion("POST_NAME <>", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameGreaterThan(String value) {
            addCriterion("POST_NAME >", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameGreaterThanOrEqualTo(String value) {
            addCriterion("POST_NAME >=", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLessThan(String value) {
            addCriterion("POST_NAME <", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLessThanOrEqualTo(String value) {
            addCriterion("POST_NAME <=", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLike(String value) {
            addCriterion("POST_NAME like", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotLike(String value) {
            addCriterion("POST_NAME not like", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameIn(List<String> values) {
            addCriterion("POST_NAME in", values, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotIn(List<String> values) {
            addCriterion("POST_NAME not in", values, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameBetween(String value1, String value2) {
            addCriterion("POST_NAME between", value1, value2, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotBetween(String value1, String value2) {
            addCriterion("POST_NAME not between", value1, value2, "postName");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNull() {
            addCriterion("POST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNotNull() {
            addCriterion("POST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostCodeEqualTo(String value) {
            addCriterion("POST_CODE =", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotEqualTo(String value) {
            addCriterion("POST_CODE <>", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThan(String value) {
            addCriterion("POST_CODE >", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThanOrEqualTo(String value) {
            addCriterion("POST_CODE >=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThan(String value) {
            addCriterion("POST_CODE <", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThanOrEqualTo(String value) {
            addCriterion("POST_CODE <=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLike(String value) {
            addCriterion("POST_CODE like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotLike(String value) {
            addCriterion("POST_CODE not like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIn(List<String> values) {
            addCriterion("POST_CODE in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotIn(List<String> values) {
            addCriterion("POST_CODE not in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeBetween(String value1, String value2) {
            addCriterion("POST_CODE between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotBetween(String value1, String value2) {
            addCriterion("POST_CODE not between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeIsNull() {
            addCriterion("SUPER_POST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeIsNotNull() {
            addCriterion("SUPER_POST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeEqualTo(String value) {
            addCriterion("SUPER_POST_CODE =", value, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeNotEqualTo(String value) {
            addCriterion("SUPER_POST_CODE <>", value, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeGreaterThan(String value) {
            addCriterion("SUPER_POST_CODE >", value, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SUPER_POST_CODE >=", value, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeLessThan(String value) {
            addCriterion("SUPER_POST_CODE <", value, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeLessThanOrEqualTo(String value) {
            addCriterion("SUPER_POST_CODE <=", value, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeLike(String value) {
            addCriterion("SUPER_POST_CODE like", value, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeNotLike(String value) {
            addCriterion("SUPER_POST_CODE not like", value, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeIn(List<String> values) {
            addCriterion("SUPER_POST_CODE in", values, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeNotIn(List<String> values) {
            addCriterion("SUPER_POST_CODE not in", values, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeBetween(String value1, String value2) {
            addCriterion("SUPER_POST_CODE between", value1, value2, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andSuperPostCodeNotBetween(String value1, String value2) {
            addCriterion("SUPER_POST_CODE not between", value1, value2, "superPostCode");
            return (Criteria) this;
        }

        public Criteria andPostStateIsNull() {
            addCriterion("POST_STATE is null");
            return (Criteria) this;
        }

        public Criteria andPostStateIsNotNull() {
            addCriterion("POST_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andPostStateEqualTo(String value) {
            addCriterion("POST_STATE =", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateNotEqualTo(String value) {
            addCriterion("POST_STATE <>", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateGreaterThan(String value) {
            addCriterion("POST_STATE >", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateGreaterThanOrEqualTo(String value) {
            addCriterion("POST_STATE >=", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateLessThan(String value) {
            addCriterion("POST_STATE <", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateLessThanOrEqualTo(String value) {
            addCriterion("POST_STATE <=", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateLike(String value) {
            addCriterion("POST_STATE like", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateNotLike(String value) {
            addCriterion("POST_STATE not like", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateIn(List<String> values) {
            addCriterion("POST_STATE in", values, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateNotIn(List<String> values) {
            addCriterion("POST_STATE not in", values, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateBetween(String value1, String value2) {
            addCriterion("POST_STATE between", value1, value2, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateNotBetween(String value1, String value2) {
            addCriterion("POST_STATE not between", value1, value2, "postState");
            return (Criteria) this;
        }

        public Criteria andPostDescIsNull() {
            addCriterion("POST_DESC is null");
            return (Criteria) this;
        }

        public Criteria andPostDescIsNotNull() {
            addCriterion("POST_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andPostDescEqualTo(String value) {
            addCriterion("POST_DESC =", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescNotEqualTo(String value) {
            addCriterion("POST_DESC <>", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescGreaterThan(String value) {
            addCriterion("POST_DESC >", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescGreaterThanOrEqualTo(String value) {
            addCriterion("POST_DESC >=", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescLessThan(String value) {
            addCriterion("POST_DESC <", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescLessThanOrEqualTo(String value) {
            addCriterion("POST_DESC <=", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescLike(String value) {
            addCriterion("POST_DESC like", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescNotLike(String value) {
            addCriterion("POST_DESC not like", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescIn(List<String> values) {
            addCriterion("POST_DESC in", values, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescNotIn(List<String> values) {
            addCriterion("POST_DESC not in", values, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescBetween(String value1, String value2) {
            addCriterion("POST_DESC between", value1, value2, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescNotBetween(String value1, String value2) {
            addCriterion("POST_DESC not between", value1, value2, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescIdIsNull() {
            addCriterion("POST_DESC_ID is null");
            return (Criteria) this;
        }

        public Criteria andPostDescIdIsNotNull() {
            addCriterion("POST_DESC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPostDescIdEqualTo(String value) {
            addCriterion("POST_DESC_ID =", value, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdNotEqualTo(String value) {
            addCriterion("POST_DESC_ID <>", value, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdGreaterThan(String value) {
            addCriterion("POST_DESC_ID >", value, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdGreaterThanOrEqualTo(String value) {
            addCriterion("POST_DESC_ID >=", value, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdLessThan(String value) {
            addCriterion("POST_DESC_ID <", value, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdLessThanOrEqualTo(String value) {
            addCriterion("POST_DESC_ID <=", value, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdLike(String value) {
            addCriterion("POST_DESC_ID like", value, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdNotLike(String value) {
            addCriterion("POST_DESC_ID not like", value, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdIn(List<String> values) {
            addCriterion("POST_DESC_ID in", values, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdNotIn(List<String> values) {
            addCriterion("POST_DESC_ID not in", values, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdBetween(String value1, String value2) {
            addCriterion("POST_DESC_ID between", value1, value2, "postDescId");
            return (Criteria) this;
        }

        public Criteria andPostDescIdNotBetween(String value1, String value2) {
            addCriterion("POST_DESC_ID not between", value1, value2, "postDescId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdIsNull() {
            addCriterion("DUTY_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdIsNotNull() {
            addCriterion("DUTY_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdEqualTo(String value) {
            addCriterion("DUTY_LEVEL_ID =", value, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdNotEqualTo(String value) {
            addCriterion("DUTY_LEVEL_ID <>", value, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdGreaterThan(String value) {
            addCriterion("DUTY_LEVEL_ID >", value, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("DUTY_LEVEL_ID >=", value, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdLessThan(String value) {
            addCriterion("DUTY_LEVEL_ID <", value, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdLessThanOrEqualTo(String value) {
            addCriterion("DUTY_LEVEL_ID <=", value, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdLike(String value) {
            addCriterion("DUTY_LEVEL_ID like", value, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdNotLike(String value) {
            addCriterion("DUTY_LEVEL_ID not like", value, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdIn(List<String> values) {
            addCriterion("DUTY_LEVEL_ID in", values, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdNotIn(List<String> values) {
            addCriterion("DUTY_LEVEL_ID not in", values, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdBetween(String value1, String value2) {
            addCriterion("DUTY_LEVEL_ID between", value1, value2, "dutyLevelId");
            return (Criteria) this;
        }

        public Criteria andDutyLevelIdNotBetween(String value1, String value2) {
            addCriterion("DUTY_LEVEL_ID not between", value1, value2, "dutyLevelId");
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