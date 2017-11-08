
package connectionSocket.inet;

public class Packet {

	private String data;

    public Packet(byte[] data, int len) {
        this.data = new String(data, 0, len);

    }

    public String getData() {
        return data;
    }
}
