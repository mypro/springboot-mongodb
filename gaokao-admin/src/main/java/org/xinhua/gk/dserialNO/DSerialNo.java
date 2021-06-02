package org.xinhua.gk.dserialNO;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "d_serial")
public class DSerialNo extends SerialNo {
    /**
     *
     */
    private static final long serialVersionUID = -7646217888832708284L;
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DSerialNo{" +
                "createTime='" + createTime + '\'' +
                "} " + super.toString();
    }
}
