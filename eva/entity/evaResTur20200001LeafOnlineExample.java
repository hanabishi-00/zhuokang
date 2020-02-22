package hdy.eva.entity;

import java.util.ArrayList;
import java.util.List;

public class evaResTur20200001LeafOnlineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public evaResTur20200001LeafOnlineExample() {
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

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Integer value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Integer value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Integer value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Integer value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Integer value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Integer> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Integer> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Integer value1, Integer value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(Byte value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(Byte value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(Byte value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(Byte value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(Byte value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(Byte value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<Byte> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<Byte> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(Byte value1, Byte value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(Byte value1, Byte value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andV1IsNull() {
            addCriterion("v1 is null");
            return (Criteria) this;
        }

        public Criteria andV1IsNotNull() {
            addCriterion("v1 is not null");
            return (Criteria) this;
        }

        public Criteria andV1EqualTo(Float value) {
            addCriterion("v1 =", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1NotEqualTo(Float value) {
            addCriterion("v1 <>", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1GreaterThan(Float value) {
            addCriterion("v1 >", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1GreaterThanOrEqualTo(Float value) {
            addCriterion("v1 >=", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1LessThan(Float value) {
            addCriterion("v1 <", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1LessThanOrEqualTo(Float value) {
            addCriterion("v1 <=", value, "v1");
            return (Criteria) this;
        }

        public Criteria andV1In(List<Float> values) {
            addCriterion("v1 in", values, "v1");
            return (Criteria) this;
        }

        public Criteria andV1NotIn(List<Float> values) {
            addCriterion("v1 not in", values, "v1");
            return (Criteria) this;
        }

        public Criteria andV1Between(Float value1, Float value2) {
            addCriterion("v1 between", value1, value2, "v1");
            return (Criteria) this;
        }

        public Criteria andV1NotBetween(Float value1, Float value2) {
            addCriterion("v1 not between", value1, value2, "v1");
            return (Criteria) this;
        }

        public Criteria andV2IsNull() {
            addCriterion("v2 is null");
            return (Criteria) this;
        }

        public Criteria andV2IsNotNull() {
            addCriterion("v2 is not null");
            return (Criteria) this;
        }

        public Criteria andV2EqualTo(Float value) {
            addCriterion("v2 =", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2NotEqualTo(Float value) {
            addCriterion("v2 <>", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2GreaterThan(Float value) {
            addCriterion("v2 >", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2GreaterThanOrEqualTo(Float value) {
            addCriterion("v2 >=", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2LessThan(Float value) {
            addCriterion("v2 <", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2LessThanOrEqualTo(Float value) {
            addCriterion("v2 <=", value, "v2");
            return (Criteria) this;
        }

        public Criteria andV2In(List<Float> values) {
            addCriterion("v2 in", values, "v2");
            return (Criteria) this;
        }

        public Criteria andV2NotIn(List<Float> values) {
            addCriterion("v2 not in", values, "v2");
            return (Criteria) this;
        }

        public Criteria andV2Between(Float value1, Float value2) {
            addCriterion("v2 between", value1, value2, "v2");
            return (Criteria) this;
        }

        public Criteria andV2NotBetween(Float value1, Float value2) {
            addCriterion("v2 not between", value1, value2, "v2");
            return (Criteria) this;
        }

        public Criteria andV3IsNull() {
            addCriterion("v3 is null");
            return (Criteria) this;
        }

        public Criteria andV3IsNotNull() {
            addCriterion("v3 is not null");
            return (Criteria) this;
        }

        public Criteria andV3EqualTo(Float value) {
            addCriterion("v3 =", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3NotEqualTo(Float value) {
            addCriterion("v3 <>", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3GreaterThan(Float value) {
            addCriterion("v3 >", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3GreaterThanOrEqualTo(Float value) {
            addCriterion("v3 >=", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3LessThan(Float value) {
            addCriterion("v3 <", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3LessThanOrEqualTo(Float value) {
            addCriterion("v3 <=", value, "v3");
            return (Criteria) this;
        }

        public Criteria andV3In(List<Float> values) {
            addCriterion("v3 in", values, "v3");
            return (Criteria) this;
        }

        public Criteria andV3NotIn(List<Float> values) {
            addCriterion("v3 not in", values, "v3");
            return (Criteria) this;
        }

        public Criteria andV3Between(Float value1, Float value2) {
            addCriterion("v3 between", value1, value2, "v3");
            return (Criteria) this;
        }

        public Criteria andV3NotBetween(Float value1, Float value2) {
            addCriterion("v3 not between", value1, value2, "v3");
            return (Criteria) this;
        }

        public Criteria andV4IsNull() {
            addCriterion("v4 is null");
            return (Criteria) this;
        }

        public Criteria andV4IsNotNull() {
            addCriterion("v4 is not null");
            return (Criteria) this;
        }

        public Criteria andV4EqualTo(Float value) {
            addCriterion("v4 =", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4NotEqualTo(Float value) {
            addCriterion("v4 <>", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4GreaterThan(Float value) {
            addCriterion("v4 >", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4GreaterThanOrEqualTo(Float value) {
            addCriterion("v4 >=", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4LessThan(Float value) {
            addCriterion("v4 <", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4LessThanOrEqualTo(Float value) {
            addCriterion("v4 <=", value, "v4");
            return (Criteria) this;
        }

        public Criteria andV4In(List<Float> values) {
            addCriterion("v4 in", values, "v4");
            return (Criteria) this;
        }

        public Criteria andV4NotIn(List<Float> values) {
            addCriterion("v4 not in", values, "v4");
            return (Criteria) this;
        }

        public Criteria andV4Between(Float value1, Float value2) {
            addCriterion("v4 between", value1, value2, "v4");
            return (Criteria) this;
        }

        public Criteria andV4NotBetween(Float value1, Float value2) {
            addCriterion("v4 not between", value1, value2, "v4");
            return (Criteria) this;
        }

        public Criteria andV5IsNull() {
            addCriterion("v5 is null");
            return (Criteria) this;
        }

        public Criteria andV5IsNotNull() {
            addCriterion("v5 is not null");
            return (Criteria) this;
        }

        public Criteria andV5EqualTo(Float value) {
            addCriterion("v5 =", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5NotEqualTo(Float value) {
            addCriterion("v5 <>", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5GreaterThan(Float value) {
            addCriterion("v5 >", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5GreaterThanOrEqualTo(Float value) {
            addCriterion("v5 >=", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5LessThan(Float value) {
            addCriterion("v5 <", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5LessThanOrEqualTo(Float value) {
            addCriterion("v5 <=", value, "v5");
            return (Criteria) this;
        }

        public Criteria andV5In(List<Float> values) {
            addCriterion("v5 in", values, "v5");
            return (Criteria) this;
        }

        public Criteria andV5NotIn(List<Float> values) {
            addCriterion("v5 not in", values, "v5");
            return (Criteria) this;
        }

        public Criteria andV5Between(Float value1, Float value2) {
            addCriterion("v5 between", value1, value2, "v5");
            return (Criteria) this;
        }

        public Criteria andV5NotBetween(Float value1, Float value2) {
            addCriterion("v5 not between", value1, value2, "v5");
            return (Criteria) this;
        }

        public Criteria andV6IsNull() {
            addCriterion("v6 is null");
            return (Criteria) this;
        }

        public Criteria andV6IsNotNull() {
            addCriterion("v6 is not null");
            return (Criteria) this;
        }

        public Criteria andV6EqualTo(Float value) {
            addCriterion("v6 =", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6NotEqualTo(Float value) {
            addCriterion("v6 <>", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6GreaterThan(Float value) {
            addCriterion("v6 >", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6GreaterThanOrEqualTo(Float value) {
            addCriterion("v6 >=", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6LessThan(Float value) {
            addCriterion("v6 <", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6LessThanOrEqualTo(Float value) {
            addCriterion("v6 <=", value, "v6");
            return (Criteria) this;
        }

        public Criteria andV6In(List<Float> values) {
            addCriterion("v6 in", values, "v6");
            return (Criteria) this;
        }

        public Criteria andV6NotIn(List<Float> values) {
            addCriterion("v6 not in", values, "v6");
            return (Criteria) this;
        }

        public Criteria andV6Between(Float value1, Float value2) {
            addCriterion("v6 between", value1, value2, "v6");
            return (Criteria) this;
        }

        public Criteria andV6NotBetween(Float value1, Float value2) {
            addCriterion("v6 not between", value1, value2, "v6");
            return (Criteria) this;
        }

        public Criteria andV7IsNull() {
            addCriterion("v7 is null");
            return (Criteria) this;
        }

        public Criteria andV7IsNotNull() {
            addCriterion("v7 is not null");
            return (Criteria) this;
        }

        public Criteria andV7EqualTo(Float value) {
            addCriterion("v7 =", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7NotEqualTo(Float value) {
            addCriterion("v7 <>", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7GreaterThan(Float value) {
            addCriterion("v7 >", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7GreaterThanOrEqualTo(Float value) {
            addCriterion("v7 >=", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7LessThan(Float value) {
            addCriterion("v7 <", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7LessThanOrEqualTo(Float value) {
            addCriterion("v7 <=", value, "v7");
            return (Criteria) this;
        }

        public Criteria andV7In(List<Float> values) {
            addCriterion("v7 in", values, "v7");
            return (Criteria) this;
        }

        public Criteria andV7NotIn(List<Float> values) {
            addCriterion("v7 not in", values, "v7");
            return (Criteria) this;
        }

        public Criteria andV7Between(Float value1, Float value2) {
            addCriterion("v7 between", value1, value2, "v7");
            return (Criteria) this;
        }

        public Criteria andV7NotBetween(Float value1, Float value2) {
            addCriterion("v7 not between", value1, value2, "v7");
            return (Criteria) this;
        }

        public Criteria andV8IsNull() {
            addCriterion("v8 is null");
            return (Criteria) this;
        }

        public Criteria andV8IsNotNull() {
            addCriterion("v8 is not null");
            return (Criteria) this;
        }

        public Criteria andV8EqualTo(Float value) {
            addCriterion("v8 =", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8NotEqualTo(Float value) {
            addCriterion("v8 <>", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8GreaterThan(Float value) {
            addCriterion("v8 >", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8GreaterThanOrEqualTo(Float value) {
            addCriterion("v8 >=", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8LessThan(Float value) {
            addCriterion("v8 <", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8LessThanOrEqualTo(Float value) {
            addCriterion("v8 <=", value, "v8");
            return (Criteria) this;
        }

        public Criteria andV8In(List<Float> values) {
            addCriterion("v8 in", values, "v8");
            return (Criteria) this;
        }

        public Criteria andV8NotIn(List<Float> values) {
            addCriterion("v8 not in", values, "v8");
            return (Criteria) this;
        }

        public Criteria andV8Between(Float value1, Float value2) {
            addCriterion("v8 between", value1, value2, "v8");
            return (Criteria) this;
        }

        public Criteria andV8NotBetween(Float value1, Float value2) {
            addCriterion("v8 not between", value1, value2, "v8");
            return (Criteria) this;
        }

        public Criteria andV9IsNull() {
            addCriterion("v9 is null");
            return (Criteria) this;
        }

        public Criteria andV9IsNotNull() {
            addCriterion("v9 is not null");
            return (Criteria) this;
        }

        public Criteria andV9EqualTo(Float value) {
            addCriterion("v9 =", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9NotEqualTo(Float value) {
            addCriterion("v9 <>", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9GreaterThan(Float value) {
            addCriterion("v9 >", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9GreaterThanOrEqualTo(Float value) {
            addCriterion("v9 >=", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9LessThan(Float value) {
            addCriterion("v9 <", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9LessThanOrEqualTo(Float value) {
            addCriterion("v9 <=", value, "v9");
            return (Criteria) this;
        }

        public Criteria andV9In(List<Float> values) {
            addCriterion("v9 in", values, "v9");
            return (Criteria) this;
        }

        public Criteria andV9NotIn(List<Float> values) {
            addCriterion("v9 not in", values, "v9");
            return (Criteria) this;
        }

        public Criteria andV9Between(Float value1, Float value2) {
            addCriterion("v9 between", value1, value2, "v9");
            return (Criteria) this;
        }

        public Criteria andV9NotBetween(Float value1, Float value2) {
            addCriterion("v9 not between", value1, value2, "v9");
            return (Criteria) this;
        }

        public Criteria andV10IsNull() {
            addCriterion("v10 is null");
            return (Criteria) this;
        }

        public Criteria andV10IsNotNull() {
            addCriterion("v10 is not null");
            return (Criteria) this;
        }

        public Criteria andV10EqualTo(Float value) {
            addCriterion("v10 =", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10NotEqualTo(Float value) {
            addCriterion("v10 <>", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10GreaterThan(Float value) {
            addCriterion("v10 >", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10GreaterThanOrEqualTo(Float value) {
            addCriterion("v10 >=", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10LessThan(Float value) {
            addCriterion("v10 <", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10LessThanOrEqualTo(Float value) {
            addCriterion("v10 <=", value, "v10");
            return (Criteria) this;
        }

        public Criteria andV10In(List<Float> values) {
            addCriterion("v10 in", values, "v10");
            return (Criteria) this;
        }

        public Criteria andV10NotIn(List<Float> values) {
            addCriterion("v10 not in", values, "v10");
            return (Criteria) this;
        }

        public Criteria andV10Between(Float value1, Float value2) {
            addCriterion("v10 between", value1, value2, "v10");
            return (Criteria) this;
        }

        public Criteria andV10NotBetween(Float value1, Float value2) {
            addCriterion("v10 not between", value1, value2, "v10");
            return (Criteria) this;
        }

        public Criteria andV11IsNull() {
            addCriterion("v11 is null");
            return (Criteria) this;
        }

        public Criteria andV11IsNotNull() {
            addCriterion("v11 is not null");
            return (Criteria) this;
        }

        public Criteria andV11EqualTo(Float value) {
            addCriterion("v11 =", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11NotEqualTo(Float value) {
            addCriterion("v11 <>", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11GreaterThan(Float value) {
            addCriterion("v11 >", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11GreaterThanOrEqualTo(Float value) {
            addCriterion("v11 >=", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11LessThan(Float value) {
            addCriterion("v11 <", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11LessThanOrEqualTo(Float value) {
            addCriterion("v11 <=", value, "v11");
            return (Criteria) this;
        }

        public Criteria andV11In(List<Float> values) {
            addCriterion("v11 in", values, "v11");
            return (Criteria) this;
        }

        public Criteria andV11NotIn(List<Float> values) {
            addCriterion("v11 not in", values, "v11");
            return (Criteria) this;
        }

        public Criteria andV11Between(Float value1, Float value2) {
            addCriterion("v11 between", value1, value2, "v11");
            return (Criteria) this;
        }

        public Criteria andV11NotBetween(Float value1, Float value2) {
            addCriterion("v11 not between", value1, value2, "v11");
            return (Criteria) this;
        }

        public Criteria andV12IsNull() {
            addCriterion("v12 is null");
            return (Criteria) this;
        }

        public Criteria andV12IsNotNull() {
            addCriterion("v12 is not null");
            return (Criteria) this;
        }

        public Criteria andV12EqualTo(Float value) {
            addCriterion("v12 =", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12NotEqualTo(Float value) {
            addCriterion("v12 <>", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12GreaterThan(Float value) {
            addCriterion("v12 >", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12GreaterThanOrEqualTo(Float value) {
            addCriterion("v12 >=", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12LessThan(Float value) {
            addCriterion("v12 <", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12LessThanOrEqualTo(Float value) {
            addCriterion("v12 <=", value, "v12");
            return (Criteria) this;
        }

        public Criteria andV12In(List<Float> values) {
            addCriterion("v12 in", values, "v12");
            return (Criteria) this;
        }

        public Criteria andV12NotIn(List<Float> values) {
            addCriterion("v12 not in", values, "v12");
            return (Criteria) this;
        }

        public Criteria andV12Between(Float value1, Float value2) {
            addCriterion("v12 between", value1, value2, "v12");
            return (Criteria) this;
        }

        public Criteria andV12NotBetween(Float value1, Float value2) {
            addCriterion("v12 not between", value1, value2, "v12");
            return (Criteria) this;
        }

        public Criteria andV13IsNull() {
            addCriterion("v13 is null");
            return (Criteria) this;
        }

        public Criteria andV13IsNotNull() {
            addCriterion("v13 is not null");
            return (Criteria) this;
        }

        public Criteria andV13EqualTo(Float value) {
            addCriterion("v13 =", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13NotEqualTo(Float value) {
            addCriterion("v13 <>", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13GreaterThan(Float value) {
            addCriterion("v13 >", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13GreaterThanOrEqualTo(Float value) {
            addCriterion("v13 >=", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13LessThan(Float value) {
            addCriterion("v13 <", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13LessThanOrEqualTo(Float value) {
            addCriterion("v13 <=", value, "v13");
            return (Criteria) this;
        }

        public Criteria andV13In(List<Float> values) {
            addCriterion("v13 in", values, "v13");
            return (Criteria) this;
        }

        public Criteria andV13NotIn(List<Float> values) {
            addCriterion("v13 not in", values, "v13");
            return (Criteria) this;
        }

        public Criteria andV13Between(Float value1, Float value2) {
            addCriterion("v13 between", value1, value2, "v13");
            return (Criteria) this;
        }

        public Criteria andV13NotBetween(Float value1, Float value2) {
            addCriterion("v13 not between", value1, value2, "v13");
            return (Criteria) this;
        }

        public Criteria andV14IsNull() {
            addCriterion("v14 is null");
            return (Criteria) this;
        }

        public Criteria andV14IsNotNull() {
            addCriterion("v14 is not null");
            return (Criteria) this;
        }

        public Criteria andV14EqualTo(Float value) {
            addCriterion("v14 =", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14NotEqualTo(Float value) {
            addCriterion("v14 <>", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14GreaterThan(Float value) {
            addCriterion("v14 >", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14GreaterThanOrEqualTo(Float value) {
            addCriterion("v14 >=", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14LessThan(Float value) {
            addCriterion("v14 <", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14LessThanOrEqualTo(Float value) {
            addCriterion("v14 <=", value, "v14");
            return (Criteria) this;
        }

        public Criteria andV14In(List<Float> values) {
            addCriterion("v14 in", values, "v14");
            return (Criteria) this;
        }

        public Criteria andV14NotIn(List<Float> values) {
            addCriterion("v14 not in", values, "v14");
            return (Criteria) this;
        }

        public Criteria andV14Between(Float value1, Float value2) {
            addCriterion("v14 between", value1, value2, "v14");
            return (Criteria) this;
        }

        public Criteria andV14NotBetween(Float value1, Float value2) {
            addCriterion("v14 not between", value1, value2, "v14");
            return (Criteria) this;
        }

        public Criteria andV15IsNull() {
            addCriterion("v15 is null");
            return (Criteria) this;
        }

        public Criteria andV15IsNotNull() {
            addCriterion("v15 is not null");
            return (Criteria) this;
        }

        public Criteria andV15EqualTo(Float value) {
            addCriterion("v15 =", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15NotEqualTo(Float value) {
            addCriterion("v15 <>", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15GreaterThan(Float value) {
            addCriterion("v15 >", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15GreaterThanOrEqualTo(Float value) {
            addCriterion("v15 >=", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15LessThan(Float value) {
            addCriterion("v15 <", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15LessThanOrEqualTo(Float value) {
            addCriterion("v15 <=", value, "v15");
            return (Criteria) this;
        }

        public Criteria andV15In(List<Float> values) {
            addCriterion("v15 in", values, "v15");
            return (Criteria) this;
        }

        public Criteria andV15NotIn(List<Float> values) {
            addCriterion("v15 not in", values, "v15");
            return (Criteria) this;
        }

        public Criteria andV15Between(Float value1, Float value2) {
            addCriterion("v15 between", value1, value2, "v15");
            return (Criteria) this;
        }

        public Criteria andV15NotBetween(Float value1, Float value2) {
            addCriterion("v15 not between", value1, value2, "v15");
            return (Criteria) this;
        }

        public Criteria andV16IsNull() {
            addCriterion("v16 is null");
            return (Criteria) this;
        }

        public Criteria andV16IsNotNull() {
            addCriterion("v16 is not null");
            return (Criteria) this;
        }

        public Criteria andV16EqualTo(Float value) {
            addCriterion("v16 =", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16NotEqualTo(Float value) {
            addCriterion("v16 <>", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16GreaterThan(Float value) {
            addCriterion("v16 >", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16GreaterThanOrEqualTo(Float value) {
            addCriterion("v16 >=", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16LessThan(Float value) {
            addCriterion("v16 <", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16LessThanOrEqualTo(Float value) {
            addCriterion("v16 <=", value, "v16");
            return (Criteria) this;
        }

        public Criteria andV16In(List<Float> values) {
            addCriterion("v16 in", values, "v16");
            return (Criteria) this;
        }

        public Criteria andV16NotIn(List<Float> values) {
            addCriterion("v16 not in", values, "v16");
            return (Criteria) this;
        }

        public Criteria andV16Between(Float value1, Float value2) {
            addCriterion("v16 between", value1, value2, "v16");
            return (Criteria) this;
        }

        public Criteria andV16NotBetween(Float value1, Float value2) {
            addCriterion("v16 not between", value1, value2, "v16");
            return (Criteria) this;
        }

        public Criteria andV17IsNull() {
            addCriterion("v17 is null");
            return (Criteria) this;
        }

        public Criteria andV17IsNotNull() {
            addCriterion("v17 is not null");
            return (Criteria) this;
        }

        public Criteria andV17EqualTo(Float value) {
            addCriterion("v17 =", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17NotEqualTo(Float value) {
            addCriterion("v17 <>", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17GreaterThan(Float value) {
            addCriterion("v17 >", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17GreaterThanOrEqualTo(Float value) {
            addCriterion("v17 >=", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17LessThan(Float value) {
            addCriterion("v17 <", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17LessThanOrEqualTo(Float value) {
            addCriterion("v17 <=", value, "v17");
            return (Criteria) this;
        }

        public Criteria andV17In(List<Float> values) {
            addCriterion("v17 in", values, "v17");
            return (Criteria) this;
        }

        public Criteria andV17NotIn(List<Float> values) {
            addCriterion("v17 not in", values, "v17");
            return (Criteria) this;
        }

        public Criteria andV17Between(Float value1, Float value2) {
            addCriterion("v17 between", value1, value2, "v17");
            return (Criteria) this;
        }

        public Criteria andV17NotBetween(Float value1, Float value2) {
            addCriterion("v17 not between", value1, value2, "v17");
            return (Criteria) this;
        }

        public Criteria andV18IsNull() {
            addCriterion("v18 is null");
            return (Criteria) this;
        }

        public Criteria andV18IsNotNull() {
            addCriterion("v18 is not null");
            return (Criteria) this;
        }

        public Criteria andV18EqualTo(Float value) {
            addCriterion("v18 =", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18NotEqualTo(Float value) {
            addCriterion("v18 <>", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18GreaterThan(Float value) {
            addCriterion("v18 >", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18GreaterThanOrEqualTo(Float value) {
            addCriterion("v18 >=", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18LessThan(Float value) {
            addCriterion("v18 <", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18LessThanOrEqualTo(Float value) {
            addCriterion("v18 <=", value, "v18");
            return (Criteria) this;
        }

        public Criteria andV18In(List<Float> values) {
            addCriterion("v18 in", values, "v18");
            return (Criteria) this;
        }

        public Criteria andV18NotIn(List<Float> values) {
            addCriterion("v18 not in", values, "v18");
            return (Criteria) this;
        }

        public Criteria andV18Between(Float value1, Float value2) {
            addCriterion("v18 between", value1, value2, "v18");
            return (Criteria) this;
        }

        public Criteria andV18NotBetween(Float value1, Float value2) {
            addCriterion("v18 not between", value1, value2, "v18");
            return (Criteria) this;
        }

        public Criteria andV19IsNull() {
            addCriterion("v19 is null");
            return (Criteria) this;
        }

        public Criteria andV19IsNotNull() {
            addCriterion("v19 is not null");
            return (Criteria) this;
        }

        public Criteria andV19EqualTo(Float value) {
            addCriterion("v19 =", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19NotEqualTo(Float value) {
            addCriterion("v19 <>", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19GreaterThan(Float value) {
            addCriterion("v19 >", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19GreaterThanOrEqualTo(Float value) {
            addCriterion("v19 >=", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19LessThan(Float value) {
            addCriterion("v19 <", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19LessThanOrEqualTo(Float value) {
            addCriterion("v19 <=", value, "v19");
            return (Criteria) this;
        }

        public Criteria andV19In(List<Float> values) {
            addCriterion("v19 in", values, "v19");
            return (Criteria) this;
        }

        public Criteria andV19NotIn(List<Float> values) {
            addCriterion("v19 not in", values, "v19");
            return (Criteria) this;
        }

        public Criteria andV19Between(Float value1, Float value2) {
            addCriterion("v19 between", value1, value2, "v19");
            return (Criteria) this;
        }

        public Criteria andV19NotBetween(Float value1, Float value2) {
            addCriterion("v19 not between", value1, value2, "v19");
            return (Criteria) this;
        }

        public Criteria andV20IsNull() {
            addCriterion("v20 is null");
            return (Criteria) this;
        }

        public Criteria andV20IsNotNull() {
            addCriterion("v20 is not null");
            return (Criteria) this;
        }

        public Criteria andV20EqualTo(Float value) {
            addCriterion("v20 =", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20NotEqualTo(Float value) {
            addCriterion("v20 <>", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20GreaterThan(Float value) {
            addCriterion("v20 >", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20GreaterThanOrEqualTo(Float value) {
            addCriterion("v20 >=", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20LessThan(Float value) {
            addCriterion("v20 <", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20LessThanOrEqualTo(Float value) {
            addCriterion("v20 <=", value, "v20");
            return (Criteria) this;
        }

        public Criteria andV20In(List<Float> values) {
            addCriterion("v20 in", values, "v20");
            return (Criteria) this;
        }

        public Criteria andV20NotIn(List<Float> values) {
            addCriterion("v20 not in", values, "v20");
            return (Criteria) this;
        }

        public Criteria andV20Between(Float value1, Float value2) {
            addCriterion("v20 between", value1, value2, "v20");
            return (Criteria) this;
        }

        public Criteria andV20NotBetween(Float value1, Float value2) {
            addCriterion("v20 not between", value1, value2, "v20");
            return (Criteria) this;
        }

        public Criteria andV21IsNull() {
            addCriterion("v21 is null");
            return (Criteria) this;
        }

        public Criteria andV21IsNotNull() {
            addCriterion("v21 is not null");
            return (Criteria) this;
        }

        public Criteria andV21EqualTo(Float value) {
            addCriterion("v21 =", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21NotEqualTo(Float value) {
            addCriterion("v21 <>", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21GreaterThan(Float value) {
            addCriterion("v21 >", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21GreaterThanOrEqualTo(Float value) {
            addCriterion("v21 >=", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21LessThan(Float value) {
            addCriterion("v21 <", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21LessThanOrEqualTo(Float value) {
            addCriterion("v21 <=", value, "v21");
            return (Criteria) this;
        }

        public Criteria andV21In(List<Float> values) {
            addCriterion("v21 in", values, "v21");
            return (Criteria) this;
        }

        public Criteria andV21NotIn(List<Float> values) {
            addCriterion("v21 not in", values, "v21");
            return (Criteria) this;
        }

        public Criteria andV21Between(Float value1, Float value2) {
            addCriterion("v21 between", value1, value2, "v21");
            return (Criteria) this;
        }

        public Criteria andV21NotBetween(Float value1, Float value2) {
            addCriterion("v21 not between", value1, value2, "v21");
            return (Criteria) this;
        }

        public Criteria andV22IsNull() {
            addCriterion("v22 is null");
            return (Criteria) this;
        }

        public Criteria andV22IsNotNull() {
            addCriterion("v22 is not null");
            return (Criteria) this;
        }

        public Criteria andV22EqualTo(Float value) {
            addCriterion("v22 =", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22NotEqualTo(Float value) {
            addCriterion("v22 <>", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22GreaterThan(Float value) {
            addCriterion("v22 >", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22GreaterThanOrEqualTo(Float value) {
            addCriterion("v22 >=", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22LessThan(Float value) {
            addCriterion("v22 <", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22LessThanOrEqualTo(Float value) {
            addCriterion("v22 <=", value, "v22");
            return (Criteria) this;
        }

        public Criteria andV22In(List<Float> values) {
            addCriterion("v22 in", values, "v22");
            return (Criteria) this;
        }

        public Criteria andV22NotIn(List<Float> values) {
            addCriterion("v22 not in", values, "v22");
            return (Criteria) this;
        }

        public Criteria andV22Between(Float value1, Float value2) {
            addCriterion("v22 between", value1, value2, "v22");
            return (Criteria) this;
        }

        public Criteria andV22NotBetween(Float value1, Float value2) {
            addCriterion("v22 not between", value1, value2, "v22");
            return (Criteria) this;
        }

        public Criteria andV23IsNull() {
            addCriterion("v23 is null");
            return (Criteria) this;
        }

        public Criteria andV23IsNotNull() {
            addCriterion("v23 is not null");
            return (Criteria) this;
        }

        public Criteria andV23EqualTo(Float value) {
            addCriterion("v23 =", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23NotEqualTo(Float value) {
            addCriterion("v23 <>", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23GreaterThan(Float value) {
            addCriterion("v23 >", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23GreaterThanOrEqualTo(Float value) {
            addCriterion("v23 >=", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23LessThan(Float value) {
            addCriterion("v23 <", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23LessThanOrEqualTo(Float value) {
            addCriterion("v23 <=", value, "v23");
            return (Criteria) this;
        }

        public Criteria andV23In(List<Float> values) {
            addCriterion("v23 in", values, "v23");
            return (Criteria) this;
        }

        public Criteria andV23NotIn(List<Float> values) {
            addCriterion("v23 not in", values, "v23");
            return (Criteria) this;
        }

        public Criteria andV23Between(Float value1, Float value2) {
            addCriterion("v23 between", value1, value2, "v23");
            return (Criteria) this;
        }

        public Criteria andV23NotBetween(Float value1, Float value2) {
            addCriterion("v23 not between", value1, value2, "v23");
            return (Criteria) this;
        }

        public Criteria andV24IsNull() {
            addCriterion("v24 is null");
            return (Criteria) this;
        }

        public Criteria andV24IsNotNull() {
            addCriterion("v24 is not null");
            return (Criteria) this;
        }

        public Criteria andV24EqualTo(Float value) {
            addCriterion("v24 =", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24NotEqualTo(Float value) {
            addCriterion("v24 <>", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24GreaterThan(Float value) {
            addCriterion("v24 >", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24GreaterThanOrEqualTo(Float value) {
            addCriterion("v24 >=", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24LessThan(Float value) {
            addCriterion("v24 <", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24LessThanOrEqualTo(Float value) {
            addCriterion("v24 <=", value, "v24");
            return (Criteria) this;
        }

        public Criteria andV24In(List<Float> values) {
            addCriterion("v24 in", values, "v24");
            return (Criteria) this;
        }

        public Criteria andV24NotIn(List<Float> values) {
            addCriterion("v24 not in", values, "v24");
            return (Criteria) this;
        }

        public Criteria andV24Between(Float value1, Float value2) {
            addCriterion("v24 between", value1, value2, "v24");
            return (Criteria) this;
        }

        public Criteria andV24NotBetween(Float value1, Float value2) {
            addCriterion("v24 not between", value1, value2, "v24");
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