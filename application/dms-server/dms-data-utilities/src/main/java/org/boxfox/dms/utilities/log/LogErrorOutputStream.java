package org.boxfox.dms.utilities.log;
import java.io.OutputStream;
import java.io.PrintStream;
 
public class LogErrorOutputStream extends PrintStream{
    
    public LogErrorOutputStream(OutputStream out) {
        super(out);
    }
    
    public OutputStream getOutputStream(){
        return out;
    }
    
    
    public void setHijackStream(OutputStream arg){
    }
    
    @Override
    public void println(String str){
        Log.e(str);
        super.println(str);
    }
    
    @Override
    public void print(String str){
    	Log.e(str);
        super.print(str);
    }
    
    @Override
    public PrintStream append(CharSequence csq){
    	Log.e(csq);
        return super.append(csq);
    }
 
}