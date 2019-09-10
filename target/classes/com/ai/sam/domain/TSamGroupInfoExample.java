package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.List;

public class TSamGroupInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamGroupInfoExample() {
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

        public Criteria andGroupIdIsNull() {
            addCriterion("GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(String value) {
            addCriterion("GROUP_ID =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(String value) {
            addCriterion("GROUP_ID <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(String value) {
            addCriterion("GROUP_ID >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_ID >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(String value) {
            addCriterion("GROUP_ID <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(String value) {
            addCriterion("GROUP_ID <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLike(String value) {
            addCriterion("GROUP_ID like", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotLike(String value) {
            addCriterion("GROUP_ID not like", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<String> values) {
            addCriterion("GROUP_ID in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<String> values) {
            addCriterion("GROUP_ID not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(String value1, String value2) {
            addCriterion("GROUP_ID between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(String value1, String value2) {
            addCriterion("GROUP_ID not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("GROUP_NAME =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("GROUP_NAME <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("GROUP_NAME >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("GROUP_NAME <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("GROUP_NAME like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("GROUP_NAME not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("GROUP_NAME in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("GROUP_NAME not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("GROUP_NAME between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("GROUP_NAME not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdIsNull() {
            addCriterion("GROUP_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdIsNotNull() {
            addCriterion("GROUP_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID =", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID <>", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdGreaterThan(String value) {
            addCriterion("GROUP_TYPE_ID >", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID >=", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdLessThan(String value) {
            addCriterion("GROUP_TYPE_ID <", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdLessThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID <=", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdLike(String value) {
            addCriterion("GROUP_TYPE_ID like", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotLike(String value) {
            addCriterion("GROUP_TYPE_ID not like", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdIn(List<String> values) {
            addCriterion("GROUP_TYPE_ID in", values, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotIn(List<String> values) {
            addCriterion("GROUP_TYPE_ID not in", values, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_ID between", value1, value2, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_ID not between", value1, value2, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIsNull() {
            addCriterion("GROUP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIsNotNull() {
            addCriterion("GROUP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andGroupCodeEqualTo(String value) {
            addCriterion("GROUP_CODE =", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotEqualTo(String value) {
            addCriterion("GROUP_CODE <>", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeGreaterThan(String value) {
            addCriterion("GROUP_CODE >", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_CODE >=", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLessThan(String value) {
            addCriterion("GROUP_CODE <", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLessThanOrEqualTo(String value) {
            addCriterion("GROUP_CODE <=", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLike(String value) {
            addCriterion("GROUP_CODE like", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotLike(String value) {
            addCriterion("GROUP_CODE not like", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIn(List<String> values) {
            addCriterion("GROUP_CODE in", values, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotIn(List<String> values) {
            addCriterion("GROUP_CODE not in", values, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeBetween(String value1, String value2) {
            addCriterion("GROUP_CODE between", value1, value2, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotBetween(String value1, String value2) {
            addCriterion("GROUP_CODE not between", value1, value2, "groupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeIsNull() {
            addCriterion("SUPER_GROUP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeIsNotNull() {
            addCriterion("SUPER_GROUP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeEqualTo(String value) {
            addCriterion("SUPER_GROUP_CODE =", value, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeNotEqualTo(String value) {
            addCriterion("SUPER_GROUP_CODE <>", value, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeGreaterThan(String value) {
            addCriterion("SUPER_GROUP_CODE >", value, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SUPER_GROUP_CODE >=", value, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeLessThan(String value) {
            addCriterion("SUPER_GROUP_CODE <", value, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeLessThanOrEqualTo(String value) {
            addCriterion("SUPER_GROUP_CODE <=", value, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeLike(String value) {
            addCriterion("SUPER_GROUP_CODE like", value, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeNotLike(String value) {
            addCriterion("SUPER_GROUP_CODE not like", value, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeIn(List<String> values) {
            addCriterion("SUPER_GROUP_CODE in", values, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeNotIn(List<String> values) {
            addCriterion("SUPER_GROUP_CODE not in", values, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeBetween(String value1, String value2) {
            addCriterion("SUPER_GROUP_CODE between", value1, value2, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andSuperGroupCodeNotBetween(String value1, String value2) {
            addCriterion("SUPER_GROUP_CODE not between", value1, value2, "superGroupCode");
            return (Criteria) this;
        }

        public Criteria andGroupDescIsNull() {
            addCriterion("GROUP_DESC is null");
            return (Criteria) this;
        }

        public Criteria andGroupDescIsNotNull() {
            addCriterion("GROUP_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andGroupDescEqualTo(String value) {
            addCriterion("GROUP_DESC =", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescNotEqualTo(String value) {
            addCriterion("GROUP_DESC <>", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescGreaterThan(String value) {
            addCriterion("GROUP_DESC >", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_DESC >=", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescLessThan(String value) {
            addCriterion("GROUP_DESC <", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescLessThanOrEqualTo(String value) {
            addCriterion("GROUP_DESC <=", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescLike(String value) {
            addCriterion("GROUP_DESC like", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescNotLike(String value) {
            addCriterion("GROUP_DESC not like", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescIn(List<String> values) {
            addCriterion("GROUP_DESC in", values, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescNotIn(List<String> values) {
            addCriterion("GROUP_DESC not in", values, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescBetween(String value1, String value2) {
            addCriterion("GROUP_DESC between", value1, value2, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescNotBetween(String value1, String value2) {
            addCriterion("GROUP_DESC not between", value1, value2, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andProvIdIsNull() {
            addCriterion("PROV_ID is null");
            return (Criteria) this;
        }

        public Criteria andProvIdIsNotNull() {
            addCriterion("PROV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProvIdEqualTo(String value) {
            addCriterion("PROV_ID =", value, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdNotEqualTo(String value) {
            addCriterion("PROV_ID <>", value, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdGreaterThan(String value) {
            addCriterion("PROV_ID >", value, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROV_ID >=", value, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdLessThan(String value) {
            addCriterion("PROV_ID <", value, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdLessThanOrEqualTo(String value) {
            addCriterion("PROV_ID <=", value, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdLike(String value) {
            addCriterion("PROV_ID like", value, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdNotLike(String value) {
            addCriterion("PROV_ID not like", value, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdIn(List<String> values) {
            addCriterion("PROV_ID in", values, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdNotIn(List<String> values) {
            addCriterion("PROV_ID not in", values, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdBetween(String value1, String value2) {
            addCriterion("PROV_ID between", value1, value2, "provId");
            return (Criteria) this;
        }

        public Criteria andProvIdNotBetween(String value1, String value2) {
            addCriterion("PROV_ID not between", value1, value2, "provId");
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