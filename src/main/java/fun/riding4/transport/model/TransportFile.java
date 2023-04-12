package fun.riding4.transport.model;

/**
 * @Author hym
 * @Date 2023-04-12 22:11
 * @Version 1.0
 * @Description
 */
public class TransportFile {
    private String name;
    private String type;
    private long size;

    public TransportFile(String name, String type, long size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public long getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
