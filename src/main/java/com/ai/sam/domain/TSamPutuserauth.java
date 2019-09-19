package com.ai.sam.domain;

import java.io.Serializable;

public class TSamPutuserauth implements Serializable {
    private String dblCenId;

    private String staffid;

    private String moduleid;

    private String tenantId;

    private String authid;

    private static final long serialVersionUID = 1L;

    public String getDblCenId() {
        return dblCenId;
    }

    public void setDblCenId(String dblCenId) {
        this.dblCenId = dblCenId == null ? null : dblCenId.trim();
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid == null ? null : moduleid.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid == null ? null : authid.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TSamPutuserauth other = (TSamPutuserauth) that;
        return (this.getDblCenId() == null ? other.getDblCenId() == null : this.getDblCenId().equals(other.getDblCenId()))
            && (this.getStaffid() == null ? other.getStaffid() == null : this.getStaffid().equals(other.getStaffid()))
            && (this.getModuleid() == null ? other.getModuleid() == null : this.getModuleid().equals(other.getModuleid()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getAuthid() == null ? other.getAuthid() == null : this.getAuthid().equals(other.getAuthid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDblCenId() == null) ? 0 : getDblCenId().hashCode());
        result = prime * result + ((getStaffid() == null) ? 0 : getStaffid().hashCode());
        result = prime * result + ((getModuleid() == null) ? 0 : getModuleid().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getAuthid() == null) ? 0 : getAuthid().hashCode());
        return result;
    }
}