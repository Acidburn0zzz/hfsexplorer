/*-
 * Copyright (C) 2008 Erik Larsson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catacombae.hfs.types.hfs;

import java.io.PrintStream;
import org.catacombae.csjc.StructElements;
import org.catacombae.csjc.structelements.Dictionary;
import org.catacombae.util.Util;

/** This class was generated by CStructToJavaClass. */
public class ExtKeyRec implements StructElements {
    /*
     * struct ExtKeyRec
     * size: 8 bytes
     * description:
     *
     * BP  Size  Type    Identifier  Description
     * ----------------------------------------------------------------------
     * 0   1     SInt8   xkrKeyLen   key length (SignedByte)
     * 1   1     SInt8   xkrFkType   fork type (SignedByte)
     * 2   4     SInt32  xkrFNum     file number (LongInt)
     * 6   2     SInt16  xkrFABN     starting file allocation block (Integer)
     */

    public static final byte FORK_TYPE_DATA = 0x00;
    public static final byte FORK_TYPE_RESOURCE = (byte)0xFF;

    public static final int STRUCTSIZE = 8;

    private final byte[] xkrKeyLen = new byte[1];
    private final byte[] xkrFkType = new byte[1];
    private final byte[] xkrFNum = new byte[4];
    private final byte[] xkrFABN = new byte[2];

    public ExtKeyRec(byte[] data, int offset) {
	System.arraycopy(data, offset+0, xkrKeyLen, 0, 1);
	System.arraycopy(data, offset+1, xkrFkType, 0, 1);
	System.arraycopy(data, offset+2, xkrFNum, 0, 4);
	System.arraycopy(data, offset+6, xkrFABN, 0, 2);
    }

    public ExtKeyRec(byte forkType, int fileID, short startBlock) {
        this.xkrKeyLen[0] = 7; // Constant
        this.xkrFkType[0] = forkType;
        System.arraycopy(Util.toByteArrayBE(fileID), 0, this.xkrFNum, 0, this.xkrFNum.length);
        System.arraycopy(Util.toByteArrayBE(startBlock), 0, this.xkrFABN, 0, this.xkrFABN.length);
    }

    public static int length() { return STRUCTSIZE; }

    /** key length (SignedByte) */
    public byte getXkrKeyLen() { return Util.readByteBE(xkrKeyLen); }
    /** fork type (SignedByte) */
    public byte getXkrFkType() { return Util.readByteBE(xkrFkType); }
    /** file number (LongInt) */
    public int getXkrFNum() { return Util.readIntBE(xkrFNum); }
    /** starting file allocation block (Integer) */
    public short getXkrFABN() { return Util.readShortBE(xkrFABN); }

    public void printFields(PrintStream ps, String prefix) {
	ps.println(prefix + " xkrKeyLen: " + getXkrKeyLen());
	ps.println(prefix + " xkrFkType: " + getXkrFkType());
	ps.println(prefix + " xkrFNum: " + getXkrFNum());
	ps.println(prefix + " xkrFABN: " + getXkrFABN());
    }

    public void print(PrintStream ps, String prefix) {
	ps.println(prefix + "ExtKeyRec:");
	printFields(ps, prefix);
    }

    public byte[] getBytes() {
	byte[] result = new byte[STRUCTSIZE];
	int offset = 0;
	System.arraycopy(xkrKeyLen, 0, result, offset, xkrKeyLen.length); offset += xkrKeyLen.length;
	System.arraycopy(xkrFkType, 0, result, offset, xkrFkType.length); offset += xkrFkType.length;
	System.arraycopy(xkrFNum, 0, result, offset, xkrFNum.length); offset += xkrFNum.length;
	System.arraycopy(xkrFABN, 0, result, offset, xkrFABN.length); offset += xkrFABN.length;
	return result;
    }

    /* @Override */
    public Dictionary getStructElements() {
        DictionaryBuilder db = new DictionaryBuilder("ExtKeyRec", "HFS extent key");

        db.addUIntBE("xkrKeyLen", xkrKeyLen, "Key length", "bytes");
        db.addUIntBE("xkrFkType", xkrFkType, "Fork type");
        db.addUIntBE("xkrFNum", xkrFNum, "File ID");
        db.addUIntBE("xkrFABN", xkrFABN, "Start block number");

        return db.getResult();
    }
}
