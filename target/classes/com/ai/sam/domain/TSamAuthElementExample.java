package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.List;

public class TSamAuthElementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamAuthElementExample() {
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

        public Criteria andElementIdIsNull() {
            addCriterion("ELEMENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andElementIdIsNotNull() {
            addCriterion("ELEMENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andElementIdEqualTo(String value) {
            addCriterion("ELEMENT_ID =", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotEqualTo(String value) {
            addCriterion("ELEMENT_ID <>", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThan(String value) {
            addCriterion("ELEMENT_ID >", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_ID >=", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLessThan(String value) {
            addCriterion("ELEMENT_ID <", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_ID <=", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLike(String value) {
            addCriterion("ELEMENT_ID like", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotLike(String value) {
            addCriterion("ELEMENT_ID not like", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdIn(List<String> values) {
            addCriterion("ELEMENT_ID in", values, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotIn(List<String> values) {
            addCriterion("ELEMENT_ID not in", values, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdBetween(String value1, String value2) {
            addCriterion("ELEMENT_ID between", value1, value2, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_ID not between", value1, value2, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementNameIsNull() {
            addCriterion("ELEMENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andElementNameIsNotNull() {
            addCriterion("ELEMENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andElementNameEqualTo(String value) {
            addCriterion("ELEMENT_NAME =", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotEqualTo(String value) {
            addCriterion("ELEMENT_NAME <>", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThan(String value) {
            addCriterion("ELEMENT_NAME >", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_NAME >=", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLessThan(String value) {
            addCriterion("ELEMENT_NAME <", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_NAME <=", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLike(String value) {
            addCriterion("ELEMENT_NAME like", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotLike(String value) {
            addCriterion("ELEMENT_NAME not like", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameIn(List<String> values) {
            addCriterion("ELEMENT_NAME in", values, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotIn(List<String> values) {
            addCriterion("ELEMENT_NAME not in", values, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameBetween(String value1, String value2) {
            addCriterion("ELEMENT_NAME between", value1, value2, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_NAME not between", value1, value2, "elementName");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNull() {
            addCriterion("MODULE_ID is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("MODULE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(String value) {
            addCriterion("MODULE_ID =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(String value) {
            addCriterion("MODULE_ID <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(String value) {
            addCriterion("MODULE_ID >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_ID >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(String value) {
            addCriterion("MODULE_ID <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(String value) {
            addCriterion("MODULE_ID <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLike(String value) {
            addCriterion("MODULE_ID like", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotLike(String value) {
            addCriterion("MODULE_ID not like", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<String> values) {
            addCriterion("MODULE_ID in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<String> values) {
            addCriterion("MODULE_ID not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(String value1, String value2) {
            addCriterion("MODULE_ID between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(String value1, String value2) {
            addCriterion("MODULE_ID not between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andElementCodeIsNull() {
            addCriterion("ELEMENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andElementCodeIsNotNull() {
            addCriterion("ELEMENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andElementCodeEqualTo(String value) {
            addCriterion("ELEMENT_CODE =", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotEqualTo(String value) {
            addCriterion("ELEMENT_CODE <>", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeGreaterThan(String value) {
            addCriterion("ELEMENT_CODE >", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_CODE >=", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeLessThan(String value) {
            addCriterion("ELEMENT_CODE <", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_CODE <=", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeLike(String value) {
            addCriterion("ELEMENT_CODE like", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotLike(String value) {
            addCriterion("ELEMENT_CODE not like", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeIn(List<String> values) {
            addCriterion("ELEMENT_CODE in", values, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotIn(List<String> values) {
            addCriterion("ELEMENT_CODE not in", values, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeBetween(String value1, String value2) {
            addCriterion("ELEMENT_CODE between", value1, value2, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_CODE not between", value1, value2, "elementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeIsNull() {
            addCriterion("SUPER_ELEMENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeIsNotNull() {
            addCriterion("SUPER_ELEMENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeEqualTo(String value) {
            addCriterion("SUPER_ELEMENT_CODE =", value, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeNotEqualTo(String value) {
            addCriterion("SUPER_ELEMENT_CODE <>", value, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeGreaterThan(String value) {
            addCriterion("SUPER_ELEMENT_CODE >", value, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SUPER_ELEMENT_CODE >=", value, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeLessThan(String value) {
            addCriterion("SUPER_ELEMENT_CODE <", value, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeLessThanOrEqualTo(String value) {
            addCriterion("SUPER_ELEMENT_CODE <=", value, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeLike(String value) {
            addCriterion("SUPER_ELEMENT_CODE like", value, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeNotLike(String value) {
            addCriterion("SUPER_ELEMENT_CODE not like", value, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeIn(List<String> values) {
            addCriterion("SUPER_ELEMENT_CODE in", values, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeNotIn(List<String> values) {
            addCriterion("SUPER_ELEMENT_CODE not in", values, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeBetween(String value1, String value2) {
            addCriterion("SUPER_ELEMENT_CODE between", value1, value2, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andSuperElementCodeNotBetween(String value1, String value2) {
            addCriterion("SUPER_ELEMENT_CODE not between", value1, value2, "superElementCode");
            return (Criteria) this;
        }

        public Criteria andMenuFlagIsNull() {
            addCriterion("MENU_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andMenuFlagIsNotNull() {
            addCriterion("MENU_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andMenuFlagEqualTo(String value) {
            addCriterion("MENU_FLAG =", value, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagNotEqualTo(String value) {
            addCriterion("MENU_FLAG <>", value, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagGreaterThan(String value) {
            addCriterion("MENU_FLAG >", value, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_FLAG >=", value, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagLessThan(String value) {
            addCriterion("MENU_FLAG <", value, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagLessThanOrEqualTo(String value) {
            addCriterion("MENU_FLAG <=", value, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagLike(String value) {
            addCriterion("MENU_FLAG like", value, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagNotLike(String value) {
            addCriterion("MENU_FLAG not like", value, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagIn(List<String> values) {
            addCriterion("MENU_FLAG in", values, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagNotIn(List<String> values) {
            addCriterion("MENU_FLAG not in", values, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagBetween(String value1, String value2) {
            addCriterion("MENU_FLAG between", value1, value2, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andMenuFlagNotBetween(String value1, String value2) {
            addCriterion("MENU_FLAG not between", value1, value2, "menuFlag");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andElementUrlIsNull() {
            addCriterion("ELEMENT_URL is null");
            return (Criteria) this;
        }

        public Criteria andElementUrlIsNotNull() {
            addCriterion("ELEMENT_URL is not null");
            return (Criteria) this;
        }

        public Criteria andElementUrlEqualTo(String value) {
            addCriterion("ELEMENT_URL =", value, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlNotEqualTo(String value) {
            addCriterion("ELEMENT_URL <>", value, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlGreaterThan(String value) {
            addCriterion("ELEMENT_URL >", value, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_URL >=", value, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlLessThan(String value) {
            addCriterion("ELEMENT_URL <", value, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_URL <=", value, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlLike(String value) {
            addCriterion("ELEMENT_URL like", value, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlNotLike(String value) {
            addCriterion("ELEMENT_URL not like", value, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlIn(List<String> values) {
            addCriterion("ELEMENT_URL in", values, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlNotIn(List<String> values) {
            addCriterion("ELEMENT_URL not in", values, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlBetween(String value1, String value2) {
            addCriterion("ELEMENT_URL between", value1, value2, "elementUrl");
            return (Criteria) this;
        }

        public Criteria andElementUrlNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_URL not between", value1, value2, "elementUrl");
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