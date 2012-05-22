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

package org.catacombae.hfsexplorer.types.resff;

import java.io.PrintStream;
import org.catacombae.csjc.PrintableStruct;
import org.catacombae.csjc.StaticStruct;
import org.catacombae.util.Util;

/** This class was generated by CStructToJavaClass. */
public class ResourceHeader implements StaticStruct, PrintableStruct {
    /*
     * struct ResourceHeader
     * size: 16 bytes
     * description:
     *
     * BP  Size  Type    Identifier  Description
     * ----------------------------------------------------------------------------------------------------------------------
     * 0   4     UInt32  dataOffset  // Offset from the beginning of the resource fork to the beginning of the resource data.
     * 4   4     UInt32  mapOffset   // Offset from the beginning of the resource fork to the beginning of the resource map.
     * 8   4     UInt32  dataLength  // Length of resource data.
     * 12  4     UInt32  mapLength   // Length of resource map.
     */

    public static final int STRUCTSIZE = 16;

    private final byte[] dataOffset = new byte[4];
    private final byte[] mapOffset = new byte[4];
    private final byte[] dataLength = new byte[4];
    private final byte[] mapLength = new byte[4];

    public ResourceHeader(byte[] data, int offset) {
	System.arraycopy(data, offset+0, dataOffset, 0, 4);
	System.arraycopy(data, offset+4, mapOffset, 0, 4);
	System.arraycopy(data, offset+8, dataLength, 0, 4);
	System.arraycopy(data, offset+12, mapLength, 0, 4);
    }

    public static int length() { return STRUCTSIZE; }

    /** // Offset from the beginning of the resource fork to the beginning of the resource data. */
    public int getDataOffset() { return Util.readIntBE(dataOffset); }
    /** // Offset from the beginning of the resource fork to the beginning of the resource map. */
    public int getMapOffset() { return Util.readIntBE(mapOffset); }
    /** // Length of resource data. */
    public int getDataLength() { return Util.readIntBE(dataLength); }
    /** // Length of resource map. */
    public int getMapLength() { return Util.readIntBE(mapLength); }

    public void printFields(PrintStream ps, String prefix) {
	ps.println(prefix + " dataOffset: " + getDataOffset());
	ps.println(prefix + " mapOffset: " + getMapOffset());
	ps.println(prefix + " dataLength: " + getDataLength());
	ps.println(prefix + " mapLength: " + getMapLength());
    }

    public void print(PrintStream ps, String prefix) {
	ps.println(prefix + "ResourceHeader:");
	printFields(ps, prefix);
    }

    public byte[] getBytes() {
	byte[] result = new byte[length()];
	int offset = 0;
	System.arraycopy(this.dataOffset, 0, result, offset, this.dataOffset.length); offset += this.dataOffset.length;
	System.arraycopy(this.mapOffset, 0, result, offset, this.mapOffset.length); offset += this.mapOffset.length;
	System.arraycopy(this.dataLength, 0, result, offset, this.dataLength.length); offset += this.dataLength.length;
	System.arraycopy(this.mapLength, 0, result, offset, this.mapLength.length); offset += this.mapLength.length;
	return result;
    }

    public int size() {
        return length();
    }
}
