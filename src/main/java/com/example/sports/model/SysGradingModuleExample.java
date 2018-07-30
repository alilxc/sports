package com.example.sports.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysGradingModuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public SysGradingModuleExample() {
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

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdIsNull() {
            addCriterion("sys_user_student_id is null");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdIsNotNull() {
            addCriterion("sys_user_student_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdEqualTo(Integer value) {
            addCriterion("sys_user_student_id =", value, "sysUserStudentId");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdNotEqualTo(Integer value) {
            addCriterion("sys_user_student_id <>", value, "sysUserStudentId");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdGreaterThan(Integer value) {
            addCriterion("sys_user_student_id >", value, "sysUserStudentId");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sys_user_student_id >=", value, "sysUserStudentId");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdLessThan(Integer value) {
            addCriterion("sys_user_student_id <", value, "sysUserStudentId");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("sys_user_student_id <=", value, "sysUserStudentId");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdIn(List<Integer> values) {
            addCriterion("sys_user_student_id in", values, "sysUserStudentId");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdNotIn(List<Integer> values) {
            addCriterion("sys_user_student_id not in", values, "sysUserStudentId");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("sys_user_student_id between", value1, value2, "sysUserStudentId");
            return (Criteria) this;
        }

        public Criteria andSysUserStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sys_user_student_id not between", value1, value2, "sysUserStudentId");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdIsNull() {
            addCriterion("sys_project_id is null");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdIsNotNull() {
            addCriterion("sys_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdEqualTo(Integer value) {
            addCriterion("sys_project_id =", value, "sysProjectId");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdNotEqualTo(Integer value) {
            addCriterion("sys_project_id <>", value, "sysProjectId");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdGreaterThan(Integer value) {
            addCriterion("sys_project_id >", value, "sysProjectId");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sys_project_id >=", value, "sysProjectId");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdLessThan(Integer value) {
            addCriterion("sys_project_id <", value, "sysProjectId");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("sys_project_id <=", value, "sysProjectId");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdIn(List<Integer> values) {
            addCriterion("sys_project_id in", values, "sysProjectId");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdNotIn(List<Integer> values) {
            addCriterion("sys_project_id not in", values, "sysProjectId");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("sys_project_id between", value1, value2, "sysProjectId");
            return (Criteria) this;
        }

        public Criteria andSysProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sys_project_id not between", value1, value2, "sysProjectId");
            return (Criteria) this;
        }

        public Criteria andAchievementIsNull() {
            addCriterion("achievement is null");
            return (Criteria) this;
        }

        public Criteria andAchievementIsNotNull() {
            addCriterion("achievement is not null");
            return (Criteria) this;
        }

        public Criteria andAchievementEqualTo(Integer value) {
            addCriterion("achievement =", value, "achievement");
            return (Criteria) this;
        }

        public Criteria andAchievementNotEqualTo(Integer value) {
            addCriterion("achievement <>", value, "achievement");
            return (Criteria) this;
        }

        public Criteria andAchievementGreaterThan(Integer value) {
            addCriterion("achievement >", value, "achievement");
            return (Criteria) this;
        }

        public Criteria andAchievementGreaterThanOrEqualTo(Integer value) {
            addCriterion("achievement >=", value, "achievement");
            return (Criteria) this;
        }

        public Criteria andAchievementLessThan(Integer value) {
            addCriterion("achievement <", value, "achievement");
            return (Criteria) this;
        }

        public Criteria andAchievementLessThanOrEqualTo(Integer value) {
            addCriterion("achievement <=", value, "achievement");
            return (Criteria) this;
        }

        public Criteria andAchievementIn(List<Integer> values) {
            addCriterion("achievement in", values, "achievement");
            return (Criteria) this;
        }

        public Criteria andAchievementNotIn(List<Integer> values) {
            addCriterion("achievement not in", values, "achievement");
            return (Criteria) this;
        }

        public Criteria andAchievementBetween(Integer value1, Integer value2) {
            addCriterion("achievement between", value1, value2, "achievement");
            return (Criteria) this;
        }

        public Criteria andAchievementNotBetween(Integer value1, Integer value2) {
            addCriterion("achievement not between", value1, value2, "achievement");
            return (Criteria) this;
        }

        public Criteria andTeamTypeIsNull() {
            addCriterion("team_type is null");
            return (Criteria) this;
        }

        public Criteria andTeamTypeIsNotNull() {
            addCriterion("team_type is not null");
            return (Criteria) this;
        }

        public Criteria andTeamTypeEqualTo(Integer value) {
            addCriterion("team_type =", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeNotEqualTo(Integer value) {
            addCriterion("team_type <>", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeGreaterThan(Integer value) {
            addCriterion("team_type >", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_type >=", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeLessThan(Integer value) {
            addCriterion("team_type <", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeLessThanOrEqualTo(Integer value) {
            addCriterion("team_type <=", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeIn(List<Integer> values) {
            addCriterion("team_type in", values, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeNotIn(List<Integer> values) {
            addCriterion("team_type not in", values, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeBetween(Integer value1, Integer value2) {
            addCriterion("team_type between", value1, value2, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("team_type not between", value1, value2, "teamType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeIsNull() {
            addCriterion("match_type is null");
            return (Criteria) this;
        }

        public Criteria andMatchTypeIsNotNull() {
            addCriterion("match_type is not null");
            return (Criteria) this;
        }

        public Criteria andMatchTypeEqualTo(Integer value) {
            addCriterion("match_type =", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeNotEqualTo(Integer value) {
            addCriterion("match_type <>", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeGreaterThan(Integer value) {
            addCriterion("match_type >", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("match_type >=", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeLessThan(Integer value) {
            addCriterion("match_type <", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeLessThanOrEqualTo(Integer value) {
            addCriterion("match_type <=", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeIn(List<Integer> values) {
            addCriterion("match_type in", values, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeNotIn(List<Integer> values) {
            addCriterion("match_type not in", values, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeBetween(Integer value1, Integer value2) {
            addCriterion("match_type between", value1, value2, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("match_type not between", value1, value2, "matchType");
            return (Criteria) this;
        }

        public Criteria andSportTypeIsNull() {
            addCriterion("sport_type is null");
            return (Criteria) this;
        }

        public Criteria andSportTypeIsNotNull() {
            addCriterion("sport_type is not null");
            return (Criteria) this;
        }

        public Criteria andSportTypeEqualTo(Integer value) {
            addCriterion("sport_type =", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotEqualTo(Integer value) {
            addCriterion("sport_type <>", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeGreaterThan(Integer value) {
            addCriterion("sport_type >", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sport_type >=", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeLessThan(Integer value) {
            addCriterion("sport_type <", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeLessThanOrEqualTo(Integer value) {
            addCriterion("sport_type <=", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeIn(List<Integer> values) {
            addCriterion("sport_type in", values, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotIn(List<Integer> values) {
            addCriterion("sport_type not in", values, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeBetween(Integer value1, Integer value2) {
            addCriterion("sport_type between", value1, value2, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sport_type not between", value1, value2, "sportType");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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