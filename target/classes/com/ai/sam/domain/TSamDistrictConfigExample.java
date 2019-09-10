package com.ai.sam.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSamDistrictConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSamDistrictConfigExample() {
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

        public Criteria andRegnIdIsNull() {
            addCriterion("REGN_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegnIdIsNotNull() {
            addCriterion("REGN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegnIdEqualTo(String value) {
            addCriterion("REGN_ID =", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdNotEqualTo(String value) {
            addCriterion("REGN_ID <>", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdGreaterThan(String value) {
            addCriterion("REGN_ID >", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdGreaterThanOrEqualTo(String value) {
            addCriterion("REGN_ID >=", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdLessThan(String value) {
            addCriterion("REGN_ID <", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdLessThanOrEqualTo(String value) {
            addCriterion("REGN_ID <=", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdLike(String value) {
            addCriterion("REGN_ID like", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdNotLike(String value) {
            addCriterion("REGN_ID not like", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdIn(List<String> values) {
            addCriterion("REGN_ID in", values, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdNotIn(List<String> values) {
            addCriterion("REGN_ID not in", values, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdBetween(String value1, String value2) {
            addCriterion("REGN_ID between", value1, value2, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdNotBetween(String value1, String value2) {
            addCriterion("REGN_ID not between", value1, value2, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnNmIsNull() {
            addCriterion("REGN_NM is null");
            return (Criteria) this;
        }

        public Criteria andRegnNmIsNotNull() {
            addCriterion("REGN_NM is not null");
            return (Criteria) this;
        }

        public Criteria andRegnNmEqualTo(String value) {
            addCriterion("REGN_NM =", value, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmNotEqualTo(String value) {
            addCriterion("REGN_NM <>", value, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmGreaterThan(String value) {
            addCriterion("REGN_NM >", value, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmGreaterThanOrEqualTo(String value) {
            addCriterion("REGN_NM >=", value, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmLessThan(String value) {
            addCriterion("REGN_NM <", value, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmLessThanOrEqualTo(String value) {
            addCriterion("REGN_NM <=", value, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmLike(String value) {
            addCriterion("REGN_NM like", value, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmNotLike(String value) {
            addCriterion("REGN_NM not like", value, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmIn(List<String> values) {
            addCriterion("REGN_NM in", values, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmNotIn(List<String> values) {
            addCriterion("REGN_NM not in", values, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmBetween(String value1, String value2) {
            addCriterion("REGN_NM between", value1, value2, "regnNm");
            return (Criteria) this;
        }

        public Criteria andRegnNmNotBetween(String value1, String value2) {
            addCriterion("REGN_NM not between", value1, value2, "regnNm");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNull() {
            addCriterion("AREA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("AREA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("AREA_CODE =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("AREA_CODE <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("AREA_CODE >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_CODE >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("AREA_CODE <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("AREA_CODE <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("AREA_CODE like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("AREA_CODE not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("AREA_CODE in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("AREA_CODE not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("AREA_CODE between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("AREA_CODE not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdIsNull() {
            addCriterion("SUPR_REGN_ID is null");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdIsNotNull() {
            addCriterion("SUPR_REGN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdEqualTo(String value) {
            addCriterion("SUPR_REGN_ID =", value, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdNotEqualTo(String value) {
            addCriterion("SUPR_REGN_ID <>", value, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdGreaterThan(String value) {
            addCriterion("SUPR_REGN_ID >", value, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUPR_REGN_ID >=", value, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdLessThan(String value) {
            addCriterion("SUPR_REGN_ID <", value, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdLessThanOrEqualTo(String value) {
            addCriterion("SUPR_REGN_ID <=", value, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdLike(String value) {
            addCriterion("SUPR_REGN_ID like", value, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdNotLike(String value) {
            addCriterion("SUPR_REGN_ID not like", value, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdIn(List<String> values) {
            addCriterion("SUPR_REGN_ID in", values, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdNotIn(List<String> values) {
            addCriterion("SUPR_REGN_ID not in", values, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdBetween(String value1, String value2) {
            addCriterion("SUPR_REGN_ID between", value1, value2, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andSuprRegnIdNotBetween(String value1, String value2) {
            addCriterion("SUPR_REGN_ID not between", value1, value2, "suprRegnId");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoIsNull() {
            addCriterion("ARGE_SEQNO is null");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoIsNotNull() {
            addCriterion("ARGE_SEQNO is not null");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoEqualTo(Integer value) {
            addCriterion("ARGE_SEQNO =", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoNotEqualTo(Integer value) {
            addCriterion("ARGE_SEQNO <>", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoGreaterThan(Integer value) {
            addCriterion("ARGE_SEQNO >", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("ARGE_SEQNO >=", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoLessThan(Integer value) {
            addCriterion("ARGE_SEQNO <", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoLessThanOrEqualTo(Integer value) {
            addCriterion("ARGE_SEQNO <=", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoIn(List<Integer> values) {
            addCriterion("ARGE_SEQNO in", values, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoNotIn(List<Integer> values) {
            addCriterion("ARGE_SEQNO not in", values, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoBetween(Integer value1, Integer value2) {
            addCriterion("ARGE_SEQNO between", value1, value2, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoNotBetween(Integer value1, Integer value2) {
            addCriterion("ARGE_SEQNO not between", value1, value2, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNull() {
            addCriterion("CRT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNotNull() {
            addCriterion("CRT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeEqualTo(Date value) {
            addCriterion("CRT_TIME =", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotEqualTo(Date value) {
            addCriterion("CRT_TIME <>", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThan(Date value) {
            addCriterion("CRT_TIME >", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CRT_TIME >=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThan(Date value) {
            addCriterion("CRT_TIME <", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThanOrEqualTo(Date value) {
            addCriterion("CRT_TIME <=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIn(List<Date> values) {
            addCriterion("CRT_TIME in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotIn(List<Date> values) {
            addCriterion("CRT_TIME not in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeBetween(Date value1, Date value2) {
            addCriterion("CRT_TIME between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotBetween(Date value1, Date value2) {
            addCriterion("CRT_TIME not between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeIsNull() {
            addCriterion("MODF_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModfTimeIsNotNull() {
            addCriterion("MODF_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModfTimeEqualTo(Date value) {
            addCriterion("MODF_TIME =", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeNotEqualTo(Date value) {
            addCriterion("MODF_TIME <>", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeGreaterThan(Date value) {
            addCriterion("MODF_TIME >", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("MODF_TIME >=", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeLessThan(Date value) {
            addCriterion("MODF_TIME <", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeLessThanOrEqualTo(Date value) {
            addCriterion("MODF_TIME <=", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeIn(List<Date> values) {
            addCriterion("MODF_TIME in", values, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeNotIn(List<Date> values) {
            addCriterion("MODF_TIME not in", values, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeBetween(Date value1, Date value2) {
            addCriterion("MODF_TIME between", value1, value2, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeNotBetween(Date value1, Date value2) {
            addCriterion("MODF_TIME not between", value1, value2, "modfTime");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdIsNull() {
            addCriterion("OP_PRSN_ID is null");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdIsNotNull() {
            addCriterion("OP_PRSN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdEqualTo(String value) {
            addCriterion("OP_PRSN_ID =", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdNotEqualTo(String value) {
            addCriterion("OP_PRSN_ID <>", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdGreaterThan(String value) {
            addCriterion("OP_PRSN_ID >", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdGreaterThanOrEqualTo(String value) {
            addCriterion("OP_PRSN_ID >=", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdLessThan(String value) {
            addCriterion("OP_PRSN_ID <", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdLessThanOrEqualTo(String value) {
            addCriterion("OP_PRSN_ID <=", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdLike(String value) {
            addCriterion("OP_PRSN_ID like", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdNotLike(String value) {
            addCriterion("OP_PRSN_ID not like", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdIn(List<String> values) {
            addCriterion("OP_PRSN_ID in", values, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdNotIn(List<String> values) {
            addCriterion("OP_PRSN_ID not in", values, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdBetween(String value1, String value2) {
            addCriterion("OP_PRSN_ID between", value1, value2, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdNotBetween(String value1, String value2) {
            addCriterion("OP_PRSN_ID not between", value1, value2, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoIsNull() {
            addCriterion("HRCY_SEQNO is null");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoIsNotNull() {
            addCriterion("HRCY_SEQNO is not null");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoEqualTo(Short value) {
            addCriterion("HRCY_SEQNO =", value, "hrcySeqno");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoNotEqualTo(Short value) {
            addCriterion("HRCY_SEQNO <>", value, "hrcySeqno");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoGreaterThan(Short value) {
            addCriterion("HRCY_SEQNO >", value, "hrcySeqno");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoGreaterThanOrEqualTo(Short value) {
            addCriterion("HRCY_SEQNO >=", value, "hrcySeqno");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoLessThan(Short value) {
            addCriterion("HRCY_SEQNO <", value, "hrcySeqno");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoLessThanOrEqualTo(Short value) {
            addCriterion("HRCY_SEQNO <=", value, "hrcySeqno");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoIn(List<Short> values) {
            addCriterion("HRCY_SEQNO in", values, "hrcySeqno");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoNotIn(List<Short> values) {
            addCriterion("HRCY_SEQNO not in", values, "hrcySeqno");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoBetween(Short value1, Short value2) {
            addCriterion("HRCY_SEQNO between", value1, value2, "hrcySeqno");
            return (Criteria) this;
        }

        public Criteria andHrcySeqnoNotBetween(Short value1, Short value2) {
            addCriterion("HRCY_SEQNO not between", value1, value2, "hrcySeqno");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeIsNull() {
            addCriterion("ALPH_SHTN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeIsNotNull() {
            addCriterion("ALPH_SHTN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeEqualTo(String value) {
            addCriterion("ALPH_SHTN_CODE =", value, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeNotEqualTo(String value) {
            addCriterion("ALPH_SHTN_CODE <>", value, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeGreaterThan(String value) {
            addCriterion("ALPH_SHTN_CODE >", value, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ALPH_SHTN_CODE >=", value, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeLessThan(String value) {
            addCriterion("ALPH_SHTN_CODE <", value, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeLessThanOrEqualTo(String value) {
            addCriterion("ALPH_SHTN_CODE <=", value, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeLike(String value) {
            addCriterion("ALPH_SHTN_CODE like", value, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeNotLike(String value) {
            addCriterion("ALPH_SHTN_CODE not like", value, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeIn(List<String> values) {
            addCriterion("ALPH_SHTN_CODE in", values, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeNotIn(List<String> values) {
            addCriterion("ALPH_SHTN_CODE not in", values, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeBetween(String value1, String value2) {
            addCriterion("ALPH_SHTN_CODE between", value1, value2, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andAlphShtnCodeNotBetween(String value1, String value2) {
            addCriterion("ALPH_SHTN_CODE not between", value1, value2, "alphShtnCode");
            return (Criteria) this;
        }

        public Criteria andRmkIsNull() {
            addCriterion("RMK is null");
            return (Criteria) this;
        }

        public Criteria andRmkIsNotNull() {
            addCriterion("RMK is not null");
            return (Criteria) this;
        }

        public Criteria andRmkEqualTo(String value) {
            addCriterion("RMK =", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotEqualTo(String value) {
            addCriterion("RMK <>", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkGreaterThan(String value) {
            addCriterion("RMK >", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkGreaterThanOrEqualTo(String value) {
            addCriterion("RMK >=", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkLessThan(String value) {
            addCriterion("RMK <", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkLessThanOrEqualTo(String value) {
            addCriterion("RMK <=", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkLike(String value) {
            addCriterion("RMK like", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotLike(String value) {
            addCriterion("RMK not like", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkIn(List<String> values) {
            addCriterion("RMK in", values, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotIn(List<String> values) {
            addCriterion("RMK not in", values, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkBetween(String value1, String value2) {
            addCriterion("RMK between", value1, value2, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotBetween(String value1, String value2) {
            addCriterion("RMK not between", value1, value2, "rmk");
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