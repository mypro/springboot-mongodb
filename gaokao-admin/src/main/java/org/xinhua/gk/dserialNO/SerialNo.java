package org.xinhua.gk.dserialNO;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "serial_no")
public class SerialNo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1697599441244054653L;

    @Id
    private String objectId;

    private Integer serialNo;

    private Integer sign;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "SerialNo{" +
                "objectId='" + objectId + '\'' +
                ", serialNo=" + serialNo +
                ", sign=" + sign +
                '}';
    }
}
