package fun.riding4.transport.model;

import java.util.List;

/**
 * @Author hym
 * @Date 2023-04-12 22:10
 * @Version 1.0
 * @Description
 */
public class TransportFileList {
    private String code;
    private List<TransportFile> files;

    public TransportFileList(String code, List<TransportFile> files) {
        this.code = code;
        this.files = files;
    }

    public String getCode() {
        return code;
    }

    public List<TransportFile> getFiles() {
        return files;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFiles(List<TransportFile> files) {
        this.files = files;
    }
}
