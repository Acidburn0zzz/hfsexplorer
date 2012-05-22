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

package org.catacombae.hfsexplorer.types.applesingle;

import java.io.PrintStream;
import org.catacombae.hfsexplorer.fs.AppleSingleBuilder;
import org.catacombae.util.Util;

/** This class was generated by CStructToJavaClass. */
public class AppleSingleHeader {
    /*
     * struct AppleSingleHeader
     * size: 26 bytes
     * description:
     *
     * BP  Size  Type      Identifier      Description
     * -----------------------------------------------
     * 0   4     UInt32    magicNumber
     * 4   4     UInt32    versionNumber
     * 8   1*16  Char[16]  homeFileSystem
     * 24  2     UInt16    numEntries
     */

    public static final int STRUCTSIZE = 26;
    private static final long MAX_UINT = 0xFFFFFFFFL;
    private static final int MAX_USHORT = 0xFFFF;


    private final byte[] magicNumber = new byte[4];
    private final byte[] versionNumber = new byte[4];
    private final byte[] homeFileSystem = new byte[1*16];
    private final byte[] numEntries = new byte[2];

    public AppleSingleHeader(byte[] data, int offset) {
	System.arraycopy(data, offset+0, magicNumber, 0, 4);
	System.arraycopy(data, offset+4, versionNumber, 0, 4);
	System.arraycopy(data, offset+8, homeFileSystem, 0, 1*16);
	System.arraycopy(data, offset+24, numEntries, 0, 2);
    }

    public AppleSingleHeader(long magicNumber, long versionNumber,
            AppleSingleBuilder.FileSystem homeFileSystem, int numEntries) {
        if(magicNumber < 0 || magicNumber > MAX_UINT)
            throw new IllegalArgumentException("Illegal value for magicNumber (" + magicNumber + ")");
        if(versionNumber < 0 || versionNumber > MAX_UINT)
            throw new IllegalArgumentException("Illegal value for versionNumber (" + versionNumber + ")");
        if(homeFileSystem == null)
            throw new IllegalArgumentException("homeFileSystem == null");
        if(numEntries < 0 || numEntries > MAX_USHORT)
            throw new IllegalArgumentException("Illegal value for numEntries (" + numEntries + ")");

        System.arraycopy(Util.toByteArrayBE((int)magicNumber), 0, this.magicNumber, 0, 4);
        System.arraycopy(Util.toByteArrayBE((int)versionNumber), 0, this.versionNumber, 0, 4);
        System.arraycopy(homeFileSystem.getIdentifier(), 0, this.homeFileSystem, 0, 16);
        System.arraycopy(Util.toByteArrayBE((short)numEntries), 0, this.numEntries, 0, 2);
    }

    public static int length() { return STRUCTSIZE; }

    /**  */
    public int getMagicNumber() { return Util.readIntBE(magicNumber); }
    /**  */
    public int getVersionNumber() { return Util.readIntBE(versionNumber); }
    /**  */
    public byte[] getHomeFileSystem() { return Util.readByteArrayBE(homeFileSystem); }
    /**  */
    public short getNumEntries() { return Util.readShortBE(numEntries); }

    public void printFields(PrintStream ps, String prefix) {
	ps.println(prefix + " magicNumber: " + getMagicNumber());
	ps.println(prefix + " versionNumber: 0x" + Util.toHexStringBE(getVersionNumber()));
	ps.println(prefix + " homeFileSystem: \"" + Util.readString(getHomeFileSystem(), "MacRoman") + "\"");
	ps.println(prefix + " numEntries: " + getNumEntries());
    }

    public void print(PrintStream ps, String prefix) {
	ps.println(prefix + "AppleSingleHeader:");
	printFields(ps, prefix);
    }

    public byte[] getBytes() {
	byte[] result = new byte[STRUCTSIZE];
	int offset = 0;
	System.arraycopy(magicNumber, 0, result, offset, magicNumber.length); offset += magicNumber.length;
	System.arraycopy(versionNumber, 0, result, offset, versionNumber.length); offset += versionNumber.length;
	System.arraycopy(homeFileSystem, 0, result, offset, homeFileSystem.length); offset += homeFileSystem.length;
	System.arraycopy(numEntries, 0, result, offset, numEntries.length); offset += numEntries.length;
	return result;
    }
}
