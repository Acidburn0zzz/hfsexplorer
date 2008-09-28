package org.catacombae.hfsexplorer.types.hfs;

import java.io.PrintStream;
import org.catacombae.hfsexplorer.Util;

/** This class was generated by CStructToJavaClass. */
public abstract class CatDataRec {
    /** One of the possible values for cdrType. */
    public static final byte HFS_DIRECTORY_RECORD = 1;
    /** One of the possible values for cdrType. */
    public static final byte HFS_FILE_RECORD = 2;
    /** One of the possible values for cdrType. */
    public static final byte HFS_DIRECTORY_THREAD_RECORD = 3;
    /** One of the possible values for cdrType. */
    public static final byte HFS_FILE_THREAD_RECORD = 4;

    /*
     * struct CatDataRec
     * size: 2 bytes
     * description: 
     * 
     * BP  Size  Type   Identifier  Description             
     * -----------------------------------------------------
     * 0   1     SInt8  cdrType     record type (SignedByte)
     * 1   1     SInt8  cdrResrv2   reserved (SignedByte)   
     */
    
    public static final int STRUCTSIZE = 2;
    
    private final byte[] cdrType = new byte[1];
    private final byte[] cdrResrv2 = new byte[1];
    
    protected CatDataRec(byte[] data, int offset) {
        System.arraycopy(data, offset + 0, cdrType, 0, 1);
        System.arraycopy(data, offset + 1, cdrResrv2, 0, 1);
    }

    public static int length() { return STRUCTSIZE; }
    
    /** record type (SignedByte) */
    public byte getCdrType() { return Util.readByteBE(cdrType); }
    /** reserved (SignedByte) */
    public byte getCdrResrv2() { return Util.readByteBE(cdrResrv2); }
    
    public void printFields(PrintStream ps, String prefix) {
        ps.println(prefix + " cdrType: " + Util.unsign(getCdrType()));
        ps.println(prefix + " cdrResrv2: " + Util.unsign(getCdrResrv2()));
    }
    
    public void print(PrintStream ps, String prefix) {
        ps.println(prefix + "CatDataRec:");
        printFields(ps, prefix);
    }
    
    public byte[] getBytes() {
        byte[] result = new byte[STRUCTSIZE];
        int offset = 0;
        System.arraycopy(cdrType, 0, result, offset, cdrType.length); offset += cdrType.length;
        System.arraycopy(cdrResrv2, 0, result, offset, cdrResrv2.length); offset += cdrResrv2.length;
        return result;
    }
}
