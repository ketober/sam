package com.ai.sam.domain;

import java.io.Serializable;

public class TSamTenantauth implements Serializable {
    private String dblCenId;

    private String staffid;

    private String tenantId;

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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
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
        TSamTenantauth other = (TSamTenantauth) that;
        return (this.getDblCenId() == null ? other.getDblCenId() == null : this.getDblCenId().equals(other.getDblCenId()))
            && (this.getStaffid() == null ? other.getStaffid() == null : this.getStaffid().equals(other.getStaffid()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDblCenId() == null) ? 0 : getDblCenId().hashCode());
        result = prime * result + ((getStaffid() == null) ? 0 : getStaffid().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        return result;
    }
}