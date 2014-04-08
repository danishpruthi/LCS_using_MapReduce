package Parallel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LCS
{    
	 public static lcsStructure lcsWithPath(java.nio.file.Path p1, java.nio.file.Path p2)throws IOException
	//public static int SeqLCS(String p1, String p2) throws IOException
	 {
		    String x,y;
		    x = readFile(p1.toString(), Charset.defaultCharset());
		    y = readFile(p2.toString(), Charset.defaultCharset());
		   
		    int lent = x.length();
		    String[] t1 = x.split("\n");
	        String[] t2 = y.split("\n");
		    return lcsWithStrings(t1,t2);
	        
	 }
	 public static lcsStructure lcsWithStrings(String line1[],String line2[])
	 {   
	        int i;
	        int M = line1.length;
	        int N = line2.length;
	        int[][] opt = new int[M+1][N+1];
		       
	        // compute length of LCS and all subproblems via dynamic programming
	        for ( i = M-1; i >= 0; i--) 
	        {
	            for (int j = N-1; j >= 0; j--) 
	            {
	                if (line1[i].equals(line2[j]))
	                {
	                    opt[i][j] = opt[i+1][j+1] + 1;
	                    //System.out.println(line1[i]);
	                     
	                }
	                else 
	                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
	            }
	        }
	        i = 0;
	        int j=0;
	        StringBuffer str= new StringBuffer("");
	        while(i<M && j<N)
	        {
	        	if(line1[i].compareTo(line2[j])==0)
	        	{
	        		str.append(line1[i]);
	        		str.append("\n");
	        		i++;
	        		j++;
	        	}
	        	else if(opt[i+1][j] >= opt[i][j+1]) i++;
	        	else j++;
	        }
	        lcsStructure ret = new lcsStructure();
	        ret.len = opt[0][0];
	        ret.lcs = str.toString().split("\n");
	        return ret;
	    }
	 
		static String readFile(String path, Charset encoding) throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return encoding.decode(ByteBuffer.wrap(encoded)).toString();
			}
}