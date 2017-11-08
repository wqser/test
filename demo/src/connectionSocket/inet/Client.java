package connectionSocket.inet;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		Connection conn = new Connection("localhost", 8898, 1);

	    if(conn.open() == true) {
            //0011:SOF0011B00HUNOINJKMk012345678`000013,20170809125423EOF
            //0017:SOF0017B00HUNOINJKMk012345678`000013,155.0*820.0*1.75,520,2511,25.3,3000,20.0,40,20170809125423EOF
            //0018:SOF0018B00HUNOINJKMk012345678`001,txt,20170228142554,20170228142558,5L2g5aW977yM5rWL6K+V6IGK5aSpenpzZGZzZGY9PSwsODg3Nw==EOF
            //0003:SOF0003B00HUNOINJKMk012345678`000001,001,20121212141024EOF
	    	String str = "SOF0003B00HUNOINJKMk012345678`000001,003,20121212141024EOF" + "\r\n";
	    	Packet packet = new Packet(str.getBytes(), str.length());
	    	conn.send(packet);

	    	Thread.sleep(50000000);
	        conn.close();
	    }
	}
}
