package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.List;

public class TSamMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamMenuExample() {
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

        public Criteria andMenuIdIsNull() {
            addCriterion("MENU_ID is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("MENU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(String value) {
            addCriterion("MENU_ID =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(String value) {
            addCriterion("MENU_ID <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(String value) {
            addCriterion("MENU_ID >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_ID >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(String value) {
            addCriterion("MENU_ID <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(String value) {
            addCriterion("MENU_ID <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLike(String value) {
            addCriterion("MENU_ID like", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotLike(String value) {
            addCriterion("MENU_ID not like", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<String> values) {
            addCriterion("MENU_ID in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<String> values) {
            addCriterion("MENU_ID not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(String value1, String value2) {
            addCriterion("MENU_ID between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(String value1, String value2) {
            addCriterion("MENU_ID not between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("PARENT_ID like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("PARENT_ID not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
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

        public Criteria andImageUrlIsNull() {
            addCriterion("IMAGE_URL is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("IMAGE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("IMAGE_URL =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("IMAGE_URL <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("IMAGE_URL >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("IMAGE_URL >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("IMAGE_URL <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("IMAGE_URL <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("IMAGE_URL like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("IMAGE_URL not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("IMAGE_URL in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("IMAGE_URL not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("IMAGE_URL between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("IMAGE_URL not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNull() {
            addCriterion("MENU_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNotNull() {
            addCriterion("MENU_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNameEqualTo(String value) {
            addCriterion("MENU_NAME =", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotEqualTo(String value) {
            addCriterion("MENU_NAME <>", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThan(String value) {
            addCriterion("MENU_NAME >", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_NAME >=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThan(String value) {
            addCriterion("MENU_NAME <", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThanOrEqualTo(String value) {
            addCriterion("MENU_NAME <=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLike(String value) {
            addCriterion("MENU_NAME like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotLike(String value) {
            addCriterion("MENU_NAME not like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameIn(List<String> values) {
            addCriterion("MENU_NAME in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotIn(List<String> values) {
            addCriterion("MENU_NAME not in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameBetween(String value1, String value2) {
            addCriterion("MENU_NAME between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotBetween(String value1, String value2) {
            addCriterion("MENU_NAME not between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionIsNull() {
            addCriterion("MENU_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionIsNotNull() {
            addCriterion("MENU_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionEqualTo(String value) {
            addCriterion("MENU_DESCRIPTION =", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionNotEqualTo(String value) {
            addCriterion("MENU_DESCRIPTION <>", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionGreaterThan(String value) {
            addCriterion("MENU_DESCRIPTION >", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_DESCRIPTION >=", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionLessThan(String value) {
            addCriterion("MENU_DESCRIPTION <", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionLessThanOrEqualTo(String value) {
            addCriterion("MENU_DESCRIPTION <=", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionLike(String value) {
            addCriterion("MENU_DESCRIPTION like", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionNotLike(String value) {
            addCriterion("MENU_DESCRIPTION not like", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionIn(List<String> values) {
            addCriterion("MENU_DESCRIPTION in", values, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionNotIn(List<String> values) {
            addCriterion("MENU_DESCRIPTION not in", values, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionBetween(String value1, String value2) {
            addCriterion("MENU_DESCRIPTION between", value1, value2, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionNotBetween(String value1, String value2) {
            addCriterion("MENU_DESCRIPTION not between", value1, value2, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuUrlIsNull() {
            addCriterion("MENU_URL is null");
            return (Criteria) this;
        }

        public Criteria andMenuUrlIsNotNull() {
            addCriterion("MENU_URL is not null");
            return (Criteria) this;
        }

        public Criteria andMenuUrlEqualTo(String value) {
            addCriterion("MENU_URL =", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlNotEqualTo(String value) {
            addCriterion("MENU_URL <>", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlGreaterThan(String value) {
            addCriterion("MENU_URL >", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_URL >=", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlLessThan(String value) {
            addCriterion("MENU_URL <", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlLessThanOrEqualTo(String value) {
            addCriterion("MENU_URL <=", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlLike(String value) {
            addCriterion("MENU_URL like", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlNotLike(String value) {
            addCriterion("MENU_URL not like", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlIn(List<String> values) {
            addCriterion("MENU_URL in", values, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlNotIn(List<String> values) {
            addCriterion("MENU_URL not in", values, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlBetween(String value1, String value2) {
            addCriterion("MENU_URL between", value1, value2, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlNotBetween(String value1, String value2) {
            addCriterion("MENU_URL not between", value1, value2, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andDisplayNoIsNull() {
            addCriterion("DISPLAY_NO is null");
            return (Criteria) this;
        }

        public Criteria andDisplayNoIsNotNull() {
            addCriterion("DISPLAY_NO is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayNoEqualTo(Short value) {
            addCriterion("DISPLAY_NO =", value, "displayNo");
            return (Criteria) this;
        }

        public Criteria andDisplayNoNotEqualTo(Short value) {
            addCriterion("DISPLAY_NO <>", value, "displayNo");
            return (Criteria) this;
        }

        public Criteria andDisplayNoGreaterThan(Short value) {
            addCriterion("DISPLAY_NO >", value, "displayNo");
            return (Criteria) this;
        }

        public Criteria andDisplayNoGreaterThanOrEqualTo(Short value) {
            addCriterion("DISPLAY_NO >=", value, "displayNo");
            return (Criteria) this;
        }

        public Criteria andDisplayNoLessThan(Short value) {
            addCriterion("DISPLAY_NO <", value, "displayNo");
            return (Criteria) this;
        }

        public Criteria andDisplayNoLessThanOrEqualTo(Short value) {
            addCriterion("DISPLAY_NO <=", value, "displayNo");
            return (Criteria) this;
        }

        public Criteria andDisplayNoIn(List<Short> values) {
            addCriterion("DISPLAY_NO in", values, "displayNo");
            return (Criteria) this;
        }

        public Criteria andDisplayNoNotIn(List<Short> values) {
            addCriterion("DISPLAY_NO not in", values, "displayNo");
            return (Criteria) this;
        }

        public Criteria andDisplayNoBetween(Short value1, Short value2) {
            addCriterion("DISPLAY_NO between", value1, value2, "displayNo");
            return (Criteria) this;
        }

        public Criteria andDisplayNoNotBetween(Short value1, Short value2) {
            addCriterion("DISPLAY_NO not between", value1, value2, "displayNo");
            return (Criteria) this;
        }

        public Criteria andOpenModuleIsNull() {
            addCriterion("OPEN_MODULE is null");
            return (Criteria) this;
        }

        public Criteria andOpenModuleIsNotNull() {
            addCriterion("OPEN_MODULE is not null");
            return (Criteria) this;
        }

        public Criteria andOpenModuleEqualTo(String value) {
            addCriterion("OPEN_MODULE =", value, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleNotEqualTo(String value) {
            addCriterion("OPEN_MODULE <>", value, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleGreaterThan(String value) {
            addCriterion("OPEN_MODULE >", value, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleGreaterThanOrEqualTo(String value) {
            addCriterion("OPEN_MODULE >=", value, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleLessThan(String value) {
            addCriterion("OPEN_MODULE <", value, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleLessThanOrEqualTo(String value) {
            addCriterion("OPEN_MODULE <=", value, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleLike(String value) {
            addCriterion("OPEN_MODULE like", value, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleNotLike(String value) {
            addCriterion("OPEN_MODULE not like", value, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleIn(List<String> values) {
            addCriterion("OPEN_MODULE in", values, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleNotIn(List<String> values) {
            addCriterion("OPEN_MODULE not in", values, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleBetween(String value1, String value2) {
            addCriterion("OPEN_MODULE between", value1, value2, "openModule");
            return (Criteria) this;
        }

        public Criteria andOpenModuleNotBetween(String value1, String value2) {
            addCriterion("OPEN_MODULE not between", value1, value2, "openModule");
            return (Criteria) this;
        }

        public Criteria andClickTotalIsNull() {
            addCriterion("CLICK_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andClickTotalIsNotNull() {
            addCriterion("CLICK_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andClickTotalEqualTo(Long value) {
            addCriterion("CLICK_TOTAL =", value, "clickTotal");
            return (Criteria) this;
        }

        public Criteria andClickTotalNotEqualTo(Long value) {
            addCriterion("CLICK_TOTAL <>", value, "clickTotal");
            return (Criteria) this;
        }

        public Criteria andClickTotalGreaterThan(Long value) {
            addCriterion("CLICK_TOTAL >", value, "clickTotal");
            return (Criteria) this;
        }

        public Criteria andClickTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("CLICK_TOTAL >=", value, "clickTotal");
            return (Criteria) this;
        }

        public Criteria andClickTotalLessThan(Long value) {
            addCriterion("CLICK_TOTAL <", value, "clickTotal");
            return (Criteria) this;
        }

        public Criteria andClickTotalLessThanOrEqualTo(Long value) {
            addCriterion("CLICK_TOTAL <=", value, "clickTotal");
            return (Criteria) this;
        }

        public Criteria andClickTotalIn(List<Long> values) {
            addCriterion("CLICK_TOTAL in", values, "clickTotal");
            return (Criteria) this;
        }

        public Criteria andClickTotalNotIn(List<Long> values) {
            addCriterion("CLICK_TOTAL not in", values, "clickTotal");
            return (Criteria) this;
        }

        public Criteria andClickTotalBetween(Long value1, Long value2) {
            addCriterion("CLICK_TOTAL between", value1, value2, "clickTotal");
            return (Criteria) this;
        }

        public Criteria andClickTotalNotBetween(Long value1, Long value2) {
            addCriterion("CLICK_TOTAL not between", value1, value2, "clickTotal");
            return (Criteria) this;
        }

        public Criteria andAbbreviationIsNull() {
            addCriterion("ABBREVIATION is null");
            return (Criteria) this;
        }

        public Criteria andAbbreviationIsNotNull() {
            addCriterion("ABBREVIATION is not null");
            return (Criteria) this;
        }

        public Criteria andAbbreviationEqualTo(String value) {
            addCriterion("ABBREVIATION =", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationNotEqualTo(String value) {
            addCriterion("ABBREVIATION <>", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationGreaterThan(String value) {
            addCriterion("ABBREVIATION >", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationGreaterThanOrEqualTo(String value) {
            addCriterion("ABBREVIATION >=", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationLessThan(String value) {
            addCriterion("ABBREVIATION <", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationLessThanOrEqualTo(String value) {
            addCriterion("ABBREVIATION <=", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationLike(String value) {
            addCriterion("ABBREVIATION like", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationNotLike(String value) {
            addCriterion("ABBREVIATION not like", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationIn(List<String> values) {
            addCriterion("ABBREVIATION in", values, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationNotIn(List<String> values) {
            addCriterion("ABBREVIATION not in", values, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationBetween(String value1, String value2) {
            addCriterion("ABBREVIATION between", value1, value2, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationNotBetween(String value1, String value2) {
            addCriterion("ABBREVIATION not between", value1, value2, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeIsNull() {
            addCriterion("NESTED_SYS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeIsNotNull() {
            addCriterion("NESTED_SYS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeEqualTo(String value) {
            addCriterion("NESTED_SYS_TYPE =", value, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeNotEqualTo(String value) {
            addCriterion("NESTED_SYS_TYPE <>", value, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeGreaterThan(String value) {
            addCriterion("NESTED_SYS_TYPE >", value, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeGreaterThanOrEqualTo(String value) {
            addCriterion("NESTED_SYS_TYPE >=", value, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeLessThan(String value) {
            addCriterion("NESTED_SYS_TYPE <", value, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeLessThanOrEqualTo(String value) {
            addCriterion("NESTED_SYS_TYPE <=", value, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeLike(String value) {
            addCriterion("NESTED_SYS_TYPE like", value, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeNotLike(String value) {
            addCriterion("NESTED_SYS_TYPE not like", value, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeIn(List<String> values) {
            addCriterion("NESTED_SYS_TYPE in", values, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeNotIn(List<String> values) {
            addCriterion("NESTED_SYS_TYPE not in", values, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeBetween(String value1, String value2) {
            addCriterion("NESTED_SYS_TYPE between", value1, value2, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andNestedSysTypeNotBetween(String value1, String value2) {
            addCriterion("NESTED_SYS_TYPE not between", value1, value2, "nestedSysType");
            return (Criteria) this;
        }

        public Criteria andMenuWideIsNull() {
            addCriterion("MENU_WIDE is null");
            return (Criteria) this;
        }

        public Criteria andMenuWideIsNotNull() {
            addCriterion("MENU_WIDE is not null");
            return (Criteria) this;
        }

        public Criteria andMenuWideEqualTo(String value) {
            addCriterion("MENU_WIDE =", value, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideNotEqualTo(String value) {
            addCriterion("MENU_WIDE <>", value, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideGreaterThan(String value) {
            addCriterion("MENU_WIDE >", value, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_WIDE >=", value, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideLessThan(String value) {
            addCriterion("MENU_WIDE <", value, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideLessThanOrEqualTo(String value) {
            addCriterion("MENU_WIDE <=", value, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideLike(String value) {
            addCriterion("MENU_WIDE like", value, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideNotLike(String value) {
            addCriterion("MENU_WIDE not like", value, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideIn(List<String> values) {
            addCriterion("MENU_WIDE in", values, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideNotIn(List<String> values) {
            addCriterion("MENU_WIDE not in", values, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideBetween(String value1, String value2) {
            addCriterion("MENU_WIDE between", value1, value2, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuWideNotBetween(String value1, String value2) {
            addCriterion("MENU_WIDE not between", value1, value2, "menuWide");
            return (Criteria) this;
        }

        public Criteria andMenuHighIsNull() {
            addCriterion("MENU_HIGH is null");
            return (Criteria) this;
        }

        public Criteria andMenuHighIsNotNull() {
            addCriterion("MENU_HIGH is not null");
            return (Criteria) this;
        }

        public Criteria andMenuHighEqualTo(String value) {
            addCriterion("MENU_HIGH =", value, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighNotEqualTo(String value) {
            addCriterion("MENU_HIGH <>", value, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighGreaterThan(String value) {
            addCriterion("MENU_HIGH >", value, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_HIGH >=", value, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighLessThan(String value) {
            addCriterion("MENU_HIGH <", value, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighLessThanOrEqualTo(String value) {
            addCriterion("MENU_HIGH <=", value, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighLike(String value) {
            addCriterion("MENU_HIGH like", value, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighNotLike(String value) {
            addCriterion("MENU_HIGH not like", value, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighIn(List<String> values) {
            addCriterion("MENU_HIGH in", values, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighNotIn(List<String> values) {
            addCriterion("MENU_HIGH not in", values, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighBetween(String value1, String value2) {
            addCriterion("MENU_HIGH between", value1, value2, "menuHigh");
            return (Criteria) this;
        }

        public Criteria andMenuHighNotBetween(String value1, String value2) {
            addCriterion("MENU_HIGH not between", value1, value2, "menuHigh");
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